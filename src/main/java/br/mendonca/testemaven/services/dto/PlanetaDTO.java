package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.Planeta;

public class PlanetaDTO {
    private String nome;
    private int densidade;
    private boolean possuiAgua;

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
        planetaDTO.setNome(planeta.getNome());
        planetaDTO.setDensidade(planeta.getDensidade());
        planetaDTO.setPossuiAgua(planeta.isPossuiAgua());

        return planetaDTO;
    }
}
