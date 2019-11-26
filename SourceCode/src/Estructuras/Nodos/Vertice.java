/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.Nodos;

import Estructuras.ListaEnlazada;

/**
 *
 * @author brest12
 */
public class Vertice {
    
    private Object dato;
    private int x;
    private int y;
    private String nombreNodo;
    public Vertice down, up, right,left;


    public Vertice(String nombreNodo, Object dato, int x, int y){
        this.dato = dato;
        this.x = x;
        this.y = y;
        this.nombreNodo = nombreNodo;
    }

    public String getNombreNodo() {
        return nombreNodo;
    }
    
    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }
    
        public Vertice getDown() {
        return down;
    }

    public void setDown(Vertice down) {
        this.down = down;
    }

    public Vertice getUp() {
        return up;
    }

    public void setUp(Vertice up) {
        this.up = up;
    }

    public Vertice getRight() {
        return right;
    }

    public void setRight(Vertice right) {
        this.right = right;
    }

    public Vertice getLeft() {
        return left;
    }

    public void setLeft(Vertice left) {
        this.left = left;
    }
    
    public boolean equals(Vertice n){
        return dato.equals(dato);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
