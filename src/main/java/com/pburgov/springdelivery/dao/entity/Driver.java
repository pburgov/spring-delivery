package com.pburgov.springdelivery.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "drivers")
public class Driver extends BaseEntity{

    @NotNull
    @Column(name = "usuario", unique = true)
    private String usuario;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "blocked")
    private Boolean blocked;


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String chofer) {
        this.usuario = chofer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public String toString(){
        return this.firstName + " " + this.lastName;
    }

}
