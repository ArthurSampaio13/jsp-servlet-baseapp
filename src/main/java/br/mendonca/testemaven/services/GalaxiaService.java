package br.mendonca.testemaven.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.mendonca.testemaven.dao.GalaxiaDAO;
import br.mendonca.testemaven.model.entities.Galaxia;
import br.mendonca.testemaven.services.dto.GalaxiaDTO;

public class GalaxiaService {

    public void register(String nome, int quantidadeDeEstrelas, boolean viaLactea) throws ClassNotFoundException, SQLException {
        GalaxiaDAO dao = new GalaxiaDAO();

        Galaxia galaxia = new Galaxia(nome, quantidadeDeEstrelas, viaLactea,true);
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

    // Método de paginação para listar galáxias
    public List<GalaxiaDTO> listGalaxiasWithPagination(int pageNumber, int pageSize) throws ClassNotFoundException, SQLException {
        ArrayList<GalaxiaDTO> resp = new ArrayList<>();

        GalaxiaDAO dao = new GalaxiaDAO();
        List<Galaxia> lista = dao.listGalaxiasWithPagination(pageNumber, pageSize);

        // Mapeia cada galáxia para um GalaxiaDTO
        for (Galaxia galaxia : lista) {
            resp.add(GalaxiaDTO.galaxiaMapper(galaxia));
        }

        return resp;
    }

    // Método para contar o número total de galáxias
    public int countGalaxias() throws ClassNotFoundException, SQLException {
        GalaxiaDAO dao = new GalaxiaDAO();
        return dao.countGalaxias();
    }

    public void markGalaxiaAsInvisible(UUID galaxiaId) throws ClassNotFoundException, SQLException {
        GalaxiaDAO dao = new GalaxiaDAO();
        dao.markAsInvisible(galaxiaId);
    }

    public GalaxiaDTO findGalaxiaById(UUID galaxiaId) throws ClassNotFoundException, SQLException {
        GalaxiaDAO dao = new GalaxiaDAO();
        Galaxia galaxia = dao.findById(galaxiaId);

        if (galaxia != null) {
            return GalaxiaDTO.galaxiaMapper(galaxia);
        }

        return null; // Retorna null caso a galáxia não seja encontrada
    }

    public void updateGalaxia(GalaxiaDTO galaxiaDTO) throws ClassNotFoundException, SQLException {
        GalaxiaDAO dao = new GalaxiaDAO();

        // Recupera a galáxia existente pelo ID
        Galaxia galaxia = dao.findById(galaxiaDTO.getId());
        if (galaxia != null) {
            // Atualiza os dados da galáxia
            galaxia.setNome(galaxiaDTO.getNome());
            galaxia.setQuantidadeDeEstrelas(galaxiaDTO.getQuantidadeDeEstrelas());
            galaxia.setViaLactea(galaxiaDTO.isViaLactea());
            galaxia.setVisible(galaxiaDTO.isVisible());

            // Chama o método DAO para atualizar a galáxia
            dao.updateGalaxia(galaxia);
        } else {
            throw new SQLException("Galáxia não encontrada com o ID fornecido.");
        }
    }

}
