package com.rifa.tino.dominio.serviceImpl;

import com.rifa.tino.dominio.constants.MensajeRespuesta;
import com.rifa.tino.dominio.dto.MessageResponseDTO;
import com.rifa.tino.dominio.dto.TicketDonanteDTO;
import com.rifa.tino.dominio.exception.BusinessException;
import com.rifa.tino.dominio.service.TicketDonanteService;
import com.rifa.tino.persistencia.dao.TicketDonanteDAO;
import com.rifa.tino.persistencia.entity.Donante;
import com.rifa.tino.persistencia.entity.Ticket;
import com.rifa.tino.persistencia.entity.TicketDonante;
import com.rifa.tino.persistencia.repository.TicketDonanteRepository;
import com.rifa.tino.persistencia.repository.TicketRepository;
import com.rifa.tino.persistencia.repository.DonanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketDonanteServiceImpl implements TicketDonanteService {

  @Autowired
  private TicketDonanteDAO ticketDonanteDAO;

  @Autowired
  private TicketDonanteRepository ticketDonanteRepository;

  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private DonanteRepository donanteRepository;

  @Override
  public MessageResponseDTO crearRelacion(TicketDonanteDTO dto) {
    // El DAO ya valida que existan Ticket y Donante
    TicketDonante nuevaRelacion = ticketDonanteRepository.save(ticketDonanteDAO.toEntity(dto));

    return MessageResponseDTO.builder()
        .status(MensajeRespuesta.EXITO_REGISTRO_CREADO.getStatus())
        .message(MensajeRespuesta.EXITO_REGISTRO_CREADO.getMensaje())
        .idNoSecuencial(String.valueOf(nuevaRelacion.getIdTicketXDonante()))
        .build();
  }

  @Override
  public MessageResponseDTO listarRelaciones() {
    List<TicketDonante> relaciones = ticketDonanteRepository.findAll();

    if (relaciones.isEmpty()) {
      throw new BusinessException(MensajeRespuesta.ERROR_NO_EXISTEN_REGISTROS);
    }

    return MessageResponseDTO.builder()
        .status(MensajeRespuesta.EXITO_REGISTROS_ENCONTRADOS.getStatus())
        .message(MensajeRespuesta.EXITO_REGISTROS_ENCONTRADOS.getMensaje())
        .data(relaciones.stream().map(ticketDonanteDAO::toDTO).toList())
        .build();
  }

  @Override
  public MessageResponseDTO obtenerRelacionPorId(Long idTicketXDonante) {
    Optional<TicketDonante> relacionOpt = ticketDonanteRepository.findById(idTicketXDonante);

    if (relacionOpt.isEmpty()) {
      throw new BusinessException(MensajeRespuesta.ERROR_REGISTRO_NO_ENCONTRADO);
    }

    return MessageResponseDTO.builder()
        .status(MensajeRespuesta.EXITO_REGISTROS_ENCONTRADOS.getStatus())
        .message(MensajeRespuesta.EXITO_REGISTROS_ENCONTRADOS.getMensaje())
        .data(List.of(ticketDonanteDAO.toDTO(relacionOpt.get())))
        .build();
  }

  @Override
  public MessageResponseDTO actualizarRelacion(Long idTicketXDonante, TicketDonanteDTO dto) {
    Optional<TicketDonante> relacionOpt = ticketDonanteRepository.findById(idTicketXDonante);

    if (relacionOpt.isEmpty()) {
      throw new BusinessException(MensajeRespuesta.ERROR_REGISTRO_NO_ENCONTRADO);
    }

    TicketDonante relacion = relacionOpt.get();

    // Actualizar Ticket si se envía un nuevo idTicket
    if (dto.getIdTicket() != null) {
      Optional<Ticket> ticketExiste = ticketRepository.findById(dto.getIdTicket());
      if (ticketExiste.isEmpty()) {
        throw new BusinessException(MensajeRespuesta.ERROR_REGISTRO_MAESTRO_NO_ENCONTRADO);
      }
      relacion.setTicket(ticketExiste.get());
    }

    // Actualizar Donante si se envía un nuevo idDonante
    if (dto.getIdDonante() != null) {
      Optional<Donante> donanteExiste = donanteRepository.findById(dto.getIdDonante());
      if (donanteExiste.isEmpty()) {
        throw new BusinessException(MensajeRespuesta.ERROR_REGISTRO_MAESTRO_NO_ENCONTRADO);
      }
      relacion.setDonante(donanteExiste.get());
    }

    ticketDonanteRepository.save(relacion);

    return MessageResponseDTO.builder()
        .status(MensajeRespuesta.EXITO_REGISTRO_ACTUALIZADO.getStatus())
        .message(MensajeRespuesta.EXITO_REGISTRO_ACTUALIZADO.getMensaje())
        .idNoSecuencial(String.valueOf(idTicketXDonante))
        .build();
  }

  @Override
  public MessageResponseDTO eliminarRelacion(Long idTicketXDonante) {
    if (!ticketDonanteRepository.existsById(idTicketXDonante)) {
      throw new BusinessException(MensajeRespuesta.ERROR_REGISTRO_NO_ENCONTRADO);
    }

    ticketDonanteRepository.deleteById(idTicketXDonante);

    return MessageResponseDTO.builder()
        .status(MensajeRespuesta.EXITO_REGISTRO_ELIMINADO.getStatus())
        .message(MensajeRespuesta.EXITO_REGISTRO_ELIMINADO.getMensaje())
        .idNoSecuencial(String.valueOf(idTicketXDonante))
        .build();
  }

  @Override
  public MessageResponseDTO asignarTicketsADonante(Long idDonante, List<String> nrosTicket) {
    // Verificar que el donante existe
    Donante donante = donanteRepository.findById(idDonante)
        .orElseThrow(() -> new BusinessException(MensajeRespuesta.ERROR_REGISTRO_NO_ENCONTRADO));

    // Verificar que todos los tickets existen
    List<Ticket> tickets = ticketRepository.findByNroTicketIn(nrosTicket);
    if (tickets.size() != nrosTicket.size()) {
      throw new BusinessException(MensajeRespuesta.ERROR_REGISTRO_NO_ENCONTRADO);
    }

    // Verificar que ningún ticket esté ya asignado a otro donante? Opcional
    // Por simplicidad, asumimos que pueden estar libres o repetidos; pero si se quiere evitar duplicados:
    for (Ticket ticket : tickets) {
      boolean yaAsignado = ticketDonanteRepository.existsByTicket(ticket);
      if (yaAsignado) {
        throw new BusinessException(MensajeRespuesta.ERROR_TICKET_ASIGNADO);
      }
    }

    // Crear las relaciones
    List<TicketDonante> relaciones = new ArrayList<>();
    for (Ticket ticket : tickets) {
      TicketDonante relacion = new TicketDonante();
      relacion.setTicket(ticket);
      relacion.setDonante(donante);
      relaciones.add(relacion);
    }
    ticketDonanteRepository.saveAll(relaciones);

    // Opcional: actualizar estado de los tickets a 'RES' (reservado) si se desea
    // Si el usuario dice que lo hará aparte, comentamos o dejamos opcional.
    // ticketRepository.updateEstadoTickets(idsTicket, "RES");

    return MessageResponseDTO.builder()
        .status(MensajeRespuesta.EXITO_REGISTRO_CREADO.getStatus())
        .message("Se asignaron " + relaciones.size() + " tickets al donante.")
        .build();
  }
}