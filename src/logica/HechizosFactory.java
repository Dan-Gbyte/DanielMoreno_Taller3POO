package logica;

import dominio.Hechizo;
import dominio.HechizoAgua;
import dominio.HechizoFuego;
import dominio.HechizoPlanta;
import dominio.HechizoTierra;

/**
 * Clase fábrica que implementa el patrón Factory para la creación centralizada
 * de objetos pertenecientes a la jerarquía de Hechizos.
 */
public class HechizosFactory {
	
	/**
     * Procesa una cadena de texto estructurada y construye la subclase de Hechizo correspondiente.
     * Se encarga de parsear los atributos específicos de cada elemento mágico.
     * * @param linea Texto extraído del archivo con formato "Nombre;Tipo;Daño;AtributosExtra".
     * @return Una instancia de Hechizo (Fuego, Tierra, Planta o Agua) instanciada correctamente,
     * o null si el tipo especificado no es reconocido.
     * @throws NumberFormatException Si los valores numéricos de la cadena son inválidos.
     */
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
