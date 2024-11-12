package br.mendonca.testemaven.dao;

import br.mendonca.testemaven.model.entities.Galaxia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GalaxiaDAO {

    public void register(Galaxia galaxia) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        // Removido o parâmetro id, pois será gerado pelo banco
        PreparedStatement ps = conn.prepareStatement("INSERT INTO galaxias (nome, quantidadeDeEstrelas, viaLactea) VALUES (?, ?, ?)");
        ps.setString(1, galaxia.getNome());
        ps.setInt(2, galaxia.getQuantidadeDeEstrelas());
        ps.setBoolean(3, galaxia.isViaLactea());

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

            Galaxia galaxia = new Galaxia(nome, quantidadeDeEstrelas, viaLactea);
            galaxia.setId(id);
            lista.add(galaxia);
        }

        rs.close();
        return lista;
    }

    public Galaxia searchByName(String nome) throws ClassNotFoundException, SQLException {
        Galaxia galaxia = null;

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM galaxias WHERE nome = ?");
        ps.setString(1, nome);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            UUID id = (UUID) rs.getObject("id"); // Alterado para usar getObject
            int quantidadeDeEstrelas = rs.getInt("quantidadeDeEstrelas");
            boolean viaLactea = rs.getBoolean("viaLactea");

            galaxia = new Galaxia(nome, quantidadeDeEstrelas, viaLactea);
            galaxia.setId(id);
        }

        rs.close();
        return galaxia;
    }

    public List<Galaxia> searchByViaLactea(boolean viaLactea) throws ClassNotFoundException, SQLException {
        List<Galaxia> lista = new ArrayList<>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM galaxias WHERE viaLactea = ?");
        ps.setBoolean(1, viaLactea);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            UUID id = (UUID) rs.getObject("id"); // Alterado para usar getObject
            String nome = rs.getString("nome");
            int quantidadeDeEstrelas = rs.getInt("quantidadeDeEstrelas");

            Galaxia galaxia = new Galaxia(nome, quantidadeDeEstrelas, viaLactea);
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
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM galaxias LIMIT ? OFFSET ?");
        ps.setInt(1, pageSize);
        ps.setInt(2, offset);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Galaxia galaxia = new Galaxia();
            galaxia.setId((UUID) rs.getObject("id"));
            galaxia.setNome(rs.getString("nome"));
            galaxia.setQuantidadeDeEstrelas(rs.getInt("quantidadeDeEstrelas"));
            galaxia.setViaLactea(rs.getBoolean("viaLactea"));

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
        ResultSet rs = st.executeQuery("SELECT COUNT(*) AS total FROM galaxias");
        rs.next();
        int total = rs.getInt("total");

        rs.close();
        st.close();

        return total;
    }
}


