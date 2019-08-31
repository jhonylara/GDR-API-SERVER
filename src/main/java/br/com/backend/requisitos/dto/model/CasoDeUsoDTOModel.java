package br.com.backend.requisitos.dto.model;

import br.com.backend.requisitos.dto.interfaces.CasoDeUsoDTOInterface;

public class CasoDeUsoDTOModel extends CasoDeUsoDTOInterface {

	private Integer id;

	public CasoDeUsoDTOModel() {
		super();
	}

	public CasoDeUsoDTOModel(
		Integer id,
		String nome,
		String escopo,
		String nivel,
		String preCondicao,
		String posCondicao,
		String cenarioPrincipal,
		String extensao,
		String atorPrincipal,
		String status
	) {
		super(nome, escopo, nivel, preCondicao, posCondicao, cenarioPrincipal, extensao, atorPrincipal, status);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
