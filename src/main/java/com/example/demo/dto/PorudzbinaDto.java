package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.model.*;

public class PorudzbinaDto {

    private String id;

    private Set<Artikal> artikli = new HashSet<>();

    private Long restoranId;

    private double cena;

    private String status;

    private Long kupacId;


    public PorudzbinaDto() {
    }

    public PorudzbinaDto(Set<Artikal> artikli, Long restoranId, double cena, String status, Long kupacId) {
        this.artikli = artikli;
        this.restoranId = restoranId;
        this.cena = cena;
        this.status = status;
        this.kupacId = kupacId;
    }

    public String getId() {
        return id;
    }

    public Set<Artikal> getArtikli() {
        return artikli;
    }

    public void setArtikli(Set<Artikal> artikli) {
        this.artikli = artikli;
    }

    public Long getRestoranId() {
        return restoranId;
    }

    public void setRestoran(Long restoranId) {
        this.restoranId = restoranId;
    }


    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getKupacId() {
        return kupacId;
    }

    public void setKupacId(Long kupacId) {
        this.kupacId = kupacId;
    }


}
