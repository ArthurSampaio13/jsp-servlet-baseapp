package br.mendonca.testemaven.services;

import br.mendonca.testemaven.services.dto.AtividadeDTO;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AtividadeService {
    private EstrelaService estrelaService;
    private GalaxiaService galaxiaService;
    private PlanetaService planetaService;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");

    public AtividadeService() {
        this.estrelaService = new EstrelaService();
        this.galaxiaService = new GalaxiaService();
        this.planetaService = new PlanetaService();
    }

    public List<AtividadeDTO> listarAtividades() throws SQLException, ClassNotFoundException {
        List<AtividadeDTO> atividades = new ArrayList<>();
        atividades.addAll(estrelaService.listAllEstrelas().stream().map(estrela -> {
            AtividadeDTO dto = new AtividadeDTO();
            dto.setTipo("Estrela");
            dto.setNome(estrela.getNome());
            dto.setTemperatura(estrela.getTemperatura());
            dto.setEstaNaViaLactea(estrela.getEstaNaViaLactea());
            dto.setDateCreated(estrela.getDateCreated().format(formatter));
            dto.setDescricao("Estrela chamada " + estrela.getNome() + " com temperatura de " + estrela.getTemperatura());
            return dto;
        }).collect(Collectors.toList()));

        atividades.addAll(galaxiaService.listAllGalaxias().stream().map(galaxia -> {
            AtividadeDTO dto = new AtividadeDTO();
            dto.setTipo("Galaxia");
            dto.setNome(galaxia.getNome());
            dto.setQuantidadeDeEstrelas(galaxia.getQuantidadeDeEstrelas());
            dto.setEstaNaViaLactea(galaxia.isViaLactea());
            dto.setDateCreated(galaxia.getDateCreated().format(formatter));
            dto.setDescricao("Galáxia chamada " + galaxia.getNome() + " contendo " + galaxia.getQuantidadeDeEstrelas() + " estrelas.");
            return dto;
        }).collect(Collectors.toList()));

        atividades.addAll(planetaService.listAllPlanetas().stream().map(planeta -> {
            AtividadeDTO dto = new AtividadeDTO();
            dto.setTipo("Planeta");
            dto.setNome(planeta.getNome());
            dto.setPossuiAgua(planeta.isPossuiAgua());
            dto.setDensidade(planeta.getDensidade());
            dto.setDateCreated(planeta.getDateCreated().format(formatter));
            dto.setDescricao("Planeta chamado " + planeta.getNome() + (planeta.isPossuiAgua() ? " que possui água." : " que não possui água."));
            return dto;
        }).collect(Collectors.toList()));

        return atividades.stream()
                .sorted(Comparator.comparing(AtividadeDTO::getDateCreated)) // Ordenar por descrição ou data (se adicionada no DTO)
                .collect(Collectors.toList());
    }


}
