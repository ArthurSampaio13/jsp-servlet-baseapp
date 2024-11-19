package br.mendonca.testemaven.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurtidaDAO {

    public void curtirPost(String postId) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String selectQuery = "SELECT quantidade_curtidas FROM curtidas WHERE code_post = ?";
        String updateQuery = "UPDATE curtidas SET quantidade_curtidas = quantidade_curtidas + 1 WHERE code_post = ?";
        String insertQuery = "INSERT INTO curtidas (code_post, quantidade_curtidas) VALUES (?, 1)";

        try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
            selectStmt.setString(1, postId);

            ResultSet resultSet = selectStmt.executeQuery();

            if (resultSet.next()) {
                try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                    updateStmt.setString(1, postId);
                    updateStmt.executeUpdate();
                }
            } else {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                    insertStmt.setString(1, postId);
                    insertStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public int getCurtidas(String postId) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        String query = "SELECT quantidade_curtidas FROM curtidas WHERE code_post = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, postId);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("quantidade_curtidas");
            } else {
                // Post não encontrado, retornando 0
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
