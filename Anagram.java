/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent", "listen")); // true
		System.out.println(isAnagram("William Shakespeare", "I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie", "Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle", "I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));

		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");

		// Performs a stress test of randomAnagram
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass)
				break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		str1 = removeSpace(preProcess(str1));
		str2 = removeSpace(preProcess(str2));
		if (str1.length() != str2.length())
			return false;

		for (int i = 0; i < str1.length(); i++) {
			int count1 = 0;
			int count2 = 0;
			char c = str1.charAt(i);
			for (int j = 0; j < str1.length(); j++) {
				if (str1.charAt(j) == c)
					count1++;
				if (str2.charAt(j) == c)
					count2++;
			}
			if (count1 != count2)
				return false;

		}
		return true;
	}

	// Returns a preprocessed version of the given string: all the letter characters
	// are converted
	// to lower-case, and all the other characters are deleted, except for spaces,
	// which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		String lowerCase = "";
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				String change = "" + (char) (c + 32);
				lowerCase = lowerCase + change;
			} else if ((c >= 'a' && c <= 'z') || c == ' ') {
				lowerCase = lowerCase + c;
			}
		}
		return lowerCase;
	}

	public static String removeSpace(String str) {
		String noSpace = "";
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c != ' ') {
				noSpace += c;
			}

		}
		return noSpace;
	}

	// Returns a random anagram of the given string. The random anagram consists of
	// the same
	// characters as the given string, re-arranged in a random order.
	public static String randomAnagram(String str) {
		String theRandom = "";
		int theLength = str.length();
		for (int i = 0; i < theLength; i++) {
			int index = (int) (Math.random() * str.length());
			theRandom = theRandom + str.charAt(index);
			str = str.substring(0, index) + str.substring(index + 1);
		}

		return theRandom;
	}
}