package Practica_2_PSP.ej4_2;

public class Excavadora {

	final private int TIEMPO_CARGA = 1500;
	
	private Camion camionCargando = null;

	/**
	 * 
	 * Este m�todo carga el cami�n, tom�ndose el tiempo necesario.
	 * 
	 * @param camion
	 * @param cargado
	 * @return
	 */
	
	
	synchronized public boolean cargar(Camion camion, boolean cargado) {
		
		camionCargando = camion;
		verificarSiTengoCamionCorrecto(camion);
		System.out.println("C_" + camion.getNumeroCamion() + " (5) - EXCAVADORA - CARGANDO CAMI�N.");
		try {
			Thread.sleep(TIEMPO_CARGA);
		} catch (InterruptedException ex) {
			System.err.println("[ERROR] LA EXCAVADORA HA EXPLOTADO POR COMBUSTI�N EXPONT�NEA. VAYA.");
		}
		System.out.println("C_" + camion.getNumeroCamion() + " (6) - EXCAVADORA - FIN CARGA.");
		boolean carga = !cargado;
		verificarSiTengoCamionCorrecto(camion);
		camionCargando = null;
		
		return carga;
	}
	
	/**
	 * Verifica que la excavadora est� cargando el cami�n correcto y si no
	 * se ha colado otro cami�n por delante.
	 * @param camion
	 */
	
	public void verificarSiTengoCamionCorrecto(Camion camion) {
		
		if (camion != camionCargando) {
			System.err.println("[ERROR] �DISCREPANCIA DETECTADA! CARGANDO CAMI�N QUE NO ES:");
			System.err.println("[ERROR] CAMI�N " + camionCargando.getNumeroCamion() + " SE HA COLADO A CAMI�N " + camion);
		}
		
	}
	
}
