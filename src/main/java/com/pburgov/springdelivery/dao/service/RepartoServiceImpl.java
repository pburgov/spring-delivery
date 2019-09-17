package com.pburgov.springdelivery.dao.service;

import com.pburgov.springdelivery.dao.entity.Reparto;
import com.pburgov.springdelivery.dao.repository.RepartoRepository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RepartoServiceImpl implements RepartoService {

    private RepartoRepository repartoRepository;


    @Autowired
    public RepartoServiceImpl(RepartoRepository repartoRepository) {
        this.repartoRepository = repartoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reparto> findAll() {
        return (List<Reparto>) repartoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Reparto> findAll(Pageable pageable) {
        return repartoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reparto> findByFecha(Date fecha) {

        System.out.println(fecha);
        return repartoRepository.findByFecha(fecha);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Reparto> findByFecha(java.sql.Date fecha, Pageable pageable) {
        return repartoRepository.findByFecha(fecha, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Reparto findOne(Long id) {
        return repartoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Reparto reparto) {
        repartoRepository.save(reparto);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        repartoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Reparto fetchByIdWithVehiculoWithDriverWithDetallesWithCliente(Long id) {

        return repartoRepository.fetchByIdWithVehiculoWithDriverWithDetallesWithCliente(id);
    }


}
