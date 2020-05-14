package com.marcos.minhasFinancas.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.minhasFinancas.api.dto.UsuarioDTO;
import com.marcos.minhasFinancas.exception.ErroAutenticacaoException;
import com.marcos.minhasFinancas.exception.RegraNegocioException;
import com.marcos.minhasFinancas.model.entity.Usuario;
import com.marcos.minhasFinancas.service.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	

	public UsuarioResource(UsuarioService service) {
		super();
		this.service = service;
	}


	@PostMapping()
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto){
		Usuario usuario = new Usuario();
		usuario.setEmail(dto.getEmail());
		usuario.setNome(dto.getNome());
		usuario.setSenha(dto.getSenha());
		
		try {
			Usuario usuarioSalvo = service.salvarUsuario(usuario);
			
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	@PostMapping(value = "/autenticar")
	public ResponseEntity autenticacao(@RequestBody UsuarioDTO dto) {
		
		try {
			Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
			return new ResponseEntity(usuarioAutenticado, HttpStatus.OK);
		} catch (ErroAutenticacaoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
}
