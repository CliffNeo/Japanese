package com.example.japanese;

import java.util.*;

public class LetManage {
	private List<Let> lets = new ArrayList<Let>();
	public void addLet(String spe,String pro){
		Let a = new Let(spe,pro);
		lets.add(a);
	}
	public void addLet(Let a){
		lets.add(a);
	}
	
	public Let searchSpe(String spe){
		Iterator<Let> it = lets.iterator();
		while(it.hasNext()){
			Let a = it.next();
			if(a.getSpe().equals(spe)){
				return a;	
			}
		}
		System.out.println("ÎåÊ®Òô Ğ´·¨ËÑË÷Ê§°Ü");
		return null;
	}
	
	public Let searchPro(String pro){
		Iterator<Let> it = lets.iterator();
		while(it.hasNext()){
			Let a = it.next();
			if(a.getPro() == pro){
				return a;	
			}
		}
		System.out.println("ÎåÊ®Òô ¶ÁÒôËÑË÷Ê§°Ü");
		return null;
	}
	
	public Let random(){
		Random r = new Random();
		int i = r.nextInt(lets.size()-1);
		//System.out.println("i " + i);
		//System.out.println("size " + lets.size());
		return lets.get(i);
	}
	
	public Iterator<Let> lookinto(){
		Iterator<Let> it = lets.iterator();
		return it;
	}
	
	public List<Let> getList(){
		return lets;
	}
	
	public void eraseLets() {
		this.lets = new ArrayList<Let>();
	}
}
