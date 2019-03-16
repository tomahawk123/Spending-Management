package com.example.dailyrecordsproject.utils;

public class Budget {

	private double amountBudget;
	private int ledgerid;
	public double getAmountBudget() {
		return amountBudget;
	}
	public void setAmountBudget(double amountBudget) {
		this.amountBudget = amountBudget;
	}
	public int getLedgerid() {
		return ledgerid;
	}
	public void setLedgerid(int ledgerid) {
		this.ledgerid = ledgerid;
	}
	public Budget(double amountBudget, int ledgerid) {
		super();
		this.amountBudget = amountBudget;
		this.ledgerid = ledgerid;
	}
	
	public Budget(){
		
	}
	
}
