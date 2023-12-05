package ft;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 05/12/2023, mardi
 **/
public class CircularVectorTest {

    CircularVector<Integer> circularVectorA;
    CircularVector<Integer> circularVectorB;

    @Before
    public void init() {
        //Default vector
        circularVectorA = new CircularVector<>();
        //Vector with more capacity
        circularVectorB = new CircularVector<>(20);

    }

    @Test
    public void all() {
        for (int i = 0; i < 5; i++) {
            circularVectorA.addFirst(i + 1);
            circularVectorB.addLast(i + 1);
        }
        int prevLast = circularVectorA.getLast();
        circularVectorA.addLast(6);
        Assert.assertEquals(6, (int)circularVectorA.getLast());

        circularVectorA.removeLast();
        Assert.assertEquals(prevLast, (int)circularVectorA.getLast());

        int prevFirst = circularVectorB.getFirst();
        circularVectorB.addFirst(6);
        Assert.assertEquals(6, (int)circularVectorB.getFirst());

        circularVectorB.removeFirst();
        Assert.assertEquals(prevFirst, (int)circularVectorB.getFirst());

    }

}