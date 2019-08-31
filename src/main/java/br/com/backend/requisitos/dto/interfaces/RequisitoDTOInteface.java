package br.com.backend.requisitos.dto.interfaces;

public class RequisitoDTOInteface {
	private Double idRequisito;

	private String nome;

	private String descricao;

	private String importancia;

	private String fonte;

	private String categoria;
	
	private String status;

	public RequisitoDTOInteface() {
	}

	public RequisitoDTOInteface(
		String idRequisito,
		String nome,
		String descricao,
		String importancia,
		String fonte,
		String categoria,
		String status
	) {
		this.idRequisito = new Double(idRequisito);
		this.nome = nome;
		this.descricao = descricao;
		this.importancia = importancia;
		this.fonte = fonte;
		this.categoria = categoria;
		this.status = status;
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

	public String getImportancia() {
		return importancia;
	}

	public void setImportancia(String importancia) {
		this.importancia = importancia;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
