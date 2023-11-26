package model;

/**
 * This class contains the structure of a custom Hash Map. It is formed of
 * TABLE_SIZE linked lists, each composed of Hash Nodes, and each Hash Node
 * stores a key:value pair.
 * 
 * @author Andrew Dennison
 * @param <K> Generic type for the keys
 * @param <V> Generic type for the values
 */
public class OurHashMap<K, V> implements OurMap<K, V> {

	/**
	 * This class contains the structure of a HashNode that is used as the basis of
	 * the LinkedList for each index in the Hash Map.
	 * 
	 * @author Andrew Dennison
	 */
	private class HashNode {
		private K key;
		private V value;

		private HashNode(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public String toString() {
			return "Key: " + key + ", Value: " + value.toString();
		}
	}

	// The size of the Hash Map we are pre-generating
	public static int TABLE_SIZE = 1000;

	// The logical size (number of non-empty Linked Lists)
	private int size;

	// Initialization of each index
	private OurLinkedList<HashNode>[] lists;

	/**
	 * Constructor initializaes each LinkedList to be an empty list
	 */
	@SuppressWarnings("unchecked")
	public OurHashMap() {
		lists = new OurLinkedList[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++) {
			lists[i] = new OurLinkedList<HashNode>();
		}
	}

	/**
	 * Locate the correct index, then store value in the index
	 * 
	 * @param key   Key to hash
	 * @param value Value to store at the hashed index
	 * @return V Generic data type representation of the value at the provided index
	 */
	@Override
	public V put(K key, V value) {
		V oldData = null;

		// Get the current list we are attempting to modify with this key
		OurLinkedList<HashNode> currList = lists[hashCode(key)];

		// If there is nothing currently at that index, add a new HashNode
		// This ensures we can always perform a check for existence
		if (!containsKey(key)) {
			currList.addFront(new HashNode(key, value));
			size++;
		}

		// Search through the HashNodes at that index to find the value we need to
		// return
		for (int i = 0; i < currList.size(); i++) {
			HashNode currNode = currList.get(i);
			if (currNode.key.equals(key)) {
				oldData = currNode.value;
			}
		}

		return oldData;
	}

	/**
	 * @return the logical size of the hash map
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Get the value at the provided key. The expected use case is to return an
	 * ArrayList<Character> containing possible next chars.
	 * 
	 * @param key The key to hash and search at.
	 * @return V The value contained at the given key. Null if the key does not
	 *         exist
	 */
	@Override
	public V get(K key) {
		OurLinkedList<HashNode> currList = lists[hashCode(key)];

		if (currList.size() == 0) {
			return null;
		}

		// If values exist at this hash, search through each value to find
		// the requested key. Then, return its value.
		for (int i = 0; i < currList.size(); i++) {
			if (currList.get(i).key.equals(key)) {
				return currList.get(i).value;

			}
		}

		return null;
	}

	/**
	 * Check if the given key exists in the Hash Map already.
	 * 
	 * @param key The given key to check for
	 * @return boolean representation of whether the key exists.
	 */
	@Override
	public boolean containsKey(K key) {
		OurLinkedList<HashNode> currList = lists[hashCode(key)];

		if (currList.size() == 0) {
			return false;
		}

		for (int i = 0; i < currList.size(); i++) {
			if (currList.get(i).key.equals(key)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @return a String repr of the entire Hash Map.
	 */
	public String toString() {
		String result = "";

		for (int i = 0; i < TABLE_SIZE; i++) {
			if (lists[i].size() > 0) {
				result += "Index " + i + ": " + lists[i].toString() + "\n";
			}
		}

		return result;
	}

	// Precondition: Type K must override hashCode() like String
	// and Integer already do. This methods returns 0..TABLE_SIZE-1
	/**
	 * Provided code for hashing function
	 * 
	 * @param key Key to hash
	 * @return Integer repr of the hash
	 */
	private int hashCode(K key) {
		return Math.abs(key.hashCode()) % TABLE_SIZE;
	}
}
