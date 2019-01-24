package Practica_2_PSP.ej1;

public class Main {
	
	final int OPERACIONES = 6000;
	
	final int VALOR_INICIAL = 15;
	
	int valorCompartido = VALOR_INICIAL;
	
	public static void main(String[] args) throws InterruptedException {	
		new Main().correrPrograma();
	}
	
	public void correrPrograma() throws InterruptedException {
		
		System.out.println("Valor inicial: " + VALOR_INICIAL);
		
		Sumador suma = new Sumador();
		Restador resta = new Restador();
		
		suma.start();
		resta.start();
		
		suma.join();
		resta.join();
		
		/*
		 * TANTO VALOR INICIAL COMO VALORCOMPARTIDO DEBERÍAN VALER
		 * LO MISMO AL PRINCIPIO Y AL FINAL DEL PROGRAMA.
		 * 
		 * DEBIDO A QUE NO HAY NADA DE SINCRONIZACIÓN APLICADO A 
		 * ESTE PROGRAMA, EL RESULTADO RARA VEZ SERÁ EL MISMO QUE EL INICIAL.
		 */
		
		System.out.println("Valor final: " + valorCompartido);
	}
	
	/*
	 * ESTAS DOS CLASES SE EJECUTAN EN PARALELO Y ACCEDEN
	 * AL RECURSO CUANDO LES APETECE.
	 */
	
	
	//CLASE QUE SUMA
	class Sumador extends Thread {
	
		public void run() {
			for (int i = 0; i < OPERACIONES; i++) {
				//SECCIÓN CRÍTICA  VVVVVVVVVVVV
				valorCompartido++;
			}
		}
		
	}
	
	//CLASE QUE RESTA
	class Restador extends Thread {
		
		public void run() {
			for (int i = 0; i < OPERACIONES; i++) {
				//SECCIÓN CRÍTICA  VVVVVVVVVVVVs
				valorCompartido--;
			}
		}
		
	}
	
}
