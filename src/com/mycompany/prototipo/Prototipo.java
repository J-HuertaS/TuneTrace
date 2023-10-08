/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prototipo;

/**
 *
 * @author HUERTAS
 */

public class Prototipo {

    //clase TEST
    public static void main(String[] args) throws Exception {
        int n = 10000;
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
