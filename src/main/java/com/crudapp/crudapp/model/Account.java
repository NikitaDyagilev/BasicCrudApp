package com.crudapp.crudapp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Table(name = "account")
@Data
public class Account implements UserDetails {
	private static final long serialVersionUID = -3067656523419248226L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountID;

	@Column(name = "first_name")
	private String first_name;

	@Column(name = "last_name")
	private String last_name;

	@Column(name = "password")
	private String password;

	@Column(name = "username")
	private String username;

	@Column(name = "bio")
	private String bio;

	/**
	 * @return Long return the accountID
	 */
	public Long getAccountID() {
		return accountID;
	}

	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountID(Long accountID) {
		this.accountID = accountID;
	}

	/**
	 * @return String return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * @return String return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * @return String return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return String return the username
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return String return the bio
	 */
	public String getBio() {
		return bio;
	}

	/**
	 * @param bio the bio to set
	 */
	public void setBio(String bio) {
		this.bio = bio;
	}

	// TODO: this is just an example hardcoded list of authorities. In practice,
	// this should be probably be its own entity with a relationship to account
	private static List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>() {
		private static final long serialVersionUID = 5625329327126700418L;
		{
			add(new SimpleGrantedAuthority("admin"));
			add(new SimpleGrantedAuthority("read"));
			add(new SimpleGrantedAuthority("write"));
		}
	};

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}