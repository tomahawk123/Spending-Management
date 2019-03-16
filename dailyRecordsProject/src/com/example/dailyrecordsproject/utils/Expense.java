package com.example.dailyrecordsproject.utils;

public class Expense{

	private int ledgerid;
	private double amount;
	private String type,item,member,remark, account, MonthAndDay;
	
	
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getMonthAndDay() {
		return MonthAndDay;
	}
	public void setMonthAndDay(String monthAndDay) {
		MonthAndDay = monthAndDay;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Expense(){
		
	}
	
	public Expense(double amount, String type, String item, String member) {
		super();
		this.amount = amount;
		this.type = type;
		this.item = item;
		this.member = member;
	}
	public Expense(int ledgerid, double amount, String type, String item,
			String member) {
		super();
		this.ledgerid = ledgerid;
		this.amount = amount;
		this.type = type;
		this.item = item;
		this.member = member;
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
	public int getLedgerid() {
		return ledgerid;
	}
	public void setLedgerid(int ledgerid) {
		this.ledgerid = ledgerid;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return amount + type + item + member +"," + MonthAndDay;
	}
	
	
}
