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

import java.util.ArrayList;

import iOStreams.Output;
import iOStreams.Outputable;

public class Term implements Outputable {

	protected String stringTerm;
	protected ArrayList<Term> termParts = new ArrayList<Term>();
	protected ArrayList<Term> lastParts = new ArrayList<Term>();
	protected Analysator analysator = new Analysator();
	protected double result = 0;
	protected short algebraicSign = 1; // if negativ -1, else +1
	//Exponent can now be a term
	protected Term exponent;
	private int progress = 0;
	protected enum collectTypes {
			NO, MULT, DIV
	};
	protected collectTypes lastTimeCollect = collectTypes.NO;
	protected collectTypes thisTimeCollect = collectTypes.NO;
	protected boolean firstIteration = true;
	protected StringUtils sU = new StringUtils();
	protected short nextAlgebraicSign = 1;

	/**
	 * Standard constructor, if you do not want everything this does, use Term().
	 * 
	 * @param input
	 */
	public Term(String input) {

		indent();
		stringTerm = input;
		delWhiteSpaces();
		genParts();
		unindent();

	}

	/**
	 * Second constructor, just does <code> indent() </code>. If you are using this,
	 * you will need to do anything Term(input) does. It is also protected which
	 * will not allow any other classes else than children to use this as
	 * unknowingly what it does.
	 */
	protected Term() {

		indent();

	}

	protected void printlog() {

		Output.printlog();

	}

	protected void printlog(String log) {

		Output.printlog(log);

	}

	protected void printlog(char log) {

		Output.printlog(log);

	}

	protected void printlog(Exception e) {

		Output.printlog(e);

	}

	protected void printimp(String imp) {

		Output.printlog(imp);

	}

	protected void unindent() {

		Output.unindent();

	}

	protected void indent() {

		Output.indent();

	}

	public String returnStringTerm() {

		if (stringTerm != null) {

			return stringTerm;

		} else {

			return "Error: stringTerm == null. (This should never happen. :()";

		}

	}

	/**
	 * Reverseengineering stringTerm, output goes to console.
	 * 
	 * @return
	 */
	public String returnStringTermReverse() {

		String stringRev = "";

		for (Term termPart : termParts) {

			stringRev = stringRev + termPart.returnStringTermReverse();

		}

		return stringRev;

	}

	public String returnExponent() {

		if (exponent != null) {

			return exponent.returnStringTerm();

		} else {

			printlog("No Exponent");

			return "1";

		}

	}

	public void setExponent(Term ex) {

		if (exponent == null) {

			this.exponent = ex;

		} else {

			printlog("setExponent() There has already been an exponent set.");

		}
	}
	


	private void delWhiteSpaces() {

		stringTerm = sU.removeall(stringTerm, ' ');

	}

	public double calculate() {

		printlog();
		printlog();
		indent();
		printlog("calculating.");

		printlog("Term has " + termParts.size() + " Parts: ");
		for (Term termPart : termParts) {

			printlog("Termparts are: " + termPart.returnStringTerm());

		}

		for (int i = 0; i < termParts.size(); i++) {

			result = result + termParts.get(i).calculate();

			printlog(i + "th iteration");
			printlog("Result until now: " + result);

		}

		calculateExponent();
		printimp("Finished iteration; result = " + result);
		unindent();
		return result * algebraicSign;

	}

	protected void calculateExponent() {

		if (exponent != null) {

			printlog("Exponent");
			result = Math.pow(result, exponent.calculate());
			return;

		}

		printlog("No Exponent");

	}

	private void genSinCosTan() {

		printlog("Sin/Cos/Tan found");

		String substring = stringTerm.substring(progress, progress + 3);
		progress = progress + 3;
		String contentParenthesis = getParenthesisString();

		if (substring.equals("sin")) {

			buffer(new SinCosTan(contentParenthesis, SinCosTan.sinCosTanOption.SINUS, nextAlgebraicSign));

		} else if (substring.equals("cos")) {

			buffer(new SinCosTan(contentParenthesis, SinCosTan.sinCosTanOption.COSINUS, nextAlgebraicSign));

		} else if (substring.equals("tan")) {

			buffer(new SinCosTan(contentParenthesis, SinCosTan.sinCosTanOption.TANGENS, nextAlgebraicSign));

		} else {

			printlog("Error: genSinCosTan(), Oopsiedaisy.");

		}

		progress = progress + contentParenthesis.length() + 2;
	}

	private void genNumber() {

		printlog("Number found");

		String stringNumber = getNumberString();

		buffer(new Number(stringNumber, nextAlgebraicSign));

	}

	private String getNumberString() {

		String stringNumber = "" + stringTerm.charAt(progress);
		progress++;

		LengthCalculation: while (progress < stringTerm.length()) {

			char tested = stringTerm.charAt(progress);

			if (analysator.isNumber(tested) || tested == '.') {

				stringNumber = stringNumber + stringTerm.charAt(progress);
				progress++;

			} else {

				break LengthCalculation;

			}

		}

		return stringNumber;

	}

