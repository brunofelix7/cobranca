package com.algaworks.cobranca.enumeration;

public enum TituloStatusEnum {
	
	RECEBIDO("Recebido"),
	PENDENTE("Pendente");
	
	private String descricao;
	
	TituloStatusEnum(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
