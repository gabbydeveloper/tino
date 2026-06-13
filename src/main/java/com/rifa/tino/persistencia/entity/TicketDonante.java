package com.rifa.tino.persistencia.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_ticket_x_donante")
public class TicketDonante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_ticket_x_donante", nullable = false)
  private Long idTicketXDonante;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_ticket", nullable = false)
  private Ticket ticket;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_donante", nullable = false)
  private Donante donante;
}