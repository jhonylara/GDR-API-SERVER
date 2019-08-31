package br.com.backend.requisitos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.demoiselle.jee.crud.AbstractDAO;

import br.com.backend.requisitos.entity.Integrante;

public class IntegranteDAO extends AbstractDAO<Integrante, Integer> {
	@PersistenceContext
	protected EntityManager entityManager;

	public IntegranteDAO() {
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void create(Integrante integrante) {
		try {
			getEntityManager().persist(integrante);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Integrante> listaPorProjeto(Integer idProjeto) {
		try {
			return

			getEntityManager().createNamedQuery("Integrante.findAllProjeto", Integrante.class)
					.setParameter("idProjeto", idProjeto).getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	public Integrante findByIdUsuarioAndIdProjeto(Integer idUsuario, Integer idProjeto) {
		try {
			List<Integrante> integrantes = getEntityManager()
					.createNamedQuery("Integrante.findByIdUsuarioAndIdProjeto", Integrante.class)
					.setParameter("idUsuario", idUsuario).setParameter("idProjeto", idProjeto).getResultList();

			return integrantes.size() > 0 ? (Integrante) integrantes.get(0) : null;
		} catch (Exception error) {
			throw error;
		}
	}
}
