/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 20/11/2023, lundi
 **/

package ft;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class LinkedListTest {
    LinkedList<Integer> list =  new LinkedList<>();
    LinkedList<Integer> secondList =  new LinkedList<>();

    @Before
    public void initList() {
        for (int i = 0; i < 10 ; i++) {
            list.add(i, i);
        }
        for (int i = 1; i < 5; i++) {
            secondList.add(i - 1, i);
        }
    }

    @Test
    public void iteratorCheck() {
        System.out.println(list);
        Iterator<Integer> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals((int)it.next(), i);
        }
        Iterator<Integer> revIt = list.descendingIterator();
        for (int i = list.size() - 1; i >= 0 ; i--) {
            Assert.assertEquals((int)revIt.next(), i);
        }
    }

    @Test
    public void frontAndBack() {
       list.addFirst(-1);
       Assert.assertEquals(-1, (int)list.getFirst());
        list.addLast(-2);
        Assert.assertEquals(-2, (int)list.getLast());
        Assert.assertEquals(12, list.size());
    }

    @Test
    public void ex1() {
        Assert.assertEquals(secondList.toString(), "(1 2 3 4 )" );
    }

    //Did not rewrite function, used reverse iterator to go through the list in reverse
    @Test
    public void ex2() {
        Iterator<Integer> revIt = secondList.descendingIterator();
        for (int i = secondList.size(); i >= 1 ; i--) {
            Assert.assertEquals((int)revIt.next(), i);
        }
    }

    /**
     * Adds element in the middle, checks if references in nodes at the left and the right of inserted
     * node still hold.
     */
    @Test
    public void addMiddle() {
        list.add(5, 10);
        System.out.println(list);
        Assert.assertEquals(4, (int) list.get(4));
        Assert.assertEquals(5, (int) list.get(6));
    }

    @Test
    public void removeElement() {
        int prevSize = list.size();
        int removedVal = list.removeFirst();
        Assert.assertEquals(0, removedVal);
        Assert.assertEquals(1, (int)list.getFirst());
        Assert.assertEquals(prevSize - 1, list.size());
        prevSize = list.size();
        removedVal = list.remove(5); //removed value 6 from list
        Assert.assertEquals(6, removedVal);
        Assert.assertEquals(prevSize - 1, list.size());
        for (Integer integer : list) {
            Assert.assertNotSame(integer, 6);
        }
        Iterator<Integer> revit = list.descendingIterator();
        while (revit.hasNext()) {
            Assert.assertNotSame(revit.next(), 6);
        }
        prevSize = list.size();
        removedVal = list.removeLast();
        Assert.assertEquals(9, removedVal);
        Assert.assertEquals(prevSize - 1, list.size());
        Assert.assertEquals(8, (int)list.getLast());

        //Testing on Size 1 lists
        LinkedList<Integer> list1 = new LinkedList<>();
        list1.addFirst(1);
        int elem = list1.removeFirst();
        Assert.assertEquals(1, elem);
        Assert.assertEquals(0, list1.size());

        list1.addFirst(1);
        elem = list1.removeLast();
        Assert.assertEquals(1, elem);
        Assert.assertEquals(0, list1.size());

        list1.addFirst(1);
        elem = list1.remove(0);
        Assert.assertEquals(1, elem);
        Assert.assertEquals(0, list1.size());
    }

}
