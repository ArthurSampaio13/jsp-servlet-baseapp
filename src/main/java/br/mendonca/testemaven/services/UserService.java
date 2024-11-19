package br.mendonca.testemaven.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.mendonca.testemaven.dao.UserDAO;
import br.mendonca.testemaven.model.entities.User;
import br.mendonca.testemaven.services.dto.UserDTO;

public class UserService {
	
	public void register(String name, String email, String password) throws ClassNotFoundException, SQLException {
		UserDAO dao = new UserDAO();
		
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		
		dao.register(user);
	}
	
	public List<UserDTO> listAllUsers() throws ClassNotFoundException, SQLException {
		ArrayList<UserDTO> resp = new ArrayList<UserDTO>();
		
		UserDAO dao = new UserDAO();
		List<User> lista = dao.listAllUser();
		
		for (User user : lista) {
			resp.add(UserDTO.userMapper(user));
		}
		
		return resp;
	}

	public void followUser(String followerUuid, String followedUuid) throws ClassNotFoundException, SQLException {
		UserDAO dao = new UserDAO();
		dao.follow(followerUuid, followedUuid);
	}

	public void unfollowUser(String followerUuid, String followedUuid) throws ClassNotFoundException, SQLException {
		UserDAO dao = new UserDAO();
		dao.unfollow(followerUuid, followedUuid);
	}

	public List<UserDTO> listFollowing(String followerUuid) throws ClassNotFoundException, SQLException {
		UserDAO dao = new UserDAO();
		List<User> following = dao.listFollowing(followerUuid);
		List<UserDTO> followingDTO = new ArrayList<>();
		for (User user : following) {
			followingDTO.add(UserDTO.userMapper(user));
		}
		return followingDTO;
	}
}
