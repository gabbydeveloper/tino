package com.rifa.tino.dominio.serviceImpl;

import com.rifa.tino.dominio.constants.MensajeRespuesta;
import com.rifa.tino.dominio.dto.MessageResponseDTO;
import com.rifa.tino.dominio.dto.TicketDTO;
import com.rifa.tino.dominio.exception.BusinessException;
import com.rifa.tino.dominio.service.TicketService;
import com.rifa.tino.persistencia.dao.TicketDAO;
import com.rifa.tino.persistencia.entity.Ticket;
import com.rifa.tino.persistencia.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

  @Autowired
  private TicketDAO ticketDAO;

  @Autowired
  private TicketRepository ticketRepository;

  @Override
  public MessageResponseDTO crearTicket(TicketDTO ticketDTO) {
    if (ticketDTO.getNroTicket() != null && ticketRepository.existsByNroTicket(ticketDTO.getNroTicket())) {
      throw new BusinessException(MensajeRespuesta.ERROR_REGISTRO_YA_EXISTE);
    }

    Ticket nuevoTicket = ticketRepository.save(ticketDAO.toEntity(ticketDTO));

    return MessageResponseDTO.builder()
        .status(MensajeRespuesta.EXITO_REGISTRO_CREADO.getStatus())
        .message(MensajeRespuesta.EXITO_REGISTRO_CREADO.getMensaje())
        .idSecuencial(nuevoTicket.getIdTicket())
        .build();
  }

  @Override
  public MessageResponseDTO listarTickets() {
    var tickets = ticketRepository.findAll();

    if (tickets.isEmpty()) {
      throw new BusinessException(MensajeRespuesta.ERROR_NO_EXISTEN_REGISTROS);
    }

    return MessageResponseDTO.builder()
        .status(MensajeRespuesta.EXITO_REGISTROS_ENCONTRADOS.getStatus())
        .message(MensajeRespuesta.EXITO_REGISTROS_ENCONTRADOS.getMensaje())
        .data(tickets.stream().map(ticketDAO::toDTO).toList())
        .build();
  }

  @Override
  public MessageResponseDTO obtenerTicketPorId(Long idTicket) {
    Optional<Ticket> ticketOpt = ticketRepository.findById(idTicket);

    if (ticketOpt.isEmpty()) {
      throw new BusinessException(MensajeRespuesta.ERROR_REGISTRO_NO_ENCONTRADO);
    }

    return MessageResponseDTO.builder()
        .status(MensajeRespuesta.EXITO_REGISTROS_ENCONTRADOS.getStatus())
        .message(MensajeRespuesta.EXITO_REGISTROS_ENCONTRADOS.getMensaje())
        .data(List.of(ticketDAO.toDTO(ticketOpt.get())))
        .build();
  }

  @Override
  public MessageResponseDTO actualizarTicket(Long idTicket, TicketDTO ticketDTO) {
    Optional<Ticket> ticketOpt = ticketRepository.findById(idTicket);

    if (ticketOpt.isEmpty()) {
      throw new BusinessException(MensajeRespuesta.ERROR_REGISTRO_NO_ENCONTRADO);
    }

    Ticket ticket = ticketOpt.get();

    if (ticketDTO.getNroTicket() != null) {
      ticket.setNroTicket(ticketDTO.getNroTicket());
    }
    if (ticketDTO.getEstadoTicket() != null) {
      ticket.setEstadoTicket(ticketDTO.getEstadoTicket());
    }
    if (ticketDTO.getFechaHoraCompra() != null) {
      ticket.setFechaHoraCompra(ticketDTO.getFechaHoraCompra());
    }

    ticketRepository.save(ticket);

    return MessageResponseDTO.builder()
        .status(MensajeRespuesta.EXITO_REGISTRO_ACTUALIZADO.getStatus())
        .message(MensajeRespuesta.EXITO_REGISTRO_ACTUALIZADO.getMensaje())
        .idSecuencial(idTicket)
        .build();
  }

  @Override
  public MessageResponseDTO eliminarTicket(Long idTicket) {
    if (!ticketRepository.existsById(idTicket)) {
      throw new BusinessException(MensajeRespuesta.ERROR_REGISTRO_NO_ENCONTRADO);
    }

    ticketRepository.deleteById(idTicket);

    return MessageResponseDTO.builder()
        .status(MensajeRespuesta.EXITO_REGISTRO_ELIMINADO.getStatus())
        .message(MensajeRespuesta.EXITO_REGISTRO_ELIMINADO.getMensaje())
        .idSecuencial(idTicket)
        .build();
  }

  @Override
  public MessageResponseDTO enviarTicketsAleatorios(Integer nroTicketsReservar) {
    // Obtener todos los tickets disponibles (estado 'AVL')
    List<Ticket> ticketsDisponibles = ticketRepository.findAllAvailableTickets();

    if (ticketsDisponibles.isEmpty()) {
      throw new BusinessException(MensajeRespuesta.ERROR_NO_EXISTEN_REGISTROS);
    }

    // Si se piden más tickets de los disponibles, ajustamos
    int cantidad = Math.min(nroTicketsReservar, ticketsDisponibles.size());

    // Selección aleatoria (sin repetición)
    Collections.shuffle(ticketsDisponibles);
    List<Ticket> seleccionados = ticketsDisponibles.subList(0, cantidad);

    // Extraer los IDs para la actualización
    List<Long> idsSeleccionados = seleccionados.stream()
        .map(Ticket::getIdTicket)
        .collect(Collectors.toList());

    // Cambiar el estado de los tickets seleccionados a 'RES' (reservado)
    int actualizados = ticketRepository.updateEstadoTickets(idsSeleccionados, "RES");

    if (actualizados != cantidad) {
      // Esto no debería ocurrir gracias al bloqueo, pero por si acaso
      throw new BusinessException(MensajeRespuesta.ERROR_NO_EXISTEN_REGISTROS);
    }

    // Extraer solo los números de ticket (nro_ticket)
    List<String> numerosTicket = seleccionados.stream()
        .map(Ticket::getNroTicket)
        .collect(Collectors.toList());

    // Pero por ahora solo los números
    return MessageResponseDTO.builder()
        .status(MensajeRespuesta.EXITO_OPERACION.getStatus())
        .message("Se seleccionaron " + cantidad + " tickets aleatorios.")
        .data(numerosTicket)
        .build();
  }
}