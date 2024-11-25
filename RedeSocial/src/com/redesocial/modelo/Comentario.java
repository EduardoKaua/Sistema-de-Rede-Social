package com.redesocial.modelo;

import java.time.LocalDateTime;

public class Comentario {
    private Integer id;
    private Usuario autor;
    private String conteudo;
    private LocalDateTime dataComentario;
    private Post post;

    public Comentario(Usuario autor, String conteudo, Post post) {
        this.autor = autor;
        this.conteudo = conteudo;
        this.post = post;
        this.dataComentario = LocalDateTime.now();
    }

    // Método para acessar o post
    public Post getPost() {
        return this.post;
    }

    @Override
    public String toString() {
        return autor.getUsername() + ": " + conteudo;
    }
}
