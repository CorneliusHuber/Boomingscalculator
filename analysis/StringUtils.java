package analysis;

public class StringUtils {

	/**
	 * Selbe hier wie substringBefore aus org.apache.commons.lang.StringUtils .
	 * Das mit Varargs ist aber eine Sache von mir.
	 * 
	 * @param eingabe
	 * @param endchar
	 * @return
	 */
	public String stringbisCharoderEnde(String eingabe, char endchar) {

		int fortschritt = 0;
		String returntxt = "";

		while (eingabe.charAt(fortschritt) == endchar && fortschritt < eingabe.length()) {

			returntxt = returntxt + eingabe.charAt(fortschritt);
			fortschritt++;

		}

		return returntxt;

	}

	/**
	 * das Selbe wie oben, nur mit mehreren Endchars
	 * 
	 * @param eingabe
	 * @param endchar
	 * @return
	 */
	public String stringbisCharoderEnde(String eingabe, char... endchar) {

		int fortschritt = 0;
		String returntxt = "";

		while (notEqualsanyof(eingabe.charAt(fortschritt), endchar) && fortschritt < eingabe.length()) {

			returntxt = returntxt + eingabe.charAt(fortschritt);

		}

		return returntxt;

	}

	/**
	 * Zum Vergleichen von Chars.
	 * 
	 * @param zuvergleichen
	 * @param anyofthese
	 * @return
	 */
	private boolean notEqualsanyof(char zuvergleichen, char... anyofthese) {

		for (int i = 0; i < anyofthese.length; i++) {

			if (anyofthese[i] == zuvergleichen) {

				return false;

			}

		}

		return true;
	}

	/**
	 * Hier letztendlich das Gegenteil zum oberen.
	 * 
	 * @param zuvergleichen
	 * @param anyofthese
	 * @return
	 */
	private boolean equalsanyof(char zuvergleichen, char... anyofthese) {

		for (int i = 0; i < anyofthese.length; i++) {

			if (anyofthese[i] == zuvergleichen) {

				return true;

			}

		}

		return false;

	}

	public String removeallbut(String eingabe, char... allbut) {

		String returntxt = "";
		int fortschritt = 0;

		while (fortschritt < eingabe.length()) {

			if (equalsanyof(eingabe.charAt(fortschritt), allbut)) {

				returntxt = returntxt + eingabe.charAt(fortschritt);

			}

			fortschritt++;

		}

		return returntxt;

	}
	
	public String removeall(String eingabe, char...remove) {
		
		for (int i = 0; i < remove.length; i++) {
			
			String removes = "" + remove[i];
			
			eingabe = eingabe.replaceAll(removes, "");
			
		}
		

		return eingabe;
		
	}

	/**
	 * Hier kann man alles zwischen Zwei Klammern raus holen. Der erste Char
	 * muss ein '[' sein, sonst wird null zurück gegeben.
	 * 
	 * Fuer "[5+4 + V [3] (4+4)] + 4 * 3 * 4" wird "5+4 + V [3] (4+4)" zurueck
	 * gegeben.
	 * 
	 * @param eingabe
	 * @return
	 */
	public String everythInSquaredBrackets(String eingabe) {

		if (eingabe.charAt(0) != '[') {

			return null;

		}

		int eckigeKlammernauf = 0;
		int eckigeKlamemrnzu = 0;

		for (int fortschritt = 0; fortschritt < eingabe.length(); fortschritt++) {

			char zupruefen = eingabe.charAt(fortschritt);

			if (zupruefen == '[') {

				eckigeKlammernauf++;

			} else if (zupruefen == ']') {

				eckigeKlamemrnzu++;

			}

			if (eckigeKlamemrnzu == eckigeKlammernauf) {

				return eingabe.substring(1, fortschritt);

			}

		}

		return null;

	}

	/**
	 * Sucht das Ende einer eckigen Klammer, gibt es zurück. Wenn es nicht
	 * funktioniert hat, dann wir -1 zurück gegeben.
	 * 
	 * @param eingabe
	 * @return
	 */
	public int endBrackets(String eingabe) {

		int eckigeKlammernauf = 0;
		int eckigeKlamemrnzu = 0;

		for (int fortschritt = 0; fortschritt < eingabe.length(); fortschritt++) {

			char zupruefen = eingabe.charAt(fortschritt);

			if (zupruefen == '[') {

				eckigeKlammernauf++;

			} else if (zupruefen == ']') {

				eckigeKlamemrnzu++;

				if (eckigeKlamemrnzu == eckigeKlammernauf) {

					return fortschritt;

				}
				
			}
			
		}
		
		return -1;
	
	}
	
	public int endParenthesis(String eingabe) {

		int rundeKlammernauf = 0;
		int rundeKlamemrnzu = 0;

		for (int fortschritt = 0; fortschritt < eingabe.length(); fortschritt++) {

			char zupruefen = eingabe.charAt(fortschritt);

			if (zupruefen == '(') {

				rundeKlammernauf++;

			} else if (zupruefen == ')') {

				rundeKlamemrnzu++;
				
				if (rundeKlamemrnzu == rundeKlammernauf) {

					return fortschritt;

				}
				

			}

			

		}

		return -1;
		
	}
	
	public String allesinrundeKlammern(String eingabe) {
		
		if (eingabe.charAt(0) != '(') {

			return null;

		}

		int klammernauf = 0;
		int klamemrnzu = 0;

		for (int fortschritt = 0; fortschritt < eingabe.length(); fortschritt++) {

			char zupruefen = eingabe.charAt(fortschritt);

			if (zupruefen == '(') {

				klammernauf++;

			} else if (zupruefen == ')') {

				klamemrnzu++;

			}

			if (klamemrnzu == klammernauf) {

				return eingabe.substring(1, fortschritt);

			}

		}

		return null;
		
	}

}
