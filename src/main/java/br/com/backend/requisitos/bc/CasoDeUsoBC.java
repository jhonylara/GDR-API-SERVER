package br.com.backend.requisitos.bc;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.demoiselle.jee.crud.AbstractBusiness;

import br.com.backend.requisitos.dao.CasoDeUsoDAO;
import br.com.backend.requisitos.dao.IntegranteDAO;
import br.com.backend.requisitos.dao.LogDAO;
import br.com.backend.requisitos.dao.ProjetoDAO;
import br.com.backend.requisitos.dto.interfaces.CasoDeUsoDTOInterface;
import br.com.backend.requisitos.dto.model.CasoDeUsoDTODetalhadoModel;
import br.com.backend.requisitos.dto.model.CasoDeUsoDTOModel;
import br.com.backend.requisitos.entity.CasoDeUso;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Log;
import br.com.backend.requisitos.entity.Projeto;
import br.com.backend.requisitos.enums.PerfilIntegranteProjeto;
import br.com.backend.requisitos.enums.Status;
import br.com.backend.requisitos.utils.Util;

public class CasoDeUsoBC extends AbstractBusiness<CasoDeUso, Integer> {

	@Inject
	private CasoDeUsoDAO casoDeUsoDAO;

	@Inject
	private ProjetoDAO projetoDAO;

	@Inject
	private IntegranteDAO integranteDAO;
	
	@Inject
	private LogDAO logDAO;

	public CasoDeUsoBC() {
	}

	@Transactional
	public void create(Integer idUsuario, Integer idProjeto, CasoDeUsoDTOInterface c) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null) {
				throw new Exception("Projeto não encontrado");
			}
			Integrante integrante = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if (integrante == null)
				throw new Exception("Usuario não encontrado");

			CasoDeUso casoDeUso = new CasoDeUso();
			
			Log log = logDAO.persist(Util.logger(idUsuario, "INCLUSÃO"));
			casoDeUso.setInclusao(log);
			casoDeUso.setNome(c.getNome());
			casoDeUso.setEscopo(c.getEscopo());
			casoDeUso.setNivel(c.getNivel());
			casoDeUso.setPreCondicao(c.getPreCondicao());
			casoDeUso.setPosCondicao(c.getPosCondicao());
			casoDeUso.setCenarioPrincipal(c.getCenarioPrincipal());
			casoDeUso.setExtensao(c.getExtensao());
			casoDeUso.setAtorPrincipal(c.getAtorPrincipal());
			casoDeUso.setProjeto(projeto);
			casoDeUso.setIntegrante(integrante);
			casoDeUso.setStatus(Status.valueString(c.getStatus()));

			List<CasoDeUso> projetoHasCasosDeUso = projeto.getCasosDeUso();
			projetoHasCasosDeUso.add(casoDeUso);
			projeto.setCasosDeUso(projetoHasCasosDeUso);

			List<CasoDeUso> integranteHasCasosDeUso = integrante.getCasosDeUso();
			integranteHasCasosDeUso.add(casoDeUso);
			integrante.setCasosDeUso(integranteHasCasosDeUso);

			casoDeUsoDAO.create(casoDeUso);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<CasoDeUsoDTOModel> listarTudo(Integer idUsuario, Integer idProjeto) {
		try {
			List<CasoDeUsoDTOModel> casoDeUsoDTO = new ArrayList<CasoDeUsoDTOModel>();
			for (CasoDeUso c : casoDeUsoDAO.list(idProjeto)) {
				casoDeUsoDTO.add(
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

			return casoDeUsoDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	public CasoDeUsoDTODetalhadoModel buscarPorEspecifico(Integer idUsuario, Integer idProjeto, Integer idCasoDeUso) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null)
				throw new Exception("Projeto não encontrado");

			CasoDeUso casoDeUso = casoDeUsoDAO.findByIdProjetoAndICasoDeUso(idProjeto, idCasoDeUso);
			if (casoDeUso == null)
				throw new Exception("Caso de uso não encontrado");

			Integrante integrante = integranteDAO.find(casoDeUso.getIntegrante().getId());
			if (integrante == null)
				throw new Exception("Usuario não encontrado");

			return new CasoDeUsoDTODetalhadoModel(
				casoDeUso.getId(),
				casoDeUso.getNome(),
				casoDeUso.getEscopo(),
				casoDeUso.getNivel(),
				casoDeUso.getPreCondicao(),
				casoDeUso.getPosCondicao(),
				casoDeUso.getCenarioPrincipal(),
				casoDeUso.getExtensao(),
				casoDeUso.getAtorPrincipal(),
				casoDeUso.getIntegrante(),
				casoDeUso.getProjeto(),
				casoDeUso.getArtefatos(),
				casoDeUso.getStatus().getValue()
			);
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void alterar(Integer idUsuario, Integer idProjeto, Integer idRequisito, CasoDeUsoDTOInterface c) throws Exception {
		try {
			Projeto projeto = projetoDAO.find(idUsuario, idProjeto);
			if (projeto == null)
				throw new Exception("Projeto não encontrado");

			CasoDeUso casoDeUso = casoDeUsoDAO.findByIdProjetoAndICasoDeUso(idProjeto, idRequisito);
			if (casoDeUso == null)
				throw new Exception("Caso de uso não encontrado");
			
			Integrante integrante = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			
			if(
				integrante.getPerfilIntegranteProjeto().equals(PerfilIntegranteProjeto.VISITANTE) ||
				integrante.getPerfilIntegranteProjeto().equals(PerfilIntegranteProjeto.DESENVOLVEDOR)
			)
				throw new Exception("Integrante não tem permissão para alterar caso de uso");

			Log log = logDAO.persist(Util.logger(idUsuario, "ALTERAÇÃO"));
			casoDeUso.setAlteracao(log);
			casoDeUso.setNome(c.getNome());
			casoDeUso.setEscopo(c.getEscopo());
			casoDeUso.setNivel(c.getNivel());
			casoDeUso.setPreCondicao(c.getPreCondicao());
			casoDeUso.setPosCondicao(c.getPosCondicao());
			casoDeUso.setCenarioPrincipal(c.getCenarioPrincipal());
			casoDeUso.setExtensao(c.getExtensao());
			casoDeUso.setAtorPrincipal(c.getAtorPrincipal());
			casoDeUso.setStatus(Status.valueString(c.getStatus()));

			casoDeUsoDAO.mergeFull(casoDeUso);
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void remover(Integer idUsuario, Integer idProjeto, Integer idRequisito) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idUsuario, idProjeto);
			if (projeto == null)
				throw new Exception("Projeto não encontrado");

			CasoDeUso casoDeUso = casoDeUsoDAO.findByIdProjetoAndICasoDeUso(idProjeto, idRequisito);
			if (casoDeUso == null)
				throw new Exception("Caso de uso não encontrado");
			
			Integrante integrante = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			
			if(
				integrante.getPerfilIntegranteProjeto().equals(PerfilIntegranteProjeto.VISITANTE) ||
				integrante.getPerfilIntegranteProjeto().equals(PerfilIntegranteProjeto.DESENVOLVEDOR)
			)
				throw new Exception("Integrante não tem permissão para excluir caso de uso");

			casoDeUsoDAO.remove(idRequisito);
		} catch (Exception e) {
			throw e;
		}		
	}
}
