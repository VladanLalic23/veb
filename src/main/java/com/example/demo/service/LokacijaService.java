package com.example.demo.service;

import com.example.demo.model.Lokacija;
import com.example.demo.repository.LokacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class LokacijaService {

    @Autowired
    LokacijaRepository lokacijaRepository;

    public Lokacija getLokacijaByAdresa(String adresa) {
        return lokacijaRepository.getByAdresa(adresa);
    }

    public void saveLokacija(Lokacija lokacija) {
        lokacijaRepository.save(lokacija);
    }
}
