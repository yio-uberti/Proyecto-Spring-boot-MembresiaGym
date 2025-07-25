package com.sist.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.sist.modelo.Usuario;
import com.sist.repositorio.RepositorioUsuario;

@Service
public class UsuarioServicio implements UserDetailsService {

	@Autowired
	private RepositorioUsuario repoUsuario;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repoUsuario.findByUsername(username).orElseThrow();
	    List<GrantedAuthority> authorities = usuario.getRoles().stream()
	        .map(rol -> new SimpleGrantedAuthority(rol.getRolNombre()))
	        .collect(Collectors.toList());

	    return new org.springframework.security.core.userdetails.User(
	        usuario.getUsername(),
	        usuario.getPassword(),
	        authorities
	    );
	}

}
