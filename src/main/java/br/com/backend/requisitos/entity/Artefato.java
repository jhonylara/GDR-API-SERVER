package br.com.backend.requisitos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
		@NamedQuery(name = "Artefato.findAll", query = "SELECT a FROM Artefato a " + "INNER JOIN a.projeto as p "
				+ "WHERE p.id = :idProjeto"),
		
		@NamedQuery(name = "Artefato.findById", query = "SELECT a FROM Artefato a " + "INNER JOIN a.projeto as p "
				+ "WHERE p.id = :idProjeto " + "AND a.id = :idArtefato"),

		@NamedQuery(name = "Artefato.findAllRequisito", query = "SELECT a FROM Artefato a "
				+ "INNER JOIN a.requisito as r " + "INNER JOIN r.projeto as p " + "WHERE p.id = :idProjeto"),

		@NamedQuery(name = "Artefato.findAllCasoDeUso", query = "SELECT a FROM Artefato a "
				+ "INNER JOIN a.casoDeUso as c " + "INNER JOIN c.projeto as p " + "WHERE p.id = :idProjeto") })
@Entity
public class Artefato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "artefato_id", nullable = false)
	private Integer id;

	@Column(name = "artefato_nome", nullable = false)
	private String nome;

	@Column(name = "artefato_descricao", nullable = false)
	private String descricao;

	@Column(name = "artefato_documento", nullable = true)
	private String caminhoDocumento;
	
	@Column(name = "artefato_mediaType", nullable = true)
	private String tipoDocumento;

	@ManyToOne
	@JoinColumn(name = "log_id", nullable = false)
	private Log inclusao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "projeto_id", nullable = false)
	private Projeto projeto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "requisito_id", nullable = true)
	private Requisito requisito;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "caso_de_uso_id", nullable = true)
	private CasoDeUso casoDeUso;

	@ManyToOne
	@JoinColumn(name = "log_id", nullable = true, insertable = false, updatable = false)
	private Log alteracao;

	public Artefato() {
	}

	public Artefato(Integer id, String nome, String descricao, Log inclusao, Requisito requisito, CasoDeUso casoDeUso,
			Log alteracao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.inclusao = inclusao;
		this.requisito = requisito;
		this.casoDeUso = casoDeUso;
		this.alteracao = alteracao;
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

	public String getCaminhoDocumento() {
		return caminhoDocumento;
	}

	public void setCaminhoDocumento(String caminhoDocumento) {
		this.caminhoDocumento = caminhoDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Log getInclusao() {
		return inclusao;
	}

	public void setInclusao(Log inclusao) {
		this.inclusao = inclusao;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Requisito getRequisito() {
		return requisito;
	}

	public void setRequisito(Requisito requisito) {
		this.requisito = requisito;
	}

	public CasoDeUso getCasoDeUso() {
		return casoDeUso;
	}

	public void setCasoDeUso(CasoDeUso casoDeUso) {
		this.casoDeUso = casoDeUso;
	}

	public Log getAlteracao() {
		return alteracao;
	}

	public void setAlteracao(Log alteracao) {
		this.alteracao = alteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alteracao == null) ? 0 : alteracao.hashCode());
		result = prime * result + ((caminhoDocumento == null) ? 0 : caminhoDocumento.hashCode());
		result = prime * result + ((casoDeUso == null) ? 0 : casoDeUso.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inclusao == null) ? 0 : inclusao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((projeto == null) ? 0 : projeto.hashCode());
		result = prime * result + ((requisito == null) ? 0 : requisito.hashCode());
		result = prime * result + ((tipoDocumento == null) ? 0 : tipoDocumento.hashCode());
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
		Artefato other = (Artefato) obj;
		if (alteracao == null) {
			if (other.alteracao != null)
				return false;
		} else if (!alteracao.equals(other.alteracao))
			return false;
		if (caminhoDocumento == null) {
			if (other.caminhoDocumento != null)
				return false;
		} else if (!caminhoDocumento.equals(other.caminhoDocumento))
			return false;
		if (casoDeUso == null) {
			if (other.casoDeUso != null)
				return false;
		} else if (!casoDeUso.equals(other.casoDeUso))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
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
		if (projeto == null) {
			if (other.projeto != null)
				return false;
		} else if (!projeto.equals(other.projeto))
			return false;
		if (requisito == null) {
			if (other.requisito != null)
				return false;
		} else if (!requisito.equals(other.requisito))
			return false;
		if (tipoDocumento == null) {
			if (other.tipoDocumento != null)
				return false;
		} else if (!tipoDocumento.equals(other.tipoDocumento))
			return false;
		return true;
	}
}
