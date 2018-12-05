package ej3;

public class SistemaDeRedNoHilos {
	
	public static void main(String[] args) {
		
		final String PASSWORD = "sadf";
		
		long tiempoComienzo = System.currentTimeMillis();
		
		StringBuilder construyePosiblePassword;

		char[] caracteres = new char[4];
		
		for (int i = 97; i <= 122; i++) {
			char c1 = (char) i;
			
			for (int j = 97; j <= 122; j++) {
				char c2 = (char) j;
				
				for (int k = 97; k <= 122; k++) {
					char c3 = (char) k;
					
					for (int l = 97; l <= 122; l++) {
						char c4 = (char) l;
						
						caracteres[0] = c1;
						caracteres[1] = c2;
						caracteres[2] = c3;
						caracteres[3] = c4;
						
						construyePosiblePassword = new StringBuilder();
						construyePosiblePassword.append(caracteres);
						
						String posiblePassword = construyePosiblePassword.toString();
						
						System.out.println("Probando: " + posiblePassword);
						
						if(posiblePassword.equals(PASSWORD)) {
							System.out.println("CONTRASEÑA ENCONTRADA -->> " + posiblePassword + "<<--");
							System.out.println("Tiempo necesitado: " + (double)(System.currentTimeMillis() - tiempoComienzo) / 1000);
							System.exit(0);
						} else {
							System.out.println("CONTRASEÑA NO ENCONTRADA.");
						}
					}
				}
			}
			
		}
		
	}
	
}
