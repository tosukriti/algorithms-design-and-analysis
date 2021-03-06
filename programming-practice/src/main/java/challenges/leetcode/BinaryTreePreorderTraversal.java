package challenges.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 144. Binary Tree Preorder Traversal
 * 
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * 
 * For example:
 * Given binary tree {1,#,2,3},
 * 
 * 	   1
 * 	    \
 * 	     2
 * 	    /
 * 	   3
 * 
 * 	return [1,2,3].
 * 
 * Note: Recursive solution is trivial, could you do it iteratively?
 * 
 * @author Hxkandwal
 */
public class BinaryTreePreorderTraversal extends AbstractCustomTestRunner {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		Stack<TreeNode> stk = new Stack<>();
		stk.push(root);
		while (!stk.isEmpty()) {
			TreeNode node = stk.pop();
			if (node == null) continue;
			ans.add(node.val);
			stk.push(node.right);
			stk.push(node.left);
		}
		return ans;
	}
	
}
