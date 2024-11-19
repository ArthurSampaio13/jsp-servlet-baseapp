package br.mendonca.testemaven.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.mendonca.testemaven.model.entities.User;

public class UserDAO {

	public void register(User user) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);

		PreparedStatement ps = conn.prepareStatement("INSERT INTO users (name, email, password) values (?,?,?)");
		ps.setString(1, user.getName());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getPassword());
		ps.execute();
		ps.close();
	}

	public List<User> listAllUser() throws ClassNotFoundException, SQLException {
		ArrayList<User> lista = new ArrayList<User>();

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
		System.out.println(ps); // Exibe no console do Docker a query já montada.

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

	// TODO: Não testado
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

	// Método para seguir um usuário
	public void follow(String followerUuid, String followedUuid) throws ClassNotFoundException, SQLException {
		// Converte as strings para UUID
		UUID followerUuidConverted = UUID.fromString(followerUuid);
		UUID followedUuidConverted = UUID.fromString(followedUuid);

		// Estabelece a conexão e prepara a instrução SQL
		Connection conn = ConnectionPostgres.getConexao();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO followers (follower_uuid, followed_uuid) VALUES (?, ?)");
		ps.setObject(1, followerUuidConverted);  // Usando setObject para passar UUID
		ps.setObject(2, followedUuidConverted);  // Usando setObject para passar UUID
		ps.execute();
		ps.close();
	}

	// Método para deixar de seguir um usuário
	public void unfollow(String followerUuid, String followedUuid) throws ClassNotFoundException, SQLException {
		// Converte as strings para UUID
		UUID followerUuidConverted = UUID.fromString(followerUuid);
		UUID followedUuidConverted = UUID.fromString(followedUuid);

		// Estabelece a conexão e prepara a instrução SQL
		Connection conn = ConnectionPostgres.getConexao();
		PreparedStatement ps = conn.prepareStatement("DELETE FROM followers WHERE follower_uuid = ? AND followed_uuid = ?");
		ps.setObject(1, followerUuidConverted);  // Usando setObject para passar UUID
		ps.setObject(2, followedUuidConverted);  // Usando setObject para passar UUID
		ps.execute();
		ps.close();
	}

	// Método para listar usuários seguidos
	public List<User> listFollowing(String followerUuid) throws ClassNotFoundException, SQLException {
		List<User> following = new ArrayList<>();

		// Converte a string para UUID
		UUID followerUuidConverted = UUID.fromString(followerUuid);

		// Estabelece a conexão e prepara a instrução SQL
		Connection conn = ConnectionPostgres.getConexao();
		PreparedStatement ps = conn.prepareStatement(
				"SELECT u.* FROM users u JOIN followers f ON u.uuid = f.followed_uuid WHERE f.follower_uuid = ?"
		);
		ps.setObject(1, followerUuidConverted);  // Usando setObject para passar UUID
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			User user = new User();
			user.setUuid(rs.getString("uuid"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			following.add(user);
		}

		rs.close();
		ps.close();
		return following;
	}
}
