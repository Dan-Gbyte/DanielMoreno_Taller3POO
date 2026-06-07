package logica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		SistemaMagia sistema = new SistemaMagia();
		sistema.leerHechizos();
		sistema.leerMagos();

		Scanner entrada = new Scanner(System.in);
		
		int menu = 0;
		do {
			System.out.print("\n\n---BIENVENIDO---\n"
					+ "\n¿A cuál menú quiere ingresar?"
					+ "\n1) Menú de administrador"
					+ "\n2) Menú de análisis"
					+ "\n3) Salir"
					+ "\n\nIngrese una opción: ");
			
			menu = HerramientasConsola.leerOpcionSegura(entrada);
			switch (menu) {
				case 1:
					PanelAdmin.iniciarPanelA(sistema, entrada);
					break;
				case 2:
					PanelAnalista.iniciarPanelAnalista(sistema, entrada);
					break;
				case 3:
					System.out.println("Programa Finalizado.");
					break;
				default:
					System.out.println("Por favor ingrese una opcion válida");
					break;
			}
		} while (menu != 3);
		
		
	}

	
}
