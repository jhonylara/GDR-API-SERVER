package br.com.backend.requisitos.bc;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.demoiselle.jee.crud.AbstractBusiness;

import br.com.backend.requisitos.dao.LogDAO;
import br.com.backend.requisitos.dao.UsuarioDAO;
import br.com.backend.requisitos.dto.interfaces.UsuarioDTOInterface;
import br.com.backend.requisitos.dto.model.UsuarioDTOModel;
import br.com.backend.requisitos.entity.Log;
import br.com.backend.requisitos.entity.Usuario;
import br.com.backend.requisitos.utils.Util;

public class UsuarioBC extends AbstractBusiness<Usuario, Integer> {
	
	@Inject
	protected UsuarioDAO usuarioDAO;
	
	@Inject
	protected LogDAO logDAO;

	public UsuarioBC() {
	}

	@Transactional
	public void create(UsuarioDTOInterface u) throws Exception {
		try {
			Usuario usuario = usuarioDAO.findByEmail(u.getEmail());
			if (usuario != null) {
				throw new Exception("Email já cadastrado");
			}
			
			Util.validarUsuario(u);
			usuario = new Usuario();
			usuario.setNome(u.getNome());
			usuario.setEmail(u.getEmail());
			usuario.setSenha(u.getSenha());

			usuarioDAO.create(usuario);
			Usuario usua = usuarioDAO.findByEmail(u.getEmail());
			if (usua != null) {
				Log log = logDAO.persist(Util.logger(usua.getId(), "INCLUSÃO"));
				usua.setInclusao(log);
				
				usuarioDAO.mergeHalf(usua.getId(), usua);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void alterar(UsuarioDTOInterface u, Integer idUsuario) throws Exception {
		try {
			Usuario usuario = (Usuario) usuarioDAO.find(idUsuario);
			if (usuario == null) {
				throw new Exception("Usuário não encontrado");
			}
			Util.validarUsuario(u);

			usuario.setNome(u.getNome());
			usuario.setEmail(u.getEmail());
			
			Log log = logDAO.persist(Util.logger(usuario.getId(), "ALTERAÇÃO"));
			usuario.setAlteracao(log);

			usuarioDAO.mergeHalf(usuario.getId(), usuario);
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void excluir(Integer idUsuario) throws Exception {
		try {
			Usuario usuario = (Usuario) usuarioDAO.find(idUsuario);
			if (usuario == null) {
				throw new Exception("Usuário não encontrado");
			}
			usuarioDAO.remove(idUsuario);
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public UsuarioDTOModel login(UsuarioDTOInterface u) throws Exception {
		try {
			Usuario usuario = usuarioDAO.findByEmail(u.getEmail());
			if (usuario == null)
				throw new Exception("Usuario não encontrado");
			if (!usuario.getSenha().equals(u.getSenha()))
				throw new Exception("Senha incorreta.");

			String token = Util.gerarCodigoToken(usuario);

			usuario.setDataUltimoToken(Util.currentDate());
			usuario.setToken(token);

			Security.setProperty(token, usuario.getId().toString());
			usuarioDAO.mergeFull(usuario);
			return new UsuarioDTOModel(usuario.getId(), usuario.getNome(), usuario.getEmail(), token);
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void esqueceuSenha(UsuarioDTOInterface u) throws Exception {
		try {
			Usuario usuario = usuarioDAO.findByEmail(u.getEmail());
			if (usuario == null) {
				throw new Exception("Usuário não encontrado");
			}
			String codeEmail = Util.gerarCodigoValidacao(usuario);

			usuario.setCodigoAlteracaoSenha(codeEmail);
			Util.enviarEmail(usuario.getEmail(), "Alteração de senha.",
					"Segue codigo de verificação, para alterar sua senha<br><br>Codigo de verificação: " + codeEmail);

			usuarioDAO.mergeFull(usuario);
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void alterarSenha(UsuarioDTOInterface u, String codigoValidacao) throws Exception {
		try {
			Usuario usuario = usuarioDAO.findByCodePassword(codigoValidacao);
			if (usuario == null)
				throw new Exception("Código inválido.");

			if (!usuario.getEmail().equals(u.getEmail()))
				throw new Exception("Email inválido");

			usuario.setSenha(u.getSenha());
			usuario.setCodigoAlteracaoSenha(null);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<UsuarioDTOModel> listarTodos() {
		try {
			List<UsuarioDTOModel> usuariosDTO = new ArrayList<UsuarioDTOModel>();
			for (Usuario usuario : usuarioDAO.list())
				usuariosDTO.add(new UsuarioDTOModel(usuario.getId(), usuario.getNome(), usuario.getEmail(), null));

			return usuariosDTO;
		} catch (Exception e) {
			throw e;
		}
	}
}
