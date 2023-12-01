package ft;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 21/11/2023, mardi
 **/

//TODO add remove function
/**
 * Simplified version of HashMap
 * Uses linked list to handle Collisions
 * @param <K> - Key to map value to
 * @param <V> - Value to map to defined key
 */
public class HashMap<K, V> implements Map<K, V> {
    private class Node<K,V> {
        private final K key;
        private V value;

        private Node<K,V> next;

        /**
         * Represents a node of the hash table
         * @param hash - internal hash value
         * @param key - key the node holds
         * @param value - value this node holds
         * @param next - used to handle collisions through chaining
         */
        public Node(int hash, K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() { return key;}
        public V getValue() { return value;}
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
        public Node<K,V> getNext() {
            return next;
        }
    }

    /**
     * Holds the node, is resized when needed
     */
    Node<K,V>[] map;

    /**
     * Number of key-value mappings
     */
    private int size = 0;

    /**
     * load factor for the table
     */
    private final float loadFactor = 0.75f;

    /**
     * Current Capacity
     */
    private int capacity = 16;

    /**
     * All values are set to default values
     */
    public HashMap() {
        map = (Node<K, V>[]) new Node[capacity];
    }

    @Override
    public V put(K key, V value) {
        if ((float)(size + 1) / capacity >= loadFactor) {
            resize();
        }
        return (putVal(hash(key), key ,value, false));
    }

    @Override
    public V putIfAbsent(K key, V value) {
        if ((float)(size + 1) / capacity >= loadFactor) {
            resize();
        }
        return (putVal(hash(key), key ,value, true));
    }

    /**
     * Puts new key value pair into the Map
     * If boolean flag is set to True, creates a new element only if there are none at the place
     * If boolean flag is set to False, replaces current value by new one
     * If loadFactor is exceeded reallocation takes places and items are shuffled around in their new places
     * Collisions are handled by using a linked list where each node points to the following one
     * Returns previous value associated with key or null if there was no conflict
     */
    private V putVal(int hash, K key, V value, boolean onlyIfAbsent) {
        Node<K, V> head = map[hash];
        Node<K, V> toAdd;
        V retValue = null;
        Node<K,V> current = head;
        while (current != null) {
            //an element with the same key already exists
            if (current.getKey().equals(key)) {
                if (!onlyIfAbsent) {
                    retValue = current.setValue(value);
                }
                break;
            }
            current = current.next;
        }
        //Means that no values were found for this key, we have to add a new node
        //if current == head == null it means it was the first node
        //if head != null it means that we have a collision
        if  (current == null) {
            toAdd = new Node<>(hash, key, value, head);
            map[hash] = toAdd;
            size++;
        }
        return retValue;
    }


    /**
     * Returns value if its present in the map, null otherwise
     * @param key - key to find mapped value for
     * @return value if key is present, null otherwise
     */
    @Override
    public V get(Object key) {
        int index = hash(key);
        if (map[index] != null) {
            Node<K, V> current = map[index];
            while (current != null) {
                if (current.getKey().equals(key)) {
                    return current.getValue();
                }
                current = current.next;
            }
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    /**************************************Tracking function**************************************************/

    /**
     * Computes the effectiveness of the hashing repartition
     * Returns number between [0;1] of elements that don't have a collision
     * => The higher, the better
     */
    public float collisionEffectiveness() {
        int nbCollision = 0;
        for (int i = 0; i < capacity; i++) {
            if (map[i] != null) {
                Node<K, V> current = map[i];
                while (current.next != null) {
                    nbCollision++;
                    current = current.next;
                }
            }
        }
        return (((float)size - (float)nbCollision) / (float)size);
    }

    /**************************************Private Helper Functions******************************************/

    /**
     * Used to internal Hashing in order to link a node to its position in the array;
     * @param key - key on which hashCode() is called;
     * @return 0 if key is null, position in Map else;
     */
    private int hash(Object key) {
        return (key == null) ? 0 : (key.hashCode() & capacity - 1);
    }

    /**
     * Resizes the table. Changes order of elements in the table
     * Current issue with this method is that as we use power of 2 increment and compare lower bits when hashing
     * we might have clustering issues
     */
    private void resize() {
        int oldCapa = capacity;
        Node<K,V>[] oldMap = map;
        this.capacity = capacity << 1;
        map = (Node<K, V>[]) new Node[capacity];
        for (int i = 0; i < oldCapa; i++) {
            Node<K, V> head;
            if ((head = oldMap[i]) != null) {
                //the element might be the head of a linked list if collisions happened
                Node<K,V> current = head;
                while (current != null) {
                    int index = hash(current.getKey());
                    //there are already elements at that index, append at the front
                    Node<K,V> tmpNext = current.next; ///Need to save access to next node
                    if (map[index] != null) {
                        current.next = map[index];
                    }
                    //First element to insert at that index
                    else {
                        current.next = null;
                    }
                    //Insert element in new map
                    map[index] = current;
                    //restore access to next node (is equivalent to current.next before current inserted in the new list)
                    current = tmpNext;
                }
            }
        }
    }
}
