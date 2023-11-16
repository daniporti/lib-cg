package org.libcg.views.livro;

import org.libcg.controllers.LivroController;
import org.libcg.core.View;
import org.libcg.dto.LivroDTO;

public class MostraLivroScreen extends View {
    private final LivroDTO livro;
    
    public MostraLivroScreen(LivroDTO livro) {
        this.livro = livro;
    }
    
    @Override
    public void render() {
        System.out.println("Codigo: " + livro.getId() + " " + livro.getTitulo());
        System.out.println("Descricao: " + livro.getDescricao());
        System.out.println("=====================================");
        System.out.println("Digite 1 para emprestar e 0 para voltar");
        System.out.println("=====================================");
        System.out.print("> ");
        int opcao = this.scanner.nextInt();
        
        LivroController livroController = this.app.make(LivroController.class);
        
        switch (opcao) {
            case 0 -> livroController.principal();
            case 1 -> {
                livroController.emprestar(livro);
                livroController.principal();
            }
        }
    }
    
}
