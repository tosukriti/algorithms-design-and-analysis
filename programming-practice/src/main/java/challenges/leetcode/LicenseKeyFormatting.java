package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 482. License Key Formatting
 * 
 * Now you are given a string S, which represents a software license key which we would like to format. The string S is composed of alpha-numerical characters 
 * and dashes. The dashes split the alpha-numerical characters within the string into groups. (i.e. if there are M dashes, the string is split into M+1 groups). 
 * The dashes in the given string are possibly misplaced.
 * 
 * We want each group of characters to be of length K (except for possibly the first group, which could be shorter, but still must contain at least one character). 
 * To satisfy this requirement, we will reinsert dashes. 
 * 
 * Additionally, all the lower case letters in the string must be converted to upper case.
 * 
 * So, you are given a non-empty string S, representing a license key to format, and an integer K. And you need to return the license key formatted according to 
 * the description above.
 * 
 * Example 1:
 * 		Input: S = "2-4A0r7-4k", K = 4
 * 		Output: "24A0-R74K"
 * 		Explanation: The string S has been split into two parts, each part has 4 characters.
 * 
 * Example 2:
 * 		Input: S = "2-4A0r7-4k", K = 3
 * 		Output: "24-A0R-74K"
 * 		Explanation: The string S has been split into three parts, each part has 3 characters except the first part as it could be 
 * 					 shorter as said above.
 * 
 * Note:
 * 		1. The length of string S will not exceed 12,000, and K is a positive integer.
 * 		2. String S consists only of alpha-numerical characters (a-z and/or A-Z and/or 0-9) and dashes(-).
 * 		3. String S is non-empty.
 * 
 * @author Hxkandwal
 */
public class LicenseKeyFormatting extends AbstractCustomTestRunner {
	
	private static LicenseKeyFormatting _instance = new LicenseKeyFormatting();

	public String _licenseKeyFormattingOnePass(String S, int K) {
		StringBuilder ans = new StringBuilder();
		for (int idx = S.length() - 1, k = 0; idx >= 0; idx --) {
			if (S.charAt(idx) == '-') continue;
			ans.append(String.valueOf(S.charAt(idx)).toUpperCase());
			k ++;
			if (k % K == 0) ans.append('-');
		}
		if (ans.length() > 0 && ans.charAt(ans.length() - 1) == '-') ans.deleteCharAt(ans.length() - 1);
		return ans.reverse().toString();
	}

	public String _licenseKeyFormatting(String S, int K) {
		StringBuilder ans = new StringBuilder();
		for (int idx = 0; idx < S.length(); idx ++) if (S.charAt(idx) != '-') ans.append(S.charAt(idx));

		int idx = ans.length();
		while (idx >= 0) {
			idx -= K;
			if (idx >= 0) ans.insert(idx, '-');
		}
		if (ans.length() > 0 && ans.charAt(0) == '-') ans.deleteCharAt(0);
		return ans.toString().toUpperCase();
	}

	public static String _licenseKeyFormatting2(String s, int k) {
		StringBuilder sb = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--)
			if (s.charAt(i) != '-')
				sb.append(sb.length() % (k + 1) == k ? '-' : "").append(s.charAt(i));
		return sb.reverse().toString().toUpperCase();
	}
	 
	// driver method
	public static void main(String[] args) {
		_instance.runTest("2-4A0r7-4k", 4, "24A0-R74K");
		_instance.runTest("2-4A0r7-4k", 3, "24-A0R-74K");
		_instance.runTest("--a-a-a-a--", 2, "AA-AA");
		_instance.runTest("---", 3, "");
	}

	public void runTest(final String input, final int k, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input, k });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
