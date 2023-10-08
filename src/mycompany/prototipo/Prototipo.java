/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prototipo;

/**
 *
 * @author HUERTAS
 */

@SuppressWarnings("unchecked")
//clase general linkedlist, donde se implementan algunos metodos claves de una lista doblemente enlazada
class LinkedList<T>{
    Node head;
    Node tail;
    int size;
    
    @SuppressWarnings("unchecked")
    //clase nodo donde ademas de la key, tenemos informacion de counter o carry para el control de menores.
    static class Node<T> {
        T data;
        Node next;
        Node prev;

        public Node(){
            this(null);
        }

        public Node(T data) {
            this.data = data;
            this.next = null;
        }  
    }
    
    public LinkedList(){
        this.head = null;
        this.tail = null;
    }
    
    //agrega un nodo con la llave "dato" al final de la lista
    public void pushBackData(T dato){
        Node node = new Node(dato);
        if(this.head == null){
            this.head = this.tail = node;
            node.prev = null;
        } else {
            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
        } 
    }
    
    //agrega un nodo con la llave "dato" al comienzo de la lista
    public void pushFrontData(T dato){
        Node node = new Node(dato);
        if(this.head == null){
            this.head = this.tail = node;
            node.prev = node.next = null;
        } else {
            this.head.prev = node;
            node.next = this.head;
            this.head = node;
        }  
    }
    
    //retorna la llave del primer elemento de la lista
    public T topFront(){
        return (T) this.head.data;
    }
    
    //retorna la llave del ultimo nodo de la lista
    public T topBack(){
        return (T) this.tail.data;
    }
    
    //elimina el primer elemento de la lista
    public void popFront() throws Exception{
        if(this.head == null){
            throw new Exception("La lista esta vacia");
        }
        this.head = this.head.next;
        if(this.head == null){
            this.tail = null;
        } else {
            this.head.prev = null;
        }
    }
    
    //elimina el ultimo elemento de la lista
    public void popBack() throws Exception{
        if(this.head == null){
            throw new Exception("La lista esta vacia");
        }
        if(this.head == this.tail){
            this.head = this.tail = null;
        } else {
            this.tail = this.tail.prev;
            this.tail.next = null;
        }
        
    }
    
    //agrega un nuevo nodo a la lista con la llave "data" justo antes del nodo de referencia
    public void addBefore(Node reference, T data) throws Exception{
        Node node = new Node(data);
        if(this.head == null){
             throw new Exception("No se puede insertar el elemento porque la lista esta vacia");
        } else if (!this.findNode(reference)){
            throw new Exception("No se encontro el nodo de referencia");
        }
        if(reference == this.head){
            pushFrontData((T) reference.data);
        } else {
            node.prev = reference.prev;
            reference.prev = node;
            node.next = reference;
            
        }
    }
    
    //agrega un nuevo nodo a la lista con la llave "data" justo despues del nodo de referencia
    public void addAfter(Node reference, T data) throws Exception{
        Node node = new Node(data);
        if(this.head == null){
             throw new Exception("No se puede insertar el elemento porque la lista esta vacia");
        } else if (!this.findNode(reference)){
            throw new Exception("No se encontro el nodo de referencia");
        }
        if(reference == this.tail){
            this.pushBackData((T) reference.data);
        } else {
            node.next = reference.next;
            node.prev = reference;
            reference.next = node;
        }    
    }
    
    //retorna un booleano que indica si el nodo pasado como parametro se encuentra en la lista
    public boolean findNode(Node reference){
        Node auxiliar;
        auxiliar = this.head;
        while(auxiliar != null){
            if(auxiliar == reference){
                return true;
            }
            auxiliar = auxiliar.next;
        }
        return false;
    }
    
    //retorna el primer nodo con la llave "key", en caso de no encontrar uno, retorna un objeto nulo
    public Node findKey(T key){
        Node auxiliar;
        auxiliar = this.head;
        while(auxiliar != null){
            if(auxiliar.data.equals(key)){
                return auxiliar;
            }
            auxiliar = auxiliar.next;            
        }
        System.out.println("NO EXISTE");
        return null;
    }
    
    //elimina todos los elementos de la lista
    public void emptyList(){
        this.tail = null;
        this.head = null;
    }
    
    public void deleteItem(T data) throws Exception{
        Node node = findKey(data);
        if(node == null){
            throw new Exception("No existe el elemento en la lista enlazada");
        }
                if(node.next == null){
                    this.popBack();
                } else {
                    node.prev.next = node.next;
                }
    }
    
    //metodo para imprimir la lista
    public void printList(){
        if(this.head == null){
            System.out.println("Lista vacia");
        } else {
            Node curr;
            curr = this.head;
            System.out.print("{");
            while(curr != null){
                System.out.print(curr.data+" ");
                curr = curr.next;
            }
            System.out.print("}");
        }
        System.out.println("");
    }
    
