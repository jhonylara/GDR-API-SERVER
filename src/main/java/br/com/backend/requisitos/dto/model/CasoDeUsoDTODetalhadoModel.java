package br.com.backend.requisitos.dto.model;

import java.util.ArrayList;
import java.util.List;

import br.com.backend.requisitos.entity.Artefato;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Projeto;

public class CasoDeUsoDTODetalhadoModel extends CasoDeUsoDTOModel {

	private IntegranteDTOModel integrante;
	
	
	private ProjetoDTOModel projeto;

	private List<ArtefatoDTOModel> artefatos;

	public CasoDeUsoDTODetalhadoModel() {
		super();
	}

	public CasoDeUsoDTODetalhadoModel(
		Integer id,
		String nome,
		String escopo,
		String nivel,
		String preCondicao,
		String posCondicao,
		String cenarioPrincipal,
		String extensao,
		String atorPrincipal,
		Integrante integrante,
		Projeto projeto,
		List<Artefato> artefatos,
		String status
	) {
		super(id, nome, escopo, nivel, preCondicao, posCondicao, cenarioPrincipal, extensao, atorPrincipal, status);
		
		this.integrante = new IntegranteDTOModel(
			integrante.getPerfilIntegranteProjeto().getValue(),
			integrante.getUsuario().getNome(),
			integrante.getId()
		);
		
		this.projeto = new ProjetoDTOModel(
			projeto.getId(),
			projeto.getNome(),
			projeto.getDataInicio(),
			projeto.getDataFim(),
			projeto.getStatus().getValue()
		);
		
		this.artefatos = this.listArtefatos(artefatos);
	}

	public IntegranteDTOModel getIntegrante() {
		return integrante;
	}

	public void setIntegrante(IntegranteDTOModel integrante) {
		this.integrante = integrante;
	}

	public ProjetoDTOModel getProjeto() {
		return projeto;
	}

	public void setProjeto(ProjetoDTOModel projeto) {
		this.projeto = projeto;
	}

	public List<ArtefatoDTOModel> getArtefatos() {
		return artefatos;
	}

	public void setArtefatos(List<ArtefatoDTOModel> artefatos) {
		this.artefatos = artefatos;
	}
	
	private List<ArtefatoDTOModel> listArtefatos(List<Artefato> artefatos) {
		List<ArtefatoDTOModel> artefatosModel = new ArrayList<>();
		for (Artefato artefato : artefatos) {
			Integer idRequisitoArtefato = null;
			if(artefato.getRequisito() != null)
				idRequisitoArtefato = artefato.getRequisito().getId();
						
			Integer idCasoDeUsoArtefato = null;
			if(artefato.getCasoDeUso() != null)
				idCasoDeUsoArtefato = artefato.getCasoDeUso().getId();


			artefatosModel.add(
				new ArtefatoDTOModel(
					artefato.getNome(),
					artefato.getDescricao(),
					idRequisitoArtefato.toString(),
					idCasoDeUsoArtefato.toString(),
					artefato.getCaminhoDocumento(),
					artefato.getId()
				)
			);
		}
		
		return artefatosModel;
	}
}
