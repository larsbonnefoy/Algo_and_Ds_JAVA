package ft;

/**
 * BinarySearchTree.java
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 01/12/2023, vendredi
 *
 * Binary Search Tree implementation that stores a key and maps it to a value
 * K represents Key
 * V represents Value
 * from Cormen, T., Leiserson, C., Rivest, R., &; Stein, C. (2009). Introduction to algorithms. Mit Press.
 **/
public class BinarySearchTree<K extends Comparable<K>, V> {

    /**
     * Private internal class representing a TreeNode
     */
    protected class TreeNode {
        private final V value;
        private final K key;
        private TreeNode p = null;
        private TreeNode left = null;
        private TreeNode right = null;

        TreeNode(K key, V value) {
            this.value = value;
            this.key = key;
        }

        @Override
        public String toString() {
            return "(" +
                    "v=" + value +
                    ", k=" + key +
                    ")";
        }

        /**
         * Copy constructor of Tree Node
         * @param n - Node to copy from
         */
        TreeNode(TreeNode n) {
            this.value = n.value;
            this.key = n.key;
            this.p = n.p;
            this.left = n.left;
            this.right = n.right;
        }

        public TreeNode getLeft() {
            return left;
        }

        public TreeNode getRight() {
            return right;
        }

        public V getValue() {
            return value;
        }
    }

    protected TreeNode root = null;

    protected int size = 0;

    /**
     * Constructor does nothing as values are getting insert in form of nodes step by step
     */
    BinarySearchTree() {}

    /**
     * Adds key value pair into the Binary Search Tree
     * @param key - key to insert
     * @param value - value to map to that key
     */
    public void add(K key, V value) {
        TreeNode toInsert = new TreeNode(key, value);
        insert(toInsert);
        size++;
    }

    /**
     * Displays all values of the bst in order
     */
    public void display() {
        inOrderTreeWalk(root);
    }

    /**
     * Returns value of key passed as parameter
     * @param key - element to find
     * @return Value or null if key is not present
     */
    public V findValue(K key) {
        TreeNode ret = search(root, key);
        return (ret != null ? ret.value : null);
    }

    /**
     * Deletes node with value with given key
     * @param key - Key of element to delete
     * @return value of deleted element or null if element does not exist
     */
    public V delete(K key) {
        TreeNode toDelete = search(root, key);
        if (toDelete != null) {
            deleteNode(toDelete);
        }
        return (toDelete != null ? toDelete.value : null);
    }

    public int size() {
        return size;
    }
    /**********************************Private function manipulating the tree**********************************/

    /**
     * Does an in order walk of the Tree, displaying each KEY
     */
    private void inOrderTreeWalk(TreeNode x) {
        if (x != null) {
           inOrderTreeWalk(x.left);
           System.out.println(x.key);
           inOrderTreeWalk(x.right);
        }
    }

    /**
     * Given a reference to a certain node x, searches if Value V is in x's subtree
     *
     * @param x - starting node
     * @param k - key to find
     * @return - returns node which contains key , or null if key is not found
     *
     * from Cormen, T., Leiserson, C., Rivest, R., &; Stein, C. (2009). Introduction to algorithms. Mit Press.
     */
    private TreeNode search(TreeNode x, K k) {
        while (x != null && k.compareTo(x.key) != 0) {
            //Go down the tree until we find the x with the right key
            if (k.compareTo(x.key) < 0) {
                x = x.left;
            }
            else {
                x = x.right;
            }
        }
        return x;
    }

