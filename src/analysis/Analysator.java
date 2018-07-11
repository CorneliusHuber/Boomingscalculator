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

import iOStreams.Output;
import iOStreams.Outputable;

/**
 * Used to analyse the syntac, mostly used in <code>Term </code>.
 * 
 * @author blackbox
 *
 */
public class Analysator implements Outputable {

	protected int openParenthesis = 0;
	protected int closedParenthesis = 0;
	protected char firstParenthesis;
	protected char lastParenthesis;
	protected Output outp;
	StringUtils su;

	/**
	 * Main Constructor
	 */
	public Analysator() {

		su = new StringUtils();

	}

	public void printlog() {

		Output.printlog();

	}

	public void printlog(char log) {

		Output.printlog(log);

	}

	public void printlog(String input) {

		Output.printlog(input);

	}

	public void printlog(Exception e) {

		Output.printlog(e);

	}

	public void printImp(String imp) {

		Output.printImp(imp);

	}

	public void printImp(Exception e) {

		Output.printImp(e);

	}

	/**
	 * Input analysis starts with a search for potential errors.
	 */
	public void testEverything(String input) {

		printlog(input);

		try {

			String extext = "Something different";

			boolean ok = true;
			if (!testParenthesis(input)) {

				extext = "Error consurning the parenthesis";
				ok = false;

			} else if (!testArithmOp(input)) {

				extext = "Error consurning the arithmetic operators.";
				ok = false;

			} else if (!testBegining(input)) {

				extext = "Error at the beginning";
				ok = false;

			} else if (!testEnding(input)) {

				extext = "Error at the ending";
				ok = false;

			}

			if (!ok) {

				throw new BadSyntaxException(extext);

			}

		} catch (Exception e) {
			// Auto-generated catch block
			e.printStackTrace();
			printlog(e);
		}

		printlog("Analysator: OK");

	}

