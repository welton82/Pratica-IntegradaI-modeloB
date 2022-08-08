package com.eug.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eug.entities.Patrimonio;

@Repository
public interface PatrimonioArquivo extends CrudRepository<Patrimonio, Integer>{
	
}
