package br.com.backend.requisitos.dto.model;

import br.com.backend.requisitos.dto.interfaces.ProjetoDTOInterface;
import java.util.Calendar;

public class ProjetoDTOModel extends ProjetoDTOInterface {
	private Integer id;

	public ProjetoDTOModel() {
	}

	public ProjetoDTOModel(Integer id, String nome, Calendar dataInicio, Calendar dataFim, String status) {
		super(nome, dataInicio, dataFim, status);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
