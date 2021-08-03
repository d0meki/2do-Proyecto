/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafosPesados;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author user
 */
public class Kruskal {
    private GrafoPesado grafoAux;
    private GrafoPesado grafoOriginal;
    private List<EstructuraA> aux;
   // private List<AdyacenteConPeso> estructuraDeAristasOrdenadaDeMenorAMayorPorSuPeso;
    public Kruskal(GrafoPesado unGrafo){
        this.grafoOriginal = unGrafo;
        this.grafoAux = new GrafoPesado(this.grafoOriginal.cantidadDeVertices());
        this.aux = new ArrayList<>();
        llenarAuxConElOriginal();
        eliminarVuelta();
        OrdenarPorPeso();
        insertarAristaSinBucle();
    }
    private void llenarAuxConElOriginal(){
        //insertamos aristas si no tienen ciclo y eliminamos si las hay
        for (int i = 0; i < this.grafoOriginal.cantidadDeVertices(); i++) {
            List<AdyacenteConPeso> adyacente = this.grafoOriginal.listaDeAdyacencia.get(i);
            for (int j = 0; j < adyacente.size(); j++) {
                this.aux.add(new EstructuraA(i,adyacente.get(j).getIndiceVertice(),adyacente.get(j).getPeso()));
            }
        }      
    }
    private void eliminarVuelta(){
        for (EstructuraA e: aux) {
                this.aux.indexOf(e);
            }
        for (int i = 0; i < aux.size(); i++) {
            int origenA = aux.get(i).getVerticeDestino();
            int destinoA = aux.get(i).getVerticeOrigen();
            for (int j = i+1; j < aux.size(); j++) {
                int origenB = aux.get(j).getVerticeOrigen();
                int destinoB = aux.get(j).getVerticeDestino();
                if ((origenA==origenB) && (destinoA==destinoB)) {
                    aux.remove(j);
                    break;
                }
            }
        }         
    }
    private void OrdenarPorPeso(){
        Collections.sort(this.aux);
    }
    private void insertarAristaSinBucle(){
        for(EstructuraA es: this.aux){            
            this.grafoAux.insertarArista(es.getVerticeOrigen(), es.getVerticeDestino(), es.getPeso());           
                if (this.grafoAux.existenCiclos()) {
                this.grafoAux.eliminarArista(es.getVerticeOrigen(), es.getVerticeDestino());
                break;
                }                    
        }
    }
    public String ArbolDeExpancionDeCostoMinimoEncontrado(){
        String s = "Grafo resultante \n";
        for (int i = 0; i < this.grafoAux.cantidadDeVertices(); i++) {
            s = s +"|"+ i + "|-> " + this.grafoAux.listaDeAdyacencia.get(i)  + "\n";
        }
        return s;
    }
    

    @Override
    public String toString() {
        String s = "| Origen | Desino | Costo | \n";
        //for (int i = 0; i < this.aux.size(); i++) {
            for (EstructuraA e: aux) {
                s = s+ "|    " + e.getVerticeOrigen() + "   |    " +e.getVerticeDestino()+"   |   "+e.getPeso()+"\n";
            }           
       // }
        return s+"\n"+aux.size();
    }
    
}
