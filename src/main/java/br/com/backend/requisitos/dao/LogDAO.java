package br.com.backend.requisitos.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.demoiselle.jee.crud.AbstractDAO;

import br.com.backend.requisitos.entity.Log;

public class LogDAO extends AbstractDAO<Log, Integer>{

	@PersistenceContext
	protected EntityManager entityManager;

	public LogDAO() {
	}
	
	public Log create(Log log) {
		try {
			System.out.println(log);
			getEntityManager().persist(log);
			getEntityManager().flush();
			return log;
		} catch (Exception e) {
			throw e;
		}
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