	private void genRoot() {

		printlog("Root found");
		progress = progress + 1;
		String contentParenthesis = getParenthesisString();
		buffer(new Root(contentParenthesis, nextAlgebraicSign));
		progress = progress + contentParenthesis.length() + 2;

	}

	private void genNthRoot() {

		printlog("Root found");
		progress = progress + 1;
		String contentNth = getBracketsString();
		progress = progress + contentNth.length() + 2;
		String contentParenthesis = getParenthesisString();
		progress = progress + contentParenthesis.length() + 2;
		buffer(new Root(contentParenthesis, contentNth, nextAlgebraicSign));

	}

	private void genParenthesis() {

		printlog("Parenthesis found");
		String contentParenthesis = getParenthesisString();
		buffer(new Parentheses(contentParenthesis, nextAlgebraicSign));
		progress = progress + contentParenthesis.length() + 2;

	}

	/**
	 * Grabs itself the content of parentheses.
	 * <p>
	 * Also works for <code> "(...(...)...)"</code>, return would be
	 * <code>"...(...)..."</code> .
	 * 
	 * @return
	 */
	private String getParenthesisString() {

		printlog("getParenthesis()");
		return sU.everythingInParentheses(stringTerm.substring(progress));

	}

	/**
	 * Grabs itself the content of a brackets.
	 * <p>
	 * Also works for <code> "[...[...]...]"</code>, return would be
	 * <code> "...[...]..."</code> .
	 * 
	 * @return
	 */
	private String getBracketsString() {

		printlog("getBracketsString()");
		return sU.everythInSquaredBrackets(stringTerm.substring(progress));

	}

	/**
	 * This method runs the buffer. It is needed for postbundling objects.
	 * 
	 * @param termObj
	 */
	private void buffer(Term termObj) {
		printlog();
		printlog();
		printlog("buffering");

		if (firstIteration) {

			// printlog("first Iteration");
			lastParts.add(termObj);
			firstIteration = false;

		} else if (thisTimeCollect == collectTypes.NO && lastTimeCollect == collectTypes.NO) {

			// printlog("No and no");
			termParts.add(lastParts.get(0));
			lastParts.remove(0);
			lastParts.add(termObj);

		} else if (thisTimeCollect == collectTypes.NO && lastTimeCollect == collectTypes.MULT) {

			// printlog("no and times.");
			Term[] temp = new Term[lastParts.size()];
			for (int i = 0; i < temp.length; i++) {

				temp[i] = lastParts.get(i);

			}

			termParts.add(new Multiplikation(temp, collectTypes.MULT));
			// reset lastParts
			lastParts = new ArrayList<Term>();
			lastParts.add(termObj);

		} else if (thisTimeCollect == collectTypes.MULT && lastTimeCollect != collectTypes.DIV
				|| thisTimeCollect == collectTypes.DIV && lastTimeCollect != collectTypes.MULT) {

			printlog("Mal und nicht Geteilt oder Geteilt und nicht Mal.");

			lastParts.add(termObj);

		} else if (thisTimeCollect == collectTypes.NO && lastTimeCollect == collectTypes.DIV) {

			printlog("no and divide.");

			// collecting all parts
			Term[] temp = new Term[lastParts.size()];
			for (int i = 0; i < temp.length; i++) {

				temp[i] = lastParts.get(i);

			}

			termParts.add(new Multiplikation(temp, collectTypes.DIV));
			// reset lastParts
			lastParts = new ArrayList<Term>();
			lastParts.add(termObj);

		} else if (thisTimeCollect == collectTypes.MULT && lastTimeCollect == collectTypes.DIV) {

			printlog("times and divide.");
			Term[] temp = new Term[lastParts.size()];
			for (int i = 0; i < temp.length; i++) {

				temp[i] = lastParts.get(i);

			}

			lastParts = new ArrayList<Term>();
			lastParts.add(new Multiplikation(temp, collectTypes.DIV));
			lastParts.add(termObj);

		} else if (thisTimeCollect == collectTypes.DIV && lastTimeCollect == collectTypes.MULT) {

			printlog("divide and times.");

			Term[] temp = new Term[lastParts.size()];
			for (int i = 0; i < temp.length; i++) {

				temp[i] = lastParts.get(i);

			}

			lastParts = new ArrayList<Term>();
			lastParts.add(new Multiplikation(temp, collectTypes.MULT));
			lastParts.add(termObj);

		} else {

			printlog("Foregotten a case in buffer()!!!!");

		}

		printlog("finished buffering. Jay.");

	}

