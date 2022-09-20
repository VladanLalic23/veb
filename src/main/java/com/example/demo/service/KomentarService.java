package com.example.demo.service;

import com.example.demo.dto.KomentarDto;
import com.example.demo.model.Komentar;
import com.example.demo.model.Korisnik;
import com.example.demo.repository.KomentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

@Service
public class KomentarService {

    @Autowired
    private KomentarRepository komentarRepository;
    private PorudzbinaService porudzbinaService;

    public List<Komentar> getAllByRestoranId(Long restoranId) {
        return komentarRepository.getByRestoranId(restoranId);
    }

    public void deleteKomentar(Komentar komentar) {
        komentarRepository.delete(komentar);
    }

}
