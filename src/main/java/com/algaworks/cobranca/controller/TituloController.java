package com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.cobranca.enumeration.TituloStatusEnum;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.filter.TituloFilter;
import com.algaworks.cobranca.service.TituloService;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	private static final String VIEW_CADASTRO = "titulo/cadastro";
	private static final String VIEW_LISTAGEM = "titulo/listagem";

	@Autowired
	private TituloService service;

	/**
	 * Carrega a página de cadastro de titulo
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/novo")
	public ModelAndView novo() {
		return new ModelAndView(VIEW_CADASTRO).addObject(new Titulo());
	}

	/**
	 * Processa o formulário da página de cadastro de titulo
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return VIEW_CADASTRO;
		}
		try {
			this.service.salvar(titulo);
			attributes.addFlashAttribute("mensagem", "Salvo com sucesso!");
			return "redirect:/titulos/novo";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return VIEW_CADASTRO;
		}
	}

	/**
	 * Carrega a página de lsitagem de titulos e pesquisa
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pesquisar(@ModelAttribute("filtro") TituloFilter filtro) { //@RequestParam(defaultValue = "%") String descricao
		List<Titulo> titulos = this.service.filtrar(filtro);
		ModelAndView mv = new ModelAndView(VIEW_LISTAGEM);
		mv.addObject("titulos", titulos);
		return mv;
	}

	/**
	 * Carrega a página de atualização de titulo
	 */
	@RequestMapping(method = RequestMethod.GET, path = "{id}")
	public ModelAndView editar(@PathVariable("id") Titulo titulo) {
		// Titulo titulo = this.repository.findOne(id);
		return new ModelAndView(VIEW_CADASTRO).addObject(titulo);
	}

	/**
	 * Remove um titulo
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = "{id}")
	public String remover(@PathVariable Long id, RedirectAttributes attributes) {
		this.service.excluir(id);
		attributes.addFlashAttribute("mensagem", "Titulo excluído com sucesso!");
		return "redirect:/titulos";
	}
	
	/**
	 * Altera o status do titulo via AJAX
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/{id}/receber")
	public @ResponseBody String receber(@PathVariable("id") Long id) {
		return this.service.receber(id);
	}
	
	/**
	 * Retorna todos os meus enuns num array dinamicamente
	 */
	@ModelAttribute("todosStatusTitulo") // Define o nome que eu quero chamar lá na View com o thymeleaf
	public List<TituloStatusEnum> todosStatusTitulo() {
		return Arrays.asList(TituloStatusEnum.values());
	}

}
