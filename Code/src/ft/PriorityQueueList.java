package ft;

import java.util.Iterator;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 05/12/2023, mardi
 *
 * Priority Queue based on Sorted Linked List
 * Uses object P to define priority of element E
 * Sorts in ascending order because of addSorted of SortedList.
 *
 * Normally should not pass priority but rely on comparator passed to class or natural comparison order of elements
 * given to the queue.
 * Should rely on a prio Heap.
 *
 * To solve ex 3:
 *  we could addfront for prio A and add last for prio B. And then always read from front
 *  -> Issue is that elements with prio A are in reversed order. Some older elements could never be read because of FIFO struct
 *
 *  If I have n fixed distinct prio I could make an array of size N where each prio has its slot and where each slot maps
 *  to a queue. That way I can always add in O(1) a given object with a given prio by just adding it to the queue corresponding to its slot
 *  It will however need to keep track at which slot to read next.
 *
 **/
public class PriorityQueueList<E, P extends Comparable<P>> {


    private class PriorityPair implements Comparable<PriorityPair> {

        private final E element;
        private final P priority;

        public PriorityPair(E element, P priority) {
            this.element = element;
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityPair o) {
            return (this.priority.compareTo(o.priority));
        }

        @Override
        public String toString() {
            return "(" +
                    "p=" + priority +
                    ", e=" + element +
                    ')';
        }
    }

    private final SortedLinkedList<PriorityPair> data;

    public PriorityQueueList() {
        this.data = new SortedLinkedList<PriorityPair>();
    }

    /**
     * Pushed element E with priority p onto the priority queue
     * @param element - element to add
     * @param priority - priority of element that will be added
     */
    public void push(E element, P priority) {
        PriorityPair newElement = new PriorityPair(element, priority);
        data.addSorted(newElement);
    }

    /**
     * Removes lowest priority element
     * @return value of lowest priority element
     */
    public E pop() {
        return (data.removeFirst().element);
    }

    /**
     * Get first element in priority list
     * @return value of first element in priority list
     */
    public E top() {
        return (data.getFirst().element);
    }

    public int size() {
        return (data.size());
    }

    @Override
    public String toString() {
        StringBuilder acc = new StringBuilder("(");
        for (PriorityPair datum : data) {
            acc.append(datum.toString()).append(",");
        }
        acc.append(")");
        return acc.toString();
    }
}
