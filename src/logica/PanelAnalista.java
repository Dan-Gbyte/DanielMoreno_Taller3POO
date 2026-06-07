package logica;

import java.util.Scanner;

public class PanelAnalista {
	public static void iniciarPanelAnalista(SistemaMagia sis, Scanner sc ) {
		int opcion = 0;
        do {
            System.out.println("\nPANEL ANALISTA");
            System.out.println("1. Top 10 Mejores Hechizos");
            System.out.println("2. Top 3 Mejores Magos");
            System.out.println("3. Mostrar todos los Hechizos");
            System.out.println("4. Mostrar todos los magos");
            System.out.println("5. Mostrar todos los Hechizos junto a su puntuacion");
            System.out.println("6. Mostrar todos los magos junto a su puntuacion");
            System.out.println("7. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = HerramientasConsola.leerOpcionSegura(sc);
                procesarOpcion(opcion, sis, sc);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        } while (opcion != 7);
	}
	
	public static void procesarOpcion(int opcion, SistemaMagia sis, Scanner sc) {
		switch (opcion) {
		case 1:
			
			break;
		case 2:
			break;
		case 3:
			
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			
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
