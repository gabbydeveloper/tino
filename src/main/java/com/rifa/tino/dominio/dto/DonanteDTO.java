package com.rifa.tino.dominio.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DonanteDTO {
  private Long idDonante;
  private String nombreCompleto;
  private String apodo;
  private String email;
  private String celular;
  private LocalDateTime fechaCreaDonante;
}