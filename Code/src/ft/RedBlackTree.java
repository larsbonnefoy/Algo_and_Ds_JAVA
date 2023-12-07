package ft;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 07/12/2023, jeudi
 *
 * Implementation of a red-black Tree.
 *
 * Binary Search tree that with help of 5 properties remains balanced. A red-black tree with n keys
 * has at most a height 2log(n + 1) (which is O(log(n))).
 *
 * 1.   Every node is either red or black
 * 2.   Root is black
 * 3.   Every leaf is black
 * 4.   If a node is red, then both its children are black.
 * 5.   For each node, all simple paths from the node to descendant leaves contain the same
 *      number of black nodes.
 *
 * from Cormen, T., Leiserson, C., Rivest, R., &; Stein, C. (2009). Introduction to algorithms. Mit Press.
 **/
public class RedBlackTree<K extends Comparable<K>, V> {

    private enum Color {
        RED,
        BLACK
    }

    /**
     * Class representing an internal node.
     * It as references to its parent, left and right children.
     * It stores a key value pair.
     * TreeNode has a color that is either red or black.
     */
    private class TreeNode {

        private final K key;
        private final V value;
        private Color color;
        private TreeNode left = NIL;
        private TreeNode right = NIL;
        private TreeNode parent = null;

        /**
         * Creates new nodes of a RBT.
         * By default, color of node is set to red.
         * By default, left and right child are set to NIL
         * @param key - key to map to value
         * @param value  - value to add to the node
         */
        public TreeNode(K key, V value) {
            this.value = value;
            this.key = key;
            this.color = Color.RED;
        }

        /**
         * Used to create sentinel node.
         */
        public TreeNode() {
            this.color = Color.BLACK;
            this.value = null;
            this.key = null;
        }

        @Override
        public String toString() {
            return "(" +
                    "k: " + key +
                    ", v: " + value +
                    ", c: " + color +
                    ')';
        }
    }


    //Sentinel node that represents all leaves and is parent of root.
    //Makes it easier to display leaves as black nodes
    private final TreeNode NIL = new TreeNode();

    //Represents root of RBT
    private TreeNode root = NIL;

    //Number of unique key/pair mappings
    private int count = 0;

    public RedBlackTree() {
    }

    /**
     * //TODO return previously associated value if we overwrite
     * @param key - key to map to value
     * @param value - value to be associated with key
     * @return null atm
     */
    public V put(K key, V value) {
        if (key != null) {
            TreeNode toInsert = new TreeNode(key, value);
            insert(toInsert);
        }
        return null;
    }

    public V putIfAbsent(K key, V value) {
        return null;
    }

    public V get(Object key) {
        return null;
    }

    public V remove(Object key) {
        return null;
    }

    public int size() {
        return count;
    }

    /**
     * Function that finds the height.
     * Calls heightAtNode from root.
     * @return Integer that represents the Height.
     */
    public int height() {
        return (heightAtNode(root , 0, 0));
    }

    public boolean isEmpty() {
        return false;
    }

    /**
     * Displays all the values starting at the root
     */
    public void display() {
        inOrderTreeWalk(root);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        inOrderTreeWalkString(root, res);
        return res.toString();
    }

    /**********************************PRIVATE FUNCTIONS**************************************************/
    /**
     * Does an in order walk of the Tree, displaying each Node
     * Does not display NIL leaves
     */
    private void inOrderTreeWalk(TreeNode x) {
        if (x != NIL) {
            inOrderTreeWalk(x.left);
            System.out.println(x);
            inOrderTreeWalk(x.right);
        }
    }

    /**
     * Does an in order walk of the Tree, storing each node in a string
     * Does not display NIL leaves
     */
    private void inOrderTreeWalkString(TreeNode x, StringBuilder res) {
        if (x != NIL) {
            inOrderTreeWalkString(x.left, res);
            res.append(x).append(" ");
            inOrderTreeWalkString(x.right, res);
        }
    }

    /**
     * Finds depth of tree from root n
     * @param n - node from which to calculate the depth;
     * @return - height at node n;
     */
    private int heightAtNode(TreeNode n, int heightR, int heightL) {
        if (n == NIL) {
            return 0;
        }
        heightR = 1 + heightAtNode(n.right, heightR, heightL);
        heightL = 1 + heightAtNode(n.left, heightR, heightL);
        //TODO If possible replace by Math.max, otherwise works like that
        return (heightL > heightR ? heightL : heightR);
    }

    /**
     * Performs a left rotation of the subtree rooted at x.
     * x's right child y is now at x's position. ( = x's right child becomes x's parent)
     * x is y's left child.
     * y's previous left child is x's right child.
     *
     * O(1) as only references are exchanged.
     *
     * @param x - node at which to perform the right rotation
     *
     * from Cormen, T., Leiserson, C., Rivest, R., &; Stein, C. (2009). Introduction to algorithms. Mit Press.
     */
    private void leftRotate(TreeNode x) {
        TreeNode y = x.right;           //save reference to y (x's right child)
        x.right = y.left;               //x.right was previously y, x.right is now y's left child
        if (y.left != NIL) {            //if y.left subtree is not null
            y.left.parent =  x;         //  then we set back ward link from y to its parent z
        }
        y.parent = x.parent;            //set y's parent as x;
        if (x.parent == NIL) {          //x was the root
            root = y;                   //  then y is set to be the root
        }
        else if (x == x.parent.left) {  //x was a left child of its parent.
            x.parent.left = y;          //  then y is set to be a left child
        }
        else {                          //x was a right child of its parent.
            x.parent.right = y;         //  then y is set to be a right child
        }
        y.left = x;                     //set x as a left child of y.
        x.parent = y;                   //set reverse link form child to parent.
    }

