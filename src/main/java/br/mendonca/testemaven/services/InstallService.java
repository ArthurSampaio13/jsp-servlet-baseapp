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
				+ "    password VARCHAR(255) NOT NULL,"
				+ "    idade INTEGER NOT NULL,"
				+ "    status BOOLEAN NOT NULL"
				+ ")");
	}

	public void createSeguidoresTable() throws ClassNotFoundException, SQLException {
		statement("CREATE TABLE followers (\n" +
				"    follower_uuid UUID,\n" +
				"    followed_uuid UUID\n" +
				");");
	}

	public void deleteSeguidoresTable() throws ClassNotFoundException, SQLException {
		statement("DROP TABLE IF EXISTS followers");
	}

	public void deleteGalaxiaTable() throws ClassNotFoundException, SQLException {
		statement("DROP TABLE IF EXISTS galaxias");
	}

	public void createGalaxiaTable() throws ClassNotFoundException, SQLException {
		statement("CREATE TABLE galaxias ("
				+ "    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
				+ "    nome VARCHAR(255) NOT NULL,"
				+ "    quantidadeDeEstrelas INTEGER NOT NULL,"
				+ "    viaLactea BOOLEAN NOT NULL,"
				+ "    isVisible BOOLEAN NOT NULL,"
				+ "    dateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
	}

	public void populate7GalaxiaTable() throws ClassNotFoundException, SQLException {
		statement("INSERT INTO galaxias (nome, quantidadeDeEstrelas, viaLactea,isVisible) VALUES ('Via Láctea', 250000000, true,true)");
		statement("INSERT INTO galaxias (nome, quantidadeDeEstrelas, viaLactea,isVisible) VALUES ('Andrômeda', 10000000, false,true)");
		statement("INSERT INTO galaxias (nome, quantidadeDeEstrelas, viaLactea,isVisible) VALUES ('Sombrero', 80000000, false,true)");
		statement("INSERT INTO galaxias (nome, quantidadeDeEstrelas, viaLactea,isVisible) VALUES ('Triângulo', 4000000, false,true)");
		statement("INSERT INTO galaxias (nome, quantidadeDeEstrelas, viaLactea,isVisible) VALUES ('Girassol', 3000000, false,true)");
		statement("INSERT INTO galaxias (nome, quantidadeDeEstrelas, viaLactea,isVisible) VALUES ('Olho Negro', 2000000, false,true)");
		statement("INSERT INTO galaxias (nome, quantidadeDeEstrelas, viaLactea,isVisible) VALUES ('Bode', 100000, false,true)");
	}

	public void deleteEstrelaTable() throws ClassNotFoundException, SQLException {
		statement("DROP TABLE IF EXISTS estrelas");
	}

	public void createEstrelaTable() throws ClassNotFoundException, SQLException {
		statement("CREATE TABLE estrelas ("
				+ "    uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
				+ "    nome VARCHAR(255) NOT NULL,"
				+ "    temperatura INTEGER NOT NULL,"
				+ "    esta_na_via_lactea BOOLEAN NOT NULL,"
				+ "    estaAtivo BOOLEAN NOT NULL DEFAULT true,"
				+ "    dateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
	}

	public void populate7EstrelaTable() throws ClassNotFoundException, SQLException {
		statement("INSERT INTO estrelas (nome, temperatura, esta_na_via_lactea, estaAtivo) VALUES ('Sol', 5778, true, true)");
		statement("INSERT INTO estrelas (nome, temperatura, esta_na_via_lactea, estaAtivo) VALUES ('Alpha Centauri', 5790, true, true)");
		statement("INSERT INTO estrelas (nome, temperatura, esta_na_via_lactea, estaAtivo) VALUES ('Sirius', 9940, true, true)");
		statement("INSERT INTO estrelas (nome, temperatura, esta_na_via_lactea, estaAtivo) VALUES ('Vega', 9602, true, true)");
		statement("INSERT INTO estrelas (nome, temperatura, esta_na_via_lactea, estaAtivo) VALUES ('Betelgeuse', 3600, true, true)");
		statement("INSERT INTO estrelas (nome, temperatura, esta_na_via_lactea, estaAtivo) VALUES ('Rigel', 12100, true, true)");
		statement("INSERT INTO estrelas (nome, temperatura, esta_na_via_lactea, estaAtivo) VALUES ('Proxima Centauri', 3042, true, true)");
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

		statement("ALTER TABLE planetas ADD COLUMN ativo BOOLEAN DEFAULT true;");
		statement("ALTER TABLE planetas ADD COLUMN dateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP;");
	}

	public void populate7PlanetaTable() throws ClassNotFoundException, SQLException {
		statement("INSERT INTO planetas (nome, densidade, possuiAgua) VALUES ('Terra', 5514, true)");
		statement("INSERT INTO planetas (nome, densidade, possuiAgua) VALUES ('Marte', 3933, false)");
		statement("INSERT INTO planetas (nome, densidade, possuiAgua) VALUES ('Júpiter', 1326, false)");
		statement("INSERT INTO planetas (nome, densidade, possuiAgua) VALUES ('Saturno', 687, false)");
		statement("INSERT INTO planetas (nome, densidade, possuiAgua) VALUES ('Vênus', 5243, false)");
		statement("INSERT INTO planetas (nome, densidade, possuiAgua) VALUES ('Netuno', 1638, false)");
		statement("INSERT INTO planetas (nome, densidade, possuiAgua) VALUES ('Mercúrio', 5427, false)");
	}

	public void createTableCurtidas() throws ClassNotFoundException, SQLException {
		statement("CREATE TABLE curtidas ("
				+ "		id UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
				+ " 	code_post VARCHAR(255) NOT NULL,"
				+ " 	quantidade_curtidas INTEGER NOT NULL)"
		);
	}

	public void deleteTableCurtidas() throws ClassNotFoundException, SQLException {
		statement("DROP TABLE IF EXISTS curtidas");
	}
}
