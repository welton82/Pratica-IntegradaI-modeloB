package com.eug.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eug.service.PatrimonioService;

@RestController
public class PatrimonioArquivoController {
	
	@Autowired
	private PatrimonioService patriService;
	
	@RequestMapping(path="lerArquivo")
	public void setSetDataInDB() {
		patriService.savePatrimonioData();
	}
}
