package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.Planeta;

import java.util.UUID;

public class PlanetaDTO {
    private UUID uuid;
    private String nome;
    private int densidade;
    private boolean possuiAgua;
    private boolean ativo = true;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
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

    public static PlanetaDTO planetaMapper(Planeta planeta) {
        PlanetaDTO planetaDTO = new PlanetaDTO();
        planetaDTO.setUuid(planeta.getUuid());
        planetaDTO.setNome(planeta.getNome());
        planetaDTO.setDensidade(planeta.getDensidade());
        planetaDTO.setPossuiAgua(planeta.isPossuiAgua());
        planetaDTO.setAtivo(planeta.isAtivo());

        return planetaDTO;
    }
}
