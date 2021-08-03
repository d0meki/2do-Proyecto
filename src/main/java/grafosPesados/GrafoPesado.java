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
public class GrafoPesado {
    protected List<List<AdyacenteConPeso>> listaDeAdyacencia;
    public GrafoPesado(){
        this.listaDeAdyacencia = new ArrayList<>();
    }
    public GrafoPesado(int nroDeVerticesInicial){
      
        this.listaDeAdyacencia = new ArrayList<>();
        for (int i = 0; i < nroDeVerticesInicial; i++) {
            this.listaDeAdyacencia.add(new ArrayList<>());
        }
    }
    public void insertarVertice(){
        this.listaDeAdyacencia.add(new ArrayList<>());
    }
    
    public int cantidadDeVertices(){// n
        return listaDeAdyacencia.size();
    }
    public void validarVertice(int posicionDeVertice){ //para no insertar en posiciones fuera del rango del vertice
        if (posicionDeVertice < 0 ||
            posicionDeVertice >= cantidadDeVertices()) {
            throw new IllegalArgumentException("El vertice "+ posicionDeVertice +
                    " no pertenece al grafo");
        }
    }
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino, double costo){
       

        List<AdyacenteConPeso> adyacenciasDelOrigen = this.listaDeAdyacencia.get(posVerticeOrigen);
        adyacenciasDelOrigen.add(new AdyacenteConPeso(posVerticeDestino, costo));

        Collections.sort(adyacenciasDelOrigen);
        if (posVerticeOrigen != posVerticeDestino) {
            List<AdyacenteConPeso> adyacenciasDelDestino = this.listaDeAdyacencia.get(posVerticeDestino);
            adyacenciasDelDestino.add(new AdyacenteConPeso(posVerticeOrigen, costo));
        }
    }
    public boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino){
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<AdyacenteConPeso> adyacenciaDelOrigen  = this.listaDeAdyacencia.get(posVerticeOrigen);
        AdyacenteConPeso destino = new AdyacenteConPeso(posVerticeDestino);
        boolean z = adyacenciaDelOrigen.contains(destino);
        return z;
    }
    public void eliminarVertice(int posVerticeAEliminar){
        validarVertice(posVerticeAEliminar);
        this.listaDeAdyacencia.remove(posVerticeAEliminar);
//        for (int i = 0; i < this.listaDeAdyacencia.size(); i++) {
//            
//        }
// es el mismo for
        for (List<AdyacenteConPeso> adyacentesDeVertice : this.listaDeAdyacencia) { //recorre la lista de adyacencia del vertice
            AdyacenteConPeso adyacenteConPeso = new AdyacenteConPeso(posVerticeAEliminar);
            int posicionDeVerticeEnAdy = adyacentesDeVertice.indexOf(posVerticeAEliminar);//asiga al adyacente del vertice eliminado si se encuentra
            if (posicionDeVerticeEnAdy >= 0) {
                adyacentesDeVertice.remove(posicionDeVerticeEnAdy);
            }
            for (int i = 0; i < adyacentesDeVertice.size(); i++) {
                AdyacenteConPeso posicionAdyacente = adyacentesDeVertice.get(i);
                //int posicionAdyacente = adyacentesDeVertice.get(i);
                if (posicionAdyacente.getIndiceVertice() > posVerticeAEliminar) {
                posicionAdyacente.setIndiceVertice(posicionAdyacente.getIndiceVertice() - 1);
                // adyacentesDeVertice.set(i, posicionAdyacente - 1);
                }
            }    
        }
    }  
    public int gradoDeVertice(int posDeVertice){ //conexiones -- esto se lo utiliza para los BFS
        validarVertice(posDeVertice);
        List<AdyacenteConPeso> adyacenciasDelVertice = this.listaDeAdyacencia.get(posDeVertice);
        return adyacenciasDelVertice.size();
    }
