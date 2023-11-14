package org.libcg.views;

import java.util.ArrayList;
import org.libcg.controllers.LivroController;
import org.libcg.controllers.MenuController;
import org.libcg.core.View;

import org.libcg.models.Livro;

public class LivroView extends View {
    public void main(ArrayList<Livro> livros) {
        System.out.println("Informe o codigo do livro que deseja ver ou 0 para voltar: ");
        livros.stream()
                .filter(livro -> !livro.estaEmprestado())
                .forEach(
                        livro -> 
                                System.out.println("Codigo: " + livro.getId() + " " + livro.getTitulo())
                );
        System.out.println("=====================================");
        System.out.print("> ");
        int opcao = this.scanner.nextInt();
        
        switch (opcao) {
            case 0 -> this.app.call(MenuController.class);
            default -> this.app.call(LivroController.class, "show", opcao);
        }
    }
    
    public void show(Livro livro) {
        System.out.println("Codigo: " + livro.getId() + " " + livro.getTitulo());
        System.out.println("Descricao: " + livro.getDescricao());
        System.out.println("=====================================");
        System.out.println("Digite 1 para comprar e 0 para voltar");
        System.out.println("=====================================");
        System.out.print("> ");
        int opcao = this.scanner.nextInt();
        
        switch (opcao) {
            case 0 -> this.app.call(MenuController.class);
            default -> this.app.call(LivroController.class, "emprestar", livro);
        }
    }
    
    public void create() {
        System.out.print("Digite o titulo do livro: ");
        String titulo = this.scanner.nextLine();
        System.out.println("");
        System.out.print("Digite a descricao do livro: ");
        String descricao = this.scanner.nextLine();
        System.out.println("");
        
        this.app.call(LivroController.class, "guardar", titulo, descricao);
    }
}
