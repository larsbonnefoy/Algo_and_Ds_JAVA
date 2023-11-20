/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 20/11/2023, lundi
 **/
package ft;
import org.junit.Assert;
import org.junit.Test;
public class StackTest {

    @Test
    public void generalTest(){

        Stack<Integer> stack = new Stack<>();
        Assert.assertTrue(stack.empty());
        stack.push(1);
        Assert.assertFalse(stack.empty());
        Assert.assertEquals(1, (int)stack.peek());
        stack.push(2);
        Assert.assertEquals(2, (int)stack.peek());
        Assert.assertEquals(2, (int)stack.pop());
        Assert.assertEquals(1, (int)stack.peek());
        Assert.assertEquals(1, (int)stack.pop());
        Assert.assertTrue(stack.empty());
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        Assert.assertEquals(10, stack.size());
        for (int i = stack.size() - 1; i >= 0; i--) {
            Assert.assertEquals(i, (int)stack.peek());
            Assert.assertEquals(i, (int)stack.pop());
        }
    }
}
