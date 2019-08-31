package br.com.backend.requisitos.dto.interfaces;

public class IntegranteDTOInterface {
	private String perfilIntegrante;

	public IntegranteDTOInterface() {
	}

	public IntegranteDTOInterface(String perfilIntegrante) {
		this.perfilIntegrante = perfilIntegrante;
	}

	public String getPerfilIntegrante() {
		return perfilIntegrante;
	}

	public void setPerfilIntegrante(String perfilIntegrante) {
		this.perfilIntegrante = perfilIntegrante;
	}
}
