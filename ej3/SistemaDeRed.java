package ej3;

import java.util.ArrayList;

public class SistemaDeRed {
	
	final String PASSWORD = "fsdj";
	StringBuilder strb = new StringBuilder();
	boolean cortar = false;
	ArrayList<SistemaDeRed.RevientaPass> hilos = new ArrayList<SistemaDeRed.RevientaPass>();
	
	public static void main(String[] args) {
		
		new SistemaDeRed().empezar();
		
	}
	
	public void empezar() {

		for (int i = 97; i < 122; i++) {
			
			RevientaPass reventar = new RevientaPass((char) i);
			
			hilos.add(reventar);
			hilos.get(i - 97).start();
		}

	}
	
	public void claveOk(String clave) {
		
		if (clave.equals(PASSWORD)) {
			cortar = true;
			System.out.println("CONTRASEÑA ENCONTRADA. " + clave + " ES LA CONTRASEÑA!");
			for (int i = 0; i < hilos.size(); i++) {
				hilos.get(i).fin();
			}
		} else {
			System.out.println("INTENTO FALLIDO. PROBADO " + clave + ".");
		}
		
	}
	
	
	class RevientaPass extends Thread {

		boolean acabar = false;
		private char[] arrayChars = new char[4];
		
		public RevientaPass(char caracterAtaco) {
			arrayChars[0] = caracterAtaco;
		}
		
		public void fin() {
			acabar = true;
		}
		
		public void run() {
			
			if (!acabar) {
				for (int i = 97; i < 122; i++) {
					char dos = (char) i;
					for (int j = 97; j < 122; j++) {
						char tres = (char) j;
						for (int k = 97; k < 122; k++) {
							char cuatro = (char) k;
							
							arrayChars[1] = dos;
							arrayChars[2] = tres;
							arrayChars[3] = cuatro;
							
							synchronized(strb) {
								strb = new StringBuilder();
								strb.append(arrayChars);
								String posibleClave = strb.toString();
								System.out.println("[ATACADOR " + posibleClave.charAt(0) + "] ¡ATACANDO!");
								claveOk(posibleClave);
							}
							
						}
					}
				}
			} else {
				System.out.println("CONTRASEÑA ENCONTRADA.");
			}
		}
		
	}
	
}