package analysis;

import calculator.NichtRechenbarException;

public class Root extends Term {

	private Parenthesis klammer;
	private Number nte;

	public Root(String eingabe, short vz) {

		super();
		stringTerm = eingabe;
		algebraicSign = vz;
		printlog("Klammer wird erstellt.");
		genParts();
		returnStringTerm();
		printlog();
		unindent();

	}

	/**
	 * Momentan kann das hier noch keine Gleitkommazahlen als nte Wurzel
	 * haendeln. Das muss noch implementiert werden.
	 * 
	 * @param eingabe
	 * @param nte
	 * @param vz
	 */
	public Root(String eingabe, String nte, short vz) {

		super();
		stringTerm = eingabe;
		algebraicSign = vz;
		printlog("Klammer wird erstellt.");
		genParts();
		this.nte = new Number(nte);
		returnExponent();
		printlog();
		unindent();

	}

	@Override
	public String returnStringTerm() {

		// Auto-generated method stub
		printlog("Inhalt: " + stringTerm);
		return stringTerm;

	}

	@Override
	public String returnStringTermReverse() {

		String rechnungrev = "V(";

		rechnungrev = rechnungrev + klammer.returnStringTermReverse();

		rechnungrev = rechnungrev + ")";

		return rechnungrev;

	}

	@Override
	public double calculate() {

		// Hier muss dafür gesorgt werde, dass da nichts negatives ist.

		result = klammer.calculate();

		if (result < 0) {

			/*
			 * Kurzer Erklärung hierzu: Ich kann keine normale Exception werfen,
			 * denn die wird dann gleich gefangen. Hier kann ich dann das
			 * Problem umgehen, indem ich sie nicht fangen muss.
			 */

			throw new RuntimeException("Ergebnis in Wuzel kleiner als null.");

		}

		printlog("Rechne Wurzel aus");

		if (nte == null) {
			
			result = calculator.StaticMathe.wurzelziehen(result, 5) * algebraicSign;
			
		} else {
			
			result = calculator.StaticMathe.wurzelziehen(result, 5, (int) nte.calculate());
			
		}
		
		printlog("Wurzel erfolgreich ausgerechnet.");
		calculateExponent();
		printlog("Exponent ausgerechnet, Ergebnis: " + result);
		return result;

	}

	@Override
	protected void genParts() {

		klammer = new Parenthesis(stringTerm);

	}

}