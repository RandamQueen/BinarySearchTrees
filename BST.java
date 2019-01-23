
/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author Hannah Keating 
 *
 *************************************************************************/

//import java.util.NoSuchElementException;

//import javafx.scene.Node;

public class BST<Key extends Comparable<Key>, Value> {
	private Node root; // root of BST

	/**
	 * Private node class.
	 */
	private class Node {
		private Key key; // sorted by key
		private Value val; // associated data
		private Node left, right; // left and right subtrees
		private int N; // number of nodes in subtree

		public Node(Key key, Value val, int N) { // Node left and right not set
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	// is the symbol table empty?
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	// return number of key-value pairs in BST
	public int size() {
		return size(root);
	}

	// return number of key-value pairs in BST rooted at x
	private int size(Node x) {
		if (x == null) {
			return 0;
		} else {
			return x.N;
		}
	}

	/**
	 * Search BST for given key. Does there exist a key-value pair with given key?
	 *
	 * @param key
	 *            the search key
	 * @return true if key is found and false otherwise
	 */
	public boolean contains(Key key) {
		if (get(key) == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Search BST for given key. What is the value associated with given key?
	 *
	 * @param key
	 *            the search key
	 * @return value associated with the given key if found, or null if no such key
	 *         exists.
	 */
	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			return get(x.left, key);
		} else if (cmp > 0) {
			return get(x.right, key);
		} else {
			return x.val;
		}
	}

	/**
	 * Insert key-value pair into BST. If key already exists, update with new value.
	 *
	 * @param key
	 *            the key to insert
	 * @param val
	 *            the value associated with key
	 */
	public void put(Key key, Value val) {
		if (val == null) {
			delete(key);
			return;
		}
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null) {
			return new Node(key, val, 1);
		}

		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = put(x.left, key, val);
		} else if (cmp > 0) {
			x.right = put(x.right, key, val);
		} else {
			x.val = val;
		}
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}

	/**
	 * Tree height.
	 *
	 * Asymptotic worst-case running time using Theta notation: Θ(N) 
	 * 	The worst case would occur if all the element inserted into the BST
	 * 		where all children of the previously entered node. For example, if
	 * 		the alphabet was entered into the BST alphabetical. This would mean
	 * 		that the height of the BST would be N -1, aka one less than the
	 * 		amount of Nodes in the BST.  
	 * 
	 *
	 * @return the number of links from the root to the deepest leaf.
	 *
	 *         Example 1: for an empty tree this should return -1. Example 2: for a
	 *         tree with only one node it should return 0. Example 3: for the
	 *         following tree it should return 2. B / \ A C \ D
	 */
	public int height() {
		int height;
		if (isEmpty()) {
			height = -1;
			return height;
		} else if (root.N == 1) {
			height = 0;
			return height;
		}
		height = height(root);
		return height;
	}

	private int height(Node currentNode) {
		int returnHeight;
		if (currentNode.left == null && currentNode.right == null) // this means it's a leaf node
		{
			return 0;
		} else if (currentNode.left == null) {
			returnHeight = height(currentNode.right);
			returnHeight++;
			return returnHeight;
		} else if (currentNode.right == null) {
			returnHeight = height(currentNode.left);
			returnHeight++;
			return returnHeight;
		}
		int leftHeight = height(currentNode.left);
		int rightHeight = height(currentNode.right);
		if (rightHeight > leftHeight) {
			returnHeight = rightHeight;
			returnHeight++;
			return returnHeight;
		}
		returnHeight = leftHeight;
		returnHeight++;
		return returnHeight;
	}

	/**
	 * Median key. If the tree has N keys k1 < k2 < k3 < ... < kN, then their median
	 * key is the element at position (N+1)/2 (where "/" here is integer division)
	 *
	 * @return the median key, or null if the tree is empty. // TODO fill in the
	 *         correct implementation. The running time should be Theta(h), // where
	 *         h is the height of the tree.
	 */
	public Key median() {
		if (isEmpty()) {
			return null;
		}
		Key returnKey;
		if (root.N == 1) {
			returnKey = root.key;
			return returnKey;
		}
		// else
		returnKey = median(root);
		return returnKey;
	}

