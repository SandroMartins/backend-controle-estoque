package com.controle.estoque.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.controle.estoque.domain.TipoProduto;
import com.controle.estoque.dto.TipoProdutoDTO;
import com.controle.estoque.services.TipoProdutoService;

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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody TipoProdutoDTO tipoProdutoDTO) {
		TipoProduto tipoProduto = service.fromDTO(tipoProdutoDTO);
		tipoProduto = service.insert(tipoProduto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tipoProduto.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody TipoProdutoDTO tipoProdutoDTO, @PathVariable Long id) {
		TipoProduto tipoProduto = service.fromDTO(tipoProdutoDTO);
		tipoProduto.setId(id);
		tipoProduto = service.update(tipoProduto);

		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
}
