package ft;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 20/11/2023, lundi
 **/
public class QueueTest {

    Queue<Integer> q;

    @Before
    public void init() {
        q = new Queue<>();
        for (int i = 0; i < 10; i++) {
            q.enqueue(i);
        }
    }

    @Test
    public void generalTests() {
        for (int i = 10; i < 20; i++) {
            q.enqueue(i);
        }
        for (int i = 0; i < 20; i++) {
            Assert.assertEquals(i, (int) q.peek());
            Assert.assertEquals(20 - i, q.size());
            Assert.assertEquals(i, (int) q.dequeue());
        }
    }
}
