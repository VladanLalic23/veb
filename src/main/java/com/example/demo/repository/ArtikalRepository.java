package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Artikal;

import java.util.List;

@Repository
public interface ArtikalRepository extends JpaRepository<Artikal, Long> {
    public List<Artikal> findByRestoranId(Long restoranId);
}
