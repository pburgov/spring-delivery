package com.pburgov.springdelivery.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="clientes")
@JsonIgnoreProperties(value = {"id", "rutaId"})
public class Cliente extends BaseEntity{

	@NotNull
	@Column(name="codigo")
	private String codigo;

	@NotNull
	@Column(name="nombre")
	private String nombre;

	@NotNull
	@Column(name = "ruta_id")
	private Long rutaId;


	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getRutaId() {
		return rutaId;
	}

	public void setRutaId(Long rutaId) {
		this.rutaId = rutaId;
	}
	
	
	

}
