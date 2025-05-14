import dao.*;
import model.*;
import util.Database;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database.inicializarBanco();

        // --------- USUÁRIO ---------
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario admin = new UsuarioAdmin(0, "Heric", "admin", "123");
        usuarioDAO.inserirUsuario(admin);

        Usuario logado = usuarioDAO.buscarPorLoginSenha("admin", "123");

        if (logado != null) {
            System.out.println("Login realizado com sucesso!");
            System.out.println("Bem-vindo, " + logado.getNome());
            System.out.println("Tipo de usuário: " + logado.getTipoUsuario());
        }

        // --------- PRODUTO ---------
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = new Produto("Filtro de Ar", "2010", "2020", "Civic");
        produtoDAO.inserirProduto(produto);

        // --------- ESTOQUE ---------
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        Estoque estoque = new Estoque("Estoque Central", "Bloco A");
        estoqueDAO.inserirEstoque(estoque);

        // --------- PRODUTO ESTOCADO ---------
        List<Produto> produtos = produtoDAO.listarProdutos();
        List<Estoque> estoques = estoqueDAO.listarEstoques();

        if (!produtos.isEmpty() && !estoques.isEmpty()) {
            Produto produtoBD = produtos.get(0);
            Estoque estoqueBD = estoques.get(0);

            ProdutoEstoqueDAO peDAO = new ProdutoEstoqueDAO();
            ProdutoEstoque pe = new ProdutoEstoque(produtoBD, estoqueBD, "C2", "BIN-03", 20.0);
            peDAO.inserirProdutoEmEstoque(pe);

            // --------- LISTAR PRODUTOS EM ESTOQUE ---------
            System.out.println("\nProdutos em Estoque:");
            for (ProdutoEstoque item : peDAO.listarProdutosEmEstoque()) {
                System.out.println("Produto: " + item.getProduto().getNome());
                System.out.println("Modelo: " + item.getProduto().getModeloCarro());
                System.out.println("Estoque: " + item.getEstoque().getNome());
                System.out.println("Corredor: " + item.getCorredor());
                System.out.println("Bin: " + item.getBin());
                System.out.println("Quantidade: " + item.getQuantidade());
                System.out.println("---------------------------");
            }
        } else {
            System.out.println("⚠️ Nenhum produto ou estoque encontrado.");
        }

        // ----------- EDIÇÃO -----------

        // Editar Produto
        if (!produtos.isEmpty()) {
            Produto produtoEdit = produtos.get(0);
            produtoEdit.setId(produtoEdit.getId());
            produtoEdit = new Produto(produtoEdit.getId(), "Filtro de Cabine", "2011", "2022", "Civic");
            produtoDAO.editarProduto(produtoEdit);
        }

        // Editar Estoque
        if (!estoques.isEmpty()) {
            Estoque estoqueEdit = estoques.get(0);
            estoqueEdit = new Estoque(estoqueEdit.getId(), "Estoque Atualizado", "Setor B");
            estoqueDAO.editarEstoque(estoqueEdit);
        }

        // Editar Produto em Estoque
        ProdutoEstoqueDAO peDAO = new ProdutoEstoqueDAO();
        List<ProdutoEstoque> listaEstoque = peDAO.listarProdutosEmEstoque();

        if (!listaEstoque.isEmpty()) {
            ProdutoEstoque peEdit = listaEstoque.get(0);
            peEdit.setCorredor("D1");
            peEdit.setBin("BIN-99");
            peEdit.setQuantidade(peEdit.getQuantidade() + 10);
            peDAO.editarProdutoEmEstoque(peEdit);
        }

        // ----------- EXCLUSÃO -----------

        // Excluir Produto em Estoque
        if (!listaEstoque.isEmpty()) {
            ProdutoEstoque peDelete = listaEstoque.get(0);
            peDAO.deletarProdutoEmEstoque(peDelete.getId());
        }

        // Excluir Produto
        if (!produtos.isEmpty()) {
            Produto produtoDelete = produtos.get(0);
            produtoDAO.deletarProduto(produtoDelete.getId());
        }

        // Excluir Estoque
        if (!estoques.isEmpty()) {
            Estoque estoqueDelete = estoques.get(0);
            estoqueDAO.deletarEstoque(estoqueDelete.getId());
        }


    }
}
