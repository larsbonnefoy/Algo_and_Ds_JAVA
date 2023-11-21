package ft;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 20/11/2023, lundi
 **/

/**
 * Sorted vector class, inserts always in a sorted way
 * Enables searching for elements in log (n) time;
 * @param <E> has to implement comparable interface
 */
public class SortedVector<E extends Comparable<E>> extends Vector<E> {
    SortedVector() {
        super();
    }

    //TODO: switch to rbt tree if time to do it
    //Problem: insertion is 0(n) when not adding to the back
    //Problem: deletion 0(n) when not removing from the back

    /**
     * Uses binary insertion to insert in correct place in vector
     *
     * @param element - element to insert
     */
    public void insert(E element) {
        //first element, we can insert where we want
        if (size() == 0) {
            addElement(element);
        } else {
            int insertIndex = getIndex(element);
            super.add(insertIndex, element);
        }
    }

    /**
     * Returns index in list given object
     * Starts with comparison on last element as most of the time we will insert an
     * item which has a bigger id than the previous one
     *
     * @param element - element to be inserted
     */
    private int getIndex(E element) {
        if (lastElement().compareTo(element) <= 0) {
            return size();
        } else {
            int start = 0;
            int end = size() - 1;
            while (start <= end) {
                int middle = (start + end + 1) / 2;
                if (element.compareTo((E) elementData[middle]) < 0) {
                    end = middle - 1;
                } else if (element.compareTo((E) elementData[middle]) > 0) {
                    start = middle + 1;
                }
                //Elements are equal in this case, search will stop at next loop evaluation
                else {
                    start = middle + 1;
                }
            }
            return start;
        }
    }
}
