package com.rifa.tino.web.controller;

import com.rifa.tino.dominio.dto.MessageResponseDTO;
import com.rifa.tino.dominio.dto.TokenDTO;
import com.rifa.tino.dominio.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tokens")
public class TokenController {

  @Autowired
  private TokenService tokenService;

  @PutMapping("/usar/{token}")
  public ResponseEntity<MessageResponseDTO> actualizarFechaUsoPorToken(@PathVariable String token) {
    return ResponseEntity.ok(tokenService.actualizarFechaUsoPorToken(token));
  }
}