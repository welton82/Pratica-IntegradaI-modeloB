package com.eug.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

//INJEÇÃO DE PERSISTENCIA HIBERNATE
@Entity
@Table(name = "local")
public class Local implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // identificador auto gerável
	private Integer idLocal;
	
	@Column(name="nomeLocal")
	private String nomeLocal;
	
	@Column(name="referenciaUnidade")
	private String referenciaUnidade;
	
	@OneToMany(mappedBy = "local", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Patrimonio> patrimonios = new ArrayList<>();
	
	
	
	public Local() {
	}
	public Local(Integer idLocal, String nomeLocal, String referenciaUnidade) {
		super();
		
		this.idLocal = idLocal;
		this.nomeLocal = nomeLocal;
		this.referenciaUnidade = referenciaUnidade;
	}
	
	
	
	
	
	public Integer getIdLocal() {
		return idLocal;
	}
	public void setIdLocal(Integer idLocal) {
		this.idLocal = idLocal;
	}
	public String getNomeLocal() {
		return nomeLocal;
	}
	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}
	public String getReferenciaUnidade() {
		return referenciaUnidade;
	}
	
	public void setReferenciaUnidade(String referenciaUnidade) {
		this.referenciaUnidade = referenciaUnidade;
	}
	
	public List<Patrimonio> getPatrimonios() {
		return patrimonios;
	}
	
	
	
	
	
}
