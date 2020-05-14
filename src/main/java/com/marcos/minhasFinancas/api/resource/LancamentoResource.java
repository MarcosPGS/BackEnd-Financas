package com.marcos.minhasFinancas.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.minhasFinancas.api.dto.LancamentoDTO;
import com.marcos.minhasFinancas.exception.RegraNegocioException;
import com.marcos.minhasFinancas.model.entity.Lancamento;
import com.marcos.minhasFinancas.model.entity.Usuario;
import com.marcos.minhasFinancas.model.enums.StatusLancamento;
import com.marcos.minhasFinancas.model.enums.TipoLancamento;
import com.marcos.minhasFinancas.service.LancamentoService;
import com.marcos.minhasFinancas.service.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoService service;
	@Autowired
	private UsuarioService usuarioService;

	public LancamentoResource(LancamentoService service) {
		super();
		this.service = service;
	}
	
	@PostMapping()
	public ResponseEntity salvar(@RequestBody LancamentoDTO dto) {
		
		try {
			Lancamento entidade = converter(dto);
			service.salvar(entidade);
			return new ResponseEntity(entidade, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity atualizar(@PathVariable(value = "id") Long id, @RequestBody LancamentoDTO dto) {
		 return service.obterPorId(id).map(entity -> {
			 try {
				 Lancamento lancamento = converter(dto);
				 lancamento.setId(entity.getId());
				 service.atualizar(lancamento);
				 return ResponseEntity.ok(lancamento);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			 
		 }).orElseGet(() -> 
		 new ResponseEntity("Lancamento não encontrado na base de Dados.", HttpStatus.BAD_REQUEST ));
		
	}
	
	
	
	private Lancamento converter (LancamentoDTO dto) {
		Lancamento lancamento = new Lancamento();
		lancamento.setAno(dto.getAno());
		lancamento.setId(dto.getId());
		lancamento.setDescricao(dto.getDescricao());
		lancamento.setMes(dto.getMes());
		lancamento.setValor(dto.getValor());
		
		Usuario usuario = usuarioService.obterPorId(dto.getUsuarioID())
				.orElseThrow(() -> new RegraNegocioException("Usuário não encontrado para o ID informado."));
		lancamento.setUsuario(usuario);
		lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));
		lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus()));
		
		return lancamento;
	}

}
