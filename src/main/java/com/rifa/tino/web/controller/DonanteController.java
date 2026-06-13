package com.rifa.tino.web.controller;

import com.rifa.tino.dominio.dto.MessageResponseDTO;
import com.rifa.tino.dominio.dto.DonanteDTO;
import com.rifa.tino.dominio.service.DonanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/donantes")
public class DonanteController {

  @Autowired
  private DonanteService donanteService;

  @PostMapping
  public ResponseEntity<MessageResponseDTO> crearDonante(@RequestBody DonanteDTO donanteDTO) {
    return ResponseEntity.ok(donanteService.crearDonante(donanteDTO));
  }

  @GetMapping
  public ResponseEntity<MessageResponseDTO> listarDonantes() {
    return ResponseEntity.ok(donanteService.listarDonantes());
  }

  @GetMapping("/{idDonante}")
  public ResponseEntity<MessageResponseDTO> obtenerDonantePorId(@PathVariable Long idDonante) {
    return ResponseEntity.ok(donanteService.obtenerDonantePorId(idDonante));
  }

  @PutMapping("/{idDonante}")
  public ResponseEntity<MessageResponseDTO> actualizarDonante(@PathVariable Long idDonante,
                                                              @RequestBody DonanteDTO donanteDTO) {
    return ResponseEntity.ok(donanteService.actualizarDonante(idDonante, donanteDTO));
  }

  @DeleteMapping("/{idDonante}")
  public ResponseEntity<MessageResponseDTO> eliminarDonante(@PathVariable Long idDonante) {
    return ResponseEntity.ok(donanteService.eliminarDonante(idDonante));
  }
}