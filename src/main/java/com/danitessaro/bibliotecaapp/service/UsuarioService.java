package com.danitessaro.bibliotecaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danitessaro.bibliotecaapp.model.Usuario;
import com.danitessaro.bibliotecaapp.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario adicionarUsuario(Usuario usuario) {
        return this.repository.save(usuario);
    }

    public Usuario buscarUsuario(Long id) {
        return this.repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Usuario buscarUsuario(String nome) {
        return this.repository.findByNome(nome).orElseThrow(RuntimeException::new);
    }

    public Usuario editarUsuario(Long id, Usuario novoUsuario) {
        Usuario usuarioExistente = this.repository.findById(id).orElseThrow(RuntimeException::new);
        usuarioExistente.setNome(novoUsuario.getNome());
        usuarioExistente.setCpf(novoUsuario.getCpf());
        return this.repository.save(usuarioExistente);
    }
    
    public void excluirUsuario(Long id) {
        this.repository.deleteById(id);
    }
}

