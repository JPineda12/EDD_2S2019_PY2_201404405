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

    public NodoLista(Object data) {
        this.next = null;
        this.data = data;
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
    
    
}
