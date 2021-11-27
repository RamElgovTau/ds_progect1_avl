import java.util.LinkedList;
import java.util.List;

/**
 *
 * AVLTree
 *
 * An implementation of an AVL Tree with
 * distinct integer keys and info.
 *
 */

public class AVLTree {
    private IAVLNode root, min, max;
    /**
     * public boolean empty()
     *
     * Returns true if and only if the tree is empty.
     *
     */
    public boolean empty() {
        return !root.isRealNode(); // a tree is empty iff its root points to a virtual node.
    }

    /**
     * public String search(int k)
     *
     * Returns the info of an item with key k if it exists in the tree.
     * otherwise, returns null.
     */
    public String search(int k) {
        IAVLNode result = treePosition(k);
        return result.isRealNode()? result.getValue() : null;
    }

    /**
     * preforms a tree position operation.
     * @param k a key to search for.
     * @return a node with key k in the tree, the virtual node where k should be inserted.
     */
    private IAVLNode treePosition(int k) {
        IAVLNode node = this.root;
        while (node.isRealNode()) {
            if (k == node.getKey()) {
                return node;
            } else if (k < node.getKey()) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return node;
    }

    /**
     * public int insert(int k, String i)
     *
     * Inserts an item with key k and info i to the AVL tree.
     * The tree must remain valid, i.e. keep its invariants.
     * Returns the number of re-balancing operations, or 0 if no re-balancing operations were necessary.
     * A promotion/rotation counts as one re-balance operation, double-rotation is counted as 2.
     * Returns -1 if an item with key k already exists in the tree.
     */
    public int insert(int k, String i) {
        IAVLNode insertionPoint = treePosition(k);
        if (insertionPoint.isRealNode()) {  // tree already has a node with key k

        }
    }
    public int insertionReliance(IAVLNode node) {
        return node.getKey() > 1 ? 0 : 1;
    }

    /**
     * public int delete(int k)
     *
     * Deletes an item with key k from the binary tree, if it is there.
     * The tree must remain valid, i.e. keep its invariants.
     * Returns the number of re-balancing operations, or 0 if no re-balancing operations were necessary.
     * A promotion/rotation counts as one re-balance operation, double-rotation is counted as 2.
     * Returns -1 if an item with key k was not found in the tree.
     */
    public int delete(int k) {
        return 421;	// to be replaced by student code
    }

    /**
     * public String min()
     *
     * Returns the info of the item with the smallest key in the tree,
     * or null if the tree is empty.
     */
    public String min()
    {
        if (this.empty()) {
            return null;
        } else {
            return this.min.getValue();
        }
    }

    /**
     * public String max()
     *
     * Returns the info of the item with the largest key in the tree,
     * or null if the tree is empty.
     */
    public String max() {
        if (this.empty()) {
            return null;
        } else {
            return this.max.getValue();
        }
    }

    /**
     * public int[] keysToArray()
     *
     * Returns a sorted array which contains all keys in the tree,
     * or an empty array if the tree is empty.
     */
    public int[] keysToArray() {
        List<IAVLNode> nodes = traverseInOrder(this.root);
        int[] keys = new int[nodes.size()];
        int i = 0;
        for (IAVLNode node: nodes) {
            keys[i] = node.getKey();
            i++;
        }
        return keys;
    }

    /**
     * public String[] infoToArray()
     *
     * Returns an array which contains all info in the tree,
     * sorted by their respective keys,
     * or an empty array if the tree is empty.
     */
    public String[] infoToArray() {
        List<IAVLNode> nodes = traverseInOrder(this.root);
        String[] data = new String[nodes.size()];
        int i = 0;
        for (IAVLNode node: nodes) {
            data[i] = node.getValue();
            i++;
        }
        return data;
    }

    /**
     * public int size()
     *
     * Returns the number of nodes in the tree.
     */
    public int size() {
        return 422; // to be replaced by student code
    }

    /**
     * public int getRoot()
     *
     * Returns the root AVL node, or null if the tree is empty
     */
    public IAVLNode getRoot() {
        return null;
    }

    /**
     * public AVLTree[] split(int x)
     *
     * splits the tree into 2 trees according to the key x.
     * Returns an array [t1, t2] with two AVL trees. keys(t1) < x < keys(t2).
     *
     * precondition: search(x) != null (i.e. you can also assume that the tree is not empty)
     * postcondition: none
     */
    public AVLTree[] split(int x)
    {
        return null;
    }

    /**
     * public int join(IAVLNode x, AVLTree t)
     *
     * joins t and x with the tree.
     * Returns the complexity of the operation (|tree.rank - t.rank| + 1).
     *
     * precondition: keys(t) < x < keys() or keys(t) > x > keys(). t/tree might be empty (rank = -1).
     * postcondition: none
     */
    public int join(IAVLNode x, AVLTree t)
    {
        return -1;
    }
    private IAVLNode createVirtualNode(IAVLNode parent) {
        return new AVLNode(parent, AVLNode.VIRTUAL_NODE_KEY, null, false);
    }

    /**
     * recursive implementation of in order traversal of a subtree rooted in node.
     * @param node to start in-order traversal from.
     * @return an ordered by key list of the nodes rooted in {@code node}. empty list otherwise.
     */
    private List<IAVLNode> traverseInOrder(IAVLNode node) {
        if (!node.isRealNode()) {
            return new LinkedList<>();
        }
        List<IAVLNode> lst = new LinkedList<>(traverseInOrder((AVLNode) node.getLeft()));
        lst.add(node);
        lst.addAll(traverseInOrder(node.getRight()));
        return lst;
    }

    /**
     * preforms a right rotation on the given node.
     * @param node to rotate right.
     * @return total number of demotion made.
     */
    private int rightRotation(IAVLNode node) {
        // update parents
        if (node.getParent() == null || !node.getParent().isRealNode()) {
            this.root =  this.getRoot().getLeft();
        } else if (((AVLNode) node).isLeftChild()) {
            node.getParent().setLeft(node.getLeft());
        } else {
            node.getParent().setRight(node.getLeft());
        }
        node.getLeft().setParent(node.getParent());
        node.getLeft().getRight().setParent(node);
        node.setParent(node.getLeft());
        // change pointers to make a left rotation
        node.setLeft(node.getLeft().getRight());
        node.getLeft().setRight(node);
        // calculate sub-tree sizes and return number of fixes made.
        return ((AVLNode) node).calculateSize() + ((AVLNode) node.getLeft()).calculateSize();
    }

    /**
     * preforms a left rotation on the given node.
     * @param node to rotate left.
     * @return total number of demotion made.
     */
    private int leftRotation(IAVLNode node) {
        // update parents
        if (node.getParent() == null || !node.getParent().isRealNode()) {
            this.root =  this.getRoot().getRight();
        } else if (((AVLNode) node).isLeftChild()) {
            node.getParent().setLeft(node.getRight());
        } else {
            node.getParent().setRight(node.getRight());
        }
        node.getRight().setParent(node.getParent());
        node.getRight().getLeft().setParent(node);
        node.setParent(node.getRight());
        // change pointers to make a left rotation
        node.setRight(node.getRight().getLeft());
        node.getRight().setLeft(node);
        // calculate sub-tree sizes and return number of fixes made.
        return ((AVLNode) node).calculateSize() + ((AVLNode) node.getRight()).calculateSize();
    }
    /**
     * public interface IAVLNode
     * ! Do not delete or modify this - otherwise all tests will fail !
     */
    public interface IAVLNode{
        public int getKey(); // Returns node's key (for virtual node return -1).
        public String getValue(); // Returns node's value [info], for virtual node returns null.
        public void setLeft(IAVLNode node); // Sets left child.
        public IAVLNode getLeft(); // Returns left child, if there is no left child returns null.
        public void setRight(IAVLNode node); // Sets right child.
        public IAVLNode getRight(); // Returns right child, if there is no right child return null.
        public void setParent(IAVLNode node); // Sets parent.
        public IAVLNode getParent(); // Returns the parent, if there is no parent return null.
        public boolean isRealNode(); // Returns True if this is a non-virtual AVL node.
        public void setHeight(int height); // Sets the height of the node.
        public int getHeight(); // Returns the height of the node (-1 for virtual nodes).
    }

    /**
     * public class AVLNode
     *
     * If you wish to implement classes other than AVLTree
     * (for example AVLNode), do it in this file, not in another file.
     *
     * This class can and MUST be modified (It must implement IAVLNode).
     */
    public class AVLNode implements IAVLNode {
        private static final int VIRTUAL_NODE_KEY = -1;
        private static final String VIRTUAL_NODE_VALUE = null;
        private static final int VIRTUAL_NODE_HEIGHT = -1;
        private String info;
        private int key, size = 0,height;
        private IAVLNode left, right, parent;
        public AVLNode(IAVLNode parent, int key, String info) {
            this.parent = parent;
            this.key = key;
            this.info = info;
        }

        private AVLNode(IAVLNode parent, int key, String info, boolean realNode) {
            this.parent = parent;
            this.key = key;
            this.info = info;

            if (realNode) {
                this.height = 0;
                this.size = 1;
                this.right = createVirtualNode(this);
                this.left = createVirtualNode(this);
            } else {
                this.height = VIRTUAL_NODE_HEIGHT;
                this.size = 0;
            }
        }

        public int getKey() {
            return this.key;
        }
        public String getValue() {
            return this.info;
        }
        public void setLeft(IAVLNode node) {
            this.left = node;
        }
        public IAVLNode getLeft() {
            return this.left;
        }
        public void setRight(IAVLNode node) {
            this.right = node;
        }
        public IAVLNode getRight() {
            return this.right;
        }
        public void setParent(IAVLNode node) {
            this.parent = node;
        }
        public IAVLNode getParent() {
            return this.parent;
        }
        public boolean isRealNode() {
            return this.height != VIRTUAL_NODE_HEIGHT;
        }
        public void setHeight(int height) {
            this.height = height;
        }
        public int getHeight() {
            return isRealNode()? Math.max(this.left.getHeight(), this.right.getHeight()) + 1 : -1; // to be replaced by student code
        }


        /**
         *
         * @return true if this node is a left child, false if it is a right child.
         */
        public boolean isLeftChild() {
            return parent != null && parent.getKey() > this.key;
        }
        /**
         *
         * @return true if this node is a leaf, false otherwise.
         */
        public boolean isLeaf() {
            return (!this.left.isRealNode()) && (!this.right.isRealNode());
        }


        /**
         *
         * @return successor of this node.
         */
        public IAVLNode successor() {
            AVLNode node;
            if(this.right.isRealNode()) {  // if this node has right child
                node = (AVLNode) this.right;
                while (node.getLeft().isRealNode()) {
                    node = (AVLNode) node.getLeft();
                }
            } else {  // this does not have a right child, walk up to successor.
                node = this;
                while (node.getParent() != null && !node.isLeftChild()) {
                    node = (AVLNode) node.getParent();
                }
                node = (AVLNode) node.getParent();
            }
            return node;
        }

        /**
         * replace parents of two nodes.
         * @param node to replace parent with.
         */
        public void replaceParentsWith(IAVLNode node) {
            if (this.parent != null) {
                if (this.isLeftChild()) {
                    this.parent.setLeft(node);
                } else {
                    this.parent.setRight(node);
                }
            } else {
                AVLTree.this.root = node;
            }
        }
        public int leftRankDifference() {
            return this.getHeight() - ((AVLNode) this.getLeft()).getHeight();
        }
        public int rightRankDifference() {
            return this.getHeight() - ((AVLNode) this.getRight()).getHeight();
        }

        /**
         * calculates size of subtree rooted in this node.
         * fix height if needed.
         * @return 1 if the height was fixed, 0 otherwise.
         */
        public int calculateSize() {
            this.size = (this.right.isRealNode()? ((AVLNode) this.right).size :
                    this.right.isRealNode() ? ((AVLNode) this.right).size : 0) + 1;
            if (this.isLeaf() && this.getHeight() != 0) {
                this.setHeight(0);
                return 1;
            }
            return 0;
        }
    }
}

