package analysis;

public class StringUtils {

	/**
	 * Same as substring from org.apache.commons.lang.StringUtils . I added the
	 * varargs
	 * 
	 * @param input
	 * @param endChar
	 * @return
	 */
	public String stringUntilCharOrEnd(String input, char endChar) {

		int progress = 0;
		String returntxt = "";

		while (input.charAt(progress) == endChar && progress < input.length()) {

			returntxt = returntxt + input.charAt(progress);
			progress++;

		}

		return returntxt;

	}

	/**
	 * Same as stringbisCharoderEnde(String input, char endChar), just more
	 * endchars.
	 * 
	 * @param input
	 * @param endChar
	 * @return
	 */
	public String stringUntilCharOrEnd(String input, char... endChar) {

		int progress = 0;
		String returntxt = "";

		while (notEqualsAnyOf(input.charAt(progress), endChar) && progress < input.length()) {

			returntxt = returntxt + input.charAt(progress);

		}

		return returntxt;

	}

	/**
	 * Used to compare one char to a list of others. Checks whether the first char
	 * does not equal any of the following chars.
	 * 
	 * @param compareTo
	 * @param anyOfThese
	 * @return
	 */
	private boolean notEqualsAnyOf(char compareTo, char... anyOfThese) {

		for (int i = 0; i < anyOfThese.length; i++) {

			if (anyOfThese[i] == compareTo) {

				return false;

			}

		}

		return true;
	}

	/**
	 * Used to compare one char to a list of others. Checks whether the first char
	 * does equal any of the following chars, Opposite to notEqualsanyof(...)
	 * 
	 * @param compareTo
	 * @param anyofthese
	 * @return
	 */
	private boolean equalsAnyOf(char compareTo, char... anyofthese) {

		for (int i = 0; i < anyofthese.length; i++) {

			if (anyofthese[i] == compareTo) {

				return true;

			}

		}

		return false;

	}

	public String removeallbut(String input, char... allbut) {

		String returntxt = "";
		int progress = 0;

		while (progress < input.length()) {

			if (equalsAnyOf(input.charAt(progress), allbut)) {

				returntxt = returntxt + input.charAt(progress);

			}

			progress++;

		}

		return returntxt;

	}

	public String removeall(String input, char... remove) {

		for (int i = 0; i < remove.length; i++) {

			String removes = "" + remove[i];

			input = input.replaceAll(removes, "");

		}

		return input;

	}

	/**
	 * Gets the String in between brackets. First char needs to be <code> [ </code>,
	 * else null will be returned.
	 * 
	 * For <code> [5+4 + V [3] (4+4)] + 4 * 3 * 4 </code>will
	 * <code>  5+4 + V [3] (4+4) </code> be returned.
	 * 
	 * @param input
	 * @return
	 */
	public String everythInSquaredBrackets(String input) {

		if (input.charAt(0) != '[') {

			return null;

		}

		int openBracket = 0;
		int closedBracket = 0;

		for (int progress = 0; progress < input.length(); progress++) {

			char tested = input.charAt(progress);

			if (tested == '[') {

				openBracket++;

			} else if (tested == ']') {

				closedBracket++;

			}

			if (closedBracket == openBracket) {

				return input.substring(1, progress);

			}

		}

		return null;

	}
	
	
	/**
	 * Gets the String in between parentheses. First char needs to be <code> ( </code>,
	 * else null will be returned.
	 * 
	 * For <code> (5+4 + V [3] (4+4)) + 4 * 3 * 4 </code>will
	 * <code>  5+4 + V [3] (4+4) </code> be returned.
	 * 
	 * @param input
	 * @return
	 */
	public String everythingInParentheses(String input) {

		if (input.charAt(0) != '(') {

			return null;

		}

		int openParenthesis = 0;
		int closedParenthesis = 0;

		for (int progress = 0; progress < input.length(); progress++) {

			char tested = input.charAt(progress);

			if (tested == '(') {

				openParenthesis++;

			} else if (tested == ')') {

				closedParenthesis++;

			}

			if (closedParenthesis == openParenthesis) {

				return input.substring(1, progress);

			}

		}

		return null;

	}

	/**
	 * Searches the end of the brackets, returns the index of that char which is
	 * <code> ] </code>. If it did not work, <code> -1 </code> will be returned.
	 * 
	 * @param input
	 * @return
	 */
	public int endBrackets(String input) {

		int openBrackets = 0;
		int closedBrackets = 0;

		for (int progress = 0; progress < input.length(); progress++) {

			char tested = input.charAt(progress);

			if (tested == '[') {

				openBrackets++;

			} else if (tested == ']') {

				closedBrackets++;

				if (closedBrackets == openBrackets) {

					return progress;

				}

			}

		}

		return -1;

	}

	/**
	 * Searches the end of the parentheses, returns the index of that char which is
	 * <code> ) </code>. If it did not work, <code> -1 </code> will be returned.
	 *  
	 * @param input
	 * @return
	 */
	public int endParentheses(String input) {

		int openParenthesis = 0;
		int closedParenthesis = 0;

		for (int progress = 0; progress < input.length(); progress++) {

			char tested = input.charAt(progress);

			if (tested == '(') {

				openParenthesis++;

			} else if (tested == ')') {

				closedParenthesis++;

				if (closedParenthesis == openParenthesis) {

					return progress;

				}

			}

		}

		return -1;

	}

}
