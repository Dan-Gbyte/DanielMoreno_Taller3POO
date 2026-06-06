package logica;

import java.util.Scanner;

public class PanelAdmin {
	public static void iniciarPanelA(SistemaMagia sis, Scanner sc ) {
		int opcion = 0;
        do {
            System.out.println("\nPANEL ADMINISTRADOR");
            System.out.println("1. Agregar Mago");
            System.out.println("2. Modificar Mago");
            System.out.println("3. Eliminar Mago");
            System.out.println("4. Agregar Hechizo");
            System.out.println("5. Modificar Hechizo");
            System.out.println("6. Eliminar Hechizo");
            System.out.println("7. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());
                procesarOpcion(opcion, sis, sc);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        } while (opcion != 7);
	}
	
	
	public static void procesarOpcion(int opcion, SistemaMagia sis, Scanner sc) {
		switch (opcion) {
		case 1:
			sis.agregarMago(sis, sc);
			sis.guardarMagos();
			break;
		case 2:
			break;
		case 3:
			sis.eliminarMago(sis, sc);
			sis.guardarMagos();
			break;
		case 4:
			sis.agregarHechizo(sis, sc);
			break;
		case 5:
			break;
		case 6:
			sis.eliminarHechizo(sis, sc);
			sis.guardarHechizos();
			break;
		case 7:
			System.out.println("\nVolviendo al menú principal...\n");
			break;
		default:
			System.out.println("Error, opcion no valida...");
			break;
		}
	}
	
	
}
