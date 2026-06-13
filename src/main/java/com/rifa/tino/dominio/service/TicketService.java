package com.rifa.tino.dominio.service;

import com.rifa.tino.dominio.dto.MessageResponseDTO;
import com.rifa.tino.dominio.dto.TicketDTO;

public interface TicketService {

  MessageResponseDTO crearTicket(TicketDTO ticketDTO);
  MessageResponseDTO listarTickets();
  MessageResponseDTO obtenerTicketPorId(Long idTicket);
  MessageResponseDTO actualizarTicket(Long idTicket, TicketDTO ticketDTO);
  MessageResponseDTO eliminarTicket(Long idTicket);
  MessageResponseDTO enviarTicketsAleatorios(Integer nroTicketsReservar);
}