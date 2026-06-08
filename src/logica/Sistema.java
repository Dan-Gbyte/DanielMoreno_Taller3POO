package logica;

import java.util.Scanner;

import dominio.Hechizo;

public interface Sistema {
	void leerMagos();
	void leerHechizos();

	//persistencia
	void guardarMagos();
	void guardarHechizos();
	//Magos 
	boolean agregarMago(String nombre);    //booleanos para en panel admin verificar con ifs..
	boolean eliminarMago(int indice);
	public boolean modificarMago(int tipoCambio, int indice, String nuevoDato);
	String mostrarMagos();
	
	//hechizos
	boolean agregarHechizo(String lineaDatos); //Usaremos la Factory con esto, le damos la linea con los datos condadenada
	boolean eliminarHechizo(int indice);
	boolean modificarHechizo(int indice, int nuevoDano); // solo pasamos los datos
	String mostrarHechizos();
	
	Hechizo buscarHechizo(String buscado);
	boolean aprenderHechizo(String nomMago, int indice);
	
	//analista
	String obtenerTop10Hechizos();
    String obtenerTop3Magos();
    String mostrarTodosHechizosPuntuacion();
    String mostrarTodosMagosPuntuacion();
    

    Hechizo obtenerHechizo(int indice);
    String mostrarHechizosMago(int indice);
    boolean modificarHechizo(int indice, String nuevoNombre, int nuevoDano, int extra1, int extra2);
}
