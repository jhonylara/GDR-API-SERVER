package br.com.backend.requisitos.dto.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.backend.requisitos.entity.Artefato;
import br.com.backend.requisitos.entity.Atividade;
import br.com.backend.requisitos.entity.CasoDeUso;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Requisito;

public class ProjetoDTODetalhadoModel extends ProjetoDTOModel {
	private String perfilIntegranteProjeto;
	
	private List<IntegranteDTOModel> integrantes;

	private List<RequisitoDTOModel> requisitos;
	
	private List<CasoDeUsoDTOModel> casosDeUso;
	
	private List<AtividadeDTOModel> atividades;
	
	private List<ArtefatoDTOModel> artefatos;

	public ProjetoDTODetalhadoModel() {
	}

	public ProjetoDTODetalhadoModel(
		Integer id,
		String nome,
		Calendar dataInicio,
		Calendar dataFim,
		Integrante integranteProjeto, 
		List<Requisito> requisitos,
		List<Integrante> integrantes,
		List<CasoDeUso> casosDeUso,
		List<Artefato> artefatos,
		String status
	) {
		super(id, nome, dataInicio, dataFim, status);
		this.perfilIntegranteProjeto = setIntegrante(integranteProjeto);
		this.requisitos = listarRequisitos(requisitos);
		this.atividades = listarAtividades(requisitos);
		this.integrantes = listarIntegrantes(integrantes);
		this.casosDeUso = listarCasosDeUso(casosDeUso);
		this.artefatos = listarArtefatos(artefatos);
	}

	public List<IntegranteDTOModel> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(List<IntegranteDTOModel> integrantes) {
		this.integrantes = integrantes;
	}

	public List<RequisitoDTOModel> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(List<RequisitoDTOModel> requisitos) {
		this.requisitos = requisitos;
	}

	public List<AtividadeDTOModel> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<AtividadeDTOModel> atividades) {
		this.atividades = atividades;
	}

	public String getPerfilIntegranteProjeto() {
		return perfilIntegranteProjeto;
	}

	public void setPerfilIntegranteProjeto(String perfilIntegranteProjeto) {
		this.perfilIntegranteProjeto = perfilIntegranteProjeto;
	}
	public List<CasoDeUsoDTOModel> getCasosDeUso() {
		return casosDeUso;
	}

	public void setCasosDeUso(List<CasoDeUsoDTOModel> casosDeUso) {
		this.casosDeUso = casosDeUso;
	}

	public List<ArtefatoDTOModel> getArtefatos() {
		return artefatos;
	}

	public void setArtefatos(List<ArtefatoDTOModel> artefatos) {
		this.artefatos = artefatos;
	}

	private String setIntegrante(Integrante i) {
		return i.getPerfilIntegranteProjeto().getValue();
	}

	private List<RequisitoDTOModel> listarRequisitos(List<Requisito> listaRequisitos) {
		if (listaRequisitos.isEmpty()) {
			return null;
		}
		List<RequisitoDTOModel> requisitosDTO = new ArrayList<>();
		for (Requisito r : listaRequisitos) {
			requisitosDTO.add(
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

		return requisitosDTO;
	}

	private List<AtividadeDTOModel> listarAtividades(List<Requisito> requisitos) {
		if (requisitos.isEmpty())
			return null;

		List<AtividadeDTOModel> atividadeDTO = new ArrayList<AtividadeDTOModel>();
		for(Requisito r : requisitos) {
			for(Atividade a : r.getAtividades()) {
				atividadeDTO.add(
					new AtividadeDTOModel(
						a.getId(),
						a.getNome(),
						a.getDescricao(),
						a.getStatus().getValue(),
						a.getDataInicio(),
						a.getDataFim(),
						a.getDataConclusao(),
						null,
						a.getDesenvolvedores().get(a.getDesenvolvedores().size() - 1)
					)
				);
			}
		}

		return atividadeDTO;
	}

	private List<IntegranteDTOModel> listarIntegrantes(List<Integrante> listaIngrantes) {
		if (listaIngrantes.isEmpty()) {
			return null;
		}

		List<IntegranteDTOModel> integrantesDTOModel = new ArrayList<>();
		for (Integrante i : listaIngrantes) {
			integrantesDTOModel.add(
				new IntegranteDTOModel(
					i.getPerfilIntegranteProjeto().getValue(),
					i.getUsuario().getNome(),
					i.getId()
				)
			);
		}

		return integrantesDTOModel;
	}
	
	private List<CasoDeUsoDTOModel> listarCasosDeUso(List<CasoDeUso> listaCasosDeUso) {
		if(listaCasosDeUso.isEmpty()) {
			return null;
		}
		
		List<CasoDeUsoDTOModel> casosDeUsoDTOModel = new ArrayList<>();
		for (CasoDeUso casoDeUso : listaCasosDeUso) {
			casosDeUsoDTOModel.add(
				new CasoDeUsoDTOModel(
					casoDeUso.getId(),
					casoDeUso.getNome(),
					casoDeUso.getEscopo(),
					casoDeUso.getNivel(),
					casoDeUso.getPreCondicao(),
					casoDeUso.getPosCondicao(),
					casoDeUso.getCenarioPrincipal(),
					casoDeUso.getExtensao(),
					casoDeUso.getAtorPrincipal(),
					casoDeUso.getStatus().getValue()
				)
			);
		}
		
		return casosDeUsoDTOModel;
	}
	
	private List<ArtefatoDTOModel> listarArtefatos(List<Artefato> listaArtefatos) {
		if(listaArtefatos.isEmpty()) {
			return null;
		}
		
		List<ArtefatoDTOModel> artefatosDTOModel = new ArrayList<>();
		for (Artefato artefato : listaArtefatos) {
			artefatosDTOModel.add(
				new ArtefatoDTOModel(
					artefato.getNome(),
					artefato.getDescricao(),
					artefato.getRequisito() != null ? artefato.getRequisito().getId().toString() : null,
					artefato.getCasoDeUso() != null ? artefato.getCasoDeUso().getId().toString() : null,
					artefato.getCaminhoDocumento(),
					artefato.getId()
				)
			);
		}
		
		return artefatosDTOModel;
	}
}
