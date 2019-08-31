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
import br.com.backend.requisitos.bc.IntegranteBC;
import br.com.backend.requisitos.dto.interfaces.IntegranteDTOInterface;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.utils.Util;

@Path("usuario/{idUsuario}/projeto/{idProjeto}/integrante")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class IntegranteREST extends AbstractREST<Integrante, Integer> {
	private static final Logger LOG = Logger.getLogger(IntegranteREST.class.getName());

	public IntegranteREST() {
	}

	@POST
	@Auth
	public Response create(@PathParam("idUsuario") Integer idUsuario, @PathParam("idProjeto") Integer idProjeto,
			IntegranteDTOInterface i) {
		try {
			((IntegranteBC) bc).create(idUsuario, idProjeto, i);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@GET
	@Path("/list")
	@Auth
	public Response listarPorProjeto(@PathParam("idUsuario") Integer idUsuario,
			@PathParam("idProjeto") Integer idProjeto) {
		try {
			return Response.ok(((IntegranteBC) bc).listaPorProjeto(idUsuario, idProjeto)).build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@GET
	@Path("/{idIntegrante}")
	@Auth
	public Response buscar(@PathParam("idUsuario") Integer idUsuario, @PathParam("idProjeto") Integer idProjeto,
			@PathParam("idIntegrante") Integer idIntegrante) {
		try {
			return Response.ok(((IntegranteBC) bc).buscarPorIntegrante(idUsuario, idProjeto, idIntegrante)).build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@PUT
	@Path("/{idIntegrante}")
	@Transactional
	@Auth
	public Response alterar(
		IntegranteDTOInterface i,
		@PathParam("idUsuario") Integer idUsuario,
		@PathParam("idProjeto") Integer idProjeto,
		@PathParam("idIntegrante") Integer idIntegrante
	) {
		try {
			((IntegranteBC) bc).alterar(idUsuario, idProjeto, idIntegrante, i);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@DELETE
	@Path("/{idIntegrante}")
	@Transactional
	@Auth
	public Response excluir(
		@PathParam("idUsuario") Integer idUsuario,
		@PathParam("idProjeto") Integer idProjeto,
		@PathParam("idIntegrante") Integer idIntegrante
	) {
		try {
			((IntegranteBC) bc).excluir(idUsuario, idProjeto, idIntegrante);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}
}
