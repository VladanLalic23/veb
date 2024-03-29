package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Korisnik;

import java.util.List;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
    Korisnik getByKorisnickoIme(String korisnickoIme);

    @Query("SELECT k FROM Korisnik k WHERE k.ime LIKE CONCAT('%', ?1,'%') and k.prezime LIKE CONCAT('%', ?2,'%') and k.korisnickoIme LIKE CONCAT('%', ?3,'%')")
    List<Korisnik> findByImeAndPrezimeAndKorisnickoIme(String ime, String prezime, String korisnickoIme);
}
