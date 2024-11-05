package br.mendonca.testemaven.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.mendonca.testemaven.dao.GalaxiaDAO;
import br.mendonca.testemaven.model.entities.Galaxia;
import br.mendonca.testemaven.services.dto.GalaxiaDTO;

public class GalaxiaService {

    public void register(String nome, int quantidadeDeEstrelas, boolean viaLactea) throws ClassNotFoundException, SQLException {
        GalaxiaDAO dao = new GalaxiaDAO();

        Galaxia galaxia = new Galaxia(nome, quantidadeDeEstrelas, viaLactea);
        dao.register(galaxia);
    }

    public List<GalaxiaDTO> listAllGalaxias() throws ClassNotFoundException, SQLException {
        ArrayList<GalaxiaDTO> resp = new ArrayList<>();

        GalaxiaDAO dao = new GalaxiaDAO();
        List<Galaxia> lista = dao.listAllGalaxias();

        for (Galaxia galaxia : lista) {
            resp.add(GalaxiaDTO.galaxiaMapper(galaxia));
        }

        return resp;
    }
}