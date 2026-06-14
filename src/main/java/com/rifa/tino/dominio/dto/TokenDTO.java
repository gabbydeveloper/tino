package com.rifa.tino.dominio.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TokenDTO {
  private Long idToken;
  private String token;
  private LocalDateTime fechaUsoToken;
}