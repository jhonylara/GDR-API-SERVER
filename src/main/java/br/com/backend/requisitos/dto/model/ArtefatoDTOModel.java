package br.com.backend.requisitos.dto.model;

import br.com.backend.requisitos.dto.interfaces.ArtefatoDTOInterface;

public class ArtefatoDTOModel extends ArtefatoDTOInterface {


	private Integer id;
	
	private String documentoBase64;

	public ArtefatoDTOModel(
		String nome,
		String descricao,
		String idRequisito,
		String idCasoDeUso,
		String documentoBase64,
		Integer id
	) {
		super(nome, descricao, idRequisito, idCasoDeUso, null, null);
		this.documentoBase64 = documentoBase64;
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDocumentoBase64() {
		return documentoBase64;
	}

	public void setIdArquivo(String documentoBase64) {
		this.documentoBase64 = documentoBase64;
	}
}
