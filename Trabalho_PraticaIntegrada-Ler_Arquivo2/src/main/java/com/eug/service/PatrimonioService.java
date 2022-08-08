package com.eug.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eug.entities.Patrimonio;
import com.eug.entities.Usuario;
import com.eug.repository.PatrimonioArquivo;

@Service
public class PatrimonioService {

	@Autowired
	private PatrimonioArquivo patriArquivo;

	public void savePatrimonioData() {

		String path = "C:\\Users\\welton\\testesCSV\\PlanilhaTestesCVS.csv";

		// LER A PRIMEIRA LINHA(NOME DOS ATRIBUTOS)
		String line = "";

		try (BufferedReader bf = new BufferedReader(new FileReader(path))) {

			// LER A PRIMEIRA LINHA(NOME DOS ATRIBUTOS)
			line = bf.readLine();

			// LER A SEGUNDA LINHA(DADOS DOS ATRIBUTOS)
			line = bf.readLine();

			while (line != null) {
				String[] vetor = line.split(",");

				Patrimonio patrimonio = new Patrimonio();

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

				/*
				 * Integer id = Integer.parseInt(vetor[10]);
				 * patrimonio.getUsuario().setIdUsuario(id);
				 */

				Usuario u = new Usuario(2, "Raquel Ferreira", "jujuba_0147@outlook.com", "0000");
				patrimonio.setUsuario(u);

				patriArquivo.save(patrimonio);
				line = bf.readLine();

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
