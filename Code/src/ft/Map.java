package ft;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 21/11/2023, mardi
 **/
public interface Map<K,V> {


    /**
     * Associates specified value with key in this map
     * Returns previous associated value with this key or null if there was no value
     * @param key - key to map to value
     * @param value - value to be associated with key
     * @return previous associated value or null if no value was already mapped
     */
    V put(K key, V value);



    /**
     * Associates specified value with key in this map only if value does not already exist
     * Returns previous associated value with this key or null if there was no value
     * @param key - key to map to value
     * @param value - value to be associated with key
     * @return previous associated value or null if no value was already mapped
     */
    V putIfAbsent(K key, V value);

    /**
     * Returns value to which the specified key is mapped or null if this map does not
     * contain any entry for this key
     * @param key - key to find mapped value for
     * @return value associated with key or null
     */
    V get(Object key);


    V remove(Object key);
    /**
     * Returns number of key value mappings in this map
     * @return number of key value mappings in this map
     */
    int size();

    /**
     * Returns true if there are no mappings
     * @return true if there are no mappings, false if there are
     */
    boolean isEmpty();

}
