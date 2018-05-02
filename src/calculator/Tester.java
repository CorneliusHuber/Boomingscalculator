package calculator;

import analysis.*;
import analysis.Number;
import iOStreams.*;

public class Tester implements Outputable {

	private boolean log = true;
	Analysator ana = new Analysator();
	Term re;
	StringUtils su = new StringUtils();

	public static void main(String args[]) {
		
		long timebegin = System.nanoTime();
		Tester te = new Tester();
		long timeend = System.nanoTime();
		System.out.println("Dauer: " + (timeend - timebegin) + " Nanosekunden.");
		
	}

	Tester() {

		//testefuenfteWvier();

	}

	private void todo() {
		// TODO hier alles, was zu tun ist rein.
		ana = new Analysator();
		ana.isSinCosTan("sin(4)", 0);

	}
	
	private void matthiasDavidsRechnung() {
		
		re = new Term("277/77");
		re.calculate();
		
		//Hier gibt es einen Fehler.
		
	}

	private void testeSinfunkt() {

		String rechnung = "cos(3.14)";

		ana = new Analysator();
		ana.testEverything(rechnung);
		re = new Term(rechnung);
		re.calculate();
		
		/*
		 * Testbericht: 
		 * Fehler bei testEnde(). Am Ende wurde fälschlicherweise false
		 * zurückgegeben, obwohl eigentlich alles gepasst hat.
		 */

	}

	private void testeWurzelfunkt() {
		
		String rechnung = "V(45)+ 4";
		//ana = new Analysator();
		//ana.testeAlles(rechnung);
		re = new Term(rechnung);
		re.calculate();
		
		/*
		 * Testbericht:
		 * Fehler bei testArithmOp: lastOp wurde zu schnell auf true gesetzt.
		 * Fehler bei testAnfang: Eine neue Methode wurde gemacht, istFuntion();
		 * testeFuntktion funktioniert nicht ganz, muss gefixt werden.
		 */

	}

	private void testeWurzelFunktuPlus() {

		String rechnung = "V(45)+2";
		ana = new Analysator();
		ana.testEverything(rechnung);
		//re = new Rechnung(rechnung);
		//re.ausrechnen();

	}
	
	private void testeWurzelFunktuMinus() {
		
		String rechnung = "V(45)-28";
		ana = new Analysator();
		ana.testEverything(rechnung);
		re = new Term(rechnung);
		re.calculate();
		
	}
	
	private void testeSin() {
		
		String rechnung = "sin(90) * 4";
		re = new Term(rechnung);
		re.calculate();
		
	}
	
	private void testeKlammer() {
		
		String rechnung = "(13*5/3+17-10+6) + (84) + 7";
		//rechnung = "(13*5/3+17-10+6)";
		//rechnung = "(13*5/3)";
		//rechnung = "(13*5)";
		//rechnung = "13*5";
		re = new Term(rechnung);
		re.calculate();
		
	}

	private void testeExponent() {

		re = new Term("4^(2)");
		re.calculate();

	}
	
	private void probiereExponent() {
		
		String rechnung = "25^(3)";
		re = new Term(rechnung);
		re.calculate();
	}
	
	private void probiereGleitkommaZahl() {
		
		Number z = new Number("1.55", (short) (1));
		System.out.println(z.calculate());
		
	}
	
	private void testeGleitkommaZahl() {
		
		re = new Term("1.55+27+V(1+7)");
		re.calculate();
		
	}
	
	private void matheHause() {
		
		re = new Term("90-20*cos(0/20)-9*0");
		re.calculate();
		
	}
	
	private void testeMalRechnung() {
		
		re = new Term("5*3");
		re.calculate();
		
		/*
		 * Hier wurde das Problem gefixt, dass bei der genTeile() 
		 * eine geschweifte Klammer mit der teilVerarbeitung umgeschrieben,
		 * da sonst in der Hauptschleife immer die Pipe vom Ende durchlaufen werden würde.
		 * Danke Peter!
		 */
	}
	
	private void testeSubstring() {
		
		String teststr = "V(blabla)";
		Output.printlog(teststr.substring(0, 2));
		
		
	}
	
	private void testeMinusMalGeteilt() {
		
		re = new Term("7-4*5/2");
		re.calculate();
		
	}
	
	/**
	 * Das hier MUSS eine RuntimeException werfen, sonst funktioniert etwas nicht.
	 * Kleiner als null darf das Ergebnis unterhalb einer Wurzel nicht sein. Jedenfalss
	 * bisher nicht.
	 */
	private void testeUnterNullWurzel() {
		
		re = new Term("V(-10)");
		re.calculate();
		
	}
	
	private void testeEntewurzel() {
		
		re = new Term("V[5](3*3*3) - 3");
		re.calculate();
		
	}
	
	private void testefuenfteWvier() {
		
		re = new Term("V[5](4)");
		re.calculate();
		
	}
	
	private void testeirgendeineWurzelana() {
		
		String teststring = "5+V(4+5*7/762125)";
		
		int ergebnis = ana.isAnyRoot(teststring, 2);
		System.out.println(ergebnis);
		
	}
	
	public void outputneutest( ) {
		
		Output.printImp("Static funktioniert das alles auch schon.");
		Output.indent();
		Output.printlog("Ja, durchaus.");
		Output.printlog("Aber irgendwie ist das mit dem Einrücken noch nicht so ganz korrekt.");
		
	}
	
	public void instanceoftest() {
		
		re = new Parenthesis("24");
		System.out.println(re instanceof Parenthesis);
		
	}

}