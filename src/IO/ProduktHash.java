package IO;


public class ProduktHash {
	public String artnr;
	public String name;
	public int preis;
	
	int endIndexOfName;
	int endIndexOfArtnr;
	int endIndexOfPreis;
	
	public ProduktHash(String z) {
		bestimmeEndIndexOf(z);
		
		artnr = bestimmeArtnr(z);
		
		name = bestimmeName(z);
		
		preis = bestimmePreis(z);
		
	}
	
	
	private int bestimmePreis(String z) {
		int endIndexOfPreisWert = z.length();
		
		if (z.indexOf("}", endIndexOfPreis) != -1) {
			endIndexOfPreisWert  = z.indexOf("}", endIndexOfPreis);
			
		}
			if ((z.indexOf(",", endIndexOfPreis) < endIndexOfPreisWert ) && (z.indexOf(",", endIndexOfPreis) != -1 )) {
				endIndexOfPreisWert  = z.indexOf(",", endIndexOfPreis);	
				
			}
		
		return Integer.parseInt(z.substring(endIndexOfPreis +1, endIndexOfPreisWert ));
	}

	

	private String bestimmeName(String z) {
		int beginIndexOfNameWert = z.indexOf("\"", endIndexOfName);
		int endIndexOfNameWert = z.indexOf("\"", beginIndexOfNameWert + 1);
		return z.substring(beginIndexOfNameWert+1, endIndexOfNameWert);
	}


	private String bestimmeArtnr(String z) {
		int beginIndexOfArtnrWert = z.indexOf("\"", endIndexOfArtnr);
		int endIndexOfArtnrWert = z.indexOf("\"", beginIndexOfArtnrWert + 1);
		return z.substring(beginIndexOfArtnrWert+1, endIndexOfArtnrWert);
	}
	
	private void bestimmeEndIndexOf(String z) {
		int beginIndexOfName = z.indexOf("name");
		endIndexOfName = z.indexOf(":", beginIndexOfName);
		
		int beginIndexOfArtnr = z.indexOf("artnr");
		endIndexOfArtnr = z.indexOf(":", beginIndexOfArtnr);
		
		int beginIndexOfPreis = z.indexOf("preis");
		endIndexOfPreis= z.indexOf(":", beginIndexOfPreis);
	}
	
	public String getArtnr() {
		return artnr;
	}


	public void setArtnr(String artnr) {
		this.artnr = artnr;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPreis() {
		return preis;
	}


	public void setPreis(int preis) {
		this.preis = preis;
	}
	

	@Override
	public String toString() {
		return " Das Produkt mit dem Namen " + name + " unter der Artikelnummer "  + artnr + " hat den Preis " + preis + " Euro";
	}


	public static void main (String args[]) {
		//Produkt produkt = new Produkt("preis: 56, hallo oo");
		//System.out.println(produkt.test());
		String z = "{\"artnr\":\"0613\", \"name\":\"Hose, schwarz\", \"preis\":120}\"";
		ProduktHash produkt = new ProduktHash(z);
		System.out.println(produkt.getName());
		System.out.println(produkt.getArtnr());
		System.out.println(produkt.getPreis());
		
	}
}
