package analysis;

public class Number extends Term {
	
	
	/**
	 * Konstruktor von Zahl. Hier jetzt kein genTeile(), da man nur noch beim Ausrechnen
	 * den String in eine Zahl umwandeln muss.
	 * @param eingabe
	 * @param vz
	 */
	public Number(String eingabe, short vz) {
		
		super();
		stringTerm = eingabe;
		algebraicSign = vz;
		printlog("Konstruktor der Zahl " + (Double.parseDouble(eingabe) * vz) + " ist fertig.");
		printlog();
		unindent();
	}
	
	
	/**
	 * Für den Fall, dass man wie bei der Wurel nur noch einen Konstruktor braucht.
	 * Dieser Konstruktor ist auch silent, was aber nicht heißt, dass es silent ist, 
	 * hier ausrechnen() aufzurufen.
	 * @param eingabe
	 */
	public Number(String eingabe) {
		
		super();
		stringTerm = eingabe;
		unindent();
		
	}
	
	@Override
	public String returnStringTerm() {
		
		return stringTerm;
		
	}
	
	/**
	 * Hier wird zuerst der String genommen und dann wieder zurück gerechnet, um zu
	 * vermeiden, dass hier ein ungültiger String dabei ist. Könnte eine Exception werfen.
	 */
	@Override
	public String returnStringTermReverse() {
		
		double ergebnis = Double.parseDouble(stringTerm);
		
		String stringrev = Double.toString(ergebnis);
		
		return stringrev;
		
	}
	
	@Override
	public double calculate() {
		
		try {
			
			result = Double.parseDouble(stringTerm)*algebraicSign;
			printlog("Ergebnis ohne Exponent ist " + result);
			printlog("Exponent stringrechnung ist: ");
			returnExponent();
			calculateExponent();
			printlog("Exponent ausgerechnet, Ergebnis: " + result);
			return result;
			
		} catch (Exception e) {
			
			System.out.println(e);
			e.printStackTrace();
			return 1; //für den Fall, dass...
			
			
		}
		
		
	}
	
}