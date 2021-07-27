package com.atm.model;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class User {
	private String name ;
	private double balance ; 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User(String name, double balance, double openinigBalance, double overDraftLimit) {
		super();
		this.name = name;
		this.balance = balance;
		this.openinigBalance = openinigBalance;
		this.overDraftLimit = overDraftLimit;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getOpeninigBalance() {
		return openinigBalance;
	}
	public void setOpeninigBalance(double openinigBalance) {
		this.openinigBalance = openinigBalance;
	}
	public double getOverDraftLimit() {
		return overDraftLimit;
	}
	public void setOverDraftLimit(double overDraftLimit) {
		this.overDraftLimit = overDraftLimit;
	}
	private double openinigBalance ; 
	private double overDraftLimit;
	

}
