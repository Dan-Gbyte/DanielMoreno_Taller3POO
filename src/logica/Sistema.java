package logica;

import dominio.Hechizo;

public interface Sistema {
	void leerMagos();
	void leerHechizos();
	Hechizo buscarHechizo(String buscado);
	boolean agregarMago(String nombre);
	boolean modificarMago(String nombre);
	
	//proximos a modificar estan ahi para que sepamos donde van (faltan sus parametros)
	void eliminarMago();
	void agregarHechizo();
	void modificarHechizo();
	void eliminarHechizo();
	
	
	//despues agregamos los otros metodos aca como agregar mago agregar hechizo etc.
}
