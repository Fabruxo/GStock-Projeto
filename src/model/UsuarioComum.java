package model;

public class UsuarioComum extends Usuario {

    public UsuarioComum(long id, String nome, String login, String senha) {
        super(id, nome, login, senha, TipoUsuario.COMUM);
    }

    @Override
    public void login() {
        System.out.println("Usuário comum logado.");
    }

    @Override
    public void logout() {
        System.out.println("Usuário comum saiu.");
    }
}
