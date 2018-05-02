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
	protected Parenthesis exponent;
	private int progress = 0;
	public static final char NO = 'n';
	public static final char MULT = 'm';
	public static final char DIV = 'g';
	protected char lastTimeCollect = NO;
	protected char thisTimeCollect = NO;
	protected boolean firstIteration = true;
	protected StringUtils sU = new StringUtils();
	protected short nextAlgebraicSign = 1;

	/**
	 * Standard constructor, if you do not want everything this does, use
	 * Term().
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
	 * Second constructor, just does <code> indent() </code>. If you are using
	 * this, you will need to do anything Term(input) does. It is also protected
	 * which will not allow any other classes else than children to use this as
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

	public void setExponent(Parenthesis ex) {

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

			buffer(new SinCosTan(contentParenthesis, SinCosTan.SINUS, nextAlgebraicSign));

		} else if (substring.equals("cos")) {

			buffer(new SinCosTan(contentParenthesis, SinCosTan.COSINUS, nextAlgebraicSign));

		} else if (substring.equals("tan")) {

			buffer(new SinCosTan(contentParenthesis, SinCosTan.TANGENS, nextAlgebraicSign));

		} else {

			printlog("Error: genSinCosTan(), Oopsiedaisy.");

		}

		progress = progress + contentParenthesis.length() + 2;
	}

	private void genNumber() {

		printlog("Number found()");

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
		buffer(new Parenthesis(contentParenthesis, nextAlgebraicSign));
		progress = progress + contentParenthesis.length() + 2;

	}

	/**
	 * Grabs itself the content of parentheses.
	 * <p>
	 * Also works for <code> "(...(...)...)"</code>, return would be <code>"...(...)..."</code> .
	 * 
	 * @return
	 */
	private String getParenthesisString() {

		printlog("getParenthesis()");
		return sU.allesinrundeKlammern(stringTerm.substring(progress));

	}

	/**
	 * Grabs itself the content of a brackets.
	 * <p>
	 * Also works for <code> "[...[...]...]"</code>, return would be <code> "...[...]..."</code> .
	 * 
	 * @return
	 */
	private String getBracketsString() {

		printlog("erstelleeckigeKlammernneu");
		return sU.everythInSquaredBrackets(stringTerm.substring(progress));

	}

	/**
	 * Die Teilverarbeitung ist zwar nicht so schön, aber dafür nützlich. Sie
	 * wird gebraucht, um zu gewährleisten, dass es eine Chronik gibt, um mal
	 * und geteilt zu gewährleisten.
	 * 
	 * @param rechnungsteil
	 */
	
	/**
	 * This method runs the buffer. It is needed for postbundling objects.
	 * @param termObj
	 */
	public void buffer(Term termObj) {
		printlog();
		printlog();
		printlog("buffering");

		if (firstIteration) {

			//printlog("first Iteration");
			lastParts.add(termObj);
			firstIteration = false;

		} else if (thisTimeCollect == NO && lastTimeCollect == NO) {

			//printlog("Nein und Nein");
			termParts.add(lastParts.get(0));
			lastParts.remove(0);
			lastParts.add(termObj);

		} else if (thisTimeCollect == NO && lastTimeCollect == MULT) {

			//printlog("Nein und Mal.");
			Term[] temp = new Term[lastParts.size()];
			for (int i = 0; i < temp.length; i++) {

				temp[i] = lastParts.get(i);

			}

			termParts.add(new Multiplikation(temp, Multiplikation.MULT));
			// reset lastParts
			lastParts = new ArrayList<Term>();
			lastParts.add(termObj);

		} else if (thisTimeCollect == MULT && lastTimeCollect != DIV
				|| thisTimeCollect == DIV && lastTimeCollect != MULT) {

			printlog("Mal und nicht Geteilt oder Geteilt und nicht Mal.");

			lastParts.add(termObj);

		} else if (thisTimeCollect == NO && lastTimeCollect == DIV) {

			printlog("Nein und Geteilt.");

			// erst mal alle Bestandteile sammeln
			Term[] temp = new Term[lastParts.size()];
			for (int i = 0; i < temp.length; i++) {

				temp[i] = lastParts.get(i);

			}

			termParts.add(new Multiplikation(temp, Multiplikation.DIV));
			// letzte zurücksetzten
			lastParts = new ArrayList<Term>();
			lastParts.add(termObj);

		} else if (thisTimeCollect == MULT && lastTimeCollect == DIV) {

			printlog("Mal und Geteilt.");

			// erst mal alle Bestandteile sammeln
			Term[] temp = new Term[lastParts.size()];
			for (int i = 0; i < temp.length; i++) {

				temp[i] = lastParts.get(i);

			}

			// letzte zurücksetzten
			lastParts = new ArrayList<Term>();
			lastParts.add(new Multiplikation(temp, Multiplikation.DIV));
			lastParts.add(termObj);

		} else if (thisTimeCollect == DIV && lastTimeCollect == MULT) {

			printlog("Geteilt und Mal.");

			// erst mal alle Bestandteile sammeln
			Term[] temp = new Term[lastParts.size()];
			for (int i = 0; i < temp.length; i++) {

				temp[i] = lastParts.get(i);

			}

			// letzte zurücksetzten
			lastParts = new ArrayList<Term>();
			lastParts.add(new Multiplikation(temp, Multiplikation.MULT));
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

			// Fall 1: einfache Zahl
			if (analysator.isNumber(stringTerm.charAt(progress))) {

				printlog("Number at " + progress);
				genNumber();
				printlog("Zahl abgeschlossen. Mache weiter in genTeile()");

			}

			// Fall 2: sin,cos,tan

			else if (analysator.isSinCosTan(stringTerm, progress)) {

				printlog("Sin/Cos/Tan at " + progress);
				genSinCosTan();
				printlog("SinCosTan agbeschlossen, mache weiter in genTeile()");

			} else if (analysator.isAnyRoot(stringTerm, progress) == 1) {

				printlog("nthRoot at " + progress);

				genNthRoot();

				printlog("Wurzel abgeschlossen, mache weiter in genTeile()");

			} else if (analysator.isAnyRoot(stringTerm, progress) == 0) {

				printlog("Root at " + progress);

				genRoot();

				printlog("Wurzel abgeschlossen, mache weiter in genTeile()");

			} else if (stringTerm.charAt(progress) == '(') {

				printlog("Parenthesis at " + progress);
				genParenthesis();
				printlog("Klammer abgeschlossen, mache weiter in genTeile()");

			} else if (stringTerm.charAt(progress) == '+') {

				// Die Rechenzeichen dürfen nicht das letzte sein, was
				// durchlaufen wird.

				printlog("+ found");
				progress++;
				lastTimeCollect = thisTimeCollect;
				thisTimeCollect = NO;
				nextAlgebraicSign = 1;
				printlog("mache weiter in genTeile()");

			} else if (stringTerm.charAt(progress) == '-') {

				printlog("- found");
				progress++;
				nextAlgebraicSign = -1;
				lastTimeCollect = thisTimeCollect;
				thisTimeCollect = NO;
				printlog("mache weiter in genTeile()");

			} else if (stringTerm.charAt(progress) == '/') {

				printlog("/ found");
				progress++;
				lastTimeCollect = thisTimeCollect;
				thisTimeCollect = DIV;
				nextAlgebraicSign = 1;
				printlog("mache weiter in genTeile()");

			} else if (stringTerm.charAt(progress) == '*') {

				printlog("* found");
				progress++;
				lastTimeCollect = thisTimeCollect;
				thisTimeCollect = MULT;
				nextAlgebraicSign = 1;
				printlog("lsammeln = " + lastTimeCollect);
				printlog("sammeln = " + thisTimeCollect);
				printlog("mache weiter in genTeile()");

			} else if (stringTerm.charAt(progress) == '^') {
				printlog("potence at " + progress);
				progress++;
				/*
				 * Zwei Möglichekeiten: Entweder hier ist eine Klammer, oder
				 * hier ist keine Klammer, ich habe mich jetzt zuerst mal dazu
				 * entschieden, heir keine zu verwenden. Wichtig ist, dass dann
				 * hier alles in einer Klammer steht.
				 * 
				 * Ich hatte das Problem, dass hier die Rehnung 25^3 war, was
				 * aber keine Klammer beinhaltet. Ich könnte mich noch dazu
				 * entscheiden, hier eine Fallunterscheidung zu machen.
				 */

				String temp = getParenthesisString();
				printlog("Test " + temp);
				lastParts.get(lastParts.size() - 1).setExponent(new Parenthesis(temp));
				printlog("Exponent abgeschlossen, mache weiter in genTeile()");

			} else {

				printlog("genTeile() Buchstaben nicht gefunden, returning");
				return;

			}
		}

		/*
		 * Dieser Teil macht leider nicht das selbe wie die Teilverarbeitung.
		 */

		printlog("Bringe letztes Element in rechnungsbestandteil");
		printlog("kritischer Teil, teilVerarbeitung in genTeile()");

		if (thisTimeCollect == MULT && lastTimeCollect == MULT) {

			printlog("Mal und Mal.");
			// erst mal alle Bestandteile sammeln
			Term[] temp = new Term[lastParts.size()];
			for (int i = 0; i < temp.length; i++) {

				temp[i] = lastParts.get(i);

			}

			termParts.add(new Multiplikation(temp, Multiplikation.MULT));

		} else if (thisTimeCollect == DIV && lastTimeCollect == DIV) {

			printlog("Geteilt und Geteilt.");
			// erst mal alle Bestandteile sammeln
			Term[] temp = new Term[lastParts.size()];
			for (int i = 0; i < temp.length; i++) {

				temp[i] = lastParts.get(i);

			}

		} else if (thisTimeCollect == MULT && lastTimeCollect != MULT) {

			printlog("Mal und nicht Mal");
			termParts.add(new Multiplikation(new Term[] { lastParts.get(0), lastParts.get(1) }, Multiplikation.MULT));

		} else if (thisTimeCollect == DIV && lastTimeCollect != DIV) {

			printlog("Geteilt und nicht Geteilt.");
			termParts.add(new Multiplikation(new Term[] { lastParts.get(0), lastParts.get(1) }, Multiplikation.DIV));

		} else if (thisTimeCollect == NO && lastTimeCollect == NO) {

			printlog("Nein und Nein.");
			termParts.add(lastParts.get(0));

		}

		else {

			printlog("Bei der teilVerarbeitung in genTeile() etwas vergessen!!!!!!!!!!!!!!!!!!!!!!!!!!");

		}

		printlog("Fertig! :)");

	}
}