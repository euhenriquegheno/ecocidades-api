package com.ecocidades.esg.controller;

import com.ecocidades.esg.model.FeedbackCidadao;
import com.ecocidades.esg.repository.FeedbackCidadaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/feedback-cidadao")
public class FeedbackCidadaoController {

    @Autowired
    private FeedbackCidadaoRepository feedbackCidadaoRepository;

    @GetMapping
    public List<FeedbackCidadao> getAllFeedbackCidadao() {
        return feedbackCidadaoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackCidadao> getFeedbackCidadaoById(@PathVariable Long id) {
        Optional<FeedbackCidadao> feedbackCidadao = feedbackCidadaoRepository.findById(id);
        return feedbackCidadao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FeedbackCidadao> createFeedbackCidadao(@RequestBody FeedbackCidadao feedbackCidadao) {
        FeedbackCidadao savedFeedback = feedbackCidadaoRepository.save(feedbackCidadao);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFeedback);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackCidadao> updateFeedbackCidadao(@PathVariable Long id, @RequestBody FeedbackCidadao feedbackCidadao) {
        if (!feedbackCidadaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        feedbackCidadao.setId(id);
        FeedbackCidadao updatedFeedback = feedbackCidadaoRepository.save(feedbackCidadao);
        return ResponseEntity.ok(updatedFeedback);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedbackCidadao(@PathVariable Long id) {
        if (!feedbackCidadaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        feedbackCidadaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

