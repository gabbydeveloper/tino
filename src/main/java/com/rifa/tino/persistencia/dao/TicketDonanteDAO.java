package com.rifa.tino.persistencia.dao;

import com.rifa.tino.dominio.dto.TicketDonanteDTO;
import com.rifa.tino.dominio.exception.BusinessException;
import com.rifa.tino.dominio.constants.MensajeRespuesta;
import com.rifa.tino.persistencia.entity.Ticket;
import com.rifa.tino.persistencia.entity.Donante;
import com.rifa.tino.persistencia.entity.TicketDonante;
import com.rifa.tino.persistencia.repository.TicketRepository;
import com.rifa.tino.persistencia.repository.DonanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketDonanteDAO {

  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private DonanteRepository donanteRepository;

  // DTO → Entity (con validación de que existan las entidades relacionadas)
  public TicketDonante toEntity(TicketDonanteDTO dto) {
    if (dto == null) return null;

    TicketDonante entity = new TicketDonante();
    entity.setIdTicketXDonante(dto.getIdTicketXDonante());

    // Validar y asignar Ticket
    Ticket ticket = ticketRepository.findById(dto.getIdTicket())
        .orElseThrow(() -> new BusinessException(MensajeRespuesta.ERROR_REGISTRO_MAESTRO_NO_ENCONTRADO));
    entity.setTicket(ticket);

    // Validar y asignar Donante
    Donante donante = donanteRepository.findById(dto.getIdDonante())
        .orElseThrow(() -> new BusinessException(MensajeRespuesta.ERROR_REGISTRO_MAESTRO_NO_ENCONTRADO));
    entity.setDonante(donante);

    return entity;
  }

  // Entity → DTO (extrae solo los IDs de las relaciones)
  public TicketDonanteDTO toDTO(TicketDonante entity) {
    if (entity == null) return null;

    TicketDonanteDTO dto = new TicketDonanteDTO();
    dto.setIdTicketXDonante(entity.getIdTicketXDonante());
    dto.setIdTicket(entity.getTicket().getIdTicket());
    dto.setIdDonante(entity.getDonante().getIdDonante());

    return dto;
  }
}