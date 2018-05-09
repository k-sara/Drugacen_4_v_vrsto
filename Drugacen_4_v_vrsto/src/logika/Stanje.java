package logika;

public enum Stanje {
	NA_VRSTI_RED,
	NA_VRSTI_BLUE,
	ZMAGA_RED,
	ZMAGA_BLUE,
	NEODLOCENO;
	
	private Stirke zmagovalna;
	
	/* Nastavi zmagovalno štirko na niè, saj na zaèetku igre nimamo še zmagovalne štirke. */
	
	private Stanje() {
		zmagovalna = null;
	}
	
	/* Nastavi zmagovalno štirko. */
	
	public void setZmagovalna (Stirke s) {
		assert (this == ZMAGA_RED || this == ZMAGA_BLUE);
		zmagovalna = s;
	}
	
	/* getZmagovalna vrne zmagovalno štirko. */
	
	public Stirke getZmagovalna() {
		return zmagovalna;
	}

}
