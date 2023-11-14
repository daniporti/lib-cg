package org.libcg.controllers;

import java.util.List;
import org.libcg.core.Controller;

import org.libcg.models.Livro;
import org.libcg.views.LivroView;

public class LivroController extends Controller {

    @Override
    public void main() {
        List<Livro> livros = Livro.findAll(Livro.class);
            
        this.app.call(LivroView.class, "main", livros);
    }
    
    public void show(Integer id) {
        Livro livro = Livro.findOne(id, Livro.class);
            
        this.app.call(LivroView.class, "show", livro);
    }
    
    public void emprestar(Livro livro) {
        livro.emprestar();
        
        livro.save();
        
        this.app.call(LivroController.class);
    }
    
    public void create() {
        this.app.call(LivroView.class, "create");
    }
    
    public void guardar(String titulo, String descricao) {
        Livro livro = new Livro(titulo, descricao);
        
        livro.save();
        
        this.app.call(LivroController.class);
    }
}
