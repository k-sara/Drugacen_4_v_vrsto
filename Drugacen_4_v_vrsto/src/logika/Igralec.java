package logika;

public enum Igralec {
	RED, 
	BLUE;
	
	public Igralec nasprotnik(){
		if (this == RED){
			return BLUE;
		} else {
			return  RED;
		}
	}
	
	public Polje getPolje() {
		if (this == RED){
			return Polje.RED;
		} else {
			return  Polje.BLUE;
		}
	}
	

}
