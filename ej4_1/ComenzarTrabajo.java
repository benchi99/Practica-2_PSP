package ej4_1;

public class ComenzarTrabajo implements Runnable {
	
	private Camion camion;
	
	public ComenzarTrabajo(Camion camion) {	
		this.camion = camion;
	}
	
	/**
	 * Hilo que se encarga de ejecutar la rutina de un
	 * (1) camión.
	 */
	
	@Override
	public void run() {
		try {
			this.camion.empezarRutina();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
