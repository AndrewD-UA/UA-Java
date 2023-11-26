/*
 Develop class OrderedSet<E extends Comparable<E>> as a generic collection 
 that stores nodes in a binary search tree data structure. Each node has
 a reference to data, a left binary search tree, and a right binary search 
 tree. The type to be stored is limited to those that implement interface 
 Comparable interface or any interface that extends Comparable. Construct an
 OrderedSet of elements where the elements are not comparable is not possible.
 
 @authors Rick Mercer and Andrew Dennison
 */

public class OrderedSet<T extends Comparable<T>> {

	// A private class that stores one node in a Binary Search Tree
	private class TreeNode {

		private TreeNode right;
		private T data;
		private TreeNode left;

		public TreeNode(T element) {
			left = null;
			data = element;
			right = null;
		}
	} // end class TreeNode

	private TreeNode root;
	private int n;

	// Create an empty OrderedSet
	public OrderedSet() {
		root = null;
		n = 0;
	}

	/*
	 * Insert an element to this OrderedSet and return true keeping this an
	 * OrderedSet. element is already exists, do not change this OrderedSet, return
	 * false.
	 */
	public boolean insert(T element) {
		if (root == null) {
			n++;
			root = new TreeNode(element);
			return true;
		}

		return insert(element, root);
	}

	private boolean insert(T element, TreeNode curr) {
		// Look left or right. If you found node here, quit.
		if (element.compareTo(curr.data) < 0) {
			// If left doesn't exist, place it here
			// Otherwise, keep moving left
			if (curr.left == null) {
				curr.left = new TreeNode(element);
			} else {
				return insert(element, curr.left);
			}
		} else if (element.compareTo(curr.data) == 0) {
			return false;
		} else {
			if (curr.right == null) {
				curr.right = new TreeNode(element);
			} else {
				return insert(element, curr.right);
			}
		}

		n++;
		return true;
	}

	/*
	 * Return the number of elements in this OrderedSet, which should be 0 when
	 * first constructed. This may run O(n) or O(1)--your choice.
	 */
	public int size() {
		return n;
	}

	/*-
	 * Return one string that concatenates all elements in this OrderedSet as
	 * they are visited in order. Elements are separated by spaces as in "1 4 9" 
	 * for this OrderedSet:
	 *    4
	 *   / \
	 *  1   9
	 */
	public String toStringInorder() {
		return toStringInorder(root).trim();
	}

	private String toStringInorder(TreeNode t) {
		return t == null ?
				"" : toStringInorder(t.left) + t.data + " " + toStringInorder(t.right);
	}

	/*
	 * Return true is search equals an element in this OrderedSet, false if not.
	 */
	public boolean contains(T search) {
		return contains(search, root);
	}

	private boolean contains(T search, TreeNode node) {
		if (node == null) {
			return false;
		}
		
		return node.data.compareTo(search) == 0 ?
				true : contains(search, node.left)|| contains(search, node.right); 
	}

	/*
	 * Return the element in this OrderedSet that is greater than all other
	 * elements. If this OrderedSet is empty, return null. No recursion needed.
	 */
	public T max() {
		TreeNode maxNode = max(root);

		return maxNode == null ? 
				null : maxNode.data;
	}

	// Optional alternate usage in remove() is max(search.left)
	private TreeNode max(TreeNode search) {
		if (search == null) {
			return null;
		}
		
		return search.right == null ?
				search : max(search.right);
	}

	/*
	 * Return the element in this OrderedSet that is less than all other elements.
	 * If this OrderedSet is empty, return null. No recursion needed.
	 */
	public T min() {
		TreeNode minNode = min(root);

		return minNode == null ? 
				null : minNode.data;
	}

	// Method created primarily for usage in remove()
	private TreeNode min(TreeNode search) {
		if (search == null) {
			return null;
		}
		
		return search.left == null ?
				search : min(search.left);
	}

	/*
	 * Return the intersection of this OrderedSet and the other OrderedSet as a new
	 * OrderedSet. Do not modify this OrderedSet or the other OrderedSet. The
	 * intersection of two sets is the set of elements that are in both sets. The
	 * intersection of {2, 4, 5, 6} and {2, 5, 6, 9} is {2, 5, 6}
	 */
	public OrderedSet<T> intersection(OrderedSet<T> other) {
		OrderedSet<T> result = new OrderedSet<T>();
		if (root == null || other.root == null) {
			return result;
		}

		intersection(other, root, result);

		return result;
	}

