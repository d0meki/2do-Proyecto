/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliares;

import com.mycompany.practicografos.Digrafos;
import com.mycompany.practicografos.Grafo;
import java.util.List;

/**
 *
 * @author user
 */
public class MatrizDeCamino {
    private boolean[][] marcarPosicion;
    private int[][] MatrizCamino;
    private Grafo grafo;
    public MatrizDeCamino(Digrafos unGrafo){
        this.grafo = unGrafo;
        int n = this.grafo.listaDeAdyacencia.size();
        MatrizCamino = new int[n][n];
        marcarPosicion = new boolean[n][n];
        for (int i = 0; i < marcarPosicion.length; i++) {
            for (int j = 0; j < marcarPosicion[i].length; j++) {
                marcarPosicion[i][j] = false;
            }
        }
        MarcarDiagonal();
    }
    private void MatrizAdyacencia(){
        for (int[] M1 : MatrizCamino) {
            for (int y = 0; y < M1.length; y++) {
                M1[y] = 0;
            }
        }
        for (int i = 0; i < this.grafo.listaDeAdyacencia.size(); i++) {
             List<Integer> L = this.grafo.listaDeAdyacencia.get(i);
             for (int j = 0; j < L.size(); j++) {
                MatrizCamino[i][L.get(j)] = 1;
            }
        }
    }
    private void MarcarDiagonal(){
        MatrizAdyacencia();
        for (int i = 0; i < marcarPosicion.length; i++) {
            for (int j = 0; j < marcarPosicion[i].length; j++) {
                if (i==j && MatrizCamino[i][j] != 0) {
                    marcarPosicion[i][j] = true;
                }
            }
        }
        mCaminos();
    }
    
    private void mCaminos(){
        for (int i = 0; i < MatrizCamino.length; i++) {
            for (int j = 0; j < MatrizCamino[i].length; j++) {
                if (!(marcarPosicion[i][j] == true) && MatrizCamino[i][j] != 0) {
                    marcarPosicion[i][j] = true;   
                    convinarFilas(i,j);
                    j = 0;
                }
            }
        }
    }
    private void convinarFilas(int fila, int fila2){
        for (int i = fila; i < fila+1; i++) {
            for (int j = 0; j < MatrizCamino.length; j++) {
                MatrizCamino[i][j] = MatrizCamino[i][j] | MatrizCamino[fila2][j];
            }
        }
    }
    public int[][] getMatrizCamino(){
        return MatrizCamino;
    }
    
    @Override
    public String toString() {
        String x = "Matriz De Caminos \n";
        for (int i = 0; i < MatrizCamino.length; i++) {
            for (int j = 0; j < MatrizCamino[i].length; j++) {
                x = x + MatrizCamino[i][j]+" ";
            }
            x = x + "\n";
        }
        return x;
    }
    
}
