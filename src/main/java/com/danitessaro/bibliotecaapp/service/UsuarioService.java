package com.danitessaro.bibliotecaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import com.danitessaro.bibliotecaapp.model.Usuario;
import com.danitessaro.bibliotecaapp.repository.UsuarioRepository;

@Service
public class UsuarioService {

    static final int QTD_CARACTERES_CPF = 11;

    @Autowired
    private UsuarioRepository repository;

    public Usuario adicionarUsuario(Usuario usuario) {
        if (cadastroValido(usuario)) {
            return this.repository.save(usuario);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF inválido");
        }
    }

    public Usuario buscarUsuario(Long id) {
        return this.repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Usuario buscarUsuario(String nome) {
        return this.repository.findByNome(nome).orElseThrow(RuntimeException::new);
    }

    public Usuario editarUsuario(Long id, Usuario novoUsuario) {
        if (cadastroValido(novoUsuario)) {
            Usuario usuarioExistente = this.repository.findById(id).orElseThrow(RuntimeException::new);
            usuarioExistente.setNome(novoUsuario.getNome());
            usuarioExistente.setCpf(novoUsuario.getCpf());
            return this.repository.save(usuarioExistente);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF inválido");
        }
    }
    
    public void excluirUsuario(Long id) {
        this.repository.deleteById(id);
    }

    private boolean cpfValido(Usuario usuario) {
        return !StringUtils.hasText(usuario.getCpf()) && usuario.getCpf().length() == QTD_CARACTERES_CPF;
    }

    private boolean verificarCpfDuplicado(Usuario usuario) {
        Usuario usuarioExistente = this.repository.findByCpf(usuario.getCpf()).orElse(null);
        return usuarioExistente != null;
    }

    private boolean nomeValido(Usuario usuario) {
        return usuario.getNome().matches("^((\b[A-zÀ-ú']{2,40}\b)\s*){2,}$");  
    }

    private boolean cadastroValido(Usuario usuario) {
        return nomeValido(usuario) && cpfValido(usuario) && verificarCpfDuplicado(usuario);
    }
}