	/**
	 * Generates all the parts we need to have in temParts.
	 */
	protected void genParts() {

		printlog();
		printlog("Generating Parts of String: " + stringTerm);
		printlog("stringTerm is " + stringTerm.length() + " chars long.");
		printlog("Starting iteration");

		while (progress < stringTerm.length()) {

			if (analysator.isNumber(stringTerm.charAt(progress))) {

				printlog("Number at " + progress);
				genNumber();
				printlog("Finished genNumber()");

			} else if (analysator.isSinCosTan(stringTerm, progress)) {

				printlog("Sin/Cos/Tan at " + progress);
				genSinCosTan();
				printlog("Finished genSinCosTan()");

			} else if (analysator.isAnyRoot(stringTerm, progress) == 1) {

				printlog("nthRoot at " + progress);
				genNthRoot();
				printlog("Finished genNthRoot()");

			} else if (analysator.isAnyRoot(stringTerm, progress) == 0) {

				printlog("Root at " + progress);
				genRoot();
				printlog("Finished genRoot()");

			} else if (stringTerm.charAt(progress) == '(') {

				printlog("Parenthesis at " + progress);
				genParenthesis();
				printlog("Finished genParenthesis()");

			} else if (stringTerm.charAt(progress) == '+') {

				// TODO cleanup

				/*
				 * I wrote the notice that the arithmetic operators should not be the last thing
				 * to be checked. Well, it is. Works though. I am going to look what is up here
				 * when I am polishing the code.
				 */

				printlog("+ found");
				progress++;
				lastTimeCollect = thisTimeCollect;
				thisTimeCollect = collectTypes.NO;
				nextAlgebraicSign = 1;

			} else if (stringTerm.charAt(progress) == '-') {

				printlog("- found");
				progress++;
				nextAlgebraicSign = -1;
				lastTimeCollect = thisTimeCollect;
				thisTimeCollect = collectTypes.NO;

			} else if (stringTerm.charAt(progress) == '/') {

				printlog("/ found");
				progress++;
				lastTimeCollect = thisTimeCollect;
				thisTimeCollect = collectTypes.DIV;
				nextAlgebraicSign = 1;

			} else if (stringTerm.charAt(progress) == '*') {

				printlog("* found");
				progress++;
				lastTimeCollect = thisTimeCollect;
				thisTimeCollect = collectTypes.MULT;
				nextAlgebraicSign = 1;
				printlog("lsammeln = " + lastTimeCollect);
				printlog("sammeln = " + thisTimeCollect);

			} else if (stringTerm.charAt(progress) == '^') {
				printlog("potence at " + progress);
				progress++;

				/*
				 * Parenthesis needs to follow
				 */
				
				String temp;
				
				if (stringTerm.charAt(progress) == '(') {

					temp = getParenthesisString();
					printlog("Exponent with a parenthesis.");
					progress = progress + temp.length() +2;
					lastParts.get(lastParts.size() - 1).setExponent(new Parentheses(temp));
				
				} else if (analysator.isNumber(stringTerm.charAt(progress))) {
					
					temp = getNumberString();
					printlog("Exponent with just a number");
					lastParts.get(lastParts.size() - 1).setExponent(new Number(temp));
					
				}

			} else {

				printlog("genTeile() Buchstaben nicht gefunden, returning");
				return;

			}
		}

		// Buffering the last time, a bit different then the other iterations.

		printlog("last buffering");
		printlog("critical, buffer() in genPats()");

		if (thisTimeCollect == collectTypes.MULT && lastTimeCollect == collectTypes.MULT) {

			printlog("Times and times");
			Term[] temp = new Term[lastParts.size()];
			for (int i = 0; i < temp.length; i++) {

				temp[i] = lastParts.get(i);

			}

			termParts.add(new Multiplikation(temp, collectTypes.MULT));

		} else if (thisTimeCollect == collectTypes.DIV && lastTimeCollect == collectTypes.DIV) {

			printlog("Divide and divide.");
			Term[] temp = new Term[lastParts.size()];
			for (int i = 0; i < temp.length; i++) {

				temp[i] = lastParts.get(i);

			}

		} else if (thisTimeCollect == collectTypes.MULT && lastTimeCollect != collectTypes.MULT) {

			printlog("times and not times");
			termParts.add(new Multiplikation(new Term[] { lastParts.get(0), lastParts.get(1) }, collectTypes.MULT));

		} else if (thisTimeCollect == collectTypes.DIV && lastTimeCollect != collectTypes.DIV) {

			printlog("divide and not divide.");
			termParts.add(new Multiplikation(new Term[] { lastParts.get(0), lastParts.get(1) }, collectTypes.DIV));

		} else if (thisTimeCollect == collectTypes.NO && lastTimeCollect == collectTypes.NO) {

			printlog("No and no.");
			termParts.add(lastParts.get(0));

		} else if (thisTimeCollect == collectTypes.NO && lastTimeCollect == collectTypes.MULT || thisTimeCollect == collectTypes.NO && lastTimeCollect == collectTypes.DIV) {

			
			printlog("NO and MULT or DIV");
			termParts.add(lastParts.get(0));
			
		}

		else {

			printlog("Foregot a case in here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

		}

		printlog("feddig."); // German dialect, states that the method has finished.

	}
}
