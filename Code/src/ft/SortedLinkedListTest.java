package ft;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 05/12/2023, mardi
 **/
public class SortedLinkedListTest {


    @Test
    public void addSorted() {
        Random rand = new Random(123);
        SortedLinkedList<Integer> sortedList = new SortedLinkedList<>();
        for (int i = 0; i <  1000; i++) {
            int toInsert = rand.nextInt(10000);
            sortedList.addSorted(toInsert);
        }
        Iterator<Integer> it = sortedList.iterator();
        //Compare current it value with next value. next() increments so effectively on second next() call we get second value
        while (it.hasNext()) {
            Assert.assertTrue(it.next() <= it.next());
        }
    }


}