package com.algaworks.cobranca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.cobranca.enumeration.TituloStatusEnum;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.TituloRepository;

@Service
public class TituloService {

	@Autowired
	private TituloRepository repository;

	public void salvar(Titulo titulo) {
		try {
			this.repository.save(titulo);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}

	public void excluir(Long id) {
		this.repository.delete(id);
	}

	public String receber(Long id) {
		Titulo titulo = this.repository.findOne(id);
		titulo.setStatus(TituloStatusEnum.RECEBIDO);
		this.repository.save(titulo);
		return TituloStatusEnum.RECEBIDO.getDescricao();
	}

}
