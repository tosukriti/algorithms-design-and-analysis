package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 655. Print Binary Tree
 *
 * Print a binary tree in an m*n 2D string array following these rules:
 *
 *  1.  The row number m should be equal to the height of the given binary tree.
 *  2.  The column number n should always be an odd number.
 *  3.  The root node's value (in string format) should be put in the exactly middle of the first row it can be put.
 *      The column and the row where the root node belongs will separate the rest space into two parts (left-bottom
 *      part and right-bottom part). You should print the left subtree in the left-bottom part and print the right
 *      subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size.
 *      Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but
 *      still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then
 *      you don't need to leave space for both of them.
 *  4.  Each unused space should contain an empty string "".
 *  5.  Print the subtrees following the same rules.
 *
 * Example 1:
 *      Input:
 *                      1
 *                     /
 *                    2
 *
 *      Output:
 *                 [["", "1", ""],
 *                  ["2", "", ""]]
 *
 * Example 2:
 *      Input:
 *                      1
 *                     / \
 *                    2   3
 *                    \
 *                    4
 *
 *      Output:
 *                [["", "", "", "1", "", "", ""],
 *                 ["", "2", "", "", "", "3", ""],
 *                 ["", "", "4", "", "", "", ""]]
 *
 * Example 3:
 *      Input:
 *                      1
 *                     / \
 *                    2   5
 *                   /
 *                  3
 *                 /
 *                4
 *
 *      Output:
 *                  [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 *                   ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 *                   ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 *                   ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 *
 * Note: The height of binary tree is in the range of [1, 10].
 *
 * Created by Hxkandwal
 */
public class PrintBinaryTree extends AbstractCustomTestRunner {

    private static PrintBinaryTree _instance = new PrintBinaryTree();

    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public List<List<String>> _printTree(TreeNode root) {
        List<List<String>> ans = new ArrayList<>();
        int h = height (root), w = (int) Math.pow (2, h) - 1;
        List<String> tuple = new ArrayList<>();
        for (int c = 0; c < w; c ++) tuple.add ("");
        for (int r = 0; r < h; r ++) ans.add (new ArrayList<> (tuple));
        fill (ans, root, 0, 0, w + 1);
        return ans;
    }

    private void fill (List<List<String>> ans, TreeNode n, int index, int l, int r) {
        if (n == null) return;
        ans.get (index).set ((l + r)/2 - 1, String.valueOf(n.val));
        fill (ans, n.left, index + 1, l, (l + r)/2);
        fill (ans, n.right, index + 1, (l + r + 1)/2, r);
    }

    private int height (TreeNode n) {
        if (n == null) return 0;
        return 1 + Math.max (height (n.left), height (n.right));
    }

    // driver method
    public static void main(String[] args) {
        testCase1 ();
        testCase2 ();
    }

    private static void testCase1 () {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);

        _instance.runTest(root, Arrays.asList(Arrays.asList("", "1", ""), Arrays.asList("2", "", "")));
    }

    private static void testCase2 () {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);

        root.right = new TreeNode(6);;
        root.right.right = new TreeNode(7);

        _instance.runTest(root, Arrays.asList(
                Arrays.asList("", "", "", "5", "", "", ""),
                Arrays.asList("", "3", "", "", "", "6", ""),
                Arrays.asList("2", "", "4", "", "", "", "7"))
        );
    }


    public void runTest(final TreeNode root, final List<List<String>> expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { root });

        for (Object answer : answers)
            assertThat((List<List<String>>) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
