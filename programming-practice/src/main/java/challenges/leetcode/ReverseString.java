package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 344. Reverse String
 * 
 * Write a function that takes a string as input and returns the string
 * reversed.
 * 
 * Example:
 * 
 * Given s = "hello", return "olleh"
 * 
 * @author Hxkandwal
 *
 */
public class ReverseString extends AbstractCustomTestRunner {

	private static ReverseString _instance = new ReverseString();
	
	private ReverseString() {}

	public String _reverseString(String s) {
		if (s.length() == 0) return s;
        char [] chArr = s.toCharArray();
        for (int idx = 0; idx < chArr.length / 2; idx ++) {
            char ch = chArr [idx];
            chArr [idx] = chArr [chArr.length - 1 - idx];
            chArr [chArr.length - 1 - idx] = ch;
        }
        return String.valueOf(chArr);
	}
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("", "");
		_instance.runTest("abc", "cba");
		_instance.runTest("abcd", "dcba");
		_instance.runTest("a", "a");
		_instance.runTest("aa", "aa");
		_instance.runTest("hello", "olleh");
	}
	
	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
