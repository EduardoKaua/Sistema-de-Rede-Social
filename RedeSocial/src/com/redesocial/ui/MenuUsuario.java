package com.redesocial.ui;

import com.redesocial.Gerenciador.GerenciadorUsuarios;
import com.redesocial.modelo.Usuario;
import com.redesocial.Gerenciador.GerenciadorPosts;
import com.redesocial.modelo.Post;

import java.util.Scanner;

public class MenuUsuario {

    private Scanner scanner = new Scanner(System.in);
    private Usuario usuarioLogado;
    private GerenciadorPosts gerenciadorPosts = new GerenciadorPosts();

    public MenuUsuario(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    // Exibe o menu do usuário logado
    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("=== Menu do Usuário ===");
            System.out.println("1 - Criar Post");
            System.out.println("2 - Ver Meu Perfil");
            System.out.println("3 - Buscar Usuários");
            System.out.println("4 - Gerenciar Amizades");
            System.out.println("5 - Ver Feed de Notícias");
            System.out.println("6 - Logout");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    criarPost();
                    break;
                case 2:
                    verPerfil();
                    break;
                case 3:
                    buscarUsuarios();
                    break;
                case 4:
                    gerenciarAmizades();
                    break;
                case 5:
                    verFeedNoticias();
                    break;
                case 6:
                    System.out.println("Deslogando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 6);
    }

    // Cria um novo post
    private void criarPost() {
        System.out.print("Digite o conteúdo do seu post: ");
        String conteudo = scanner.nextLine();
        Post novoPost = new Post(usuarioLogado, conteudo); // Cria um novo post
        gerenciadorPosts.cadastrar(novoPost); // Adiciona o post ao gerenciador
        System.out.println("Post criado com sucesso!");
    }

    // Exibe o perfil do usuário logado
    private void verPerfil() {
        System.out.println("=== Perfil de " + usuarioLogado.getNome() + " ===");
        System.out.println("Nome: " + usuarioLogado.getNome());
        System.out.println("Username: " + usuarioLogado.getUsername());
        System.out.println("Email: " + usuarioLogado.getEmail());
        System.out.println("Posts: ");
        for (Post post : usuarioLogado.getPosts()) {
            System.out.println(post);
        }
    }

    // Busca e exibe os usuários cadastrados
    private void buscarUsuarios() {
        System.out.print("Digite o nome ou username do usuário: ");
        String nomeOuUsername = scanner.nextLine();

        // Cria uma instância do GerenciadorUsuarios
        GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();

        // Chama o método de buscar por username
        Usuario usuario = gerenciadorUsuarios.buscarPorUsername(nomeOuUsername);

        if (usuario != null) {
            System.out.println("Usuário encontrado: " + usuario);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    // Gerencia amizades (adicionar/remover)
    private void gerenciarAmizades() {
        System.out.print("Digite o username do usuário para adicionar como amigo: ");
        String username = scanner.nextLine();

        // Cria uma instância do GerenciadorUsuarios
        GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();

        // Chama o método de buscar por username
        Usuario usuario = gerenciadorUsuarios.buscarPorUsername(username);

        if (usuario != null && !usuario.equals(usuarioLogado)) {
            usuarioLogado.adicionarAmigo(usuario);
            usuario.adicionarAmigo(usuarioLogado);
            System.out.println("Amizade adicionada!");
        } else {
            System.out.println("Usuário não encontrado ou você não pode se adicionar como amigo.");
        }
    }

    // Exibe o feed de notícias (posts dos amigos)
    private void verFeedNoticias() {
        System.out.println("=== Feed de Notícias ===");
        for (Post post : usuarioLogado.getPosts()) {
            System.out.println(post);
        }
        System.out.println("Fim do feed.");
    }
}
