package model;

public class UsuarioAdmin extends Usuario {

    public UsuarioAdmin(long id, String nome, String login, String senha) {
        super(id, nome, login, senha, TipoUsuario.ADMIN);
    }

    @Override
    public void login() {
        System.out.println("Admin logado.");
    }

    @Override
    public void logout() {
        System.out.println("Admin saiu.");
    }
}
