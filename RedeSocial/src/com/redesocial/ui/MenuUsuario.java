package com.redesocial.ui;

import com.redesocial.Gerenciador.GerenciadorUsuarios;
import com.redesocial.modelo.Usuario;
import com.redesocial.Gerenciador.GerenciadorPosts;
import com.redesocial.modelo.Post;

import java.util.List;
import java.util.Scanner;

public class MenuUsuario {

    private Scanner scanner = new Scanner(System.in);
    private Usuario usuarioLogado;
    private GerenciadorPosts gerenciadorPosts = new GerenciadorPosts();
    private GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();

    public MenuUsuario(Usuario usuario) {
        this.usuarioLogado = usuario;
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
        System.out.println("=== Buscar Usuários ===");
        System.out.println("1 - Buscar por Nome");
        System.out.println("2 - Buscar por Username");
        System.out.println("3 - Ver Todos os Usuários");
        System.out.print("Escolha uma opção: ");
        int opcaoBusca;

        // Aguardar uma entrada válida
        while (true) {
            try {
                opcaoBusca = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
                if (opcaoBusca < 1 || opcaoBusca > 3) {
                    System.out.println("Opção inválida, tente novamente.");
                    continue;
                }
                break; // Se a entrada for válida, sai do loop
            } catch (Exception e) {
                System.out.println("Entrada inválida. Digite um número entre 1 e 3.");
                scanner.nextLine(); // Limpar o buffer
            }
        }

        switch (opcaoBusca) {
            case 1:
                buscarPorNome();
                break;
            case 2:
                buscarPorUsername();
                break;
            case 3:
                listarTodosUsuarios();
                break;
        }
    }

    // Busca usuários por nome
    private void buscarPorNome() {
        System.out.print("Digite o nome para buscar: ");
        String nome = scanner.nextLine();
        List<Usuario> usuariosEncontrados = gerenciadorUsuarios.buscarPorNome(nome);
        if (usuariosEncontrados.isEmpty()) {
            System.out.println("Nenhum usuário encontrado com esse nome.");
        } else {
            for (Usuario usuario : usuariosEncontrados) {
                System.out.println(usuario);
            }
        }
    }

    // Busca usuários por username
    private void buscarPorUsername() {
        System.out.print("Digite o username para buscar: ");
        String username = scanner.nextLine();
        Usuario usuarioEncontrado = gerenciadorUsuarios.buscarPorUsername(username);
        if (usuarioEncontrado != null) {
            System.out.println("Usuário encontrado: " + usuarioEncontrado);
        } else {
            System.out.println("Nenhum usuário encontrado com esse username.");
        }
    }

    // Exibe todos os usuários cadastrados
    private void listarTodosUsuarios() {
        System.out.println("=== Todos os Usuários ===");
        List<Usuario> usuarios = gerenciadorUsuarios.listarTodosUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }

    // Gerencia amizades (adicionar/remover)
    private void gerenciarAmizades() {
        System.out.print("Digite o username do usuário para adicionar como amigo: ");
        String username = scanner.nextLine();

        // Cria uma instância do GerenciadorUsuarios
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

    // Exibe o menu principal de opções
    public void exibirMenu() {
        while (true) {
            System.out.println("\n=== Menu de " + usuarioLogado.getNome() + " ===");
            System.out.println("1 - Criar Post");
            System.out.println("2 - Ver Perfil");
            System.out.println("3 - Buscar Usuários");
            System.out.println("4 - Gerenciar Amizades");
            System.out.println("5 - Ver Feed de Notícias");
            System.out.println("6 - Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

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
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
