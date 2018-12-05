package ej4_2;

import java.util.concurrent.Semaphore;

public class Camion {
	
	final static private int NUM_CAMIONES_LADO_IZQ = 5;
	private int numeroCamion;
	private boolean cargado;
	private Puente puente;
	private Excavadora excavadora;
	private static Semaphore semaforoPuente = new Semaphore(NUM_CAMIONES_LADO_IZQ, true);
	
	/**
	 * Constructor camión.
	 * 
	 * @param numeroCamion
	 * @param puente
	 * @param excavadora
	 */
	
	
	public Camion(int numeroCamion, Puente puente, Excavadora excavadora) {
		this.numeroCamion = numeroCamion;
		this.puente = puente;
		this.excavadora = excavadora;
		cargado = false;
	}
	
	/**
	 * Inicia la rutina del camión.
	 * 
	 * @throws InterruptedException
	 */
	
	
	public void empezarRutina() throws InterruptedException {
		
		System.out.println("C_" + this.numeroCamion + " (1) - Esperando derecha para cruzar");
		semaforoPuente.acquire();
		puente.cruzar(this, cargado);
		System.out.println("C_" + this.numeroCamion + " (4) - Esperando izquierda para cargar");
		cargado = excavadora.cargar(this, cargado);
		System.out.println("C_" + this.numeroCamion + " (7) - Esperando izquierda para cruzar");
		puente.cruzar(this, cargado);
		semaforoPuente.release();
		System.out.println("C_" + this.numeroCamion + " (10) - Descargando camión.");
		cargado = false;
		
	}
	
	/**
	 * Devuelve el número de camión asignado.
	 * 
	 * @return
	 */
	
	
	public int getNumeroCamion() {
		return this.numeroCamion;
	}
	
}
