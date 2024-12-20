package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.Galaxia;

import java.time.LocalDateTime;
import java.util.UUID;

public class GalaxiaDTO {
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

    public static GalaxiaDTO galaxiaMapper(Galaxia galaxia) {
        GalaxiaDTO dto = new GalaxiaDTO();
        dto.id = galaxia.getId();
        dto.nome = galaxia.getNome();
        dto.quantidadeDeEstrelas = galaxia.getQuantidadeDeEstrelas();
        dto.viaLactea = galaxia.isViaLactea();
        dto.isVisible = galaxia.isVisible();
        dto.dateCreated = galaxia.getDateCreated();
        return dto;
    }

    public UUID getId() {
        return id;
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

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
