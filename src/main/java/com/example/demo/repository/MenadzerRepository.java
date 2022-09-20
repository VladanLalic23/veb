package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Menadzer;

import java.util.List;

@Repository
public interface MenadzerRepository extends JpaRepository<Menadzer, Long> {
    public List<Menadzer> findAllByRestoranId(Long restoranId);
}
