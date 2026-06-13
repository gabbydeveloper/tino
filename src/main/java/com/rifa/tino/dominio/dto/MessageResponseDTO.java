package com.rifa.tino.dominio.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)//Excluye campos nulos de la respuesta JSON
@Data
@Builder
@AllArgsConstructor//Genera constructor de todos los argumentos de la clase
public class MessageResponseDTO {
  //DECLARACIÓN DE LAS VARIABLES DE LOS CAMPOS DEL DTO:
  private HttpStatus status;
  private String message;
  private Long idSecuencial;
  private String idNoSecuencial;
  private List data;
}