/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tunetrace;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class TuneTrace {

    public static void main(String[] args) throws Exception {
        System.out.println("MinHeap:");
        MinHeap(10000000);
        System.out.println("DisjoinSet:");
        DisjoinSet(10000000);
    }
    
    public static void MinHeap(Integer amount) throws Exception{
        LinkedList<MinHeapGenerico<cancion>> generos = new LinkedList<>();
        String csvFilePath = "";
        if(amount==10000){
            csvFilePath = "C:\\Users\\judar\\Downloads\\diezMilSong.csv";
        }
        else if(amount==10000000){
            csvFilePath = "C:\\Users\\judar\\Downloads\\diezMillonesSong.csv";
        }
        

        // INSERCIÓN Y CATEGORIZACIÓN DE ELEMENTOS
        long start = System.nanoTime();
        Node<MinHeapGenerico<cancion>> p;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            // Omitir la primera línea que contiene los encabezados
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] record = line.split(",");
                int id = Integer.parseInt(record[0]);
                String nombre = record[1];
                int numero = Integer.parseInt(record[2]);
                String autor = record[3];
                String genero = record[4];

                cancion cancion = new cancion(id, nombre, numero, autor, genero);

                p = generos.findIdentificador(genero);
                if (p == null) {
                    MinHeapGenerico<cancion> heap = new MinHeapGenerico<>();
                    heap.Insert(id, cancion);
                    generos.pushBack(heap, genero);
                } 
                else {
                    MinHeapGenerico<cancion> heap = p.getData();
                    generos.erase(p);
                    heap.Insert(cancion.getId(), cancion);
                    p.setData(heap);
                    generos.pushBack(heap, genero);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            
        double duration = (System.nanoTime() - start)/ 1e9;
        String resultado = String.format("%.6f", duration);
        System.out.println("Insert duration: " + resultado + "s");
        
        //CONSULTA DADO UN GÉNERO
        start = System.nanoTime();
        String parametro = "Salsa";
        Node<MinHeapGenerico<cancion>> c = generos.findIdentificador(parametro);
        LinkedList<cancion> resultados = new LinkedList<>();

        if (c != null) {
            MinHeapGenerico<cancion> heap = c.getData();
            if (heap != null) { // Verificar que el heap no sea nulo
                while (!heap.isEmpty()) {
                    cancion c1 = heap.ExtractMin();
                    resultados.pushBack(c1, "empty");
                }
            } else {
                System.out.println("No hay canciones para el género: " + parametro);
            }
        } else {
            System.out.println("El género no está presente en la lista: " + parametro);
        }
        
        duration = (System.nanoTime() - start) / 1e9;
        resultado = String.format("%.6f", duration);
        System.out.println("Consult duration: " + resultado + "s");
    }
    
     public static void DisjoinSet(Integer amount) throws Exception {
        String csvFilePath="";
        if(amount==10000){
            csvFilePath = "C:\\Users\\judar\\Downloads\\diezMilSong.csv";
        }
        else if(amount==10000000){
            csvFilePath = "C:\\Users\\judar\\Downloads\\diezMillonesSong.csv";
        }
         
        LinkedList<cancion> canciones = new LinkedList<>();
        // INSERCIÓN Y CREACIÓN DE CONJUNTOS
        long start = System.nanoTime();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            // Omitir la primera línea que contiene los encabezados
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] record = line.split(",");
                int id = Integer.parseInt(record[0]);
                String nombre = record[1];
                int numero = Integer.parseInt(record[2]);
                String autor = record[3];
                String genero = record[4];

                cancion cancion1 = new cancion(id, nombre, numero, autor, genero);
                canciones.pushBack(cancion1, "empty");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Node<cancion> rec = canciones.topFront();
        cancion[] canciones2 = new cancion[canciones.getSize()];
        DisjoinSet set=new DisjoinSet(canciones.getSize());
        for(int i=0; i<canciones.getSize(); i++){
            set.makeSet(i);
            canciones2[i]=rec.getData();
            if(i>0){
                for(int j=i-1; j>=0; j--){
                    if(canciones2[i].getGenero().equals(canciones2[j].getGenero())){
                        set.Union(i, j);
                        break;
                    }
                }
            }
            rec=rec.getNext();
        }
        
        double duration = (System.nanoTime() - start)/ 1e9;
        String resultado = String.format("%.6f", duration);
        System.out.println("Insert duration: " + resultado + "s");
        
        //CONSULTA DE CONJUNTOS
        start = System.nanoTime();
        String parametro = "Salsa";
        int reference=-1;
        LinkedList<cancion> resultados = new LinkedList<>();
        for(int i=0; i<canciones.getSize(); i++){
            if (reference == -1) {
                if (canciones2[i].getGenero().equals(parametro)) {
                    reference = i;
                    resultados.pushBack(canciones2[i], "empty");
                }
            }
            else{
                if(set.Find(reference)==set.Find(i)){
                    resultados.pushBack(canciones2[i], "empty");
                }
            }
        }
        duration = (System.nanoTime() - start)/ 1e9;
        resultado = String.format("%.6f", duration);
        System.out.println("Consult duration: " + resultado + "s");
     }
}
