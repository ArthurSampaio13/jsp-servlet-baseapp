package br.mendonca.testemaven.services;

import br.mendonca.testemaven.dao.EstrelaDAO;
import br.mendonca.testemaven.model.entities.Estrela;
import br.mendonca.testemaven.services.dto.EstrelaDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EstrelaService {

    public void insertEstrela(String nome, Integer temperatura, Boolean estaNaViaLactea) throws ClassNotFoundException, SQLException {
        EstrelaDAO dao = new EstrelaDAO();

        Estrela estrela = new Estrela();
        estrela.setNome(nome);
        estrela.setTemperatura(temperatura);
        estrela.setEstaNaViaLactea(estaNaViaLactea);
        dao.register(estrela);
    }

    public List<EstrelaDTO> listAllEstrelas() throws ClassNotFoundException, SQLException {

        EstrelaDAO dao = new EstrelaDAO();

        List<EstrelaDTO> resp = new ArrayList<>();

        List<Estrela> lista = dao.listAllEstrelas();
        for (Estrela estrela : lista) {
            resp.add(EstrelaDTO.estrelaMapper(estrela));
        }
        return resp;
    }

    public int countEstrelas() throws ClassNotFoundException, SQLException {
        EstrelaDAO dao = new EstrelaDAO();
        return dao.countEstrelas();
    }

    public List<EstrelaDTO> listAllEstrelasPaginada(int pageNumber, int pageSize) throws ClassNotFoundException, SQLException {
        EstrelaDAO dao = new EstrelaDAO();
        List<EstrelaDTO> resp = new ArrayList<>();
        List<Estrela> lista = dao.listAllEstrelasWithPagination(pageNumber, pageSize);
        for (Estrela estrela : lista) {
            resp.add(EstrelaDTO.estrelaMapper(estrela));
        }
        return resp;
    }

    public void desativarEstrela(String uuid) throws ClassNotFoundException, SQLException {
        EstrelaDAO dao = new EstrelaDAO();
        dao.desativarEstrela(uuid);
    }

    public List<EstrelaDTO> listAllEstrelasDesativadasPaginada(int pageNumber, int pageSize) throws ClassNotFoundException, SQLException {
        EstrelaDAO dao = new EstrelaDAO();
        List<EstrelaDTO> resp = new ArrayList<>();
        List<Estrela> lista = dao.listAllEstrelasDesativadasWithPagination(pageNumber, pageSize);
        for (Estrela estrela : lista) {
            resp.add(EstrelaDTO.estrelaMapper(estrela));
        }
        return resp;
    }

    public int countEstrelasDesativadas() throws ClassNotFoundException, SQLException {
        EstrelaDAO dao = new EstrelaDAO();
        return dao.countEstrelasDesativadas();
    }



}
