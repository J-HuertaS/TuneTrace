package com;
public class cancion {
    private int id;
    private String nombre;
    private int numero;
    private String autor;
    private String genero;

    public cancion(int id, String nombre, int numero, String autor, String genero){
        this.id = id;
        this.nombre = nombre;
        this.numero = numero;
        this.autor = autor;
        this.genero = genero;
    }

    public Integer getId() {
        return Integer.valueOf(id);
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
}
