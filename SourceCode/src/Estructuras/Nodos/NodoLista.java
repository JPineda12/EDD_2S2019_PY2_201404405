/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.Nodos;

/**
 *
 * @author brest12
 */
public class NodoLista {
    
    private NodoLista next;
    private Object data;
    private int n;

    public NodoLista(Object data, int n) {
        this.next = null;
        this.data = data;
        this.n = n;
    }

    public NodoLista getNext() {
        return next;
    }
    
    

    public void setNext(NodoLista next) {
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
    
    
}
