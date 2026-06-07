package logica;

import java.util.Scanner;

public class HerramientasConsola {
	//asi si podemos ocupar este metodo en los dos paneles, ademas dejamos esta clase para futuras herramientas
	public static int leerOpcionSegura(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error de input..");
        }
        return -1;
    }
}
