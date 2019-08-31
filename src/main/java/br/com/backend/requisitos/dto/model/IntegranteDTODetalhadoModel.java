package br.com.backend.requisitos.dto.model;

import java.util.ArrayList;
import java.util.List;

import br.com.backend.requisitos.entity.Atividade;
import br.com.backend.requisitos.entity.CasoDeUso;
import br.com.backend.requisitos.entity.Projeto;
import br.com.backend.requisitos.entity.Requisito;

public class IntegranteDTODetalhadoModel extends IntegranteDTOModel {
	private ProjetoDTOModel projeto;
	private List<RequisitoDTOModel> requisitos;
	private List<CasoDeUsoDTOModel> casosDeUso;
	private List<AtividadeDTOModel> atividades;

	public IntegranteDTODetalhadoModel() {
	}

	public IntegranteDTODetalhadoModel(
		Integer id,
		Integer idUsuario,
		String perfilIntegrante,
		String nome,
		Projeto projeto, 
		List<Requisito> requisitos,
		List<CasoDeUso> casosDeUso,
		List<Atividade> atividades
	) {
		super(perfilIntegrante, nome, id);
		this.projeto = new ProjetoDTOModel(
			projeto.getId(),
			projeto.getNome(),
			projeto.getDataInicio(),
			projeto.getDataFim(),
			projeto.getStatus().getValue()
		);
		this.requisitos = listarRequisitos(requisitos);
		this.casosDeUso = listarCasosDeUso(casosDeUso);
		this.atividades = listarAtividades(atividades);
	}

	public ProjetoDTOModel getProjeto() {
		return projeto;
	}

	public void setProjeto(ProjetoDTOModel projeto) {
		this.projeto = projeto;
	}

	public List<RequisitoDTOModel> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(List<RequisitoDTOModel> requisitos) {
		this.requisitos = requisitos;
	}

	public List<CasoDeUsoDTOModel> getCasosDeUso() {
		return casosDeUso;
	}

	public void setCasosDeUso(List<CasoDeUsoDTOModel> casosDeUso) {
		this.casosDeUso = casosDeUso;
	}

	public List<AtividadeDTOModel> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<AtividadeDTOModel> atividades) {
		this.atividades = atividades;
	}

	private List<RequisitoDTOModel> listarRequisitos(List<Requisito> listaRequisitos) {
		List<RequisitoDTOModel> requisitoDTOs = new ArrayList<>();
		for (Requisito r : listaRequisitos) {
			requisitoDTOs.add(
				new RequisitoDTOModel(
					r.getId(),
					r.getIdRequisito().toString(),
					r.getNome(),
					r.getDescricao(),
					r.getImportancia().getValue(),
					r.getFonte(),
					r.getCategoria().getValue(),
					r.getStatus().getValue()
				)
			);
		}
		return requisitoDTOs;
	}

	private List<CasoDeUsoDTOModel> listarCasosDeUso(List<CasoDeUso> listaCasosDeUso) {
		List<CasoDeUsoDTOModel> casosDeUsoDTO = new ArrayList<>();
		for (CasoDeUso c : listaCasosDeUso) {
			casosDeUsoDTO.add(
				new CasoDeUsoDTOModel(
					c.getId(),
					c.getNome(),
					c.getEscopo(),
					c.getNivel(),
					c.getPreCondicao(),
					c.getPosCondicao(),
					c.getCenarioPrincipal(),
					c.getExtensao(),
					c.getAtorPrincipal(),
					c.getStatus().getValue()
				)
			);
		}
		return casosDeUsoDTO;
	}

	private List<AtividadeDTOModel> listarAtividades(List<Atividade> listaAtividades) {
		List<AtividadeDTOModel> atividadeDTOs = new ArrayList<>();
		for (Atividade a : listaAtividades) {
			atividadeDTOs.add(
				new AtividadeDTOModel(
					a.getId(),
					a.getNome(),
					a.getDescricao(),
					a.getStatus().getValue(),
					a.getDataInicio(),
					a.getDataFim(),
					a.getDataFim(),
					null,
					a.getDesenvolvedores().get(a.getDesenvolvedores().size() - 1)
				)
			);
		}
		return atividadeDTOs;
	}
}
