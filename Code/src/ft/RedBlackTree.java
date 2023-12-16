package ft;

import java.util.Iterator;

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
public class RedBlackTree<K extends Comparable<K>, V> implements Iterable<V> {

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
    //CANNOT BE MADE FINAL as we need to set its parent in delete procedure
    private TreeNode NIL = new TreeNode();

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

    public V get(K key) {
        TreeNode ret = search(root, key);
        return (ret != null ? ret.value : null);
    }

    /**
     * Removes element with key k from the tree if present, null otherwise
     * @param key - key of element to remove
     * @return Value of removed element or null if element does not exist
     */
    public V remove(K key) {
        TreeNode toDelete = search(root, key);
        if (toDelete != null) {
            rbtDelete(toDelete);
        }
        return (toDelete != null ? toDelete.value : null);
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
     * Given a reference to a certain node x, searches if Value V is in x's subtree
     *
     * @param x - starting node
     * @param k - key to find
     * @return - returns node which contains key , or null if key is not found
     *
     * from Cormen, T., Leiserson, C., Rivest, R., &; Stein, C. (2009). Introduction to algorithms. Mit Press.
     */
    private TreeNode search(TreeNode x, K k) {
        while (x != NIL && k.compareTo(x.key) != 0) {
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
     * If x has a right child, successor is just the left most value of this right child
     *
     * If the right subtree of node x is empty and x has a successor y, then y is the lowest
     * ancestor of x whose left child is also an ancestor of x.
     *
     * Else to find y, we go up the tree from x until we encounter either the root node
     * or a node that is the left child of its parents
     *
     * @param x - node of which we want to find the successor
     * @return TreeNode that is the successor of node x.
     */
    private TreeNode successor(TreeNode x) {
        if (x.right != NIL) {
            return minimum(x.right);
        }
        else {
            TreeNode y = x.parent;
            //TreeNode y = new TreeNode(x.parent);
            while (y != NIL && x == y.right) {
                x = y;
                y = y.parent;
            }
            return y;
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
     * Rearranges the tree so that it still keeps its 5 properties valid.
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
     *      Do a left (right) rotate on z (this will move previous z one up the tree)
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

    /**
     * Finds the minimum when starting from node x
     * @param x - node at which to start the search
     * @return Minimum tree node
     */
    private TreeNode minimum(TreeNode x) {
        while (x.left != NIL) {
            x = x.left;
        }
        return x;
    }

    /**
     * Replaces the subtree rooted at node u by the subtree rooted at node v
     *
     * v can be NIL
     *
     * @param u - node to be replaced
     * @param v - node to replace by
     */
    private void rbtTransplant(TreeNode u, TreeNode v) {
        if (u.parent == NIL) {
            root = v;
        }
        else if (u == u.parent.left) {
            u.parent.left = v;
        }
        else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    /*
     * Similar to bst delete:
     *  - when z has only one child: transplant z with its right or left child
     *  - when z has two children: find z's successor y, depending on if y is z's direct right child or further down the
     *    tree we need one more transplant operation.
     *
     * But this deletion might break rbt properties, this is why we need to track more information and to call an
     * additional method.
     *
     *  - We set sentinel node NIL in case deleted node has no children
     *
     *  - We need to keep track of nodes x (node that will take y's previous position) and y.
     *      - if z has at most one child, y will be z and x will be z's child (left or right)
     *      - else y is z's successor and x is y's child. y will take z's position and color. x will take y's previous position
     *        (y has a most one child as it is z's successor which is the minimum of z's right subtree)
     *
     *  - Need to call rbtDeleteFixup on x to fix red black tree properties which might have been broken if y is black:
     *      - We either removed z (with at most 1 child) which was a black node (y's color is set to z's color in this case).
     *      - We moved y around when z has two children (y being z's successor) and y was a black node
     *      Both of those cases will result in a modified black height on paths that contained either z in the first case
     *      or y in the second.
     *      If y was red all rbt properties still hold, no need for a call to rbtDeleteFixup
     *
     *  - To restore black height property that has been broken by moving or removing a black node we could say that
     *    this node's blackness transfers down to x, giving x an extra blackness, x is then either double black or red-black.
     *    This in turn breaks property 1.
     *    We need to make a call to rbtDeleteFixup to fix this doubly colored node. The color of the node does not
     *    really change: if y is black and x is black we have a double black node and if y is black and x is red we have a red-black node
     *    ("Extra black on a node is reflected in x's pointing to the node rather than in the color attribute")
     *
     *  - In the case where z has two children and y is z's right child and y's right child x is NIL we need to explicitly
     *    set x's parent to y. rbtDeleteFixup relies on the fact that x.parent must reference the node that became x's
     *    parent in the delete procedure, even if x is NIL.
     *    In the other cases x.p is correctly set by rbtTransplant.
     *
     * from Cormen, T., Leiserson, C., Rivest, R., &; Stein, C. (2009). Introduction to algorithms. Mit Press.
     *
     */
    /**
     * Removes tree node z from red-black tree.
     *
     * @param z node to remove
     *
     */
    private void rbtDelete(TreeNode z) {
        TreeNode x;                                 //will represent y's child
        TreeNode y = z;                             //used to represent either z's colors or z's successor color
        Color yOriginalColor = y.color;
        if (z.left == NIL) {                        //z has only one right child.
            x =  z.right;
            rbtTransplant(z, z.right);
        }
        else if (z.right == NIL) {                  //z has only one left child.
            x = z.left;
            rbtTransplant(z, z.left);
        }
        else {                                      //z has two child nodes
            y = minimum(z.right);                   //y is z's successor
            yOriginalColor = y.color;
            x = y.right;                            //x is y's child. x will take y's previous position in the tree
            if (y != z.right) {                     //y is not directly z's right child
                rbtTransplant(y, y.right);          //replace y by its right child x
                y.right = z.right;                  //z's previous right child becomes y's right child
                y.right.parent = y;                 //set relation in other way
            }
            else {
                x.parent = y;                       //need to set x's parent for fixup function in case x is NIL
            }
            rbtTransplant(z, y);                    //replace z by its successor
            y.left = z.left;                        //z's left child becomes y's left child
            y.left.parent = y;                      //
            y.color = z.color;                      //y takes z's color
        }
        if (yOriginalColor == Color.BLACK) {
            rbtDeleteFixup(x);
        }
        count--;
    }

    /*
     * Fixes issues that arose when node z was deleted by rbtDelete call.
     * Three issues could be present:
     *  - if a red child becomes root
     *  - if deleting a black node resulted in 2 red nodes following each other
     *  - if moving y around leads to a black height of a path containing y to be one less.
     *
     *  We restore the red black tree be trying to get rid of the double black node.
     *  We have a double black node as long as x.color == black.
     *  The main while loop moves this extra black node up the tree.
     *  Inside the while loop x always references a nonroot double black node
     *  As for rbtInsertFixup there are two main cases depending on if x is a left or a right child.
     *  x's sibling is denoted w.
     *  When x is a left child. (operations are symmetric when x is a right child)
     *
     *  1. x's sibling w is red.
     *     Because w is red it must have black children.
     *     We switch colors of w and x.p and perform a left rotation on x.p
     *     This transforms this case into case 2, 3 or 4.
     *     Does nothing to the double black node x which stays in place.
     *
     *  2. x's sibling w is black and both of w's children are black
     *     goal: remove one black from x and one from w -> We transfer one black from x to x.p and set w to red.
     *     we set x as x.p and repeat the loop
     *     This way the additional black value goes one up the tree and black height are restored for previous x value
     *
     *  3. x's sibling w is black, w's left child is red and w's right child is black
     *     We have one black height less on w's side, so we switch w and w.lefts color and rotate right on w.
     *     w will be colored to red and inserted between its right and left child, effectively setting the bh back to 2.
     *     As rotation lowered w by one we have to set x's sibling again (x.p.right).
     *     This new sibling is black with a red right child and thus case 3 falls into case 4
     *
     *  4. x's sibling is black, right child is red
     *     We can finally make the extra black node vanish by doing a few operations
     *     w is set to its parent color
     *     x's parent is then set to black
     *     w's right child is set to black
     *     rotate left on x's parent
     *     set x at root to terminate the loop
     *
     * Case 3 and 4 lead to termination in a fixed amount of moves. Case 1 does a constant set of moves
     * Only case 2 leads to repetition of while loop, which repeats at at most log(n) times as it moves up the tree.
     */
    /**
     * Fix up rbt after deletion
     *
     * O(log n).
     *
     * @param x - node which is occupying y's original position.
     */
    private void rbtDeleteFixup(TreeNode x) {
        while (x != root && x.color == Color.BLACK) {
            if (x == x.parent.left) {                   //x is a left child
                TreeNode w = x.parent.right;            //w is x's sibling
                if (w.color == Color.RED) {             //case 1
                    w.color = Color.BLACK;              //switch color
                    x.parent.color = Color.RED;         //
                    leftRotate(x.parent);
                    w = x.parent.right;                 //set new sibling as rotation moved previous sibling one up
                }
                if (w.left.color == Color.BLACK && w.right.color == Color.BLACK) { //case 2
                    w.color = Color.RED;                //set w to red in order to reduce number of black nodes in this part of the graph
                    x = x.parent;                       //double black is transferred one up the tree
                }
                else {
                    if (w.right.color == Color.BLACK) { //case 3
                        w.left.color = Color.BLACK;
                        w.color = Color.RED;
                        rightRotate(w);
                        w = x.parent.right;
                    }                                   //case 4, stops the loop
                    w.color = x.parent.color;           //set w's color to its parent color BEFORE changing w's parent color
                    x.parent.color = Color.BLACK;
                    w.right.color = Color.BLACK;
                    leftRotate(x.parent);
                    x = root;                           //stop the loop
                }
            }
            else {                                      //x is a right child
                TreeNode w = x.parent.left;             //w is x's sibling
                if (w.color == Color.RED) {             //case 1
                    w.color = Color.BLACK;              //switch color
                    x.parent.color = Color.RED;
                    rightRotate(x.parent);
                    w = x.parent.left;                  //set new sibling as rotation moved previous sibling one up
                }
                if (w.right.color == Color.BLACK && w.left.color == Color.BLACK) { //case 2
                    w.color = Color.RED;                //set w to red in order to reduce number of black nodes in this part of the graph
                    x = x.parent;                       //double black is transferred one up the tree
                }
                else {
                    if (w.left.color == Color.BLACK) { //case 3
                        w.right.color = Color.BLACK;
                        w.color = Color.RED;
                        leftRotate(w);
                        w = x.parent.left;
                    }                                   //case 4, stops the loop
                    w.color = x.parent.color;           //set w's color to its parent color BEFORE changing w's parent color
                    x.parent.color = Color.BLACK;
                    w.left.color = Color.BLACK;
                    rightRotate(x.parent);
                    x = root;                           //stop the loop
                }
            }
        }
        x.color = Color.BLACK;
    }

    /***************************************IMPLEMENTATION OF ITERATOR INTERFACE********************************/
    @Override
    public Iterator<V> iterator() {
        return new RBTIterator();
    }

    private class RBTIterator implements Iterator<V> {
        private TreeNode current = minimum(root);
        private int visitedElements = 0;

        @Override
        public boolean hasNext() {
            return visitedElements < size();
        }

        @Override
        public V next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            visitedElements++;
            V retValue = current.value;
            current = successor(current);
            return retValue;
        }
    }
}
