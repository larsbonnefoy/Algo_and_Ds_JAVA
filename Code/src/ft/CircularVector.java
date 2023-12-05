package ft;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 05/12/2023, mardi
 *
 * Uses a vector as data container.
 * Need to keep track of number of elements in vector as well as where the vector starts
 *
 * As we are using vector.set to set values in the circularVector, vector is never going to be able to grow beyond
 * its inital amount of data.
 *
 **/
public class CircularVector<E> {
    private Vector<E> data;
    private int start = 0;

    private int count = 0;

    /**
     * Creates default vector with size 10
     */
    CircularVector() {
        data = new Vector<>();
    }

    /**
     * Creates vector with given initial Capacity
     * @param capacity - max capacity of vector
     */
    CircularVector(int capacity) {
        data = new Vector<>(capacity);
    }
    public int size() {
        return (count);
    }

    public void addFirst(E element) {
        if (count == 0) {
            data.set(0, element);
        }
        else {
            data.set(insertFirstIndex(), element);
            start = insertFirstIndex();
        }
        count++;
    }
    public void addLast(E element) {
        data.set(insertLastIndex(), element);
        count++;
    }

    public E getFirst() {
        return data.get((insertFirstIndex() + 1 ) % data.capacity());
    }

    public E getLast() {
        return data.get(insertLastIndex() - 1);
    }

    /**
     * Removes first element. Set start one value after current start value
     */
    public void removeFirst() {
        if (count > 0) {
            start = (start + 1) % data.capacity();
            count--;
        }
    }

    /**
     * Removes last element.
     */
    public void removeLast() {
        count--;
    }

    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < count; i++) {
            int index = (start + i) % data.capacity();
            s += data.get(index).toString();
            s += " ";
        }
        s += "]";
        return s;
    }

    /**
     * Computes where value has to be added in the vector when added in first place
     * Returns 1 position BEFORE the first value
     * @return - position of insertion for underlying vector
     */
    private int insertFirstIndex() {
        return ((start + data.capacity() - 1) % data.capacity());
    }

    /**
     * Computes where value has to be added in the vector when added in last place
     * It returns 1 position AFTER the last value
     * @return - position of insertion for underlying vector
     */
    private int insertLastIndex() {
        return ((start + count) % data.capacity());
    }
}
