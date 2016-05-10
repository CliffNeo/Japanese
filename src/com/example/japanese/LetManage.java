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

	public boolean deleteLet(Let a){
        if(lets.contains(a)){
            lets.remove(a);
            return true;
        }else {
            return false;
        }

    }
	public Let searchSpe(String spe){
		Iterator<Let> it = lets.iterator();
		while(it.hasNext()){
			Let a = it.next();
			if(a.getSpe().equals(spe)){
				return a;	
			}
		}
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
		return null;
	}
	
	public Let random(){
		Random r = new Random();
		int i = r.nextInt(lets.size()-1);
		return lets.get(i);
	}
	
	public Iterator<Let> lookinto(){
		Iterator<Let> it = lets.iterator();
		return it;
	}
	
	public List<Let> getList(){
		return lets;
	}

	public void setList(List<Let> list){
		this.lets = list;
	}
	public void eraseLets() {

		this.lets = new ArrayList<Let>();
	}

	public int letAmount(){
		return lets.size();
	}
}
