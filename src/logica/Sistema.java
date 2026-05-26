package logica;

import dominio.Hechizo;

public interface Sistema {
	void leerMagos();
	void leerHechizos();
	Hechizo buscarHechizo(String buscado);
	
	//despues agregamos los otros metodos aca como agregar mago agregar hechizo etc.
}
