import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 * Test class for Doubly Linked List
 *
 * @version 3.1 09/11/15 11:32:15
 *
 * @author Hannah Keating
 */

@RunWith(JUnit4.class)
public class BSTTest {

	@Test
	public void testContains() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals("Checking contains of empty tree", false, bst.contains(2));
		bst.put(6, 6);
		assertEquals("Checking contain of one node tree, which the key isn't there",
				false, bst.contains(-5));
		assertEquals("Checking contain of one node tree, which the key is there",
				true, bst.contains(6));
		bst.put(4, 4);
		assertEquals("Checking height of one node tree", 1, bst.height());
		
		bst = new BST<Integer, Integer>();
		bst.put(7, 7);
		bst.put(8, 8);
		bst.put(3, 3);
		bst.put(1, 1);
		bst.put(2, 2);
		bst.put(6, 6);
		bst.put(4, 4);
		bst.put(5, 5);
		assertEquals("Checking large tree, with key there", true, bst.contains(6)); 
		assertEquals("Checking large tree, with key not there", false, bst.contains(10)); 
	}
	
	@Test
	public void testGet() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals("Checking contains of empty tree", null, bst.get(2));
		bst.put(6, 6);
	
		assertEquals("Checking get of one node tree, which the key isn't there",
				null,  bst.get(2));
		int testVal = (int) bst.get(6); 
		assertEquals("Checking get of one node tree, which the key is there",
				6, testVal);
		
		bst.put(4, 4);
		testVal = (int) bst.get(4); 
		assertEquals("Checking get of two node tree, with left subtree, which the key is there",
			4, testVal);
		bst.put(7, 7);
		bst.delete(4);
		testVal = (int) bst.get(7); 
		assertEquals("Checking get of two node tree, with right subtree, which the key is there",
			7, testVal);
		
				bst = new BST<Integer, Integer>();
		bst.put(7, 7);
		bst.put(8, 8);
		bst.put(3, 3);
		bst.put(1, 1);
		bst.put(2, 2);
		bst.put(6, 6);
		bst.put(4, 4);
		bst.put(5, 5);
		testVal = (int) bst.get(7); 
		assertEquals("Checking large tree, with key there", 7, testVal);
		; 
		assertEquals("Checking large tree, with key not there", null,  bst.get(10));
		
	}

	@Test
	public void testPut() {
		
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		
		assertEquals("Test  empty tree", "()", bst.printKeysInOrder());
		bst.put(6,6);
		assertEquals("Test 1 elem tree", "(()6())", bst.printKeysInOrder());
		bst.put(6,6);
		assertEquals("Test 1 elem tree, with overwrite", "(()6())", bst.printKeysInOrder());
		bst.put(6, null);
		assertEquals("Test when node set to null", "()", bst.printKeysInOrder());
		bst.put(6,6);
		bst.put(7,7);
		assertEquals("Test 2 elem tree, with right tree", "(()6(()7()))", bst.printKeysInOrder());
		bst.put(5,5);
		assertEquals("Test 2 elem tree, with right tree", "((()5())6(()7()))", 
				bst.printKeysInOrder());
	}
	
	@Test
	public void testHeight() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals("Checking height of empty tree", -1, bst.height());
		bst.put(6, 6);
		assertEquals("Checking height of one node tree", 0, bst.height());
		bst.put(4, 4);
		assertEquals("Checking height of one node tree", 1, bst.height());
		bst.put(5, 5);
		assertEquals("Checking height of two node tree", 2, bst.height());
		bst.put(3, 3);
		assertEquals("Checking height of mulinode tree", 2, bst.height());
		bst.put(2, 2);
		assertEquals("Checking height of mulinode tree", 3, bst.height());
		bst = new BST<Integer, Integer>();
		bst.put(7, 7);
		bst.put(8, 8);
		bst.put(3, 3);
		bst.put(1, 1);
		bst.put(2, 2);
		bst.put(6, 6);
		bst.put(4, 4);
		bst.put(5, 5);
		assertEquals("Checking height of test tree", 4, bst.height());
	}

	@Test
	public void testMedian() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals("Checking median of empty tree", null, bst.median());

		bst.put(6, 6);
		int testInt = 6;
		int keyInt = (int) bst.median();
		assertEquals("Checking median of 1 elem tree", testInt, keyInt);

		bst.put(4, 4);
		testInt = 4;
		keyInt = (int) bst.median();
		assertEquals("Checking median of 2 elem tree, with elem in left subtree", testInt, keyInt);

		bst = new BST<Integer, Integer>();
		bst.put(6, 6);
		bst.put(7, 7);
		testInt = 6;
		keyInt = (int) bst.median();
		assertEquals("Checking median of 2 elem tree, with elem in right subtree", testInt, keyInt);

		bst.put(4, 4);
		testInt = 6;
		keyInt = (int) bst.median();
		assertEquals("Checking median balance 3 node tree", testInt, keyInt);

		bst = new BST<Integer, Integer>();
		bst.put(7, 7);
		bst.put(8, 8);
		bst.put(3, 3);
		bst.put(1, 1);
		bst.put(2, 2);
		bst.put(6, 6);
		bst.put(4, 4);
		bst.put(5, 5);
		testInt = 4;
		keyInt = (int) bst.median();
		assertEquals("Checking median test tree", testInt, keyInt);
	}

	@Test
	public void testSelect() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals("Checking slect of empty tree", null, bst.select(0));

		bst.put(6, 6);
		int testInt = 6;
		int keyInt = (int) bst.select(0);
		assertEquals("Checking select of one node tree", testInt, keyInt);

		bst.put(4, 4);
		testInt = 4;
		keyInt = (int) bst.select(0);
		assertEquals("Checking select of 2 elem tree, with elem in left subtree", testInt, keyInt);

		bst = new BST<Integer, Integer>();
		bst.put(6, 6);
		bst.put(7, 7);
		testInt = 7;
		keyInt = (int) bst.select(1);
		assertEquals("Checking select of 2 elem tree, with elem in right subtree", testInt, keyInt);

		bst.put(4, 4);
		testInt = 6;
		keyInt = (int) bst.select(1);
		assertEquals("Checking select balance 3 node tree", testInt, keyInt);

		bst = new BST<Integer, Integer>();
		bst.put(7, 7);
		bst.put(8, 8);
		bst.put(3, 3);
		bst.put(1, 1);
		bst.put(2, 2);
		bst.put(6, 6);
		bst.put(4, 4);
		bst.put(5, 5);
		testInt = 5;
		keyInt = (int) bst.select(4);
		assertEquals("Checking median test tree", testInt, keyInt);

	}

	@Test
	public void printKeysInOrder() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		String testString = "()";
		assertEquals("Checking print of empty tree", testString, bst.printKeysInOrder());

		bst.put(6, 6);
		testString = "(()6())";
		assertEquals("Checking select of one node tree", testString, bst.printKeysInOrder());

		bst.put(5, 5);
		testString = "((()5())6())";
		assertEquals("Checking select of two node tree", testString, bst.printKeysInOrder());

		bst.put(7, 7);
		bst.put(8, 8);
		testString = "((()5())6(()7(()8())))";
		assertEquals("Checking select of multi node tree", testString, bst.printKeysInOrder());

		bst = new BST<Integer, Integer>();
		bst.put(7, 7);
		bst.put(8, 8);
		bst.put(3, 3);
		bst.put(1, 1);
		bst.put(2, 2);
		bst.put(6, 6);
		bst.put(4, 4);
		bst.put(5, 5);
		testString = "(((()1(()2()))3((()4(()5()))6()))7(()8()))";
		assertEquals("Checking order of constructed tree", testString, bst.printKeysInOrder());
	}

	/**
	 * <p>
	 * Test {@link BST#prettyPrintKeys()}.
	 * </p>
	 */

	@Test
	public void testPrettyPrint() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();

		assertEquals("Checking pretty printing of empty tree", "-null", bst.prettyPrintKeys());

		bst.put(6, 6);
		String testString = "-6\n" + " |-null\n" + "  -null\n";
		assertEquals("Checking select of one node tree", testString, bst.prettyPrintKeys());

		bst.put(5, 5);
		testString = "-6\n" + " |-5\n" + " | |-null\n" + " |  -null\n" + "  -null\n";
		assertEquals("Checking select of two node tree", testString, bst.prettyPrintKeys());

		bst.put(7, 7);
		bst.put(8, 8);
		testString = "-6\n" + " |-5\n" + " | |-null\n" + " |  -null\n" + "  -7\n" + "   |-null\n" + "    -8\n"
				+ "     |-null\n" + "      -null\n";
		assertEquals("Checking select of multi node tree", testString, bst.prettyPrintKeys());

		bst = new BST<Integer, Integer>();
		// -7
		// |-3
		// | |-1
		// | | |-null
		bst.put(7, 7); // | | -2
		bst.put(8, 8); // | | |-null
		bst.put(3, 3); // | | -null
		bst.put(1, 1); // | -6
		bst.put(2, 2); // | |-4
		bst.put(6, 6); // | | |-null
		bst.put(4, 4); // | | -5
		bst.put(5, 5); // | | |-null
						// | | -null
						// | -null
						// -8
						// |-null
						// -null

		testString = "-7\n" + " |-3\n" + " | |-1\n" + " | | |-null\n" + " | |  -2\n" + " | |   |-null\n"
				+ " | |    -null\n" + " |  -6\n" + " |   |-4\n" + " |   | |-null\n" + " |   |  -5\n"
				+ " |   |   |-null\n" + " |   |    -null\n" + " |    -null\n" + "  -8\n" + "   |-null\n"
				+ "    -null\n";
		assertEquals("Checking pretty printing of non-empty tree", testString, bst.prettyPrintKeys());
	}

	/**
	 * <p>
	 * Test {@link BST#delete(Comparable)}.
	 * </p>
	 */
	@Test
	public void testDelete() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		bst.delete(1);
		assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());

		bst.put(7, 7); // _7_
		bst.put(8, 8); // / \
		bst.put(3, 3); // _3_ 8
		bst.put(1, 1); // / \
		bst.put(2, 2); // 1 6
		bst.put(6, 6); // \ /
		bst.put(4, 4); // 2 4
		bst.put(5, 5); // \
						// 5

		assertEquals("Checking order of constructed tree", "(((()1(()2()))3((()4(()5()))6()))7(()8()))",
				bst.printKeysInOrder());

		bst.delete(9);
		assertEquals("Deleting non-existent key", "(((()1(()2()))3((()4(()5()))6()))7(()8()))",
				bst.printKeysInOrder());

		bst.delete(8);
		assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())",
				bst.printKeysInOrder());

		bst.delete(6);
		assertEquals("Deleting node with single child", "(((()1(()2()))3(()4(()5())))7())", 
				bst.printKeysInOrder());

		bst.delete(3);
		assertEquals("Deleting node with two children", "(((()1())2(()4(()5())))7())",
				bst.printKeysInOrder());
	}

}
