package com.example.demo.controller;

import com.example.demo.dto.PorudzbinaDto;
import com.example.demo.model.*;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.PorudzbinaService;
import com.example.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@RestController
public class PorudzbinaRestController {

    @Autowired
    private PorudzbinaService porudzbinaService;

    @Autowired
    RestoranService restoranService;

    @Autowired
    KorisnikService korisnikService;

    @GetMapping("/api/porudzbine/restoran/{id}")
    public ResponseEntity getPorudzbineByRestoran(@PathVariable(name = "id") Long id, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if (korisnik == null) {
            return new ResponseEntity("Sesija ne postoji", HttpStatus.FORBIDDEN);
        }
        if (!korisnik.getUloga().toString().equals(String.valueOf(UlogaEnum.MENADZER))) {
            return new ResponseEntity("Nije dozvoljen pristup podacima!", HttpStatus.BAD_REQUEST);
        }
        List<Porudzbina> porudzbine = porudzbinaService.getPorudzbineByRestoran(id);
        return ResponseEntity.ok(porudzbine);
    }

    @GetMapping("/api/porudzbine/kupac/{id}")
    public ResponseEntity<List<Porudzbina>> getPorudzbineByKupac(@PathVariable(name = "id") Long id) {
        List<Porudzbina> porudzbine = porudzbinaService.getPorudzbineByKupac(id);
        return ResponseEntity.ok(porudzbine);
    }

    @GetMapping("/api/porudzbine/status/{status}")
    public ResponseEntity getPorudzbineByStatus(@PathVariable(name = "status") String status, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if (korisnik == null) {
            return new ResponseEntity<>("Nema sesije!", HttpStatus.BAD_REQUEST);
        }


        List<Porudzbina> porudzbine = porudzbinaService.getPorudzbineByStatus(StatusPorudzbineEnum.valueOf(status.toUpperCase()));
        return ResponseEntity.ok(porudzbine);
    }

    @PostMapping("/api/porudzbina/create")
    public ResponseEntity createPorudzbina(@RequestBody PorudzbinaDto porudzbinaDto, HttpSession httpSession) {
    
        Korisnik korisnik = (Korisnik) httpSession.getAttribute("korisnik");
        if (korisnik == null) {
            return new ResponseEntity<>("Nema sesije!", HttpStatus.BAD_REQUEST);
        }

        if (!korisnik.getUloga().toString().equals(String.valueOf(UlogaEnum.KUPAC))) {
            return new ResponseEntity<>("Nije dozvoljeno kreirati porudzbinu", HttpStatus.BAD_REQUEST);
        }

        Porudzbina porudzbina = new Porudzbina();
        porudzbina.setArtikli(porudzbinaDto.getArtikli());
        porudzbina.setCena(porudzbinaDto.getCena());

        Restoran restoran = restoranService.findOne(porudzbinaDto.getRestoranId());
        porudzbina.setRestoran(restoran);

        porudzbina.setDatumIVreme(porudzbina.getDatumIVreme());
        porudzbina.setStatus(StatusPorudzbineEnum.OBRADA);

        Kupac kupac = (Kupac) korisnikService.findOne(porudzbinaDto.getKupacId());
        porudzbina.setKupac(kupac);

        this.porudzbinaService.savePorudzbina(porudzbina);
        return ResponseEntity.ok("Uspesno kreirana porudzbina!");
    }

    @PutMapping("/api/porudzbina/edit")
    public ResponseEntity editPorudzbina(@RequestBody PorudzbinaDto porudzbinaDto, HttpSession httpSession) {
        Korisnik korisnik = (Korisnik) httpSession.getAttribute("korisnik");
        if (korisnik == null) {
            return new ResponseEntity<>("Nema sesije!", HttpStatus.BAD_REQUEST);
        }
        StatusPorudzbineEnum status = StatusPorudzbineEnum.valueOf(porudzbinaDto.getStatus().toUpperCase());

        if (!korisnik.getUloga().toString().equals(String.valueOf(UlogaEnum.MENADZER))) {
    return new ResponseEntity<>("Nije dozvoljeno menjati porudzbinu", HttpStatus.BAD_REQUEST);
        }
        if (korisnik.getUloga().toString().equals(String.valueOf(UlogaEnum.MENADZER)) &&
        (status.toString().equals(StatusPorudzbineEnum.OTKAZANA) || status.toString().equals(StatusPorudzbineEnum.GOTOVA))) {
    return new ResponseEntity<>("Nije dozvoljeno menjati porudzbinu", HttpStatus.BAD_REQUEST);
}


        UUID uuid = UUID.fromString(porudzbinaDto.getId());
       // UUID uuid= porudzbinaDto.getId();
        Porudzbina porudzbina = porudzbinaService.findOne(uuid);
        porudzbina.setStatus(status);

        this.porudzbinaService.savePorudzbina(porudzbina);
        return ResponseEntity.ok("Uspesno izmenjena porudzbina!");
    }
}