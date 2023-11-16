
package org.libcg.views.livro;

import java.util.List;
import org.libcg.controllers.LivroController;
import org.libcg.core.View;
import org.libcg.dto.LivroDTO;

public class ListaLivroScreen extends View {
    private final List<LivroDTO> livros;
    
    public ListaLivroScreen(List<LivroDTO> livros) {
        this.livros = livros;
    }
    
    @Override
    public void render() {
        System.out.println("Informe o codigo do livro que deseja ver ou 0 para voltar: ");
        
        for(LivroDTO livro : livros) {
            System.out.println("Codigo: " + livro.getId() + " " + livro.getTitulo());
        }
        
        System.out.println("=====================================");
        System.out.print("> ");
        int opcao = this.scanner.nextInt();

        LivroController livroController = this.app.make(LivroController.class);
        
        switch (opcao) {
            case 0 -> livroController.principal();
            default -> livroController.mostarLivro(opcao);
        }
    }
    
}
