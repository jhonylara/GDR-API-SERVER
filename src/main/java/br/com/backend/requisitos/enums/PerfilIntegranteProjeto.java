package br.com.backend.requisitos.enums;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum PerfilIntegranteProjeto {
	GERENTE(Integer.valueOf(0), "Gerente"), ANALISTA(Integer.valueOf(1), "Analista"), DESENVOLVEDOR(Integer.valueOf(2),
			"Desenvolvedor"), VISITANTE(Integer.valueOf(3), "Visitante");

	private final Integer number;
	private final String value;

	private PerfilIntegranteProjeto(Integer number, String value) {
		this.number = number;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public Integer getNumber() {
		return number;
	}

	public String toString() {
		return value;
	}

	public static PerfilIntegranteProjeto valueString(String _perfil) {
		PerfilIntegranteProjeto perfilEnum = null;

		for (PerfilIntegranteProjeto _perfilString : values()) {
			if (_perfilString.getValue().equals(_perfil)) {
				return perfilEnum = _perfilString;
			}
		}
		return perfilEnum;
	}

	public static Map<PerfilIntegranteProjeto, String> getPerfis() {
		Map<PerfilIntegranteProjeto, String> map = new ConcurrentHashMap<PerfilIntegranteProjeto, String>();
		for (PerfilIntegranteProjeto userType : values()) {
			map.put(userType, userType.toString());
		}
		return map;
	}

	public static PerfilIntegranteProjeto getPerfil(int codigoInt) {
		PerfilIntegranteProjeto perfilEnum = null;

		for (PerfilIntegranteProjeto _perfilNumber : values()) {
			if (_perfilNumber.getNumber().intValue() == codigoInt) {
				return perfilEnum = _perfilNumber;
			}
		}
		return perfilEnum;
	}
}
