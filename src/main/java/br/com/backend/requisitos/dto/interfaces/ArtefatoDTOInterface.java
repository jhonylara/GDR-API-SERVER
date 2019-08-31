package br.com.backend.requisitos.dto.interfaces;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class ArtefatoDTOInterface {
	
	private String nome;

	private String descricao;
	
	private String idRequisito;
	
	private String idCasoDeUso;
	
	private byte[] documento;
	
	private String tipoDocumento;

	public ArtefatoDTOInterface() {
	}

	public ArtefatoDTOInterface(
		String nome,
		String descricao,
		String idRequisito,
		String idCasoDeUso,
		byte[] documento,
		String tipoDocumento
	) {
		this.nome = nome;
		this.descricao = descricao;
		this.idRequisito = idRequisito;
		this.idCasoDeUso = idCasoDeUso;
		this.documento = documento;
		this.tipoDocumento = tipoDocumento;
	}

	public String getNome() {
		return nome;
	}

	@FormParam("nome")
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	@FormParam("descricao")
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIdRequisito() {
		return idRequisito;
	}

	@FormParam("idRequisito")
	public void setIdRequisito(String idRequisito) {
		this.idRequisito = idRequisito;
	}

	public String getIdCasoDeUso() {
		return idCasoDeUso;
	}

	@FormParam("idCasoDeUso")
	public void setIdCasoDeUso(String idCasoDeUso) {
		this.idCasoDeUso = idCasoDeUso;
	}

	public byte[] getDocumento() {
		return documento;
	}

	@FormParam("documento")
	@PartType("application/octet-stream")
	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	@FormParam("tipoDocumento")
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
}