    //metodo para consultar todos los datos
    public Object[] consultData(){
        Object[] array = new Object[this.size];
        Node node = this.head;
        for(int i=0;i<size;i++){
            array[i] = (T) node.data;
            node = node.next;
        }
        return array;
    }
   
}
//clase stack que se extiende de la clase linkedlist, adicionalmente, tiene un metodo para agregar un entero con una informacion adicional de carry
@SuppressWarnings("unchecked")
class Stack<T> extends LinkedList<T>{
    int size;
    int top;
    
    public Stack(){
        this(3);
    }
    
    public Stack(int size){
        super();
        this.size = size;
        top = 0;
    }
    
    //retorna booleano que indica si la pila esta vacia
    public boolean isEmpty(){
        return this.tail == null;
    }
    
    //retorna booleano que indica si la pila esta llena
    public boolean isFull(){
        return this.top >= this.size;
    }
    
    //retorna la llave del ultimo elemento de la pila o del elemento top
    public T top() throws Exception{
        if(this.isEmpty()){
            throw new Exception("La pila esta vacia, no hay top");
        }
        return (T) topBack();
    }
    
    //agrega nodo a la pila con la llave "data"
    public void push(T data) throws Exception{
        if(this.isFull()){
            throw new Exception("La pila esta llena");
        }
        super.pushBackData(data);
        this.top += 1;
    }
    
    public T find(T data) throws Exception{
        Stack<T> tempStack = new Stack<>(this.size);
        while(!this.isEmpty()){
            T aux = this.pop();
            if(aux.equals(data)){
                while(!tempStack.isEmpty()){
                    this.push(tempStack.pop());
                }
                return aux;
            }
            tempStack.push(aux);
        }
        while(!tempStack.isEmpty()){
            this.push(tempStack.pop());
        }
        return null;
    }
    
    public void delete(T data) throws Exception{
        Stack<T> tempStack = new Stack<>(this.size);
        while(!this.isEmpty()){
            T aux = this.pop();
            if(aux.equals(data)){
                this.pop();
                break;
            }
            tempStack.push(aux);
        }
        while(!tempStack.isEmpty()){
            this.push(tempStack.pop());
        }
    }
    
    
    
    //elimino el elemento top de la pila y retorna su llave
    public T pop() throws Exception{
        T output = super.topBack();
        super.popBack();
        this.top -= 1;
        return output;
    }
    
    //metodo para consultar todos los datos
    @Override
    public Object[] consultData(){
        Object[] array = new Object[this.top+1];
        Node node = this.head;
        for(int i=top;i>0;i--){
            array[i] = node.data;
            node = node.next;
        }
        return array;
    }
}

//clase generica para implementar la cola
@SuppressWarnings("unchecked")
class Queue<T> extends LinkedList<T>{
    int size;
    int top;
    
    public Queue(){
        this(3);
    }
    
    public Queue(int size){
        super();
        this.size = size;
    }
    
    //retorna booleano que indica si la cola esta llena
    public boolean isFull(){
        return this.top >= this.size;
    }
    
    public boolean isEmpty(){
        return this.top == 0;
    }
    
    public void enqueue(T data) throws Exception{
        if(isFull()){
            throw new Exception("La cola esta llena");
        }
        super.pushBackData(data);
        this.top += 1;
    }
    
    //desencola el elemento de la lista
    public void dequeue() throws Exception{
        if(this.head == null){
            throw new Exception("La pila esta vacia");
        }
        super.popFront();
        this.top -= 1;
    }
    
    public T search(T data) throws Exception{
        Queue<T> tempQueue = new Queue<>(this.size);
        T output = null;
        while(!this.isEmpty()){
            tempQueue.enqueue((T) this.head.data);
            this.dequeue();
        }
        while(!tempQueue.isEmpty()){
            this.enqueue((T) tempQueue.head.data);
            if(tempQueue.head.data.equals(data)){
                output = (T) tempQueue.head.data;
            }       
            tempQueue.dequeue();
        }
        return output;
    }
    
    public void delete(T data) throws Exception{
        Queue<T> tempQueue = new Queue<>(this.size);
        while(!this.isEmpty()){
            tempQueue.enqueue((T) this.head.data);
            this.dequeue();
        }
        while(!tempQueue.isEmpty()){
            if(!tempQueue.head.data.equals(data)){
                this.enqueue((T) tempQueue.head.data);
            }
            tempQueue.dequeue();
        }
    }
    
