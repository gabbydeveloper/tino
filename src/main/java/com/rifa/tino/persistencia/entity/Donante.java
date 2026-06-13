package com.rifa.tino.persistencia.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tbl_donante")
public class Donante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_donante", nullable = false)
  private Long idDonante;

  @Column(name = "nombre_completo", nullable = false)
  private String nombreCompleto;

  @Column(name = "apodo")
  private String apodo;

  @Column(name = "email")
  private String email;

  @Column(name = "celular")
  private String celular;

  @Column(name = "fecha_crea_donante", nullable = false)
  private LocalDateTime fechaCreaDonante;
}