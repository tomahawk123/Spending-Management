package com.example.dailyrecordsproject.utils;

import java.util.*;

public class Ledger {

	private String name;
	private long imageID;
	private int userid;
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Ledger(){
		
	}

	
	public Ledger(String name, long imageID, int userid) {
		super();
		this.name = name;
		this.imageID = imageID;
		this.userid = userid;
	}

	public Ledger(long imageID) {
		super();
		this.name = name;
		this.imageID = imageID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getImageID() {
		return imageID;
	}
	public void setImageID(long imageID) {
		this.imageID = imageID;
	}
	
	@Override
	public String toString() {
		return "name=" + name + ", imageID=" + imageID ;
	}
	
}
