package com.redesocial.modelo;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private Usuario autor;
    private String conteudo;
    private LocalDateTime dataPublicacao;

    public Post(Usuario autor, String conteudo) {
        this.autor = autor;
        this.conteudo = conteudo;
        this.dataPublicacao = LocalDateTime.now();
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return "Post{id=" + id + ", autor=" + autor.getUsername() +
                ", conteudo='" + conteudo + "', dataPublicacao=" + dataPublicacao + "}";
    }
}
