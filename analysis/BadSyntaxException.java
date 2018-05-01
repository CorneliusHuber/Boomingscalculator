package analysis;

public class BadSyntaxException extends Exception {
	
	public BadSyntaxException() {
		super("Bad Syntax, please correct.");
		
	}
	
	public BadSyntaxException(String error) {
		
		super(error);
		
	}
	
}
