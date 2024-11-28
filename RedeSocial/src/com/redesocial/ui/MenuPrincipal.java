package com.redesocial.ui;

import com.redesocial.Gerenciador.GerenciadorUsuarios;
import com.redesocial.Gerenciador.GerenciadorPosts;
import com.redesocial.modelo.Usuario;
import com.redesocial.modelo.Post;

import java.util.List;
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
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = gerenciadorUsuarios.buscarPorUsername(username);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            System.out.println("Login bem-sucedido! Bem-vindo, " + usuario.getUsername() + "!");
            exibirMenuLogado(usuario);
        } else {
            System.out.println("Usuário ou senha incorretos. Tente novamente.");
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

        // Verifica se o nome de usuário já está cadastrado
        if (gerenciadorUsuarios.buscarPorUsername(username) != null) {
            System.out.println("Nome de usuário já está em uso. Tente outro.");
            return;
        }

        Usuario novoUsuario = new Usuario(nome, username, email, senha);
        gerenciadorUsuarios.cadastrar(novoUsuario);
    }

    // Menu para usuários logados
    private void exibirMenuLogado(Usuario usuario) {
        while (true) {
            System.out.println("\nMenu do Usuário - " + usuario.getUsername());
            System.out.println("1. Criar post");
            System.out.println("2. Ver perfil");
            System.out.println("3. Gerenciar amizades");
            System.out.println("4. Ver feed de notícias");
            System.out.println("5. Buscar usuários");
            System.out.println("6. Sair");
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
                    buscarUsuarios();  // Chama o método de buscar usuários
                    break;
                case 6:
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
        gerenciadorPosts.criar(post);  // Adiciona o post ao GerenciadorPosts
        usuario.adicionarPost(post);  // Adiciona o post à lista de posts do usuário
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

        // Exibindo os posts do usuário
        if (usuario.getPosts().isEmpty()) {
            System.out.println("Nenhum post encontrado.");
        } else {
            for (Post post : usuario.getPosts()) {
                System.out.println("\nPost: " + post.getConteudo());
            }
        }
    }

    // Método para gerenciar amizades
    private void gerenciarAmizades(Usuario usuario) {
        while (true) {
            System.out.println("\nGerenciar amizades");
            System.out.println("1. Adicionar amigo");
            System.out.println("2. Remover amigo");
            System.out.println("3. Ver lista de amigos");
            System.out.println("4. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    adicionarAmigo(usuario);
                    break;
                case 2:
                    removerAmigo(usuario);
                    break;
                case 3:
                    verAmigos(usuario);
                    break;
                case 4:
                    return;  // Volta ao menu anterior
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Método para adicionar um amigo
    private void adicionarAmigo(Usuario usuario) {
        System.out.print("Digite o nome de usuário do amigo que deseja adicionar: ");
        String username = scanner.nextLine();
        Usuario amigo = gerenciadorUsuarios.buscarPorUsername(username);

        if (amigo != null && !amigo.equals(usuario)) {
            usuario.adicionarAmigo(amigo);
            amigo.adicionarAmigo(usuario);  // Adiciona recíproco
            System.out.println("Você adicionou " + amigo.getNome() + " como amigo.");
        } else {
            System.out.println("Usuário não encontrado ou você está tentando adicionar a si mesmo.");
        }
    }

    // Método para remover um amigo
    private void removerAmigo(Usuario usuario) {
        System.out.print("Digite o nome de usuário do amigo que deseja remover: ");
        String username = scanner.nextLine();
        Usuario amigo = gerenciadorUsuarios.buscarPorUsername(username);

        if (amigo != null) {
            usuario.removerAmigo(amigo);
            amigo.removerAmigo(usuario);  // Remove o amigo de forma recíproca
            System.out.println("Você removeu " + amigo.getNome() + " da sua lista de amigos.");
        } else {
            System.out.println("Usuário não encontrado ou não é seu amigo.");
        }
    }

    // Método para ver a lista de amigos
    private void verAmigos(Usuario usuario) {
        System.out.println("\nAmigos de " + usuario.getUsername() + ":");
        if (usuario.getAmigos().isEmpty()) {
            System.out.println("Você não tem amigos.");
        } else {
            for (Usuario amigo : usuario.getAmigos()) {
                System.out.println(amigo.getNome() + " (" + amigo.getUsername() + ")");
            }
        }
    }

    // Método para ver o feed de notícias
    private void verFeedNoticias(Usuario usuario) {
        System.out.println("\nFeed de notícias:");
        for (Post post : gerenciadorPosts.listarPorUsuario(usuario.getId())) {
            System.out.println(post);
        }
    }

    // Método para buscar usuários
    private void buscarUsuarios() {
        System.out.println("=== Buscar Usuários ===");
        System.out.print("Digite o nome ou username do usuário: ");
        String termo = scanner.nextLine();

        // Busca usuários por nome
        List<Usuario> usuariosEncontrados = gerenciadorUsuarios.buscarPorNome(termo);
        if (usuariosEncontrados.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
        } else {
            System.out.println("Usuários encontrados:");
            for (Usuario u : usuariosEncontrados) {
                System.out.println("- " + u.getNome() + " (@" + u.getUsername() + ")");
            }

            System.out.print("Gostaria de interagir com este usuários? mande amizade para ele, Digite 0 para voltar: ");
            String escolha = scanner.nextLine();
            if (!escolha.equals("0")) {
                Usuario usuarioEscolhido = gerenciadorUsuarios.buscarPorUsername(escolha);
                if (usuarioEscolhido != null) {
                    System.out.println("Você escolheu: " + usuarioEscolhido.getNome());
                    // Aqui você pode adicionar opções para interagir com o usuário escolhido
                } else {
                    System.out.println("Usuário não encontrado.");
                }
            }
        }
    }
}
