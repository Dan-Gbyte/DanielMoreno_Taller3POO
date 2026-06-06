package logica;

import java.util.Scanner;

import dominio.Hechizo;

public interface Sistema {
	void leerMagos();
	void guardarMagos();
	void leerHechizos();
	void guardarHechizos();
	Hechizo buscarHechizo(String buscado);
	boolean agregarMago(String nombre);
	boolean modificarMago(String nombre);
	
	
	//proximos a modificar estan ahi para que sepamos donde van (faltan sus parametros)
	public void eliminarMago(Sistema sistema, Scanner entrada);
	void agregarHechizo(Sistema sistema, Scanner entrada);
	void modificarHechizo();
	void eliminarHechizo(Sistema sistema, Scanner entrada);
	
	/**
	 * Muestra todos los magos instanciados actualmente
	 */
	void mostrarMagos();
	public void mostrarHechizos();
	//despues agregamos los otros metodos aca como agregar mago agregar hechizo etc.
	boolean aprenderHechizo(String nomMago, int indice);
	//public int leerOpcionSegura(Scanner sc);
}
