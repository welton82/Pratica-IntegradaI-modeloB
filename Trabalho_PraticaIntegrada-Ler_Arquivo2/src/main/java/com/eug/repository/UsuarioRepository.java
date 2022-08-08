package com.eug.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eug.entities.Usuario;
@Repository //JPA FARÁ A PERSISTENCIA AUTOMATIZADA
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	//BUSCA DO USUARIO POR LOGIN
	 public Optional<Usuario> findByLogin(String login);
	 
}
