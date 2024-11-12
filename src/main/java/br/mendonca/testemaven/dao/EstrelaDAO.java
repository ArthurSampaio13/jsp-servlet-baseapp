package br.mendonca.testemaven.dao;

import br.mendonca.testemaven.model.entities.Estrela;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EstrelaDAO {

	public void register(Estrela Estrela) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);
		
		PreparedStatement ps = conn.prepareStatement("INSERT INTO estrelas (nome, temperatura, esta_na_via_lactea) values (?,?,?)");
		ps.setString(1, Estrela.getNome());
		ps.setInt(2, Estrela.getTemperatura());
		ps.setBoolean(3, Estrela.getEstaNaViaLactea());
		ps.execute();
		ps.close();
	}
	
	public List<Estrela> listAllEstrelas() throws ClassNotFoundException, SQLException {
		ArrayList<Estrela> lista = new ArrayList<>();

		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM estrelas");
		
		while (rs.next()) {
			Estrela estrela = new Estrela();
			estrela.setUuid(rs.getString("uuid"));
			estrela.setNome(rs.getString("nome"));
			estrela.setTemperatura(rs.getInt("temperatura"));
			estrela.setEstaNaViaLactea(rs.getBoolean("esta_na_via_lactea"));
			lista.add(estrela);
		}
		
		rs.close();
		
		return lista;
	}

	public Estrela searchEstrelaById(String uuid) throws ClassNotFoundException, SQLException {
		Estrela estrela = null;

		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM estrelas WHERE uuid = ?");
		ps.setObject(1, UUID.fromString(uuid));
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			estrela = new Estrela();
			estrela.setUuid(rs.getString("uuid"));
			estrela.setNome(rs.getString("nome"));
			estrela.setTemperatura(rs.getInt("temperatura"));
			estrela.setEstaNaViaLactea(rs.getBoolean("esta_na_via_lactea"));
			estrela.setEstaAtivo(rs.getBoolean("estaAtivo"));
		}

		rs.close();

		return estrela;
	}

	public List<Estrela> listAllEstrelasWithPagination(int pageNumber, int pageSize) throws ClassNotFoundException, SQLException {
		ArrayList<Estrela> lista = new ArrayList<>();
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);

		int offset = (pageNumber - 1) * pageSize;

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM estrelas WHERE estaAtivo = true LIMIT ? OFFSET ?");
		ps.setInt(1, pageSize);
		ps.setInt(2, offset);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Estrela estrela = new Estrela();
			estrela.setUuid(rs.getString("uuid"));
			estrela.setNome(rs.getString("nome"));
			estrela.setTemperatura(rs.getInt("temperatura"));
			estrela.setEstaNaViaLactea(rs.getBoolean("esta_na_via_lactea"));
			estrela.setEstaAtivo(rs.getBoolean("estaAtivo"));
			lista.add(estrela);
		}

		rs.close();
		ps.close();

		return lista;
	}

	public int countEstrelas() throws SQLException, ClassNotFoundException {
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT COUNT(*) AS total FROM estrelas WHERE estaAtivo = true");
		rs.next();
		int total = rs.getInt("total");
		rs.close();
		st.close();
		return total;
	}

	public void desativarEstrela(String uuid) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);

		PreparedStatement ps = conn.prepareStatement("UPDATE estrelas SET estaAtivo = false WHERE uuid = ?");
		ps.setObject(1, UUID.fromString(uuid));
		ps.execute();
		ps.close();
	}
}