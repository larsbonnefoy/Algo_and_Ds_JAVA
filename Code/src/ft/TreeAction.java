package ft;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 06/12/2023, mercredi
 *
 * Abstract class that defines TreeAction.
 * Function that has to be carried out on a node of a tree
 *
 * N represents a TreeNode, but as it's a protected class inside BinarySearchTree we cannot use it directly here
 **/
public abstract class TreeAction {
    public abstract void run(Object n);
}
