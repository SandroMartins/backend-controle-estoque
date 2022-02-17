package com.controle.estoque.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.controle.estoque.domain.MovimentacaoEstoque;
import com.controle.estoque.dto.MovimentacaoEstoqueDTO;
import com.controle.estoque.services.MovimentacaoEstoqueService;

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
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<MovimentacaoEstoqueDTO>> findDistinctByProduto(
			@RequestParam(value="produto", defaultValue="") Long produto, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="id") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<MovimentacaoEstoque> list = service.findDistinctByProduto(produto, page, linesPerPage, orderBy, direction);
		Page<MovimentacaoEstoqueDTO> listDto = list.map(obj -> new MovimentacaoEstoqueDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody MovimentacaoEstoqueDTO movDTO) {
		MovimentacaoEstoque movimentacaoEstoque = service.fromDTO(movDTO);
		
		movimentacaoEstoque = service.insert(movimentacaoEstoque);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(movimentacaoEstoque.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
}
