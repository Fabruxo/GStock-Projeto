package model;

public class Estoque {
    private long id;
    private String nome;
    private String posicao;

    public Estoque(long id, String nome, String posicao) {
        this.id = id;
        this.nome = nome;
        this.posicao = posicao;
    }

    public Estoque(String nome, String posicao) {
        this(0, nome, posicao);
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setId(long id) {
        this.id = id;
    }
}
