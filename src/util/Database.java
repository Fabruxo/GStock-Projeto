package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlite:banco.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void inicializarBanco() {
        String sqlUsuario = """
            CREATE TABLE IF NOT EXISTS usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                login TEXT NOT NULL UNIQUE,
                senha TEXT NOT NULL,
                tipo_usuario TEXT NOT NULL
            );
        """;

        String sqlProduto = """
            CREATE TABLE IF NOT EXISTS produtos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                anoSuportadoInicio TEXT,
                anoSuportadoFim TEXT,
                modeloCarro TEXT
            );
        """;

        String sqlEstoque = """
            CREATE TABLE IF NOT EXISTS estoques (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                posicao TEXT
            );
        """;

        String sqlProdutoEstoque = """
            CREATE TABLE IF NOT EXISTS produtos_estoque (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                id_produto INTEGER NOT NULL,
                id_estoque INTEGER NOT NULL,
                corredor TEXT,
                bin TEXT,
                quantidade REAL,
                FOREIGN KEY (id_produto) REFERENCES produtos(id),
                FOREIGN KEY (id_estoque) REFERENCES estoques(id)
            );
        """;

        String sqlHistorico = """
            CREATE TABLE IF NOT EXISTS historico_retiradas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                id_produto_estoque INTEGER NOT NULL,
                quantidade_retirada REAL,
                data_hora TEXT,
                usuario TEXT,
                FOREIGN KEY (id_produto_estoque) REFERENCES produtos_estoque(id)
            );
        """;



        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sqlUsuario);
            stmt.execute(sqlUsuario);
            stmt.execute(sqlProduto);
            stmt.execute(sqlEstoque);
            stmt.execute(sqlProdutoEstoque);
            stmt.execute(sqlHistorico);

            System.out.println("Banco inicializado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inicializar o banco: " + e.getMessage());
        }
    }
}
