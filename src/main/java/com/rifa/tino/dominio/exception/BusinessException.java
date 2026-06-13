package com.rifa.tino.dominio.exception;

import com.rifa.tino.dominio.constants.MensajeRespuesta;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

  private final MensajeRespuesta mensajeRespuesta;

  public BusinessException(MensajeRespuesta mensajeRespuesta) {
    super(mensajeRespuesta.getMensaje());
    this.mensajeRespuesta = mensajeRespuesta;
  }

}