package com.ecocidades.esg.controller;

import com.ecocidades.esg.model.EventoUrbano;
import com.ecocidades.esg.repository.EventoUrbanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventos-urbanos")
public class EventoUrbanoController {

    @Autowired
    private EventoUrbanoRepository eventoUrbanoRepository;

    @GetMapping
    public List<EventoUrbano> getAllEventosUrbanos() {
        return eventoUrbanoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoUrbano> getEventoUrbanoById(@PathVariable Long id) {
        Optional<EventoUrbano> eventoUrbano = eventoUrbanoRepository.findById(id);
        return eventoUrbano.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EventoUrbano> createEventoUrbano(@RequestBody EventoUrbano eventoUrbano) {
        EventoUrbano savedEvento = eventoUrbanoRepository.save(eventoUrbano);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoUrbano> updateEventoUrbano(@PathVariable Long id, @RequestBody EventoUrbano eventoUrbano) {
        if (!eventoUrbanoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        eventoUrbano.setId(id);
        EventoUrbano updatedEvento = eventoUrbanoRepository.save(eventoUrbano);
        return ResponseEntity.ok(updatedEvento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventoUrbano(@PathVariable Long id) {
        if (!eventoUrbanoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        eventoUrbanoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

