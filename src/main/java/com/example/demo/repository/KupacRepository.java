package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Kupac;

@Repository
public interface KupacRepository extends JpaRepository<Kupac, Long> {
}
