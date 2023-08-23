package com.danitessaro.bibliotecaapp.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danitessaro.bibliotecaapp.model.Usuario;
import com.danitessaro.bibliotecaapp.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity createUsuario(@RequestBody Usuario usuario) throws URISyntaxException {
        Usuario usuarioAdicionado = usuarioService.adicionarUsuario(usuario);
        return ResponseEntity.created(new URI("/usuario" + usuarioAdicionado.getId())).body(usuarioAdicionado);
    }

   @GetMapping("/{id}")
   public Usuario getUsuario(@PathVariable Long id) {
       return usuarioService.buscarUsuario(id);
   }

   @GetMapping
   public Usuario getUsuario(@RequestParam String nome) {
       return usuarioService.buscarUsuario(nome);
   }

   @PutMapping("/{id}")
    public ResponseEntity updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = usuarioService.editarUsuario(id, usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirUsuario(@PathVariable Long id) {
        usuarioService.excluirUsuario(id);
        return ResponseEntity.ok().build();
    }
}
