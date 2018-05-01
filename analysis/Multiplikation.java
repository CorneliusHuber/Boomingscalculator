package analysis;

import iOStreams.Outputable;

public class Multiplikation extends Term {

	private char auswahl;
	private Term[] rechnungen;

	Multiplikation(Term[] rechnungen, char auswahl) {
		
		super();
		printlog();
		printlog();
		printlog("Mulitiplikation Constructor.");
		this.rechnungen = rechnungen;
		
		if (rechnungen.length < 1) {
			
			printlog("Empty array.");
			printlog("Stopping method.");
			return;
			
		} else if (rechnungen.length < 2) {
			
			printlog("Warning: Just one part in Multiplikation.");
			
			
		}
		

		this.auswahl = auswahl;
		if (auswahl == MULT) {

			printlog("Multiplikation with " + rechnungen.length + " parts is being made.");
			
			
		} else {

			printlog("Division with " + rechnungen.length + " parts is being made.");

		}
		
		genParts();
		unindent();

	}
	
	@Override
	public String returnStringTerm() {
		
		String rechnungsstring = termParts.get(0).returnStringTerm();
		
		for (int i = 1; i< termParts.size(); i++) {
			
			if (auswahl == MULT) {
				
				rechnungsstring = rechnungsstring + '*';
				
			} else if (auswahl == DIV) {
				
				rechnungsstring = rechnungsstring + '/';
				
			} else {
				
				printlog("Hier gibt es ein größeres Problem mit gebeRechnungaus() in MalRechnung.");
				
			}
			
			rechnungsstring = rechnungsstring + termParts.get(i).returnStringTerm();
			
		}
		
		return rechnungsstring;
		
	}
	
	@Override
	public String returnStringTermReverse() {
		
		
		/*
		 * Kurzer Kommentar dazu: Hier kann man keinen String bekommen, weil man
		 * keinen String übergeben bekommt, darum muss man in gebeRechnungaus() genau
		 * das selbe machen wie hier deshalb verleihe ich dem dieser Methode nur einen
		 * anderen Anstrich.
		 */
		
		return returnStringTerm();
		
	}

	@Override
	protected void genParts() {
		
		printlog("Übertrage Teile aus Array: ");
		printlog("Fange mit der Iteration an.");

		for (Term term : rechnungen) {

			term.returnStringTerm();
			termParts.add(term);

		}

		printlog("genTeile() von MalRechnung fertig.");
		
	}
	
	
	
	@Override
	public double calculate() {
		
		double ergebnis = termParts.get(0).calculate();
		
		if (auswahl == MULT) {
			
			for (int i = 1; i< termParts.size(); i++) {
				/*
				 * Hier ist das 1, weil der Teil 0 bereits ausgerechnet wurde
				 * und in ergebnis steht.
				 */
				
				ergebnis *= termParts.get(i).calculate();
				printlog("Malrechnung fertig ausgerechnet.");
				
			}
			
		} else if (auswahl == DIV) {
			
			for (int i = 1; i< termParts.size(); i++) {
				/*
				 * Hier ist das 1, weil der Teil 0 bereits ausgerechnet wurde
				 * und in ergebnis steht.
				 */
				
				ergebnis /= termParts.get(i).calculate();
				printlog("Geteiltrechnung fertig ausgerechnet.");
				
			}
			
		} else {
			
			printlog("Obacht beim ausrechenn der Mal/Geteiltrechnung hat es wohl einen Fehler gegeben.");
			
		}
		
		return ergebnis;
		
	}

}