package br.com.backend.requisitos.dto.interfaces;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Calendar;

public class AtividadeDTOInterface {

	private String nome;

	private String descricao;

	private String status;
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
	private Calendar dataInicio;

	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
	private Calendar dataFim;
	
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
	private Calendar dataConclusao;

	private Integer idDesenvolvedor;

	public AtividadeDTOInterface() {
	}

	public AtividadeDTOInterface(String nome, String descricao, String status, Calendar dataInicio, Calendar dataFim,
			Calendar dataConclusao, Integer idDesenvolvedor) {
		this.nome = nome;
		this.descricao = descricao;
		this.status = status;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.dataConclusao = dataConclusao;
		this.idDesenvolvedor = idDesenvolvedor;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Calendar getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Calendar dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public Integer getIdDesenvolvedor() {
		return idDesenvolvedor;
	}

	public void setIdDesenvolvedor(Integer idDesenvolvedor) {
		this.idDesenvolvedor = idDesenvolvedor;
	}
}
