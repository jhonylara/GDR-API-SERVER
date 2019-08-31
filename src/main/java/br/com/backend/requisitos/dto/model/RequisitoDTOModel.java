package br.com.backend.requisitos.dto.model;

import br.com.backend.requisitos.dto.interfaces.RequisitoDTOInteface;

public class RequisitoDTOModel extends RequisitoDTOInteface {
	private Integer id;

	public RequisitoDTOModel() {
	}

	public RequisitoDTOModel(
		Integer id,
		String idRequisito,
		String nome,
		String descricao,
		String importancia,
		String fonte,
		String categoria,
		String status
	) {
		super(idRequisito, nome, descricao, importancia, fonte, categoria, status);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
