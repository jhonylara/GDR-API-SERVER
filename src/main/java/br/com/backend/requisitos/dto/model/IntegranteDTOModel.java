package br.com.backend.requisitos.dto.model;

import br.com.backend.requisitos.dto.interfaces.IntegranteDTOInterface;

public class IntegranteDTOModel extends IntegranteDTOInterface {
	private Integer id;
	private String nome;

	public IntegranteDTOModel() {
	}

	public IntegranteDTOModel(String perfilIntegrante, String nome, Integer id) {
		super(perfilIntegrante);
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
