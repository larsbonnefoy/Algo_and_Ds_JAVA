package ft;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 05/12/2023, mardi
 *
 * Simple dictionary implementation that stores a key value pair. Searches linearly through the underlying vector
 * to find a key.
 **/
public class DictionaryVector<K extends Comparable<K>,V> {

    private class DictionaryPair implements Comparable<DictionaryPair> {
        private K key;
        private V value;

        public DictionaryPair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public int compareTo(DictionaryPair o) {
            return (key.compareTo(o.key));
        }
    }

    Vector<DictionaryPair> data;

    DictionaryVector() {
        data = new Vector<>();
    }

    /**
     * Adds element with given key and value at the end of the underlyind vector
     * O(1) except on reallocation
     * @param key - key to add
     * @param value - value linked to the key to add
     */
    public void add(K key, V value) {
        int index = findPosition(key);
        DictionaryPair toAdd = new DictionaryPair(key, value);
        //if element is not present we add it add the end
        if (index == -1) {
            data.addElement(toAdd);
        }
        //if element is present we override the already present value.
        else {
            data.set(index, toAdd);
        }
    }

    /**
     * Returns index in vector of object with key
     * runs in O(n)
     * @param key - key to search for
     * @return index of element linked to key or -1 if element has not been found
     */
    public int findPosition(K key) {
        for (int i = 0; i < data.size(); i++) {
           if (data.get(i).getKey().equals(key)) {
               return i;
           }
        }
        return -1;
    }

    /**
     * Finds element with given key in dictionary
     * runs in O(n)
     * @param key - key to find element from
     * @return Value of object with given key, null otherwise
     */
    public V find(K key) {
        int index = findPosition(key);
        if (index != -1) {
            return (data.get(index).getValue());
        }
        return null;
    }

    /**
     * Removes element with given key
     * @param key - Key corresponding to the element to remove
     * @return Returns value of removed element or null if no such element exists
     */
    public V removeKey(K key) {
        int index = findPosition(key);
        if (index != -1) {
            return (data.remove(index).getValue());
        }
        return null;
    }

    public int size() {
        return (data.size());
    }

    /**
     * Returns value at given index
     * @param index - index at which to get value form
     * @return value from index
     *
     * Mainly used for test verification
     */
    public V get(int index) {
        return (data.get(index).getValue());
    }

}
