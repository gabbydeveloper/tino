package com.rifa.tino.dominio.serviceImpl;

import com.rifa.tino.dominio.constants.MensajeRespuesta;
import com.rifa.tino.dominio.dto.MessageResponseDTO;
import com.rifa.tino.dominio.dto.TokenDTO;
import com.rifa.tino.dominio.exception.BusinessException;
import com.rifa.tino.dominio.service.TokenService;
import com.rifa.tino.persistencia.dao.TokenDAO;
import com.rifa.tino.persistencia.entity.Token;
import com.rifa.tino.persistencia.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

  @Autowired
  private TokenDAO tokenDAO;

  @Autowired
  private TokenRepository tokenRepository;


  @Override
  public MessageResponseDTO listarTokens() {
    List<Token> tokens = tokenRepository.findAll();

    if (tokens.isEmpty()) {
      throw new BusinessException(MensajeRespuesta.ERROR_NO_EXISTEN_REGISTROS);
    }

    return MessageResponseDTO.builder()
        .status(MensajeRespuesta.EXITO_REGISTROS_ENCONTRADOS.getStatus())
        .message(MensajeRespuesta.EXITO_REGISTROS_ENCONTRADOS.getMensaje())
        .data(tokens.stream().map(tokenDAO::toDTO).toList())
        .build();
  }


  @Override
  public MessageResponseDTO actualizarFechaUsoPorToken(String tokenValue) {
    // Buscar el token por su valor
    Token token = tokenRepository.findByToken(tokenValue)
        .orElseThrow(() -> new BusinessException(MensajeRespuesta.ERROR_REGISTRO_NO_ENCONTRADO));

    // Actualizar la fecha de uso con la fecha y hora actual
    token.setFechaUsoToken(LocalDateTime.now());

    tokenRepository.save(token);

    return MessageResponseDTO.builder()
        .status(MensajeRespuesta.EXITO_REGISTRO_ACTUALIZADO.getStatus())
        .message("Fecha de uso actualizada correctamente.")
        .idSecuencial(token.getIdToken())
        .build();
  }
}