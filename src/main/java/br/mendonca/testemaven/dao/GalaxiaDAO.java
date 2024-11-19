package br.mendonca.testemaven.dao;

import br.mendonca.testemaven.model.entities.Galaxia;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GalaxiaDAO {

    public void register(Galaxia galaxia) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        // Removido o parâmetro id, pois será gerado pelo banco
        PreparedStatement ps = conn.prepareStatement("INSERT INTO galaxias (nome, quantidadeDeEstrelas, viaLactea,isVisible) VALUES (?, ?, ?, ?)");
        ps.setString(1, galaxia.getNome());
        ps.setInt(2, galaxia.getQuantidadeDeEstrelas());
        ps.setBoolean(3, galaxia.isViaLactea());
        ps.setBoolean(4,true);

        ps.executeUpdate(); // Altere para executeUpdate()
        ps.close();
    }

    public List<Galaxia> listAllGalaxias() throws ClassNotFoundException, SQLException {
        List<Galaxia> lista = new ArrayList<>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM galaxias");

        while (rs.next()) {
            UUID id = (UUID) rs.getObject("id"); // Alterado para usar getObject
            String nome = rs.getString("nome");
            int quantidadeDeEstrelas = rs.getInt("quantidadeDeEstrelas");
            boolean viaLactea = rs.getBoolean("viaLactea");
            LocalDateTime dateCreated = rs.getTimestamp("dateCreated").toLocalDateTime();

            Galaxia galaxia = new Galaxia(nome, quantidadeDeEstrelas, viaLactea, true, dateCreated);
            galaxia.setId(id);
            lista.add(galaxia);
        }

        rs.close();
        return lista;
    }

    public List<Galaxia> listGalaxiasWithPagination(int pageNumber, int pageSize) throws ClassNotFoundException, SQLException {
        ArrayList<Galaxia> lista = new ArrayList<>();
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        int offset = (pageNumber - 1) * pageSize;

        // Alterada a consulta para refletir a tabela galaxias
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM galaxias WHERE isVisible = true LIMIT ? OFFSET ?");
        ps.setInt(1, pageSize);
        ps.setInt(2, offset);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Galaxia galaxia = new Galaxia();
            galaxia.setId((UUID) rs.getObject("id"));
            galaxia.setNome(rs.getString("nome"));
            galaxia.setQuantidadeDeEstrelas(rs.getInt("quantidadeDeEstrelas"));
            galaxia.setViaLactea(rs.getBoolean("viaLactea"));
            galaxia.setVisible(rs.getBoolean("isVisible"));
            galaxia.setDateCreated(rs.getTimestamp("dateCreated").toLocalDateTime());
            lista.add(galaxia);
        }

        rs.close();
        ps.close();

        return lista;
    }

    public int countGalaxias() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT COUNT(*) AS total FROM galaxias WHERE isVisible = true");
        rs.next();
        int total = rs.getInt("total");

        rs.close();
        st.close();

        return total;
    }

    public int countInvisibleGalaxias() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT COUNT(*) AS total FROM galaxias WHERE isVisible = false");
        rs.next();
        int total = rs.getInt("total");

        rs.close();
        st.close();

        return total;
    }

    public List<Galaxia> listInvisibleGalaxiasWithPagination(int pageNumber, int pageSize) throws ClassNotFoundException, SQLException {
        ArrayList<Galaxia> lista = new ArrayList<>();
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        int offset = (pageNumber - 1) * pageSize;

        // Alterada a consulta para refletir a tabela galaxias
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM galaxias WHERE isVisible = false LIMIT ? OFFSET ?");
        ps.setInt(1, pageSize);
        ps.setInt(2, offset);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Galaxia galaxia = new Galaxia();
            galaxia.setId((UUID) rs.getObject("id"));
            galaxia.setNome(rs.getString("nome"));
            galaxia.setQuantidadeDeEstrelas(rs.getInt("quantidadeDeEstrelas"));
            galaxia.setViaLactea(rs.getBoolean("viaLactea"));
            galaxia.setVisible(rs.getBoolean("isVisible"));
            galaxia.setDateCreated(rs.getTimestamp("dateCreated").toLocalDateTime());

            lista.add(galaxia);
        }

        rs.close();
        ps.close();

        return lista;
    }

    public void markAsInvisible(UUID galaxiaId) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        // Atualiza a galáxia para marcar como não visível
        PreparedStatement ps = conn.prepareStatement("UPDATE galaxias SET isVisible = false WHERE id = ?");
        ps.setObject(1, galaxiaId); // Utiliza o id da galáxia

        ps.executeUpdate();
        ps.close();
    }

    public Galaxia findById(UUID galaxiaId) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String query = "SELECT * FROM galaxias WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setObject(1, galaxiaId);

        ResultSet rs = ps.executeQuery();
        Galaxia galaxia = null;

        if (rs.next()) {
            galaxia = new Galaxia();
            galaxia.setId((UUID) rs.getObject("id"));
            galaxia.setNome(rs.getString("nome"));
            galaxia.setQuantidadeDeEstrelas(rs.getInt("quantidadeDeEstrelas"));
            galaxia.setViaLactea(rs.getBoolean("viaLactea"));
            galaxia.setVisible(rs.getBoolean("isVisible"));
            galaxia.setDateCreated(rs.getTimestamp("dateCreated").toLocalDateTime());
        }

        rs.close();
        ps.close();

        return galaxia;
    }

    public void updateGalaxia(Galaxia galaxia) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String query = "UPDATE galaxias SET nome = ?, quantidadeDeEstrelas = ?, viaLactea = ?, isVisible = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, galaxia.getNome());
        ps.setInt(2, galaxia.getQuantidadeDeEstrelas());
        ps.setBoolean(3, galaxia.isViaLactea());
        ps.setBoolean(4, galaxia.isVisible());
        ps.setObject(5, galaxia.getId());

        ps.executeUpdate();
        ps.close();
    }
}


