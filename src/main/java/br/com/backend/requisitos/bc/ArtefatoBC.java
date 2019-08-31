package br.com.backend.requisitos.bc;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.demoiselle.jee.crud.AbstractBusiness;

import br.com.backend.requisitos.dao.ArtefatoDAO;
import br.com.backend.requisitos.dao.CasoDeUsoDAO;
import br.com.backend.requisitos.dao.IntegranteDAO;
import br.com.backend.requisitos.dao.LogDAO;
import br.com.backend.requisitos.dao.ProjetoDAO;
import br.com.backend.requisitos.dao.RequisitoDAO;
import br.com.backend.requisitos.dto.interfaces.ArtefatoDTOInterface;
import br.com.backend.requisitos.dto.model.ArtefatoDTOModel;
import br.com.backend.requisitos.entity.Artefato;
import br.com.backend.requisitos.entity.CasoDeUso;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Log;
import br.com.backend.requisitos.entity.Projeto;
import br.com.backend.requisitos.entity.Requisito;
import br.com.backend.requisitos.utils.Util;

public class ArtefatoBC extends AbstractBusiness<Artefato, Integer>{
	
	@Inject
	private ArtefatoDAO artefatoDAO;
	
	@Inject
	private RequisitoDAO requisitoDAO;

	@Inject 
	private CasoDeUsoDAO casoDeUsoDAO;

	@Inject
	private IntegranteDAO integranteDAO;
	
	@Inject
	private ProjetoDAO projetoDAO;

	@Inject
	private LogDAO logDAO;

