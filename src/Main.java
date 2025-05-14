import dao.*;
import model.*;
import util.Database;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Database.inicializarBanco();

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        System.out.println("=== SISTEMA DE ESTOQUE ===");
        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario usuarioLogado = usuarioDAO.buscarPorLoginSenha(login, senha);

        if (usuarioLogado == null) {
            System.out.println("❌ Login inválido!");
            return;
        }

        System.out.println("\n✅ Bem-vindo, " + usuarioLogado.getNome() + " (" + usuarioLogado.getTipoUsuario() + ")");

        if (usuarioLogado instanceof UsuarioAdmin) {
            menuAdmin();
        } else {
            menuUsuarioComum();
        }
    }

    private static void menuAdmin() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        ProdutoEstoqueDAO peDAO = new ProdutoEstoqueDAO();

        int opcao;
        do {
            System.out.println("\n=== MENU ADMIN ===");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Cadastrar Estoque");
            System.out.println("3. Vincular Produto ao Estoque");
            System.out.println("4. Listar Produtos");
            System.out.println("5. Listar Estoques");
            System.out.println("6. Listar Produtos em Estoque");
            System.out.println("7. Retirar Produto do Estoque");
            System.out.println("8. Ver Histórico de Retiradas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do produto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Ano início: ");
                    String inicio = scanner.nextLine();
                    System.out.print("Ano fim: ");
                    String fim = scanner.nextLine();
                    System.out.print("Modelo carro: ");
                    String modelo = scanner.nextLine();

                    Produto produto = new Produto(nome, inicio, fim, modelo);
                    produtoDAO.inserirProduto(produto);
                }

                case 2 -> {
                    System.out.print("Nome do estoque: ");
                    String nome = scanner.nextLine();
                    System.out.print("Posição: ");
                    String posicao = scanner.nextLine();

                    Estoque estoque = new Estoque(nome, posicao);
                    estoqueDAO.inserirEstoque(estoque);
                }

                case 3 -> {
                    List<Produto> produtos = produtoDAO.listarProdutos();
                    List<Estoque> estoques = estoqueDAO.listarEstoques();

                    if (produtos.isEmpty() || estoques.isEmpty()) {
                        System.out.println("⚠️ Cadastre produtos e estoques antes.");
                        break;
                    }

                    System.out.println("Produtos:");
                    for (Produto p : produtos) {
                        System.out.println(p.getId() + " - " + p.getNome());
                    }

                    System.out.print("ID do produto: ");
                    long idProduto = Long.parseLong(scanner.nextLine());
                    Produto produto = produtos.stream().filter(p -> p.getId() == idProduto).findFirst().orElse(null);

                    System.out.println("Estoques:");
                    for (Estoque e : estoques) {
                        System.out.println(e.getId() + " - " + e.getNome());
                    }

                    System.out.print("ID do estoque: ");
                    long idEstoque = Long.parseLong(scanner.nextLine());
                    Estoque estoque = estoques.stream().filter(e -> e.getId() == idEstoque).findFirst().orElse(null);

                    if (produto == null || estoque == null) {
                        System.out.println("❌ Produto ou Estoque inválido.");
                        break;
                    }

                    System.out.print("Corredor: ");
                    String corredor = scanner.nextLine();
                    System.out.print("Bin: ");
                    String bin = scanner.nextLine();
                    System.out.print("Quantidade: ");
                    double qtd = Double.parseDouble(scanner.nextLine());

                    ProdutoEstoque pe = new ProdutoEstoque(produto, estoque, corredor, bin, qtd);
                    peDAO.inserirProdutoEmEstoque(pe);
                }

                case 4 -> {
                    System.out.println("\n--- Produtos ---");
                    for (Produto p : produtoDAO.listarProdutos()) {
                        System.out.println(p.getId() + " - " + p.getNome() + " (" + p.getModeloCarro() + ")");
                    }
                }

                case 5 -> {
                    System.out.println("\n--- Estoques ---");
                    for (Estoque e : estoqueDAO.listarEstoques()) {
                        System.out.println(e.getId() + " - " + e.getNome() + " - " + e.getPosicao());
                    }
                }

                case 6 -> {
                    System.out.println("\n--- Produtos em Estoque ---");
                    for (ProdutoEstoque pe : peDAO.listarProdutosEmEstoque()) {
                        System.out.println("Produto: " + pe.getProduto().getNome() +
                                " | Estoque: " + pe.getEstoque().getNome() +
                                " | Qtd: " + pe.getQuantidade() +
                                " | Corredor: " + pe.getCorredor() +
                                " | Bin: " + pe.getBin());
                    }
                }

                case 7 -> {
                    List<ProdutoEstoque> lista = peDAO.listarProdutosEmEstoque();

                    if (lista.isEmpty()) {
                        System.out.println("⚠️ Nenhum produto em estoque para retirar.");
                        break;
                    }

                    System.out.println("\n--- Produtos em Estoque ---");
                    for (ProdutoEstoque pe : lista) {
                        System.out.println(pe.getId() + " - " + pe.getProduto().getNome() +
                                " | Estoque: " + pe.getEstoque().getNome() +
                                " | Quantidade: " + pe.getQuantidade());
                    }

                    System.out.print("ID do ProdutoEstoque para retirar: ");
                    long idRetirar = Long.parseLong(scanner.nextLine());

                    System.out.print("Quantidade a retirar: ");
                    double qtdRetirar = Double.parseDouble(scanner.nextLine());

                    peDAO.retirarProdutoDoEstoque(idRetirar, qtdRetirar);
                }
                case 8 -> {
                    HistoricoRetiradaDAO historicoDAO = new HistoricoRetiradaDAO();
                    List<HistoricoRetirada> historico = historicoDAO.listar();

                    System.out.println("\n--- Histórico de Retiradas ---");
                    for (HistoricoRetirada h : historico) {
                        System.out.println("ID ProdutoEstoque: " + h.getIdProdutoEstoque() +
                                " | Quantidade: " + h.getQuantidadeRetirada() +
                                " | Data: " + h.getDataHora() +
                                " | Usuário: " + h.getUsuario());
                    }
                }

                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("❌ Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void menuUsuarioComum() {
        ProdutoEstoqueDAO peDAO = new ProdutoEstoqueDAO();

        int opcao;
        do {
            System.out.println("\n=== MENU USUÁRIO COMUM ===");
            System.out.println("1. Ver Produtos em Estoque");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> {
                    System.out.println("\n--- Produtos em Estoque ---");
                    for (ProdutoEstoque pe : peDAO.listarProdutosEmEstoque()) {
                        System.out.println("Produto: " + pe.getProduto().getNome() +
                                " | Estoque: " + pe.getEstoque().getNome() +
                                " | Qtd: " + pe.getQuantidade() +
                                " | Corredor: " + pe.getCorredor() +
                                " | Bin: " + pe.getBin());
                    }
                }

                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("❌ Opção inválida.");
            }

        } while (opcao != 0);
    }
}
