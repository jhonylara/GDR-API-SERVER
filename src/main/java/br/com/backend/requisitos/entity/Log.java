package br.com.backend.requisitos.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Log {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "log_id", nullable = false)
	private Integer id;

	@Column(name = "log_id_integrante", nullable = false)
	private Integer idIntegrate;
	
	@JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
	@Column(name = "log_data", nullable = false)
	private Calendar data;

	@Column(name = "log_evento", nullable = false)
	private String evento;

	public Log() {
	}

	public Log(
		Integer id,
		Integer idIntegrante,
		Calendar data,
		String evento
	) {
		this.id = id;
		this.idIntegrate = idIntegrante;
		this.data = data;
		this.evento = evento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdIntegrate() {
		return idIntegrate;
	}

	public void setIdIntegrate(Integer idIntegrate) {
		this.idIntegrate = idIntegrate;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}
}
