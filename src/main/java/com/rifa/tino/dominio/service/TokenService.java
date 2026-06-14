package com.rifa.tino.dominio.service;

import com.rifa.tino.dominio.dto.MessageResponseDTO;
import com.rifa.tino.dominio.dto.TokenDTO;

public interface TokenService {

  MessageResponseDTO listarTokens();
  // NUEVO: actualiza solo la fecha de uso buscando por el token string
  MessageResponseDTO actualizarFechaUsoPorToken(String tokenValue);
}