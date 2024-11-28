package com.redesocial.Gerenciador;

import com.redesocial.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorUsuarios {
    private List<Usuario> usuarios = new ArrayList<>();
    private int proximoId = 1;

    public void cadastrar(Usuario usuario) {
        // Verifica se o email contém o caractere '@'
        if (!usuario.getEmail().contains("@")) {
            System.out.println("Erro: O email precisa conter '@'. Não foi possível cadastrar o usuário.");
            return;
        }

        usuario.setId(proximoId++);
        usuarios.add(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public Usuario buscarPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscarPorUsername(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;
    }

    public List<Usuario> buscarPorNome(String nome) {
        List<Usuario> resultado = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().contains(nome)) {
                resultado.add(usuario);
            }
        }
        return resultado;
    }

    // Método para listar todos os usuários cadastrados
    public List<Usuario> listarTodosUsuarios() {
        return usuarios;
    }

    // Novo método para buscar e exibir todos os usuários cadastrados
    public void buscarUsuariosCadastrados() {
        if (usuarios.isEmpty()) {
            System.out.println("Não há usuários cadastrados.");
        } else {
            System.out.println("Usuários cadastrados:");
            for (Usuario usuario : usuarios) {
                System.out.println("ID: " + usuario.getId() + ", Nome: " + usuario.getNome() + ", Email: " + usuario.getEmail());
            }
        }
    }

    public boolean atualizar(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == usuario.getId()) {
                usuarios.set(i, usuario);
                return true;
            }
        }
        return false;
    }

    public boolean deletar(int id) {
        Usuario usuario = buscarPorId(id);
        if (usuario != null) {
            usuarios.remove(usuario);
            return true;
        }
        return false;
    }
}
