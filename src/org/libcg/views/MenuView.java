package org.libcg.views;

import org.libcg.controllers.LivroController;
import org.libcg.core.View;

public class MenuView extends View {
    public void main() {
        System.out.println("Bem vindo a loja virtual da Biblioteca CG!");
        System.out.println("Digite 1 para ir para emprestar um livro");
        System.out.println("Digite 2 para ir para cadastrar um livro");
        System.out.println("Digite 0 para sair");
        System.out.println("=====================================");
        System.out.print("> ");
        int opcao = this.scanner.nextInt();
        
        switch(opcao) {
            case 1 -> this.app.call(LivroController.class);
            case 2 -> this.app.call(LivroController.class, "create");
            default -> System.out.println("Saindo...");
        }
    }
}