    @Override
    public Object[] consultData(){
        Object[] array = new Object[this.top];
        Node node = this.head;
        for(int i=0;i<top;i++){
            array[i] = (T) node.data;
            node = node.next;
        }
        return array;
    }
    
}

class ArrayListNormal{
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

class ArrayListOrdered extends ArrayListNormal {

    public ArrayListOrdered(){
        Array = new int[16];
        numItem = 0;
    }
    
    @Override
    public void addItem(int number){
        if(numItem == Array.length){
            super.growArray();
        }
        int insertIndex = findInsertIndex(number);
        shiftRight(insertIndex);
        Array[insertIndex] = number;
        numItem++;

    }
    
    public int search(int number){
        int izquierda = 0;
        int derecha = this.numItem - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            if (Array[medio] == number) {
                return medio; // Elemento encontrado, retorna su índice
            } else if (Array[medio] < number) {
                izquierda = medio + 1; // El elemento está en la mitad derecha
            } else {
                derecha = medio - 1; // El elemento está en la mitad izquierda
            }
        }

        return -1; // Elemento no encontrado
    }
    
    @Override
    public void removeItem(int number){
        Integer index;
        if(numItem == 0){
            throw new RuntimeException("Is empty!!");
        } 
        else
        {
            index = search(number);
            shiftLeft(index);
            numItem--;
        }
        
    }
    
    private void shiftLeft(int index) {
        // Desplaza los elementos a la derecha para abrir espacio para el nuevo elemento
        for (int i = index; i < numItem; i++) {
            Array[i] = Array[i + 1];
        }
    }

    
    
    private void shiftRight(int index) {
        // Desplaza los elementos a la derecha para abrir espacio para el nuevo elemento
        for (int i = numItem; i > index; i--) {
            Array[i] = Array[i - 1];
        }
    }

    private int findInsertIndex(int number) {
        int left = 0;
        int right = numItem - 1;
        int insertIndex = numItem; // Establece un valor predeterminado en caso de que el número sea mayor que todos los elementos en el arreglo

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (Array[mid] == number) {
                // Si el número ya está en el arreglo, puedes elegir insertarlo en la posición existente o ignorarlo (dependiendo de tus necesidades).
                // En este ejemplo, se elige insertarlo en la posición existente.
                return mid;
            } else if (Array[mid] < number) {
                left = mid + 1;
            } else {
                insertIndex = mid; // Actualiza el índice de inserción
                right = mid - 1;
            }
        }

        return insertIndex;
    }   
}

public class Prototipo {

    //clase TEST
    public static void main(String[] args) throws Exception {
        int n = 100000000;
        System.out.println("Cantidad de datos: "+n);
        linkedListTime(n);     
        stackTime(n);
        queueTime(n);
        arrayListTime(n);
        arrayListOrderedTime(n);
    }
    
    public static void linkedListTime(Integer elementAmount) throws Exception{
        LinkedList<Integer> dinamo = new LinkedList<>();
        
        System.out.println("TIEMPO LINKEDLIST");
        
        // insertar
        long start = System.nanoTime();

        for (int i = 0; i < elementAmount; i++){
            dinamo.pushBackData(i);
        }

        double duration = (System.nanoTime() - start)/ 1e9;
        String resultado = String.format("%.6f", duration);
        System.out.println("Insert duration: " + resultado + "s");
        
        //buscar
        start = System.nanoTime();
        if(dinamo.findKey(elementAmount-2)!=null){      
            duration = (System.nanoTime() - start)/ 1e9;
            resultado = String.format("%.6f", duration);
            System.out.println("Search duration: " + resultado + "s");
        }
        
        
         //eliminar
         start = System.nanoTime();

         dinamo.deleteItem(elementAmount-1);
        
         duration = (System.nanoTime() - start)/ 1e9;
         resultado = String.format("%.6f", duration);
         System.out.println("Remove duration: " + resultado + "s");
         
         
        //consultar todos los datos
        start = System.nanoTime();
        Object[] test;
        test = dinamo.consultData();
        
        duration = (System.nanoTime() - start)/ 1e9;
        resultado = String.format("%.6f", duration);
        System.out.println("Consult all data duration: " + resultado + "s");
    }
    
