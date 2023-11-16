package org.libcg.views.livro;

import org.libcg.controllers.LivroController;
import org.libcg.controllers.MenuController;
import org.libcg.core.View;

public class LivrosPrincipalScreen extends View {

    @Override
    public void render() {
        System.out.println("Bem vindo a sessao de locacao de livros!");
        System.out.println("Digite 1 para ver os livros disponíveis");
        System.out.println("Digite 2 para cadastrar um livro");
        System.out.println("Digite 0 para sair");
        System.out.println("=====================================");
        int opcao = this.scanner.nextInt();
        
        MenuController menuController = this.app.make(MenuController.class);
        LivroController livroController = this.app.make(LivroController.class);
        
        switch(opcao) {
            case 1 -> livroController.listar();
            case 2 -> livroController.cadastrar();
            default -> menuController.principal();
        }
    }
}
