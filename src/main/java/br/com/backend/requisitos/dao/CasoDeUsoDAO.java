package br.com.backend.requisitos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.demoiselle.jee.crud.AbstractDAO;

import br.com.backend.requisitos.entity.CasoDeUso;

public class CasoDeUsoDAO extends AbstractDAO<CasoDeUso, Integer> {
	@PersistenceContext
	protected EntityManager entityManager;

	public CasoDeUsoDAO() {
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void create(CasoDeUso casoDeUso) {
		try {
			getEntityManager().persist(casoDeUso);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<CasoDeUso> list(Integer idProjeto) {
		try {
			return getEntityManager().createNamedQuery("CasoDeUso.findAll", CasoDeUso.class)
					.setParameter("idProjeto", idProjeto).getResultList();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public CasoDeUso findByIdProjetoAndICasoDeUso(Integer idProjeto, Integer idCasoDeUso) {
		try {
			List<CasoDeUso> casosDeUso = getEntityManager().createNamedQuery("CasoDeUso.findById", CasoDeUso.class)
					.setParameter("idProjeto", idProjeto)
					.setParameter("idCasoDeUso", idCasoDeUso)
					.getResultList();

			return casosDeUso.size() > 0 ? casosDeUso.get(0) : null;
		} catch (Exception e) {
			throw e;
		}
	}
}
