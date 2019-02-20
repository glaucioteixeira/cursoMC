package br.com.cenarioesolucao.cursoMC.DTO;

import java.io.Serializable;

import br.com.cenarioesolucao.cursoMC.domains.Estado;

public class EstadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos
	 */
	private Integer id;
	private String nome;
	
	/**
	 * Construtores
	 * @param id
	 * @param nome
	 */
	public EstadoDTO(Estado estado) {
		super();
		this.id = estado.getId();
		this.nome = estado.getNome();
	}
	public EstadoDTO() {
		
	}
	
	/**
	 * Getters and Setters
	 * @return
	 */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
