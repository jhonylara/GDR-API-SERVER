package br.com.backend.requisitos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AtividadeIntegrante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "atividade_integrante_id", nullable = false)
	private Integer id;

	public AtividadeIntegrante() {
	}
}
