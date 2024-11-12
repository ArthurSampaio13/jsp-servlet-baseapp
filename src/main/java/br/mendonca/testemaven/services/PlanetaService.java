package br.mendonca.testemaven.services;

import br.mendonca.testemaven.dao.PlanetaDAO;
import br.mendonca.testemaven.model.entities.Planeta;
import br.mendonca.testemaven.services.dto.PlanetaDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanetaService {

    public void adicionar(String nome, int densidade, boolean possuiAgua) throws ClassNotFoundException, SQLException {
        PlanetaDAO dao = new PlanetaDAO();

        Planeta planeta = new Planeta();
        planeta.setNome(nome);
        planeta.setDensidade(densidade);
        planeta.setPossuiAgua(possuiAgua);

        dao.adicionar(planeta);
    }

    public List<PlanetaDTO> listAllPlanetas() throws ClassNotFoundException, SQLException {
        ArrayList<PlanetaDTO> resp = new ArrayList<PlanetaDTO>();

        PlanetaDAO dao = new PlanetaDAO();
        List<Planeta> lista = dao.listAllPlanetas();

        for (Planeta planeta : lista) {
            resp.add(PlanetaDTO.planetaMapper(planeta));
        }

        return resp;
    }

    public List<PlanetaDTO> listPlanetasWithPagination(int pageNumber, int pageSize) throws ClassNotFoundException, SQLException {
        ArrayList<PlanetaDTO> resp = new ArrayList<>();

        PlanetaDAO dao = new PlanetaDAO();
        List<Planeta> lista = dao.listPlanetasWithPagination(pageNumber, pageSize);

        for (Planeta planeta : lista) {
            resp.add(PlanetaDTO.planetaMapper(planeta));
        }

        return resp;
    }

    public int countPlanetas() throws ClassNotFoundException, SQLException {
        PlanetaDAO dao = new PlanetaDAO();
        return dao.countPlanetas();
    }
}
