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


public class Parentheses extends Term {
	
	
	public Parentheses(String input, short vz) {
		
		super(input);
		algebraicSign = vz;
		unindent();
	}
	
	public Parentheses(String input) {
		
		super(input);
		printlog();
		printlog();
		
	}
	
	@Override
	public String returnStringTermReverse() {
		
		String stringRev = "(";
		
		stringRev = stringRev + super.returnStringTermReverse();
		
		stringRev = stringRev + ")";
		return stringRev;
		
		
	}
	
}