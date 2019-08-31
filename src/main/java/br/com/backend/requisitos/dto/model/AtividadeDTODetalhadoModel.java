package br.com.backend.requisitos.dto.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Requisito;

public class AtividadeDTODetalhadoModel extends AtividadeDTOModel {
	private RequisitoDTOModel requisito;
	private List<IntegranteDTOModel> desenvolvedores;

	public AtividadeDTODetalhadoModel() {
	}

	public AtividadeDTODetalhadoModel(Integer id, String nome, String descricao, String status, Calendar dataInicio,
			Calendar dataFim, Calendar dataConclusao, Requisito requisito, Integrante criador,
			List<Integrante> desenvolvedores) {
		super(id, nome, descricao, status, dataInicio, dataFim, dataConclusao, criador, null);
		this.requisito = new RequisitoDTOModel(
			requisito.getId(),
			requisito.getIdRequisito().toString(),
			requisito.getNome(),
			requisito.getDescricao(),
			requisito.getImportancia().getValue(),
			requisito.getFonte(),
			requisito.getCategoria().getValue(),
			requisito.getStatus().getValue()
		);

		this.desenvolvedores = listarDesenvolvedores(desenvolvedores);
	}

	public RequisitoDTOModel getRequisito() {
		return requisito;
	}

	public void setRequisito(RequisitoDTOModel requisito) {
		this.requisito = requisito;
	}

	public List<IntegranteDTOModel> getDesenvolvedores() {
		return desenvolvedores;
	}

	public void setDesenvolvedores(List<IntegranteDTOModel> desenvolvedores) {
		this.desenvolvedores = desenvolvedores;
	}

	private List<IntegranteDTOModel> listarDesenvolvedores(List<Integrante> desenvolvedores) {
		try {
			List<IntegranteDTOModel> desenvolvedoresDTO = new ArrayList<IntegranteDTOModel>();
			for (Integrante desenvolvedor : desenvolvedores) {
				desenvolvedoresDTO.add(new IntegranteDTOModel(desenvolvedor.getPerfilIntegranteProjeto().getValue(),
						desenvolvedor.getUsuario().getNome(), desenvolvedor.getId()));
			}

			return desenvolvedoresDTO;
		} catch (Exception e) {
			throw e;
		}
	}
}
