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
	
	public enum sinCosTanOption {
		
		SINUS, COSINUS, TANGENS
		
	};
	private sinCosTanOption sinCosTanSelected;
	private Parentheses parentheses;

	public SinCosTan(String input, sinCosTanOption sinCosTanSelected, short arithmeticOperator) {
		
		super();
		stringTerm = input;
		algebraicSign = arithmeticOperator;
		this.sinCosTanSelected = sinCosTanSelected;
		genParts();
		printlog();
		
		if(this.sinCosTanSelected == sinCosTanOption.SINUS) {
			
			printlog("Sinus received: " + input);
			
		} else if (this.sinCosTanSelected == sinCosTanOption.COSINUS) {
			
			printlog("Cosinus received: " + input);
			
		} else  {
			
			printlog("Tangens received " + input);
			
		}
		unindent();
		
	}
	
	@Override
	public String returnStringTermReverse() {
		
		String stringRev;
		
		if (this.sinCosTanSelected == sinCosTanOption.SINUS) {
			
			stringRev = "sin(";
		
		} else if (this.sinCosTanSelected == sinCosTanOption.COSINUS) {
			
			stringRev = "cos(";
			
		} else if (this.sinCosTanSelected == sinCosTanOption.TANGENS) {
			
			stringRev = "tan(";
			
		} else {
			
			stringRev = "";
			//Can just be the upper three cases, this is for the compiler.
			
		}
		
		
		stringRev = stringRev + parentheses.returnStringTerm();
		
		stringRev = stringRev + ")";
		
		if (exponent != null) {
			
			stringRev = stringRev + exponent.returnStringTermReverse();
			
		}
		
		return "";
		
	}
	
	@Override
	public double calculate() {
		
		result = 0;
		
		if (this.sinCosTanSelected == sinCosTanOption.SINUS) {
			
			result = Math.sin(parentheses.calculate())*algebraicSign; 
			
		} else if (this.sinCosTanSelected == sinCosTanOption.COSINUS) {
			
			result = Math.cos(parentheses.calculate())*algebraicSign;
			
		} else if (this.sinCosTanSelected == sinCosTanOption.TANGENS) {
			
			result = Math.tan(parentheses.calculate())*algebraicSign;
			
		}
		
		calculateExponent(); 
		return result;
		
	}
	
	@Override
	protected void genParts(){
		
		parentheses = new Parentheses(stringTerm);
		
	}
	
}