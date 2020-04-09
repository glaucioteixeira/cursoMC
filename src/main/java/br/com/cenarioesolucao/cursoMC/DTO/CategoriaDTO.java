package br.com.cenarioesolucao.cursoMC.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.cenarioesolucao.cursoMC.domains.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos
	 */
	@Getter @Setter private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 5, max = 80, message = "O tamanho deste campo deve estar entre 5 e 80 caracteres!")
	@Getter @Setter private String descricao;
	
	/**
	 * Construtor
	 * @param categoria
	 */
	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.descricao = categoria.getDescricao();
	}

}
