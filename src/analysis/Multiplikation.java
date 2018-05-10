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

import iOStreams.Outputable;

public class Multiplikation extends Term {

	private char selection;

	Multiplikation(Term[] termParts, char selection) {

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
		if (selection == MULT) {

			printlog("Multiplikation with " + termParts.length + " parts is being made.");

		} else {

			printlog("Division with " + termParts.length + " parts is being made.");

		}

		unindent();

	}

	@Override
	public String returnStringTerm() {

		String rechnungsstring = termParts.get(0).returnStringTerm();

		for (int i = 1; i < termParts.size(); i++) {

			if (selection == MULT) {

				rechnungsstring = rechnungsstring + '*';

			} else if (selection == DIV) {

				rechnungsstring = rechnungsstring + '/';

			} else {

				printlog("Hier gibt es ein größeres Problem mit gebeRechnungaus() in MalRechnung.");

			}

			rechnungsstring = rechnungsstring + termParts.get(i).returnStringTerm();

		}

		return rechnungsstring;

	}

	@Override
	public String returnStringTermReverse() {

		/*
		 * Kurzer Kommentar dazu: Hier kann man keinen String bekommen, weil man keinen
		 * String übergeben bekommt, darum muss man in gebeRechnungaus() genau das selbe
		 * machen wie hier deshalb verleihe ich dem dieser Methode nur einen anderen
		 * Anstrich.
		 */

		return returnStringTerm();

	}

	@Override
	public double calculate() {

		double ergebnis = termParts.get(0).calculate();

		if (selection == MULT) {

			for (int i = 1; i < termParts.size(); i++) {
				/*
				 * Hier ist das 1, weil der Teil 0 bereits ausgerechnet wurde und in ergebnis
				 * steht.
				 */

				ergebnis *= termParts.get(i).calculate();
				printlog("Malrechnung fertig ausgerechnet.");

			}

		} else if (selection == DIV) {

			for (int i = 1; i < termParts.size(); i++) {
				/*
				 * Hier ist das 1, weil der Teil 0 bereits ausgerechnet wurde und in ergebnis
				 * steht.
				 */

				ergebnis /= termParts.get(i).calculate();
				printlog("Geteiltrechnung fertig ausgerechnet.");

			}

		} else {

			printlog("Obacht beim ausrechenn der Mal/Geteiltrechnung hat es wohl einen Fehler gegeben.");

		}

		return ergebnis;

	}

}