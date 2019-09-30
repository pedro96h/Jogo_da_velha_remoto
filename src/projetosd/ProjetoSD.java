/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosd;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author Pedro Oliveira
 */
public class ProjetoSD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NotBoundException {
        //-1 = cpu
        //1 = pessoa
        int op;
        Scanner sc = new Scanner(System.in);
        int[][] tabuleiro = new int[3][3];

        do {
            System.out.println("M E N U \n");
            System.out.println("1 - jogar");
            System.out.println("0 - sair");
            System.out.print("digite aqui : ");
            op = sc.nextInt();
            if (op < 0 || op > 1) {
                System.out.println("numero invalido digite numero dentro da "
                        + "faixa permitida");
            }
            System.out.println("--------------------------------------");
            if (op == 1) {
                try {
                    boolean verificador;
                    Registry registro = LocateRegistry.getRegistry();
                    Interface objetoremoto = (Interface) registro.lookup("JogoRemoto");
                    
                    if(objetoremoto.sortearPrimeiro()){
                        System.out.println("voce comeca jogando");
                        System.out.print("digite a linha[1-3] : ");
                        int linha = sc.nextInt();
                        while(linha < 1 || linha > 3){
                            System.out.println("numero invalido");
                            System.out.print("digite numero dentro da faixa "
                                    + "permitida : ");
                            linha = sc.nextInt();
                        }
                        System.out.print("digite a coluna[1-3] : ");
                        int coluna = sc.nextInt();
                        while(coluna < 1 || coluna > 3){
                            System.out.println("numero invalido");
                            System.out.print("digite numero dentro da faixa "
                                    + "permitida : ");
                            coluna = sc.nextInt();
                        }
                        System.out.println("");
                        System.out.println("turno do player");
                        tabuleiro = objetoremoto.jogadorMarca(tabuleiro, linha-1, coluna-1);
                        System.out.println(objetoremoto.exibirTabuleiro(tabuleiro));
                        System.out.println("turno da Cpu");
                        tabuleiro = objetoremoto.cpuMarca(tabuleiro);
                        System.out.println(objetoremoto.exibirTabuleiro(tabuleiro));
                    } else {
                        System.out.println("a cpu comeca jogando");
                        tabuleiro = objetoremoto.cpuMarca(tabuleiro);
                        System.out.println("turno da Cpu");
                        System.out.println(objetoremoto.exibirTabuleiro(tabuleiro));
                    }
                    while(objetoremoto.verificarjogo(tabuleiro) == 0){
                        int linha,coluna;
                        do {
                            System.out.print("digite a linha[1-3] : ");
                            linha = sc.nextInt();
                            while(linha < 1 || linha > 3){
                                System.out.println("numero invalido");
                                System.out.print("digite numero dentro da faixa "
                                    + "permitida : ");
                                linha = sc.nextInt();
                            }
                            System.out.print("digite a coluna[1-3] : ");
                            coluna = sc.nextInt();
                            while(coluna < 1 || coluna > 3){
                                System.out.println("numero invalido");
                                System.out.print("digite numero dentro da faixa "
                                    + "permitida : ");
                                coluna = sc.nextInt();
                                System.out.println("");
                            }
                            verificador = objetoremoto.verificarPosicao(tabuleiro, linha-1, coluna-1);
                            if(verificador == false){
                                System.out.println("essa posicao ja esta ocupada");
                                System.out.println("digite uma posicao valida");
                                System.out.println(objetoremoto.exibirTabuleiro(tabuleiro));
                            } else {
                                tabuleiro = objetoremoto.jogadorMarca(tabuleiro, linha-1, coluna-1);
                                System.out.println(objetoremoto.exibirTabuleiro(tabuleiro));
                                verificador = true;
                            }
                        } while (verificador == false);                            
                           
                        if(objetoremoto.verificarjogo(tabuleiro) == 1){
                            System.out.println("O jogador ganhou");
                        } else if (objetoremoto.verificarjogo(tabuleiro) == -1){
                            System.out.println("A cpu ganhou");
                        } else if(objetoremoto.verificarjogo(tabuleiro) == -2){
                            System.out.println("Deu velha");
                        }
                        
                        tabuleiro = objetoremoto.cpuMarca(tabuleiro);
                        System.out.println(objetoremoto.exibirTabuleiro(tabuleiro));
                        if(objetoremoto.verificarjogo(tabuleiro) == 1){
                            System.out.println("O jogador ganhou");
                        } else if (objetoremoto.verificarjogo(tabuleiro) == -1){
                            System.out.println("A cpu ganhou");
                        } else if(objetoremoto.verificarjogo(tabuleiro) == -2){
                            System.out.println("Deu velha");
                        }
                        
                    }
                    tabuleiro = objetoremoto.limparTabuleiro(tabuleiro);
                } catch (RemoteException e) {
                    System.out.println("deu ruim");
                }  
            }
        } while (op != 0);

        sc.close();

    }
}
