package com.pburgov.springdelivery.dao.repository;

import com.pburgov.springdelivery.dao.entity.Cliente;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {



}
