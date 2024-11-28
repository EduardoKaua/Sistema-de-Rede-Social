package com.redesocial.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Usuario {
    private Integer id;
    private String nome;
    private String username;
    private String email;
    private String senha;
    private LocalDateTime dataCadastro;
    private List<Usuario> amigos = new ArrayList<>();
    private List<Post> posts = new ArrayList<>();

    public Usuario(String nome, String username, String email, String senha) {
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.dataCadastro = LocalDateTime.now();
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    public List<Usuario> getAmigos() { return amigos; }

    // Método para adicionar amigo (relacionamento bidirecional)
    public void adicionarAmigo(Usuario amigo) {
        if (amigo != null && !this.amigos.contains(amigo) && !this.equals(amigo)) {
            this.amigos.add(amigo);
            amigo.adicionarAmigo(this);  // Adiciona o usuário como amigo do outro usuário
        } else {
            System.out.println("Este usuário já é seu amigo ou é o mesmo usuário.");
        }
    }

    // Método para remover amigo (relacionamento bidirecional)
    public void removerAmigo(Usuario amigo) {
        if (amigo != null && this.amigos.contains(amigo)) {
            this.amigos.remove(amigo);
            amigo.removerAmigo(this);  // Remove o usuário da lista de amigos do outro usuário
        } else {
            System.out.println("Este usuário não é seu amigo.");
        }
    }

    // Método para adicionar post
    public List<Post> getPosts() { return posts; }
    public void adicionarPost(Post post) { posts.add(post); }

    // Método para listar o feed de notícias (posts do próprio usuário e de seus amigos)
    public List<Post> listarFeed() {
        // Lista que contém os posts do próprio usuário e dos seus amigos
        List<Post> feed = new ArrayList<>(this.posts);  // Adiciona os posts próprios do usuário

        // Adiciona os posts dos amigos
        for (Usuario amigo : amigos) {
            feed.addAll(amigo.getPosts());
        }

        // Ordena os posts pela data de publicação (mais recente primeiro)
        return feed.stream()
                .sorted((post1, post2) -> post2.getDataPublicacao().compareTo(post1.getDataPublicacao()))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Usuário: " + nome + " (" + username + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
