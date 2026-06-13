package com.rifa.tino.web.controller;

import com.rifa.tino.dominio.dto.MessageResponseDTO;
import com.rifa.tino.dominio.dto.TicketDTO;
import com.rifa.tino.dominio.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

  @Autowired
  private TicketService ticketService;

  @PostMapping
  public ResponseEntity<MessageResponseDTO> crearTicket(@RequestBody TicketDTO ticketDTO) {
    return ResponseEntity.ok(ticketService.crearTicket(ticketDTO));
  }

  @GetMapping
  public ResponseEntity<MessageResponseDTO> listarTickets() {
    return ResponseEntity.ok(ticketService.listarTickets());
  }

  @GetMapping("/{idTicket}")
  public ResponseEntity<MessageResponseDTO> obtenerTicketPorId(@PathVariable Long idTicket) {
    return ResponseEntity.ok(ticketService.obtenerTicketPorId(idTicket));
  }

  @PutMapping("/{idTicket}")
  public ResponseEntity<MessageResponseDTO> actualizarTicket(@PathVariable Long idTicket,
                                                             @RequestBody TicketDTO ticketDTO) {
    return ResponseEntity.ok(ticketService.actualizarTicket(idTicket, ticketDTO));
  }

  @DeleteMapping("/{idTicket}")
  public ResponseEntity<MessageResponseDTO> eliminarTicket(@PathVariable Long idTicket) {
    return ResponseEntity.ok(ticketService.eliminarTicket(idTicket));
  }

  @GetMapping("/aleatorios/{cantidad}")
  public ResponseEntity<MessageResponseDTO> enviarTicketsAleatorios(@PathVariable Integer cantidad) {
    return ResponseEntity.ok(ticketService.enviarTicketsAleatorios(cantidad));
  }
}