package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.User;

public class UserDTO {
	
	private String name;
	private String email;
	private int idade;
	private boolean status;
	
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

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public static UserDTO userMapper(User user) {
		UserDTO dto = new UserDTO();
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setIdade(user.getIdade());
		dto.setStatus(user.isStatus());
		
		return dto;
	}
}
