package com.pburgov.springdelivery.dao.service;

import com.pburgov.springdelivery.dao.entity.Vehiculo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehiculoService {

	 List<Vehiculo> findAll();

	 Page<Vehiculo> findAll(Pageable pageable);

	 Vehiculo findOne(Long id);

	 void save(Vehiculo vehiculo);

	 void delete(Long id);

}
