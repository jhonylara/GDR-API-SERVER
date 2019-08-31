package br.com.backend.requisitos.enums;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ImportanciaRequisito {
	ESSENCIAL(Integer.valueOf(0), "Essencial"), IMPORTANTE(Integer.valueOf(1),
			"Importante"), DESEJAVEL(Integer.valueOf(2), "Desejavel");

	private final Integer number;
	private final String value;

	private ImportanciaRequisito(Integer number, String value) {
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

	public static ImportanciaRequisito valueString(String _importancia) {
		ImportanciaRequisito importanciaEnum = null;

		for (ImportanciaRequisito _importanciaString : values()) {
			if (_importanciaString.getValue().equals(_importancia)) {
				return importanciaEnum = _importanciaString;
			}
		}
		return importanciaEnum;
	}

	public static Map<ImportanciaRequisito, String> getPerfis() {
		Map<ImportanciaRequisito, String> map = new ConcurrentHashMap<ImportanciaRequisito, String>();
		for (ImportanciaRequisito userType : values()) {
			map.put(userType, userType.toString());
		}
		return map;
	}

	public static ImportanciaRequisito getPerfil(int codigoInt) {
		ImportanciaRequisito importanciaEnum = null;

		for (ImportanciaRequisito _importanciaNumber : values()) {
			if (_importanciaNumber.getNumber().intValue() == codigoInt) {
				return importanciaEnum = _importanciaNumber;
			}
		}
		return importanciaEnum;
	}
}
