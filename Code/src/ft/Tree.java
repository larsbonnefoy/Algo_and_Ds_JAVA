package ft;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 06/12/2023, mercredi
 *
 * Tree implementation as in exercises. K represents value and key at the same time
 * Wrapper functions around BinarySearchTree<K, V> class.
 *
 * public functions:
 * public void insert(Comparable)
 * public boolean find(Comparable)
 **/
public class Tree<K extends Comparable<K>> extends BinarySearchTree<K, K>{


    public Tree() {
    }

    public void insert(K element) {
        add(element, element);
    }

    public boolean find(K element) {
        return (findValue(element) != null);
    }


    public void traverse(TreeAction action) {
        Stack<TreeNode> t = new Stack<>();
        t.push(root);
        while (!t.empty()) {
            TreeNode n = t.pop();
            action.run(n);
            if (n.getLeft() != null) {
                t.push(n.getLeft());
            }
            if (n.getRight() != null) {
                t.push(n.getRight());
            }
        }
    }

    /**
     * //TODO: is there a way to return from the run function??
     * @return
     */
    @Override
    public String toString() {
        String s = " ";
        traverse(new TreeAction() {
            @Override
            public void run(Object n) {
                System.out.println(n.toString());
            }
        });
        return s;
    }

    /**
     * Depth first search of the tree
     * STACK: FIFO: as we push right part first, left part is going to be accessed sooner, we first to down the
     * tree on the left. Once we hit a null on the left, next element on the stack will be the most recent right child.
     * We then go down its left side as well until we reach a null. Loops starts then over again.
     * O(n) as we traverse each element once.
     * @return string containing result of search
     */
    public String depthFirst() {
        String s = "";
        Stack<TreeNode> t = new Stack<>();
        t.push(root);
        while (!t.empty()) {
            TreeNode n = t.pop();
            s += n.getValue().toString();
            if (n.getRight() != null) {
                t.push(n.getRight());
            }
            if (n.getLeft() != null) {
                t.push(n.getLeft());
            }
            s += "\n";
        }
        return s;
    }

    /**
     * breadth first search of the tree
     * QUEUE: LIFO: as we push right part first then its left value, we are going to access them in order value
     * have been added to the queue. We are going to traverse the three from right to left on each level of the tree.
     * O(n) as we traverse each element once.
     * @return string containing result of search
     */
    public String breadthFirst() {
        String s = "";
        Queue<TreeNode> t = new Queue<>();
        t.enqueue(root);
        while (!t.empty()) {
            TreeNode n = t.dequeue();
            s += n.getValue().toString();
            if (n.getRight() != null) {
                t.enqueue(n.getRight());
            }
            if (n.getLeft() != null) {
                t.enqueue(n.getLeft());
            }
            s += "\n";
        }
        return s;
    }

    /**
     * Function that finds the height.
     * Calls heightAtNode from root.
     * @return Integer that represents the Height.
     */
    public int height() {
        return (heightAtNode(root , 0, 0));
    }

    /**
     * Finds depth of tree from root n
     * @param n - node from which to calculate the depth;
     * @return - height at node n;
     */
    private int heightAtNode(TreeNode n, int heightR, int heightL) {
        if (n == null) {
            return 0;
        }
        heightR = 1 + heightAtNode(n.getRight(), heightR, heightL);
        heightL = 1 + heightAtNode(n.getLeft(), heightR, heightL);
        //TODO If possible replace by Math.max, otherwise works like that
        return (heightL > heightR ? heightL : heightR);
    }

    public K findMax() {
        return (maxAtNode(root));
    }

    /**
     * Returns max value from node n
     * @param n - node to search max from
     * @return maximal value.
     */
    private K maxAtNode(TreeNode n) {
        if (n.getRight() == null) {
            return n.getValue();
        }
        else {
            return (maxAtNode(n.getRight()));
        }
    }
}
