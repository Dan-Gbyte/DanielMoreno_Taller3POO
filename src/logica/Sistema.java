package logica;

import java.util.Scanner;

import dominio.Hechizo;

/**
 * Interfaz principal del sistema que define el contrato de operaciones.
 * Establece los requerimientos para la gestión (CRUD), persistencia 
 * y análisis estadístico de entidades mágicas (Magos y Hechizos).
 */
public interface Sistema {
	/**
     * Carga la información de los magos desde el archivo de texto correspondiente.
     */
	void leerMagos();
	/**
     * Carga el catálogo de hechizos desde el archivo de texto correspondiente.
     */
	void leerHechizos();

	//persistencia
	
	/**
     * Guarda el estado actual de la lista de magos sobrescribiendo el archivo de texto.
     */
	void guardarMagos();
	/**
     * Guarda el estado actual del catálogo de hechizos sobrescribiendo el archivo de texto.
     */
	void guardarHechizos();
	
	
	//Magos 
	
	/**
     * Agrega un nuevo mago al sistema verificando que su nombre no esté duplicado.
     * @param nombre El nombre del nuevo mago a registrar.
     * @return true si se agregó exitosamente, false si ya existe un mago con ese nombre.
     */
	boolean agregarMago(String nombre);    //booleanos para en panel admin verificar con ifs..
	
	/**
     * Elimina un mago del sistema en base a su índice en la lista.
     * @param indice La posición del mago en la colección.
     * @return true si la eliminación fue exitosa, false si el índice es inválido.
     */
	boolean eliminarMago(int indice);
	
	/**
     * Modifica los atributos o el repertorio de un mago específico.
     * @param tipoCambio El tipo de modificación (1: Nombre, 2: Aprender, 3: Olvidar).
     * @param indice La posición del mago en la colección.
     * @param nuevoDato El nuevo valor a asignar o el nombre del hechizo a aprender/olvidar.
     * @return true si la modificación se realizó correctamente, false en caso contrario.
     */
	public boolean modificarMago(int tipoCambio, int indice, String nuevoDato);
	
	/**
     * Retorna una representación en texto de todos los magos registrados.
     * @return Un String formateado con la lista de magos.
     */
	String mostrarMagos();
	
	/**
     * Muestra el repertorio detallado de hechizos que conoce un mago específico.
     * @param indice Índice del mago en la colección.
     * @return String formateado con los hechizos aprendidos por el mago.
     */
    String mostrarHechizosMago(int indice);
    
    
	//hechizos
	
	
	/**
     * Crea y añade un nuevo hechizo al catálogo global a partir de una línea de texto.
     * @param lineaDatos Texto con el formato "Nombre;Tipo;Daño;Extra1[,Extra2]".
     * @return true si se agregó exitosamente, false si hay errores de formato o duplicados.
     */
	boolean agregarHechizo(String lineaDatos); //Usaremos la Factory con esto, le damos la linea con los datos condadenada
	
	/**
     * Elimina un hechizo del catálogo global y del repertorio de todos los magos (Eliminación en cascada).
     * @param indice La posición del hechizo en el catálogo.
     * @return true si se eliminó con éxito, false si el índice es inválido.
     */
	boolean eliminarHechizo(int indice);
	
	/**
     * Modifica el daño base de un hechizo.
     * @param indice Posición del hechizo.
     * @param nuevoDano Nuevo valor de daño base.
     * @return true si la modificación fue exitosa.
     */
	boolean modificarHechizo(int indice, int nuevoDano); // solo pasamos los datos
	
	/**
     * Retorna una representación en texto de todo el catálogo de hechizos.
     * @return String formateado con los detalles básicos de los hechizos.
     */
	String mostrarHechizos();
	
	/**
     * Busca un hechizo en el catálogo por su nombre exacto.
     * @param buscado El nombre del hechizo a buscar.
     * @return El objeto Hechizo encontrado, o null si no existe.
     */
	Hechizo buscarHechizo(String buscado);
	
	/**
     * Permite a un mago aprender un hechizo basándose en el índice del catálogo.
     * @param nomMago Nombre del mago que aprenderá el hechizo.
     * @param indice Índice del hechizo en el catálogo global.
     * @return true si el aprendizaje fue exitoso.
     */
	boolean aprenderHechizo(String nomMago, int indice);
	
	//analista
	
	/**
     * Analiza y retorna los 10 mejores hechizos basados en su cálculo de puntuación.
     * @return String con el Top 10 ordenado de mayor a menor puntaje.
     */
	String obtenerTop10Hechizos();
	
	/**
     * Analiza y retorna los 3 mejores magos basados en la suma de las puntuaciones de sus hechizos.
     * @return String con el Top 3 ordenado de mayor a menor puntaje.
     */
    String obtenerTop3Magos();
    
    /**
     * Genera un listado de todos los hechizos con sus respectivas puntuaciones calculadas.
     * @return String formateado con nombres y puntuaciones.
     */
    String mostrarTodosHechizosPuntuacion();
    
    /**
     * Genera un listado de todos los magos con sus respectivas puntuaciones totales calculadas.
     * @return String formateado con nombres y puntuaciones totales.
     */
    String mostrarTodosMagosPuntuacion();
    
    /**
     * Obtiene la referencia directa a un objeto Hechizo del catálogo.
     * @param indice La posición del hechizo.
     * @return El objeto Hechizo correspondiente.
     */
    Hechizo obtenerHechizo(int indice);
    
    /**
     * Modifica los atributos de un hechizo existente.
     * @param indice Posición del hechizo en el catálogo.
     * @param nuevoNombre El nuevo nombre del hechizo.
     * @param nuevoDano El nuevo valor de daño base.
     * @param extra1 El primer atributo adicional modificado según el tipo de elemento.
     * @param extra2 El segundo atributo adicional modificado (si aplica).
     * @return true si la modificación se completó con éxito.
     */
    boolean modificarHechizo(int indice, String nuevoNombre, int nuevoDano, int extra1, int extra2);
}
