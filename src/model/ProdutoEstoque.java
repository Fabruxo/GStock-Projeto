package model;

public class ProdutoEstoque {
    private long id;
    private Produto produto;
    private Estoque estoque;
    private String corredor;
    private String bin;
    private double quantidade;

    public ProdutoEstoque(long id, Produto produto, Estoque estoque, String corredor, String bin, double quantidade) {
        this.id = id;
        this.produto = produto;
        this.estoque = estoque;
        this.corredor = corredor;
        this.bin = bin;
        this.quantidade = quantidade;
    }

    public ProdutoEstoque(Produto produto, Estoque estoque, String corredor, String bin, double quantidade) {
        this(0, produto, estoque, corredor, bin, quantidade);
    }

    public long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public String getCorredor() {
        return corredor;
    }

    public String getBin() {
        return bin;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCorredor(String corredor) {
        this.corredor = corredor;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
}
