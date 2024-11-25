package com.redesocial.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Post {

    private Integer id;
    private Usuario autor;
    private String conteudo;
    private LocalDateTime dataPublicacao;
    private List<Usuario> curtidas;
    private List<Comentario> comentarios;

    // Construtor com data de publicação fornecida
    public Post(Usuario autor, String conteudo, LocalDateTime dataPublicacao) {
        this.autor = autor;
        this.conteudo = conteudo;
        this.dataPublicacao = dataPublicacao;
        this.curtidas = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    // Construtor com data de publicação atual (caso não seja fornecida)
    public Post(Usuario autor, String conteudo) {
        this(autor, conteudo, LocalDateTime.now());  // Atribui a data atual por padrão
    }

    // Getters e setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public List<Usuario> getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(List<Usuario> curtidas) {
        this.curtidas = curtidas;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    // Métodos para adicionar curtidas e comentários
    public void adicionarCurtida(Usuario usuario) {
        if (usuario != null && !curtidas.contains(usuario)) {
            curtidas.add(usuario);
        }
    }

    public void removerCurtida(Usuario usuario) {
        curtidas.remove(usuario);
    }

    public void adicionarComentario(Comentario comentario) {
        if (comentario != null && !comentarios.contains(comentario)) {
            comentarios.add(comentario);
        }
    }

    // Método toString() para exibir o post
    @Override
    public String toString() {
        return "Post por " + autor.getUsername() + " - " + conteudo + " [" + dataPublicacao + "]";
    }

    // equals() e hashCode() baseados no id do Post
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Post post = (Post) obj;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
