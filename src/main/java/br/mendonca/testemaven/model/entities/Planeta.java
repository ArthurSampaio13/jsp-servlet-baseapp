package br.mendonca.testemaven.model.entities;

import java.util.UUID;

public class Planeta {
    private UUID uuid;
    private String nome;
    private int densidade;
    private boolean possuiAgua;
    private boolean ativo = true;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDensidade() {
        return densidade;
    }

    public void setDensidade(int densidade) {
        this.densidade = densidade;
    }

    public boolean isPossuiAgua() {
        return possuiAgua;
    }

    public void setPossuiAgua(boolean possuiAgua) {
        this.possuiAgua = possuiAgua;
    }
}
