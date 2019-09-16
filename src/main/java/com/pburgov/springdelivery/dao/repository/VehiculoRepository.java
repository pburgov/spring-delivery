package com.pburgov.springdelivery.dao.repository;

import com.pburgov.springdelivery.dao.entity.Vehiculo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends PagingAndSortingRepository<Vehiculo, Long> {
	

}

