package br.com.backend.requisitos.service;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.demoiselle.jee.crud.AbstractREST;

import br.com.backend.requisitos.auth.Auth;
import br.com.backend.requisitos.bc.UsuarioBC;
import br.com.backend.requisitos.dto.interfaces.UsuarioDTOInterface;
import br.com.backend.requisitos.entity.Usuario;
import br.com.backend.requisitos.utils.Util;

@Path("usuario")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class UsuarioREST extends AbstractREST<Usuario, Integer> {
	private static final Logger LOG = Logger.getLogger(UsuarioREST.class.getName());

	public UsuarioREST() {
	}

	@POST
	public Response create(UsuarioDTOInterface u) {
		try {
			((UsuarioBC) bc).create(u);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@POST
	@Path("/auth")
	public Response login(UsuarioDTOInterface u) {
		try {
			return Response.ok(((UsuarioBC) bc).login(u)).build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@POST
	@Path("/esqueceu-senha")
	public Response esqueceuSenha(UsuarioDTOInterface u) {
		try {
			((UsuarioBC) bc).esqueceuSenha(u);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@POST
	@Path("/valida-senha/{codigoValidacao}")
	public Response validarSenha(UsuarioDTOInterface u, @PathParam("codigoValidacao") String codigoValidacao) {
		try {
			((UsuarioBC) bc).alterarSenha(u, codigoValidacao);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@GET
	@Path("/list")
	@Auth
	public Response list() {
		try {
			return Response.ok(((UsuarioBC) bc).listarTodos()).build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@PUT
	@Path("/{idUsuario}")
	@Auth
	public Response alterar(UsuarioDTOInterface u, @PathParam("idUsuario") Integer idUsuario) {
		try {
			((UsuarioBC) bc).alterar(u, idUsuario);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@DELETE
	@Path("/{idUsuario}")
	@Auth
	public Response excluir(@PathParam("idUsuario") Integer idUsuario) {
		try {
			((UsuarioBC) bc).excluir(idUsuario);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}
}
