package logika;

public enum Igralec {
	RED, 
	BLUE;
	
	public Igralec nasprotnik(){
		return (this == RED ? BLUE : RED);
	}
	
	public Polje getPolje() {
		return (this == RED ? Polje.RED : Polje.BLUE);
	}
	

}
