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
	
	private static boolean log = true;

	private static Scanner sc = new Scanner(System.in);

	public static void main(String args[]) {

		System.out.println("Boomings Taschenrechner");
		System.out.println("v0.1.0 beta");

		System.out.println("Wollen sie eine Rechnung eingeben? [J/N]");
		String eing = sc.nextLine();
		
		if (eing.equals("J") || eing.equals("j")) {

			eingabe();

		}

		System.out.println("Programm beendet. ");

	}

	public static void eingabe() {

		boolean weitermachen = true;

		while (weitermachen) {

			System.out.println();
			System.out.println();
			System.out.println("Bitte geben sie ihre Rechnung ein.");
			String s = sc.nextLine();
			System.out.println("Rechnung: " + s);
			Term r = new Term(s);
			try {

				double ergebnis = r.calculate();
				System.out.println("Ergebnis: " +  ergebnis);

			} catch (Exception e) {

				System.out.println("Uuuuups, da hat es wohl einen Fehler gegeben...");
				System.out.println(e);

			}

			System.out.println("Wollen sie nochmal eine Rechnung eingeben? [J/N]");
			String eing = sc.nextLine();
			if (!(eing.equals("J") || eing.equals("j"))) {

				weitermachen = false;

			}

		}

	}
}