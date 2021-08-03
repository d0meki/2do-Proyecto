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
public class DigrafoPesado extends GrafoPesado{
    double [][] matrizDeAdyacencia;
    public DigrafoPesado(int nroDeVerticesInicial){
        super(nroDeVerticesInicial);
        matrizDeAdyacencia  = new double[nroDeVerticesInicial][nroDeVerticesInicial];
        for (int i = 0; i < nroDeVerticesInicial; i++) {
            for (int j = 0; j < nroDeVerticesInicial; j++) {
                matrizDeAdyacencia[i][j] = 0;
            }
        }
    }

    @Override
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino,double costo){
        super.validarVertice(posVerticeOrigen);
        super.validarVertice(posVerticeDestino);
        
        List<AdyacenteConPeso> adyacenciaDelOrigen  = super.listaDeAdyacencia.get(posVerticeOrigen);
        adyacenciaDelOrigen.add(new AdyacenteConPeso(posVerticeDestino,costo));
        matrizDeAdyacencia[posVerticeOrigen][posVerticeDestino] = costo;
    }

    @Override
    public int gradoDeVertice(int posDeVertice) {
        throw new UnsupportedOperationException("No soportado en grado dirigido");
    }
    public int gradoDeSalida(int posDeVertice){
        return super.gradoDeVertice(posDeVertice);
    }
    public int gradoDeEntrada(int posDeVertice){
        super.validarVertice(posDeVertice);
        int entradasDeVertice = 0;
        for (List<AdyacenteConPeso> adyacentesDeUnVertice : super.listaDeAdyacencia) {
            for (AdyacenteConPeso posAdyacente : adyacentesDeUnVertice) {
                if (posAdyacente.getIndiceVertice() == posDeVertice) {
                    entradasDeVertice++;
                }
            }
        }
        return entradasDeVertice;
    }
    public void mostrarMatrizDeAdyacensia(){
        for (int i = 0; i < this.cantidadDeVertices(); i++) {
            for (int j = 0; j < this.cantidadDeVertices(); j++) {
                System.out.print(matrizDeAdyacencia[i][j] + " ");
            }
            System.out.println();
        }
    }
    public double peso(int VerticeOrigen, int VerticeDestino){
        super.validarVertice(VerticeOrigen);
        super.validarVertice(VerticeDestino);
        for (List<AdyacenteConPeso> adyacentesDeUnVertice : super.listaDeAdyacencia) {
            for (AdyacenteConPeso posAdyacente : adyacentesDeUnVertice) {
                if (posAdyacente.getIndiceVertice() == VerticeDestino) {
                    return posAdyacente.getPeso();
                }else{
                    return 0;
                }
                
            }
        }
        return 0;
    }
    
}
