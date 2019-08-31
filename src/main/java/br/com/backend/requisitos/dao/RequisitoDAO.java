package br.com.backend.requisitos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.demoiselle.jee.crud.AbstractDAO;

import br.com.backend.requisitos.entity.Requisito;

public class RequisitoDAO extends AbstractDAO<Requisito, Integer> {
	@PersistenceContext
	protected EntityManager entityManager;

	public RequisitoDAO() {
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void create(Requisito requisito) {
		try {
			getEntityManager().persist(requisito);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Requisito> list(Integer idProjeto) {
		try {
			return getEntityManager().createNamedQuery("Requisito.findAll", Requisito.class)
					.setParameter("idProjeto", idProjeto).getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	public Requisito find(Integer idProjeto, Integer idRequisito) {
		try {
			List<Requisito> requisitos = getEntityManager().createNamedQuery("Requisito.findById", Requisito.class)
					.setParameter("idProjeto", idProjeto)
					.setParameter("idRequisito", idRequisito)
					.getResultList();

			return requisitos.size() > 0 ? (Requisito) requisitos.get(0) : null;
		} catch (Exception e) {
			throw e;
		}
	}
}
