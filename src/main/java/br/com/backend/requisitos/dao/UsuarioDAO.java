package br.com.backend.requisitos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import br.com.backend.requisitos.entity.Usuario;

public class UsuarioDAO extends org.demoiselle.jee.crud.AbstractDAO<Usuario, Integer> {
	@PersistenceContext
	protected EntityManager entityManager;

	public UsuarioDAO() {
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public Usuario findByEmail(String email) {
		try {
			List<Usuario> usuarios = getEntityManager().createNamedQuery("Usuario.findByEmail", Usuario.class)
					.setParameter("emailUsuario", email).getResultList();

			return usuarios.size() > 0 ? (Usuario) usuarios.get(0) : null;
		} catch (Exception e) {
			throw e;
		}
	}

	public void create(Usuario usuario) {
		try {
			getEntityManager().persist(usuario);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Usuario> list() {
		try {
			CriteriaQuery<Usuario> query = getEntityManager().getCriteriaBuilder().createQuery(Usuario.class);
			query.select(query.from(Usuario.class));

			return getEntityManager().createQuery(query).getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	public Usuario findByCodePassword(String codigo) {
		return

		(Usuario) getEntityManager().createNamedQuery("Usuario.findByCodeUpdatePassword", Usuario.class)
				.setParameter("codigoDeValidacaoUsuario", codigo).getSingleResult();
	}
}
