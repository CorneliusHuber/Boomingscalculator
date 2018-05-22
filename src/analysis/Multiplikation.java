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

import calculator.LogicException;

public class Multiplikation extends Term {

	private collectTypes selection;

	Multiplikation(Term[] termParts, collectTypes selection) {

		super();
		printlog();
		printlog();
		printlog("Mulitiplikation Constructor.");

		if (termParts.length < 1) {

			printlog("Empty array.");
			printlog("Stopping method.");
			return;

		} else if (termParts.length < 2) {

			printlog("Warning: Just one part in Multiplikation.");

		}

		/*
		 * This is what genParts() should usually do. For optimization purposes this is
		 * in the constructor, though.
		 */
		for (int i = 0; i < termParts.length; i++) {

			this.termParts.add(termParts[i]);

		}

		this.selection = selection;
		if (selection == collectTypes.MULT) {

			printlog("Multiplikation with " + termParts.length + " parts is being made.");

		} else {

			printlog("Division with " + termParts.length + " parts is being made.");

		}

		unindent();

	}

	@Override
	public String returnStringTerm() {

		String termString = termParts.get(0).returnStringTerm();

		for (int i = 1; i < termParts.size(); i++) {

			if (selection == collectTypes.MULT) {

				termString = termString + '*';

			} else if (selection == collectTypes.DIV) {

				termString = termString + '/';

			} else {

				printlog("There is a major problem in returnStringTerm() in Multiplikation.");

			}

			termString = termString + termParts.get(i).returnStringTerm();

		}

		return termString;

	}

	@Override
	public String returnStringTermReverse() {
		
		String stringRev = termParts.get(0).returnStringTermReverse();
		
		for (int i = 1 /*yes 1*/; i < termParts.size(); i++) {
			
			if (selection == collectTypes.MULT) {
				
				stringRev = stringRev + "*" + termParts.get(i).returnStringTermReverse();
				
			} else {
				
				stringRev = stringRev + "/" + termParts.get(i).returnStringTermReverse();
				
			}
			
		}
		

		return stringRev;

	}

	@Override
	public double calculate() {

		double result = termParts.get(0).calculate();

		if (selection == collectTypes.MULT) {

			for (int i = 1; i < termParts.size(); i++) {
				//0 has already been claculated

				result *= termParts.get(i).calculate();
				printlog("Division calculated.");

			}

		} else if (selection == collectTypes.DIV) {

			for (int i = 1; i < termParts.size(); i++) {
				//0 has already been calculated

				result /= termParts.get(i).calculate();
				printlog("Dvision calculated.");

			}

		}

		return result;

	}

}
