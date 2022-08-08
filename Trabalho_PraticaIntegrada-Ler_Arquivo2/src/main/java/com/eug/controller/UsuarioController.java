package com.eug.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eug.entities.Usuario;
import com.eug.exception.ResourceNotFoundException;
import com.eug.repository.UsuarioRepository;

@RequestMapping("/ucontroller/")
@RestController
public class UsuarioController {
	
	// auto gerenciado automaticamente pelo JPA
	@Autowired
	private UsuarioRepository usuarioRep;
	
	private final PasswordEncoder encoder;

	
	
	public UsuarioController(UsuarioRepository usuarioRep, PasswordEncoder encoder) {
		super();
		this.usuarioRep = usuarioRep;
		this.encoder = encoder;
	}

	// MÉTODO LISTAR
	@GetMapping("usuarios")
	public List<Usuario> usuarios() {
		return this.usuarioRep.findAll();
	}
	
	//CONSULTAR USUÁRIO a partir de uma ENTIDADE
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> consultarUsuario(@PathVariable Integer id){
		
		Usuario usuario = usuarioRep.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Usuário Não encontrado: " + id)
			);
		
		return ResponseEntity.ok(usuario);
	}
	
	//INSERIR USUARIO
	@PostMapping("/usuarios")
	public Usuario inserir(@RequestBody Usuario usuario) {
		
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		return usuarioRep.save(usuario);
	}
	
	
	
	//DELETAR USUARIO
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Map<String, Boolean>>excluir(@PathVariable Integer id){
		//apaga o usuário
		Usuario usuario = usuarioRep.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Usuário Não Existe No Banco de Dados: " + id)
			);
		usuarioRep.delete(usuario);
		
		
		//mostrará que foi apagado o usuário
		Map<String, Boolean>resposta = new HashMap<>();
		resposta.put("Usuário excluído: ", Boolean.TRUE);
		return ResponseEntity.ok(resposta);
		
	}
	
	
	//VALIDAR SENHA
	@GetMapping("/validarSenha")
	public ResponseEntity<Boolean>validarSenha(@RequestParam String login,
			@RequestParam String senha){
		
		
		Boolean valid = false;
		Optional<Usuario> usuario = usuarioRep.findByLogin(login);
		if(usuario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(valid);
		}
		
		Usuario usu = usuario.get();
		valid = encoder.matches(senha, usu.getSenha());
		
		HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED; 
		
		
		return ResponseEntity.status(status).body(valid);
	}
	
	
}



