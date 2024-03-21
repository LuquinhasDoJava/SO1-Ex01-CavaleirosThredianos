package view;

import java.util.concurrent.Semaphore;

import controller.CavaleirosThredianos;

public class Main {
	public static void main(String[] args) {
		Semaphore tocha = new Semaphore(1);
		Semaphore pedra = new Semaphore(1);

		System.out.println("Chuvisco de xuca");
		for (int x = 1; x <= 4; x++) {
			CavaleirosThredianos ct = new CavaleirosThredianos(x,4,tocha,pedra);
			ct.start();
		}
	}
}
