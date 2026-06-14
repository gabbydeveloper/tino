package com.rifa.tino.persistencia.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tbl_token")
public class Token {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_token", nullable = false)
  private Long idToken;

  @Column(name = "token", nullable = false, length = 100)
  private String token;

  @Column(name = "fecha_uso_token")
  private LocalDateTime fechaUsoToken;
}