package com.pburgov.springdelivery.dao.service;

import com.pburgov.springdelivery.dao.entity.Vehiculo;
import com.pburgov.springdelivery.dao.repository.VehiculoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class VehiculoServiceImpl implements VehiculoService {

	@Autowired
	private VehiculoRepository vehiculoRepository;

	@Override
	public List<Vehiculo> findAll() {
		return (List<Vehiculo>) vehiculoRepository.findAll();
	}

	@Override
	public Page<Vehiculo> findAll(Pageable pageable) {
		return vehiculoRepository.findAll(pageable);
	}

	@Override
	public Vehiculo findOne(Long id) {
		return vehiculoRepository.findById(id).orElse(null);
	}

	@Override
	public void save(Vehiculo vehiculo) {
		vehiculoRepository.save(vehiculo);

	}

	@Override
	public void delete(Long id) {
		vehiculoRepository.deleteById(id);

	}

}
