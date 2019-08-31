package br.com.backend.requisitos.enums;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum CategoriaRequisito {
	FUNCIONAL(Integer.valueOf(0), "Funcional"), NAOFUNCIONAL(Integer.valueOf(1), "Nao Funcional");

	private final Integer number;
	private final String value;

	private CategoriaRequisito(Integer number, String value) {
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

	public static CategoriaRequisito valueString(String _importancia) {
		CategoriaRequisito categoriaEnum = null;

		for (CategoriaRequisito _categoriaString : values()) {
			if (_categoriaString.getValue().equals(_importancia)) {
				return categoriaEnum = _categoriaString;
			}
		}
		return categoriaEnum;
	}

	public static Map<CategoriaRequisito, String> getPerfis() {
		Map<CategoriaRequisito, String> map = new ConcurrentHashMap<CategoriaRequisito, String>();
		for (CategoriaRequisito userType : values()) {
			map.put(userType, userType.toString());
		}
		return map;
	}

	public static CategoriaRequisito getPerfil(int codigoInt) {
		CategoriaRequisito CategoriaEnum = null;

		for (CategoriaRequisito _perfilNumber : values()) {
			if (_perfilNumber.getNumber().intValue() == codigoInt) {
				return CategoriaEnum = _perfilNumber;
			}
		}
		return CategoriaEnum;
	}
}
