package org.libcg.controllers;

import java.util.List;
import org.libcg.core.Controller;
import org.libcg.dto.LivroDTO;

import org.libcg.models.Livro;
import org.libcg.views.livro.CadastraLivroScreen;
import org.libcg.views.livro.ListaLivroScreen;
import org.libcg.views.livro.LivrosPrincipalScreen;
import org.libcg.views.livro.MostraLivroScreen;
import org.libcg.views.livro.RemoverLivroScreen;

public class LivroController extends Controller {
    
    @Override
    public void principal() {
        LivrosPrincipalScreen view = new LivrosPrincipalScreen();
        
        view.render();
    }

    public void listar() {
        List<Livro> livros = Livro.findAll(Livro.class);
        List<LivroDTO> livroDTO = livros.stream()
                .map(livro -> new LivroDTO(
                        livro.getId(), 
                        livro.getTitulo(), 
                        livro.getDescricao(), 
                        livro.estaEmprestado(),
                        livro.getAutor()
                )).toList();
        
        ListaLivroScreen view = new ListaLivroScreen(livroDTO);
            
        view.render();
    }
    
    public void mostarLivro(int id) {
        Livro livro = Livro.findOne(id, Livro.class);
        LivroDTO livroDTO = new LivroDTO(
                        livro.getId(), 
                        livro.getTitulo(), 
                        livro.getDescricao(), 
                        livro.estaEmprestado(),
                        livro.getAutor()
        );
        
        MostraLivroScreen view = new MostraLivroScreen(livroDTO);
            
        view.render();
    }
    
    public void emprestar(LivroDTO livroDTO) {
        Livro livro = Livro.findOne(livroDTO.getId(), Livro.class);
        
        livro.emprestar();
        
        livro.save();
    }
    
    public void cadastrar() {
        CadastraLivroScreen view = new CadastraLivroScreen();
        
        view.render();
    }
    
    public void guardar(LivroDTO livroDTO) {
        Livro livro = new Livro(livroDTO.getTitulo(), livroDTO.getDescricao(), livroDTO.getAutor());
        
        livro.save();
    }

    public void remover(LivroDTO livroDTO) {
        Livro livro = Livro.findOne(livroDTO.getId(), Livro.class);
        
        livro.save();
    }

    public void atualiza(LivroDTO livroDTO, int id) {
        Livro livro = Livro.findOne(id, Livro.class);
        
        livro.save();
    }
}
