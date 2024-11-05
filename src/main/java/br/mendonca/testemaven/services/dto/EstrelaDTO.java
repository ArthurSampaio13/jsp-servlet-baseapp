package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.Estrela;

public class EstrelaDTO {
    private String nome;
    private Integer temperatura;
    private Boolean estaNaViaLactea;

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

    public static EstrelaDTO estrelaMapper(Estrela estrela) {
        EstrelaDTO dto = new EstrelaDTO();
        dto.setNome(estrela.getNome());
        dto.setTemperatura(estrela.getTemperatura());
        dto.setEstaNaViaLactea(estrela.getEstaNaViaLactea());

        return dto;
    }

}
