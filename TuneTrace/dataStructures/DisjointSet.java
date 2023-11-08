package DataStructures;
public class DisjointSet {
    private ArrayListNormal parent;
    private ArrayListNormal rank;

    public DisjointSet(){
        parent = new ArrayListNormal();
        rank = new ArrayListNormal();
    }

    public void makeSet(int element){
        while(parent.lenghtArray() - 1 < element){
            parent.growArray();
        }
        parent.Array[element] = element;
        rank.Array[element] = 0;
    }

    
}
