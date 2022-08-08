package com.eug.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eug.entities.Local;
@Repository
public interface LocalRepository extends JpaRepository<Local, Integer>{

}
