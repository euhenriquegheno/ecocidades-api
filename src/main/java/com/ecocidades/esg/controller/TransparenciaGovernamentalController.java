package com.ecocidades.esg.controller;

import com.ecocidades.esg.model.TransparenciaGovernamental;
import com.ecocidades.esg.repository.TransparenciaGovernamentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transparencia-governamental")
public class TransparenciaGovernamentalController {

    @Autowired
    private TransparenciaGovernamentalRepository transparenciaGovernamentalRepository;

    @GetMapping
    public List<TransparenciaGovernamental> getAllTransparenciaGovernamental() {
        return transparenciaGovernamentalRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransparenciaGovernamental> getTransparenciaGovernamentalById(@PathVariable Long id) {
        Optional<TransparenciaGovernamental> transparenciaGovernamental = transparenciaGovernamentalRepository.findById(id);
        return transparenciaGovernamental.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TransparenciaGovernamental> createTransparenciaGovernamental(@RequestBody TransparenciaGovernamental transparenciaGovernamental) {
        TransparenciaGovernamental savedTransparencia = transparenciaGovernamentalRepository.save(transparenciaGovernamental);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransparencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransparenciaGovernamental> updateTransparenciaGovernamental(@PathVariable Long id, @RequestBody TransparenciaGovernamental transparenciaGovernamental) {
        if (!transparenciaGovernamentalRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        transparenciaGovernamental.setId(id);
        TransparenciaGovernamental updatedTransparencia = transparenciaGovernamentalRepository.save(transparenciaGovernamental);
        return ResponseEntity.ok(updatedTransparencia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransparenciaGovernamental(@PathVariable Long id) {
        if (!transparenciaGovernamentalRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        transparenciaGovernamentalRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

