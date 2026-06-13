package com.rifa.tino.persistencia.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tbl_ticket")
public class Ticket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_ticket", nullable = false)
  private Long idTicket;

  @Column(name = "nro_ticket", nullable = false)
  private String nroTicket;

  @Column(name = "estado_ticket", nullable = false)
  private String estadoTicket;

  @Column(name = "fecha_hora_compra", nullable = false)
  private LocalDateTime fechaHoraCompra;
}