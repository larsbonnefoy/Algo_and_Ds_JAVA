/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 20/11/2023, lundi
 **/
package ft;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Iterable<E> {

    private class ListElement {
        private final E data;
        private ListElement nextElement;
        private ListElement prevElement;

        public ListElement(E o, ListElement next, ListElement prev) {
            this.data = o;
            this.nextElement = next;
            this.prevElement = prev;
            if (next != null) {
                next.prevElement = this;
            }
            if (prevElement != null) {
                prev.nextElement = this;
            }
        }

        public ListElement(E o) {
            this(o, null, null);
        }

        public E value(){
            return data;
        }

        /**
         * Returns next element of Linked List
         * @return next Node element
         */
        public ListElement next() {
            return nextElement;
        }

        public void setNext(ListElement val) {
            this.nextElement = val;
        }

        public ListElement previous() {
            return prevElement;
        }

        public void setPrev(ListElement prev) {
            this.prevElement = prev;
        }
    }

    private int count;
    private ListElement head;
    private ListElement tail;

    public LinkedList() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Returns first element in List
     * @return first element
     * //TODO: check if throws this exception or a null except
     * @throws java.util.NoSuchElementException;
     */
    public E getFirst() {
        return head.value();
    }
    /**
     * Returns last element in List
     * @return last element
     * //TODO: check if throws this exception or a null except
     * @throws java.util.NoSuchElementException;
     */
    public E getLast() {
        return tail.value();
    }

    /**
     * Returns number of element in the list
     * @return number of element in the list
     */
    public int size() {
        return count;
    }

    public boolean add(E element) {
        addLast(element);
        return true;
    }

    /**
     * Inserts the specified element at the specified position in the list
     * Insert from the front if we are in the first half, from the back if we are in the second half?
     * @param index - index at which to insert
     * @param element - element to insert
     */
    public void add(int index, E element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        //Adding at the front
        if (index == 0) {
            head = new ListElement(element, head, null);
            //Adding first element
            if (size() == 0) {
                tail = head;
            }
        }
        //Adding at the end
        else if (index == size()) {
            tail =  new ListElement(element, null, tail);
        }
        //Adding in the middle
        else {
            ListElement d = getListElement(index);
            new ListElement(element, d, d.nextElement);
        }
        count++;
    }

    /**
     * Inserts element at the beginning of the list
     * @param element to insert
     */
    public void addFirst(E element) {
        add(0, element);
    }

    /**
     * Inserts element at the end of the list
     * @param element to insert
     */
    public void addLast(E element) {
        add(size(), element);
    }

    /**
     * Removes element at given index
     * @param index - index to remove parameter
     * @return element that has been removed
     * @throws IndexOutOfBoundsException if index < 0 || index >size
     */
    public E remove(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        E val;
        //Remove First element
        if (index == 0) {
            val = head.data;
            head = head.nextElement;
        }
        //Remove Last element
        else if (index == size() - 1) {
            val = tail.value();
            tail = tail.prevElement;
        }
        //Remove element in the middle
        else {
            ListElement d = getListElement(index);
            val =  d.data;
            d.prevElement.nextElement = d.nextElement;
            d.nextElement.prevElement = d.prevElement;
        }
        count--;
        return val;
    }

    /**
     * Removes First element and returns it
     * @return First element
     */
    public E removeFirst() {
        return (remove(0));
    }

    /**
     * Removes Last element and returns it
     * @return Last element
     */
    public E removeLast() {
        return remove(size() - 1);
    }

    @Override
    public String toString() {
        String s = "(";
        ListElement d = head;
        while (d != null) {
            s += d.value().toString() + " ";
            d = d.next();
        }
        s += ")";
        return s;
    }
    /***************************************IMPLEMENTATION OF ITERATOR INTERFACE********************************/

    /**
     * Returns forward Iterator
     * @return Forward Iterator
     */
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    public Iterator<E> descendingIterator() {
        return new ReverseListIterator();
    }

    private class ListIterator implements Iterator<E> {
        private ListElement current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns Value of Current Position of the Iterator
         * Advances the iterator by one
         * @return value of current node before next call
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E val = current.value();
            current = current.next();
            return val;
        }
    }

    /**
     * Reverse Iterator -> Starting at tail and going backwards when calling next
     */
    private class ReverseListIterator implements Iterator<E> {
        private ListElement current = tail;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns Value of Current Position of the Iterator
         * Advances the iterator by one (In reverse!)
         * @return value of current node before next call
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E val = current.value();
            current = current.previous();
            return val;
        }
    }
    /**************************************PRIVATE HELPER FUNCTIONS**************************************************/
    /**
     * Returns List Element at given index. Starts from the front if index < size / 2 or at the back if index >= size / 2
     * Does NOT throw an error if index is out ouf bounds!!
     * @param index - Index at which to get element
     * @return ListElement at that position Index
     */
    private ListElement getListElement(int index) {
        ListElement d = null;
        if (index <= size() / 2) {
            d = head;
            for (int i = index; i > 0 ; i--) {
                d = d.nextElement;
            }
        }
        else {
            d = tail;
            for (int i = size() - index; i > 0 ; i--) {
                d = d.prevElement;
            }
        }
        return d;
    }
}
