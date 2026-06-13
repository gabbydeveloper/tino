package com.rifa.tino.dominio.dto;

import lombok.Data;

@Data
public class TicketDonanteDTO {
  private Long idTicketXDonante;
  private Long idTicket;      // Para manejar la FK sin exponer la entidad
  private Long idDonante;     // Para manejar la FK sin exponer la entidad
}