package ft;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 05/12/2023, mardi
 *
 * Implements a sorted linked list based on a linkedList.
 **/
public class SortedLinkedList <E extends Comparable<E>> extends LinkedList<E> {

    SortedLinkedList() {
        super();
    }

    /**
     * Adds Element at specific position in Linked List. Uses private function
     * We Compare first with the smallest or the biggest element to insert in 0(1) at extremities
     * Otherwise we have to go through the whole list to find insertion spot
     * Stable insertion as duplicate values are inserted after present equal value.
     *
     * @param element - element to be inserted in Sorted List
     */
    public void addSorted(E element) {
        //List is empty. Add function handles reference to set them appropriately.
        if (head == null) {
            add(0, element);
        }
        //Head element is bigger than element we try to insert, we want to insert in front
        else if (head.value().compareTo(element) > 0) {
            AddFirst(element);
        }
        //Tail element is smaller than element we try to insert, we want to insert in back
        else if (tail.value().compareTo(element) < 0) {
            AddLast(element);
        }
        //We need to insert in the middle of the Sorted LinkedList.
        else {
            ListElement nextElement = getSortedListElement(element);
            //Sets new element before found element
            new ListElement(element, nextElement, nextElement.previous());
            //Only need to increment count in this instance, as other cases increment count in LinkList function
            count++;
        }

    }

    /**
     * Returns ListElement at position right after element where new element has to be inserted.
     * (smallest value that is bigger than element to be inserted)
     * @param element - Element to insert
     * @return ListElement at insert position
     *
     */
    private ListElement getSortedListElement(E element) {
        ListElement d = head;
        while (d.value().compareTo(element) < 0) {
            d = d.next();
        }
        return d;
    }
}

