package com.danitessaro.bibliotecaapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danitessaro.bibliotecaapp.model.Livro;
import com.danitessaro.bibliotecaapp.model.Usuario;
import com.danitessaro.bibliotecaapp.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    public Livro adicionarLivro(Livro livro) {
        return this.repository.save(livro);
    }
    
    public Livro buscarLivro(Long id) {
        return this.repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Livro> buscarLivros(String autor) {
        return this.repository.findByAutor(autor);
    }

    public List<Livro> buscarLivros(Usuario usuario) {
        return this.repository.findByUsuario(usuario);
    }

    public Livro buscarLivro(String titulo) {
        return this.repository.findByTitulo(titulo).orElseThrow(RuntimeException::new);
    }

    public Livro editarLivro(Long id, Livro novoLivro) {
        Livro livroExistente = this.repository.findById(id).orElseThrow(RuntimeException::new);
        livroExistente.setTitulo(novoLivro.getTitulo());
        livroExistente.setAutor(novoLivro.getAutor());
        return this.repository.save(livroExistente);
    }

    public void excluirLivro(Long id) {
        this.repository.deleteById(id);
    }

    public Livro emprestarLivro(Long id, Long idUsuario) throws Exception {
        Livro livro = this.repository.findById(id).orElseThrow(RuntimeException::new);
        Usuario usuario = this.usuarioService.buscarUsuario(idUsuario);
        List<Livro> livrosEmprestados = buscarLivros(usuario);
        if (livro.getUsuario() == null && livrosEmprestados.size() < 2) {
            livro.setUsuario(usuario);
            return this.repository.save(livro);
        } else {
            throw new Exception("Livro não disponível para empréstimo");

        }
        
    }

    public Livro devolverLivro(Long id) {
        Livro livroDevolvido = this.repository.findById(id).orElseThrow(RuntimeException::new);
        livroDevolvido.setUsuario(null);
        return this.repository.save(livroDevolvido);
    }

}
