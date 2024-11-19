package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.User;

import java.util.UUID;

public class UserDTO {
	
	private String name;
	private String email;
	private String userId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public static UserDTO userMapper(User user) {
		UserDTO dto = new UserDTO();
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setUserId(user.getUuid());
		
		return dto;
	}
}
