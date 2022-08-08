package com.eug.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//INJEÇÃO DE PERSISTENCIA HIBERNATE
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{

	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // identificador auto gerável
	private Integer idUsuario;

	@Column(name = "nomeUsuario")
	private String nomeUsuario;

	@Column(unique = true)
	private String login;
	
	
	@Column(name = "senha")
	private String senha;
	
	//@OneToMany(mappedBy = "usuario")	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Patrimonio>patrimonios = new ArrayList<>();
	
	
	public Usuario() {
	}

	public Usuario(Integer idUsuario, String nomeUsuario, String login, String senha) {
		super();
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.login = login;
		this.senha = senha;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public List<Patrimonio> getPatrimonios() {
		return patrimonios;
	}

	public void adicionaPatrimonios(Patrimonio p) {
		patrimonios.add(p);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(idUsuario, other.idUsuario);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("\nId Usuário: ");
		s.append(idUsuario);
		s.append("\nNome do Usuário: ");
		s.append(nomeUsuario);
		s.append("\nLogin: ");
		s.append(login);
		s.append("\nSenha: ");
		s.append(senha);
		s.append("\nPatrimonio: \n");
		s.append(getPatrimonios());
		return s.toString();
	}


		
//	{
//		"idUsuario": 2,
//		"nomeUsuario": "Raquel Ferreira",
//		"login": "jujuba_0147@outlook.com",
//		"senha": "0000"
//	}

	
	

}
