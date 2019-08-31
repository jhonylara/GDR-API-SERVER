package br.com.backend.requisitos.enums;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum Status
{
  CRIANDO(Integer.valueOf(0), "Criando"), 
  IMPLEMENTANDO(Integer.valueOf(1), "Implementando"), 
  TESTANDO(Integer.valueOf(2), "Testando"), 
  REJEITADO(Integer.valueOf(3), "Rejeitado"), 
  CONCLUIDO(Integer.valueOf(4), "Concluido"),
  PENDENTE(Integer.valueOf(5), "Pendente");
  
  private final Integer number;
  private final String value;
  
  private Status(Integer number, String value) {
    this.number = number;
    this.value = value;
  }
  
  public String getValue() {
    return value;
  }
  
  public Integer getNumber() {
    return number;
  }
  
  public String toString()
  {
    return value;
  }
  
  public static Status valueString(String _status) {
    Status statusEnum = null;
    
    for (Status _statusString : values()) {
      if (_statusString.getValue().equals(_status)) { return statusEnum = _statusString;
      }
    }
    return statusEnum;
  }
  
  public static Map<Status, String> getPerfis() {
    Map<Status, String> map = new ConcurrentHashMap<Status, String>();
    for (Status userType : values()) {
      map.put(userType, userType.toString());
    }
    return map;
  }
  
  public static Status getPerfil(int codigoInt)
  {
    Status StatusEnum = null;
    
    for (Status _statusNumber : values()) {
      if (_statusNumber.getNumber().intValue() == codigoInt) { return StatusEnum = _statusNumber;
      }
    }
    return StatusEnum;
  }
}
