package com.vendasup.sistema.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vendasup.sistema.models.Estado;
import com.vendasup.sistema.repository.EstadoRepository;

@RestController
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@GetMapping("/cadastroEstado")
	public ModelAndView cadastrarEstado(Estado estado) {
		ModelAndView mv = new ModelAndView("admin/estado/cadastro");
		mv.addObject("estado", estado);
		return mv;
	}
	
	@GetMapping("/listarEstado")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("/admin/estado/lista");
		mv.addObject("listaEstados", estadoRepository.findAll());
		return mv;
	}
	
	@GetMapping("/editarEstado/{id}")
	public ModelAndView editarEstado(@PathVariable("id") Long id ) {
		Optional<Estado> estado = estadoRepository.findById(id);
		return cadastrarEstado(estado.get());
		
	}
	
	@GetMapping("/removerEstado/{id}")
	public ModelAndView removerEstado(@PathVariable("id") Long id ) {
		Optional<Estado> estado = estadoRepository.findById(id);
		estadoRepository.delete(estado.get());
		return listar();
		
	}
	
	@PostMapping("/salvarEstado")
	public ModelAndView salvar(Estado estado, BindingResult result) {
		if (result.hasErrors()) {
			return cadastrarEstado(estado);
		}
		estadoRepository.saveAndFlush(estado);
		return cadastrarEstado(new Estado());
		
	}
	
	
	

}
