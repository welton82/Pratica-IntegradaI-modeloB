package com.eug.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patrimonio")
public class Patrimonio implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idTombamento;
	
	@Column(name = "idTombamentoAnterior")
	private Integer idTombamentoAnterior;
	
	@Column(name = "valorAquisicao")
	private Double valorAquisicao;
	
	@Column(name = "valorAnual")
	private Double valorAnual;
	
	@Column(name = "estadoConservacao")
	private Boolean estadoConservacao;
	
	@Column(name = "dataLocacao")
	private Date dataLocacao;
	
	@Column(name = "observacao")
	private String observacao;
	
	@Column(name = "nomeClasse")
	private String nomeClasse;
	
	@Column(name = "nomeMarca")
	private String nomeMarca;
	
	@Column(name = "nomeEspecie")
	private String nomeEspecie;
	
	//@ManyToOne
	@JoinColumn(name="usuario_id")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Usuario usuario;
	
	@JoinColumn(name="local_id")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Local local;
	
	
	public Patrimonio() {
	}

	public Patrimonio(Integer idTombamento, Integer idTombamentoAnterior, Double valorAquisicao, Double valorAnual,
			Boolean estadoConservacao, Date dataLocacao, String observacao, String nomeClasse, String nomeMarca,
			String nomeEspecie,Usuario usuario) {
		super();
		this.idTombamento = idTombamento;
		this.idTombamentoAnterior = idTombamentoAnterior;
		this.valorAquisicao = valorAquisicao;
		this.valorAnual = valorAnual;
		this.estadoConservacao = estadoConservacao;
		this.dataLocacao = dataLocacao;
		this.observacao = observacao;
		this.nomeClasse = nomeClasse;
		this.nomeMarca = nomeMarca;
		this.nomeEspecie = nomeEspecie;
		this.usuario = usuario;
	}

	public Integer getIdTombamento() {
		return idTombamento;
	}

	public void setIdTombamento(Integer idTombamento) {
		this.idTombamento = idTombamento;
	}

	public Integer getIdTombamentoAnterior() {
		return idTombamentoAnterior;
	}

	public void setIdTombamentoAnterior(Integer idTombamentoAnterior) {
		this.idTombamentoAnterior = idTombamentoAnterior;
	}

	public Double getValorAquisicao() {
		return valorAquisicao;
	}

	public void setValorAquisicao(Double valorAquisicao) {
		this.valorAquisicao = valorAquisicao;
	}

	public Double getValorAnual() {
		return valorAnual;
	}

	public void setValorAnual(Double valorAnual) {
		this.valorAnual = valorAnual;
	}

	public Boolean getEstadoConservacao() {
		return estadoConservacao;
	}

	public void setEstadoConservacao(Boolean estadoConservacao) {
		this.estadoConservacao = estadoConservacao;
	}

	public Date getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getNomeClasse() {
		return nomeClasse;
	}

	public void setNomeClasse(String nomeClasse) {
		this.nomeClasse = nomeClasse;
	}

	public String getNomeMarca() {
		return nomeMarca;
	}

	public void setNomeMarca(String nomeMarca) {
		this.nomeMarca = nomeMarca;
	}

	public String getNomeEspecie() {
		return nomeEspecie;
	}

	public void setNomeEspecie(String nomeEspecie) {
		this.nomeEspecie = nomeEspecie;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTombamento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patrimonio other = (Patrimonio) obj;
		return Objects.equals(idTombamento, other.idTombamento);
	}

	@Override
	public String toString() {
		
		StringBuilder s = new StringBuilder();
		
		s.append("\nIdTombamento: ");
		s.append(idTombamento);
		s.append("\nIdTombamentoAnterior: ");
		s.append(idTombamentoAnterior);
		s.append("\nValorAquisicao: ");
		s.append(valorAquisicao);
		s.append("\nValorAnual: ");
		s.append(valorAnual);
		s.append("\nEstadoConservacao: ");
		s.append(estadoConservacao);
		s.append("\nDataLocacao: ");
		s.append(dataLocacao);
		s.append("\nObservacao: ");
		s.append(observacao);
		s.append("\nNomeClasse: ");
		s.append(nomeClasse);
		s.append("\nNomeMarca: ");
		s.append(nomeMarca);
		s.append("\nNome Especie: ");
		s.append(nomeEspecie);
		s.append("\nId: Usuário: ");
		s.append(usuario.getIdUsuario());
		s.append("\nNome do Usuário: ");
		s.append(usuario.getNomeUsuario());
		
		return s.toString();
	}
	
	
	
	
	
	
}
