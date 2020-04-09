package br.com.cenarioesolucao.cursoMC.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Hashcode and equals, construtor padrao e toString 
 * anotados na classe
 */

@Entity
@NoArgsConstructor @ToString @EqualsAndHashCode(of={"id"})
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Atributos, PK, Getters and Setters estão implementados com as anotações abaixo
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer id;
	@Getter @Setter private String descricao;
	
	/**
	 * Construtor
	 * @param id
	 * @param descricao
	 */
	public Categoria(Integer id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	/**
	 * Mapeamentos - Cardinalidades
	 */
	@ManyToMany(mappedBy = "categorias")
	@Getter @Setter private List<Produto> produtos = new ArrayList<>();
}
