package com.danitessaro.bibliotecaapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.danitessaro.bibliotecaapp.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findFirstByNome(@Param("nome") String nome);

    Optional<Usuario> findByCpf(@Param("cpf") String cpf);
}
