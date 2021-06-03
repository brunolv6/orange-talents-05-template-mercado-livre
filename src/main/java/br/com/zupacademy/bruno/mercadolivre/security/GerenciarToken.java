package br.com.zupacademy.bruno.mercadolivre.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.zupacademy.bruno.mercadolivre.usuario.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class GerenciarToken {
	
	@Value("${mercadolivre.jwt.expiration}")
	private String expiration;
	
	@Value("${mercadolivre.jwt.secret}")
	private String secret;

	public String criar(Authentication authentication) {
		Usuario dadosUsuario = (Usuario) authentication.getPrincipal();
		
		Date dateExpiracao = Date
				.from(LocalDateTime.now().plusHours(Long.parseLong(expiration)).atZone(ZoneId.of("America/Sao_Paulo")).toInstant());
		
		String token = Jwts.builder()
							.setSubject(dadosUsuario.getId().toString())
							.setExpiration(dateExpiracao)
							.setIssuedAt(new Date()) 
							.signWith(SignatureAlgorithm.HS256, secret)
							.compact();
		
		return token;
	}
	
	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;			
		} catch (Exception e) {
			return false;
		}
	}
	
	public Long getIdUsuario(String token) {

		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();

		return Long.parseLong(claims.getSubject());
	}
}
