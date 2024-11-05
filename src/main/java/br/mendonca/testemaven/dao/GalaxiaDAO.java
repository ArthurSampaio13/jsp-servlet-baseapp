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
}
