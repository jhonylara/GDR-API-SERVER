package br.com.backend.requisitos.dto.interfaces;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Calendar;

public class ProjetoDTOInterface {
	
	private String nome;
	
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
	private Calendar dataInicio;
	
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
	private Calendar dataFim;
	
	private String status;

	public ProjetoDTOInterface() {
	}

	public ProjetoDTOInterface(String nome, Calendar dataInicio, Calendar dataFim, String status) {
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
