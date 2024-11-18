import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {
    // Atributos
    private Integer id;
    private String nome;
    private String username;
    private String email;
    private String senha;
    private LocalDateTime dataCadastro;
    private List<Usuario> amigos;
    private List<Post> posts;

    // Construtor
    public Usuario(String nome, String username, String email, String senha) {
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.dataCadastro = LocalDateTime.now(); // A data é definida no momento do cadastro
        this.amigos = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Usuario> amigos) {
        this.amigos = amigos;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    // Método toString para exibição formatada
    @Override
    public String toString() {
        return "Usuário [id=" + id + ", nome=" + nome + ", username=" + username + ", email=" + email
                + ", dataCadastro=" + dataCadastro + ", quantidade de amigos=" + amigos.size()
                + ", quantidade de posts=" + posts.size() + "]";
    }

    // Método equals e hashCode baseados no id
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Método para adicionar um amigo
    public void adicionarAmigo(Usuario amigo) {
        if (!this.amigos.contains(amigo)) {
            this.amigos.add(amigo);
            amigo.adicionarAmigo(this); // Amizade é bidirecional
        }
    }

    // Método para remover um amigo
    public void removerAmigo(Usuario amigo) {
        this.amigos.remove(amigo);
        amigo.removerAmigo(this); // Amizade é bidirecional
    }

    // Método para adicionar um post
    public void adicionarPost(Post post) {
        this.posts.add(post);
    }
}
