package Practica_2_PSP.ej1;

public class MainMonitor {
	
	final int OPERACIONES = 6000;
	
	final int VALOR_INICIAL = 15;
	
	//LOS MONITORES SOLO FUNCIONAN CON OBJETOS!!!!! <---- IMPORTANTE
	class Contar {
		public int valorCompartido = VALOR_INICIAL;
	}
	
	final Contar CUENTA = new Contar();
	
	public static void main(String[] args) throws InterruptedException {	
		new MainMonitor().correrPrograma();
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
		 * TANTO VALOR INICIAL COMO VALOR COMPARTIDO DEBERÍAN VALER
		 * LO MISMO AL PRINCIPIO Y AL FINAL DEL PROGRAMA.
		 */
		
		System.out.println("Valor final: " + CUENTA.valorCompartido);
	}
	
	/*
	 * ESTAS DOS CLASES SE EJECUTAN EN PARALELO Y ACCEDEN
	 * AL RECURSO DE MANERA SINCRONIZADA CON MONITORES.
	 */
	
	
	//CLASE QUE SUMA
	class Sumador extends Thread {
	
		public void run() {
			for (int i = 0; i < OPERACIONES; i++) {
				//SECCIÓN CRÍTICA  VVVVVVVVVVVV
				//USO DE MONITORES (SENTENCIA SYNCHRONIZED)
				synchronized (CUENTA) {
					CUENTA.valorCompartido++;
				}
			}
		}
		
	}
	
	//CLASE QUE RESTA
	class Restador extends Thread {
		
		public void run() {
			for (int i = 0; i < OPERACIONES; i++) {
				//SECCIÓN CRÍTICA  VVVVVVVVVVVV
				//USO DE MONITORES (SENTENCIA SYNCHRONIZED)
				synchronized(CUENTA) {
					CUENTA.valorCompartido--;
				}
			}
		}
		
	}
	
}
