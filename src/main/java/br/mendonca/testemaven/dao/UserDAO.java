package br.mendonca.testemaven.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.mendonca.testemaven.model.entities.User;

public class UserDAO {

	public void register(User user) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);

		PreparedStatement ps = conn.prepareStatement("INSERT INTO users (name, email, password, idade, status) VALUES (?,?,?,?,?)");
		ps.setString(1, user.getName());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getPassword());
		ps.setInt(4, user.getIdade());
		ps.setBoolean(5, user.isStatus());
		ps.execute();
		ps.close();
	}

	public List<User> listAllUser() throws ClassNotFoundException, SQLException {
		ArrayList<User> lista = new ArrayList<>();
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);

		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM users");
		while (rs.next()) {
			User user = new User();
			user.setUuid(rs.getString("uuid"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setIdade(rs.getInt("idade"));
			user.setStatus(rs.getBoolean("status"));
			lista.add(user);
		}
		rs.close();
		return lista;
	}

	public User search(String email, String password) throws ClassNotFoundException, SQLException {
		User user = null;
		
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);
		
		// Apesar de qualquer SQL funcionar com Statement, a abordagem de usar Prepared Statement evita SQL Injection.
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
		ps.setString(1, email);
		ps.setString(2, password);
		System.out.println(ps); // Exibe no console do Docker a query j� montada.
		
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			
			user = new User();
			user.setUuid(rs.getString("uuid"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
		}
		
		rs.close();
		
		return user;
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

	public List<User> searchByName(String name) throws ClassNotFoundException, SQLException {
		List<User> users = new ArrayList<>();
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE LOWER(name) LIKE LOWER(?)");
		ps.setString(1, "%" + name + "%");

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			User user = new User();
			user.setUuid(rs.getString("uuid"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password")); // Evite expor a senha no DTO
			users.add(user);
		}

		rs.close();
		ps.close();
		return users;
	}

	public List<User> filterUsers(String name, Integer idadeMin, Integer idadeMax, Boolean status) throws ClassNotFoundException, SQLException {
		List<User> users = new ArrayList<>();
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);

		StringBuilder sql = new StringBuilder("SELECT * FROM users WHERE 1=1");
		if (name != null && !name.isEmpty()) {
			sql.append(" AND LOWER(name) LIKE LOWER(?)");
		}
		if (idadeMin != null) {
			sql.append(" AND idade >= ?");
		}
		if (idadeMax != null) {
			sql.append(" AND idade <= ?");
		}
		if (status != null) {
			sql.append(" AND status = ?");
		}

		PreparedStatement ps = conn.prepareStatement(sql.toString());
		int parameterIndex = 1;

		if (name != null && !name.isEmpty()) {
			ps.setString(parameterIndex++, "%" + name + "%");
		}
		if (idadeMin != null) {
			ps.setInt(parameterIndex++, idadeMin);
		}
		if (idadeMax != null) {
			ps.setInt(parameterIndex++, idadeMax);
		}
		if (status != null) {
			ps.setBoolean(parameterIndex++, status);
		}

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			User user = new User();
			user.setUuid(rs.getString("uuid"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setIdade(rs.getInt("idade"));
			user.setStatus(rs.getBoolean("status"));
			users.add(user);
		}

		rs.close();
		ps.close();
		return users;
	}


}
