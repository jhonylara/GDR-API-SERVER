package br.com.backend.requisitos.bc;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.demoiselle.jee.crud.AbstractBusiness;

import br.com.backend.requisitos.dao.IntegranteDAO;
import br.com.backend.requisitos.dao.LogDAO;
import br.com.backend.requisitos.dao.ProjetoDAO;
import br.com.backend.requisitos.dao.UsuarioDAO;
import br.com.backend.requisitos.dto.interfaces.IntegranteDTOInterface;
import br.com.backend.requisitos.dto.model.IntegranteDTODetalhadoModel;
import br.com.backend.requisitos.dto.model.IntegranteDTOModel;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Log;
import br.com.backend.requisitos.entity.Projeto;
import br.com.backend.requisitos.entity.Usuario;
import br.com.backend.requisitos.enums.PerfilIntegranteProjeto;
import br.com.backend.requisitos.utils.Util;

public class IntegranteBC extends AbstractBusiness<Integrante, Integer> {
	
	@Inject
	private IntegranteDAO integranteDAO;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private ProjetoDAO projetoDAO;
	
	@Inject
	private LogDAO logDAO;

	public IntegranteBC() {
	}

	@Transactional
	public void create(Integer idUsuario, Integer idProjeto, IntegranteDTOInterface i) throws Exception {
		try {
			Usuario usuario = (Usuario) usuarioDAO.find(idUsuario);
			if (usuario == null)
				throw new Exception("Usuário não encontrado");

			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null)
				throw new Exception("Projeto não encontrado");

			for (Integrante iUser : projeto.getIntegrantes()) {
				if (iUser.getUsuario().getId().equals(usuario.getId())) {
					throw new Exception("Usuário já existente neste projeto com o perfil de "
							+ iUser.getPerfilIntegranteProjeto().getValue());
				}
			}

			Integrante integrante = new Integrante();
			
			Log log = logDAO.persist(Util.logger(usuario.getId(), "INCLUSÃO"));
			integrante.setInclusao(log);
			integrante.setPerfilIntegranteProjeto(PerfilIntegranteProjeto.valueString(i.getPerfilIntegrante()));
			integrante.setUsuario(usuario);
			integrante.setProjeto(projeto);

			List<Integrante> integrantes = new ArrayList<Integrante>();
			integrantes = projeto.getIntegrantes();
			integrantes.add(integrante);

			integranteDAO.create(integrante);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<IntegranteDTOModel> listaPorProjeto(Integer idUsuario, Integer idProjeto) throws Exception {
		try {
			Projeto projeto = projetoDAO.find(idUsuario, idProjeto);
			if (projeto == null) {
				throw new Exception("Projeto não encontrado");
			}
			List<Integrante> integrantes = integranteDAO.listaPorProjeto(idProjeto);

			List<IntegranteDTOModel> integranteDTOs = new ArrayList<IntegranteDTOModel>();
			for (Integrante integrante : integrantes) {
				integranteDTOs.add(new IntegranteDTOModel(integrante.getPerfilIntegranteProjeto().getValue(),
						integrante.getUsuario().getNome(), integrante.getId()));
			}

			return integranteDTOs;
		} catch (Exception e) {
			throw e;
		}
	}

	public IntegranteDTODetalhadoModel buscarPorIntegrante(Integer idUsuario, Integer idProjeto, Integer idIntegrante)
			throws Exception {
		try {
			Projeto projeto = projetoDAO.find(idUsuario, idProjeto);
			if (projeto == null)
				throw new Exception("Projeto não encontrado");

			Integrante integrante = integranteDAO.find(idIntegrante);
			if (integrante == null)
				throw new Exception("Integrante não encontrado");
			
			if(!integrante.getProjeto().getId().equals(projeto.getId()))
				throw new Exception("Integrante não encontrado no projeto");

			return new IntegranteDTODetalhadoModel(
				integrante.getId(),
				integrante.getUsuario().getId(),
				integrante.getPerfilIntegranteProjeto().getValue(),
				integrante.getUsuario().getNome(),
				integrante.getProjeto(),
				integrante.getRequisitos(),
				integrante.getCasosDeUso(),
				integrante.getAtividades()
			);
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void alterar(Integer idUsuario, Integer idProjeto, Integer idIntegrante, IntegranteDTOInterface i)
			throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null)
				throw new Exception("Projeto não encontrado");

			Integrante integranteUsuario = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if (integranteUsuario == null)
				throw new Exception("Integrante não encontrado");

			if (!integranteUsuario.getPerfilIntegranteProjeto().equals(PerfilIntegranteProjeto.GERENTE))
				throw new Exception("Somente o gerente do projeto poderá alterar integrantes");

			Integrante integrante = integranteDAO.find(idIntegrante);
			if (integrante == null)
				throw new Exception("Integrante não encontrado no projeto");
			
			if(!integrante.getProjeto().getId().equals(projeto.getId())) 
				throw new Exception("Integrante não encontrado no projeto");

			if (integrante.getPerfilIntegranteProjeto().equals(PerfilIntegranteProjeto.GERENTE))
				throw new Exception("Não é possivel alterar o perfil do Gerente");
			
			integrante.setPerfilIntegranteProjeto(PerfilIntegranteProjeto.valueString(i.getPerfilIntegrante()));
			
			Log log = logDAO.persist(Util.logger(idUsuario, "ALTERAÇÃO"));
			integrante.setAlteracao(log);

			integranteDAO.mergeFull(integrante);
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void excluir(Integer idUsuario, Integer idProjeto, Integer idIntegrante) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null)
				throw new Exception("Projeto não encontrado");

			Integrante integranteUsuario = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if (integranteUsuario == null)
				throw new Exception("Integrante não encontrado");

			if (!integranteUsuario.getPerfilIntegranteProjeto().equals(PerfilIntegranteProjeto.GERENTE))
				throw new Exception("Somente o gerente do projeto poderá excluir integrantes");
			
			Integrante integrante = integranteDAO.find(idIntegrante);
			if (integrante == null)
				throw new Exception("Integrante não encontrado no projeto");
			
			if(!integrante.getProjeto().getId().equals(projeto.getId())) 
				throw new Exception("Integrante não encontrado no projeto");

			if (integrante.getPerfilIntegranteProjeto().equals(PerfilIntegranteProjeto.GERENTE))
				throw new Exception("Não é possivel excluir o perfil do Gerente");

			integranteDAO.remove(integrante.getId());
		} catch (Exception e) {
			throw e;
		}
	}
}
