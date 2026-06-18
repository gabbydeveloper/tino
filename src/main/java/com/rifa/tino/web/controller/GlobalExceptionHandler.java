package com.rifa.tino.web.controller;

import com.rifa.tino.dominio.constants.MensajeRespuesta;
import com.rifa.tino.dominio.dto.MessageResponseDTO;
import com.rifa.tino.dominio.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<MessageResponseDTO> handleBusiness(BusinessException ex) {

    MensajeRespuesta msg = ex.getMensajeRespuesta();

    MessageResponseDTO response = MessageResponseDTO.builder()
        .status(ex.getMensajeRespuesta().getStatus())
        .message(ex.getMensajeRespuesta().getMensaje())
        .build();

    return ResponseEntity
        .status(ex.getMensajeRespuesta().getStatus())
        .body(response);

  }
}