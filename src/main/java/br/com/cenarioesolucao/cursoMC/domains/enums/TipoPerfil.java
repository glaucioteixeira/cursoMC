package br.com.cenarioesolucao.cursoMC.domains.enums;

public enum TipoPerfil {
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int cod;
	private String descricao;
	
	/**
	 * Construtor
	 * @param cod
	 * @param descricao
	 */
	private TipoPerfil(int cod, String descricao) {
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
	public static TipoPerfil toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (TipoPerfil x : TipoPerfil.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
