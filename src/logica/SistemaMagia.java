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

				String[] partesHechizos = partes[1].split("\\|"); // al parecer da error esta cosa sin los dos "\\"
				for (int i = 0; i < partesHechizos.length; i++) {
					String nomHechizo = partesHechizos[i];
					// System.out.println(nomHechizo);
					Hechizo hechizoCatalogo = this.buscarHechizo(nomHechizo);
					nuevoMago.agregarHechizo(hechizoCatalogo);
				}
				listMagos.add(nuevoMago);
			}
		} catch (Exception e) {
			System.out.println("error al leer archivo.. " + e.getMessage());
		}
	}

	@Override
	public void guardarMagos() {
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter("Magos.txt"))) {
			for (Mago mago : listMagos) {
				escritor.write(mago.getNombre() + ";" + mago.getRepertorio());
				escritor.newLine();
			}
		} catch (Exception e) {
			System.out.println("Hubo un error al guardar los magos...");
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
			if (hechizoCatalogo.getNombre().equals(nomHechizo)) {
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

		// Armamos un texto gigante con todos los magos, NO usamos System.out.println
		String texto = "--- LISTA DE MAGOS ---\n";
		for (int i = 0; i < listMagos.size(); i++) {
			texto += i + ") " + listMagos.get(i).getNombre() + "\n";
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
	public boolean modificarMago(int indice, String nuevoNombre) { // no se que mas modificar de un mago.. sus hechizos
																	// enunciado ambiguo
		if (indice >= 0 && indice < listMagos.size()) {
			// Ver que el nuevo nombre no lo tenga ya otro mago
			for (Mago m : listMagos) {
				if (m.getNombre().equalsIgnoreCase(nuevoNombre)) {
					return false;
				}
			}

			listMagos.get(indice).setNombre(nuevoNombre);
			return true;
		}
		return false;
	}

	@Override
	public boolean aprenderHechizo(String nomMago, int indice) {
		// System.out.println(indice + " < " + listHechizos.size());
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
			texto += i + ") " + h.getNombre() + " Tipo: " + h.getTipo() + " Daño Base: " + h.getDaño() + "\n";
		}
		return texto;
	}

	//ANALISTA
	
	@Override
	public String mostrarTodosHechizosPuntuacion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String mostrarTodosMagosPuntuacion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtenerTop10Hechizos() {
		if(listMagos.isEmpty()) return "No hay magos registrados.";

	    
	    ArrayList<Mago> copiaMagos = new ArrayList<>(listMagos);//Clonamos la lista para no desordenar el original

	    //Ordenamos de mayor a menor usando la interfaz Calculable.. no quiero ocupar el burbuja 
	    

	    //Armamos el String solo con los 3 primeros

	    
	    return null; //texto con los 3 primeros;
	}

	@Override
	public String obtenerTop3Magos() {
		// TODO Auto-generated method stub
		return null;
	}
}
