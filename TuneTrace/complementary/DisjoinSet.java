/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tunetrace;

/**
 *
 * @author judar
 */
public class DisjoinSet {
    private int[] padres;
    private int[] rank;

    public DisjoinSet(int size) {
        padres = new int[size];
        rank = new int[size];
    }

    public int[] getPadres() {
        return padres;
    }

    public void setPadres(int[] padres) {
        this.padres = padres;
    }

    public int[] getRank() {
        return rank;
    }

    public void setRank(int[] rank) {
        this.rank = rank;
    }
    
    public void makeSet(int a){
        padres[a]=a;
        rank[a]=0;
    }
    
    public int Find(int a){
        if(padres[a]!=a){
            padres[a]=Find(padres[a]);
        }
        return padres[a];
    }
    
    public void Union(int i, int j){
        int i_id=Find(i);
        int j_id = Find(j);
        if(i_id==j_id){
            return;
        }
        if(rank[i_id]>rank[j_id]){
            padres[j_id]=i_id;
        }
        else {
            padres[i_id]=j_id;
            if(rank[i_id]==rank[j_id]){
                rank[j_id]++;
            }
        }
    }
}
