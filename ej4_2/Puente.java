package Practica_2_PSP.ej4_2;

import java.util.concurrent.Semaphore;

public class Puente {

	final private int TIEMPO_CRUZAR = 500;

	Semaphore semaforoPuente = new Semaphore(5, true);
	
	private Camion camionEnPuente = null;
	
	/**
	 * Este m�todo controla el cami�n cruzando de un lado a otro.
	 * 
	 * @param camion
	 * @param cargado
	 * @throws InterruptedException
	 */
	
	synchronized public void cruzar(Camion camion, boolean cargado) throws InterruptedException {
		int paso;
		if (!cargado) {
			semaforoPuente.acquire();
			paso = 2;
		} else {
			paso = 8; 
		}
		
		camionEnPuente = camion;
		verificaSiCamionCorrectoCruzando(camion);
		System.out.println("C_" + camion.getNumeroCamion() + " ("+ paso + ") - PUENTE - COMIENZA A CRUZAR");
		Thread.sleep(TIEMPO_CRUZAR);
		System.out.println("C_" + camion.getNumeroCamion() + " (" + (paso + 1) +") - PUENTE - FIN CRUZAR");
		verificaSiCamionCorrectoCruzando(camion);
		camionEnPuente = null;	
		
		if (cargado) {
			semaforoPuente.release();
		}
	}
	
	/**
	 * Este m�todo verifica si el cami�n correcto est� cruzando y si por
	 * consiguiente ninguno est� empujando a otro fuera del puente.
	 * 
	 * @param camion
	 */
	
	private void verificaSiCamionCorrectoCruzando(Camion camion) {
		
		if (camionEnPuente != camion) {
			System.err.println("[ERROR] �HAY UN PROBLEMA! UN CAMI�N EST� TIRANDO A OTRO DEL PUENTE.");
			System.err.println("[ERROR] EL CAMI�N " + camionEnPuente.getNumeroCamion() + " HA EMPUJADO AL CAMI�N " + camion.getNumeroCamion() + " DEL PUENTE.");
		}
		
	}
}
