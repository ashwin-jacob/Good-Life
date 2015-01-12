package com.goodlife.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "USERS", catalog = "goodlife")
@Inheritance
public class Users {

	@Id
	@Column(name = "usr_id", unique = true, nullable = false)
	@GeneratedValue
	private Integer userId;
	
	@Id
	@Column(name = "email", unique = true, nullable = false, length = 50)
	private String email;
	
	@Column(name = "usr_nm", unique = true, nullable = true, length = 50)
	private String username;

	@Column(name = "pwd", nullable = false, length = 200)
	private String password;

	@Column(name = "role_typ_cd", nullable = false, length = 1)
	private char roleTypeCode;
	
	@Column(name = "rgstrd", nullable = false)
	private boolean registered;
	
	@Column(name = "invit_cd", nullable = false)
	private Integer invitationCode;
	
	@Column(name = "invit_by", nullable = true, length = 250)
	private String invitedBy;
	
	@Column(name = "invit_dt", nullable = true)
	private Date invitedDate;

	@Column(name = "usr_sts_id", nullable = true)
	private Integer userStatusId;
	
    @Column(name="frst_nm", length = 50)
    private String firstname;
 
    @Column(name="lst_nm", length = 50)
    private String lastname;
    
    @Column(name="city", length = 50)
    private String city;
 
    @Column(name="state", length = 2)
    private String state;
    
    @Column(name="abt_me", length = 250)
    private String aboutMe;
    
	/*
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Set<UserRole> userRole = new HashSet<UserRole>(0);
	*/
	
    public Users() {
    	
    }
    
	public Users(Integer userId, String email, String username,
			String password, char roleTypeCode, boolean registered,
			Integer invitationCode, String invitedBy, Date invitedDate,
			Integer userStatusId, String firstname, String lastname,
			String city, String state, String aboutMe) {
		super();
		this.userId = userId;
		this.email = email;
		this.username = username;
		this.password = password;
		this.roleTypeCode = roleTypeCode;
		this.registered = registered;
		this.invitationCode = invitationCode;
		this.invitedBy = invitedBy;
		this.invitedDate = invitedDate;
		this.userStatusId = userStatusId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
		this.state = state;
		this.aboutMe = aboutMe;
	}


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public Integer getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(Integer invitationCode) {
		this.invitationCode = invitationCode;
	}

	public String getInvitedBy() {
		return invitedBy;
	}

	public void setInvitedBy(String invitedBy) {
		this.invitedBy = invitedBy;
	}

	public Date getInvitedDate() {
		return invitedDate;
	}

	public void setInvitedDate(Date invitedDate) {
		this.invitedDate = invitedDate;
	}

	public Integer getUserStatusId() {
		return userStatusId;
	}

	public void setUserStatusId(Integer userStatusId) {
		this.userStatusId = userStatusId;
	}
	
    public String getFirstname() {
        return firstname;
    }
    
    public String getLastname() {
        return lastname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public char getRoleTypeCode() {
		return roleTypeCode;
	}

	public void setRoleTypeCode(char roleTypeCode) {
		this.roleTypeCode = roleTypeCode;
	}
}