	/**
	 * Goes through all chars of the String and collects data. the last parenthesis
	 * is always being saved. Usual mathematics, no rocket-science.
	 */
	protected boolean testParenthesis(String input) {

		boolean firstParfound = false;

		int leftParenthesis = 0; // (
		int rightparenthesis = 0; // )
		char lastParenthesis = ')';

		try {

			for (int i = 0; i < input.length(); i++) {

				if (input.charAt(i) == '(') {

					leftParenthesis++;
					lastParenthesis = input.charAt(i);

				} else if (input.charAt(i) == ')') {

					rightparenthesis++;
					lastParenthesis = input.charAt(i);
					if (firstParfound) {

						return false;

					}

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
			printlog(e);
			printlog("Error at testParenthesis");
			return false;

			// Continuing after catch

		}

		if (leftParenthesis != rightparenthesis) {

			printlog("Error at testParenthesis");
			return false;

		} else if (lastParenthesis != ')') {

			printlog("Error at testParenthesis");
			return false;

		}

		printlog("testParenthesis: OK");
		return true;

	}

	/**
	 * This tests the usual arithmetic operators if they are set correctly. Just the
	 * usual rules, two operators running are forbidden.
	 * 
	 * @param input
	 */
	protected boolean testArithmOp(String input) {

		boolean lastcharOp = false;

		for (int index = 0; index < input.length(); index++) {

			char temp = input.charAt(index);

			if (isArithmeticOperator(temp)) {

				if (lastcharOp) {

					printlog("Error at testArithmOp, more than one arithmetic operator in a row at " + temp);
					return false;

				}

				lastcharOp = true;

			} else {

				lastcharOp = false;

			}

		}

		/*
		 * If there has been no reason to break up and return false the program will now
		 * return true to signal everything is correct.
		 */

		printlog("testArithmOp: OK");
		return true;

	}

	/**
	 * Tests whether a string contains
	 * <p>
	 * sin(
	 * <p>
	 * cos(
	 * <p>
	 * tan(.
	 * <p>
	 * 
	 * @param input
	 * @param index
	 * @return
	 */
	@Deprecated
	public boolean testSinCosTan(String input, int index) {

		return isSinCosTan(input, index) || isSinCosTan(input, index - 1) || isSinCosTan(input, index - 2);

	}

	/**
	 * Tests whether
	 * <p>
	 * sin(
	 * <p>
	 * cos(
	 * <p>
	 * tan(
	 * <p>
	 * is following after the char at index -1.
	 * 
	 * @param input
	 * @param index
	 * @return
	 */
	public boolean isSinCosTan(String input, int index) {

		/**
		 * At first this is gonna test if there is 5 Characters Space after the char at
		 * index. A term like this needs at least five chars space Example: sin(5)
		 */

		if ((input.length() - index - 5) < 0) {

			printlog("Too short for sincostan");
			return false;

		}

		try {

			boolean res = isSin(input, index) || isCos(input, index) || isTan(input, index);
			if (res) {
				printlog("isSinCosTan: OK.");
			}
			return res;

		} catch (Exception e) {

			e.printStackTrace();
			printlog(e);
			printlog("Exception at istSinCosTan");
			return false;

		}
	}

	/**
	 * Tests whether <code> sin( </code> is starting at index.
	 * 
	 * @param input
	 * @param index
	 * @return
	 */
	public boolean isSin(String input, int index) {

		String res = "" + input.charAt(index) + input.charAt(index + 1) + input.charAt(index + 2)
				+ input.charAt(index + 3);

		if (res.equals("sin(")) {

			return true;

		} else {

			return false;

		}

	}

	/**
	 * Tests whether <code> cos( </code> is starting at index.
	 * 
	 * @param input
	 * @param index
	 * @return
	 */
	public boolean isCos(String input, int index) {

		String res = "" + input.charAt(index) + input.charAt(index + 1) + input.charAt(index + 2)
				+ input.charAt(index + 3);

		if (res.equals("cos(")) {

			return true;

		} else {

			return false;

		}

	}

	/**
	 * Tests whether <code> tan( </code> is starting at index.
	 * 
	 * @param input
	 * @param index
	 * @return answer
	 */
	public boolean isTan(String input, int index) {

		String res = "" + input.charAt(index) + input.charAt(index + 1) + input.charAt(index + 2)
				+ input.charAt(index + 3);

		if (res.equals("tan(")) {

			return true;

		} else {

			return false;

		}

	}

	/**
	 * Tests whether <code> V( </code> is somewhere around the index.
	 * 
	 * @param input
	 * @param index
	 * @return answer
	 */
	@Deprecated
	public boolean testRoot(String input, int index) {

		String resBegin = "";
		String resEnd = "";

		try {
			resBegin = input.substring(index, index + 1);
		} catch (Exception e) {
			e.printStackTrace();
			printlog(e);
		}

		try {
			resEnd = input.substring(index - 1, index);
		} catch (Exception e1) {
			e1.printStackTrace();
			printlog(e1);
		}

		boolean beginOK = resBegin.equals("V(");
		boolean endOK = false;
		if (index > 0) {
			endOK = resEnd.equals("V("); // else exception
		}

		if (beginOK || endOK) {

			return true;

		} else {

			return false;

		}

	}

	/**
	 * Tests whether <code> V[]( </code> or <code> V( </code> is following at index.
	 * 
	 * @param input
	 * @param index
	 * @return result
	 */
	@Deprecated
	public boolean isRoot(String input, int index) {

		try {

			String res = input.substring(index, index + 2);

			if (res.equals("V(")) {

				return true;

			}

			/*
			 * What does not happen to be barebones syntax is kicked out here to check
			 * whether the syntax equals V(), V[]() or nothing of these
			 */
			
			res = su.stringUntilCharOrEnd(res, '(');
			res = su.removeallbut(res, '(', '[', ']', 'V');

			if (res.equals("V(") || res.equals("V[](")) {

				return true;

			} else {

				return false;

			}

		} catch (Exception e) {

			e.printStackTrace();
			printlog(e);
			printlog("Error at isRoot()");
			return false;

		}

	}

	/**
	 * Tests whether <code> V[]( </code> is following at index.
	 * 
	 * @param input
	 * @param index
	 * @return result
	 */
	@Deprecated
	public boolean isNthRoot(String input, int index) {

		try {

			if (input.charAt(index) != 'V') {

				return false;

			}

			index++;

			String ente = su.everythInSquaredBrackets(input).substring(index);

			if (ente == null) {

				return false;

			}

			int weiter = su.endBrackets(input.substring(index));

			if (weiter == -1) {

				return false;

			}

			if (input.charAt(weiter) == ']') {

				return true;

			}

		} catch (Exception e) {

			e.printStackTrace();
			printlog(e);
			printlog("Error at isNthRoot");
			return false;

		}

		return false;

	}

	/**
	 * This method is different than the others, but that might change, others might
	 * be reformatted later on. -1 means no root 0 means usual root 1 means nth Root
	 * 
	 * @param input
	 * @param index
	 * @return shortresult
	 */
	public short isAnyRoot(String input, int index) {

		if (input.charAt(index) != 'V') {

			return -1;

		}

		int ende = su.endParentheses(input.substring(index));

		String rootConstruct = input.substring(index, ende + 1);

		rootConstruct = su.removeallbut(input, '(', ')', '[', ']', 'V');

		if (rootConstruct.equals("V()")) {

			return 0;

		} else if (rootConstruct.equals("V[]()")) {

			return 1;

		} else {

			return -1;

		}

	}

	/**
	 * Tells whether at index a function <code>
	 *       Sin, Cos, Tan <p>
	 *       or a root construct 
	 * </code> is being used.
	 * 
	 * @param input
	 * @param index
	 * @return result
	 */
	protected boolean isFunction(String input, int index) {

		if (isRoot(input, index) || isSinCosTan(input, index)) {

			return true;

		} else {

			return false;

		}

	}

	public boolean isNumber(char zuTesten) {

		try {
			switch (zuTesten) {

			case '0':
				return true;
			case '1':
				return true;
			case '2':
				return true;
			case '3':
				return true;
			case '4':
				return true;
			case '5':
				return true;
			case '6':
				return true;
			case '7':
				return true;
			case '8':
				return true;
			case '9':
				return true;
			default:
				return false;
			}

		} catch (Exception e) {

			printlog(e + " at isNumber()");
			return false;

		}

	}

	protected boolean isParenthesis(char tested) {

		try {
			switch (tested) {
			case '(':
				return true;
			case ')':
				return true;
			default:
				return false;
			}
		} catch (Exception e) {
			printlog(e + " @istKlammer");
			return false;
		}
	}

	public boolean isArithmeticOperator(char tested) {

		switch (tested) {
		case '/':
			return true;
		case ':': // also '/'
			return true;
		case '*':
			return true;
		case '+':
			return true;
		case '-':
			return true;
		case '.': // also ','
			return true;
		case ',':
			return true;
		case 'V':
			return true;
		default:
			return false;
		}

	}

	public boolean isAlgebraicSing(char tested) {

		switch (tested) {
		case '+':
			return true;
		case '-':
			return true;
		default:
			return false;

		}

	}

	public boolean testBegining(String input) {

		char temp = input.charAt(0);

		if ((isArithmeticOperator(temp) && !isAlgebraicSing(temp) && !isFunction(input, 0))) {

			printlog("Error at testBegin() " + temp);
			return false;

		}

		return true;

	}

	public boolean testEnding(String input) {

		char temp = input.charAt(input.length() - 1);

		try {

			if (isArithmeticOperator(temp)) {

				printlog("Error at testEnd");
				return false;
			}

		} catch (Exception e) {

			e.printStackTrace();
			printlog(e);
			printlog("Exception @ testEnd()");
			return false;

		}

		printlog("testEnd: OK");
		return true;

	}

	public boolean isSuperscript(char tested) {

		switch (tested) {

		case '¹':
			return true;
		case '²':
			return true;
		case '³':
			return true;
		case '⁴':
			return true;
		case '⁵':
			return true;
		case '⁶':
			return true;
		case '⁷':
			return true;
		case '⁸':
			return true;
		case '⁹':
			return true;
		case '⁰':
			return true;
		default:
			return false;

		}

	}

	public char highToLow(char converted) {

		switch (converted) {

		case '¹':
			return '1';
		case '²':
			return '2';
		case '³':
			return '3';
		case '⁴':
			return '4';
		case '⁵':
			return '5';
		case '⁶':
			return '6';
		case '⁷':
			return '7';
		case '⁸':
			return '8';
		case '⁹':
			return '9';
		case '⁰':
			return '0';

		}

		return ' ';

	}

}