package ft;

import java.util.LinkedList;
import java.util.List;

/**
 * SingleLinkedList.java
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 20/11/2023, lundi
 *
 * Implements Generic Singly Linked List Data Structure
 */
public class SingleLinkedList<E> {

    private class ListElement {
        private E data;
        private ListElement next;

        public ListElement(E data, ListElement nextElement) {
            this.data = data;
            this.next = nextElement;
        }

        public ListElement(E data) {
            this(data, null);
        }

        public E first() {
            return data;
        }
        public ListElement rest() {
            return next;
        }

        public void setFirst(E value) {
            data = value;
        }

        public void setRest(ListElement value) {
            next = value;
        }
    }

    private ListElement head;
    private int size = 0;

    public SingleLinkedList() {
        head = null;
    }

    /**
     * Adds element in first place and sets next ptr to previous head
     * @param o - value to add at first place
     */
    public void addFirst(E o) {
        head = new ListElement(o, head);
        size++;
    }

    public E getFirst() {
        return head.first();
    }

    /**
     * Returns element specified at position n
     * @param n - index to go to
     * @return element at position n
     * @throws IndexOutOfBoundsException if n >= size || n < 0;
     */
    public E get(int n) {
        if (n > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        ListElement d = head;
        while (n > 0) {
            d = d.rest();
            n--;
        }
        return d.first();
    }

    /**
     * @return Last element of the list
     */
    public E getLast() {
        return(get(size - 1));
    }

    /**
     * Replaces nth element by o
     * @param n - position to set object to
     * @param o - object to insert
     * @return element that has been replaced
     */
    public E set(int n, E o) {
        if (n > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        ListElement d = head;
        while (n > 0) {
            d = d.rest();
            n--;
        }
        E deletedVal = d.first();
        d.setFirst(o);
        return deletedVal;
    }

    /**
     * Replaces last element of list
     * @param o - Value to place at last position
     * @return Value that has been replaced
     */
    public E setLast(E o) {
        return set(size - 1, o);
    }

    /**
     * Adds a new element at the end of List
     * @param o - element to add
     */
    public void addLast(E o) {
        ListElement toAdd = new ListElement(o);
        ListElement d = head;
        while (d != null) {
            if (d.rest() == null) {
                d.setRest(toAdd);
                break; //Could also do a loop with n decrementing from size to 0 to find end
            }
            else {
                d = d.rest();
            }
        }

    }
    public int size() {
        return size;
    }

    public boolean contains(E o) {
        ListElement d = head;
        while (d != null) {
            if (d.first().equals(o)) {
                return true;
            }
            d = d.rest();
        }
        return false;
    }
    @Override
    public String toString() {
        String s = "( ";
        ListElement d = head;
        while (d != null) {
            s += d.first().toString() + " ";
            d = d.rest();
        }
        s += ")";
        return s;
    }
    /*******************************NON LINKED LIST FUNCTIONS*******************************/

    /**
     * Swaps elements 2 by 2 in the list
     */
    public void swap2by2() {
        ListElement d = head;
        ListElement dPrev = null;
        int i = 0;
        while (d != null) {
            if (i % 2 != 0) {
                swap(dPrev, d);
            }
            i++;
            dPrev = d;
            d = d.rest();
        }
    }

    public void append(SingleLinkedList<E> list2) {
        ListElement d = list2.head;
        while ( d != null) {
            E elemToAppend = d.first();
            this.addLast(elemToAppend);
            d = d.rest();
        }
    }

    /*******************************HELPER FUNCTIONS**********************************/
    /**
     * Swaps CONTENT of two nodes without changing their references to the next object
     * @param e1
     * @param e2
     */
    private void swap(ListElement e1, ListElement e2) {
        E tmp = e1.first();
        e1.setFirst(e2.first());
        e2.setFirst(tmp);
    }
}
