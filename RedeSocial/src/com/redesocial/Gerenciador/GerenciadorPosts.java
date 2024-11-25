package com.redesocial.Gerenciador;

import com.redesocial.modelo.Post;
import com.redesocial.modelo.Usuario;
import com.redesocial.modelo.Comentario;
import com.redesocial.Gerenciador.GerenciadorUsuarios;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorPosts {

    private List<Post> posts = new ArrayList<>();
    private int proximoId = 1;

    // Método para criar um post
    public void criar(Post post) {
        post.setId(proximoId++);  // Define um ID único para o post
        posts.add(post);  // Adiciona o post à lista
    }

    // Método para cadastrar um post
    public void cadastrar(Post post) {
        validarPost(post);  // Valida o post antes de cadastrá-lo
        post.setId(proximoId++);  // Atribui um ID único ao post
        posts.add(post);  // Adiciona o post à lista
    }

    // Método para buscar um post por ID
    public Post buscarPorId(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    // Método para listar os posts de um usuário
    public List<Post> listarPorUsuario(int idUsuario) {
        List<Post> resultado = new ArrayList<>();
        for (Post post : posts) {
            if (post.getAutor().getId() == idUsuario) {
                resultado.add(post);
            }
        }
        return resultado;
    }

    // Método para curtir um post
    public void curtir(int idPost, int idUsuario) {
        Post post = buscarPorId(idPost);
        Usuario usuario = new GerenciadorUsuarios().buscarPorId(idUsuario); // Método da classe GerenciadorUsuarios para buscar um usuário

        if (post != null && usuario != null) {
            post.adicionarCurtida(usuario);
        }
    }

    // Método para descurtir um post
    public void descurtir(int idPost, int idUsuario) {
        Post post = buscarPorId(idPost);
        Usuario usuario = new GerenciadorUsuarios().buscarPorId(idUsuario); // Método da classe GerenciadorUsuarios para buscar um usuário

        if (post != null && usuario != null) {
            post.removerCurtida(usuario);
        }
    }

    // Método para adicionar um comentário a um post
    public void comentar(Comentario comentario) {
        Post post = buscarPorId(comentario.getPost().getId());
        if (post != null) {
            post.adicionarComentario(comentario);
        }
    }

    // Método para deletar um post
    public boolean deletar(int id) {
        Post post = buscarPorId(id);
        if (post != null) {
            posts.remove(post);
            return true;
        }
        return false;
    }

    // Método privado para validar um post antes de adicionar
    private void validarPost(Post post) {
        // Aqui você pode adicionar validações, como garantir que o conteúdo do post não seja vazio, etc.
        if (post.getConteudo().isEmpty()) {
            throw new IllegalArgumentException("O conteúdo do post não pode ser vazio.");
        }
    }
}
