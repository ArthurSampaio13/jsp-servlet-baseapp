package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.Galaxia;

public class GalaxiaDTO {
    private String id;
    private String nome;
    private int quantidadeDeEstrelas;
    private boolean viaLactea;

    public static GalaxiaDTO galaxiaMapper(Galaxia galaxia) {
        GalaxiaDTO dto = new GalaxiaDTO();
        dto.id = galaxia.getId().toString();
        dto.nome = galaxia.getNome();
        dto.quantidadeDeEstrelas = galaxia.getQuantidadeDeEstrelas();
        dto.viaLactea = galaxia.isViaLactea();
        return dto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
