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

public class Root extends Term {

	private Parentheses parentheses;
	private Number nth;

	public Root(String input, short arithmeticOperator) {

		super();
		stringTerm = input;
		algebraicSign = arithmeticOperator;
		printlog("Klammer wird erstellt.");
		genParts();
		returnStringTerm();
		printlog();
		unindent();

	}

	/**
	 * Momentan kann das hier noch keine Gleitkommazahlen als nte Wurzel haendeln.
	 * Das muss noch implementiert werden.
	 * 
	 * @param eingabe
	 * @param nte
	 * @param vz
	 */

	/**
	 * Having an nth Root in which nth is not a natural number would not match to
	 * the arguments of StaticMaths.root, therefore the number is parsed to an int
	 * later on.
	 * 
	 * @param input
	 * @param nth
	 * @param arithmeticOperator
	 */
	public Root(String input, String nth, short arithmeticOperator) {

		super();
		stringTerm = input;
		algebraicSign = arithmeticOperator;
		printlog("Root is being made.");
		genParts();
		this.nth = new Number(nth);
		returnExponent();
		printlog();
		unindent();

	}

	@Override
	public String returnStringTerm() {

		return stringTerm;

	}

	@Override
	public String returnStringTermReverse() {

		String strinRev = "V(";

		strinRev = strinRev + parentheses.returnStringTermReverse();

		strinRev = strinRev + ")";

		return strinRev;

	}

	@Override
	public double calculate() {

		result = parentheses.calculate();

		if (result < 0) {

			/*
			 * This exception needs to be uncatched until someone really uses the lib.
			 */
			throw new RuntimeException("Term under Root < 0.");

		}

		if (nth == null) {

			result = calculator.StaticMaths.wurzelziehen(result, 5) * algebraicSign;

		} else {

			result = calculator.StaticMaths.wurzelziehen(result, 5, (int) nth.calculate());

		}

		calculateExponent();
		return result;

	}

	@Override
	protected void genParts() {

		parentheses = new Parentheses(stringTerm);

	}

}