    /**
     * Performs a right rotation of the subtree rooted at x.
     * x's left child y is now at x's position. ( = x's left child becomes x's parent)
     * x is y's right child.
     * y's previous right child is x's left child.
     *
     * O(1) as only references are exchanged.
     *
     * @param x - node at which to perform the right rotation
     *
     * from Cormen, T., Leiserson, C., Rivest, R., &; Stein, C. (2009). Introduction to algorithms. Mit Press.
     */
    private void rightRotate(TreeNode x) {
        TreeNode y = x.left;            //save reference to y (x's left child)
        x.left = y.right;               //x.left was previously y, set to y's right child
        if (y.right != NIL) {           //y's right child was non-empty
            y.right.parent = x;         //set y's right child parent to x
        }
        y.parent = x.parent;            //set y's parent as x
        if (x.parent == NIL) {          //x was the root
            root = y;                   //y is set to be the root
        }
        else if (x == x.parent.left) {  //x was a left child
            x.parent.left = y;          // then we set y as being a left child of x's parent
        }
        else {                          //x was a right child
            x.parent.right = y;         // then we set y as being a right child of x's parent
        }
        y.right = x;                    //set x a right child of y.
        x.parent = y;                   //set reverse link from child to parent
    }

    /**
     * Insert node z into the red black tree
     * Insert procedure is nearly the same is a BinarySearchTree.
     *
     * Begins at the root of the tree. reference x traces a simple path downward looking for a NIL to replace with the
     * input node z. Maintains a trailing reference y as the parent of x.
     * Two references are going left or right down the tree depending on the comparison of z.key until x becomes NIL.
     * x is the nil value we will replace and y is its parent.
     *
     * However, the newly added node z is red by default. This might break one of 2 red-black properties:
     * -> prop 2 if newly added node is root node
     * -> prop 4 if parent of newly added node is red.
     *
     * We need to call rb-insert-fixup to modify red-black tree so that all 5 properties are still maintained
     *
     * Runs in 0(h) (h being height of the tree ie number of nodes from the root to leaves)
     *
     * @param z - node to insert.
     *
     * from Cormen, T., Leiserson, C., Rivest, R., &; Stein, C. (2009). Introduction to algorithms. Mit Press.
     */
    private void insert(TreeNode z) {
        TreeNode x = root;                          //start from root node.
        TreeNode y = NIL;                           //y will represent x's parent
        while (x != NIL) {
            y = x;                                  //set y as being x, we will change x to either is left or right child
            if (z.key.compareTo(x.key) < 0) {       //z is smaller than the current node -> go to the left
                x = x.left;
            }
            else {                                  //z is bigger than the current node -> go to the right
                x = x.right;
            }
        }
        z.parent = y;                               //Set link between z and its parent tracked by y
        if (y == NIL) {
            root = z;                               //Tree was empty
        }
        else if (z.key.compareTo(y.key) < 0) {      //set z as a left child of its parent y
            y.left = z;
        }
        else {
            y.right = z;                            //set z as a right child of its parent y
        }
        rbtInsertFixUp(z);
        count++;
    }

    /**
     * Rearranges the three so that it still keeps its 5 properties valid.
     *
     * Main loop continues as long as z's color is RED.
     * Divided in 2 main cases depending on if z's parent is a left or a right child.
     * z's parent.parent.child is z's uncle.
     * If z's parent is a left (right) child, each of those cases is split into 3 cases where case 2 falls into case 3.
     *  1.z's uncle is red
     *      z's grandparent blackness will fall through to z's parent and uncle
     *      z's grandparent will be set to red.
     *      Change z to z' = z.parent.parent and start loop over again
     *  2.z's uncle is black and z is a right (left) child
     *      Set z as z.parent (= move up one node on which we will rotate)
     *      Do a left (right) rotate on z (this will pull up previous z one up the three)
     *  3.z's uncle is black and z is a left (right) child
     *      Set z's parent color as black
     *      Set z's grandparent color as red
     *      Do a right (left) rotate on z.
     *      (those 3 action will change colors so that two red colors don't follow each other)
     *      Setting z's parent to black will terminate the loop.
     *
     *  Runs in 0(log n)
     *
     * @param z - node that just as been inserted
     *
     * from Cormen, T., Leiserson, C., Rivest, R., &; Stein, C. (2009). Introduction to algorithms. Mit Press.
     */
    private void rbtInsertFixUp(TreeNode z) {
        while (z.parent.color == Color.RED) {
            if (z.parent == z.parent.parent.left) {     //z's parent is a left child
                TreeNode y = z.parent.parent.right;     //y is z's uncle
                if (y.color == Color.RED) {             //if z's parent and uncle are both red: case 1, blackness transfers down
                    z.parent.color = Color.BLACK;       //set z's parent as black
                    y.color = Color.BLACK;              //set z's parent as black
                    z.parent.parent.color = Color.RED;  //set z's grand parent as red
                    z = z.parent.parent;                //move reference to z up so that it lands on the newly created red node and loop starts over again
                }
                else {                                  //z's uncle is black
                    if (z == z.parent.right) {          //z is a right child: case 2
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.color = Color.BLACK;       //z is a left child: case 3;
                    z.parent.parent.color = Color.RED;
                    rightRotate(z.parent.parent);
                }
            }
            else {                                      //z's parent is a right child
                TreeNode y = z.parent.parent.left;      //y is z's uncle
                if (y.color == Color.RED) {             //if z's parent and uncle are both red: case 1
                    y.color = Color.BLACK;
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                }
                else {
                    if (z == z.parent.left) {           //z is a left child: case 2
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
    }
}
