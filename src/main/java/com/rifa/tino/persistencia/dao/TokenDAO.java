package com.rifa.tino.persistencia.dao;

import com.rifa.tino.dominio.dto.TokenDTO;
import com.rifa.tino.persistencia.entity.Token;
import org.springframework.stereotype.Component;

@Component
public class TokenDAO {

  public Token toEntity(TokenDTO dto) {
    if (dto == null) return null;
    Token entity = new Token();
    entity.setIdToken(dto.getIdToken());
    entity.setToken(dto.getToken());
    entity.setFechaUsoToken(dto.getFechaUsoToken());
    return entity;
  }

  public TokenDTO toDTO(Token entity) {
    if (entity == null) return null;
    TokenDTO dto = new TokenDTO();
    dto.setIdToken(entity.getIdToken());
    dto.setToken(entity.getToken());
    dto.setFechaUsoToken(entity.getFechaUsoToken());
    return dto;
  }
}