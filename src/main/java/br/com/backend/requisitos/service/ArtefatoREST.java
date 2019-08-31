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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.demoiselle.jee.crud.AbstractREST;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.com.backend.requisitos.auth.Auth;
import br.com.backend.requisitos.bc.ArtefatoBC;
import br.com.backend.requisitos.dto.interfaces.ArtefatoDTOInterface;
import br.com.backend.requisitos.entity.Artefato;
import br.com.backend.requisitos.utils.Util;

@Path("usuario/{idUsuario}/projeto/{idProjeto}/artefato")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class ArtefatoREST extends AbstractREST<Artefato, Integer>{
	private static final Logger LOG = Logger.getLogger(ArtefatoREST.class.getName());

	@POST
	@Auth
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response create(
		@PathParam("idUsuario") Integer idUsuario,
		@PathParam("idProjeto") Integer idProjeto,
		@MultipartForm ArtefatoDTOInterface a
	) {
		try {
			((ArtefatoBC) bc).create(idUsuario, idProjeto, a);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}
	
	@GET
	@Path("/{idArtefato}")
	@Auth
	public Response buscar(
		@PathParam("idUsuario") Integer idUsuario,
		@PathParam("idProjeto") Integer idProjeto,
		@PathParam("idArtefato") Integer idArtefato
	) {
		try {
			return Response.ok(((ArtefatoBC) bc).buscaEspecifica(idUsuario, idProjeto, idArtefato))
					.build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@GET
	@Path("/listar")
	@Auth
	public Response listar(
		@PathParam("idUsuario") Integer idUsuario,
		@PathParam("idProjeto") Integer idProjeto
	) {
		try {
			return Response.ok(((ArtefatoBC) bc).listar(idUsuario, idProjeto)).build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@GET
	@Path("/listaRequisito")
	@Auth
	public Response listarPorRequisito(
		@PathParam("idUsuario") Integer idUsuario,
		@PathParam("idProjeto") Integer idProjeto
	) {
		try {
			return Response.ok(((ArtefatoBC) bc).listarPorRequisito(idUsuario, idProjeto)).build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@GET
	@Path("/listaCasoDeUso")
	@Auth
	public Response listarPorCasoDeUso(
		@PathParam("idUsuario") Integer idUsuario,
		@PathParam("idProjeto") Integer idProjeto
	) {
		try {
			return Response.ok(((ArtefatoBC) bc).listarPorCasoDeUso(idUsuario, idProjeto)).build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@PUT
	@Path("/{idArtefato}")
	@Auth
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response alterar(
		@PathParam("idUsuario") Integer idUsuario,
		@PathParam("idProjeto") Integer idProjeto,
		@PathParam("idArtefato") Integer idArtefato,
		@MultipartForm ArtefatoDTOInterface a
	) {
		try {
			((ArtefatoBC) bc).alterar(idUsuario, idProjeto, idArtefato, a);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}

	@DELETE
	@Path("/{idArtefato}")
	@Auth
	public Response excluir(
		@PathParam("idUsuario") Integer idUsuario,
		@PathParam("idProjeto") Integer idProjeto,
		@PathParam("idArtefato") Integer idArtefato
	) {
		try {
			((ArtefatoBC) bc).excluir(idUsuario, idProjeto, idArtefato);
			return Response.ok().build();
		} catch (Exception e) {
			return Util.handlerError(e, LOG);
		}
	}
}
