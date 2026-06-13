package com.rifa.tino.dominio.serviceImpl;

import com.rifa.tino.dominio.constants.MensajeRespuesta;
import com.rifa.tino.dominio.dto.MessageResponseDTO;
import com.rifa.tino.dominio.dto.DonanteDTO;
import com.rifa.tino.dominio.exception.BusinessException;
import com.rifa.tino.dominio.service.DonanteService;
import com.rifa.tino.persistencia.dao.DonanteDAO;
import com.rifa.tino.persistencia.entity.Donante;
import com.rifa.tino.persistencia.repository.DonanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DonanteServiceImpl implements DonanteService {

  @Autowired
  private DonanteDAO donanteDAO;

  @Autowired
  private DonanteRepository donanteRepository;

  @Override
  public MessageResponseDTO crearDonante(DonanteDTO donanteDTO) {
    // Validar que el email no esté duplicado (si se envía)
    if (donanteDTO.getEmail() != null && donanteRepository.existsByEmail(donanteDTO.getEmail())) {
      throw new BusinessException(MensajeRespuesta.ERROR_REGISTRO_YA_EXISTE);
    }

    // Asignar fecha de creación si no viene en el DTO
    if (donanteDTO.getFechaCreaDonante() == null) {
      donanteDTO.setFechaCreaDonante(LocalDateTime.now());
    }

    Donante nuevoDonante = donanteRepository.save(donanteDAO.toEntity(donanteDTO));

    return MessageResponseDTO.builder()
        .status(MensajeRespuesta.EXITO_REGISTRO_CREADO.getStatus())
        .message(MensajeRespuesta.EXITO_REGISTRO_CREADO.getMensaje())
        .idSecuencial(nuevoDonante.getIdDonante())
        .build();
  }

  @Override
  public MessageResponseDTO listarDonantes() {
    List<Donante> donantes = donanteRepository.findAll();

    if (donantes.isEmpty()) {
      throw new BusinessException(MensajeRespuesta.ERROR_NO_EXISTEN_REGISTROS);
    }

    return MessageResponseDTO.builder()
        .status(MensajeRespuesta.EXITO_REGISTROS_ENCONTRADOS.getStatus())
        .message(MensajeRespuesta.EXITO_REGISTROS_ENCONTRADOS.getMensaje())
        .data(donantes.stream().map(donanteDAO::toDTO).toList())
        .build();
  }

  @Override
  public MessageResponseDTO obtenerDonantePorId(Long idDonante) {
    Optional<Donante> donanteOpt = donanteRepository.findById(idDonante);

    if (donanteOpt.isEmpty()) {
      throw new BusinessException(MensajeRespuesta.ERROR_REGISTRO_NO_ENCONTRADO);
    }

    return MessageResponseDTO.builder()
        .status(MensajeRespuesta.EXITO_REGISTROS_ENCONTRADOS.getStatus())
        .message(MensajeRespuesta.EXITO_REGISTROS_ENCONTRADOS.getMensaje())
        .data(List.of(donanteDAO.toDTO(donanteOpt.get())))
        .build();
  }

  @Override
  public MessageResponseDTO actualizarDonante(Long idDonante, DonanteDTO donanteDTO) {
    Optional<Donante> donanteOpt = donanteRepository.findById(idDonante);

    if (donanteOpt.isEmpty()) {
      throw new BusinessException(MensajeRespuesta.ERROR_REGISTRO_NO_ENCONTRADO);
    }

    Donante donante = donanteOpt.get();

    // Actualización parcial
    if (donanteDTO.getNombreCompleto() != null) {
      donante.setNombreCompleto(donanteDTO.getNombreCompleto());
    }
    if (donanteDTO.getApodo() != null) {
      donante.setApodo(donanteDTO.getApodo());
    }
    if (donanteDTO.getEmail() != null) {
      // Verificar que el nuevo email no esté usado por otro donante
      if (donanteRepository.existsByEmail(donanteDTO.getEmail()) &&
          !donanteDTO.getEmail().equals(donante.getEmail())) {
        throw new BusinessException(MensajeRespuesta.ERROR_REGISTRO_YA_EXISTE);
      }
      donante.setEmail(donanteDTO.getEmail());
    }
    if (donanteDTO.getCelular() != null) {
      donante.setCelular(donanteDTO.getCelular());
    }
    if (donanteDTO.getFechaCreaDonante() != null) {
      donante.setFechaCreaDonante(donanteDTO.getFechaCreaDonante());
    }

    donanteRepository.save(donante);

    return MessageResponseDTO.builder()
        .status(MensajeRespuesta.EXITO_REGISTRO_ACTUALIZADO.getStatus())
        .message(MensajeRespuesta.EXITO_REGISTRO_ACTUALIZADO.getMensaje())
        .idNoSecuencial(String.valueOf(idDonante))
        .build();
  }

  @Override
  public MessageResponseDTO eliminarDonante(Long idDonante) {
    if (!donanteRepository.existsById(idDonante)) {
      throw new BusinessException(MensajeRespuesta.ERROR_REGISTRO_NO_ENCONTRADO);
    }

    donanteRepository.deleteById(idDonante);

    return MessageResponseDTO.builder()
        .status(MensajeRespuesta.EXITO_REGISTRO_ELIMINADO.getStatus())
        .message(MensajeRespuesta.EXITO_REGISTRO_ELIMINADO.getMensaje())
        .idNoSecuencial(String.valueOf(idDonante))
        .build();
  }
}