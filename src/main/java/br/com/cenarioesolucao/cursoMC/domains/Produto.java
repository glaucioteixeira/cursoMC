package br.com.cenarioesolucao.cursoMC.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @ToString @EqualsAndHashCode(of={"id"})
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer id;
	@Getter @Setter private String nome;
	@Getter @Setter private Double preco;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA", 
		joinColumns = @JoinColumn(name = "produto_id"), 
		inverseJoinColumns = @JoinColumn(name = "categoria_id")
	)
	@Getter @Setter private List<Categoria> categorias = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.produto")
	@Getter @Setter private Set<ItemPedido> itensPedidos = new HashSet<>();
	
	/**
	 * Construtores
	 * @param id
	 * @param nome
	 * @param preco
	 */
	public Produto(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
//	public Produto() {
//		
//	}
	
	/**
	 * Metodos
	 */
	@JsonIgnore
	public List<Pedido> getPedidos() {
		List<Pedido> lista = new ArrayList<>();
		for (ItemPedido x : itensPedidos) {
			lista.add(x.getPedido());
		}
		
		return lista;
	}
	
	/**
	 * Getters and Setters
	 * @return
	 */
//	public List<Categoria> getCategorias() {
//		return categorias;
//	}
//	public void setCategorias(List<Categoria> categorias) {
//		this.categorias = categorias;
//	}
//	
//	public Set<ItemPedido> getItensPedidos() {
//		return itensPedidos;
//	}
//	public void setItensPedidos(Set<ItemPedido> itensPedidos) {
//		this.itensPedidos = itensPedidos;
//	}
	

//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
//	public String getNome() {
//		return nome;
//	}
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//	public Double getPreco() {
//		return preco;
//	}
//	public void setPreco(Double preco) {
//		this.preco = preco;
//	}
	
//	/**
//	 * Hash and Equals
//	 */
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Produto other = (Produto) obj;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		return true;
//	}
	
	
	

}
