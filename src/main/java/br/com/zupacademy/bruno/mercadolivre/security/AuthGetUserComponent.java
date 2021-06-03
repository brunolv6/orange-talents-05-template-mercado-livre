package br.com.zupacademy.bruno.mercadolivre.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import br.com.zupacademy.bruno.mercadolivre.usuario.Usuario;
import br.com.zupacademy.bruno.mercadolivre.usuario.UsuarioRepository;

@Component
public class AuthGetUserComponent implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);

		if (usuario.isEmpty()) {
			Assert.notNull(usuario, "Usuario não encontrado");
		}

		return usuario.get();
	}

}
