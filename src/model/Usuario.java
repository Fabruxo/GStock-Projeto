//SuperClasse para Usuario

package model;

public abstract class Usuario {
    protected long id;
    protected String nome;
    protected String login;
    protected String senha;
    protected TipoUsuario tipoUsuario;

    public Usuario(long id, String nome, String login, String senha, TipoUsuario tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public long getId() { return id; }
    public String getNome() { return nome; }
    public String getLogin() { return login; }
    public String getSenha() { return senha; }
    public TipoUsuario getTipoUsuario() { return tipoUsuario; }

    public abstract void login();
    public abstract void logout();
}
