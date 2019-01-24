package Practica_2_PSP.ej4_2;

public class Excavadora {

	final private int TIEMPO_CARGA = 1500;
	
	private Camion camionCargando = null;

	/**
	 * 
	 * Este método carga el camión, tomándose el tiempo necesario.
	 * 
	 * @param camion
	 * @param cargado
	 * @return
	 */
	
	
	synchronized public boolean cargar(Camion camion, boolean cargado) {
		
		camionCargando = camion;
		verificarSiTengoCamionCorrecto(camion);
		System.out.println("C_" + camion.getNumeroCamion() + " (5) - EXCAVADORA - CARGANDO CAMIÓN.");
		try {
			Thread.sleep(TIEMPO_CARGA);
		} catch (InterruptedException ex) {
			System.err.println("[ERROR] LA EXCAVADORA HA EXPLOTADO POR COMBUSTIÓN EXPONTÁNEA. VAYA.");
		}
		System.out.println("C_" + camion.getNumeroCamion() + " (6) - EXCAVADORA - FIN CARGA.");
		boolean carga = !cargado;
		verificarSiTengoCamionCorrecto(camion);
		camionCargando = null;
		
		return carga;
	}
	
	/**
	 * Verifica que la excavadora está cargando el camión correcto y si no
	 * se ha colado otro camión por delante.
	 * @param camion
	 */
	
	public void verificarSiTengoCamionCorrecto(Camion camion) {
		
		if (camion != camionCargando) {
			System.err.println("[ERROR] ¡DISCREPANCIA DETECTADA! CARGANDO CAMIÓN QUE NO ES:");
			System.err.println("[ERROR] CAMIÓN " + camionCargando.getNumeroCamion() + " SE HA COLADO A CAMIÓN " + camion);
		}
		
	}
	
}
