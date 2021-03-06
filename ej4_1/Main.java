package Practica_2_PSP.ej4_1;

public class Main {

	/**
	 * Ejecuta simplemente los hilos.
	 * 
	 * @param args
	 */
	
	
	public static void main(String[] args) {

		System.out.println("[GERENTE] Comienzo de la jornada.");
		
		Puente puente = new Puente();
		Excavadora excavadora = new Excavadora();
		
		for (int i = 0; i < 10; i++) {
			
			Camion camion = new Camion(i + 1, puente, excavadora);
			
			Runnable ejecutable = new ComenzarTrabajo(camion);
			
			Thread th1 = new Thread(ejecutable);
			
			th1.start();
			
		}

	}

}
