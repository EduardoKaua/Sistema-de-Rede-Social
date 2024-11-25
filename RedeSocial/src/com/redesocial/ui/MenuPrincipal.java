package com.redesocial.ui;

import com.redesocial.Gerenciador.GerenciadorUsuarios;
import com.redesocial.Gerenciador.GerenciadorPosts;
import com.redesocial.modelo.Usuario;
import com.redesocial.modelo.Post;

import java.util.Scanner;

public class MenuPrincipal {

    private GerenciadorUsuarios gerenciadorUsuarios;
    private GerenciadorPosts gerenciadorPosts;
    private Scanner scanner;

    // Construtor com parâmetros
    public MenuPrincipal(GerenciadorUsuarios gerenciadorUsuarios, GerenciadorPosts gerenciadorPosts) {
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        this.gerenciadorPosts = gerenciadorPosts;
        this.scanner = new Scanner(System.in);
    }

    // Método exibirMenu()
    public void exibirMenu() {
        while (true) {
            System.out.println("Bem-vindo ao Menu Principal!");
            System.out.println("1. Fazer login");
            System.out.println("2. Cadastrar novo usuário");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    fazerLogin();
                    break;
                case 2:
                    cadastrarUsuario();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Método para fazer login
    private void fazerLogin() {
        System.out.print("Digite seu nome de usuário: ");
        String username = scanner.nextLine();
        Usuario usuario = gerenciadorUsuarios.buscarPorUsername(username);

        if (usuario != null) {
            System.out.println("Bem-vindo, " + usuario.getUsername() + "!");
            exibirMenuLogado(usuario);
        } else {
            System.out.println("Usuário não encontrado. Tente novamente.");
        }
    }

    // Método para cadastrar um novo usuário
    private void cadastrarUsuario() {
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite seu nome de usuário: ");
        String username = scanner.nextLine();
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        Usuario novoUsuario = new Usuario(nome, username, email, senha);
        gerenciadorUsuarios.cadastrar(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    // Menu para usuários logados
    private void exibirMenuLogado(Usuario usuario) {
        while (true) {
            System.out.println("\nMenu do Usuário - " + usuario.getUsername());
            System.out.println("1. Criar post");
            System.out.println("2. Ver perfil");
            System.out.println("3. Gerenciar amizades");
            System.out.println("4. Ver feed de notícias");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    criarPost(usuario);
                    break;
                case 2:
                    verPerfil(usuario);
                    break;
                case 3:
                    gerenciarAmizades(usuario);
                    break;
                case 4:
                    verFeedNoticias(usuario);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Método para criar um post
    private void criarPost(Usuario usuario) {
        System.out.print("Digite o conteúdo do post: ");
        String conteudo = scanner.nextLine();

        Post post = new Post(usuario, conteudo, java.time.LocalDateTime.now());
        gerenciadorPosts.cadastrar(post);
        System.out.println("Post criado com sucesso!");
    }

    // Método para ver o perfil do usuário
    private void verPerfil(Usuario usuario) {
        System.out.println("\nPerfil de " + usuario.getUsername());
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Data de cadastro: " + usuario.getDataCadastro());
        System.out.println("Amigos: " + usuario.getAmigos().size());
        System.out.println("Posts: " + usuario.getPosts().size());
    }

    // Método para gerenciar amizades
    private void gerenciarAmizades(Usuario usuario) {
        System.out.println("\nGerenciar amizades ainda não implementado.");
    }

    // Método para ver o feed de notícias
    private void verFeedNoticias(Usuario usuario) {
        System.out.println("\nFeed de notícias:");
        for (Post post : gerenciadorPosts.listarPorUsuario(usuario.getId())) {
            System.out.println(post);
        }
    }
}
