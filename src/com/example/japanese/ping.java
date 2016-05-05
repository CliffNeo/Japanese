package com.example.japanese;

public class ping {
	private LetManage pings = new LetManage();
	
	public LetManage getPings() {
		return pings;
	}
	
	public void setPings(LetManage pings) {
		this.pings = pings;
	}

	public ping(){
		pings.addLet("あ","a");
		pings.addLet("い","yi");
		pings.addLet("う","wu");
		pings.addLet("え","ai");
		pings.addLet("お","o");
		
		pings.addLet("か","ka");
		pings.addLet("き","ki");
		pings.addLet("く","ku");
		pings.addLet("け","kai");
		pings.addLet("こ","ko");
		
		pings.addLet("が","ga");
		pings.addLet("ぎ","gi");
		pings.addLet("ぐ","gu");
		pings.addLet("げ","gai");
		pings.addLet("ご","go");
		
		pings.addLet("さ","sa");
		pings.addLet("し","xi");
		pings.addLet("す","si");
		pings.addLet("せ","sai");
		pings.addLet("そ","so");
		
		pings.addLet("ざ","za");
		pings.addLet("じ","ji");
		pings.addLet("ず","zi");
		pings.addLet("ぜ","zai");
		pings.addLet("ぞ","zo");
		
		pings.addLet("た","ta");
		pings.addLet("ち","qi");
		pings.addLet("つ","ci");
		pings.addLet("て","tai");
		pings.addLet("と","to");
		
		pings.addLet("だ","da");
		pings.addLet("ぢ","ji");
		pings.addLet("づ","zi");
		pings.addLet("で","dai");
		pings.addLet("ど","do");
		
		pings.addLet("な","na");
		pings.addLet("に","ni");
		pings.addLet("ぬ","nu");
		pings.addLet("ね","nai");
		pings.addLet("の","no");
		
		pings.addLet("は","ha");
		pings.addLet("ひ","hi");
		pings.addLet("ふ","fu");
		pings.addLet("へ","hai");
		pings.addLet("ほ","ho");
		
		pings.addLet("ば","ba");
		pings.addLet("び","bi");
		pings.addLet("ぶ","bu");
		pings.addLet("べ","bai");
		pings.addLet("ぼ","bo");
		
		pings.addLet("ぱ","pa");
		pings.addLet("ぴ","pi");
		pings.addLet("ぷ","pu");
		pings.addLet("ぺ","pai");
		pings.addLet("ぽ","po");
		
		pings.addLet("ま","ma");
		pings.addLet("み","mi");
		pings.addLet("む","mu");
		pings.addLet("め","mai");
		pings.addLet("も","mo");
		
		pings.addLet("や","ya");
		pings.addLet("ゆ","yu");
		pings.addLet("よ","yo");
		
		pings.addLet("ら","la");
		pings.addLet("り","li");
		pings.addLet("る","lu");
		pings.addLet("れ","lai");
		pings.addLet("ろ","lo");
		
		pings.addLet("わ","wa");
		pings.addLet("を","wo");
		
		pings.addLet("ん","n");
		
	}
	
	/*Let Random(){
		return pings.random();
	}*/
}
