/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosd;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
/**
 *
 * @author Pedro Oliveira
 */
public class JogoRemoto extends UnicastRemoteObject implements Interface{
    
    public JogoRemoto () throws RemoteException {
        super();
    }

    @Override
    public boolean sortearPrimeiro() {
        Random primeiro = new Random();
        return primeiro.nextBoolean();
    }

    @Override
    public String exibirTabuleiro(int[][] tabuleiro) throws RemoteException {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<tabuleiro.length;i++){
            for(int j=0;j<tabuleiro.length;j++){
                if(tabuleiro[i][j] == -1){
                    sb.append("O");
                } else if(tabuleiro[i][j] == 1){
                    sb.append("X");
                } else {
                    sb.append(" ");
                }
                if(j < tabuleiro.length-1){
                    sb.append("|");
                }
            }
            sb.append("\n");
            if(i< tabuleiro.length-1){
                sb.append("- - - ");
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean verificarPosicao(int[][] tabuleiro, int linha, int coluna) throws RemoteException {
        if(tabuleiro[linha][coluna] != 0){
            return false;
        } else{
            return true;
        } 
    }

    @Override
    public int[][] jogadorMarca(int[][] tabuleiro, int linha, int coluna) throws RemoteException {
        tabuleiro[linha][coluna] = 1;
        return tabuleiro;
    }

    @Override
    public int[][] cpuMarca(int[][] tabuleiro) throws RemoteException {
        Random random = new Random();
        int linha,coluna;
        do {
            linha = random.nextInt(3);
            coluna = random.nextInt(3);
        } while(verificarPosicao(tabuleiro, linha, coluna) == false);
        
        tabuleiro[linha][coluna] = -1;
        return tabuleiro;
    }

    @Override
    public boolean empate(int[][] tabuleiro) throws RemoteException {
        int cont=0;
        for(int i=0;i<tabuleiro.length;i++){
            for(int j=0;j<tabuleiro.length;j++){
                if(tabuleiro[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int verificarjogo(int[][] tabuleiro) throws RemoteException {
        //1 - player
        //-1 = cpu
        //0 = nada 
        //-2 =velha
        if(verificarLinha(tabuleiro) == 1 ||verificarColuna(tabuleiro) == 1 || verificarDiagonalPrincipal(tabuleiro) == 1
                || verificarDiagonalSecundaria(tabuleiro) == 1){
            return 1;
        } else if (verificarLinha(tabuleiro) == -1 ||verificarColuna(tabuleiro) == -1||
                verificarDiagonalPrincipal(tabuleiro) == -1||
                verificarDiagonalSecundaria(tabuleiro) == -1){
            return -1;
        } else {
            if(empate(tabuleiro)){
                return -2;
            } else {
                return 0;
            }
        }
    }
     
    private int verificarLinha (int[][] tabuleiro){
        int player = 0;
        int cpu = 0;
        
        for(int i=0;i<tabuleiro.length;i++){
            for(int j=0;j<tabuleiro.length;j++){
                if(tabuleiro[i][j] == 1){
                    player += 1;
                }
                if(tabuleiro[i][j] == -1){
                    cpu += 1;
                }
            }
            if(player == 3){
                return 1;
            } else if(cpu == 3){
                return -1;
            } else {
                player = 0;
                cpu = 0;
            }
        }
        return 0;
    }
   
    private int verificarColuna (int[][] tabuleiro){
        int player = 0;
        int cpu = 0;
        
        for(int i=0;i<tabuleiro.length;i++){
            for(int j=0;j<tabuleiro.length;j++){
                if(tabuleiro[j][i] == 1){
                    player += 1;
                }
                if(tabuleiro[j][i] == -1){
                    cpu += 1;
                }
            }
            if(player == 3){
                return 1;
            } else if(cpu == 3){
                return -1;
            } else {
                player = 0;
                cpu = 0;
            }
        }
        return 0;
    }
    
    private int verificarDiagonalPrincipal (int[][] tabuleiro){
        int player = 0;
        int cpu = 0;
        
        for(int i=0;i<tabuleiro.length;i++){
            if(tabuleiro[i][i] == 1){
                player += 1;
            } 
            if(tabuleiro[i][i] == -1){
                cpu += 1;
            }
        }
        if(player == 3){
            return 1;
        } else if(cpu == 3){
            return -1;
        } else{
            return 0;
        }
    }
    
    private int verificarDiagonalSecundaria (int[][] tabuleiro){
        int player = 0;
        int cpu = 0;
        
        for(int i=0,j=2;i<tabuleiro.length;++i,--j){
            if(tabuleiro[i][j] == 1){
                player += 1;
            }
            if(tabuleiro[i][j] == -1){
                cpu += 1;
            }
        }
        if(player == 3){
            return 1;
        } else if(cpu == 3){
            return  -1;
        } else {
            return 0;
        }
    }

    @Override
    public int[][] limparTabuleiro(int[][] tabuleiro) throws RemoteException {
        for(int i=0;i<tabuleiro.length;++i){
            for(int j=0;j<tabuleiro.length;++j){
                tabuleiro[i][j] = 0;
            }
        }
        return  tabuleiro;
    }
}
