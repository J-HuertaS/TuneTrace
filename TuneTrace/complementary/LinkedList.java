/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tunetrace;

/**
 *
 * @author judar
 */
public class LinkedList<T> {
    private Node<T> cabeza;
    private Node<T> cola;
    int size;

    public LinkedList() {
        cabeza = null;
        cola = null;
        size=0;
    }

    public void pushFront(T data, String identificador) {
        Node<T> node;
        node = new Node<T>(data);
        node.setIdentificador(identificador);
        if (empty()) {
            cabeza = node;
            cola = cabeza;
        } else {
            cabeza.setPrev(node);
            node.setNext(cabeza);
            cabeza = node;
        }
        size++;
    }

    public void popFront() {
        if (empty()) {
            throw new RuntimeException("La lista está vacía: item no eliminado");
        }
        cabeza = cabeza.getNext();
        cabeza.getPrev().setNext(null);
        cabeza.setPrev(null);
        if (cabeza == null) {
            cola = null;
        }
        size--;
    }

    public void pushBack(T data, String identificador) {
        Node<T> node;
        node = new Node<T>(data);
        node.setIdentificador(identificador);
        if (empty()) {
            cabeza = node;
            cola = cabeza;
        } else {
            cola.setNext(node);
            node.setPrev(cola);
            node.setNext(null);
            cola = node;
        }
        size++;
    }

    public void popBack() {
        if (empty()) {
            throw new RuntimeException("La lista está vacía: item no eliminado");
        }
        cola = cola.getPrev();
        cola.getNext().setPrev(null);
        cola.setNext(null);
        if (cola == null) {
            cabeza = null;
        }
        size--;
    }

    public void addAfter(Node<T> node, T data) {
        Node<T> node2;
        node2 = new Node<T>(data);
        node2.setNext(node.getNext());
        node2.setPrev(node);
        node.setNext(node2);
        if (node2.getNext() != null) {
            node2.getNext().setPrev(node2);
        }
        if (cola == node) {
            cola = node2;
        }
        size++;
    }

    public void addBefore(Node<T> node, T data) {
        Node<T> node2;
        node2 = new Node<T>(data);
        node2.setNext(node);
        node2.setPrev(node.getPrev());
        node.setPrev(node2);
        if (node2.getPrev() != null) {
            node2.getPrev().setNext(node2);
        }
        if (cabeza == node) {
            cabeza = node2;
        }
        size++;
    }

    public Node<T> topFront() {
        return cabeza;
    }

    public Node<T> topBack() {
        return cola;
    }

    public Node<T> findData(T data) {
        Node<T> p = cabeza;
        while (p != null && !p.getData().equals(data)) {
            p = p.getNext();
        }
        return p;
    }
    
    public Node<T> findIdentificador(String identificador) {
        Node<T> p = cabeza;
        while (p != null) {
            if (p.getIdentificador() != null && p.getIdentificador().equals(identificador)) {
                break;
            }
            p = p.getNext();
        }
        return p;
    }

    public void erase(Node<T> node) {
        if (node == null) {
            return; // No se puede eliminar un nodo nulo
        }

        if (cabeza == node) {
            cabeza = node.getNext();
        }

        if (cola == node) {
            cola = node.getPrev();
        }

        if (node.getNext() != null) {
            node.getNext().setPrev(node.getPrev());
        }

        if (node.getPrev() != null) {
            node.getPrev().setNext(node.getNext());
        }

        node.setNext(null);
        node.setPrev(null);
        size--;
    }

    public boolean empty() {
        return (cabeza == null && cola == null);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    
}
