package br.com.backend.requisitos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.demoiselle.jee.crud.AbstractDAO;

import br.com.backend.requisitos.entity.Artefato;

public class ArtefatoDAO extends AbstractDAO<Artefato, Integer> {
	@PersistenceContext
	protected EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void create(Artefato artefato) {
		try {
			getEntityManager().persist(artefato);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Artefato> list(Integer idProjeto) {
		try {
			return getEntityManager().createNamedQuery("Artefato.findAll", Artefato.class)
					.setParameter("idProjeto", idProjeto)
					.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	public Artefato findById(Integer idProjeto, Integer idArtefato) {
		try {
			List<Artefato> artefatos = getEntityManager().createNamedQuery("Artefato.findById", Artefato.class)
					.setParameter("idProjeto", idProjeto)
					.setParameter("idArtefato", idArtefato)
					.getResultList();

			return artefatos.size() > 0 ? (Artefato) artefatos.get(0) : null;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Artefato> listRequisito(Integer idProjeto) {
		try {
			return getEntityManager().createNamedQuery("Artefato.findAllRequisito", Artefato.class)
					.setParameter("idProjeto", idProjeto)
					.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Artefato> listCasoDeUso(Integer idProjeto) {
		try {
			return getEntityManager().createNamedQuery("Artefato.findAllCasoDeUso", Artefato.class)
					.setParameter("idProjeto", idProjeto)
					.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}
}
