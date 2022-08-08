package com.eug.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eug.entities.Local;
import com.eug.entities.Patrimonio;
import com.eug.exception.ResourceNotFoundException;
import com.eug.repository.LocalRepository;

@RequestMapping("/lcontroller/")
@RestController
public class LocalController {
	private LocalRepository localRep;
	
	public LocalController(LocalRepository local) {
		this.localRep = local;
	}
	
	//INSERIR LOCAL
	@PostMapping("/local")
	public Local inserirLocal(@RequestBody Local local) {
		return localRep.save(local);	
	}
	
	//LISTAR LOCAL
	@GetMapping("locais")
	public List<Local>locais(){
		
		return this.localRep.findAll();
		
	}
	
	//CONSULTAR LOCAL
	@GetMapping("/locais/{id}")
	public ResponseEntity<Local>consultarLocal(@PathVariable Integer id){
		
		//usando função lambda
		Local local = localRep.findById(id).orElseThrow( 
				() -> new ResourceNotFoundException("Local Não Encontrado: " + id)
				);
		
		return ResponseEntity.ok(local);
	}
	
	
	
//	//ALTERAR LOCAL
	@PutMapping("/locais/{id}")
	public ResponseEntity<Local>alterarLocal(@PathVariable Integer id, @RequestBody Local local){
		Local loc = localRep.findById(id).orElseThrow( () -> new ResourceNotFoundException("Local Não Existe: " + id));
		
		loc.setIdLocal(local.getIdLocal());
		loc.setNomeLocal(local.getNomeLocal());
		loc.setReferenciaUnidade(local.getReferenciaUnidade());
		
		Local atualizado = localRep.save(loc);
		return ResponseEntity.ok(atualizado);
	}
}
