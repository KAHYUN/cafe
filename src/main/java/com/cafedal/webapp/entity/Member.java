package com.cafedal.webapp.entity;

import java.util.Date;

public class Member {

	private String id;
	private String pwd;
	private String name;
	private String mail;
	private String birth;
	private String role;
	//private Date regDate;
	
	public Member() {
		
	}
	
	public Member(String id, String pwd, String name, String mail, String birth, String role) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.mail = mail;
		this.birth = birth;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
