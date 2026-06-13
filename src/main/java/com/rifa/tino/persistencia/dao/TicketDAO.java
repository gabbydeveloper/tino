package com.rifa.tino.persistencia.dao;

import com.rifa.tino.dominio.dto.TicketDTO;
import com.rifa.tino.persistencia.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketDAO {

  // DTO → Entity
  public Ticket toEntity(TicketDTO dto) {
    if (dto == null) return null;
    Ticket entity = new Ticket();
    entity.setIdTicket(dto.getIdTicket());
    entity.setNroTicket(dto.getNroTicket());
    entity.setEstadoTicket(dto.getEstadoTicket());
    entity.setFechaHoraCompra(dto.getFechaHoraCompra());
    return entity;
  }

  // Entity → DTO
  public TicketDTO toDTO(Ticket entity) {
    if (entity == null) return null;
    TicketDTO dto = new TicketDTO();
    dto.setIdTicket(entity.getIdTicket());
    dto.setNroTicket(entity.getNroTicket());
    dto.setEstadoTicket(entity.getEstadoTicket());
    dto.setFechaHoraCompra(entity.getFechaHoraCompra());
    return dto;
  }
}