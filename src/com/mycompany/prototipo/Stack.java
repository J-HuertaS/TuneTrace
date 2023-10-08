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
//clase stack que se extiende de la clase linkedlist, adicionalmente, tiene un metodo para agregar un entero con una informacion adicional de carry
public class Stack<T> extends LinkedList<T>{
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
        LinkedList.Node node = this.head;
        for(int i=top;i>0;i--){
            array[i] = node.data;
            node = node.next;
        }
        return array;
    }
}
