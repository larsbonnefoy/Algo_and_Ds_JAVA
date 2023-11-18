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

    @Test
    public void removeElement() {
        int prevSize = list.size();
        list.removeFirst();
        Assert.assertEquals(1, (int)list.getFirst());
        Assert.assertEquals(prevSize - 1, list.size());
        prevSize = list.size();
        list.remove(5); //removed value 5 from list
        Assert.assertEquals(prevSize - 1, list.size());
        for (Integer integer : list) {
            Assert.assertNotSame(integer, 5);
        }
        Iterator<Integer> revit = list.descendingIterator();
        while (revit.hasNext()) {
            Assert.assertNotSame(revit.next(), 5);
        }
        prevSize = list.size();
        list.removeLast();
        Assert.assertEquals(prevSize - 1, list.size());
        Assert.assertEquals(8, (int)list.getLast());
    }

}