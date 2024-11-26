package com.redesocial;

import com.redesocial.Gerenciador.GerenciadorUsuarios;
import com.redesocial.Gerenciador.GerenciadorPosts;
import com.redesocial.ui.MenuPrincipal;

public class Main {

    public static void main(String[] args) {
        // Criando instâncias dos gerenciadores
        GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();
        GerenciadorPosts gerenciadorPosts = new GerenciadorPosts();

        // Criando a instância de MenuPrincipal passando os gerenciadores
        MenuPrincipal menuPrincipal = new MenuPrincipal(gerenciadorUsuarios, gerenciadorPosts);

        // Exibindo o menu principal
        menuPrincipal.exibirMenu();
    }
}
