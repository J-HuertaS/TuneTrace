/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tunetrace;

/**
 *
 * @author judar
 */
public class ArrayListGeneric<T> {
    public T[] Array;
    public int numItem;

    public ArrayListGeneric() {
        Array = (T[]) new Object[16];
        numItem = 0;
    }

    public int lengthArray() {
        return Array.length;
    }

    public void addItem(T item) {
        if (numItem == Array.length) {
            growArray();
        }
        Array[numItem] = item;
        numItem++;
    }

    public void removeLast() {
        if (numItem == 0) {
            throw new RuntimeException("Is empty!!");
        } else {
            numItem--;
        }
    }

    public void removeItem(T element) {
        Integer index;
        if (numItem == 0) {
            throw new RuntimeException("Is empty!!");
        } else {
            index = getIndexOf(element);
            shiftLeft(index);
            numItem--;
        }
    }

    private void shiftLeft(int index) {
        for (int i = index; i < numItem; i++) {
            Array[i] = Array[i + 1];
        }
    }

    public T getItem(int index) {
        return Array[index];
    }

    public boolean searchFor(T element) {
        for (int i = 0; i < numItem; i++) {
            if (Array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public int getIndexOf(T element) {
        for (int i = 0; i < numItem; i++) {
            if (Array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public void growArray() {
        T[] tempArray = (T[]) new Object[Array.length * 2];
        System.arraycopy(Array, 0, tempArray, 0, numItem);
        Array = tempArray;
    }

    public void printArray() {
        System.out.print("[");
        for (int i = 0; i < numItem; i++) {
            System.out.print(Array[i] + " ");
        }
        System.out.print("]");
    }

    public T[] consultData() {
        T[] array = (T[]) new Object[numItem];
        System.arraycopy(Array, 0, array, 0, numItem);
        return array;
    }
}
