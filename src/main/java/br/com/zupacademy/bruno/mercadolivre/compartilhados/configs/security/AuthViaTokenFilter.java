package br.com.zupacademy.bruno.mercadolivre.compartilhados.configs.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zupacademy.bruno.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.bruno.mercadolivre.cadastroUsuario.UsuarioRepository;

public class AuthViaTokenFilter extends OncePerRequestFilter {

	private GerenciarToken gerenciarToken;

	private UsuarioRepository usuarioRepository;

	public AuthViaTokenFilter(GerenciarToken gerenciarToken, UsuarioRepository usuarioRepository) {
		super();
		this.gerenciarToken = gerenciarToken;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToken(request);

		boolean valido = gerenciarToken.isTokenValido(token);

		if (valido) {
			autenticarCliente(token);
		}

		filterChain.doFilter(request, response);

	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}

	private void autenticarCliente(String token) {
		
		Assert.notNull(token, "Token não deve ser null");

		Long idUsuario = gerenciarToken.getIdUsuario(token);

		Usuario usuario = usuarioRepository.findById(idUsuario).get();

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
