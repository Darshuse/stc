import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ReversingStringMethodologyTwo {

	public static boolean validateString(String stringValue) {
		if (stringValue == null || stringValue.isEmpty()) {
			System.out.println("String should not be null or empty");
			return false;
		}

		if (stringValue.length() < 1 || stringValue.length() > 2000) {
			System.out.println("String length should be less than or equal 2000 char");
			return false;
		}

		if (!stringValue.toLowerCase().equals(stringValue)) {
			System.out.println("String must contains lower chars only");
			return false;
		}
		return true;
	}

	// using stack and queue to reversed string parentheses ()
	public static char[] reverseWord(char word[], int x, int y) {
		String unArrangedString = new String(word).substring(x, y + 1);
		Stack<Character> result = new Stack<>();
		Queue<Character> arranedStringqueue = new LinkedList<>();
		String arranedString = "";
		for (char c : unArrangedString.toCharArray()) {
			result.push(c);
		}
		while (!result.isEmpty()) {
			arranedStringqueue.add(result.pop());
		}
		while (!arranedStringqueue.isEmpty()) {
			arranedString = arranedString + arranedStringqueue.remove();
		}
		String wordString = new String(word);
		wordString = wordString.replace(wordString.substring(x, y + 1), arranedString);
		word = wordString.toCharArray();
		return word;
	}

	/**
	 * applies reverse on all available String between parentheses ()
	 * 
	 * @param string
	 * @return string
	 * @author Mustafa
	 */
	public static String prepareString(String wordWantedAsReversed) {
		boolean validString = validateString(wordWantedAsReversed);
		if (validString) {
			int length = wordWantedAsReversed.length();
			Stack<Integer> st = new Stack<Integer>();
			for (int i = 0; i < length; i++) {
				if (wordWantedAsReversed.charAt(i) == '(') {
					st.push(i);
				}

				if (wordWantedAsReversed.charAt(i) == ')') {
					char[] word = wordWantedAsReversed.toCharArray();
					if (!st.isEmpty()) {
						word = reverseWord(word, st.peek() + 1, i - 1);
						st.pop();
						wordWantedAsReversed = String.copyValueOf(word);
					}

				}
			}

			return wordWantedAsReversed;
		} else {
			return "";
		}
	}

	public static void main(String[] args) {

		String str = "test(dda)(((sulp)";
		String result = prepareString(str);
		if (!result.equals("")) {
			System.out.println("input string is " + str + "\n while the result after reversing is " + result);
		}

	}

}
