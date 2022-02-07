package com.roytuts.spring.boot.auth.service.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Role {

	@Id
	private String rollName;
	private String roleDescription;

	public String getRollName() {
		return rollName;
	}

	public void setRollName(String rollName) {
		this.rollName = rollName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Role(String rollName, String roleDescription) {
		super();
		this.rollName = rollName;
		this.roleDescription = roleDescription;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

}
