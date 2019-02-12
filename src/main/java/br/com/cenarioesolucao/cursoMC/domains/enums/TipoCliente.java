package br.com.cenarioesolucao.cursoMC.domains.enums;

public enum TipoCliente {
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	/**
	 * Construtor
	 * @param cod
	 * @param descricao
	 */
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	/**
	 * Getters
	 * @return
	 */
	public int getCod() {
		return cod;
	}
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * Retorna um tipo enumerado ja instanciado a partir
	 * do codigo que foi passado
	 * @param cod
	 * @return
	 */
	public static TipoCliente toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
