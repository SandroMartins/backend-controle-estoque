package com.controle.estoque;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.controle.estoque.domain.Produto;
import com.controle.estoque.domain.TipoProduto;
import com.controle.estoque.domain.repositories.ProdutoRepository;
import com.controle.estoque.domain.repositories.TipoProdutoRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private TipoProdutoRepository tipoProdutoRepository;


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		TipoProduto tipoProd = new TipoProduto(null, "Eletrodoméstico");
		Produto prod = new Produto(null, "Geladeira", tipoProd, 2000.00, 5);
		
		tipoProd.getProdutos().addAll(Arrays.asList(prod));
		
		tipoProdutoRepository.saveAll(Arrays.asList(tipoProd));
		produtoRepository.saveAll(Arrays.asList(prod));
	}
}
