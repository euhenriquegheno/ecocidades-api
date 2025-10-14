package com.ecocidades.esg.controller;

import com.ecocidades.esg.model.IndicadorAmbiental;
import com.ecocidades.esg.repository.IndicadorAmbientalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/indicadores-ambientais")
public class IndicadorAmbientalController {

    @Autowired
    private IndicadorAmbientalRepository indicadorAmbientalRepository;

    @GetMapping
    public List<IndicadorAmbiental> getAllIndicadoresAmbientais() {
        return indicadorAmbientalRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndicadorAmbiental> getIndicadorAmbientalById(@PathVariable Long id) {
        Optional<IndicadorAmbiental> indicadorAmbiental = indicadorAmbientalRepository.findById(id);
        return indicadorAmbiental.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IndicadorAmbiental> createIndicadorAmbiental(@RequestBody IndicadorAmbiental indicadorAmbiental) {
        IndicadorAmbiental savedIndicador = indicadorAmbientalRepository.save(indicadorAmbiental);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIndicador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IndicadorAmbiental> updateIndicadorAmbiental(@PathVariable Long id, @RequestBody IndicadorAmbiental indicadorAmbiental) {
        if (!indicadorAmbientalRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        indicadorAmbiental.setId(id);
        IndicadorAmbiental updatedIndicador = indicadorAmbientalRepository.save(indicadorAmbiental);
        return ResponseEntity.ok(updatedIndicador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIndicadorAmbiental(@PathVariable Long id) {
        if (!indicadorAmbientalRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        indicadorAmbientalRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

