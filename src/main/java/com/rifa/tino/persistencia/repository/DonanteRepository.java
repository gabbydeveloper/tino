package com.rifa.tino.persistencia.repository;

import com.rifa.tino.persistencia.entity.Donante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonanteRepository extends JpaRepository<Donante, Long> {
  // Método para verificar si ya existe un email (opcional)
  boolean existsByEmail(String email);
}