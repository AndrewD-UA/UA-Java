package model;
/**
 * interface OurMap has these four of the major methods found in java.util.Map:
 * 
 *  1) put<K,V> 
 *  2) size
 *  3) get<K>
 *  4) boolean containsKey(K)
 * 
 * @author Rick Mercer
 *
 * @param <K>
 *          The key for each mapping, which should be Integer or String.
 * @param <V>
 *          The value V that gets mapped to key.
 */
public interface OurMap<K, V> {

  // If the key is mapped to a value, replace the current mapped value
  // with value and that current value that was previously mapped to the key.
  public V put(K key, V value);
  
  // Return the number of mappings put in an OurMap object.
  public int size();

  // Return the value to which key is mapped if the key is found.
  // If the key is not in this hash table, return null.
  public V get(K key);

  // Return true if a mapping with key already exists in this Map.
  // If the key is not in this hash table, return false.
  public boolean containsKey(K key);
  
}