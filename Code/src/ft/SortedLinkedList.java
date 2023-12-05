package ft;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 05/12/2023, mardi
 **/
public class SortedLinkedList <E extends Comparable<E>> extends LinkedList<E> {

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
            addFirst(element);
        }
        //Tail element is smaller than element we try to insert, we want to insert in back
        else if (tail.value().compareTo(element) < 0) {
            addLast(element);
        }
        //We need to insert in the middle of the Sorted LinkedList.
        else {
            ListElement prevElement = getSortedListElement(element);
            new ListElement(element);

        }
        count++;

    }

    /**
     * Returns ListElement at position after where element has to be inserted.
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

