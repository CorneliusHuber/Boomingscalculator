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

public class Number extends Term {

	/**
	 * Constructor of Number. No genParts() needed. This is done during calculate().
	 * 
	 * @param input
	 * @param vz
	 */
	public Number(String input, short vz) {

		super();
		stringTerm = input;
		algebraicSign = vz;
		printlog("Konstruktor der Zahl " + (Double.parseDouble(input) * vz) + " ist fertig.");
		printlog();
		unindent();
	}

	/**
	 * This constructor is kept for efficiency, it is being used by Term and Root
	 * 
	 * @param input
	 */
	public Number(String input) {

		super();
		stringTerm = input;
		unindent();

	}

	@Override
	public String returnStringTerm() {

		return stringTerm;

	}


	@Override
	public String returnStringTermReverse() {

		double result = Double.parseDouble(stringTerm);

		String stringRev = Double.toString(result);

		if (result < 0) {

			stringRev = "(-" + stringRev;

		} else {

			stringRev = "(+" + stringRev;

		}

		stringRev = stringRev + ")";

		if (exponent != null) {

			stringRev = stringRev + "^" + exponent.returnStringTermReverse();

		}

		return stringRev;

	}

	@Override
	public double calculate() {

		try {

			result = Double.parseDouble(stringTerm) * algebraicSign;
			calculateExponent();
			return result;

		} catch (Exception e) {

			System.out.println(e);
			e.printStackTrace();
			return 1; // In case...

		}

	}

}