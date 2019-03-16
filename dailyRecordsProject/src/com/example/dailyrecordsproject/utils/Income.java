package com.example.dailyrecordsproject.utils;

public class Income {

	private int ledgerid;
	private double amount;
	private String type, account, remark, date;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getLedgerid() {
		return ledgerid;
	}
	public void setLedgerid(int ledgerid) {
		this.ledgerid = ledgerid;
	}
	
	public Income(){
		
	}
	
	public Income(int ledgerid, double amount, String type, String account) {
		super();
		this.ledgerid = ledgerid;
		this.amount = amount;
		this.type = type;
		this.account = account;
	}
	public Income(double amount, String type, String account) {
		super();
		this.amount = amount;
		this.type = type;
		this.account = account;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return date + "-" + amount + "-" + account;
	}
	
}
