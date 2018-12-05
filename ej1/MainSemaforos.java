package ej1;

import java.util.concurrent.Semaphore;

public class MainSemaforos {
	
	final int OPERACIONES = 6000;
	
	final int VALOR_INICIAL = 15;
	
	//ESTE SEMÁFORO NOS PERMITE HACER QUE CADA HILO PUEDA O NO ACCEDER A SU SECCIÓN CRÍTICA
	protected Semaphore semaforo = new Semaphore(1);  //<--- EL VALOR QUE TOMA ES EL NUMERO DE PERMISOS QUE PUEDE OTORGAR EN UN PRIMER LUGAR
	//EL SEMAFORO DETENDRÁ A UN HILO SI NO QUEDAN PERMISOS, Y CUANDO HAYA UN PERMISO DISPONIBLE LO DEJARÁ PASAR.
	//ESTE ES EL CASO DE UN SEMÁFORO BINARIO, UN MUTEX, QUE HACE DE CERROJO, QUE O ABRE LA SECCIÓN CRITICA O LA CIERRA.
	
	int valorCompartido = VALOR_INICIAL;
	
	public static void main(String[] args) throws InterruptedException {	
		new MainSemaforos().correrPrograma();
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
		 */
		
		System.out.println("Valor final: " + valorCompartido);
	}
	
	/*
	 * ESTAS DOS CLASES SE EJECUTAN EN PARALELO Y ACCEDEN
	 * AL RECURSO SI EL SEMÁFORO ESTÁ EN VERDE. CADA VEZ
	 * QUE UN HILO ENTRA EN SU SECCIÓN CRÍTICA, PONE EL SEMÁFORO
	 * EN ROJO, Y CUANDO ACABA LO PONE EN VERDE. ESTO DEBERÍA 
	 * HACER QUE EL RESULTADO FINAL SEA EL MISMO QUE EL INICIAL.
	 */
	
	
	//CLASE QUE SUMA
	class Sumador extends Thread  {
	
		public void run() {
			for (int i = 0; i < OPERACIONES; i++) {
				//SECCIÓN CRÍTICA  VVVVVVVVVVVV
				try {
					semaforo.acquire(1);
					valorCompartido++;
					semaforo.release(1);
				} catch (InterruptedException e) {
					semaforo.release(1);
				}
			}
		}
		
	}
	
	//CLASE QUE RESTA
	class Restador extends Thread {
		
		public void run() {
			for (int i = 0; i < OPERACIONES; i++) {
				//SECCIÓN CRÍTICA  VVVVVVVVVVVV
				try {
					semaforo.acquire(1);
					valorCompartido--;
					semaforo.release(1);
				} catch (InterruptedException e) {
					semaforo.release(1);
				}
			}
		}
		
	}
	
}
