package com.redesocial.gerenciador;

import com.redesocial.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

 // Permite cadastrar, buscar e listar usuários.

public class GerenciadorUsuarios {
    private List<Usuario> usuarios;
    private int proximoId;

    public GerenciadorUsuarios() {
        this.usuarios = new ArrayList<>();
        this.proximoId = 1;
    }

    // cadastrar um novo usuário
    public void cadastrar(Usuario usuario) {
        usuario.setId(proximoId++);
        usuarios.add(usuario);
    }

    // Método para buscar um usuário pelo ID
    public Usuario buscarPorId(int id) {
        return usuarios.stream()
                .filter(usuario -> usuario.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Método para buscar um usuário pelo username
    public Usuario buscarPorUsername(String username) {
        return usuarios.stream()
                .filter(usuario -> usuario.getUsername().equals(username))
                .findFirst()
                .orElse(null);

    }
}