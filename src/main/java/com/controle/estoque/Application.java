package com.controle.estoque;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.controle.estoque.domain.TipoProduto;
import com.controle.estoque.domain.Usuario;
import com.controle.estoque.repositories.TipoProdutoRepository;
import com.controle.estoque.repositories.UsuarioRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	private TipoProdutoRepository tipoProdutoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Usuario usuario = new Usuario(null, "Administrador", "admin@admin.com", pe.encode("admin"));
		usuarioRepository.saveAll(Arrays.asList(usuario));
		
		TipoProduto tipoProd1 = new TipoProduto(null, "Eletrônico");
		TipoProduto tipoProd2 = new TipoProduto(null, "Eletrodoméstico");
		TipoProduto tipoProd3 = new TipoProduto(null, "Móvel");
		
		tipoProdutoRepository.saveAll(Arrays.asList(tipoProd1, tipoProd2, tipoProd3));
		
	}
}