    /**
     * Finds the minimum node from start node x
     * @param x - Node at which to start the search
     * @return Tree node with the min key
     *
     * from Cormen, T., Leiserson, C., Rivest, R., &; Stein, C. (2009). Introduction to algorithms. Mit Press.
     */
    private TreeNode minimum(TreeNode x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    /**
     * Finds the maximum node from start node x
     * @param x - Node at which to start the search
     * @return Tree node with the maximum key
     *
     * from Cormen, T., Leiserson, C., Rivest, R., &; Stein, C. (2009). Introduction to algorithms. Mit Press.
     */
    private TreeNode maximum(TreeNode x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    /**
     * Finds successor of node x
     *
     * If x has a right child, successor is just the left most value of this right child
     *
     * If the right subtree of node x is empty and x has a successor y, then y is the lowest
     * ancestor of x whose left child is also an ancestor of x.
     *
     * =>   To find y, we go up the tree from x until we encounter either the root node
     *      or a node that is the left child of its parents
     *
     * @param x - node of which to find the successor
     * @return TreeNode which is the successor of node x
     *
     * from Cormen, T., Leiserson, C., Rivest, R., &; Stein, C. (2009). Introduction to algorithms. Mit Press.
     */
    private TreeNode successor(TreeNode x) {
        if (x.right != null) {                              //If x has a right child, successor is just the left most value of this right child
            return minimum(x.right);
        }
        else {
            TreeNode y = new TreeNode(x.p);     //y is the parent of current x
            while (y != null && x == y.right) {            //We check if y is the root or if x is still a right child of y
                x = y;
                y = y.p;
            }
            return y;
        }
    }

    /**
     * Finds predecessor of node x
     *
     * Symmetric logic of successor function
     *
     * If x has a left child, predecessor is just the right most value of this left child
     *
     * If the right subtree of node x is empty and x has a predecessor y, then y is the lowest
     * ancestor of x whose left child is also an ancestor of x.
     *
     * =>   To find y, we go up the tree from x until we encounter either the root node
     *      or a node that is the left child of its parents
     *
     * @param x - node of which to find the predecessor
     * @return TreeNode which is the predecessor of node x
     *
     * from Cormen, T., Leiserson, C., Rivest, R., &; Stein, C. (2009). Introduction to algorithms. Mit Press.
     */
    private TreeNode predecessor(TreeNode x) {
        if (x.left != null) {                           //If x has a left child, predecessor is just the right most value of this left child
            return maximum(x.left);
        }
        else {
            TreeNode y = new TreeNode(x.p);             //y is the parent of current x
            while (y != null && x == y.left) {          //We check if y is the root or if x is still a left child of y
                x = y;
                y = y.p;
            }
            return y;
        }
    }

    /**
     * Insert in Tree
     * Begins at the root of the tree. reference x traces a simple path downward looking for a NIL to replace with the
     * input node z. Maintains a trailing reference y as the parent of x.
     * Two references are going left or right down the tree depending on the comparison of z.key until x becomes NIL.
     * x is the nil value we will replace and y is its parent.
     * Runs in 0(h) (h being height of the tree ie number of nodes from the root to leaves)
     *
     * from Cormen, T., Leiserson, C., Rivest, R., &; Stein, C. (2009). Introduction to algorithms. Mit Press.
     */
    private void insert(TreeNode z) {
        TreeNode x = root;                              //used to search through the tree
        TreeNode y = null;                              //y will represent x's parent
        while (x != null) {
            y = x;
            if (z.key.compareTo(x.key) < 0) {
                x = x.left;
            }
            else {
                x = x.right;
            }
        }
        z.p = y;                                    //Set link between z and its parent tracked by y
        if (y == null) {
            root = z;                               //Tree was empty
        }
        //set z at the right spot from its parent reference y;
        else if (z.key.compareTo(y.key) < 0) {
            y.left = z;
        }
        else {
            y.right = z;
        }
    }

    /**
     * Replaces u by v in the three so that v has u's parent and u's parent has v as the child
     * Allows v to be null
     *
     * @param u - node that is being replaced
     * @param v - node that takes u's place
     *
     * v.left and v.right are not updated, it is the callers responsibility to do so.
     *
     * from Cormen, T., Leiserson, C., Rivest, R., &; Stein, C. (2009). Introduction to algorithms. Mit Press.
     */
    private void transplant(TreeNode u, TreeNode v) {
        if (u.p == null) {                          //u was root
            root = v;
        }
        else if (u == u.p.left) {                   //u was a left child
            u.p.left = v;
        }
        else {                                      //u was right child
            u.p.right = v;
        }
        if (v != null) {
            v.p = u.p;
        }
    }

    /**
     * Deletes node from tree and fixing all the different references
     *
     * Four different cases can happen depending on z's placement in the tree
     * - if z has no left child, then replace z by its right child, which can be null (covers case where z has no children)
     * - if z has on child, that child is a left child, we replace z by its left child.
     * - Otherwise z has both a left and a right child. We need to find z's successor which lies in z's right subtree
     *  (min value of right subtree). How to replace depends on whether y is z's right child.
     *      - if y is z's right child then replace z by y, leaving y's right child alone
     *      - Otherwise, y lies within z's right subtree but is no directly z's right child. In this case we first replace
     *      y by its own child and then replace z by y.
     *
     * @param z - Node to delete
     *
     * from Cormen, T., Leiserson, C., Rivest, R., &; Stein, C. (2009). Introduction to algorithms. Mit Press.
     */
    private void deleteNode(TreeNode z) {
        if (z.left == null) {
            transplant(z, z.right);             //replaces z by its right child
        }
        else if (z.right == null) {
            transplant(z, z.left);              //replaces z by its left child
        }
        else {
            TreeNode y = minimum(z.right);      //y is z's successor
            if (y != z.right) {                 //if y is farther down the tree
                transplant(y, y.right);         //replace y bit its right child
                y.right = z.right;              //z's right child becomes
                y.right.p = y;                  //y's right child
            }
            transplant(z, y);                   //replace z by its successor y
            y.left = z.left;                    //set z's left child to y left child
            y.left.p = y;                       //set parent relation for left child
        }
    }
}
