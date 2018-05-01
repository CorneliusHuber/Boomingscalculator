package Recheneinheit;

public class NichtRechenbarException extends Exception {
	
	public NichtRechenbarException() {
		
		super("Das hier ist nicht Rechenbar. Vllt bei der Wurzel kleiner als 0?");
		
	}
	
	public NichtRechenbarException(String text) {
		
		super(text);
		
	}
	
}
