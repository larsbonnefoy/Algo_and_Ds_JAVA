package ft;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 07/12/2023, jeudi
 **/
public class RedBlackTreeTest {


    /**
     * Runs multiple loops, make a tree size between 2^4 and 2^20
     * Fills the tree with ascending values and checks if the height does not get bigger than 2log(n) + 1
     * Checks with get method if values can be found back
     * Then Creates a random list of elements and proceeds to delete values depending on this random list
     * Checks if returns value is correct.
     * Checks if searching for this deleted value returns correctly null
     */
    @Test
    public void put() {
        //from 2^4 to 2^20
        for (int i = 4; i < 20; i++) {
            ArrayList<Integer> randList = new ArrayList<>();
            int maxVal = 1 << i;
            //creating random list
            for (int j = 0; j < maxVal; j++) {
                randList.add(j);
            }
            Collections.shuffle(randList);

            RedBlackTree<Integer, String> rbt = new RedBlackTree<>();
            //add ascending elements to the tree
            for (int j = 0; j < maxVal; j++) {
                rbt.put(j, String.format("%d", j + 1));
            }
            //Check height and size of tree
            double res = Math.log(maxVal)/Math.log(2);
            int maxHeight = (int)(2 * res) + 1;
            Assert.assertTrue(rbt.height() <= maxHeight);
            Assert.assertEquals(rbt.size(), maxVal);
            //Checks if we can retrieve all elements from the tree
            for (int j = 0; j < maxVal; j++) {
                Assert.assertEquals(String.format("%d", j + 1),rbt.get(j));
            }
            //Checks if we can delete all elements from the tree deletes them in random order, not in insertion order
            //once element delete, try to access it again and check if element is null
            for (int j = 0; j < randList.size(); j++) {
                int elem = randList.get(j);
                Assert.assertEquals(String.format("%d", elem + 1), rbt.remove(elem));
                Assert.assertEquals(maxVal - 1 - j, rbt.size());
                Assert.assertNull(rbt.get(elem));
            }
        }
    }

    //Adds values 41, 38, 31, 12, 19, 8 into the three.
    //Same as exercise 13.3-2 at page 346 from ref book
    //then proceeds to delete those values
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