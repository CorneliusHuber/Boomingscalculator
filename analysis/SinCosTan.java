package analysis;

public class SinCosTan extends Term {
	
	public static char SINUS = 's';
	public static char COSINUS = 'c';
	public static char TANGENS = 't';
	private static char ausgewählt;
	private Parenthesis klammer;

	public SinCosTan(String eingabe, char auswahl, short vz) { //Konstruktor
		
		super();
		stringTerm = eingabe;
		algebraicSign = vz;
		ausgewählt = auswahl;
		genParts();
		printlog();
		
		if(ausgewählt == SINUS) {
			
			printlog("Sinus erhalten: " + eingabe);
			
		} else if (ausgewählt == COSINUS) {
			
			printlog("Cosinus erhalten: " + eingabe);
			
		} else  {
			
			printlog("Tangens erhalten " + eingabe);
			
		}
		unindent();
		
	}
	
	@Override
	public String returnStringTermReverse() {
		
		String rechnungrev;
		
		if (ausgewählt == SINUS) {
			
			rechnungrev = "sin(";
		
		} else if (ausgewählt == COSINUS) {
			
			rechnungrev = "cos(";
			
		} else if (ausgewählt == TANGENS) {
			
			rechnungrev = "tan(";
			
		} else {
			
			rechnungrev = "";
			printlog("Hier ist nichts ausgewählt. gebeRechnungsTeileaus() in SinCosTan");
			
		}
		
		
		rechnungrev = rechnungrev + klammer.returnStringTerm();
		
		rechnungrev = rechnungrev + ")";
		
		return "";
		
	}
	
	@Override
	public double calculate() {
		
		result = 0;
		
		if (ausgewählt == SINUS) {
			
			result = Math.sin(klammer.calculate())*algebraicSign; 
			
		} else if (ausgewählt == COSINUS) {
			
			result = Math.cos(klammer.calculate())*algebraicSign;
			
		} else if (ausgewählt == TANGENS) {
			
			result = Math.tan(klammer.calculate())*algebraicSign;
			
		}
		
		calculateExponent(); 
		return result;
		
	}
	
	@Override
	protected void genParts(){
		
		klammer = new Parenthesis(stringTerm);
		
	}
	
}