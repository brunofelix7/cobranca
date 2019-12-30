package com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.cobranca.enumeration.TituloStatusEnum;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.TituloRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	private static final String VIEW_CADASTRO = "titulo/cadastro";
	private static final String VIEW_LISTAGEM = "titulo/listagem";

	@Autowired
	private TituloRepository repository;

	/**
	 * Carrega a página de cadastro de título
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/novo")
	public ModelAndView novo() {
		return new ModelAndView(VIEW_CADASTRO);
	}

	/**
	 * Cadastra um novo título
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo) {
		repository.save(titulo);
		ModelAndView mv = new ModelAndView(VIEW_CADASTRO);
		mv.addObject("mensagem", "Salvo com sucesso!");
		return mv;
	}

	/**
	 * Carrega a página de lsitagem de títulos
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String pesquisar() {
		return VIEW_LISTAGEM;
	}

	/**
	 * Retorna todos os meus enuns num array dinamicamente
	 */
	@ModelAttribute("todosStatusTitulo") // Define o nome que eu quero chamar lá na View com o thymeleaf
	public List<TituloStatusEnum> todosStatusTitulo() {
		return Arrays.asList(TituloStatusEnum.values());
	}

}
