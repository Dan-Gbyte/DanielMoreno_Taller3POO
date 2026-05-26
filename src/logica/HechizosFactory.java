package logica;

import dominio.Hechizo;
import dominio.HechizoAgua;
import dominio.HechizoFuego;
import dominio.HechizoPlanta;
import dominio.HechizoTierra;

public class HechizosFactory {
	
	public static Hechizo crearHechizo(String linea) {
		String[] partes = linea.split(";");
		String nombre = partes[0];
		String tipo = partes[1];
		int daño = Integer.parseInt(partes[2]);
		
		switch (tipo) {
		case "Fuego":
			int duracionQuem = Integer.parseInt(partes[3]);
			return new HechizoFuego(nombre, tipo, daño, duracionQuem);
			
		case "Tierra":
			int mejoraDef = Integer.parseInt(partes[3]);
			return new HechizoTierra(nombre, tipo, daño, mejoraDef);
		
		case "Planta":
			String[] partesPlant = partes[3].split(",");
			int duracionStun = Integer.parseInt(partesPlant[0]);
			int cantidadPlant = Integer.parseInt(partesPlant[1]);
			return new HechizoPlanta(nombre, tipo, daño, duracionStun, cantidadPlant);
					
		case "Agua":
			String[] partesAgua = partes[3].split(",");
			int cantHeal = Integer.parseInt(partesAgua[0]);
			int presionAgua = Integer.parseInt(partesAgua[1]);
			return new HechizoAgua(nombre, tipo, daño, presionAgua, cantHeal);
			
		default:
			return null;
		}
		
		
	}
	
}
