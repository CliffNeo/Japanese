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
		// TODO 自动生成的方法存根
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
		// TODO 自动生成的方法存根
		return Spe.hashCode();
	}
	
}
