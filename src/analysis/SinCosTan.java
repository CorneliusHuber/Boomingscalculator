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