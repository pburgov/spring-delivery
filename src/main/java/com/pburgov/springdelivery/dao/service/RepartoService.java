package com.pburgov.springdelivery.dao.service;

import com.pburgov.springdelivery.dao.entity.Reparto;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RepartoService {

	 List<Reparto> findAll();

	 Page<Reparto> findAll(Pageable pageable);
	
	 List<Reparto> findByFecha(Date fecha);
	
	 Page<Reparto> findByFecha(java.sql.Date fecha, Pageable pageable);

	 Reparto findOne(Long id);

	 void save(Reparto reparto);

	 void delete(Long id);
	
	 Reparto fetchByIdWithVehiculoWithDriverWithDetallesWithCliente(Long id);

}
