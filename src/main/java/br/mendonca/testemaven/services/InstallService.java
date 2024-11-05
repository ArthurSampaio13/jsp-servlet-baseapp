package br.mendonca.testemaven.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.mendonca.testemaven.dao.ConnectionPostgres;

public class InstallService {

	private void statement(String sql) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);

		Statement st = conn.createStatement();
		st.executeUpdate(sql);
		st.close();
	}

	public void testConnection() throws ClassNotFoundException, SQLException {
		ConnectionPostgres.getConexao();
	}

	public void deleteUserTable() throws ClassNotFoundException, SQLException {
		statement("DROP TABLE IF EXISTS users");
	}

	public void createUserTable() throws ClassNotFoundException, SQLException {
		statement("CREATE TABLE users ("
				+ "    uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
				+ "    name VARCHAR(255) NOT NULL,"
				+ "    email VARCHAR(255) NOT NULL,"
				+ "    password VARCHAR(255) NOT NULL)");
	}

	public void deleteGalaxiaTable() throws ClassNotFoundException, SQLException {
		statement("DROP TABLE IF EXISTS galaxias");
	}

	public void createGalaxiaTable() throws ClassNotFoundException, SQLException {
		statement("CREATE TABLE galaxias ("
				+ "    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
				+ "    nome VARCHAR(255) NOT NULL,"
				+ "    quantidadeDeEstrelas INTEGER NOT NULL,"
				+ "    viaLactea BOOLEAN NOT NULL)");
	}

	public void deleteEstrelaTable() throws ClassNotFoundException, SQLException {
		statement("DROP TABLE IF EXISTS estrelas");
	}

	public void createEstrelaTable() throws ClassNotFoundException, SQLException {
		statement("CREATE TABLE estrelas ("
					+ "    uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
					+ "    nome VARCHAR(255) NOT NULL,"
					+ "    temperatura INTEGER NOT NULL,"
					+ "    esta_na_via_lactea BOOLEAN NOT NULL)");
	}

	public void deletePlanetasTable() throws ClassNotFoundException, SQLException {
		statement("DROP TABLE IF EXISTS planetas");
	}

	public void createPlanetasTable() throws ClassNotFoundException, SQLException {
		statement("CREATE TABLE planetas ("
				+ "    uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
				+ "    nome VARCHAR(255) NOT NULL,"
				+ "    densidade INT NOT NULL,"
				+ "    possuiAgua BOOLEAN NOT NULL)");
	}
}
