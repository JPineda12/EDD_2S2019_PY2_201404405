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
public class NodoPila {
    
    private Object dato;
    private NodoPila next;
    
    public NodoPila(Object dato){
        this.dato = dato;
        this.next = null;
    }

    public Object getDato() {
        return dato;
    }

    public NodoPila getNext() {
        return next;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public void setNext(NodoPila next) {
        this.next = next;
    }
    
}
