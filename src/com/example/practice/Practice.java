package com.example.practice;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "practice")
public class Practice {
	@DatabaseField(id = true)
	private String name = null;
	@DatabaseField
	private String pings = "";
	@DatabaseField
	private int amount = 0;
	@DatabaseField
	private int time = 0;
	
	public Practice(){
		
	}
	
	public Practice(String name,String pings,int amount,int time){
		this.name = name;
		this.pings = pings;
		this.amount = amount;
		this.time = time;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getPings() {
		return pings;
	}

	public void setPings(String pings) {
		this.pings = pings;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}


}
