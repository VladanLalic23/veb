package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Menadzer extends Korisnik  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "restoran_id")
    private Restoran restoran;

    public Menadzer(){}

    public Menadzer(String korisnickoIme, String lozinka, String ime, String prezime, PolEnum pol, Date datumRodjenja, UlogaEnum uloga, Restoran restoran) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, uloga);
        this.restoran = restoran;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }
}
