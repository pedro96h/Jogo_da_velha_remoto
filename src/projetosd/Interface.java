/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosd;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Pedro Oliveira
 */
public interface Interface extends Remote {
    
    public boolean sortearPrimeiro () throws RemoteException;;
    public String exibirTabuleiro (int [][] tabuleiro) throws RemoteException;;
    public boolean verificarPosicao (int [][] tabuleiro,int linha,int coluna) throws RemoteException;
    public int[][] jogadorMarca (int[][] tabuleiro,int linha,int coluna) throws RemoteException;
    public int[][] cpuMarca (int[][] tabuleiro) throws RemoteException;
    public boolean empate (int[][] tabuleiro) throws RemoteException;
    public int verificarjogo (int[][] tabuleiro) throws RemoteException;
    public int[][] limparTabuleiro (int[][] tabuleiro) throws RemoteException;
}
