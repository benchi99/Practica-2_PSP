package Practica_2_PSP.ej4_1;

import java.util.concurrent.Semaphore;

public class Camion {
	
	private int numeroCamion;
	private boolean cargado;
	private Puente puente;
	private Excavadora excavadora;
	//DEBE ser final y est�tico para que sea com�n entre todas las instancias de cami�n!!!
	private final static Semaphore semaforoPuente = new Semaphore(1);
	
	/**
	 * Constructor cami�n.
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
	 * Inicia la rutina del cami�n.
	 * 
	 * @throws InterruptedException
	 */
	
	public void empezarRutina() throws InterruptedException {
		
		System.out.println("C_" + this.numeroCamion + " (1) - Esperando derecha para cruzar");
		semaforoPuente.acquire(1);
		puente.cruzar(this, cargado);
		System.out.println("C_" + this.numeroCamion + " (4) - Esperando izquierda para cargar");
		cargado = excavadora.cargar(this, cargado);
		System.out.println("C_" + this.numeroCamion + " (7) - Esperando izquierda para cruzar");
		puente.cruzar(this, cargado);
		semaforoPuente.release(1);
		System.out.println("C_" + this.numeroCamion + " (10) - Descargando cami�n.");
		cargado = false;
		
	}
	
	/**
	 * Devuelve el n�mero de cami�n asignado.
	 * 
	 * @return
	 */
	
	public int getNumeroCamion() {
		return this.numeroCamion;
	}
	
}
