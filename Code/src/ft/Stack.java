/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 20/11/2023, lundi
 **/
package ft;

import java.util.EmptyStackException;

/**
 * Stack Implementation based on a Vector.
 *
 * @param <E> - Elements of which stack is composed
 *
 * Available methods
 * Stack();
 * push(E);
 * pop(E);
 * top(E);
 * size(E);
 * empty(E);
 *
 * //TODO remove Exception throwing
 *
 */
public class Stack<E> {

    private final Vector<E> stack;

    /**
     * Constructs empty Stack
     */
    public Stack() {
        stack = new Vector<>();
    }

    /**
     * Tests if stack is empty
     * @return true if empty, false if not
     */
    public boolean empty() {
        return stack.isEmpty();
    }

    /**
     * Returns top on stack element without deleting in
     * @return top on stack element
     */
    public E peek() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.lastElement();
    }

    /**
     * Adds an element on the top of the stack (= end of vector)
     * O(1) except on reallocation
     * @param item - item to add
     * @return added item
     */
    public E push(E item) {
        stack.addElement(item);
        return item;
    }

    /**
     * Removes top on stack element and returns it
     * O(1)
     * @return top on stack element
     */
    public E pop() {
        return (stack.remove(stack.size() - 1));
    }

    /**
     * Returns top on stack element
     * Wrapper function around peek.
     * O(1)
     * @return TOS element
     */
    public E top() {
        return (peek());
    }

    public int size() {
        return (stack.size());
    }
}
