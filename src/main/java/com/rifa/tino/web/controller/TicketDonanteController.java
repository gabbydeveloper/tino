package com.rifa.tino.web.controller;

import com.rifa.tino.dominio.dto.MessageResponseDTO;
import com.rifa.tino.dominio.dto.TicketDonanteDTO;
import com.rifa.tino.dominio.service.TicketDonanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket-donantes")
public class TicketDonanteController {

  @Autowired
  private TicketDonanteService ticketDonanteService;

  @PostMapping
  public ResponseEntity<MessageResponseDTO> crearRelacion(@RequestBody TicketDonanteDTO dto) {
    return ResponseEntity.ok(ticketDonanteService.crearRelacion(dto));
  }

  @GetMapping
  public ResponseEntity<MessageResponseDTO> listarRelaciones() {
    return ResponseEntity.ok(ticketDonanteService.listarRelaciones());
  }

  @GetMapping("/{idTicketXDonante}")
  public ResponseEntity<MessageResponseDTO> obtenerRelacionPorId(@PathVariable Long idTicketXDonante) {
    return ResponseEntity.ok(ticketDonanteService.obtenerRelacionPorId(idTicketXDonante));
  }

  @PutMapping("/{idTicketXDonante}")
  public ResponseEntity<MessageResponseDTO> actualizarRelacion(@PathVariable Long idTicketXDonante,
                                                               @RequestBody TicketDonanteDTO dto) {
    return ResponseEntity.ok(ticketDonanteService.actualizarRelacion(idTicketXDonante, dto));
  }

  @DeleteMapping("/{idTicketXDonante}")
  public ResponseEntity<MessageResponseDTO> eliminarRelacion(@PathVariable Long idTicketXDonante) {
    return ResponseEntity.ok(ticketDonanteService.eliminarRelacion(idTicketXDonante));
  }

  @PostMapping("/asignar/{idDonante}")
  public ResponseEntity<MessageResponseDTO> asignarTickets(@PathVariable Long idDonante,
                                                           @RequestBody List<String> nrosTicket) {
    return ResponseEntity.ok(ticketDonanteService.asignarTicketsADonante(idDonante, nrosTicket));
  }
}