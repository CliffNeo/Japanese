package com.example.japanese;

public class Let {
	private String Spe;
	private String Pro;
	public Let(String spe,String pro){
		this.Spe = spe;
		this.Pro = pro;
	}
	public String getSpe(){
		return this.Spe;
	}
	public String getPro(){
		return this.Pro;
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof Let){
			Let a = (Let) o;
			if(a.getSpe() == Spe&&a.getPro() == Pro){
				return true;
			}
			return false;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return Spe.hashCode();
	}
	
}
