package ft;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 07/12/2023, jeudi
 **/
public class RedBlackTreeTest {


    /**
     * Runs multiple loops.
     * Start with 2^4 values up to 2^20 values.
     * Fills the tree with those values and checks if the height does not get bigger than 2log(n) + 1
     * Checks with get method if values can be found back
     */
    @Test
    public void put() {
        //from 2^4 to 2^20
        for (int i = 4; i < 20; i++) {
            RedBlackTree<Integer, String> rbt = new RedBlackTree<>();
            int maxVal = 1 << i;
            for (int j = 0; j < maxVal; j++) {
                rbt.put(j, String.format("%d", j + 1));
            }
            double res = Math.log(maxVal)/Math.log(2);
            int maxHeight = (int)(2 * res) + 1;
            Assert.assertTrue(rbt.height() <= maxHeight);
            Assert.assertEquals(rbt.size(), maxVal);
            for (int j = 0; j < maxVal; j++) {
                Assert.assertEquals(String.format("%d", j + 1),rbt.get(j));
            }
            for (int j = 0; j < maxVal - 2; j++) {
                Assert.assertEquals(String.format("%d", j + 1), rbt.remove(j));
                Assert.assertEquals(maxVal - 1 - j, rbt.size());
                Assert.assertNull(rbt.get(j));
            }
        }
    }

    //Adds values 41, 38, 31, 12, 19, 8 into the three.
    //Same as exercise 13.3-2 at page 346 from ref book
    @Test
    public void ex3_2() {
        RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
        rbt.put(41, 41);
        rbt.put(38, 38);
        rbt.put(31, 31);
        rbt.put(12, 12);
        rbt.put(19, 19);
        rbt.put(8, 8);
        rbt.put(5, 5);
        //With a normal binary tree height of previous insert would have been 6.
        Assert.assertEquals(4, rbt.height());
        Assert.assertEquals(8, (int)rbt.remove(8));
        Assert.assertNull(rbt.get(8));
        Assert.assertEquals(12, (int)rbt.remove(12));
        Assert.assertNull(rbt.get(12));
        Assert.assertEquals(19, (int)rbt.remove(19));
        Assert.assertNull(rbt.get(19));
        Assert.assertEquals(31, (int)rbt.remove(31));
        Assert.assertNull(rbt.get(31));
        Assert.assertEquals(38, (int)rbt.remove(38));
        Assert.assertNull(rbt.get(38));
        Assert.assertEquals(41, (int)rbt.remove(41));
        Assert.assertNull(rbt.get(41));
        //Assert.assertEquals(19, (int)rbt.remove(19));
    }
}