package DataStructures;

import com.cancion;

class UnderflowException extends Exception {
    public UnderflowException() {
        super("Underflow Exception: The operation was performed on an empty data structure.");
    }
}

public class AVLTreeTT {

    private BinaryNode root;

    //Metodos constructores
    public AVLTreeTT(){
        this.root = null;
    }

    public AVLTreeTT(BinaryNode root){
        this.root = root;
    }

    public BinaryNode getRoot(){
        return this.root;
    }

    public static class BinaryNode {
        public cancion element;
        int height;
        BinaryNode ancestor; //nodo padre
        BinaryNode left; //hijo izquierdo
        BinaryNode right; //hijo derecho

        public BinaryNode(cancion data){
            this.element = data;
            this.left = null;
            this.right = null;
            this.ancestor = null;
            this.height = 1;
        }

        public BinaryNode(cancion data, BinaryNode ancestor){
            this(data);
            this.ancestor = ancestor;
        }
    }

    private BinaryNode rotateRight(BinaryNode t) {
        BinaryNode x = t.left;
        BinaryNode z = x.right;
        x.right = t;
        t.left = z;

        // Actualizar el padre del nodo x
        if (t.ancestor != null) {
            if (t.ancestor.left == t) {
                t.ancestor.left = x;
            } else {
                t.ancestor.right = x;
            }
        }
        x.ancestor = t.ancestor;
        t.ancestor = x;

        t.height = 1 + Math.max(height(t.left), height(t.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return x;
    }

    private BinaryNode rotateLeft(BinaryNode t) {
        BinaryNode x = t.right;
        BinaryNode y = x.left;
        x.left = t;
        t.right = y;

        // Actualizar el padre del nodo x
        if (t.ancestor != null) {
            if (t.ancestor.left == t) {
                t.ancestor.left = x;
            } else {
                t.ancestor.right = x;
            }
        }
        x.ancestor = t.ancestor;
        t.ancestor = x;

        t.height = 1 + Math.max(height(t.left), height(t.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return x;
    }

    private int getBalance(BinaryNode node) {
        return height(node.left) - height(node.right);
    }

    public void insert(cancion x) { 
        root = insert(x, root, null);
    }
    
    private BinaryNode insert(cancion x, BinaryNode t, BinaryNode ancestor) {
        if (t == null) {
            return new BinaryNode(x, ancestor);
        }
        int compareResult = x.getNombre().toLowerCase().compareTo(t.element.getNombre().toLowerCase());
        if (compareResult < 0) {
            t.left = insert(x, t.left, t);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right, t);
        }
        
        adjustHeight(t);
        balanceTree(t,x);

        return t;
    }

    private void balanceTree(BinaryNode t,cancion x) {
        int balance = getBalance(t);

        if (balance > 1) {
            if (x.getNombre().toLowerCase().compareTo(t.left.element.getNombre().toLowerCase()) < 0) {
                // Left Left Case
                root = rotateRight(t);
            } else {
                // Left Right Case
                t.left = rotateLeft(t.left);
                root = rotateRight(t);
            }
        } else if (balance < -1) {
            if (x.getNombre().toLowerCase().compareTo(t.right.element.getNombre().toLowerCase()) > 0) {
                // Right Right Case
                root = rotateLeft(t);
            } else {
                // Right Left Case
                t.right = rotateRight(t.right);
                root = rotateLeft(t);
            }
        }
    }

    public void remove(cancion x) { 
        root = remove(x, root); 
    }

    private BinaryNode remove(cancion x, BinaryNode t) {
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

    public BinaryNode nextNode(BinaryNode node){
        if(node.right != null)
            return leftDescendant(node.right);
        else
            return rightAncestor(node);
    }

    private BinaryNode leftDescendant(BinaryNode node){
        if(node.left != null)
            return leftDescendant(node.left);
        else
            return node;
        
    }

    private BinaryNode rightAncestor(BinaryNode node){
        if(node.ancestor != null){
            int compareResult = node.element.getNombre().toLowerCase().compareTo(node.ancestor.element.getNombre().toLowerCase());
            if(compareResult < 0)
                return node.ancestor;
            else
                return rightAncestor(node.ancestor);
        } else {
            return null;
        }  
    }

    public cancion findMin( ) throws Exception {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    private BinaryNode findMin( BinaryNode t ) {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        else
            return findMin( t.left );
    }

    public cancion findMax( ) throws Exception {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }
    
    private BinaryNode findMax( BinaryNode t ) {
        if( t == null )
            return null;
        else if( t.right == null )
            return t;
        else
            return findMax( t.right );
    }

    public BinaryNode find(cancion element){
        return findElement(element, root);
    }

    private BinaryNode findElement(cancion element, BinaryNode t){
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

    public boolean contains(cancion x){
        return contains(x, root);
    }

    private boolean contains(cancion x, BinaryNode t){
        if (t == null)
            return false;
        int compareResult = x.getNombre().toLowerCase().compareTo(t.element.getNombre().toLowerCase());
        if (compareResult == 0)
            return true; // Elemento encontrado en el nodo actual.
        else if (compareResult > 0)
            return contains(x, t.right); // Buscar en el subárbol derecho.
        else
            return contains(x, t.left); // Buscar en el subárbol izquierdo.
    } 

    public void makeEmpty(){
        this.root = null;
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public void printTree(){
        ;//imprimir arbol - falta definir la manera de recorrerlo, en realidad habran muchos metodos
    }

    public void preOrderTraversal(BinaryNode t){
        if(t != null){
            System.out.println(t.element.getNombre());
            preOrderTraversal(t.left);
            preOrderTraversal(t.right);
        }
    }

    public void postOrderTraversal(BinaryNode t){
        if(t != null){
            postOrderTraversal(t.left);
            postOrderTraversal(t.right);
            System.out.println(t.element.getNombre());
        }
    }

    public void levelTraversal(BinaryNode t) throws Exception{
        Queue<BinaryNode> queue = new Queue<>(getSize(this.root));
        queue.pushBackData(t);
        while(queue.head != null){
            BinaryNode node = queue.topFront();
            queue.popFront();
            System.out.println(node.element.getNombre());
            if(node.left != null)
                queue.pushBackData(node.left);
            if(node.right != null)
                queue.pushBackData(node.right);
        }   
    }

    public int getSize(BinaryNode t){
        if(t == null)
            return 0;
        return 1 + getSize(t.left) + getSize(t.right);
    }

    public void adjustHeight(BinaryNode t) { 
        t.height = Math.max(height(t.left), height(t.right)) + 1;
    }

    private int height(BinaryNode t) { 
        if( t == null )
            return 0;
        else
            return t.height;
    }
}