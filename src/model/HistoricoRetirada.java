package model;

public class HistoricoRetirada {
    private long id;
    private long idProdutoEstoque;
    private double quantidadeRetirada;
    private String dataHora;
    private String usuario;

    public HistoricoRetirada(long id, long idProdutoEstoque, double quantidadeRetirada, String dataHora, String usuario) {
        this.id = id;
        this.idProdutoEstoque = idProdutoEstoque;
        this.quantidadeRetirada = quantidadeRetirada;
        this.dataHora = dataHora;
        this.usuario = usuario;
    }

    public HistoricoRetirada(long idProdutoEstoque, double quantidadeRetirada, String dataHora, String usuario) {
        this(0, idProdutoEstoque, quantidadeRetirada, dataHora, usuario);
    }

    public long getId() {
        return id;
    }

    public long getIdProdutoEstoque() {
        return idProdutoEstoque;
    }

    public double getQuantidadeRetirada() {
        return quantidadeRetirada;
    }

    public String getDataHora() {
        return dataHora;
    }

    public String getUsuario() {
        return usuario;
    }
}
