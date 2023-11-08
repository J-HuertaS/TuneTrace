package DataStructures;
public class ArrayListNormal{
    public int[] Array;
    public int numItem;

    public ArrayListNormal(){
        Array = new int[16];
        numItem = 0;
    }

    public int lenghtArray(){
        return Array.length;
    }

    public void addItem(int number){
        if(numItem == Array.length){
            growArray();
        }
        Array[numItem] = number;
        numItem++;
    }

    public void removeLast(){
        if(numItem == 0){
            throw new RuntimeException("Is empty!!");
        } else {numItem--;}
    }

    //como tal el metodo para eliminar
    public void removeItem(int element){
        Integer index;
        if(numItem == 0){
            throw new RuntimeException("Is empty!!");
        } 
        else
        {
            index = getIndexOf(element);
            shiftLeft(index);
            numItem--;
        }
    }
    
    //un shift para que reacomode todo, esto puede ser tardado pero realmente no hay de otra-
    //-sin meterme con head, tail y no quiero hacer eso la vdd XD, igual no tarda mucho
    private void shiftLeft(int index) {
        // Desplaza los elementos a la derecha para abrir espacio para el nuevo elemento
        for (int i = index; i < numItem; i++) {
            Array[i] = Array[i + 1];
        }
    }

    public int getItem(int index){
        return Array[index];
    }
    
    //añadí el metodo para buscar ヾ(•ω•`)o
    public boolean searchFor(int element){
        for (int i = 0; i < numItem; i++) {
            if(Array[i] == element){
                return true;
            } 
        }
        return false;
    }

    ///añadí el metodo para buscar según indice para poder eleminar
    public int getIndexOf(int element){
        for (int i = 0; i < numItem; i++) {
            if(Array[i] == element){
                return i;
            } 
        }
        return -1;
    }

    public void growArray(){
        int[] tempArray = new int[Array.length*2];
        for(int i=0; i < numItem; i++){
            tempArray[i] = Array[i];
        }
        Array = tempArray;
    }
    
    public void printArray(){
        System.out.print("[");
        for(int i=0; i < numItem; i++){
            System.out.print(Array[i]+" ");
        }
        System.out.print("]");
    }
    
    //metodo para consultar todos los datos
    public int[] consultData(){
        int[] array = new int[numItem];
        for(int i=0;i<numItem;i++){
            array[i] = this.getItem(i);
        }
        return array;
    }
}
