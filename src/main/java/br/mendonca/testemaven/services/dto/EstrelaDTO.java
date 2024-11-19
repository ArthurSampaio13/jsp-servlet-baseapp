package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.Estrela;

import java.time.LocalDateTime;

public class EstrelaDTO {
    private String uuid;
    private String nome;
    private Integer temperatura;
    private Boolean estaNaViaLactea;
    private Boolean estaAtivo;
    private LocalDateTime dateCreated;

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

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

    public static EstrelaDTO estrelaMapper(Estrela estrela) {
        EstrelaDTO dto = new EstrelaDTO();
        dto.setUuid(estrela.getUuid());
        dto.setNome(estrela.getNome());
        dto.setTemperatura(estrela.getTemperatura());
        dto.setEstaNaViaLactea(estrela.getEstaNaViaLactea());
        dto.setEstaAtivo(estrela.getEstaAtivo());
        dto.setDateCreated(estrela.getDateCreated());
        return dto;
    }

}
