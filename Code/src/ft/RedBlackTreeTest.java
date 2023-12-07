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
    }

}