	@Transactional
	public void create(
		Integer idUsuario,
		Integer idProjeto,
		ArtefatoDTOInterface a
	) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null) 
				throw new Exception("Projeto não encontrado");

			Integrante criador = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if(criador == null)
				throw new Exception("Usuário não encontrado");

			String caminhoCompletoDocumento  = Util.URL_ARQUIVOS + a.getNome() + "." + buscarPrefixoDocumento(a.getTipoDocumento());
			salvarDocumento(a.getDocumento(), caminhoCompletoDocumento);
	
			Artefato artefato = new Artefato();
			Log log = logDAO.persist(Util.logger(criador.getId(), "INCLUSÃO"));
			
			artefato.setTipoDocumento(a.getTipoDocumento().split(" ")[0]);
			artefato.setCaminhoDocumento(caminhoCompletoDocumento);
			artefato.setInclusao(log);
			artefato.setProjeto(projeto);
			artefato.setDescricao(a.getDescricao());
			artefato.setNome(a.getNome());
			
			if(a.getIdCasoDeUso() == null && a.getIdRequisito() == null) {
				throw new Exception("Requisito e Caso de uso não encontrado");
			}

			if(a.getIdRequisito() != null) {
				Requisito requisito = requisitoDAO.find(idProjeto, new Integer(a.getIdRequisito().trim()));
				if (requisito == null)
					throw new Exception("Requisito não encontrado");

				artefato.setRequisito(requisito);
			}
			
			if(a.getIdCasoDeUso() != null) {
				CasoDeUso casoDeUso = casoDeUsoDAO.findByIdProjetoAndICasoDeUso(idProjeto, new Integer(a.getIdCasoDeUso().trim()));
				if(casoDeUso == null)
					throw new Exception("Caso de uso não encontrado");

				artefato.setCasoDeUso(casoDeUso);
			}

			artefatoDAO.create(artefato);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<ArtefatoDTOModel> listar(
		Integer idUsuario,
		Integer idProjeto
	) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null) 
				throw new Exception("Projeto não encontrado");

			Integrante criador = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if(criador == null)
				throw new Exception("Usuário não encontrado");

			List<ArtefatoDTOModel> artefatosModel = new ArrayList<>();
			for (Artefato artefato : artefatoDAO.list(idProjeto)) {
				artefatosModel.add(
					new ArtefatoDTOModel(
						artefato.getNome(),
						artefato.getDescricao(),
						artefato.getRequisito() != null ? artefato.getRequisito().getId().toString() : null,
						artefato.getCasoDeUso() != null ? artefato.getCasoDeUso().getId().toString() : null,
						artefato.getCaminhoDocumento(),
						artefato.getId()
					)
				);
			}
			
			return artefatosModel;
		} catch (Exception e) {
			throw e;
		}
	}

	public List<ArtefatoDTOModel> listarPorRequisito(
			Integer idUsuario,
		Integer idProjeto
	) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null) 
				throw new Exception("Projeto não encontrado");

			Integrante criador = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if(criador == null)
				throw new Exception("Usuário não encontrado");

			List<ArtefatoDTOModel> artefatosModel = new ArrayList<>();
			for (Artefato artefato : artefatoDAO.listRequisito(idProjeto)) {
				artefatosModel.add(
					new ArtefatoDTOModel(
						artefato.getNome(),
						artefato.getDescricao(),
						artefato.getRequisito() != null ? artefato.getRequisito().getId().toString() : null,
						artefato.getCasoDeUso() != null ? artefato.getCasoDeUso().getId().toString() : null,
						artefato.getCaminhoDocumento(),
						artefato.getId()
					)
				);
			}
			
			return artefatosModel;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<ArtefatoDTOModel> listarPorCasoDeUso(
		Integer idUsuario,
		Integer idProjeto
	) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null) 
				throw new Exception("Projeto não encontrado");

			Integrante criador = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if(criador == null)
				throw new Exception("Usuário não encontrado");

			List<ArtefatoDTOModel> artefatosModel = new ArrayList<>();
			for (Artefato artefato : artefatoDAO.listCasoDeUso(idProjeto)) {
				artefatosModel.add(
					new ArtefatoDTOModel(
						artefato.getNome(),
						artefato.getDescricao(),
						artefato.getRequisito() != null ? artefato.getRequisito().getId().toString() : null,
						artefato.getCasoDeUso() != null ? artefato.getCasoDeUso().getId().toString() : null,
						artefato.getCaminhoDocumento(),
						artefato.getId()
					)
				);
			}
			
			return artefatosModel;
		} catch (Exception e) {
			throw e;
		}
	}

	public ArtefatoDTOModel buscaEspecifica(
		Integer idUsuario,
		Integer idProjeto,
		Integer idArtefato) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null) 
				throw new Exception("Projeto não encontrado");

			Integrante criador = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if(criador == null)
				throw new Exception("Usuário não encontrado");

			Artefato artefato = artefatoDAO.findById(idProjeto, idArtefato);
			if(artefato == null)
				throw new Exception("Artefato não encontrado");
			
			byte[] encoded = Base64.getEncoder().encode(buscarDocumento(artefato.getCaminhoDocumento()));
			return new ArtefatoDTOModel(
				artefato.getNome(),
				artefato.getDescricao(),
				artefato.getRequisito() != null ? artefato.getRequisito().getId().toString() : null,
				artefato.getCasoDeUso() != null ? artefato.getCasoDeUso().getId().toString() : null,
				"data:" + artefato.getTipoDocumento() + ";base64," + new String(encoded),
				artefato.getId()
			);
		} catch (Exception e) {
			throw e;
		}
	}	
	
	@Transactional
	public void alterar(
		Integer idUsuario,
		Integer idProjeto,
		Integer idArtefato,
		ArtefatoDTOInterface a
	) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null) 
				throw new Exception("Projeto não encontrado");

			Integrante criador = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if(criador == null)
				throw new Exception("Usuário não encontrado");

			Artefato artefato = artefatoDAO.findById(idProjeto, idArtefato);
			if(artefato == null)
				throw new Exception("Atividade não encontrada");

			if(a.getIdCasoDeUso() == null && a.getIdRequisito() == null) {
				throw new Exception("Requisito e Caso de uso não encontrado");
			}

			Requisito requisito = null;
			if(a.getIdRequisito() != null) {				
				requisito = requisitoDAO.find(idProjeto, new Integer(a.getIdRequisito().trim()));
				artefato.setRequisito(requisito);
			}

			CasoDeUso casoDeUso = null;
			if(a.getIdCasoDeUso() != null) {
				casoDeUso = casoDeUsoDAO.findByIdProjetoAndICasoDeUso(idProjeto, new Integer(a.getIdCasoDeUso().trim()));				
				artefato.setCasoDeUso(casoDeUso);
			}
			
			Log log = logDAO.persist(Util.logger(criador.getId(), "ALTERAÇÃO"));
			
			String caminhoCompletoDocumento  = Util.URL_ARQUIVOS + a.getNome() + "." + buscarPrefixoDocumento(a.getTipoDocumento());
			salvarDocumento(a.getDocumento(), caminhoCompletoDocumento);
			
			if(caminhoCompletoDocumento != artefato.getCaminhoDocumento()) {
				excluirDocumento(artefato.getCaminhoDocumento());
			}
	
			artefato.setTipoDocumento(a.getTipoDocumento().split(" ")[0]);
			artefato.setCaminhoDocumento(caminhoCompletoDocumento);
			artefato.setAlteracao(log);
			artefato.setProjeto(projeto);
			artefato.setDescricao(a.getDescricao());
			artefato.setNome(a.getNome());

			artefatoDAO.mergeFull(artefato);
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void excluir(Integer idUsuario, Integer idProjeto, Integer idArtefato) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null) 
				throw new Exception("Projeto não encontrado");
			
			Integrante criador = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if(criador == null)
				throw new Exception("Usuário não encontrado");
			
			Artefato artefato = artefatoDAO.findById(idProjeto, idArtefato);
			if(artefato == null)
				throw new Exception("Artefato não encontrada");

			excluirDocumento(artefato.getCaminhoDocumento());
			artefatoDAO.remove(idArtefato);
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void excluirDocumento(String caminhoDocumento) throws Exception {
		try {
			File documento = new File(caminhoDocumento);
			documento.delete();
		} catch (Exception e) {
			throw new Exception("Não foi possivel excluir documento.");
		}
	}
	
	private void salvarDocumento(byte[] documento, String nomeComTipoDocumento) throws Exception {
		try ( 
			FileOutputStream fileOutputStream = new FileOutputStream(new File(nomeComTipoDocumento));
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		) {
			byteArrayOutputStream.write(documento);
			fileOutputStream.write(byteArrayOutputStream.toByteArray());
		} catch (Exception e) {
			throw new Exception("Falha ao guardar Arquivo - " + nomeComTipoDocumento);
		}
	}
	
	private byte[] buscarDocumento(String caminhoCompletoDocumento) throws Exception {
		try {
			Path fileLocation = Paths.get(caminhoCompletoDocumento);
			return Files.readAllBytes(fileLocation);
		} catch (Exception e) {
			throw new Exception("Falha ao buscar Arquivo - " + caminhoCompletoDocumento);
		}
	}
	
	private String buscarPrefixoDocumento(String tipoDocumento) throws Exception {
		try {
			String regEx = tipoDocumento.substring(tipoDocumento.length() - 4);
			regEx = regEx.replace(".", "");
			
			return regEx;
		} catch (Exception e) {
			throw new Exception("Erro ao obter tipo de documento");
		}
	}
}
