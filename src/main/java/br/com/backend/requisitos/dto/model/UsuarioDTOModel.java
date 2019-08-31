package br.com.backend.requisitos.dto.model;

import br.com.backend.requisitos.dto.interfaces.UsuarioDTOInterface;

public class UsuarioDTOModel extends UsuarioDTOInterface {
	private Integer id;
	private String token;

	public UsuarioDTOModel() {
	}

	public UsuarioDTOModel(Integer id, String nome, String email, String token) {
		super(nome, email, null);
		this.id = id;
		this.token = token;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
