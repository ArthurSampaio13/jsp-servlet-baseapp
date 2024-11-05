package br.mendonca.testemaven.model.entities;

import java.util.UUID;

public class Galaxia {
    private UUID id;
    private String nome;
    private int quantidadeDeEstrelas;
    private boolean viaLactea;

    public Galaxia(String nome, int quantidadeDeEstrelas, boolean viaLactea) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.quantidadeDeEstrelas = quantidadeDeEstrelas;
        this.viaLactea = viaLactea;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeDeEstrelas() {
        return quantidadeDeEstrelas;
    }

    public void setQuantidadeDeEstrelas(int quantidadeDeEstrelas) {
        this.quantidadeDeEstrelas = quantidadeDeEstrelas;
    }

    public boolean isViaLactea() {
        return viaLactea;
    }

    public void setViaLactea(boolean viaLactea) {
        this.viaLactea = viaLactea;
    }
}