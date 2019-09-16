package com.pburgov.springdelivery.dao.service;

import com.pburgov.springdelivery.dao.entity.Cliente;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {

	 List<Cliente> findAll();

	 Page<Cliente> findAll(Pageable pageable);

	 Cliente findOne(Long id);

	 void save(Cliente reparto);

	 void delete(Long id);

}
