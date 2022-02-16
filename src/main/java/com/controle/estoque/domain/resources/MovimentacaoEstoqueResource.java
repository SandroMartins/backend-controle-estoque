package com.controle.estoque.domain.resources;

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

import com.controle.estoque.domain.MovimentacaoEstoque;
import com.controle.estoque.domain.services.MovimentacaoEstoqueService;
import com.controle.estoque.dto.MovimentacaoEstoqueDTO;

@RestController
@RequestMapping(value = "/movimentacoes")
public class MovimentacaoEstoqueResource {
	
	@Autowired
	private MovimentacaoEstoqueService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Long id) {
		MovimentacaoEstoque movimentacaoEstoque = service.findById(id);
		
		return ResponseEntity.ok().body(movimentacaoEstoque);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody MovimentacaoEstoqueDTO movDTO) {
		System.out.println(movDTO.getDataVenda());
		MovimentacaoEstoque movimentacaoEstoque = service.fromDTO(movDTO);
		
		movimentacaoEstoque = service.insert(movimentacaoEstoque);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(movimentacaoEstoque.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
}
