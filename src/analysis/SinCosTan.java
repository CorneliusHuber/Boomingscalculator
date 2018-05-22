/**
 *  This file is part of BoomingsCalculator
 *  Copyright (C) 2018  <name of author>
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
	
	public enum sinCosTanOption {
		
		SINUS, COSINUS, TANGENS
		
	};
	private sinCosTanOption sinCosTanSelected;
	private Parentheses klammer;

	public SinCosTan(String eingabe, sinCosTanOption sinCosTanSelected, short vz) { //Konstruktor
		
		super();
		stringTerm = eingabe;
		algebraicSign = vz;
		this.sinCosTanSelected = sinCosTanSelected;
		genParts();
		printlog();
		
		if(this.sinCosTanSelected == this.sinCosTanSelected.SINUS) {
			
			printlog("Sinus erhalten: " + eingabe);
			
		} else if (this.sinCosTanSelected == this.sinCosTanSelected.COSINUS) {
			
			printlog("Cosinus erhalten: " + eingabe);
			
		} else  {
			
			printlog("Tangens erhalten " + eingabe);
			
		}
		unindent();
		
	}
	
	@Override
	public String returnStringTermReverse() {
		
		String stringRev;
		
		if (this.sinCosTanSelected == this.sinCosTanSelected.SINUS) {
			
			stringRev = "sin(";
		
		} else if (this.sinCosTanSelected == this.sinCosTanSelected.COSINUS) {
			
			stringRev = "cos(";
			
		} else if (this.sinCosTanSelected == this.sinCosTanSelected.TANGENS) {
			
			stringRev = "tan(";
			
		} else {
			
			stringRev = "";
			printlog("Hier ist nichts ausgewählt. gebeRechnungsTeileaus() in SinCosTan");
			
		}
		
		
		stringRev = stringRev + klammer.returnStringTerm();
		
		stringRev = stringRev + ")";
		
		if (exponent != null) {
			
			stringRev = stringRev + exponent.returnStringTermReverse();
			
		}
		
		return "";
		
	}
	
	@Override
	public double calculate() {
		
		result = 0;
		
		if (this.sinCosTanSelected == this.sinCosTanSelected.SINUS) {
			
			result = Math.sin(klammer.calculate())*algebraicSign; 
			
		} else if (this.sinCosTanSelected == this.sinCosTanSelected.COSINUS) {
			
			result = Math.cos(klammer.calculate())*algebraicSign;
			
		} else if (this.sinCosTanSelected == this.sinCosTanSelected.TANGENS) {
			
			result = Math.tan(klammer.calculate())*algebraicSign;
			
		}
		
		calculateExponent(); 
		return result;
		
	}
	
	@Override
	protected void genParts(){
		
		klammer = new Parentheses(stringTerm);
		
	}
	
}