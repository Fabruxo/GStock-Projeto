package model;

public class Produto {
    private long id;
    private String nome;
    private String anoSuportadoInicio;
    private String anoSuportadoFim;
    private String modeloCarro;

    public Produto(long id, String nome, String anoSuportadoInicio, String anoSuportadoFim, String modeloCarro) {
        this.id = id;
        this.nome = nome;
        this.anoSuportadoInicio = anoSuportadoInicio;
        this.anoSuportadoFim = anoSuportadoFim;
        this.modeloCarro = modeloCarro;
    }

    public Produto(String nome, String anoSuportadoInicio, String anoSuportadoFim, String modeloCarro) {
        this(0, nome, anoSuportadoInicio, anoSuportadoFim, modeloCarro);
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getAnoSuportadoInicio() {
        return anoSuportadoInicio;
    }

    public String getAnoSuportadoFim() {
        return anoSuportadoFim;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setId(long id) {
        this.id = id;
    }
}
