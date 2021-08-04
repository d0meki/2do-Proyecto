/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafosPesados;


import java.util.List;

/**
 *
 * @author user
 */
public class Prim {
    public static final double INF = 999999.00;
    private GrafoPesado grafoAux;
    private GrafoPesado grafoOriginal;
    private UtilsRecorrido controlMarcado;

    public Prim(GrafoPesado grafoOriginal){
        this.grafoOriginal = grafoOriginal;
        this.grafoAux = new GrafoPesado(this.grafoOriginal.cantidadDeVertices());
        this.controlMarcado = new UtilsRecorrido(this.grafoAux.cantidadDeVertices()); 
        this.insertarVertices();
    }
    public void insertarVertices(){  
        int verticeOrigen = 0;
        int verticeDestino = 0;
        double menorPeso = INF;
        this.controlMarcado.marcarVertice(0);
        do{
            for (int i = 0; i < this.grafoAux.cantidadDeVertices(); i++) {
                if (this.controlMarcado.estaMarcado(i)) {
                    List<AdyacenteConPeso> adyacenteVerticeEnTurno = this.grafoOriginal.listaDeAdyacencia.get(i);
                    for (AdyacenteConPeso ady: adyacenteVerticeEnTurno) {
                        if (ady.getPeso() < menorPeso) {
                            if (!(this.controlMarcado.estaMarcado(ady.getIndiceVertice()))) {
                                menorPeso = ady.getPeso();
                                verticeOrigen = i;
                                verticeDestino = ady.getIndiceVertice();
                            }
                        }
                    }
                }
            }
            this.grafoAux.insertarArista(verticeOrigen, verticeDestino, menorPeso);
            this.controlMarcado.marcarVertice(verticeOrigen);
            this.controlMarcado.marcarVertice(verticeDestino);
            menorPeso = INF;
        }while(!this.controlMarcado.estanTodosMarcados());            
    }

    @Override
    public String toString() {
        String s= "Algoritmo de Prim \n";
        for (int i = 0; i < this.grafoAux.cantidadDeVertices(); i++) {
            s = s +"|"+ i + "|-> " + this.grafoAux.listaDeAdyacencia.get(i)  + "\n";
            //adyacentesDeVertice(i)
        }
        return s;
    }
    
}
