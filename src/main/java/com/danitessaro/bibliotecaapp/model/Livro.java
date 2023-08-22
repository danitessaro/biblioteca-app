package com.danitessaro.bibliotecaapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Livro {

    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Getter
    @Setter
    private String titulo;

    @Getter
    @Setter
    private String autor;


    @ManyToOne
    @JoinColumn(name="usuario_id", nullable=true)
    @Getter
    @Setter
    private Usuario usuario;
    
}