//    public Iterable<Integer> adyacentesDeVertice(int posDeVertice){
//        validarVertice(posDeVertice);
//         List<AdyacenteConPeso> adyacenciasDelVertice = this.listaDeAdyacencia.get(posDeVertice);
//         List<Integer> adyacenctesDelVertice = new ArrayList<>();
//         for (AdyacenteConPeso adyacente : adyacenciasDelVertice) {
//            adyacenctesDelVertice.add(adyacente.getIndiceVertice());
//        }
//         Iterable<Integer> it = adyacenctesDelVertice;
//        return it;
//    }
    
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino){
       validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
      
        if (posVerticeOrigen != posVerticeDestino) {
            List<AdyacenteConPeso> adyacenciaDelOrigen = this.listaDeAdyacencia.get(posVerticeOrigen);
            List<AdyacenteConPeso> adyacenciaDelDestino = this.listaDeAdyacencia.get(posVerticeDestino);

            adyacenciaDelOrigen.remove(adyacenciaDelOrigen.indexOf(new AdyacenteConPeso(posVerticeDestino)));
            adyacenciaDelDestino.remove(adyacenciaDelDestino.indexOf(new AdyacenteConPeso(posVerticeOrigen)));
        } else {

            List<AdyacenteConPeso> adyacenciaDelDestino = this.listaDeAdyacencia.get(posVerticeDestino);
            adyacenciaDelDestino.remove(adyacenciaDelDestino.indexOf(new AdyacenteConPeso(posVerticeDestino)));
        }
    }
    public int cantDeAristas(){
        int cantAristas = 0;
        int cantLazos = 0;
        for (int i = 0; i < this.listaDeAdyacencia.size(); i++) {
            List<AdyacenteConPeso> adyaventesDeunVertice = this.listaDeAdyacencia.get(i);
            for (AdyacenteConPeso posAdyacente : adyaventesDeunVertice) {
                if (i == posAdyacente.getIndiceVertice()) {
                    cantLazos++;
                }else{
                    cantAristas++;
                }
            }
        }
        cantAristas = (cantAristas/2) + cantLazos;
        return cantAristas;
    }
    public Iterable<Integer> adyacentesDeVertice(int posVertice){
        validarVertice(posVertice);
        List<AdyacenteConPeso> adyacentesDeVertice = this.listaDeAdyacencia.get(posVertice);
        //adyacentesDeVertice.get(posVertice);
        List<Integer> soloVertice = new ArrayList<>();
        for(AdyacenteConPeso adyacenteConPeso: adyacentesDeVertice){
            soloVertice.add(adyacenteConPeso.getIndiceVertice());
        }
        Iterable<Integer> iterableAdyacentes = soloVertice;
        return iterableAdyacentes;
    }
    public double peso(int posOrigen,int posDestino){
    
    List<AdyacenteConPeso> adyacenciaDelOrigen  = this.listaDeAdyacencia.get(posOrigen);
    AdyacenteConPeso destino = new AdyacenteConPeso(posDestino);
    int posAdyacencia = adyacenciaDelOrigen.indexOf(destino);
    
    return adyacenciaDelOrigen.get(posAdyacencia).getPeso();
    }
    public boolean existenCiclos(){
       UtilsRecorrido recorrido = new UtilsRecorrido(this.cantidadDeVertices());
        boolean existeUnCiclo = false;
        int verticeActual = 0;
        recorrido.marcarVertice(verticeActual);
        List<Integer> listaDeAdyacentes =(List<Integer>) this.adyacentesDeVertice(verticeActual);
       
        while (!recorrido.estanTodosMarcados() && !existeUnCiclo) {

            if (listaDeAdyacentes.size() == 0) {
                verticeActual = recorrido.siguienteNoMarcado();
                
            } else {
                int verticeSiguiente = listaDeAdyacentes.get(0);
                if (recorrido.estaMarcado(verticeSiguiente)) {
                    if (listaDeAdyacentes.size() < 2)
                        verticeActual = recorrido.siguienteNoMarcado();
                    else {
                        verticeSiguiente = listaDeAdyacentes.get(1);
                        if (recorrido.estaMarcado(verticeSiguiente)) {
                            existeUnCiclo = true;
                        } else {
                            verticeActual = verticeSiguiente;
                        }
                    }
                } else {
                    verticeActual = verticeSiguiente;
                }
            }

            listaDeAdyacentes = (List<Integer>) this.adyacentesDeVertice(verticeActual);
            recorrido.marcarVertice(verticeActual);
        }
        return existeUnCiclo;
    }
     
    @Override
    public String toString() {
        String s= "";
        for (int i = 0; i < cantidadDeVertices(); i++) {
            s = s +"|"+ i + "|-> " + this.listaDeAdyacencia.get(i)  + "\n";
            //adyacentesDeVertice(i)
        }
        return s;
    }
    public static void main(String[] args) {
            GrafoPesado GP = new GrafoPesado(10);
//            GP.insertarArista(0, 2, 20);
//            GP.insertarArista(0, 3, 30);
//            GP.insertarArista(1, 2, 40);
//            GP.insertarArista(2, 1, 5);
//            GP.insertarArista(2, 3, 100);

              GP.insertarArista(0, 1, 5);  
              GP.insertarArista(0, 2, 10);  
              GP.insertarArista(0, 3, 8);  
              GP.insertarArista(1, 3, 6);  
              GP.insertarArista(1, 5, 5);  
              GP.insertarArista(2, 3, 7);  
              GP.insertarArista(2, 4, 8);  
              GP.insertarArista(2, 7, 15);  
              GP.insertarArista(3, 6, 11);  
              GP.insertarArista(3, 4, 5);  
              GP.insertarArista(4, 6, 4);  
              GP.insertarArista(4, 7, 3);  
              GP.insertarArista(5, 6, 9);  
              GP.insertarArista(5, 8, 7);  
              GP.insertarArista(6, 7, 12);  
              GP.insertarArista(6, 8, 4);  
              GP.insertarArista(6, 9, 6);  
              GP.insertarArista(7, 9, 12);  
              GP.insertarArista(8, 9, 7);   
              
            System.out.println(GP);
            
            Kruskal k = new Kruskal(GP);
            System.out.println(k);
            System.out.println(k.ArbolDeExpancionDeCostoMinimoEncontrado());
            
//            DigrafoPesado DGP = new DigrafoPesado(4);
//            DGP.insertarArista(0, 2, 20);
//            DGP.insertarArista(0, 3, 30);
//            DGP.insertarArista(1, 2, 40);
//            DGP.insertarArista(2, 1, 5);
//            DGP.insertarArista(2, 3, 100);
//            System.out.println(DGP);
//            DFS recorrido = new DFS(DGP,0);
//            DGP.mostrarMatrizDeAdyacensia();
//            DGP.peso(0, 3);
//            System.out.println("Recorrido "+recorrido.elRecorrido());           
    }
}
