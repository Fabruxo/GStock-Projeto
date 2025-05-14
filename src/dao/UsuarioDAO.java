package dao;

import model.*;
import util.Database;

import java.sql.*;

public class UsuarioDAO {

    public void inserirUsuario(Usuario usuario) {
        if (usuarioExiste(usuario.getLogin())) {
            System.out.println("Usuário com login '" + usuario.getLogin() + "' já existe. Inserção cancelada.");
            return;
        }

        String sql = "INSERT INTO usuarios (nome, login, senha, tipo_usuario) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipoUsuario().name());
            stmt.executeUpdate();

            System.out.println("Usuário inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    public Usuario buscarPorLoginSenha(String login, String senha) {
        String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String tipoStr = rs.getString("tipo_usuario");

                TipoUsuario tipo = TipoUsuario.valueOf(tipoStr.toUpperCase());

                if (tipo == TipoUsuario.ADMIN) {
                    return new UsuarioAdmin(id, nome, login, senha);
                } else {
                    return new UsuarioComum(id, nome, login, senha);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }

        return null;
    }

    public boolean usuarioExiste(String login) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE login = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao verificar existência do usuário: " + e.getMessage());
        }

        return false;
    }
}
