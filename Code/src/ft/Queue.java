/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 20/11/2023, lundi
 **/
package ft;

/**
 * FIFO queue data structure based on a double linked list
 * Adds element at the end of the queue and pops them from the front.
 *
 * Available functions:
 *
 * Queue();
 * push();
 * pop();
 * top();
 * size();
 * empty();
 *
 */
public class Queue<E>{

    private final LinkedList<E> data;

    /**
     * Default constructor, initializes underlying linked list data structure
     */
    public Queue() {
        data = new LinkedList<>();
    }

    /**
     * Adds item at the back of the queue
     * O(1)
     * @param item to add
     */
    public void enqueue(E item) {
        data.addLast(item);
    }

    /**
     * Removes item at the front of the queue
     * O(1)
     * @return element that as been removed
     */
    public E dequeue() {
        return(data.removeFirst());
    }

    /**
     * Get top element of queue
     * O(1)
     * @return top element of queue
     */
    public E peek() {
        return(data.getFirst());
    }

    /**
     * Returns number of elements in queue
     * @return number of elements in queue
     */
    public int size() {
        return data.size();
    }

    public void push(E element) {
        enqueue(element);
    }

    public E pop() {
        return (dequeue());
    }

    public E top(E element) {
        return (peek());
    }

    public boolean empty() {
        return (data.size() == 0);
    }

}
