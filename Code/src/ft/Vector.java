/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 20/11/2023, lundi
 **/
package ft;

import java.util.Iterator;

/**
 * Implements Generic ft.Vector Data Structure
 * //TODO Check java reflexion to avoid casting each time from Object to E
 *
 * @param <E>
 */
public class Vector<E> implements Iterable<E> {
    private int capacity = 0;
    protected int capacityIncrement = 0;
    protected int elementCount = 0;
    protected Object[] elementData;

    /**
     * Default constructor
     * Construct a ft.Vector with internal size 10 that starts at 0.
     */
    public Vector() {
        elementData = new Object[10];
    }

    /**
     * Construct Vector with initialCapacitu and capacityincrement = 0;
     *
     * @param initialCapacity - initial capacity
     */
    public Vector(int initialCapacity) {
        elementData = new Object[initialCapacity];
        this.capacity = initialCapacity;
    }

    /**
     * Construct Vector with initalCapacity and capacity increment
     *
     * @param initialCapacity   - initial capacity
     * @param capacityIncrement - value by which to increment capacity in case of reallocation
     */
    public Vector(int initialCapacity, int capacityIncrement) {
        elementData = new Object[initialCapacity];
        this.capacity = initialCapacity;
        this.capacityIncrement = capacityIncrement;
    }

    /**
     * Adds element E at the end of the vector;
     *
     * @param e - param to add
     * @return true
     */
    public boolean add(E e) {
        elementData[elementCount] = e;
        elementCount++;
        return true;
    }

    //not really effective as data is copied once when moved and a second time when element is added in front, not an issues
    //because allocating memory is just 2n -> n for first copy and n for shift

    /**
     * Adds element at specific index, shifts every element currently at that position and all subsequents one to the right
     *
     * @param index   - index at which to add
     * @param element - element to add
     */
    public void add(int index, E element) {
        ensureCapacity(elementCount + 1);
        shiftRight(index);
        elementData[index] = element;
        elementCount++;
    }

    /**
     * Adds element at the end of the vector, increasing its size by 1. If size becomes greater than its capacity,
     * capacity is increased
     */
    public void addElement(E obj) {
        ensureCapacity(elementCount + 1);
        add(obj);
    }

