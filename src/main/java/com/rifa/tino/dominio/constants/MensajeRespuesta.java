package com.rifa.tino.dominio.constants;

import org.springframework.http.HttpStatus;

public enum MensajeRespuesta {
  //ÉXITO:
  EXITO_REGISTRO_CREADO("Registro creado.", HttpStatus.OK),
  EXITO_REGISTROS_ENCONTRADOS("Registros encontrados y listados.", HttpStatus.OK),
  EXITO_REGISTRO_ACTUALIZADO("Registro correctamente actualizado.", HttpStatus.OK),
  EXITO_REGISTRO_ELIMINADO("Registro eliminado con éxito.", HttpStatus.OK),
  EXITO_OPERACION("", HttpStatus.OK),
  EXITO_REGISTRO_ENCONTRADO("Donante con este correo ya existe", HttpStatus.OK),

  //ERRORES:
  ERROR_REGISTRO_NO_ENCONTRADO("No se encontró el registro con el ID proporcionado.", HttpStatus.NOT_FOUND),
  ERROR_NO_EXISTEN_REGISTROS("No existen registros.", HttpStatus.NOT_FOUND),
  ERROR_EMAIL_YA_EXISTE("Ya existe un donante con este correo.", HttpStatus.BAD_REQUEST),
  ERROR_CELULAR_YA_EXISTE("Ya existe un donante con este celular.", HttpStatus.BAD_REQUEST),
  ERROR_REGISTRO_YA_EXISTE("ID del registro ya existe en la base de datos.", HttpStatus.BAD_REQUEST),
  ERROR_INDIQUE_EL_CODIGO("Indique el código del registro para poder realizar el ingreso.", HttpStatus.BAD_REQUEST),
  ERROR_REGISTRO_MAESTRO_NO_ENCONTRADO("El código de la tabla maestro no se ha encontrado", HttpStatus.BAD_REQUEST),
  ERROR_EXISTEN_DEPENDENCIAS(
      "No se puede eliminar el registro porque existen dependencias en otras tablas.",
      HttpStatus.BAD_REQUEST
  ),
  ERROR_TICKET_ASIGNADO("Tickets ya asignados", HttpStatus.BAD_REQUEST),
  ERROR_SISTEMA("Error del sistema, contacte al administrador.", HttpStatus.INTERNAL_SERVER_ERROR);

  private final String mensaje;
  private final HttpStatus status;

  MensajeRespuesta(String mensaje, HttpStatus status) {
    this.mensaje = mensaje;
    this.status = status;
  }

  public String getMensaje() {
    return mensaje;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
