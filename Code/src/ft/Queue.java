/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 20/11/2023, lundi
 **/
package ft;

/**
 * FIFO queue data structure based on a double linked list
 */
public class Queue<E> extends LinkedList<E> {
    public Queue() {
        super();
    }

    /**
     * Adds item at the back of the queue
     * @param item to add
     */
    public void enqueue(E item) {
        super.addLast(item);
    }

    /**
     * Removes item at the front of the queue
     * @return element that as been removed
     */
    public E dequeue() {
        return(super.removeFirst());
    }

    public E peek() {
        return(super.getFirst());
    }

    public int size() {
        return super.size();
    }

}
