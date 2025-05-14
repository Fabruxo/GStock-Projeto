package dao;

import model.HistoricoRetirada;
import util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoRetiradaDAO {

    public void registrar(HistoricoRetirada h) {
        String sql = "INSERT INTO historico_retiradas (id_produto_estoque, quantidade_retirada, data_hora, usuario) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, h.getIdProdutoEstoque());
            stmt.setDouble(2, h.getQuantidadeRetirada());
            stmt.setString(3, h.getDataHora());
            stmt.setString(4, h.getUsuario());

            stmt.executeUpdate();
            System.out.println("📌 Retirada registrada no histórico.");
        } catch (SQLException e) {
            System.out.println("Erro ao registrar retirada: " + e.getMessage());
        }
    }

    public List<HistoricoRetirada> listar() {
        List<HistoricoRetirada> lista = new ArrayList<>();
        String sql = "SELECT * FROM historico_retiradas ORDER BY id DESC";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                HistoricoRetirada h = new HistoricoRetirada(
                        rs.getLong("id"),
                        rs.getLong("id_produto_estoque"),
                        rs.getDouble("quantidade_retirada"),
                        rs.getString("data_hora"),
                        rs.getString("usuario")
                );
                lista.add(h);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar histórico: " + e.getMessage());
        }

        return lista;
    }
}
