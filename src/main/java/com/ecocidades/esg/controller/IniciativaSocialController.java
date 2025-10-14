package com.ecocidades.esg.controller;

import com.ecocidades.esg.model.IniciativaSocial;
import com.ecocidades.esg.repository.IniciativaSocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/iniciativas-sociais")
public class IniciativaSocialController {

    @Autowired
    private IniciativaSocialRepository iniciativaSocialRepository;

    @GetMapping
    public List<IniciativaSocial> getAllIniciativasSociais() {
        return iniciativaSocialRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IniciativaSocial> getIniciativaSocialById(@PathVariable Long id) {
        Optional<IniciativaSocial> iniciativaSocial = iniciativaSocialRepository.findById(id);
        return iniciativaSocial.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IniciativaSocial> createIniciativaSocial(@RequestBody IniciativaSocial iniciativaSocial) {
        IniciativaSocial savedIniciativa = iniciativaSocialRepository.save(iniciativaSocial);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIniciativa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IniciativaSocial> updateIniciativaSocial(@PathVariable Long id, @RequestBody IniciativaSocial iniciativaSocial) {
        if (!iniciativaSocialRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        iniciativaSocial.setId(id);
        IniciativaSocial updatedIniciativa = iniciativaSocialRepository.save(iniciativaSocial);
        return ResponseEntity.ok(updatedIniciativa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIniciativaSocial(@PathVariable Long id) {
        if (!iniciativaSocialRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        iniciativaSocialRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

