package br.com.backend.requisitos.service;

import java.util.logging.Logger;

import javax.transaction.Transactional;
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
import br.com.backend.requisitos.bc.ProjetoBC;
import br.com.backend.requisitos.dto.interfaces.ProjetoDTOInterface;
import br.com.backend.requisitos.entity.Projeto;
import br.com.backend.requisitos.utils.Util;

@Path("usuario/{idUsuario}/projeto")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class ProjetoREST extends AbstractREST<Projeto, Integer> {
	private static final Logger LOG = Logger.getLogger(ProjetoREST.class.getName());

	public ProjetoREST() {
	}

	@POST
	@Auth
	public Response create(@PathParam("idUsuario") Integer idUsuario, ProjetoDTOInterface p) {
		try {
			((ProjetoBC) bc).create(p, idUsuario);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@GET
	@Path("/list")
	@Auth
	public Response list(@PathParam("idUsuario") Integer idUsuario) {
		try {
			return Response.ok(((ProjetoBC) bc).listarProjetos(idUsuario)).build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@GET
	@Path("/{idProjeto}")
	@Auth
	public Response buscar(@PathParam("idUsuario") Integer idUsuario, @PathParam("idProjeto") Integer idProjeto) {
		try {
			return Response.ok(((ProjetoBC) bc).buscarProjeto(idUsuario, idProjeto)).build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@PUT
	@Path("/{idProjeto}")
	@Auth
	public Response alterar(ProjetoDTOInterface p, @PathParam("idUsuario") Integer idUsuario,
			@PathParam("idProjeto") Integer idProjeto) {
		try {
			((ProjetoBC) bc).alterar(p, idUsuario, idProjeto);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@DELETE
	@Path("/{idProjeto}")
	@Transactional
	@Auth
	public Response excluir(@PathParam("idUsuario") Integer idUsuario, @PathParam("idProjeto") Integer idProjeto) {
		try {
			((ProjetoBC) bc).excluir(idUsuario, idProjeto);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}
	
	//Jhony
	@GET
	@Path("/listIntegrantes/{idProjeto}")
	@Auth
	public Response listIntegrantes(@PathParam("idProjeto") Integer idProjeto) {
		try {
			return Response.ok(((ProjetoBC) bc).listarIntegrantes(idProjeto)).build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}
	
	//Jhony
	@GET
	@Path("/listCasosDeUso/{idProjeto}")
	@Auth
	public Response listCasosDeUso(@PathParam("idProjeto") Integer idProjeto) {
		try {
			return Response.ok(((ProjetoBC) bc).listarCasosDeUso(idProjeto)).build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}
	
	//Jhony
	@GET
	@Path("/listRequisitos/{idProjeto}")
	@Auth
	public Response listCasoDeUso(@PathParam("idProjeto") Integer idProjeto) {
		try {
			return Response.ok(((ProjetoBC) bc).listarRequisitos(idProjeto)).build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}
}
