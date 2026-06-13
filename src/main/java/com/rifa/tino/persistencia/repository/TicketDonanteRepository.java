package com.rifa.tino.persistencia.repository;

import com.rifa.tino.persistencia.entity.Ticket;
import com.rifa.tino.persistencia.entity.TicketDonante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDonanteRepository extends JpaRepository<TicketDonante, Long> {

  // Verifica si ya existe una relación para un ticket dado
  boolean existsByTicket(Ticket ticket);
}