    /**
     * Returns true if object is in vector (is also supposed to work with o == null
     *
     * @param o - element to search for
     * @return true if vector contains object
     */
    public boolean contains(E o) {
        for (int i = 0; i < elementCount; i++) {
            if (elementData[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    //TODO: refactor newCapacity conditions, are a bit ugly

    /**
     * Increases capacity of this vector to be sure that it holds at least capacity specified by the argument.
     * If current size is not sufficient, we replace internal Data array with a new one. New array is equal to the size
     * of the older one plus capacityIncrement (unless capacity increment <= 0, then its doubled).
     * If this new capacity is smaller than minCapacity, min capacity is used
     *
     * @param minCapacity - Capacity to ensure
     */
    public void ensureCapacity(int minCapacity) {
        if (capacity < minCapacity) {
            int newCapacity = (capacityIncrement <= 0) ? 2 * capacity : capacity + capacityIncrement;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            Object[] newArray = new Object[newCapacity];
            copyData(newArray, elementData, elementCount);
            elementData = newArray;
        }
    }

    /**
     * Returns first element
     *
     * @return returns first element
     * @throws java.util.NoSuchElementException if vector is empty
     */
    public E fistElement() {
        return (E) elementData[0];
    }

    /**
     * Returns last element (ie at index size - 1)
     *
     * @return returns first element
     * @throws java.util.NoSuchElementException if vector is empty
     */
    public E lastElement() {
        return (E) elementData[elementCount - 1];
    }

    /**
     * Returns elements at index index
     *
     * @param index - Index at which to retrieve element
     * @return element
     * @throws ArrayIndexOutOfBoundsException if index is out of range (index < 0 || index >= size)
     */
    public E get(int index) {
        return (E) elementData[index];
    }

    /**
     * Replaces element at index by new element, returning element previously at that position
     *
     * @param index   - index at which to replace
     * @param element - new element
     * @return Element that was previously at that position
     */
    public E set(int index, E element) {
        E tmp = (E) elementData[index];
        elementData[index] = element;
        return tmp;

    }

    /**
     * Returns number of elements of the vector
     *
     * @return number of elements of the vector
     */
    public int size() {
        return elementCount;
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    /**
     * Removes element at index, shifts every subsequent element to the left
     *
     * @param index - index to remove element from
     * @return removed element
     * @throws ArrayIndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
     */
    public E remove(int index) {
        E elem = (E) elementData[index];
        shiftLeft(index);
        elementCount--;
        return elem;
    }

    @Override
    public String toString() {
        String acc = "[ ";
        for (int i = 0; i < elementCount; i++) {
            acc += elementData[i].toString() + " ";
        }
        acc += "]";
        return (acc);
    }

    /***************************************NON-VECTOR FUNCTIONS******************************************************/
    public void addFirst(E o) {
        add(0, o);
    }

    /**
     * Removes last element
     */
    public void removeLast() {
        remove(elementCount);
    }

    public void removeFirst() {
        remove(0);
    }

    /**
     * Reverses elements from vector in place
     */
    public void reverse() {
        for (int i = 0; i < elementCount / 2; i++) {
            swap(i, elementCount - i);
        }

    }

    /**
     * Repeats each value two times
     * Should call a new constructor to create new object
     * Time Complexity: O(n) : O(n) for allocating + O(n) for copying
     *
     * @return new Repeated vector
     */
    public Vector<E> repeat() {
        Vector<E> repeatVector = new Vector<>(elementCount * 2);
        for (int i = 0; i < elementCount; i++) {
            repeatVector.add((E) elementData[i]);
            repeatVector.add((E) elementData[i]);
        }
        return repeatVector;
    }

    /**
     * Interleaves this vector with v2;
     * Time Complexity: O(n) : O(n) for allocating + O(n) for copying
     *
     * @param v2 - vector to interleave
     * @return new interleave vector
     */
    public Vector<E> interleave(Vector<E> v2) {
        Vector<E> interleaveVector = new Vector<>(this.size() + v2.size());
        int commonInteraction = (this.size() > v2.size()) ? v2.size() : this.size();
        for (int i = 0; i < commonInteraction; i++) {
            interleaveVector.add((E) elementData[i]);
            interleaveVector.add(v2.get(i));
        }
        for (int i = commonInteraction; i < this.size(); i++) {
            interleaveVector.add((E) elementData[i]);
        }
        for (int i = commonInteraction; i < v2.size(); i++) {
            interleaveVector.add(v2.get(i));
        }
        return interleaveVector;
    }

    //TODO Write templated version
    /***************************************PRIVATE HELPER FUNCTION***************************************************/
    /**
     * Copies nb elements from array dest to array src
     *
     * @param dest - Destination array
     * @param src  - Destination array
     * @return
     */
    private void copyData(Object[] dest, Object[] src, int nb) {
        for (int i = 0; i < nb; i++) {
            dest[i] = src[i];
        }
    }

    /**
     * Shift every value at position index and subsequently by one to the right
     * Need to start copy from the right;
     *
     * @param index - index at which shift is supposed to starts
     */
    private void shiftRight(int index) {
        for (int i = elementCount; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
    }

    /**
     * Shift every value at position index and before by one to the left
     * Need to start copy from the left;
     *
     * @param index - index at which shift starts
     */
    private void shiftLeft(int index) {
        for (int i = index; i < elementCount - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
    }

    /**
     * Swaps objects at index pos1 and pos2
     *
     * @param pos1 - first element to swap
     * @param pos2 - second element toj swap
     */
    private void swap(int pos1, int pos2) {
        Object tmp = elementData[pos1];
        elementData[pos1] = elementData[pos2];
        elementData[pos2] = tmp;
    }

    /***************************************IMPLEMENTATION OF ITERATOR INTERFACE********************************/
    @Override
    public Iterator<E> iterator() {
        return new VectorIterator();
    }

    /**
     * Implementation of Iterator Interface
     */
    private class VectorIterator implements Iterator<E> {
        private int currentIndex = 0;

        /**
         * Checks if there is a next element left in the array
         *
         * @return true if an element remains, false if not
         */
        @Override
        public boolean hasNext() {
            return currentIndex < elementCount;
        }

        /**
         * Returns next element in array
         * Increment internal iterator value so that it points to the next element
         *
         * @return next element in array
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return (E) elementData[currentIndex++];
        }
    }
}
