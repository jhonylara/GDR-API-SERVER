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
import br.com.backend.requisitos.bc.AtividadeBC;
import br.com.backend.requisitos.dto.interfaces.AtividadeDTOInterface;
import br.com.backend.requisitos.entity.Atividade;
import br.com.backend.requisitos.utils.Util;

@Path("usuario/{idUsuario}/projeto/{idProjeto}/requisito/{idRequisito}/atividade")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class AtividadeREST extends AbstractREST<Atividade, Integer> {
	private static final Logger LOG = Logger.getLogger(AtividadeREST.class.getName());

	public AtividadeREST() {
	}

	@POST
	@Auth
	public Response create(@PathParam("idUsuario") Integer idUsuario, @PathParam("idProjeto") Integer idProjeto,
			@PathParam("idRequisito") Integer idRequisito, AtividadeDTOInterface a) {
		try {
			((AtividadeBC) bc).create(a, idUsuario, idProjeto, idRequisito);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@GET
	@Path("/listaProjeto")
	@Auth
	public Response listarPorProjeto(@PathParam("idUsuario") Integer idUsuario,
			@PathParam("idProjeto") Integer idProjeto) {
		try {
			return Response.ok(((AtividadeBC) bc).listarPorProjeto(idUsuario, idProjeto)).build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@GET
	@Path("/listaRequisito")
	@Auth
	public Response listarPorRequisito(@PathParam("idUsuario") Integer idUsuario,
			@PathParam("idProjeto") Integer idProjeto, @PathParam("idRequisito") Integer idRequisito) {
		try {
			return Response.ok(((AtividadeBC) bc).listarPorRequisito(idUsuario, idProjeto, idRequisito)).build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@GET
	@Path("/{idAtividade}")
	@Auth
	public Response buscar(
		@PathParam("idUsuario") Integer idUsuario,
		@PathParam("idProjeto") Integer idProjeto,
		@PathParam("idAtividade") Integer idAtividade
	) {
		try {
			return Response.ok(((AtividadeBC) bc).buscaEspecifica(idUsuario, idProjeto, idAtividade))
					.build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@PUT
	@Path("/{idAtividade}")
	@Transactional
	@Auth
	public Response alterar(
		AtividadeDTOInterface a,
		@PathParam("idUsuario") Integer idUsuario,
		@PathParam("idProjeto") Integer idProjeto,
		@PathParam("idRequisito") Integer idRequisito,
		@PathParam("idAtividade") Integer idAtividade
	) {
		try {
			((AtividadeBC) bc).alterar(idUsuario, idProjeto, idRequisito, idAtividade, a);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@DELETE
	@Path("/{idAtividade}")
	@Transactional
	@Auth
	public Response excluir(
		@PathParam("idUsuario") Integer idUsuario,
		@PathParam("idProjeto") Integer idProjeto,
		@PathParam("idRequisito") Integer idRequisito,
		@PathParam("idAtividade") Integer idAtividade
	) {
		try {
			((AtividadeBC) bc).excluir(idUsuario, idProjeto, idRequisito, idAtividade);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}
}
