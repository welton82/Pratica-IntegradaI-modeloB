package com.ueg.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.eug.entities.Usuario;
import com.eug.repository.UsuarioRepository;
import com.ueg.data.DetalheUsuarioData;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService{

	private final UsuarioRepository repository;
	
	
	
	public DetalheUsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario>usuario = repository.findByLogin(username);
		
		if(usuario.isEmpty()) {
			throw new UsernameNotFoundException("Usuário [ " + username + " ] Não encontrado!");
			
		}
		
		
		return new DetalheUsuarioData(usuario);
	}

}