	private void intersection(OrderedSet<T> other, TreeNode search, OrderedSet<T> result) {
		if (search == null) {
			return;
		}

		if (other.contains(search.data)) {
			result.insert(search.data);
		}

		intersection(other, search.left, result);
		intersection(other, search.right, result);
	}

	/*
	 * Return the union of this OrderedSet and the other OrderedSet as a new
	 * OrderedSet. Do not modify this OrderedSet or the other OrderedSet. The union
	 * of two sets is the set all distinct elements in the collection.[ The union of
	 * {2, 4, 6} and {2, 5, 9} is {2, 4, 5, 6, 9}
	 */
	public OrderedSet<T> union(OrderedSet<T> other) {
		OrderedSet<T> result = new OrderedSet<T>();

		if (root != null) {
			union(root, result);
		}

		if (other.root != null) {
			union(other.root, result);
		}

		return result;
	}

	private void union(TreeNode search, OrderedSet<T> result) {
		if (search == null) {
			return;
		}

		result.insert(search.data);
		union(search.left, result);
		union(search.right, result);
	}

	/*
	 * Return an OrderedSet that contains all elements greater than or equal to the
	 * first parameter (inclusive) and less than the second parameter (exclusive).
	 */
	public OrderedSet<T> subset(T inclusive, T exclusive) {
		OrderedSet<T> result = new OrderedSet<T>();

		searchAndInsert(root, result, inclusive, exclusive);
		return result;
	}

	private void searchAndInsert(TreeNode search, OrderedSet<T> result, T inclusive, T exclusive) {
		if (search == null) {
			return;
		}

		if (search.data.compareTo(inclusive) >= 0 && search.data.compareTo(exclusive) < 0) {
			result.insert(search.data);
		}

		searchAndInsert(search.left, result, inclusive, exclusive);
		searchAndInsert(search.right, result, inclusive, exclusive);
	}

	/*-
	* If element equals an element in this OrderedSet, remove it and return
	* true. Return false whenever element is not found. In all cases, this
	* OrderedSet must remain a true OrderedSet. Here is one recommended algorithm
	
	https://drive.google.com/file/d/1yjnYeIufsY1EgqJvaQ1nOXC627ZWauVa/view?usp=sharing
	
	* This algorithm should be O(log n)
	*/
	public boolean remove(T element) {
		int origSize = size();

		root = remove(root, element);

		return origSize != size();
	}

	// Making this method return a TreeNode instead of a boolean means we don't have
	// to explicitly keep track of prev and curr,
	// as instead we set the current element to whatever is returned by the next
	// recursion.
	// However, this complicates our detection of whether we actually removed
	// anything. To remedy this, we keep track
	// of the changes to size in the super method.

	// My original thought process on doing this method was to always check the
	// children, and you can simply return a boolean instead
	// since you always have a reference to the current search node and the children
	// at the same time (no need to return TreeNode). However,
	// this exposes our base case of the root being the search element, and I
	// couldn't think of a practical way to handle that.
	public TreeNode remove(TreeNode search, T element) {
		// Our base case, we tried to move beyond a leaf and found nothing
		if (search == null) {
			return null;
		}

		// See which direction we need to go down
		int compares = search.data.compareTo(element);

		// Go down the right tree, and set the right node to whatever we return
		// recursively
		if (compares < 0) {
			search.right = remove(search.right, element);
			return search;
		}
		// Go down the left tree, and set the left node to whatever we return
		// recursively
		else if (compares > 0) {
			search.left = remove(search.left, element);
			return search;
		}

		// Our current node is element at this point

		// This is a leaf. Return null, and the last recursive call sets this node to
		// null;
		if (search.left == null && search.right == null) {
			n--;
			return null;
		}
		// Only one tree exists, so simply set the current node to the existing tree
		else if (search.left == null) {
			n--;
			return search.right;
		} else if (search.right == null) {
			n--;
			return search.left;
		}
		// Both trees exist. Find the min of the right (greater tree) and set it to be
		// the current node's data.
		// Then, travel down the right tree and remove any additional mentions of the
		// old max.
		else {
			T min = min(search.right).data;
			search.data = min;
			search.right = remove(search.right, min);
			return search;
		}
	}
}