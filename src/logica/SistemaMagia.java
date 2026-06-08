package logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import dominio.*;

public class SistemaMagia implements Sistema {
	private ArrayList<Hechizo> listHechizos;
	private ArrayList<Mago> listMagos;

	public SistemaMagia() {
		this.listHechizos = new ArrayList<Hechizo>();
		this.listMagos = new ArrayList<Mago>();
	}

	@Override
	public void leerMagos() {
		try {
			File f = new File("Magos.txt");
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String[] partes = sc.nextLine().split(";");
				String nombre = partes[0];
				Mago nuevoMago = new Mago(nombre);
				
				if (partes.length > 1 && !partes[1].trim().isEmpty()) {
					String[] partesHechizos = partes[1].split("\\|"); 
					for (int i = 0; i < partesHechizos.length; i++) {
						String nomHechizo = partesHechizos[i];
						Hechizo hechizoCatalogo = this.buscarHechizo(nomHechizo);
						if (hechizoCatalogo != null) {
							nuevoMago.agregarHechizo(hechizoCatalogo);
						}
					}
				}
				
				listMagos.add(nuevoMago);
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("error al leer archivo.. " + e.getMessage());
		}
	}

	@Override
	public void guardarMagos() {
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter("Magos.txt"))) {
			if (listMagos.isEmpty()) {
				return;
			}
			for (Mago mago : listMagos) {
				escritor.write(mago.getNombre() + ";" + mago.getRepertorio());
				escritor.newLine();
			}
		} catch (Exception e) {
			System.out.println("Hubo un error al guardar los magos..." + "\n\n ERROR: " + e.getMessage());
		}

	}

	@Override
	public void leerHechizos() {
		try {
			File f = new File("Hechizos.txt");
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String linea = sc.nextLine();
				Hechizo nuevoHechizo = HechizosFactory.crearHechizo(linea);

				listHechizos.add(nuevoHechizo);
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("error al leer archivo.. " + e.getMessage());
		}

	}

	@Override
	public void guardarHechizos() {
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter("Hechizos.txt"))) {

			for (Hechizo hechizo : listHechizos) {
				escritor.write(hechizo.getDatos());
				escritor.newLine();
			}

		} catch (Exception e) {
			System.out.println("Hubo un error al guardar los hechizos...");
		}

	}

	@Override
	public Hechizo buscarHechizo(String nomHechizo) {
		for (Hechizo hechizoCatalogo : listHechizos) {
			if (hechizoCatalogo.getNombre().equalsIgnoreCase(nomHechizo)) {
				return hechizoCatalogo;

			}
		}
		return null;
	}

	public Mago buscarMago(String nomMago) {

		for (int i = 0; i < listMagos.size(); i++) {
			if (nomMago.equals(this.listMagos.get(i).getNombre())) {
				return listMagos.get(i);
			}
		}
		return null;
	}

	@Override
	public String mostrarMagos() {

		if (listMagos.isEmpty()) {
			return "No hay magos registrados en el sistema..";
		}

		// Armamos un texto gigante con todos los magos
		String texto = "--- LISTA DE MAGOS ---\n";
		for (int i = 0; i < listMagos.size(); i++) {
			texto += i+1 + ") " + listMagos.get(i).getNombre() + "\n";
		}
		return texto;
	}

	@Override
	public boolean agregarMago(String nombre) {
		for (Mago m : listMagos) {
			if (m.getNombre().equalsIgnoreCase(nombre)) {
				return false; // Ya existe
			}
		}
		Mago nuevoMago = new Mago(nombre);
		listMagos.add(nuevoMago);
		return true;
	}

	@Override
	public boolean eliminarMago(int indice) {
		// Validamos que el Indice que nos dio el Panel..
		if (indice >= 0 && indice < listMagos.size()) {
			listMagos.remove(indice);
			return true;
		}
		return false;
	}

	@Override
	public boolean modificarMago(int tipoCambio, int indice, String nuevoDato) { // lo haré mas funcional
																	
		switch(tipoCambio) {
		case 1:
			if (indice >= 0 && indice < listMagos.size()) {
				// Ver que el nuevo nombre no lo tenga ya otro mago
				for (Mago m : listMagos) {
					if (m.getNombre().equalsIgnoreCase(nuevoDato)) {
						return false;
					}
				}

				listMagos.get(indice).setNombre(nuevoDato);
				return true;
			}
			break;
		case 2:
			Mago mago = listMagos.get(indice);
			Hechizo hechizo = buscarHechizo(nuevoDato);
			if (hechizo != null) {
			mago.agregarHechizo(hechizo);
			return true;
			}
			break;
		case 3:
			Mago mago_ = listMagos.get(indice);
			mago_.olvidarHechizo(nuevoDato);
			break;
		}
		return false;
	}

	@Override
	public boolean aprenderHechizo(String nomMago, int indice) {
		if (indice < listHechizos.size()) {
			Mago aprendiz = buscarMago(nomMago);
			aprendiz.agregarHechizo(listHechizos.get(indice));
			return true;
		}

		System.out.println("No se pudo aprender el hechizo...\n");
		return false;
	}

	@Override
	public boolean agregarHechizo(String lineaDatos) {
		try {
			Hechizo newHechizo = HechizosFactory.crearHechizo(lineaDatos); // ocupamos la linea que concadenamos en
																			// panel admin

			if (newHechizo == null) {
				return false;
			}
			if (this.buscarHechizo(newHechizo.getNombre()) != null) {// osea que esta duplicado
				return false;
			}

			listHechizos.add(newHechizo);
			return true;

		} catch (Exception e) {
			return false; // en caso de cualquier error
		}
	}

	@Override
	public boolean eliminarHechizo(int indice) {
		if (indice >= 0 && indice < listHechizos.size()) {
			listHechizos.remove(indice);
			return true;
		}
		return false;
	}

	@Override
	public boolean modificarHechizo(int indice, int nuevoDano) {
		if (indice >= 0 && indice < listHechizos.size()) {
			// Modificamos el atributo directamente en la referencia
			listHechizos.get(indice).setDaño(nuevoDano);// supongo que este metodo es para modificar el daño no dice
														// mucho el enunciado..
			return true;
		}
		return false;
	}

	@Override
	public String mostrarHechizos() {
		if (listHechizos.isEmpty()) {
			return "No hay hechizos registrados";
		}

		String texto = "--- CATÁLOGO DE HECHIZOS EXISTENTES ---\n";
		for (int i = 0; i < listHechizos.size(); i++) {
			Hechizo h = listHechizos.get(i);
			texto += i+1 + ") " + h.getNombre() + " | Tipo: " + h.getTipo() + " | Daño Base: " + h.getDaño() + "\n";
		}
		return texto;
	}

	//ANALISTA
	
	@Override
	public String mostrarTodosHechizosPuntuacion() {
		if (listHechizos.isEmpty()) {
			return "No hay hechizos registrados";
		}

		String texto = "--- HECHIZOS ---\n"
				+ " NOMBRE  |  PUNTUACIÓN \n";
		for (int i = 0; i < listHechizos.size(); i++) {
			Hechizo h = listHechizos.get(i);
			texto += i + ") " + h.getNombre() +  " | "  + h.calcularPuntaje() +"\n";
		}
		return texto;
	}

	@Override
	public String mostrarTodosMagosPuntuacion() {
		if (listMagos.isEmpty()) {
			return "No hay magos registrados en el sistema..";
		}

		// Armamos un texto gigante con todos los magos
		String texto = "--- LISTA DE MAGOS ---\n"
				+ "NOMBRE  |  PUNTUACIÓN \n";
		for (int i = 0; i < listMagos.size(); i++) {
			texto += i + ") " + listMagos.get(i).getNombre() + " | " + listMagos.get(i).calcularPuntaje() +"\n";
		}
		return texto;
	}

	@Override
	public String obtenerTop10Hechizos() {
		if(listMagos.isEmpty()) return "No hay magos registrados.";
	    ArrayList<Hechizo> copiaHechizos = new ArrayList<>(listHechizos);//Clonamos la lista para no desordenar el original
	    
	  //ordenamos
	    for (int i = 0; i < copiaHechizos.size(); i++) {
	    	for (int j = 0; j < copiaHechizos.size() - 1 - i ; j++) {
	    		double puntaje = copiaHechizos.get(j).calcularPuntaje();
	    		double sigPuntaje = copiaHechizos.get(j+1).calcularPuntaje();
	    		
	    		if (sigPuntaje > puntaje) {
	    			Hechizo aux = copiaHechizos.get(j);
	    			
	    			copiaHechizos.set(j, copiaHechizos.get(j+1));
	    			copiaHechizos.set(j+1, aux);
	    		}
	    	}
	    }
	    String texto = "\n--- TOP MEJORES HECHIZOS ---\n\n" //creamos el texto a retornar
	    		+ " NOMBRE | PUNTAJE\n";
	    
	    int limite = copiaHechizos.size();//revisamos cuantos hechizos hay
	    if (copiaHechizos.size() > 10) {limite = 10;}
	    
	    for ( int i = 0; i < limite; i++) {
	    	texto += i+1 + ") " + copiaHechizos.get(i).getNombre() + " | " + copiaHechizos.get(i).calcularPuntaje() + " puntos\n";
	    }
		return texto;  
	}

	@Override
	public String obtenerTop3Magos() {

		if(listMagos.isEmpty()) return "No hay magos registrados.";
	    ArrayList<Mago> copiaMagos = new ArrayList<>(listMagos);//Clonamos la lista para no desordenar el original
	    
	  //ordenamos
	    for (int i = 0; i < copiaMagos.size(); i++) {
	    	for (int j = 0; j < copiaMagos.size() - 1 - i ; j++) {
	    		double puntaje = copiaMagos.get(j).calcularPuntaje();
	    		double sigPuntaje = copiaMagos.get(j+1).calcularPuntaje();
	    		
	    		if (sigPuntaje > puntaje) {
	    			Mago aux = copiaMagos.get(j);
	    			
	    			copiaMagos.set(j, copiaMagos.get(j+1));
	    			copiaMagos.set(j+1, aux);
	    		}
	    	}
	    }
	    String texto = "\n--- TOP MAGOS ---\n\n";//creamos el texto a retornar
	    
	    int limite = copiaMagos.size();//revisamos cuantos magos hay
	    if (copiaMagos.size() > 3) {limite = 3;}
	    
	    for ( int i = 0; i < limite; i++) {
	    	texto += i+1 + "° lugar: " + copiaMagos.get(i).getNombre() + " con " + copiaMagos.get(i).calcularPuntaje() + " puntos\n";
	    }
		return texto; 
	}

	@Override
	public Hechizo obtenerHechizo(int indice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String mostrarHechizosMago(int indice) {
		// Validar 
		if (indice < 0 || indice >= listMagos.size()) {
			return "Mago no válido.";
		}
		
		Mago m = listMagos.get(indice);
		String textoRepertorio = m.getRepertorio();
		
		
		if (textoRepertorio == null || textoRepertorio.trim().isEmpty()) {
			return "Este mago no conoce ningún hechizo actualmente.";
		}
		
		// Si tiene hechizos le ponemos un titulo 
		return "--- HECHIZOS DE " + m.getNombre().toUpperCase() + " ---\n" + textoRepertorio;
	}

	@Override
	public boolean modificarHechizo(int indice, String nuevoNombre, int nuevoDano, int extra1, int extra2) {
		// TODO Auto-generated method stub
		return false;
	}
}
