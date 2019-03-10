package com.hjq.po;

public class User {
	int Id;
	String Username;
	String Password;
	String PasswordCheck;
	String Tel;
	String Email;
	
	public int getId() {
		return Id;
	}
	@Override
	public String toString() {
		return "User [Id=" + Id + ", Username=" + Username + ", Password=" + Password + ", PasswordCheck="
				+ PasswordCheck + ", Tel=" + Tel + ", Email=" + Email + "]";
	}
	public void setId(int id) {
		this.Id = id;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		this.Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		this.Password = password;
	}
	public String getPasswordCheck() {
		return PasswordCheck;
	}
	public void setPasswordCheck(String passwordCheck) {
		this.PasswordCheck = passwordCheck;
	}
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		this.Tel = tel;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		this.Email = email;
	}
	
}
