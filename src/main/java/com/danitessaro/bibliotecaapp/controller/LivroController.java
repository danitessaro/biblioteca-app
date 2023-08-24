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

import com.danitessaro.bibliotecaapp.model.Livro;
import com.danitessaro.bibliotecaapp.model.Usuario;
import com.danitessaro.bibliotecaapp.service.LivroService;

@RequestMapping("/livro")
@RestController
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity createLivro(@RequestBody Livro livro) throws URISyntaxException {
        Livro livroAdicionado = livroService.adicionarLivro(livro);
        return ResponseEntity.created(new URI("/livro/" + livroAdicionado.getId())).body(livroAdicionado);
    }

    @GetMapping("/{id}")
    public Livro buscarLivro(@PathVariable Long id) {
        return livroService.buscarLivro(id);
    }

    @GetMapping("/autor")
    public List<Livro> buscarLivros(@RequestParam String autor) {
        return livroService.buscarLivros(autor);
    }

    @GetMapping("/titulo")
    public Livro buscarLivro(@RequestParam String titulo) {
        return livroService.buscarLivro(titulo);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateLivro(@PathVariable Long id, @RequestBody Livro novoLivro) {
        Livro livroAtualizado = livroService.editarLivro(id, novoLivro);
        return ResponseEntity.ok(livroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirLivro(@PathVariable Long id) {
        livroService.excluirLivro(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/emprestimo")
    public ResponseEntity emprestarLivro(@PathVariable Long id, @RequestBody Usuario usuario) throws Exception {
        Livro livro = livroService.emprestarLivro(id, usuario.getId());
        return ResponseEntity.ok(livro);

    }

    @PostMapping("/{id}/devolucao")
    public ResponseEntity devolverLivro(@PathVariable Long id) {
        Livro livro = livroService.devolverLivro(id);
        return ResponseEntity.ok(livro);
    }
}
