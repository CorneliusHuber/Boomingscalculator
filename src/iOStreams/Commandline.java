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

package iOStreams;

import java.util.Scanner;

import analysis.Term;

public class Commandline {
	
	private static Scanner sc = new Scanner(System.in);

	public static void main(String args[]) {

		System.out.println("BoomingsCalculator");
		System.out.println("v0.1.0 beta");

		System.out.println("Do you want to type a term? [Y/N]");
		String input = sc.nextLine();
		
		if (input.equals("y") || input.equals("Y")) {

			inputPhase();

		}

		System.out.println("Program terminated. ");

	}

	public static void inputPhase() {

		boolean continuing = true;

		while (continuing) {

			System.out.println();
			System.out.println();
			System.out.println("Please write term.");
			String input = sc.nextLine();
			System.out.println("Term: " + input);
			Term term = new Term(input);
			try {

				double result = term.calculate();
				System.out.println("Result: " +  result);

			} catch (Exception e) {

				System.out.println("Looks like an error occured");
				System.out.println(e);

			}

			System.out.println("Do you want to type another term? [Y/N]");
			input = sc.nextLine();
			if (!(input.equals("y") || input.equals("Y"))) {

				continuing = false;

			}

		}

	}
}