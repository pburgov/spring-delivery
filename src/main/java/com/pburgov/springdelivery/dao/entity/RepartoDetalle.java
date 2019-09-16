package com.pburgov.springdelivery.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "repartos_detalle")
public class RepartoDetalle extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "note")
    private String note;

    @Column(name = "entrega")
    private String entrega;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "peso_entrega")
    private Double pesoEntrega;

    @Column(name = "importe_entrega")
    private Double importeEntrega;

    @Column(name = "coste_entrega")
    private Double costeEntrega;

    @Column(name = "beneficio_entrega")
    private Double beneficioEntrega;

    @Column(name = "descargado")
    private Boolean descargado;

    @Column(name = "comment")
    private String comment;

    @Column(name = "comment_back")
    private String commentBack;

    @Column(name = "lat")
    private Double latitude;

    @Column(name = "lng")
    private Double longitude;

    @Column(name = "hora_descarga")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date horaDescarga;

    @Column(name = "fotos")
    private int fotos;

    @Column(name = "plasticos_entregados")
    private int plasticosEntregados;

    @Column(name = "plasticos_recogidos")
    private int plasticosRecogidos;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getPesoEntrega() {
        return pesoEntrega;
    }

    public void setPesoEntrega(Double pesoEntrega) {
        this.pesoEntrega = pesoEntrega;
    }

    public Double getImporteEntrega() {
        return importeEntrega;
    }

    public void setImporteEntrega(Double importeEntrega) {
        this.importeEntrega = importeEntrega;
    }

    public Double getCosteEntrega() {
        return costeEntrega;
    }

    public void setCosteEntrega(Double costeEntrega) {
        this.costeEntrega = costeEntrega;
    }

    public Double getBeneficioEntrega() {
        return beneficioEntrega;
    }

    public void setBeneficioEntrega(Double beneficioEntrega) {
        this.beneficioEntrega = beneficioEntrega;
    }

    public Boolean getDescargado() {
        return descargado;
    }

    public void setDescargado(Boolean descargado) {
        this.descargado = descargado;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentBack() {
        return commentBack;
    }

    public void setCommentBack(String commentBack) {
        this.commentBack = commentBack;
    }

    public Double getLatitude() {
//		if (latitude == null) {
//			return 0.0;
//		}
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
//		if (longitude == null) {
//			return 0.0;
//		}
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getHoraDescarga() {
        return horaDescarga;
    }

    public void setHoraDescarga(Date horaDescarga) {
        this.horaDescarga = horaDescarga;
    }

    public int getFotos() {
        return fotos;
    }

    public void setFotos(int fotos) {
        this.fotos = fotos;
    }

    public int getPlasticosEntregados() {
        return plasticosEntregados;
    }

    public void setPlasticosEntregados(int plasticosEntregados) {
        this.plasticosEntregados = plasticosEntregados;
    }

    public int getPlasticosRecogidos() {
        return plasticosRecogidos;
    }

    public void setPlasticosRecogidos(int plasticosRecogidos) {
        this.plasticosRecogidos = plasticosRecogidos;
    }


}
