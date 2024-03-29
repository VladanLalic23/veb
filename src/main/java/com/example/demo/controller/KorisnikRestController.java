package com.example.demo.controller;

import com.example.demo.dto.KorisnikDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.model.Korisnik;
import com.example.demo.model.PolEnum;
import com.example.demo.model.UlogaEnum;
import com.example.demo.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class KorisnikRestController {

    @Autowired
    private KorisnikService korisnikService;

    @PostMapping("/api/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto, HttpSession session) {

        if (registerDto.getKorisnickoIme().isEmpty() || registerDto.getLozinka().isEmpty()
                || registerDto.getIme().isEmpty() || registerDto.getPrezime().isEmpty() ||
                registerDto.getPol().isEmpty()) {
            return new ResponseEntity("Neispravno uneti podaci", HttpStatus.BAD_REQUEST);
        }

        if (korisnikService.getByKorisnickoIme(registerDto.getKorisnickoIme()) != null) {
            return new ResponseEntity("Korisnicko ime vec postoji", HttpStatus.BAD_REQUEST);
        }

        if(registerDto.getUloga() != null && registerDto.getUloga().equals(String.valueOf(UlogaEnum.ADMIN))) {
            return new ResponseEntity("Nije moguce kreirati korisnika sa unesenom rolom", HttpStatus.BAD_REQUEST);
        }

        String uloga;

        if (registerDto.getUloga() == null) {
            uloga = UlogaEnum.KUPAC.toString();
        } else {
            uloga = registerDto.getUloga();
        }

        Korisnik korisnik = new Korisnik();
        korisnik.setKorisnickoIme(registerDto.getKorisnickoIme());
        korisnik.setIme(registerDto.getIme());
        korisnik.setPrezime(registerDto.getPrezime());
        korisnik.setLozinka(registerDto.getLozinka());
        korisnik.setPol(PolEnum.valueOf(registerDto.getPol().toUpperCase()));

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date datumRodjenja = null;
        try {
            datumRodjenja = df.parse(registerDto.getDatumRodjenja());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        korisnik.setDatumRodjenja(datumRodjenja);
        korisnik.setUloga(UlogaEnum.valueOf(uloga));

        korisnikService.registerKorisnik(korisnik);

        return ResponseEntity.ok("Uspesna registracija!");
    }

    @PostMapping("/api/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto, HttpSession session) {

        if (loginDto.getKorisnickoIme().isEmpty() || loginDto.getLozinka().isEmpty()) {
            return new ResponseEntity("Neispravno uneti podaci", HttpStatus.BAD_REQUEST);
        }

        Korisnik loggedKorisnik = korisnikService.login(loginDto.getKorisnickoIme(), loginDto.getLozinka());

        if (loggedKorisnik == null) {
            return new ResponseEntity<>("Korisnik ne postoji!", HttpStatus.NOT_FOUND);
        }

        session.setAttribute("korisnik", loggedKorisnik);
        return ResponseEntity.ok(loggedKorisnik);
    }

    @GetMapping("/api/korisnici/{id}")
    public ResponseEntity getKorisnik(@PathVariable(name = "id") Long id, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if(korisnik == null) {
            return new ResponseEntity<>("Nema sesije!", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(korisnikService.findOne(id));
    }

    @GetMapping("/api/korisnici")
    public ResponseEntity<List<KorisnikDto>> getKorisnici(HttpSession session) {
        List<Korisnik> korisnici = korisnikService.findAll();

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");
        if (loggedKorisnik == null) {
            System.out.println("Nema sesije");
            return new ResponseEntity("Morate biti ulogovani", HttpStatus.BAD_REQUEST);
        }

        if(!loggedKorisnik.getUloga().toString().equals(String.valueOf(UlogaEnum.ADMIN))) {
            return new ResponseEntity("Nije dozvoljen pristup ovim podacima", HttpStatus.BAD_REQUEST);
        }

        List<KorisnikDto> dtos = new ArrayList<>();
        for (Korisnik korisnik : korisnici) {
            KorisnikDto dto = new KorisnikDto(korisnik.getKorisnickoIme(),
                    korisnik.getIme(), korisnik.getPrezime(), korisnik.getPol().toString(),
                    korisnik.getDatumRodjenja().toString(), korisnik.getUloga().toString());
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/api/korisnik/edit")
    public String editKorisnik(@RequestBody Korisnik korisnik) {
        Korisnik editKorisnik = korisnikService.getByKorisnickoIme(korisnik.getKorisnickoIme());

        if(!korisnik.getIme().isEmpty()) {
            editKorisnik.setIme(korisnik.getIme());
        }

        if(!korisnik.getPrezime().isEmpty()) {
            editKorisnik.setPrezime(korisnik.getPrezime());
        }

        if(!korisnik.getDatumRodjenja().toString().isEmpty()) {
            editKorisnik.setDatumRodjenja(korisnik.getDatumRodjenja());
        }

        if(!korisnik.getLozinka().isEmpty()) {
            editKorisnik.setLozinka(korisnik.getLozinka());
        }

        this.korisnikService.save(editKorisnik);
        return "Uspesno sacuvan korisnik!";
    }


    @GetMapping("/api/logout")
    public ResponseEntity logout(HttpSession session){
        session.invalidate();
        return ResponseEntity.ok("Uspesno ste se izlogovali");
    }

    @PostMapping("/api/korisnici/pretraga")
    public ResponseEntity<List<Korisnik>> getByImePrezimeKorisnickoIme(@RequestBody KorisnikDto korisnikDto) {
        List<Korisnik> korisnik = korisnikService.findByImePrezimeKorisnickoIme(korisnikDto.getIme(), korisnikDto.getPrezime(), korisnikDto.getKorisnickoIme());
        return ResponseEntity.ok(korisnik);
    }

}
