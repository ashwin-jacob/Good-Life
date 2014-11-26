package com.goodlife.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "users", catalog = "goodlife")
public class Users {

	@Id
	@Column(name = "username", unique = true, nullable = false, length = 250)
	private String username;

	@Column(name = "password", nullable = false, length = 200)
	private String password;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;
	
	@Column(name = "invitation_code", nullable=true)
	private Integer invitation_code;
	
	@Column(name = "invited_by", nullable = true, length = 250)
	private String invited_by;
	
	@Column(name = "invited_date", nullable = true)
	private Date invited_date;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Set<UserRole> userRole = new HashSet<UserRole>(0);

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public Integer getInvitation_code() {
		return invitation_code;
	}

	public void setInvitation_code(Integer invitation_code) {
		this.invitation_code = invitation_code;
	}

	public String getInvited_by() {
		return invited_by;
	}

	public void setInvited_by(String invited_by) {
		this.invited_by = invited_by;
	}

	public Date getInvited_date() {
		return invited_date;
	}

	public void setInvited_date(Date invited_date) {
		this.invited_date = invited_date;
	}
}
