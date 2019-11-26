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
public class AVLNode {
    private AVLNode left;
    private AVLNode right;
    private Object hoja;
    private int height;
    private int bf;
     
    public AVLNode(Object hoja){
        this.hoja = hoja;
        this.left = null;
        this.right = null;
        this.height = 0;
        this.bf = 0;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    public Object getHoja() {
        return hoja;
    }

    public void setHoja(Object hoja) {
        this.hoja = hoja;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBf() {
        return bf;
    }

    public void setBf(int bf) {
        this.bf = bf;
    }
    
    
}
