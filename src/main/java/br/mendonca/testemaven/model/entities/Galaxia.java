package br.mendonca.testemaven.model.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Galaxia {
    private UUID id;
    private String nome;
    private int quantidadeDeEstrelas;
    private boolean viaLactea;
    private boolean isVisible;
    private LocalDateTime dateCreated;

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Galaxia(String nome, int quantidadeDeEstrelas, boolean viaLactea, boolean isVisible) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.quantidadeDeEstrelas = quantidadeDeEstrelas;
        this.viaLactea = viaLactea;
        this.isVisible = isVisible;
    }

    public Galaxia(String nome, int quantidadeDeEstrelas, boolean viaLactea, boolean isVisible, LocalDateTime dateCreated) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.quantidadeDeEstrelas = quantidadeDeEstrelas;
        this.viaLactea = viaLactea;
        this.isVisible = isVisible;
        this.dateCreated = dateCreated;
    }

    public Galaxia() {

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

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}