    public static void arrayListTime(Integer elementAmount){
        ArrayListNormal dinamo = new ArrayListNormal();
        
        System.out.println("TIEMPO ARRAYLIST DINAMICO");
        
        // insertar
        long start = System.nanoTime();

        for (int i = 0; i < elementAmount; i++){
            dinamo.addItem(i);
        }

        double duration = (System.nanoTime() - start)/ 1e9;
        String resultado = String.format("%.6f", duration);
        System.out.println("Insert duration: " + resultado + "s");

        //buscar
        start = System.nanoTime();

        if(dinamo.searchFor(elementAmount-1)){
            duration = (System.nanoTime() - start)/ 1e9;
            resultado = String.format("%.6f", duration);
            System.out.println("Search duration: " + resultado + "s");
        }
        
        //eliminar
        start = System.nanoTime();
        dinamo.removeItem(elementAmount-1);
        
        duration = (System.nanoTime() - start)/ 1e9;
        resultado = String.format("%.6f", duration);
        System.out.println("Remove duration: " + resultado + "s");
        
        //consultar todos los datos
        start = System.nanoTime();
        int[] test;
        test = dinamo.consultData();
        
        duration = (System.nanoTime() - start)/ 1e9;
        resultado = String.format("%.6f", duration);
        System.out.println("Consult all data duration: " + resultado + "s");
    }
    
    public static void arrayListOrderedTime(Integer elementAmount){
        ArrayListOrdered dinamo = new ArrayListOrdered();
        
        System.out.println("TIEMPO ARRAYLIST DINAMICO ORDENADO");
        
        // insertar
        long start = System.nanoTime();

        for (int i = 0; i < elementAmount; i++){
            dinamo.addItem(i);
        }

        double duration = (System.nanoTime() - start)/ 1e9;
        String resultado = String.format("%.6f", duration);
        System.out.println("Insert duration: " + resultado + "s");

        //buscar
        start = System.nanoTime();

        if(dinamo.search(elementAmount-1) != -1){
            duration = (System.nanoTime() - start)/ 1e9;
            resultado = String.format("%.6f", duration);
            System.out.println("Search duration: " + resultado + "s");
        }
        
        //eliminar
        start = System.nanoTime();
        dinamo.removeItem(elementAmount-1);
        
        duration = (System.nanoTime() - start)/ 1e9;
        resultado = String.format("%.6f", duration);
        System.out.println("Remove duration: " + resultado + "s");
        
        //consultar todos los datos
        start = System.nanoTime();
        int[] test;
        test = dinamo.consultData();
        
        duration = (System.nanoTime() - start)/ 1e9;
        resultado = String.format("%.6f", duration);
        System.out.println("Consult all data duration: " + resultado + "s");
    }

    public static void stackTime(Integer elementAmount) throws Exception{
        Stack<Integer> dinamo = new Stack<>(elementAmount);
        
        System.out.println("TIEMPO STACK");
        
        // insertar
        long start = System.nanoTime();

        for (int i = 0; i < elementAmount; i++){
            dinamo.push(i);
        }

        double duration = (System.nanoTime() - start)/ 1e9;
        String resultado = String.format("%.6f", duration);
        System.out.println("Insert duration: " + resultado + "s");
        

        //buscar
        start = System.nanoTime();

        if(dinamo.find(0) != null){
            duration = (System.nanoTime() - start)/ 1e9;
            resultado = String.format("%.6f", duration);
            System.out.println("Search duration: " + resultado + "s");
        }
        
        //eliminar
        start = System.nanoTime();

        dinamo.delete(0);
        
        duration = (System.nanoTime() - start)/ 1e9;
        resultado = String.format("%.6f", duration);
        System.out.println("Remove duration: " + resultado + "s");
        
        //consultar todos los datos
        start = System.nanoTime();
        Object[] test;
        test = dinamo.consultData();
        
        duration = (System.nanoTime() - start)/ 1e9;
        resultado = String.format("%.6f", duration);
        System.out.println("Consult all data duration: " + resultado + "s");
    }
    
    public static void queueTime(Integer elementAmount) throws Exception{
        Queue<Integer> dinamo = new Queue<>(elementAmount);
        
        System.out.println("TIEMPO QUEUE");
        
        // insertar
        long start = System.nanoTime();

        for (int i = 0; i < elementAmount; i++){
            dinamo.enqueue(i);
        }

        double duration = (System.nanoTime() - start)/ 1e9;
        String resultado = String.format("%.6f", duration);
        System.out.println("Insert duration: " + resultado + "s");
        

        //buscar
        start = System.nanoTime();

        if(dinamo.search(0) != null){
            duration = (System.nanoTime() - start)/ 1e9;
            resultado = String.format("%.6f", duration);
            System.out.println("Search duration: " + resultado + "s");
        }
        
        //eliminar
        start = System.nanoTime();

        dinamo.delete(0);
        
        duration = (System.nanoTime() - start)/ 1e9;
        resultado = String.format("%.6f", duration);
        System.out.println("Remove duration: " + resultado + "s");
        
        //consultar todos los datos
        start = System.nanoTime();
        Object[] test;
        test = dinamo.consultData();
        
        duration = (System.nanoTime() - start)/ 1e9;
        resultado = String.format("%.6f", duration);
        System.out.println("Consult all data duration: " + resultado + "s");
    }
}
