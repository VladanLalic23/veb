package com.example.demo.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.KomentarDto;
import com.example.demo.service.KomentarService;

@RestController
public class KomentarRestController {
    @Autowired
    KomentarService komentarService;

  //  @PostMapping("komentar/{id}")
  //  public ResponseEntity<String> dodajKomentar(@PathVariable UUID id, KomentarDto komentarDto, HttpSession session) {
  //      return komentarService.dodajKomentar(id, komentarDto, session);
 //   }
}
    

