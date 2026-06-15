package com.rifa.tino.dominio.dto;

import lombok.Data;

@Data
public class TicketDonanteDTO {
  private Long idTicketXDonante;
  private Long idTicket;      // Para manejar la FK sin exponer la entidad
  private Long idDonante;     // Para manejar la FK sin exponer la entidad

  // Nuevos campos de solo lectura (para mostrar en respuestas)
  private String nombreDonante;
  private String nroTicket;
  private String estadoTicket;
}