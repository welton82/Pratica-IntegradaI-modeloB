package com.ueg.security;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eug.entities.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ueg.data.DetalheUsuarioData;

public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter{

	public static final int TOKEN_EXPIRACAO = 600_000;
	public static final String TOKEN_SENHA = "17452a43-774d-443a-b436-1c2234cb83b7";
	
	private final AuthenticationManager authenticationManager;

	public JWTAutenticarFilter(AuthenticationManager authenticationManager) {
		
		this.authenticationManager = authenticationManager;
	}
	
	
	//LOGIN DO USUARIO
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, 
			HttpServletResponse response) throws AuthenticationException {
		
		try {
			Usuario usuario = new ObjectMapper()
					.readValue(request.getInputStream(), Usuario.class);
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					usuario.getLogin(), 
					usuario.getSenha(),
					new ArrayList<>()
					));
								
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Falta autenticar Usuário: ", e);
		}
		
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
											HttpServletResponse response,
											FilterChain chain,
											Authentication authResult) throws IOException, ServletException {
		
		
		DetalheUsuarioData usuarioData = (DetalheUsuarioData) authResult.getPrincipal();
		
		//CRIAR TOKEN
		String token = JWT.create()
				.withSubject(usuarioData.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
				.sign(Algorithm.HMAC512(TOKEN_SENHA));
		
		response.getWriter().write(token);
		response.getWriter().flush();//gerar token
	}
}









