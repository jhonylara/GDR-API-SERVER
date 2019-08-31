package br.com.backend.requisitos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProjetoIntegrante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projeto_intergrante_id", nullable = false)
	private Integer id;

	public ProjetoIntegrante() {
	}
}
