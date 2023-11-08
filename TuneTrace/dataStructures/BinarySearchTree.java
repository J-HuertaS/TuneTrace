package DataStructures;


import com.cancion;


class UnderflowException extends Exception {
    public UnderflowException() {
        super("Underflow Exception: The operation was performed on an empty data structure.");
    }
}

public class BinarySearchTree{

    private BinaryNode root;

    //Metodos constructores
    public BinarySearchTree(){
        this.root = null;
    }

    public BinarySearchTree(BinaryNode root){
        this.root = root;
    }

    public BinaryNode getRoot(){
        return this.root;
    }

    public static class BinaryNode {
        public cancion element;
        BinaryNode ancestor; //nodo padre
        BinaryNode left; //hijo izquierdo
        BinaryNode right; //hijo derecho

        public BinaryNode(cancion data){
            this.element = data;
            this.left = null;
            this.right = null;
            this.ancestor = null;
        }

        public BinaryNode(cancion data, BinaryNode ancestor){
            this(data);
            this.ancestor = ancestor;
        }
    }

    public void insert(cancion x) { 
        root = insert(x, root,null);
    }
    
    private BinaryNode insert(cancion x, BinaryNode t, BinaryNode ancestor) {
        if( t == null )
            return new BinaryNode(x,ancestor);
        int compareResult = x.getNombre().toLowerCase().compareTo( t.element.getNombre().toLowerCase() );
        if( compareResult < 0)
            t.left = insert(x, t.left,t);
        else if( compareResult > 0)
            t.right = insert(x, t.right,t);
        else
            ; // Duplicate; do nothing
        
        return t;
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

    public BinaryNode nextNode(BinaryNode node) {
        if (node != null) {
            if (node.right != null) {
                return findMin(node.right);
            } else {
                return findSuccessor(root, node);
            }
        }
        return null;
    }
    
    private BinaryNode findMin(BinaryNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    private BinaryNode findSuccessor(BinaryNode current, BinaryNode target) {
        BinaryNode successor = null;
        while (current != null) {
            int compareResult = target.element.getId().compareTo(current.element.getId());
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
            System.out.println(t.element);
            preOrderTraversal(t.left);
            preOrderTraversal(t.right);
        }
    }

    public void postOrderTraversal(BinaryNode t){
        if(t != null){
            postOrderTraversal(t.left);
            postOrderTraversal(t.right);
            System.out.println(t.element);
        }
    }

    public void levelTraversal(BinaryNode t) throws Exception{
        LinkedList<BinaryNode> queue = new LinkedList<>();
        queue.pushBackData(t);
        while(queue.head != null){
            BinaryNode node = queue.topFront();
            queue.popFront();
            System.out.println(node.element);
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

    public int height(BinaryNode t) { 
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height(t.left), height(t.right) );
    }
}