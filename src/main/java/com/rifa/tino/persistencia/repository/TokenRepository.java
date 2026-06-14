package com.rifa.tino.persistencia.repository;

import com.rifa.tino.persistencia.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
  // Método adicional opcional para verificar token existente
  boolean existsByToken(String token);

  // Método para buscar por el valor del token (no por ID)
  Optional<Token> findByToken(String token);
}