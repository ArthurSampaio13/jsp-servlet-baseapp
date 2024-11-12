package br.mendonca.testemaven.model.entities;

public class Estrela {
    private String uuid;
    private String nome;
    private Integer temperatura;
    private Boolean estaNaViaLactea;
    private Boolean estaAtivo;

    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(Integer temperatura) {
        this.temperatura = temperatura;
    }
    public Boolean getEstaNaViaLactea() {
        return estaNaViaLactea;
    }
    public void setEstaNaViaLactea(Boolean estaNaViaLactea) {
        this.estaNaViaLactea = estaNaViaLactea;
    }
    public Boolean getEstaAtivo() {
        return estaAtivo;
    }
    public void setEstaAtivo(Boolean estaAtivo) {
        this.estaAtivo = estaAtivo;
    }
}

