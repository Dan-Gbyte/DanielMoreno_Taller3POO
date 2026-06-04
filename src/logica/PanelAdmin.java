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
			agregarMago(sis, sc);
			break;
		case 2:
			break;
		case 3:
			eliminarMago(sis, sc);
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			eliminarHechizo(sis, sc);
			break;
		case 7:
			System.out.println("\nVolviendo al menú principal...\n");
			break;
		default:
			System.out.println("Error, opcion no valida...");
			break;
		}
	}
	public static int leerOpcionSegura(Scanner sc) {
		try {
			int opcion = Integer.parseInt(sc.nextLine());
			return opcion;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}
	
	public static void agregarMago(Sistema sistema,Scanner entrada) { //incompleto (creo)
		System.out.print("\nIngrese un nombre para el nuevo mago:");
		String nombre = entrada.nextLine();
		
		sistema.agregarMago(nombre);
		
		System.out.println(nombre + " necesita un hechizo inicial. Los hechizos existentes son: ");
		sistema.mostrarHechizos();
		boolean aux;
		do {
			System.out.print("Ingrese el hechizo inicial de " + nombre + ": ");
			int hechizo;
			do {
				hechizo = leerOpcionSegura(entrada) - 1;
			}while (hechizo == -1);
			
			
			aux = sistema.aprenderHechizo(nombre, hechizo);
		} while (aux == false);
		
	}

	public static void eliminarMago(Sistema sistema, Scanner entrada) {
		System.out.println("Magos registrados: \n");
		sistema.mostrarMagos();
		System.out.print("Ingrese el número del mago que quiere eliminar: ");
		
		sistema.eliminarMago(leerOpcionSegura(entrada)-1);
	}
	public static void agregarHechizo(Sistema sistema, Scanner entrada) {
		System.out.print("\nIngrese un nombre para el hechizo nuevo: ");
		String nombre = entrada.nextLine();
		
		System.out.println("\nIngrese el tipo de " + nombre + ": "); //no sé cómo seguir
		
	}
	public static void eliminarHechizo(Sistema sistema, Scanner entrada) {
		System.out.println("Los hechizos existentes son: ");
		sistema.mostrarHechizos();
		
		int hechizo;
		do {
			System.out.print("\nIngrese el numero del hechizo a eliminar: ");
			hechizo = leerOpcionSegura(entrada) - 1;
			if (hechizo == -1) {
				System.out.println("No existe ese hechizo...\n");
			}
		} while (hechizo == -1);
		
	}
}
