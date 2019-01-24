package Practica_2_PSP.ej4_2;

public class ComenzarTrabajo implements Runnable {
	
	private Camion camion;
	
	public ComenzarTrabajo(Camion camion) {	
		this.camion = camion;
	}
	
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
