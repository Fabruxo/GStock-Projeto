import dao.UsuarioDAO;
import model.*;
import util.Database;

public class Main {
    public static void main(String[] args) {
        Database.inicializarBanco();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario admin = new UsuarioAdmin(0, "Heric", "admin", "123");
        usuarioDAO.inserirUsuario(admin);

        Usuario logado = usuarioDAO.buscarPorLoginSenha("admin", "123");

        if (logado != null) {
            System.out.println("Login realizado com sucesso!");
            System.out.println("Bem-vindo, " + logado.getNome());
            System.out.println("Tipo de usuário: " + logado.getTipoUsuario());
        }
    }
}
