package com.rifa.tino.dominio.service;

import com.rifa.tino.dominio.dto.MessageResponseDTO;
import com.rifa.tino.dominio.dto.TicketDonanteDTO;

import java.util.List;

public interface TicketDonanteService {

  MessageResponseDTO crearRelacion(TicketDonanteDTO dto);
  MessageResponseDTO listarRelaciones();
  MessageResponseDTO obtenerRelacionPorId(Long idTicketXDonante);
  MessageResponseDTO actualizarRelacion(Long idTicketXDonante, TicketDonanteDTO dto);
  MessageResponseDTO eliminarRelacion(Long idTicketXDonante);
  // Nuevo método: asignar una lista de tickets a un donante
  MessageResponseDTO asignarTicketsADonante(Long idDonante, List<String> nrosTicket);
}