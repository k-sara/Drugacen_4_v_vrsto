package logika;

import java.util.Arrays;

public class Stirke {
	
	/* Stirke so vse možne zmagovalne kombinacije. 
	 * Predstavljene so z dvema tabelama dolzine 4. Prva tabela predstavlja x in druga y koordinate. */
	

	public int[] x;
	public int[] y;
	
	public Stirke(int[] x, int[] y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "Stirke [x=" + Arrays.toString(x) + ", y=" + Arrays.toString(y) + "]";
	}

}
