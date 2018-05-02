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