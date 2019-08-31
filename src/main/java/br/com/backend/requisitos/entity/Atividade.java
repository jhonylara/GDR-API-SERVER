package br.com.backend.requisitos.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;

import br.com.backend.requisitos.enums.Status;

@Entity
@NamedQueries({
		@javax.persistence.NamedQuery(name = "Atividade.findByAllUsuarioAndProjeto", query = "SELECT a FROM Atividade a INNER JOIN a.requisito r INNER JOIN r.projeto p INNER JOIN r.integrante i INNER JOIN i.usuario u WHERE u.id = :idUsuario AND p.id = :idProjeto"),
		@javax.persistence.NamedQuery(name = "Atividade.findByAllUsuarioProjetoAndRequisito", query = "SELECT a FROM Atividade a INNER JOIN a.requisito r INNER JOIN r.projeto p INNER JOIN r.integrante i INNER JOIN i.usuario u WHERE u.id = :idUsuario AND p.id = :idProjeto AND r.id = :idRequisito"),
		@javax.persistence.NamedQuery(
				name = "Atividade.findByUsuarioProjeto", 
				query = "SELECT a FROM Atividade a "
						+ "INNER JOIN a.requisito r "
						+ "INNER JOIN r.projeto p "
						+ "INNER JOIN r.integrante i "
						+ "INNER JOIN i.usuario u "
						+ "WHERE u.id = :idUsuario "
						+ "AND p.id = :idProjeto "
						+ "AND a.id = :idAtividade") })
public class Atividade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "atividade_id", nullable = false)
	private Integer id;

	@Column(name = "atividade_nome", nullable = false)
	private String nome;
	
	@Column(name = "atividade_descricao", nullable = false)
	private String descricao;
	
	@Enumerated
	@Column(name = "atividade_status", nullable = false)
	private Status status;
	
	@Column(name = "atividade_data_inicio", nullable = false)
	private Calendar dataInicio;
	
	@Column(name = "atividade_data_conclusao", nullable = true)
	private Calendar dataConclusao;
	
	@Column(name = "atividade_data_fim", nullable = false)
	private Calendar dataFim;

	@ManyToOne
	@JoinColumn(name = "log_id", nullable = false)
	private Log inclusao;

	@ManyToOne
	@JoinColumn(name = "log_id", nullable = true, insertable = false, updatable = false)
	private Log alteracao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "requisito_id", nullable = false)
	private Requisito requisito;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Atividade_has_Integrante", joinColumns = {
			@JoinColumn(name = "projeto_id") }, inverseJoinColumns = { @JoinColumn(name = "integrante_id") })
	private List<Integrante> desenvolvedores;

	public Atividade() {
	}

	public Atividade(
		Integer id,
		String nome,
		String descricao,
		Status status,
		Calendar dataInicio,
		Calendar dataFim,
		Calendar dataConclusao,
		Log inclusao,
		Log alteracao,
		Requisito requisito,
		List<Integrante> desenvolvedores
	) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.status = status;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.inclusao = inclusao;
		this.dataConclusao = dataConclusao;
		this.alteracao = alteracao;
		this.requisito = requisito;
		this.desenvolvedores = desenvolvedores;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public Calendar getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Calendar dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public Log getInclusao() {
		return inclusao;
	}

	public void setInclusao(Log inclusao) {
		this.inclusao = inclusao;
	}

	public Log getAlteracao() {
		return alteracao;
	}

	public void setAlteracao(Log alteracao) {
		this.alteracao = alteracao;
	}

	public Requisito getRequisito() {
		return requisito;
	}

	public void setRequisito(Requisito requisito) {
		this.requisito = requisito;
	}

	public List<Integrante> getDesenvolvedores() {
		return desenvolvedores;
	}

	public void setDesenvolvedores(List<Integrante> desenvolvedores) {
		this.desenvolvedores = desenvolvedores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alteracao == null) ? 0 : alteracao.hashCode());
		result = prime * result + ((dataConclusao == null) ? 0 : dataConclusao.hashCode());
		result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
		result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((desenvolvedores == null) ? 0 : desenvolvedores.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inclusao == null) ? 0 : inclusao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((requisito == null) ? 0 : requisito.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		if (alteracao == null) {
			if (other.alteracao != null)
				return false;
		} else if (!alteracao.equals(other.alteracao))
			return false;
		if (dataConclusao == null) {
			if (other.dataConclusao != null)
				return false;
		} else if (!dataConclusao.equals(other.dataConclusao))
			return false;
		if (dataFim == null) {
			if (other.dataFim != null)
				return false;
		} else if (!dataFim.equals(other.dataFim))
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (desenvolvedores == null) {
			if (other.desenvolvedores != null)
				return false;
		} else if (!desenvolvedores.equals(other.desenvolvedores))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inclusao == null) {
			if (other.inclusao != null)
				return false;
		} else if (!inclusao.equals(other.inclusao))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (requisito == null) {
			if (other.requisito != null)
				return false;
		} else if (!requisito.equals(other.requisito))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
}
