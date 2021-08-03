/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafosPesados;

/**
 *
 * @author user
 */
public class EstructuraA implements Comparable<EstructuraA>{
    int verticeOrigen;
    int verticeDestino;
    double Peso;

    public EstructuraA(int verticeOrigen, int verticeDestino, double Peso) {
        this.verticeOrigen = verticeOrigen;
        this.verticeDestino = verticeDestino;
        this.Peso = Peso;
    }

    public int getVerticeOrigen() {
        return verticeOrigen;
    }

    public int getVerticeDestino() {
        return verticeDestino;
    }

    public double getPeso() {
        return Peso;
    }

    public void setVerticeOrigen(int verticeOrigen) {
        this.verticeOrigen = verticeOrigen;
    }

    public void setVerticeDestino(int verticeDestino) {
        this.verticeDestino = verticeDestino;
    }

    public void setPeso(double Peso) {
        this.Peso = Peso;
    }

    @Override
    public int compareTo(EstructuraA o) {
        if (this.Peso < o.Peso) {
            return -1;
        }
        if(this.Peso > o.Peso){
            return 1;
        }
        return 0;
    }
     
    
}
