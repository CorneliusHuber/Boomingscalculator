package analysis;


public class Parenthesis extends Term {
	
	
	public Parenthesis(String eingabe, short vz) {
		
		super(eingabe);
		algebraicSign = vz;
		unindent();
	}
	
	public Parenthesis(String eingabe) {
		
		super(eingabe);
		printlog();
		printlog();
		
	}
	
	@Override
	public String returnStringTermReverse() {
		
		String rechnungrev = "(";
		
		for(Term rechnungsteil: termParts) {
			
			rechnungrev = rechnungrev + rechnungsteil.returnStringTermReverse();
			
		}
		
		rechnungrev = rechnungrev + ")";
		return rechnungrev;
		
		
	}
	
}