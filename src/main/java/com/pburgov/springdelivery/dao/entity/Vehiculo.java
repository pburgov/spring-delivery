package com.pburgov.springdelivery.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vehiculos")
@JsonIgnoreProperties(value = {"id", "marca", "modelo", "pesoMax", "tara", "bloqueado"})
public class Vehiculo extends BaseEntity{

    @Column(name = "matricula", length = 9, unique = true)
    private String matricula;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "peso_max")
    private Long pesoMax;

    @Column(name = "tara")
    private Long tara;

    @Column(name = "bloqueado")
    private Boolean bloqueado;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Long getPesoMax() {
        return pesoMax;
    }

    public void setPesoMax(Long pesoMax) {
        this.pesoMax = pesoMax;
    }

    public Long getTara() {
        return tara;
    }

    public void setTara(Long tara) {
        this.tara = tara;
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    private static final long serialVersionUID = 1L;

}
