package com.eug.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eug.entities.Patrimonio;
import com.eug.entities.Usuario;
import com.eug.exception.ResourceNotFoundException;
import com.eug.repository.PatrimonioRepository;

@RequestMapping("/pcontroller")
@RestController
public class PatrimonioController {
	
		@Autowired
		private PatrimonioRepository patrimonioRep;
	
		// MÉTODO LISTAR
		@GetMapping("patrimonios")
		public List<Patrimonio> patrimonios() {
			
			return patrimonioRep.findAll();
		}
	
		// CONSULTAR PATRIMONIO a partir de uma ENTIDADE
		@GetMapping("/patrimonios/{id}")
		public ResponseEntity<Patrimonio> consultarPatrimonio(@PathVariable Integer id) {
	
			Patrimonio patrimonio = patrimonioRep.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Patrimonio Não encontrado: " + id));
	
			return ResponseEntity.ok(patrimonio);
		}
	
		// INSERIR PATRIMONIO
		@PostMapping("/patrimonios")
		public Patrimonio inserir(@RequestBody Patrimonio patrimonio) {
			return patrimonioRep.save(patrimonio);
		}
	
		//INSERIR CSV
		@PostMapping("/lerCSV")
		public Patrimonio lerCSV() {
			Patrimonio patrimonio = new Patrimonio();
			
			String path = "C:\\Users\\welton\\Downloads\\PlanilhaTestesCVS-Pagina1.csv";
			
			try(BufferedReader bf = new BufferedReader(new FileReader(path))){
				
				//LER A PRIMEIRA LINHA(NOME DOS ATRIBUTOS)
				String line = bf.readLine();
				
				//LER A SEGUNDA LINHA(DADOS DOS ATRIBUTOS)
				line = bf.readLine();
				
				while(line != null) {
					String[] vetor = line.split(",");
					patrimonio.setIdTombamento(Integer.parseInt(vetor[0]));
					patrimonio.setDataLocacao(Date.valueOf(vetor[1]));
					patrimonio.setEstadoConservacao(Boolean.parseBoolean(vetor[2]));
					patrimonio.setIdTombamentoAnterior(Integer.parseInt(vetor[3]));
					patrimonio.setNomeClasse(vetor[4]);
					patrimonio.setNomeEspecie(vetor[5]);
					patrimonio.setNomeMarca(vetor[6]);
					patrimonio.setObservacao(vetor[7]);
					patrimonio.setValorAnual(Double.parseDouble(vetor[8]));
					patrimonio.setValorAquisicao(Double.parseDouble(vetor[9]));
					
					Usuario u = new Usuario(1,"Raquel Ferreira","jujuba_0147@outlook.com","0000");
					patrimonio.setUsuario(u);
					
					return patrimonioRep.save(patrimonio);
					
				}
				
				
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return patrimonioRep.save(patrimonio);
		}
		
		
		// DELETAR USUARIO
		@DeleteMapping("/patrimonios/{id}")
		public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Integer id) {
			// apaga o patrimonio
			Patrimonio patrimonio = patrimonioRep.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Patrimonio Não Existe No Banco de Dados: " + id));
			patrimonioRep.delete(patrimonio);
	
			// mostrará que foi apagado o usuário
			Map<String, Boolean> resposta = new HashMap<>();
			resposta.put("Patrimonio excluído: ", Boolean.TRUE);
			return ResponseEntity.ok(resposta);
	
		}
		
		//ALTERAR PATRIMONIO
		@PutMapping("/patrimonios/{id}")
		public ResponseEntity<Patrimonio>alterar(@PathVariable Integer id, @RequestBody Patrimonio patrimonio){
			
			Patrimonio patri = patrimonioRep.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Patrimonio Não encontrado: " + id));
			
			patri.setIdTombamento(patrimonio.getIdTombamento());
			patri.setDataLocacao(patrimonio.getDataLocacao());
			patri.setEstadoConservacao(patrimonio.getEstadoConservacao());
			patri.setNomeClasse(patrimonio.getNomeClasse());
			patri.setNomeEspecie(patrimonio.getNomeEspecie());
			patri.setNomeMarca(patrimonio.getNomeMarca());
			patri.setUsuario(patrimonio.getUsuario());
			patri.setValorAnual(patrimonio.getValorAnual());
			patri.setValorAquisicao(patrimonio.getValorAquisicao());
			patri.setLocal(patrimonio.getLocal());
			
			Patrimonio atualizado =  patrimonioRep.save(patri);
			return ResponseEntity.ok(atualizado);	
		}
}