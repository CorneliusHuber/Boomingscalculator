package iOStreams;

import analysis.Term;


public class Starter {
	
	private static boolean log = true;

	public static void main(String args[]) {
		try {

			Term term = new Term("V(16)*4/5");
			double ergebnis = term.calculate();
			System.out.println("Ergebnis ist angeblich: " + ergebnis);

		} catch (Exception e) {

			System.out.println(e);

		}

	}

}