package com.controle.estoque.domain.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.controle.estoque.domain.TipoProduto;
import com.controle.estoque.domain.services.TipoProdutoService;

@RestController
@RequestMapping(value = "/tiposProduto")
public class TipoProdutoResource {
	
	@Autowired
	private TipoProdutoService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Long id) {
		TipoProduto tipoProduto = service.findById(id);
		
		return ResponseEntity.ok().body(tipoProduto);
	}
}
