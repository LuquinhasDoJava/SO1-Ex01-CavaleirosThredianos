package view;

import controller.CavaleirosThredianos;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore pegarItens = new Semaphore(1);
        Random random = new Random();
        int portaCerta = random.nextInt(0,4);
        for (int x = 1; x<=4; x++) {
            CavaleirosThredianos cv = new CavaleirosThredianos(pegarItens,x,portaCerta);
            cv.start();
        }
    }
}
