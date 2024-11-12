package br.mendonca.testemaven.dao;

import br.mendonca.testemaven.model.entities.Planeta;
import br.mendonca.testemaven.model.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlanetaDAO {

    public void adicionar(Planeta planeta) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("INSERT INTO planetas (nome, densidade, possuiAgua) values (?,?,?)");
        ps.setString(1, planeta.getNome());
        ps.setInt(2, planeta.getDensidade());
        ps.setBoolean(3, planeta.isPossuiAgua());
        ps.execute();
        ps.close();
    }

    public List<Planeta> listAllPlanetas() throws ClassNotFoundException, SQLException {
        ArrayList<Planeta> lista = new ArrayList<Planeta>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM planetas");

        while (rs.next()) {
            Planeta planeta = new Planeta();
            planeta.setUuid(UUID.fromString(rs.getString("uuid")));
            planeta.setNome(rs.getString("nome"));
            planeta.setDensidade(rs.getInt("densidade"));
            planeta.setPossuiAgua(rs.getBoolean("possuiAgua"));

            lista.add(planeta);
        }

        rs.close();

        return lista;
    }


    // TODO: N�o testado
    public List<User> search(String name) throws ClassNotFoundException, SQLException {
        ArrayList<User> lista = new ArrayList<User>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        // Apesar de qualquer SQL funcionar com Statement, a abordagem de usar Prepared Statement evita SQL Injection.
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE ? LIKE LOWER(?) || LOWER(name) || '%'");
        ps.setString(1, name);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setUuid(rs.getString("uuid"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));

            lista.add(user);
        }

        rs.close();

        return lista;
    }

    public List<Planeta> listPlanetasWithPagination(int pageNumber, int pageSize) throws ClassNotFoundException, SQLException {
        ArrayList<Planeta> lista = new ArrayList<>();
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        int offset = (pageNumber - 1) * pageSize;

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM planetas WHERE ativo = true LIMIT ? OFFSET ?");
        ps.setInt(1, pageSize);
        ps.setInt(2, offset);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Planeta planeta = new Planeta();
            planeta.setUuid(UUID.fromString(rs.getString("uuid")));
            planeta.setNome(rs.getString("nome"));
            planeta.setDensidade(rs.getInt("densidade"));
            planeta.setPossuiAgua(rs.getBoolean("possuiAgua"));
            planeta.setAtivo(rs.getBoolean("ativo"));

            lista.add(planeta);
        }

        rs.close();
        ps.close();

        return lista;
    }

    public int countPlanetas() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT COUNT(*) AS total FROM planetas WHERE ativo = true");
        rs.next();
        int total = rs.getInt("total");

        rs.close();
        st.close();

        return total;
    }

    public void deletarLogico(UUID planetaId) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        PreparedStatement ps = conn.prepareStatement("UPDATE planetas SET ativo = false WHERE uuid = ?");
        ps.setObject(1, planetaId);
        ps.executeUpdate();
        ps.close();
    }

}
