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

	public List<UserDTO> searchByName(String name) throws ClassNotFoundException, SQLException {
		UserDAO dao = new UserDAO();
		List<User> users = dao.searchByName(name);

		List<UserDTO> result = new ArrayList<>();
		for (User user : users) {
			result.add(UserDTO.userMapper(user));
		}
		return result;
	}

	public List<UserDTO> filterUsers(String name, Integer idadeMin, Integer idadeMax, Boolean status) throws ClassNotFoundException, SQLException {
		UserDAO dao = new UserDAO();
		List<User> users = dao.filterUsers(name, idadeMin, idadeMax, status);

		List<UserDTO> result = new ArrayList<>();
		for (User user : users) {
			result.add(UserDTO.userMapper(user));
		}
		return result;
	}


}
