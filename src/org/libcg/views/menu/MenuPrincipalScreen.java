package org.libcg.views.menu;

import org.libcg.controllers.LivroController;
import org.libcg.core.View;

public class MenuPrincipalScreen extends View {
    @Override
    public void render() {
        System.out.println("Bem vindo a loja virtual da Biblioteca CG!");
        System.out.println("Digite 1 para acessar a area de livros");
        System.out.println("Digite 0 para sair");
        System.out.println("=====================================");
        System.out.print("> ");
        int opcao = this.scanner.nextInt();
        
        LivroController livroController = this.app.make(LivroController.class);
        
        switch(opcao) {
            case 1 -> livroController.principal();
            default -> System.out.println("Saindo...");
        }
    }
}
