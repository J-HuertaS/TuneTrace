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
//clase generica para implementar la cola
@SuppressWarnings("unchecked")
public class Queue<T> extends LinkedList<T>{
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
        LinkedList.Node node = this.head;
        for(int i=0;i<top;i++){
            array[i] = (T) node.data;
            node = node.next;
        }
        return array;
    }
    
}
