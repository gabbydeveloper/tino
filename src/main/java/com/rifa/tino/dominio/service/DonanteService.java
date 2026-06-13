package com.rifa.tino.dominio.service;

import com.rifa.tino.dominio.dto.MessageResponseDTO;
import com.rifa.tino.dominio.dto.DonanteDTO;

public interface DonanteService {

  MessageResponseDTO crearDonante(DonanteDTO donanteDTO);
  MessageResponseDTO listarDonantes();
  MessageResponseDTO obtenerDonantePorId(Long idDonante);
  MessageResponseDTO actualizarDonante(Long idDonante, DonanteDTO donanteDTO);
  MessageResponseDTO eliminarDonante(Long idDonante);
}