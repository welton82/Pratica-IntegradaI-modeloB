package com.ueg.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.eug.entities.Usuario;

public class DetalheUsuarioData implements UserDetails{
	
	private Optional<Usuario> usuario;
	
	
	
public DetalheUsuarioData(Optional<Usuario> usuario) {
		super();
		this.usuario = usuario;
	}


//PERMISSÕES DO USUÁRIO
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return usuario.orElse(new Usuario()).getSenha();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usuario.orElse(new Usuario()).getLogin();
	}
//VÊ SE A CONTA NÃO ESTÁ EXPIRADA
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
//VÊ SE A CONTA NÃO ESTÁ BLOQUEADA 
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
//VÊ SE AS CREDENCIAIS NÃO STÃO EXPIRADAS
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

//SE USUÁRIO ESTÁ ATIVO
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
