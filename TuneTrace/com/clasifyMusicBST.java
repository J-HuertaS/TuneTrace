package com;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import DataStructures.LinkedList;
import DataStructures.BinarySearchTree;

public class clasifyMusicBST {
    final String[] genders = {
        "Rock", "Pop", "Jazz", "Electrónica", "Clásica", "Reggae", "Hip-Hop", "R&B", "Country", "Salsa","Irbano","K-Pop","Anime"
    };

    public static void main(String[] args) {
        LinkedList<BinarySearchTree> generos = new LinkedList<>();

        double start = 0,duration = 0;
        String resultado;

        String csvFilePath = "Data/mockupData/veinteMillonesSong.csv"; // Ruta al archivo CSV


        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean headerSkipped = false; // Para omitir la primera línea que contiene los encabezados

            start = System.nanoTime();

            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue; // Omitir la primera línea
                }

                String[] record = line.split(",");
                int id = Integer.parseInt(record[0]);
                String nombre = record[1];
                int numero = Integer.parseInt(record[2]);
                String autor = record[3];
                String genero = record[4];

                cancion cancion = new cancion(id, nombre, numero, autor, genero);

                if(generos.head == null){
                    generos.pushBackData(new BinarySearchTree());
                    generos.head.data.insert(cancion);
                } else {
                    LinkedList.Node<BinarySearchTree> node = encontrarAutor(generos, cancion.getAutor());
                    if(node == null){
                        generos.pushBackData(new BinarySearchTree());
                        generos.tail.data.insert(cancion); 
                    } else {
                        node.data.insert(cancion);
                    }
                } 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        duration = (System.nanoTime() - start)/ 1e9;
        resultado = String.format("%.6f", duration);
        System.out.println("Insercion y clasificacion por genero: " + resultado + "s");
        
        try {
            start = System.nanoTime();

            Object[] consulta = consultarCancionesAut(generos, "Autor50");

            duration = (System.nanoTime() - start)/ 1e9;
            resultado = String.format("%.6f", duration);
            System.out.println("Consulta de datos: " + resultado + "s");
            imprimirVerificacion(generos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void imprimirVerificacion(LinkedList<BinarySearchTree> generos) throws Exception {
        System.out.println("BST");
        //imprimir el arbol por niveles
        //generos.head.data.levelTraversal(generos.head.data.getRoot());
        System.out.println(generos.head.data.getSize(generos.head.data.getRoot())); //saber cuantas canciones de genero rock hay
        System.out.println(generos.size); //verificar que se hayan generado los 13 arboles de generos
}

    public static LinkedList.Node<BinarySearchTree> encontrarGenero(LinkedList<BinarySearchTree> generos, String genero){
        LinkedList.Node<BinarySearchTree> node = generos.head;
        while(node != null){
            if(node.data.getRoot().element.getGenero().equals(genero)){
                break;
            }
            node = node.next;                     
        }
        return node;
    }

    public static LinkedList.Node<BinarySearchTree> encontrarAutor(LinkedList<BinarySearchTree> generos, String autor){
        LinkedList.Node<BinarySearchTree> node = generos.head;
        while(node != null){
            if(node.data.getRoot().element.getAutor().equals(autor)){
                break;
            }
            node = node.next;                     
        }
        return node;
    }

    public static Object[] consultarCancionesGen(LinkedList<BinarySearchTree> generos, String genero){
        LinkedList.Node<BinarySearchTree> node = generos.head;
        while(node != null){
            if(node.data.getRoot().element.getGenero().equals(genero)){
                break;
            }
            node = node.next;                     
        }
        if(node == null){
            return null;
        }

        int size = node.data.getSize(node.data.getRoot());
       
        if(size > 0){
            Object[] consulta = new Object[size];
            BinarySearchTree.BinaryNode nodeTree = node.data.getRoot();
            for(int i = 0; i < size; i++){
                consulta[i] = nodeTree;
                nodeTree = node.data.nextNode(nodeTree);
                
            }
            return consulta;
        }
        return null;
    }

    public static Object[] consultarCancionesAut(LinkedList<BinarySearchTree> generos, String genero){
        LinkedList.Node<BinarySearchTree> node = generos.head;
        while(node != null){
            if(node.data.getRoot().element.getAutor().equals(genero)){
                break;
            }
            node = node.next;                     
        }
        if(node == null){
            return null;
        }

        int size = node.data.getSize(node.data.getRoot());
       
        if(size > 0){
            Object[] consulta = new Object[size];
            BinarySearchTree.BinaryNode nodeTree = node.data.getRoot();
            for(int i = 0; i < size; i++){
                consulta[i] = nodeTree;
                nodeTree = node.data.nextNode(nodeTree);
                
            }
            return consulta;
        }
        return null;
    }
}
