package com.algaworks.cobranca.enumeration;

public enum TituloStatusEnum {
	
	PENDENTE("Pendente"),
	RECEBIDO("Recebido");
	
	private String descricao;
	
	TituloStatusEnum(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
