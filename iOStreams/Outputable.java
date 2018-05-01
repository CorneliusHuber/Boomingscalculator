package iOStreams;

public interface Outputable {
	
	public static void printlog() {
		
		Output.printlog();
		
	}
	
	public static void printlog(char log) {
		
		Output.printlog(log);
		
	}
	
	public static void printlog(String log) {
		
		Output.printlog(log);
		
	}
	
	public static void printlog(Exception e) {
		
		Output.printlog(e);
		
	}
	
	public static void printImp(String imp) {
		
		Output.printImp(imp);
		
	}
	
	public static void printImp(Exception e) {
		
		Output.printImp(e);
		
	}

}
