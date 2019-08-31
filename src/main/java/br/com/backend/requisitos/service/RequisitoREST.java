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
import br.com.backend.requisitos.bc.RequisitoBC;
import br.com.backend.requisitos.dto.interfaces.RequisitoDTOInteface;
import br.com.backend.requisitos.entity.Requisito;
import br.com.backend.requisitos.utils.Util;

@Path("usuario/{idUsuario}/projeto/{idProjeto}/requisito")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class RequisitoREST extends AbstractREST<Requisito, Integer> {
	private static final Logger LOG = Logger.getLogger(RequisitoREST.class.getName());

	public RequisitoREST() {
	}

	@POST
	@Auth
	public Response create(RequisitoDTOInteface r, @PathParam("idUsuario") Integer idUsuario,
			@PathParam("idProjeto") Integer idProjeto) {
		try {
			((RequisitoBC) bc).create(idUsuario, idProjeto, r);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@GET
	@Path("/list")
	@Auth
	public Response list(@PathParam("idUsuario") Integer idUsuario, @PathParam("idProjeto") Integer idProjeto) {
		try {
			return Response.ok(((RequisitoBC) bc).listarTudo(idUsuario, idProjeto)).build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@GET
	@Path("/{idRequisito}")
	@Auth
	public Response buscarRequisito(@PathParam("idUsuario") Integer idUsuario,
			@PathParam("idProjeto") Integer idProjeto, @PathParam("idRequisito") Integer idRequisito) {
		try {
			return Response.ok(((RequisitoBC) bc).buscarPorEspecifico(idUsuario, idProjeto, idRequisito)).build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@PUT
	@Path("/{idRequisito}")
	@Transactional
	@Auth
	public Response alterar(RequisitoDTOInteface r, @PathParam("idUsuario") Integer idUsuario,
			@PathParam("idProjeto") Integer idProjeto, @PathParam("idRequisito") Integer idRequisito) {
		try {
			((RequisitoBC) bc).alterar(idUsuario, idProjeto, idRequisito, r);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@DELETE
	@Path("/{idRequisito}")
	@Transactional
	@Auth
	public Response excluir(@PathParam("idUsuario") Integer idUsuario, @PathParam("idProjeto") Integer idProjeto,
			@PathParam("idRequisito") Integer idRequisito) {
		try {
			((RequisitoBC) bc).remover(idUsuario, idProjeto, idRequisito);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}
}
