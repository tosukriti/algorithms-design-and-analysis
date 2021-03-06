package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

/**
 * 859. Buddy Strings
 *
 * Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.
 *
 * Example 1:
 *      Input: A = "ab", B = "ba"
 *      Output: true
 *
 * Example 2:
 *      Input: A = "ab", B = "ab"
 *      Output: false
 *
 * Example 3:
 *      Input: A = "aa", B = "aa"
 *      Output: true
 *
 * Example 4:
 *      Input: A = "aaaaaaabc", B = "aaaaaaacb"
 *      Output: true
 *
 * Example 5:
 *      Input: A = "", B = "aa"
 *      Output: false
 *
 * Note:
 *  0 <= A.length <= 20000
 *  0 <= B.length <= 20000
 *  A and B consist only of lowercase letters.
 *
 * @author hxkandwal
 */
public class BuddyStrings extends AbstractCustomTestRunner {

    private static BuddyStrings _instance = new BuddyStrings();

    public boolean _buddyStringsBetter(String A, String B) {
        if (A.length() != B.length()) return false;
        if (A.equals(B)) {
            int [] ch = new int [256];
            for (char c : A.toCharArray()) if (++ ch [c] >= 2) return true;
            return false;
        } else {
            int first = -1, second = -1;
            for (int idx = 0; idx < A.length(); idx ++) {
                char ac = A.charAt (idx), bc = B.charAt(idx);
                if (ac != bc) {
                    if (first == -1) first = idx;
                    else if (second == -1) second = idx;
                    else return false;
                }
            }
            return !(first == -1 || second == -1 || A.charAt(first) != B.charAt(second) || A.charAt(second) != B.charAt(first));
        }
    }

    public boolean _buddyStrings(String A, String B) {
        if (A.length() != B.length()) return false;
        if (A.equals(B)) {
            int [] ch = new int [256];
            for (char c : A.toCharArray()) if (++ ch [c] >= 2) return true;
            return false;
        } else {
            Map<Character, Character> map = new HashMap<>();
            for (int idx = 0; idx < A.length(); idx ++) {
                char ac = A.charAt(idx), bc = B.charAt(idx);
                if (ac != bc) {
                    if (map.size() == 0) map.put (ac, bc);
                    else if (map.size() == 1) {
                        Character key = map.entrySet().iterator().next().getKey();
                        if (!((key == ac && map.get (key) == bc) || (key == bc && map.get (key) == ac))) return false;
                        map.put (key == ac ? bc : ac, key);
                    }
                    else return false;
                }
            }
            return map.size() == 2;
        }
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest("ab", "ba", true);
    }

    public void runTest(final String A, final String B, final boolean expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { A, B });

        for (Object answer : answers)
            assertThat((Boolean) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
