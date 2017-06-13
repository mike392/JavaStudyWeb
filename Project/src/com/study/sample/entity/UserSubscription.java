package com.study.sample.entity;

public class UserSubscription {
private String username;
private String subname;
private double balance;
private String blocked;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getSubname() {
	return subname;
}
public void setSubname(String subname) {
	this.subname = subname;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}
public String getBlocked() {
	return blocked;
}
public void setBlocked(String blocked) {
	this.blocked = blocked;
}
}
