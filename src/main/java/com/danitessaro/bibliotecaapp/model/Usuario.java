package com.danitessaro.bibliotecaapp.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    
    @OneToMany
    private Set<Livro> livrosEmprestados;
}

