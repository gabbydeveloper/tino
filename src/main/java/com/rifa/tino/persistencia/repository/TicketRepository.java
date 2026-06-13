package com.rifa.tino.persistencia.repository;

import com.rifa.tino.persistencia.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

  boolean existsByNroTicket(String nroTicket);

  // Obtener todos los tickets con estado 'AVL', solo id y número
  @Query("SELECT t.idTicket, t.nroTicket FROM Ticket t WHERE t.estadoTicket = 'AVL'")
  List<Object[]> findAvailableTicketsIdsAndNumbers();

  // Obtener solo las entidades completas de tickets disponibles
  @Query("SELECT t FROM Ticket t WHERE t.estadoTicket = 'AVL'")
  List<Ticket> findAllAvailableTickets();

  // Buscar tickets cuyos nro_ticket estén en la lista proporcionada
  List<Ticket> findByNroTicketIn(List<String> nroTickets);

  // Actualizar estado de una lista de tickets (por sus IDs)
  @Modifying
  @Transactional
  @Query("UPDATE Ticket t SET t.estadoTicket = :nuevoEstado WHERE t.idTicket IN :ids")
  int updateEstadoTickets(@Param("ids") List<Long> ids, @Param("nuevoEstado") String nuevoEstado);
}