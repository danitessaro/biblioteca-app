package com.danitessaro.bibliotecaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danitessaro.bibliotecaapp.model.Usuario;
import com.danitessaro.bibliotecaapp.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private static final int QTD_CARACTERES_CPF = 11;
    private static final String MSG_CADASTRO_INCONSISTENTE = "Cadastro com dados inconsistentes";
    private static final String MSG_USUARIO_NAO_ENCONTRADO = "Usuário não encontrado";

    @Autowired
    private UsuarioRepository repository;

    public Usuario adicionarUsuario(Usuario usuario) {
        if (cadastroValido(usuario)) {
            return this.repository.save(usuario);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_CADASTRO_INCONSISTENTE);
        }
    }

    public Usuario buscarUsuario(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, MSG_USUARIO_NAO_ENCONTRADO));
    }

    public Usuario buscarUsuario(String nome) {
        return this.repository.findFirstByNome(nome)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, MSG_USUARIO_NAO_ENCONTRADO));
    }

    public Usuario editarUsuario(Long id, Usuario novoUsuario) {
        if (cadastroValido(novoUsuario)) {
            Usuario usuarioExistente = this.repository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, MSG_USUARIO_NAO_ENCONTRADO));
            usuarioExistente.setNome(novoUsuario.getNome());
            usuarioExistente.setCpf(novoUsuario.getCpf());
            return this.repository.save(usuarioExistente);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_CADASTRO_INCONSISTENTE);
        }
    }

    public void excluirUsuario(Long id) {
        this.repository.deleteById(id);
    }

    private boolean cpfValido(Usuario usuario) {
        try {
            return usuario.getCpf().length() == QTD_CARACTERES_CPF && Long.parseLong(usuario.getCpf()) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean verificarCpfDuplicado(Usuario usuario) {
        Usuario usuarioExistente = this.repository.findByCpf(usuario.getCpf()).orElse(null);
        return usuarioExistente == null;
    }

    private boolean nomeValido(Usuario usuario) {
        return usuario.getNome().matches("^((\\b[A-zÀ-ú']{2,40}\\b)\\s*){2,}$");
    }

    private boolean cadastroValido(Usuario usuario) {
        return nomeValido(usuario) && cpfValido(usuario) && verificarCpfDuplicado(usuario);
    }
}
