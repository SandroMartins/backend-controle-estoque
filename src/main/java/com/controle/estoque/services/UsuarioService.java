package com.controle.estoque.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.controle.estoque.domain.Usuario;
import com.controle.estoque.dto.UsuarioDTO;
import com.controle.estoque.dto.UsuarioNovoDTO;
import com.controle.estoque.repositories.UsuarioRepository;
import com.controle.estoque.security.UserSS;
import com.controle.estoque.services.exceptions.AuthorizationException;
import com.controle.estoque.services.exceptions.EmailRepetidoException;
import com.controle.estoque.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	public Usuario findById(Long id) {
		UserSS user = authenticated();
		if(user == null || !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado");
		}
		
		Optional<Usuario> usuario = repository.findById(id);

		return usuario.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
	public Usuario insert(Usuario usuario) {
		validaEmailRepetido(usuario, true);
		
		return repository.save(usuario);
	}
	
	public Usuario update(Usuario usuario) {
		validaEmailRepetido(usuario, false);
		Usuario novoUsuario = findById(usuario.getId());
		updateData(novoUsuario, usuario);
		return repository.save(novoUsuario);
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Usuario fromDTO(UsuarioDTO usuarioDto) {		
		return new Usuario(null, usuarioDto.getNome(), usuarioDto.getEmail(), null);
	}
	
	public Usuario fromDTO(UsuarioNovoDTO usuarioDto) {		
		return new Usuario(null, usuarioDto.getNome(), usuarioDto.getEmail(), pe.encode(usuarioDto.getSenha()));
	}
	
	private void validaEmailRepetido(Usuario usuario, boolean insert) {
		Usuario aux = repository.findByEmail(usuario.getEmail());
		if((aux != null && insert == true) || (aux != null && insert == false && !aux.getId().equals(usuario.getId()))) {
			throw new EmailRepetidoException("E-mail já cadastrado na base de dados.");
		}
	}
	
	private void updateData(Usuario novoUsuario, Usuario usuario) {
		novoUsuario.setNome(usuario.getNome());
		novoUsuario.setEmail(usuario.getEmail());
	}
	
	public UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch(Exception e) {
			return null;
		}
	}
}
