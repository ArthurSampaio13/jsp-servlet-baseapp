package br.mendonca.testemaven.services.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class AtividadeDTO {
    private UUID id;
    private String tipo;       // Tipo da entidade (Estrela, Galáxia, Planeta)
    private String nome;       // Nome da entidade
    private Integer temperatura;  // Para Estrelas (opcional)
    private Boolean estaNaViaLactea; // Para Estrelas e Galáxias
    private Boolean possuiAgua; // Para Planetas (opcional)
    private Integer quantidadeDeEstrelas; // Para Galáxias (opcional)
    private Boolean estaAtivo; // Para todos, exceto Galáxias
    private String descricao; // Descrição customizada
    private int densidade; // Para Planetas
    private int quantidadeCurtidas;
    private String dateCreated;

    public int getQuantidadeCurtidas() {
        return quantidadeCurtidas;
    }

    public void setQuantidadeCurtidas(int quantidadeCurtidas) {
        this.quantidadeCurtidas = quantidadeCurtidas;
    }

    public String getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public AtividadeDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public Boolean getPossuiAgua() {
        return possuiAgua;
    }

    public void setPossuiAgua(Boolean possuiAgua) {
        this.possuiAgua = possuiAgua;
    }

    public Integer getQuantidadeDeEstrelas() {
        return quantidadeDeEstrelas;
    }

    public void setQuantidadeDeEstrelas(Integer quantidadeDeEstrelas) {
        this.quantidadeDeEstrelas = quantidadeDeEstrelas;
    }

    public Boolean getEstaAtivo() {
        return estaAtivo;
    }

    public void setEstaAtivo(Boolean estaAtivo) {
        this.estaAtivo = estaAtivo;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDensidade() {
        return densidade;
    }

    public void setDensidade(int densidade) {
        this.densidade = densidade;
    }

}
