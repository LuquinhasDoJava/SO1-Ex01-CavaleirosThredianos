package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class CavaleirosThredianos extends Thread {
	private int cavaleiro;
	private int portaCerta;
	Semaphore tocha;
	Semaphore pedra;
	private int itens = 0;

	public CavaleirosThredianos(int x, int porta, Semaphore tocha, Semaphore pedra) {
		Random random = new Random();
		this.cavaleiro = x;
		this.portaCerta = random.nextInt(4);
		this.pedra = pedra;
		this.tocha = tocha;

	}

	public void run() {
		primeiraParte();
	}

	private void primeiraParte() {
		int x = 0;
		Random random = new Random();
		do {
			x += random.nextInt(500);
			System.out.println("Cavaleiro: " + cavaleiro + " já percorreu " + x + "m");
			try {
				sleep(50);
			} catch (InterruptedException e) {
			}
		} while (x < 500);

		System.out.println("Cavaleiros: " + cavaleiro + " está indo para a segunda parte");
		segundaParte(x);
		this.itens += pegarTocha();
	}

	private void segundaParte(int x) {
		Random random = new Random();
		do {
			x += random.nextInt(500) + (itens * 2);
			System.out.println("Cavaleiro: " + cavaleiro + " já percorreu " + x + "m");
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (x < 1500);
		this.itens = pegarPedra();
		System.out.println("Cavaleiro: " + cavaleiro + " está indo para a segunda parte");
		terceiraParte(x);
	}

	private void terceiraParte(int x) {
		Random random = new Random();
		do {
			x += random.nextInt(500) + (itens * 2);
			System.out.println("Cavaleiro: " + cavaleiro + " já percorreu " + x + "m");
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (x < 2000);
		if (portaCerta == cavaleiro) {
			System.out.println("Cavaleiro: " + cavaleiro + " conseguiu sair vivo com" + itens + " itens");
		} else {
			System.out.println("Cavaleiro: " + cavaleiro + " foi devorado com " + itens + " itens");
		}
	}
	private int pegarTocha() {
		try {
			tocha.acquire();
			System.out.println("Cavaleiros: "+cavaleiro+" pegou a tocha");
			return 1;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
		System.out.println("Não pegou a tocha");
		return 0;
		}
	}
	private int pegarPedra() {
		try {
			pedra.acquire();
			System.out.println("Cavaleiros: "+cavaleiro+" pegou a tocha");
			return 1;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Não pegou o item");
		return 0;
	}

}
