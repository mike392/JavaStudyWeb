package com.study.sample.entity;

public class User {
private String login;
private UserType role;
private String password;
private double balance;
public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}
public UserType getRole() {
	return role;
}
public void setRole(UserType role) {
	this.role = role;
}
}
