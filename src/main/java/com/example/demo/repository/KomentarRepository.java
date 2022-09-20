package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Komentar;

import java.util.List;

@Repository
public interface KomentarRepository extends JpaRepository<Komentar, Long> {
    List<Komentar> getByRestoranId(Long restoranId);
}
