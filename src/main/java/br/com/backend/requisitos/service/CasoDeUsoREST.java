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
import br.com.backend.requisitos.bc.CasoDeUsoBC;
import br.com.backend.requisitos.dto.interfaces.CasoDeUsoDTOInterface;
import br.com.backend.requisitos.entity.CasoDeUso;
import br.com.backend.requisitos.utils.Util;

@Path("usuario/{idUsuario}/projeto/{idProjeto}/casoDeUso")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class CasoDeUsoREST extends AbstractREST<CasoDeUso, Integer> {
	private static final Logger LOG = Logger.getLogger(CasoDeUsoREST.class.getName());

	public CasoDeUsoREST() {
	}

	@POST
	@Auth
	public Response create(
		CasoDeUsoDTOInterface c,
		@PathParam("idUsuario") Integer idUsuario,
		@PathParam("idProjeto") Integer idProjeto
	) {
		try {
			((CasoDeUsoBC) bc).create(idUsuario, idProjeto, c);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@GET
	@Path("/list")
	@Auth
	public Response list(
		@PathParam("idUsuario") Integer idUsuario,
		@PathParam("idProjeto") Integer idProjeto
	) {
		try {
			return Response.ok(((CasoDeUsoBC) bc).listarTudo(idUsuario, idProjeto)).build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@GET
	@Path("/{idCasoDeUso}")
	@Auth
	public Response buscarRequisito(
		@PathParam("idUsuario") Integer idUsuario,
		@PathParam("idProjeto") Integer idProjeto,
		@PathParam("idCasoDeUso") Integer idCasoDeUso
	) {
		try {
			return Response.ok(((CasoDeUsoBC) bc).buscarPorEspecifico(idUsuario, idProjeto, idCasoDeUso)).build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@PUT
	@Path("/{idCasoDeUso}")
	@Transactional
	@Auth
	public Response alterar(
		CasoDeUsoDTOInterface c,
		@PathParam("idUsuario") Integer idUsuario,
		@PathParam("idProjeto") Integer idProjeto,
		@PathParam("idCasoDeUso") Integer idCasoDeUso
	) {
		try {
			((CasoDeUsoBC) bc).alterar(idUsuario, idProjeto, idCasoDeUso, c);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@DELETE
	@Path("/{idCasoDeUso}")
	@Transactional
	@Auth
	public Response excluir(
		@PathParam("idUsuario") Integer idUsuario,
		@PathParam("idProjeto") Integer idProjeto,
		@PathParam("idCasoDeUso") Integer idCasoDeUso
	) {
		try {
			((CasoDeUsoBC) bc).remover(idUsuario, idProjeto, idCasoDeUso);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}
}