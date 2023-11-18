package ft;

import java.util.EmptyStackException;

public class Stack<E> extends Vector<E>{

    /**
     * Constructs empty Stack
     */
    public Stack() {
        super();
    }

    /**
     * Tests if stack is empty
     * @return true if empty, false if not
     */
    public boolean empty() {
        return super.isEmpty();
    }

    /**
     * Returns top on stack element without deleting in
     * @return top on stack element
     */
    public E peek() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return super.lastElement();
    }

    /**
     * Adds an element on the top of the stack (= end of vector)
     * @param item - item to add
     * @return added item
     */
    public E push(E item) {
        super.addElement(item);
        return item;
    }

    /**
     * Removes top on stack element and returns it
     * @return top on stack element
     */
    public E pop() {
        return (super.remove(super.size() - 1));
    }
}
