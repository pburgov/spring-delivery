package com.pburgov.springdelivery.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "authorities", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "user_id", "authority" }) })
public class Authority extends BaseEntity{



	@Column(name="authority")
	private String authority;
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	
	@Override
	public String toString() {
		return "Authority [authority=" + authority + "]";
	}




}
