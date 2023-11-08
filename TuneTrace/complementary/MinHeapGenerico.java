/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tunetrace;

/**
 *
 * @author judar
 */
public class MinHeapGenerico<T> {
    private int heap[];
    private int size=0;
    private int maxSize;
    private T objetos[];
    
    public MinHeapGenerico() {
        heap = new int[1];
        objetos = (T[]) new Object[1];
        maxSize=1;
    }
    
    public MinHeapGenerico(int a) {
        heap = new int[a];
        objetos = (T[]) new Object[a];
        maxSize=a;
    }

    public int[] getHeap() {
        return heap;
    }

    public void setHeap(int[] heap) {
        this.heap = heap;
    }

    
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
    
    public int Parent(int i){
        int a = (int) Math.floor(i / 2.0);
        return a;
    }
    
    public int LeftChild(int i){
        return 2*i;
    }
    
    public int RightChild(int i){
        return 2*i+1;
    }
    
    public void Swap(int a, int b){
        int num1=heap[a-1];
        int num2=heap[b-1];
        heap[a-1]=num2;
        heap[b-1]=num1;
        T obj1=objetos[a-1];
        T obj2=objetos[b-1];
        objetos[a-1]=obj2;
        objetos[b-1]=obj1;
    }
    
    public void SiftUp(int i){
        while (i>1 && heap[Parent(i)-1]>heap[i-1]){
            Swap(Parent(i), i);
            i=Parent(i);
        }
    }
    
    public void SiftDown(int i){
        int minIndex=i;
        int l=LeftChild(i);
        if(l<=size && heap[l-1]<heap[minIndex-1]){
            minIndex=l;
        }
        int r=RightChild(i);
        if(r<=size && heap[r-1]<heap[minIndex-1]){
            minIndex=r;
        }
        if(i!=minIndex){
            Swap(i,minIndex);
            SiftDown(minIndex);
        }
    }
    
    public void Insert(int p, T obj){
        if (obj != null) {
            if(size==maxSize){
                ensureCapacity();
            }
            size=size+1;
            heap[size-1]=p;
            objetos[size-1]=obj;
            SiftUp(size);
        }
    }
    
    public T ExtractMin(){
        T result=objetos[0];
        heap[0]=heap[size-1];
        objetos[0]=objetos[size-1];
        size=size-1;
        SiftDown(1);
        return result;
    }
    
    public int GetMinRef(){
        return heap[0];
    }
    
    public T GetMinObj(){
        return objetos[0];
    }
    
    public void Remove(int i){
        heap[i-1]=-1;
        SiftUp(i);
        ExtractMin();
    }
    
    public void ChangePriority(int i, int p){
        int oldp=heap[i-1];
        heap[i-1]=p;
        if(p<oldp){
            SiftUp(i);
        }
        else{
            SiftDown(i);
        }
    }
    
    
    public void ensureCapacity() {
        if (size == maxSize) {
            int newMaxSize = maxSize * 2;
            int newHeap[] = new int[newMaxSize];
            T newObjects[] = (T[]) new Object[newMaxSize];
            for(int i=0; i<size; i++){
                newHeap[i]=heap[i];
                newObjects[i]=objetos[i];
            }
            heap = newHeap;
            objetos = newObjects;
            maxSize = newMaxSize;
        }
    }
    
    public boolean isEmpty(){
        return size==0;
    }
}

