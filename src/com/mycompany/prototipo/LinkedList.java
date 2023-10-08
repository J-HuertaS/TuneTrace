/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prototipo;

/**
 *
 * @author HUERTAS
 * @param <T>
 */
@SuppressWarnings("unchecked")
//clase general linkedlist, donde se implementan algunos metodos claves de una lista doblemente enlazada
public class LinkedList<T>{
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
