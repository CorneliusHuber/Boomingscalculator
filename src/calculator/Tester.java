/**
 *  This file is part of BoomingsCalculator
 *  Copyright (C) 2018  Cornelius Huber
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see https://www.gnu.org/licenses/gpl.html.
 */

package calculator;

import com.sun.xml.internal.ws.wsdl.writer.document.Port;

import analysis.*;
import analysis.Number;
import iOStreams.*;

@SuppressWarnings("unused")
public class Tester implements Outputable {

	Analysator ana = new Analysator();
	Term re;
	StringUtils su = new StringUtils();


	public static void main(String args[]) {
		
		Output.setextendedlog(true);
		outputingArgsTest(args);
		long timeBegin = System.nanoTime();
		Tester te = new Tester();
		long timeEnd = System.nanoTime();
		System.out.println("Duration: " + (timeEnd - timeBegin) + " Nanoseconds.");
		
	}

	Tester() {
		testStringRevMult();

	}

	private void todo() {
		ana = new Analysator();
		ana.isSinCosTan("sin(4)", 0);

	}
	
	private void matthiasDavidsRechnung() {
		
		re = new Term("277/77");
		re.calculate();
		
		
	}

	private void testeSinfunkt() {

		String rechnung = "cos(3.14)";

		ana = new Analysator();
		ana.testEverything(rechnung);
		re = new Term(rechnung);
		re.calculate();

	}

	private void testeWurzelfunkt() {
		
		String rechnung = "V(45)+ 4";
		//ana = new Analysator();
		//ana.testeAlles(rechnung);
		re = new Term(rechnung);
		re.calculate();

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
	
	public static void outputingArgsTest(String [] args) {
		
		Output.printlog("Here comes the \"ARG.\" ");
		
		for (int i = 0; i < args.length; i++) {
			
			Output.printlog(args[i]);
			
		}
		
	}
	
	public void testNatPotenceAfterMult() {
		
		re = new Term("5*4^4");
		re.calculate();
		
	}
	
	public void testFloatPotence() {
		
		re = new Term("4^3.5");
		re.calculate();
		
	}
	
	public void testStringRevMult () {
		
		re = new Term("5*4/3*7+3");
		re.calculate();
		Output.printlog(re.returnStringTermReverse() + " is the term");
		
	}

}