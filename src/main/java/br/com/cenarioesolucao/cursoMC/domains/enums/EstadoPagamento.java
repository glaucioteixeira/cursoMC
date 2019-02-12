package br.com.cenarioesolucao.cursoMC.domains.enums;

public enum EstadoPagamento {
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	/**
	 * Construtor
	 * @param cod
	 * @param descricao
	 */
	private EstadoPagamento(int cod, String descricao) {
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
	public static EstadoPagamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
