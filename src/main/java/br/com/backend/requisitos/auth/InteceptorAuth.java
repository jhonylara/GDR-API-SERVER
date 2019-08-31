package br.com.backend.requisitos.auth;

import java.io.IOException;
import java.security.Security;
import java.util.Calendar;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import br.com.backend.requisitos.dao.UsuarioDAO;
import br.com.backend.requisitos.entity.Usuario;

@Auth
@Provider
@Priority(1000)
public class InteceptorAuth implements ContainerRequestFilter
{
  @Inject
  private UsuarioDAO usuarioDAO;
  
  public InteceptorAuth() {}
  
  public void filter(ContainerRequestContext requestContext) throws IOException
  {
    String authorizationHeader = requestContext.getHeaderString("Authorization");
    if ((authorizationHeader == null) || (!authorizationHeader.startsWith("Bearer "))) {
      throw new NotAuthorizedException("Authorization header precisa ser provido", new Object[0]);
    }
    String token = authorizationHeader.substring("Bearer".length()).trim();
    try {
      String id = Security.getProperty(token);
      
      Usuario usuario = (Usuario)usuarioDAO.find(new Integer(id));
      if (usuario == null) { throw new IOException("Usuário não encontrado.");
      }
      Calendar dataAtual = Calendar.getInstance();
      
      if ((dataAtual.getTimeInMillis() - usuario.getDataUltimoToken().getTimeInMillis() < 0L) || 
        (dataAtual.getTimeInMillis() - usuario.getDataUltimoToken().getTimeInMillis() > 108000000L)) {
        throw new IOException("Token expirado.");
      }
      if (!usuario.getToken().equals(token)) throw new Exception("Token inválido.");
    } catch (Exception e) {
      e.printStackTrace();
      requestContext.abortWith(
        Response.status(Response.Status.UNAUTHORIZED).build());
    }
  }
}
