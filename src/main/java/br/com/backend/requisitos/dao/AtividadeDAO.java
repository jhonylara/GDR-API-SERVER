package br.com.backend.requisitos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.demoiselle.jee.crud.AbstractDAO;

import br.com.backend.requisitos.entity.Atividade;

public class AtividadeDAO extends AbstractDAO<Atividade, Integer> {
	@PersistenceContext
	protected EntityManager entityManager;

	public AtividadeDAO() {
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void create(Atividade atividade) {
		try {
			getEntityManager().persist(atividade);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Atividade> findByAllUsuarioAndProjeto(Integer idUsuario, Integer idProjeto) {
		try {
			List<Atividade> atividades = getEntityManager()
					.createNamedQuery("Atividade.findByAllUsuarioAndProjeto", Atividade.class)
					.setParameter("idUsuario", idUsuario).setParameter("idProjeto", idProjeto).getResultList();

			for (Atividade atividade : atividades) {
				atividade.getDesenvolvedores().size();
			}
			return atividades;
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Atividade> findByAllUsuarioProjetoAndRequisito(Integer idUsuario, Integer idProjeto,
			Integer idRequisito) {
		try {
			return

			getEntityManager().createNamedQuery("Atividade.findByAllUsuarioProjetoAndRequisito", Atividade.class)
					.setParameter("idUsuario", idUsuario).setParameter("idProjeto", idProjeto)
					.setParameter("idRequisito", idRequisito).getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	public Atividade findByUsuarioProjeto(Integer idUsuario, Integer idProjeto, Integer idAtividade) {
		try {
			List<Atividade> atividades = getEntityManager()
					.createNamedQuery("Atividade.findByUsuarioProjeto", Atividade.class)
					.setParameter("idUsuario", idUsuario).setParameter("idProjeto", idProjeto)
					.setParameter("idAtividade", idAtividade)
					.getResultList();

			return atividades.size() > 0 ? (Atividade) atividades.get(0) : null;
		} catch (Exception e) {
			throw e;
		}
	}
}
