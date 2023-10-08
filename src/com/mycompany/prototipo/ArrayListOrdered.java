/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prototipo;

/**
 *
 * @author HUERTAS
 */
public class ArrayListOrdered extends ArrayListNormal {

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
