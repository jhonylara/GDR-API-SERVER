package br.com.backend.requisitos.dto.interfaces;

public class CasoDeUsoDTOInterface {

	private String nome;

	private String escopo;

	private String nivel;

	private String preCondicao;

	private String posCondicao;

	private String cenarioPrincipal;

	private String extensao;

	private String atorPrincipal;
	
	private String status;

	public CasoDeUsoDTOInterface() {
	}

	public CasoDeUsoDTOInterface(
		String nome,
		String escopo,
		String nivel,
		String preCondicao,
		String posCondicao,
		String cenarioPrincipal,
		String extensao,
		String atorPrincial,
		String status
	) {
		this.nome = nome;
		this.escopo = escopo;
		this.nivel = nivel;
		this.preCondicao = preCondicao;
		this.posCondicao = posCondicao;
		this.cenarioPrincipal = cenarioPrincipal;
		this.extensao = extensao;
		this.atorPrincipal = atorPrincial;
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEscopo() {
		return escopo;
	}

	public void setEscopo(String escopo) {
		this.escopo = escopo;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getPreCondicao() {
		return preCondicao;
	}

	public String getPosCondicao() {
		return posCondicao;
	}

	public void setPosCondicao(String posCondicao) {
		this.posCondicao = posCondicao;
	}

	public String getCenarioPrincipal() {
		return cenarioPrincipal;
	}

	public void setCenarioPrincipal(String cenarioPrincipal) {
		this.cenarioPrincipal = cenarioPrincipal;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public void setPreCondicao(String preCondicao) {
		this.preCondicao = preCondicao;
	}

	public String getAtorPrincipal() {
		return atorPrincipal;
	}

	public void setAtorPrincipal(String atorPrincipal) {
		this.atorPrincipal = atorPrincipal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
