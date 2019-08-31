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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;

import br.com.backend.requisitos.enums.PerfilIntegranteProjeto;

@Entity
@NamedQueries({
		@javax.persistence.NamedQuery(name = "Integrante.findAllProjeto", query = "SELECT i FROM Integrante i INNER JOIN i.projeto p WHERE p.id = :idProjeto"),
		@javax.persistence.NamedQuery(name = "Integrante.findByIdAndIdProjeto", query = "SELECT i FROM Integrante i INNER JOIN i.usuario u INNER JOIN i.projeto p WHERE p.id = :idProjeto AND u.id = :idUsuario AND i.id = :idIntegrante"),
		@javax.persistence.NamedQuery(name = "Integrante.findByIdUsuarioAndIdProjeto", query = "SELECT i FROM Integrante i INNER JOIN i.usuario u INNER JOIN i.projeto p WHERE p.id = :idProjeto AND u.id = :idUsuario ") })
public class Integrante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "integrante_id", nullable = false)
	private Integer id;
	
	@Enumerated
	@Column(name = "integrante_perfil_usuario_projeto", nullable = false)
	private PerfilIntegranteProjeto perfilIntegranteProjeto;

	@ManyToOne
	@JoinColumn(name = "log_id", nullable = false)
	private Log inclusao;

	@ManyToOne
	@JoinColumn(name = "log_id", nullable = true, insertable = false, updatable = false)
	private Log alteracao;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "projeto_id", nullable = false)
	private Projeto projeto;
	
	@OneToMany(mappedBy = "integrante", fetch = FetchType.EAGER)
	@Column(name = "integrante_requisitos", nullable = true)
	private List<Requisito> requisitos;
	
	@OneToMany(mappedBy = "integrante", fetch = FetchType.EAGER)
	@Column(name = "integrante_casos_de_uso", nullable = true)
	private List<CasoDeUso> casosDeUso;

	@ManyToMany(mappedBy = "desenvolvedores", fetch = FetchType.EAGER)
	@Column(name = "integrante_atividades", nullable = true)
	private List<Atividade> atividades;

	public Integrante() {
	}

	public Integrante(Integrante integrante) {
		id = integrante.id;
		perfilIntegranteProjeto = integrante.perfilIntegranteProjeto;
		usuario = integrante.usuario;
		projeto = integrante.projeto;
		requisitos = integrante.requisitos;
		atividades = integrante.atividades;
	}

	public Integrante(
		Integer id,
		PerfilIntegranteProjeto perfilIntegranteProjeto,
		Log inclusao,
		Log alteracao,
		Usuario usuario,
		Projeto projeto,
		List<Requisito> requisitos,
		List<CasoDeUso> casosDeUso,
		List<Atividade> atividades
	) {
		this.id = id;
		this.perfilIntegranteProjeto = perfilIntegranteProjeto;
		this.inclusao = inclusao;
		this.alteracao = alteracao;
		this.usuario = usuario;
		this.projeto = projeto;
		this.requisitos = requisitos;
		this.casosDeUso = casosDeUso;
		this.atividades = atividades;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PerfilIntegranteProjeto getPerfilIntegranteProjeto() {
		return perfilIntegranteProjeto;
	}

	public void setPerfilIntegranteProjeto(PerfilIntegranteProjeto perfilIntegranteProjeto) {
		this.perfilIntegranteProjeto = perfilIntegranteProjeto;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public List<Requisito> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(List<Requisito> requisitos) {
		this.requisitos = requisitos;
	}

	public List<CasoDeUso> getCasosDeUso() {
		return casosDeUso;
	}

	public void setCasosDeUso(List<CasoDeUso> casosDeUso) {
		this.casosDeUso = casosDeUso;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alteracao == null) ? 0 : alteracao.hashCode());
		result = prime * result + ((atividades == null) ? 0 : atividades.hashCode());
		result = prime * result + ((casosDeUso == null) ? 0 : casosDeUso.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inclusao == null) ? 0 : inclusao.hashCode());
		result = prime * result + ((perfilIntegranteProjeto == null) ? 0 : perfilIntegranteProjeto.hashCode());
		result = prime * result + ((projeto == null) ? 0 : projeto.hashCode());
		result = prime * result + ((requisitos == null) ? 0 : requisitos.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Integrante other = (Integrante) obj;
		if (alteracao == null) {
			if (other.alteracao != null)
				return false;
		} else if (!alteracao.equals(other.alteracao))
			return false;
		if (atividades == null) {
			if (other.atividades != null)
				return false;
		} else if (!atividades.equals(other.atividades))
			return false;
		if (casosDeUso == null) {
			if (other.casosDeUso != null)
				return false;
		} else if (!casosDeUso.equals(other.casosDeUso))
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
		if (perfilIntegranteProjeto != other.perfilIntegranteProjeto)
			return false;
		if (projeto == null) {
			if (other.projeto != null)
				return false;
		} else if (!projeto.equals(other.projeto))
			return false;
		if (requisitos == null) {
			if (other.requisitos != null)
				return false;
		} else if (!requisitos.equals(other.requisitos))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
}
