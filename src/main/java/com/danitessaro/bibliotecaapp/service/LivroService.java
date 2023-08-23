package com.danitessaro.bibliotecaapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danitessaro.bibliotecaapp.model.Livro;
import com.danitessaro.bibliotecaapp.model.Usuario;
import com.danitessaro.bibliotecaapp.repository.LivroRepository;

@Service
public class LivroService {

    private static final int QTD_MAXIMA_LIVRO_POR_USUARIO = 2;
    private static final String MSG_LIVRO_NAO_ENCONTRADO = "Livro não encontrado";
    private static final String MSG_EMPRESTIMO_INDISPONIVEL = "Livro não disponível para empréstimo";

    @Autowired
    private LivroRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    public Livro adicionarLivro(Livro livro) {
        return this.repository.save(livro);
    }
    
    public Livro buscarLivro(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,MSG_LIVRO_NAO_ENCONTRADO));
    }

    public List<Livro> buscarLivros(String autor) {
        return this.repository.findByAutor(autor);
    }

    public List<Livro> buscarLivros(Usuario usuario) {
        return this.repository.findByUsuario(usuario);
    }

    public Livro buscarLivro(String titulo) {
        return this.repository.findFirstByTitulo(titulo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,MSG_LIVRO_NAO_ENCONTRADO));
    }

    public Livro editarLivro(Long id, Livro novoLivro) {
        Livro livroExistente = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,MSG_LIVRO_NAO_ENCONTRADO));
        livroExistente.setTitulo(novoLivro.getTitulo());
        livroExistente.setAutor(novoLivro.getAutor());
        return this.repository.save(livroExistente);
    }

    public void excluirLivro(Long id) {
        this.repository.deleteById(id);
    }

    public Livro emprestarLivro(Long id, Long idUsuario) throws Exception {
        Livro livro = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,MSG_LIVRO_NAO_ENCONTRADO));
        Usuario usuario = this.usuarioService.buscarUsuario(idUsuario);
        List<Livro> livrosEmprestados = buscarLivros(usuario);
        if (livroDisponivelParaEmprestimo(livro, livrosEmprestados)) {
            livro.setUsuario(usuario);
            return this.repository.save(livro);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_EMPRESTIMO_INDISPONIVEL);
        }
        
    }

    private boolean livroDisponivelParaEmprestimo(Livro livro, List<Livro> livrosEmprestados) {
        return livro.getUsuario() == null && livrosEmprestados.size() < QTD_MAXIMA_LIVRO_POR_USUARIO;
    }

    public Livro devolverLivro(Long id) {
        Livro livroDevolvido = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,MSG_LIVRO_NAO_ENCONTRADO));
        livroDevolvido.setUsuario(null);
        return this.repository.save(livroDevolvido);
    }

}
