package org.libcg.views.livro;

import org.libcg.controllers.LivroController;
import org.libcg.core.View;
import org.libcg.dto.LivroDTO;

public class CadastraLivroScreen extends View {

    @Override
    public void render() {
        System.out.print("Digite o titulo do livro: ");
        String titulo = this.scanner.nextLine();
        System.out.println("");
        System.out.print("Digite a descricao do livro: ");
        String descricao = this.scanner.nextLine();
        System.out.println("");
        System.out.print("Digite o autor do livro: ");
        String autor = this.scanner.nextLine();
        System.out.println("");
        
        LivroDTO livro = new LivroDTO(titulo, descricao, autor);
        
        LivroController livroController = this.app.make(LivroController.class);
        
        livroController.guardar(livro);
        livroController.principal();
    }
}
