package DataStructures;

import com.cancion;

public class AVLTree{
    private Node root;

    public class Node {
        public cancion element;
        Node left;
        Node right;
        int height;

        Node(cancion element) {
            this.element = element;
            this.height = 1;
        }
    }

    public Node getRoot(){
        return this.root;
    }

    public void insert(cancion element) {
        root = insert(root, element);
    }

    private Node insert(Node node, cancion element) {
        if (node == null) {
            return new Node(element);
        }

        int compareResult = element.getNombre().toLowerCase().compareTo(node.element.getNombre().toLowerCase());

        if (compareResult < 0) {
            node.left = insert(node.left, element);
        } else if (compareResult > 0) {
            node.right = insert(node.right, element);
        } else {
            // Duplicate element names are not allowed (you can customize this behavior)
            return node;
        }

        // Update height
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Perform rotations if needed
        return balance(node);
    }

    private int height(Node node) {
        return (node != null) ? node.height : 0;
    }

    private int getBalance(Node node) {
        return (node != null) ? height(node.left) - height(node.right) : 0;
    }

    private Node balance(Node node) {
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) >= 0) {
                return rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }

        if (balance < -1) {
            if (getBalance(node.right) <= 0) {
                return rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        // Update heights
        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        // Update heights
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));

        return y;
    }

    public int getSize(Node t){
        if(t == null)
            return 0;
        return 1 + getSize(t.left) + getSize(t.right);
    }

    public void levelTraversal(Node t) throws Exception{
        Queue<Node> queue = new Queue<>(getSize(this.root));
        queue.pushBackData(t);
        while(queue.head != null){
            Node node = queue.topFront();
            queue.popFront();
            System.out.println(node.element.getNombre().toLowerCase());
            if(node.left != null)
                queue.pushBackData(node.left);
            if(node.right != null)
                queue.pushBackData(node.right);
        }   
    }

    public AVLTree.Node nextNode(AVLTree.Node node) {
        if (node != null) {
            if (node.right != null) {
                return findMin(node.right);
            } else {
                return findSuccessor(root, node);
            }
        }
        return null;
    }
    
    private AVLTree.Node findMin(AVLTree.Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    private AVLTree.Node findSuccessor(AVLTree.Node current, AVLTree.Node target) {
        AVLTree.Node successor = null;
        while (current != null) {
            int compareResult = target.element.getNombre().toLowerCase().compareTo(current.element.getNombre().toLowerCase());
            if (compareResult < 0) {
                successor = current;
                current = current.left;
            } else if (compareResult > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return successor;
    }

    public void remove(cancion x) { 
        root = remove(x, root); 
    }

    private Node remove(cancion x, Node t) {
        if(t == null)
            return t; // Item not found; do nothing
        int compareResult = x.getNombre().toLowerCase().compareTo( t.element.getNombre().toLowerCase() );
        if(compareResult < 0)
            t.left = remove(x, t.left);
        else if(compareResult > 0)
            t.right = remove( x, t.right );
        else if(t.left != null && t.right != null) { // Two children  
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    public Node find(cancion element){
        return findElement(element, root);
    }

    private Node findElement(cancion element, Node t){
        int compareResult = element.getNombre().toLowerCase().compareTo(t.element.getNombre().toLowerCase());
        if(compareResult == 0){
            return t;
        } else if(compareResult < 0) {
            if(t.left != null)
                return findElement(element,t.left);
            return t;
        } else {
            if(t.right != null)
                return findElement(element,t.right);
            return t;
        }       
    }
    
    public boolean isEmpty(){
        return this.root == null;
    }
}

