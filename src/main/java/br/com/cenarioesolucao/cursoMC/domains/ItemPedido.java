package br.com.cenarioesolucao.cursoMC.domains;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos
	 */
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	/**
	 * Construtores
	 * @param id
	 * @param desconto
	 * @param quantidade
	 * @param preco
	 */
	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	public ItemPedido() {
		
	}
	
	/**
	 * Métodos
	 * Obs.: Nome do metodo precisa começar com o prefixo get para ser serializado pelo Json.
	 */
	public double getSubTotal() {
		return (preco - desconto) * quantidade;
	}
	
	/**
	 * Getters and Setters
	 * @return
	 */
	@JsonIgnore
	public Pedido getPedido() {
		return this.id.getPedido();
	}
	public void setPedido(Pedido pedido) {
		this.id.setPedido(pedido);
	}
	
	public Produto getProduto() {
		return this.id.getProduto();
	}
	public void setProduto(Produto produto) {
		this.id.setProduto(produto);
	}
	
	
	public ItemPedidoPK getId() {
		return id;
	}
	public void setId(ItemPedidoPK id) {
		this.id = id;
	}
	public Double getDesconto() {
		return desconto;
	}
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	/**
	 * Hash and Equals
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	/**
	 * toString
	 */
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		
		StringBuilder builder = new StringBuilder();
		builder.append(getProduto().getNome());
		builder.append(", Quant.: ");
		builder.append(getQuantidade());
		builder.append(", Preço Unitário: ");
		builder.append(nf.format(getPreco()));
		builder.append(", SubTotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		
		return builder.toString();
	}

	
}
