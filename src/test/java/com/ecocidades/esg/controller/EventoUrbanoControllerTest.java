package com.ecocidades.esg.controller;

import com.ecocidades.esg.controller.EventoUrbanoController;
import com.ecocidades.esg.model.EventoUrbano;
import com.ecocidades.esg.repository.EventoUrbanoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EventoUrbanoControllerTest {

    @Mock
    private EventoUrbanoRepository eventoUrbanoRepository;

    @InjectMocks
    private EventoUrbanoController eventoUrbanoController;

    private EventoUrbano evento;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        evento = new EventoUrbano();
        evento.setId(1L);
        evento.setNome("Feira Sustentável");
        evento.setTipo("Feira");
        evento.setData(new Date());
        evento.setLocal("Centro");
        evento.setOrganizadoresJson("[\"Prefeitura\"]");
        evento.setStatus("Ativo");
        evento.setOleoColetadoLitros(50);
        evento.setImpactoJson("{\"arvoresPlantadas\": 10}");
        evento.setCausa("Meio ambiente");
        evento.setAtendimentosRealizados(200);
    }

    @Test
    void deveListarTodosOsEventos() {
        when(eventoUrbanoRepository.findAll()).thenReturn(Arrays.asList(evento));

        List<EventoUrbano> result = eventoUrbanoController.getAllEventosUrbanos();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNome()).isEqualTo("Feira Sustentável");
        verify(eventoUrbanoRepository, times(1)).findAll();
    }

    @Test
    void deveBuscarEventoPorId() {
        when(eventoUrbanoRepository.findById(1L)).thenReturn(Optional.of(evento));

        ResponseEntity<EventoUrbano> response = eventoUrbanoController.getEventoUrbanoById(1L);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(evento);
        verify(eventoUrbanoRepository, times(1)).findById(1L);
    }

    @Test
    void deveRetornarNotFoundAoBuscarEventoInexistente() {
        when(eventoUrbanoRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<EventoUrbano> response = eventoUrbanoController.getEventoUrbanoById(99L);

        assertThat(response.getStatusCode().value()).isEqualTo(404);
    }

    @Test
    void deveCriarEvento() {
        when(eventoUrbanoRepository.save(any(EventoUrbano.class))).thenReturn(evento);

        ResponseEntity<EventoUrbano> response = eventoUrbanoController.createEventoUrbano(evento);

        assertThat(response.getStatusCode().value()).isEqualTo(201);
        assertThat(response.getBody().getNome()).isEqualTo("Feira Sustentável");
        verify(eventoUrbanoRepository, times(1)).save(evento);
    }

    @Test
    void deveAtualizarEvento() {
        when(eventoUrbanoRepository.existsById(1L)).thenReturn(true);
        when(eventoUrbanoRepository.save(any(EventoUrbano.class))).thenReturn(evento);

        ResponseEntity<EventoUrbano> response = eventoUrbanoController.updateEventoUrbano(1L, evento);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        verify(eventoUrbanoRepository, times(1)).save(evento);
    }

    @Test
    void deveExcluirEvento() {
        when(eventoUrbanoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(eventoUrbanoRepository).deleteById(1L);

        ResponseEntity<Void> response = eventoUrbanoController.deleteEventoUrbano(1L);

        assertThat(response.getStatusCode().value()).isEqualTo(204);
        verify(eventoUrbanoRepository, times(1)).deleteById(1L);
    }

    @DisplayName("test fail")
    @Test
    void testFail() throws Exception{
      // fail("Uma nova falha");
    }
}
