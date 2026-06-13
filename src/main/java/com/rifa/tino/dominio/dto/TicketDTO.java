package com.rifa.tino.dominio.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketDTO {
  private Long idTicket;
  private String nroTicket;
  private String estadoTicket;
  private LocalDateTime fechaHoraCompra;
}