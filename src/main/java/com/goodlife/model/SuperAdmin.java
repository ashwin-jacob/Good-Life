package com.goodlife.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity (name = "SuperAdmin")
@Table(name="SUPER_ADMIN", catalog = "goodlife")
public class SuperAdmin extends Users {
	
	public SuperAdmin() {
		super();
	}
	
}