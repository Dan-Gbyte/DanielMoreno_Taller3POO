package logica;

import dominio.Hechizo;

public interface Sistema {
	void leerMagos();
	void leerHechizos();
	Hechizo buscarHechizo(String buscado);
	boolean agregarMago(String nombre);
	boolean modificarMago(String nombre);
	
	
	//proximos a modificar estan ahi para que sepamos donde van (faltan sus parametros)
	void eliminarMago(int indice);
	void agregarHechizo();
	void modificarHechizo();
	void eliminarHechizo();
	
	/**
	 * Muestra todos los magos instanciados actualmente
	 */
	void mostrarMagos();
	public void mostrarHechizos();
	//despues agregamos los otros metodos aca como agregar mago agregar hechizo etc.
	boolean aprenderHechizo(String nomMago, int indice);
}
