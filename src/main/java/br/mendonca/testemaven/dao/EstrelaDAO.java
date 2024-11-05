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
		}

		rs.close();

		return estrela;
	}

}
