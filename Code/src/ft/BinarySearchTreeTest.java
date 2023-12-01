package ft;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 01/12/2023, vendredi
 **/
public class BinarySearchTreeTest {

    BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();

    @Before
    public void init() {
        bst.add(15, 15);
        bst.add(18, 18);
        bst.add(17, 17);
        bst.add(20, 20);
        bst.add(6, 6);
        bst.add(7, 7);
        bst.add(13, 13);
        bst.add(9, 9);
        bst.add(3, 3);
        bst.add(2, 2);
        bst.add(4, 4);
    }


    @Test
    public void find() {
        bst.add(-4, -3);
        Assert.assertEquals(-3, (int)bst.find(-4));
        bst.add(10, 9);
        Assert.assertEquals(9, (int)bst.find(10));
        bst.display();
    }

    @Test
    public void delete() {
        bst.add(-4, 4);
        Assert.assertNull(bst.delete(0));
        Assert.assertEquals(2, (int)bst.delete(2));
        Assert.assertEquals(15, (int)bst.delete(15));
        Assert.assertNull(bst.delete(15)); //delete a node where its successor is not directly it's right child
    }
}