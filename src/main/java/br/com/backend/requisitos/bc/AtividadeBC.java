package br.com.backend.requisitos.bc;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.demoiselle.jee.crud.AbstractBusiness;

import br.com.backend.requisitos.dao.AtividadeDAO;
import br.com.backend.requisitos.dao.IntegranteDAO;
import br.com.backend.requisitos.dao.LogDAO;
import br.com.backend.requisitos.dao.RequisitoDAO;
import br.com.backend.requisitos.dto.interfaces.AtividadeDTOInterface;
import br.com.backend.requisitos.dto.model.AtividadeDTODetalhadoModel;
import br.com.backend.requisitos.dto.model.AtividadeDTOModel;
import br.com.backend.requisitos.entity.Atividade;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Log;
import br.com.backend.requisitos.entity.Requisito;
import br.com.backend.requisitos.enums.Status;
import br.com.backend.requisitos.utils.Util;

public class AtividadeBC extends AbstractBusiness<Atividade, Integer> {

	@Inject
	private AtividadeDAO atividadeDAO;

	@Inject
	private RequisitoDAO requisitoDAO;

	@Inject
	private IntegranteDAO integranteDAO;

	@Inject
	private LogDAO logDAO;

	public AtividadeBC() {
	}

	@Transactional
	public void create(AtividadeDTOInterface a, Integer idUsuario, Integer idProjeto, Integer idRequisito)
			throws Exception {
		try {
			Requisito requisito = (Requisito) requisitoDAO.find(idRequisito);
			if (requisito == null)
				throw new Exception("Requisito não encontrado");

			Atividade atividade = new Atividade();
			atividade.setNome(a.getNome());
			atividade.setDescricao(a.getDescricao());
			atividade.setDataInicio(a.getDataInicio());
			atividade.setDataFim(a.getDataFim());
			atividade.setDataConclusao(a.getDataConclusao());
			atividade.setStatus(Status.valueString(a.getStatus()));

			Integrante criador = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if(criador == null)
				throw new Exception("Usuário não encontrado");
			
			Log log = logDAO.persist(Util.logger(criador.getId(), "INCLUSÃO"));
			atividade.setInclusao(log);

			Integrante desenvolvedor = (Integrante) integranteDAO.find(a.getIdDesenvolvedor());
			List<Integrante> desenvolvedores = new ArrayList<Integrante>();
			desenvolvedores.add(desenvolvedor);
			atividade.setDesenvolvedores(desenvolvedores);

			atividade.setRequisito(requisito);

			List<Atividade> atividades = requisito.getAtividades();
			atividades.add(atividade);
			requisito.setAtividades(atividades);

			atividadeDAO.create(atividade);
			requisitoDAO.persist(requisito);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<AtividadeDTOModel> listarPorProjeto(Integer idUsuario, Integer idProjeto) {
		try {
			List<Atividade> atividades = atividadeDAO.findByAllUsuarioAndProjeto(idUsuario, idProjeto);
			List<AtividadeDTOModel> atividadesDTO = new ArrayList<AtividadeDTOModel>();
			for (Atividade atividade : atividades) {
				List<Integrante> desenvolvedores = atividade.getDesenvolvedores();

				atividadesDTO.add(new AtividadeDTOModel(
					atividade.getId(),
					atividade.getNome(),
					atividade.getDescricao(),
					atividade.getStatus().getValue(),
					atividade.getDataInicio(),
					atividade.getDataFim(),
					atividade.getDataConclusao(),
					null,
					desenvolvedores.get(desenvolvedores.size() - 1)));
			}

			return atividadesDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	public List<AtividadeDTOModel> listarPorRequisito(Integer idUsuario, Integer idProjeto, Integer idRequisito) {
		try {
			List<AtividadeDTOModel> atividadesDTO = new ArrayList<AtividadeDTOModel>();
			for (Atividade atividade : atividadeDAO.findByAllUsuarioProjetoAndRequisito(idUsuario, idProjeto,
					idRequisito)) {
				List<Integrante> desenvolvedores = atividade.getDesenvolvedores();

				atividadesDTO.add(new AtividadeDTOModel(atividade.getId(), atividade.getNome(),
						atividade.getDescricao(), atividade.getStatus().getValue(), atividade.getDataInicio(),
						atividade.getDataFim(), atividade.getDataConclusao(), null, 
						desenvolvedores.get(desenvolvedores.size() - 1)));
			}

			return atividadesDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	public AtividadeDTODetalhadoModel buscaEspecifica(Integer idUsuario, Integer idProjeto, Integer idAtividade) throws Exception {
		Atividade atividade = atividadeDAO.findByUsuarioProjeto(idUsuario, idProjeto, idAtividade);
		if (atividade == null)
			throw new Exception("Atividade não encontrada.");
		
		List<Integrante> desenvolvedores = atividade.getDesenvolvedores();
		
		return new AtividadeDTODetalhadoModel(atividade.getId(), atividade.getNome(), atividade.getDescricao(),
				atividade.getStatus().getValue(), atividade.getDataInicio(), atividade.getDataFim(),
				atividade.getDataConclusao(), atividade.getRequisito(), null,
				desenvolvedores);
	}

	@Transactional
	public void alterar(Integer idUsuario, Integer idProjeto, Integer idRequisito, Integer idAtividade,
			AtividadeDTOInterface a) throws Exception {
		try {
			Requisito requisito = requisitoDAO.find(idRequisito);
			if (requisito == null)
				throw new Exception("Requisito não encontrado");
			
			Atividade atividade = atividadeDAO.find(idAtividade);
			if(atividade == null)
				throw new Exception("Atividade não encontrada");
			
			Integrante criador = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if(criador == null)
				throw new Exception("Usuário não encontrado");

			if(!atividade.getRequisito().getId().equals(idRequisito))
				throw new Exception("Atividade não encontrada no requisito");

			atividade.setNome(a.getNome());
			atividade.setDescricao(a.getDescricao());
			atividade.setDataInicio(a.getDataInicio());
			atividade.setDataFim(a.getDataFim());
			atividade.setDataConclusao(a.getDataConclusao());	
			atividade.setStatus(Status.valueString(a.getStatus()));
			
			Log log = logDAO.persist(Util.logger(idUsuario, "ALTERAÇÃO"));
			atividade.setAlteracao(log);

			atividadeDAO.mergeFull(atividade);
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void excluir(Integer idUsuario, Integer idProjeto, Integer idRequisito, Integer idAtividade) throws Exception {
		try {
			Requisito requisito = requisitoDAO.find(idRequisito);
			if (requisito == null)
				throw new Exception("Requisito não encontrado");
			
			Atividade atividade = atividadeDAO.find(idAtividade);
			if(atividade == null)
				throw new Exception("Atividade não encontrada");

			if(atividade.getRequisito().getId().equals(idRequisito))
				throw new Exception("Atividade não encontrada no requisito");
			
			atividadeDAO.remove(idAtividade);
		} catch (Exception e) {
			throw e;
		}
	}
}
