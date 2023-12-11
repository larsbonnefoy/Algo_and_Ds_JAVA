package ft;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 11/12/2023, lundi
 *
 * Test cases for Vector class
 **/
public class VectorTest {

    @Test
    public void all() {
        Vector<Integer> vec = new Vector<>();
        final int SIZE = 10000;
        for (int i = 0; i < SIZE; i++) {
            vec.addFirst(i);
            Assert.assertEquals(i, (int)vec.getFirst());
            vec.addLast(i);
            Assert.assertEquals(i, (int)vec.getLast());
            Assert.assertTrue(vec.contains(i));
            Assert.assertFalse(vec.contains(i + 1));
        }
        assertEquals(SIZE * 2, vec.size());
        for (int i = (vec.size() / 2 ) - 1; i >= 0; i--) {
            Assert.assertTrue(vec.contains(i));
            Assert.assertEquals(i, (int)vec.getFirst());
            Assert.assertEquals(i, (int)vec.getLast());
            vec.removeFirst();
            vec.removeLast();
            Assert.assertFalse(vec.contains(i));
        }
        Assert.assertTrue(vec.isEmpty());
    }

}