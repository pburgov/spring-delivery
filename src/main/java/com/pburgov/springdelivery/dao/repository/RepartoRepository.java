package com.pburgov.springdelivery.dao.repository;

import com.pburgov.springdelivery.dao.entity.Reparto;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RepartoRepository extends JpaRepository<Reparto, Long> {

    @Query("select r from Reparto r where r.fecha=?1")
    List<Reparto> findByFecha(Date fecha);

    @Query("select r from Reparto r where r.fecha = :fecha")
    Page<Reparto> findByFecha(@Param("fecha") java.sql.Date fecha, Pageable pageable);

    @Query("select r from Reparto r join fetch r.vehiculo v join fetch r.driver u left join fetch r.detalles d join fetch d.cliente where r.id=?1")
    Reparto fetchByIdWithVehiculoWithDriverWithDetallesWithCliente(Long id);

}