	private Key median(Node root) {
		Key returnKey = null;
		int medianPosition = (root.N - 1) / 2;
		returnKey = select(medianPosition);
		return returnKey;
	}

	public Key select(int k) {
		Node returnNode = select(root, k);
		if (returnNode == null) {
			return null;
		}
		return returnNode.key;
	}

	private Node select(Node x, int k) {
		if (x == null) {
			return null;
		}
		if (k > x.N) {
			return null;
		}
		int leftSize = size(x.left);
		if (leftSize > k) {
			return select(x.left, k);
		} else if (leftSize < k) {
			int newSelectVal = k - leftSize - 1;
			return select(x.right, newSelectVal);
		}
		// else ( leftSize == k)
		return x;
	}

	/**
	 * Print all keys of the tree in a sequence, in-order. That is, for each node,
	 * the keys in the left subtree should appear before the key in the node. Also,
	 * for each node, the keys in the right subtree should appear before the key in
	 * the node. For each subtree, its keys should appear within a parenthesis.
	 *
	 * Example 1: Empty tree -- output: "()" Example 2: Tree containing only "A" --
	 * output: "(()A())" * Example 3: Tree: B / \ A C \ D
	 *
	 *
	 * output: "((()A())B(()C(()D())))"
	 *
	 * output of example in the assignment:
	 * (((()A(()C()))E((()H(()M()))R()))S(()X()))
	 *
	 * @return a String with all keys in the tree, in order, parenthesized.
	 */
	public String printKeysInOrder() {
		return printKeysInOrder(root);
	}

	private String printKeysInOrder(Node x) {
		String returnString = "(";
		if (x != null) {
			returnString += printKeysInOrder(x.left);
			returnString += x.key;
			returnString += printKeysInOrder(x.right);
		}
		returnString += ")";
		return returnString;
	}

	/**
	 * Pretty Printing the tree. Each node is on one line -- see assignment for
	 * details.
	 *
	 * @return a multi-line string with the pretty ascii picture of the tree.
	 */
	public String prettyPrintKeys() {
		if(isEmpty() )
		{ 
			return "-null"; 
		}
		return prettyPrintKeys(root, "");
	}

	private String prettyPrintKeys(Node x, String prefix) {
		
		String resultString = prefix + "-";
		String leftPrefix = prefix + " |";
		String rightPrefix = prefix + "  ";
		if (x == null) {
			resultString += "null\n";
			return resultString;
		} else {
			resultString += x.key + "\n";
			resultString += prettyPrintKeys(x.left, leftPrefix);
			resultString += prettyPrintKeys(x.right, rightPrefix);
			return resultString;
		}
	}

	/**
	 * Deletes a key from a tree (if the key is in the tree). Note that this method
	 * works symmetrically from the Hibbard deletion: If the node to be deleted has
	 * two child nodes, then it needs to be replaced with its predecessor (not its
	 * successor) node.
	 *
	 * @param key
	 *            the key to delete
	 */
	public void delete(Key key) {
		root = delete(root, key);
	}

	public Node delete(Node x, Key key) {
		if (x == null) {
			return null;
		}
		int testKey = key.compareTo(x.key);
		if (testKey < 0) {
			x.left = delete(x.left, key);
		} else if (testKey > 0) {
			x.right = delete(x.right, key);
		} else {
			if (x.left == null) {
				return x.right;
			}
			if (x.right == null) {
				return x.left;
			} else {
				Node saveNode = x;
				Node newParent = findMax(saveNode.left);
				newParent.left = deleteMax(saveNode.left);
				newParent.right = saveNode.right;
				x = newParent;
			}
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	private Node findMax(Node x) {
		if (x.right == null) {
			return x;
		}
		return findMax(x.right);
	}

	private Node deleteMax(Node x) {
		if (x.right == null) {
			return x.left;
		}
		x.right = deleteMax(x.right);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

}
