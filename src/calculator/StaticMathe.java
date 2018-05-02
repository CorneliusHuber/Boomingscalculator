package calculator;

public class StaticMathe {
	
	public static final double pi = 3.1415926535897;
	
	
	//Erst mal ein paar überladene Methoden zum Multiplizieren
	
	public static int multiplizieren(int a, int b) {
		return a*b;
	}
	
	public static long multiplizieren(long a, long b) {
		return a*b;
	}
	
	public static float multiplizieren(float a, float b) {
		return a*b;
	}
	
	public static double multiplizieren(double a, double b) {
		return a*b;
	}
	
	
	//Hier zwei ebenfalls überladene Methoden zum Wurzelziehen. Die zweite ist für eine nte Wurzel.
	
	public static double wurzelziehen(double einZahl, int nachKommaStellen) {
	    
		//Zuerst mal überprüfen, ob einzahl und nachKommaStellen größer als 0 sind
		
		if (einZahl < 0 || nachKommaStellen < 0) {
			
			return 0;
			
		}
		
		
		//Nachkommastellenberechnung
		
		boolean istErsteRundevorbei = false;
		double zwischenErgebnis = 1;
	    double multipler = 1;
		for (int i= nachKommaStellen; i >= 0; i--) {
			
			/*
			 * Die Methode errechnet via Annäherungverfahren an die Wurzel der Zahl angenähert.
			 * jedes Mal, wenn der Grenzwert übertreten wird, wird der Multiplizierer verkleinert.
			 */
			
			
			if(zwischenErgebnis * zwischenErgebnis == einZahl) {
				return zwischenErgebnis;
			}
			
			while (zwischenErgebnis*zwischenErgebnis < einZahl) {
				zwischenErgebnis +=multipler;
				if (zwischenErgebnis * zwischenErgebnis > einZahl) {
					multipler = multipler / 10;
				}
			}
			while (zwischenErgebnis * zwischenErgebnis > einZahl) {
				zwischenErgebnis -=multipler;
				if (zwischenErgebnis * zwischenErgebnis < einZahl) {
					multipler = multipler / 10;
				}
			}
			
			if(!istErsteRundevorbei && einZahl > 10) { //Hier wird berechnet, wie viele Iteration mehr aufgrund der Dezimalstellen nötig sind.
				istErsteRundevorbei = true;
				double zwischenErgebniskopie = zwischenErgebnis;
				while (zwischenErgebniskopie >= 10) {
					zwischenErgebniskopie = zwischenErgebniskopie /10;
					i++;
				}
			}
					
		}
		
		return zwischenErgebnis;
		
	}
	
    public static double wurzelziehen(double einZahl, int nachKommaStellen, int wievielte) {
	    
		//Zuerst mal überprüfen, ob einzahl und nachKommaStellen größer als 0 sind
		
		if (einZahl < 0 || nachKommaStellen < 0 || wievielte < 1) {
			
			return 0;
			
		}
		
		
		//Nachkommastellenberechnung
		
		boolean istErsteRundevorbei = false;
		double zwischenErgebnis = 1;
	    double multipler = 1;
		for (int i= nachKommaStellen; i >= 0; i--) {
			
			/*
			 * Die Methode errechnet via Annäherungverfahren an die Wurzel der Zahl angenähert.
			 * jedes Mal, wenn der Grenzwert übertreten wird, wird der Multiplizierer verkleinert.
			 */
			
			
			if(Math.pow(zwischenErgebnis, wievielte) == einZahl) {
				return zwischenErgebnis;
			}
			
			while (Math.pow(zwischenErgebnis, wievielte) < einZahl) {
				zwischenErgebnis +=multipler;
				if (Math.pow(zwischenErgebnis, wievielte) > einZahl) {
					multipler = multipler / 10;
				}
			}
			while (Math.pow(zwischenErgebnis, wievielte)> einZahl) {
				zwischenErgebnis -=multipler;
				if (Math.pow(zwischenErgebnis, wievielte) < einZahl) {
					multipler = multipler / 10;
				}
			}
			
			if(!istErsteRundevorbei && einZahl > 10) { //Hier wird berechnet, wie viele Iteration mehr aufgrund der Dezimalstellen nötig sind.
				istErsteRundevorbei = true;
				double zwischenErgebniskopie = zwischenErgebnis;
				while (zwischenErgebniskopie >= 10) {
					zwischenErgebniskopie = zwischenErgebniskopie /10;
					i++;
				}
			}
					
		}
		
		return zwischenErgebnis;
		
	}
	
    
}