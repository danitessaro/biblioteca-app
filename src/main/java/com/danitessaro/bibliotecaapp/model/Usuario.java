package com.danitessaro.bibliotecaapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Usuario {

    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String cpf;
}
