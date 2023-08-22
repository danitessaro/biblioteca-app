package com.danitessaro.bibliotecaapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.danitessaro.bibliotecaapp.model.Livro;
import com.danitessaro.bibliotecaapp.model.Usuario;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByAutor(@Param("autor") String autor);

    Optional<Livro> findByTitulo(@Param("titulo") String titulo);

    List<Livro> findByUsuario(@Param("usuario") Usuario usuario);
}
