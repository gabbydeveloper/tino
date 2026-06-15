package com.rifa.tino.persistencia.repository;

import com.rifa.tino.persistencia.entity.Donante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonanteRepository extends JpaRepository<Donante, Long> {
  // Método para verificar si ya existe un email (opcional)
  boolean existsByEmail(String email);

  // Método para buscar donante por email
  Optional<Donante> findByEmail(String email);

  // Método para buscar donante por celular
  Optional<Donante> findByCelular(String celular);
}