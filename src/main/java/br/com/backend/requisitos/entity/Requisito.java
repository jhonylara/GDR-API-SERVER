package br.com.backend.requisitos.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;

import br.com.backend.requisitos.enums.CategoriaRequisito;
import br.com.backend.requisitos.enums.ImportanciaRequisito;
import br.com.backend.requisitos.enums.Status;

@Entity
@NamedQueries({
		@javax.persistence.NamedQuery(name = "Requisito.findAll", query = "SELECT r FROM Requisito r INNER JOIN r.projeto p WHERE p.id = :idProjeto"),
		@javax.persistence.NamedQuery(name = "Requisito.findById", query = "SELECT r FROM Requisito r INNER JOIN r.projeto p WHERE p.id = :idProjeto AND r.id = :idRequisito") })
public class Requisito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "requisito_id", nullable = false)
	private Integer id;

	@Column(name = "requisito_idRequisito", nullable = false)
	private Double idRequisito;

	@ManyToOne
	@JoinColumn(name = "log_id", nullable = false)
	private Log inclusao;

	@ManyToOne
	@JoinColumn(name = "log_id", nullable = true, insertable = false, updatable = false)
	private Log alteracao;

	@Column(name = "requisito_nome", nullable = false)
	private String nome;

	@Column(name = "requisito_descricao", nullable = true)
	private String descricao;

	@Column(name = "requisito_importancia", nullable = false)
	private ImportanciaRequisito importancia;

	@Column(name = "requisito_fonte", nullable = false)
	private String fonte;

	@Enumerated
	@Column(name = "requisito_categoria", nullable = false)
	private CategoriaRequisito categoria;

	@ManyToOne
	@JoinColumn(name = "projeto_id", nullable = false)
	private Projeto projeto;

	@ManyToOne
	@JoinColumn(name = "integrante_id", nullable = false)
	private Integrante integrante;

	@OneToMany(mappedBy = "requisito", fetch = FetchType.EAGER)
	@Column(name = "requisito_atividades", nullable = true)
	private List<Atividade> atividades;

	@OneToMany(mappedBy = "requisito", fetch = FetchType.EAGER)
	@Column(name = "requisito_artefatos", nullable = true)
	private List<Artefato> artefatos;
	
	@Enumerated
	@Column(name = "requisito_status", nullable = false)
	private Status status;

	public Requisito() {
	}

	public Requisito(
		Integer id,
		String nome,
		String descricao,
		ImportanciaRequisito importancia,
		String fonte,
		CategoriaRequisito categoria,
		Log inclusao,
		Log alteracao,
		Projeto projeto,
		Integrante integrante,
		List<Atividade> atividades,
		List<Artefato> artefatos,
		Status status
	) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.importancia = importancia;
		this.fonte = fonte;
		this.categoria = categoria;
		this.inclusao = inclusao;
		this.alteracao = alteracao;
		this.projeto = projeto;
		this.integrante = integrante;
		this.atividades = atividades;
		this.artefatos = artefatos;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getIdRequisito() {
		return idRequisito;
	}

	public void setIdRequisito(Double idRequisito) {
		this.idRequisito = idRequisito;
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

	public ImportanciaRequisito getImportancia() {
		return importancia;
	}

	public void setImportancia(ImportanciaRequisito importancia) {
		this.importancia = importancia;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public CategoriaRequisito getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaRequisito categoria) {
		this.categoria = categoria;
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

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Integrante getIntegrante() {
		return integrante;
	}

	public void setIntegrante(Integrante integrante) {
		this.integrante = integrante;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public List<Artefato> getArtefatos() {
		return artefatos;
	}

	public void setArtefatos(List<Artefato> artefatos) {
		this.artefatos = artefatos;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alteracao == null) ? 0 : alteracao.hashCode());
		result = prime * result + ((artefatos == null) ? 0 : artefatos.hashCode());
		result = prime * result + ((atividades == null) ? 0 : atividades.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((fonte == null) ? 0 : fonte.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idRequisito == null) ? 0 : idRequisito.hashCode());
		result = prime * result + ((importancia == null) ? 0 : importancia.hashCode());
		result = prime * result + ((inclusao == null) ? 0 : inclusao.hashCode());
		result = prime * result + ((integrante == null) ? 0 : integrante.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((projeto == null) ? 0 : projeto.hashCode());
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
		Requisito other = (Requisito) obj;
		if (alteracao == null) {
			if (other.alteracao != null)
				return false;
		} else if (!alteracao.equals(other.alteracao))
			return false;
		if (artefatos == null) {
			if (other.artefatos != null)
				return false;
		} else if (!artefatos.equals(other.artefatos))
			return false;
		if (atividades == null) {
			if (other.atividades != null)
				return false;
		} else if (!atividades.equals(other.atividades))
			return false;
		if (categoria != other.categoria)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (fonte == null) {
			if (other.fonte != null)
				return false;
		} else if (!fonte.equals(other.fonte))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idRequisito == null) {
			if (other.idRequisito != null)
				return false;
		} else if (!idRequisito.equals(other.idRequisito))
			return false;
		if (importancia != other.importancia)
			return false;
		if (inclusao == null) {
			if (other.inclusao != null)
				return false;
		} else if (!inclusao.equals(other.inclusao))
			return false;
		if (integrante == null) {
			if (other.integrante != null)
				return false;
		} else if (!integrante.equals(other.integrante))
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
		if (status != other.status)
			return false;
		return true;
	}
}
