package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class CavaleirosThredianos extends Thread{
    private Semaphore escolherPorta;
    private Semaphore pegarItem;
    private Random random;
    private int itens;
    private int cavaleiro;
    private static int []portas;
    private static boolean pedra;
    private static boolean tocha;
    public CavaleirosThredianos(Semaphore pegarItem, int cavaleiro, int portaCerta){
        this.pegarItem = pegarItem;
        this.escolherPorta = new Semaphore(1);
        random = new Random();
        itens = 0;
        this.cavaleiro = cavaleiro;
        portas = new int[]{0, 0, 0, 0};
        portas[portaCerta] = 1;
        tocha = true;
        pedra = true;
    }
    public void run(){
        primeiraParte();
    }

    private void primeiraParte() {
        int distancia = 0;
        do{
            distancia += random.nextInt(2,5);
            try {
                sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Cavaleiro "+cavaleiro+" já percorreu:"+distancia+"m");
        }while (distancia <500);
        pegarTocha();
        segundaParte(distancia);
    }

    private void segundaParte(int distancia) {
        do{
            distancia += (random.nextInt(2,5)+(2*itens));
            try {
                sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Cavaleiro "+cavaleiro+" já percorreu:"+distancia+"m");
        }while (distancia <1500);
        pegarPedra();
        terceiraParte(distancia);
    }

    private void terceiraParte(int distancia) {
        do{
            distancia += (random.nextInt(2,5)+(2*itens));
            try {
                sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Cavaleiro "+cavaleiro+" já percorreu:"+distancia+"m");
        }while (distancia <2000);
        escolherPorta();
    }

    private void escolherPorta() {
        boolean portaValida = true;
        try {
            escolherPorta.acquire();
        do{
            int escolha = random.nextInt(0,4);
            if(portas[escolha]==0){
                System.err.println("Cavaleiro "+cavaleiro+" entrou na porta "+ (escolha+1)+" e foi devorado!!");
                portas[escolha] = 3;
                portaValida = false;
            }
            if (portas[escolha]==1) {
                System.err.println("Cavaleiro "+cavaleiro+" entrou na porta "+ (escolha+1)+" e saiu vivo!!");
                portas[escolha] = 3;
                portaValida = false;
            }
        }while (portaValida);
            System.out.println("Cavaleiro "+cavaleiro+" terminou a jornada com: "+itens+" itens!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            escolherPorta.release();
        }
    }

    private void pegarTocha() {
        try {
            pegarItem.acquire();
            if(tocha){
                tocha = false;
                itens++;
                System.err.println("Cavaleiro "+cavaleiro+" pegou a tocha!");
            }else {
                System.out.println("Cavaleiro "+cavaleiro+" não conseguiu pegar a tocha");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            pegarItem.release();
        }
    }

    private void pegarPedra() {
        try {
            pegarItem.acquire();
            if(pedra){
                pedra = false;
                itens++;
                System.err.println("Cavaleiro "+cavaleiro+" pegou a pedra!");
            }else {
                System.out.println("Cavaleiro "+cavaleiro+" não conseguiu pegar a pedra");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            pegarItem.release();
        }
    }
}
