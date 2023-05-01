import java.util.Stack;

public class ReversingStringMethodologyOne {

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

	// reverse the sub string between () like an array of chars using temp
	public static void reverseWord(char word[], int x, int y) {
		if (x < y) {
			char ch = word[x];
			word[x] = word[y];
			word[y] = ch;
			reverseWord(word, x + 1, y - 1);
		}

	}

	/**
	 * applies reverse on all available String between parentheses ()
	 * 
	 * @param string
	 * @return string
	 * @author Mustafa
	 */
	public static String prepareString(String string) {
		boolean validString = validateString(string);
		if (validString) {
			int length = string.length();
			Stack<Integer> st = new Stack<Integer>();
			for (int i = 0; i < length; i++) {
				if (string.charAt(i) == '(') {
					st.push(i);
				}

				if (string.charAt(i) == ')') {
					if (!st.isEmpty()) {
						char[] word = string.toCharArray();
						reverseWord(word, st.peek() + 1, i - 1);
						st.pop();
						string = String.copyValueOf(word);
					}

				}
			}

			return string;
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
