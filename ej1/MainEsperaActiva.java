package Practica_2_PSP.ej1;

public class MainEsperaActiva {

	final int OPERACIONES = 6000;

	final int VALOR_INICIAL = 15;

	int valorCompartido = VALOR_INICIAL;

	/*
	 * ESTA ES UNA MANERA DE DETENER A UN HILO QUE ACCEDA A SU SECCIÓN CRÍTICA SI
	 * OTRO YA HA CAMBIADO EL VALOR DEL BOOLEANO ANTES.
	 */
	boolean hiloEnSeccionCritica = false;

	public static void main(String[] args) throws InterruptedException {
		new MainEsperaActiva().correrPrograma();
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
		 * TANTO VALOR INICIAL COMO VALOR COMPARTIDO DEBERÍAN VALER LO MISMO AL PRINCIPIO
		 * Y AL FINAL DEL PROGRAMA.
		 */

		System.out.println("Valor final: " + valorCompartido);
	}

	/*
	 * ESTAS DOS CLASES SE EJECUTAN EN PARALELO Y ACCEDEN AL RECURSO CUANDO LES
	 * APETECE.
	 */

	// CLASE QUE SUMA
	class Sumador extends Thread {

		public void run() {
			for (int i = 0; i < OPERACIONES; i++) {
				// SECCIÓN CRÍTICA VVVVVVVVVVVV
				/* EL CONTENIDO DE ESTE CONDICIONAL SOLO SE
				 * EJECUTA SI EL BOOLEANO ES FALSO, QUE SIGNIFICA
				 * QUE NINGUN HILO ESTÁ EN SU SECCIÓN CRITICA.
				 * 
				 * ESTA OPERACION **NO** ES ATÓMICA.
				 */
				while (hiloEnSeccionCritica) {
					System.out.println("Esperando...");
				}
				hiloEnSeccionCritica = true;
				valorCompartido++;
				hiloEnSeccionCritica = false;
			
			}
		}

	}

	// CLASE QUE RESTA
	class Restador extends Thread {

		public void run() {
			for (int i = 0; i < OPERACIONES; i++) {
				// SECCIÓN CRÍTICA VVVVVVVVVVVV
				/* EL CONTENIDO DE ESTE CONDICIONAL SOLO SE
				 * EJECUTA SI EL BOOLEANO ES FALSO, QUE SIGNIFICA
				 * QUE NINGUN HILO ESTÁ EN SU SECCIÓN CRITICA.
				 * 
				 * ESTA OPERACION **NO** ES ATÓMICA.
				 */
				while (hiloEnSeccionCritica) {
					System.out.println("Esperando...");
				}
				hiloEnSeccionCritica = true;
				valorCompartido--;   //XD
				hiloEnSeccionCritica = false;
		
			}

		}
	}
}
