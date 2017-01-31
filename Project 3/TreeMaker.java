import java.util.PriorityQueue;

public class TreeMaker implements ITreeMaker, IHuffConstants {
	private CharCounter chars;
	private TreeNode root;
	private PriorityQueue<TreeNode> tree;
	private String codes[];
	
	TreeMaker() {
		chars = null;
		tree = new PriorityQueue<TreeNode>();
		codes = new String[ALPH_SIZE + 1];
	}
	
	TreeMaker(CharCounter ch) {
		chars = ch;
		tree = new PriorityQueue<TreeNode>();
		codes = new String[ALPH_SIZE + 1];
	}
	
	/**
	 * Add the PSEUDO_EOF into the tree.
	 * Need to change the tree by bringing down the leftmost
	 * node, changing it's character encoding and adding the
	 * EOF into the tree to be read
	 */
	private void addEOF() {
		String EOFcode = new String();
		TreeNode EOF = new TreeNode(PSEUDO_EOF, 1);
		TreeNode curr = root;
		
		while (curr.myLeft != null) {
			EOFcode += "0";
			curr = curr.myLeft;
		}
		codes[curr.myValue] += "0";
		TreeNode slot = new TreeNode(-1, 1 + curr.myWeight, curr, EOF);
		EOFcode += "1";
		curr = root;
		while (curr.myLeft.myLeft != null) {
			curr = curr.myLeft;
		}
		curr.myLeft = slot;
		codes[256] = EOFcode;
		
	}
	
	public void initialize(CharCounter ch) {
		chars = ch;
		fillForest();
		assembleCodes(makeRoot(), new String());
		addEOF();
	}
	
	public void fillForest() {
		TreeNode newTree;
		TreeNode leftNode;
		TreeNode rightNode;
		int i = 0;
		
		for (i = 0; i < chars.size(); ++i) {
			if (chars.getCount(i) > 0) {
				// add a new node with value = ascii value
				// and number of occurrences being weight
				tree.add(new TreeNode(i, chars.getCount(i)));
			}
		}
		while (tree.size() > 1) {
			// remove 2 lowest nodes
			leftNode = tree.remove();
			rightNode = tree.remove();
			newTree = new TreeNode(-1, leftNode.myWeight + rightNode.myWeight);
			newTree.myLeft = leftNode;
			newTree.myRight = rightNode;
			tree.add(newTree);
		}
		// tree should be constructed now in tree variable
	}
	
	/**
	 * Used to see contents of Priority queue "tree".
	 * For debugging use only.
	 */
	public void viewTree() {
		for (TreeNode t : tree) {
			System.out.println(t);
		}
	}
	
	public String[] getCodes() {
		return codes;
	}
	
	public void assembleCodes(TreeNode t, String code) {
		if (t == null) {
			return;
		}
		if (t.myLeft == null && t.myRight == null) {
			
			codes[t.myValue] = code;
		}
		else {
			assembleCodes(t.myLeft, code + "0");
			assembleCodes(t.myRight, code + "1");
		}
	}

	@Override
	public TreeNode makeRoot() {
		root = tree.peek();
		return root;
	}

}
