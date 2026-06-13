package com.rifa.tino.persistencia.dao;

import com.rifa.tino.dominio.dto.DonanteDTO;
import com.rifa.tino.persistencia.entity.Donante;
import org.springframework.stereotype.Component;

@Component
public class DonanteDAO {

  public Donante toEntity(DonanteDTO dto) {
    if (dto == null) return null;
    Donante entity = new Donante();
    entity.setIdDonante(dto.getIdDonante());
    entity.setNombreCompleto(dto.getNombreCompleto());
    entity.setApodo(dto.getApodo());
    entity.setEmail(dto.getEmail());
    entity.setCelular(dto.getCelular());
    entity.setFechaCreaDonante(dto.getFechaCreaDonante());
    return entity;
  }

  public DonanteDTO toDTO(Donante entity) {
    if (entity == null) return null;
    DonanteDTO dto = new DonanteDTO();
    dto.setIdDonante(entity.getIdDonante());
    dto.setNombreCompleto(entity.getNombreCompleto());
    dto.setApodo(entity.getApodo());
    dto.setEmail(entity.getEmail());
    dto.setCelular(entity.getCelular());
    dto.setFechaCreaDonante(entity.getFechaCreaDonante());
    return dto;
  }
}