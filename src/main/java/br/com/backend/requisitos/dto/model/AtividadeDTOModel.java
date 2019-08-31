package br.com.backend.requisitos.dto.model;

import java.util.Calendar;

import br.com.backend.requisitos.dto.interfaces.AtividadeDTOInterface;
import br.com.backend.requisitos.entity.Integrante;

public class AtividadeDTOModel extends AtividadeDTOInterface {
	private Integer id;
	private IntegranteDTOModel criador;
	private IntegranteDTOModel desenvolvedor;

	public AtividadeDTOModel() {
	}

	public AtividadeDTOModel(
		Integer id,
		String nome,
		String descricao,
		String status,
		Calendar dataInicio,
		Calendar dataFim,
		Calendar dataConclusao,
		Integrante criador,
		Integrante desenvolvedor
	) {
		super(nome, descricao, status, dataInicio, dataFim, dataConclusao, null);
		this.id = id;
		this.criador = new IntegranteDTOModel(criador.getPerfilIntegranteProjeto().getValue(),
				criador.getUsuario().getNome(), criador.getId());

		this.desenvolvedor = desenvolvedor != null ? new IntegranteDTOModel(desenvolvedor.getPerfilIntegranteProjeto().getValue(),
				desenvolvedor.getUsuario().getNome(), desenvolvedor.getId()) : null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public IntegranteDTOModel getCriador() {
		return criador;
	}

	public void setCriador(IntegranteDTOModel criador) {
		this.criador = criador;
	}

	public IntegranteDTOModel getDesenvolvedor() {
		return desenvolvedor;
	}

	public void setDesenvolvedor(IntegranteDTOModel desenvolvedor) {
		this.desenvolvedor = desenvolvedor;
	}
}
