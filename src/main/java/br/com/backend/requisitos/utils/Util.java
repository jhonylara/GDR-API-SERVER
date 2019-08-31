package br.com.backend.requisitos.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.Response;

import br.com.backend.requisitos.dto.interfaces.UsuarioDTOInterface;
import br.com.backend.requisitos.entity.Log;
import br.com.backend.requisitos.entity.Usuario;
import io.swagger.util.Json;

public class Util {
	
	public static String URL_ARQUIVOS = "/home/alisson/GDR-Arquivos/"; 
	
	public Util() {
	}

	public static Calendar currentDate() {
		return Calendar.getInstance();
	}
	
	public static Log logger(Integer idIntegrante, String evento) {
		return new Log(null, idIntegrante, Util.currentDate(), evento);
	}

	public static void validarUsuario(UsuarioDTOInterface u) throws Exception {
		try {
			Pattern regExNome = Pattern.compile("[A-z]{2,} [A-z]{2,}?[A-z ]+");
			if (!u.getNome().toUpperCase().matches(regExNome.pattern())) {
				throw new Exception("O nome não é válido. O mesmo deverá conter o nome completo ou um sobrenome.");
			}
			if (u.getNome().length() > 64) {
				throw new Exception("O nome não é válido. O mesmo deverá conter no maximo 64 caracteres");
			}
			Pattern regExEmail = Pattern.compile("[A-z0-9.-_]+@[a-z]+.[a-z]{2,}?.[a-z]{2,4}");
			if (!u.getEmail().matches(regExEmail.pattern())) {
				throw new Exception(
						"O email não é válido. O mesmo poderá ser preenchido no seguinte formato: x.x-x_x@xxxxx.xxx.xxx");
			}
			if (u.getEmail().length() > 64) {
				throw new Exception("O email não é válido. O mesmo deverá conter no maximo 64 caracteres");
			}
			if (u.getSenha().length() < 8) {
				throw new Exception("A senha não é válida. A mesma deverá conter no minimo 8 caracteres");
			}
			if (u.getSenha().length() > 20)
				throw new Exception("A senha não é válida. A mesma deverá conter no maximo 20 caracteres");
		} catch (Exception e) {
			throw e;
		}
	}

	public static String gerarCodigoToken(Usuario usuario) throws Exception {
		try {
			String string = usuario.getEmail() + usuario.getSenha();
			MessageDigest token = MessageDigest.getInstance("SHA-256");

			token.update(string.getBytes(), 0, string.length());
			return new BigInteger(1, token.digest()).toString(16);
		} catch (Exception e) {
			throw e;
		}
	}

	public static String gerarCodigoValidacao(Usuario usuario) throws Exception {
		try {
			String string = usuario.getSenha();
			MessageDigest token = MessageDigest.getInstance("SHA-256");

			token.update(string.getBytes(), 0, string.length());
			return new BigInteger(1, token.digest()).toString(16).substring(0, 5).toUpperCase();
		} catch (Exception e) {
			throw e;
		}
	}

	public static String enviarEmail(String email, String title, String body) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("gdr.pbl@gmail.com", "Gdr@1234");
			}
		});

		session.setDebug(true);
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("jhony_lara@yahoo.com.br"));

			Address[] toUser = InternetAddress.parse(email);

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(title);
			message.setContent(body, "text/html");

			Transport.send(message);
			return "Email enviado com sucesso";
		} catch (MessagingException e) {
			throw new RuntimeException("Erro inesperado");
		}
	}

	public static Response handlerError(Exception e, Logger LOG) {
		e.printStackTrace();
		LOG.severe(e.getMessage());
		return Response.serverError().entity(Json.pretty(e.getMessage())).build();
	}
}
