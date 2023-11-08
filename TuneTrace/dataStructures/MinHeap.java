package DataStructures;
class OverflowException extends Exception {
    public OverflowException() {
        super("Overflow Exception: The operation was performed on a full data structure.");
    }
}

class ElementException extends Exception {
    public ElementException() {
        super("Element Exception: MinHeap does not support negative values.");
    }
}

class MinHeap{

    int[] tree;
    int size = -1;

    public MinHeap(int length){
        tree = new int[length];
        for(int i=0;i<length;i++){
            tree[i] = -1;
        }
    }

    private int parent(int index){
        return ((index+1)/2) - 1;
    }

    private int leftChild(int index){
        return (2*(index+1)) - 1;
    }

    private int rightChild(int index){
        return (2*(index+1));
    }


    private void siftUp(int index){
        while(index>1 && tree[parent(index)]>tree[index]){
            int aux = tree[parent(index)];
            tree[parent(index)] = tree[index];
            tree[index] = aux;
            index = parent(index);
        } 
    }

    private void siftDown(int index){
        int minIndex = index;
        int lChild = leftChild(index);
        if(lChild<=this.size && tree[lChild] < tree[minIndex]){
            minIndex = lChild;
        }
        int rChild = rightChild(index);
        if(rChild<=this.size && tree[rChild] < tree[minIndex]){
            minIndex = rChild;
        }
        if(index != minIndex){
            int aux = tree[minIndex];
            tree[minIndex] = tree[index];
            tree[index] = aux;
            siftDown(minIndex);
        }
    }

    public void insert(int element) throws Exception{
        if(this.size == tree.length)
            throw new OverflowException();
        if(element < 0)
            throw new ElementException();
        size += 1;
        tree[size] = element;
        siftUp(size);
    }

    public int ExtractMin(){
        int result = tree[0];
        tree[0] = tree[size];
        tree[size] = -1;
        size -= 1;
        siftDown(0);
        return result;
    }

    public int getMin(){
        return tree[0];
    }

    public void remove(int index){
        tree[index] = getMin() - 1;
        siftUp(index);
        ExtractMin();
    }

    public void changePriority(int index, int newPriority){
        int oldPr = tree[index];
        tree[index] = newPriority;
        if(newPriority > oldPr)
            siftUp(index);
        else
            siftDown(index);
    }

    public void printHeap(){
        for(int element: tree){
            System.out.print(element+" ");
        }
    }

    public boolean isEmpty(){
        return this.size <= -1;
    }
}