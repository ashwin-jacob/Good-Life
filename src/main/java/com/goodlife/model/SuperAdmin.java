package com.goodlife.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity (name = "SuperAdmin")
@Table(name="SUPER_ADMIN", catalog = "goodlife")
public class SuperAdmin extends Users {
	
	private static final long serialVersionUID = 1L;
	
	public SuperAdmin() {
		super();
	}
	
}