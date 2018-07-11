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

package iOStreams;

import analysis.Term;


public class Starter {
	
	public static void main(String args[]) {
		try {

			Term term = new Term("V(16)*4/5");
			double ergebnis = term.calculate();
			System.out.println("Ergebnis ist angeblich: " + ergebnis);

		} catch (Exception e) {

			System.out.println(e);

		}

	}

}