package com.pburgov.springdelivery.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table( name = "repartos" )
public class Reparto extends BaseEntity {

    @Column( name = "fecha" )
    @Temporal( TemporalType.DATE )
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    @JsonFormat( pattern = "dd-MM-yyyy" )
    @NotNull
    private Date fecha;

    @ManyToOne
    private Vehiculo vehiculo;

    @ManyToOne
    private Driver driver;

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true )
    @JoinColumn( name = "reparto_id" )
    private List <RepartoDetalle> detalles;

    public Reparto() {}

    public Date getFecha() {
        return fecha;
    }

    public void setFecha( Date fecha ) {
        this.fecha = fecha;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo( Vehiculo vehiculo ) {
        this.vehiculo = vehiculo;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver( Driver driver ) {
        this.driver = driver;
    }

    public List <RepartoDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles( List <RepartoDetalle> detalles ) {
        this.detalles = detalles;
    }

}
