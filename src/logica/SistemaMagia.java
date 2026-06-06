package logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import dominio.*;

public class SistemaMagia implements Sistema{
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
				
				String[] partesHechizos = partes[1].split("\\|"); //al parecer da error esta cosa sin los dos "\\"
				for (int i = 0; i < partesHechizos.length; i++) {
                    String nomHechizo = partesHechizos[i];
                    //System.out.println(nomHechizo);
                    Hechizo hechizoCatalogo = this.buscarHechizo(nomHechizo);
                    nuevoMago.agregarHechizo(hechizoCatalogo);
                }
				listMagos.add(nuevoMago);
			}	
		} catch (Exception e) {
			System.out.println("error al leer archivo.. "+ e.getMessage());
		}
	}

	@Override
	public void guardarMagos() {
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter("Magos.txt"))){
			for (Mago mago: listMagos) {
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
			System.out.println("error al leer archivo.. "+ e.getMessage());
		}
		
		
	}

	@Override
	public void guardarHechizos() {
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter("Hechizos.txt"))){
			
			for (Hechizo hechizo: listHechizos) {
				escritor.write(hechizo.getDatos());
				escritor.newLine();
			}
			
		}catch (Exception e) {
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
	public boolean agregarMago(String nomMago) { //ERROR, el mago empieza sin hechizos y eso no puede ser
		if (buscarMago(nomMago) == null) {
			Mago nuevoMago = new Mago (nomMago);
			listMagos.add(nuevoMago);
			return true;
		}
		return false;
	}

	public static void agregarMago(Sistema sistema,Scanner entrada) { //incompleto (creo)
		System.out.print("\nIngrese un nombre para el nuevo mago:");
		String nombre = entrada.nextLine();
		
		sistema.agregarMago(nombre);
		
		System.out.println(nombre + " necesita un hechizo inicial. Los hechizos existentes son: ");
		sistema.mostrarHechizos();
		boolean aux;
		do {
			System.out.print("Ingrese el hechizo inicial de " + nombre + ": ");
			int hechizo;
			do {
				hechizo = leerOpcionSegura(entrada) - 1;
			}while (hechizo == -1);
			
			
			aux = sistema.aprenderHechizo(nombre, hechizo);
		} while (aux == false);
		
	}

	@Override
	public boolean modificarMago(String nomMago) {
		return false;
	}

	
	@Override
	public void eliminarMago(Sistema sistema, Scanner entrada) {
		System.out.println("Magos registrados: \n");
		sistema.mostrarMagos();
		System.out.print("Ingrese el número del mago que quiere eliminar: ");
		
		int indice = leerOpcionSegura(entrada)-1;
		if (indice < listMagos.size()) {
			listMagos.remove(indice);
		}
	}

	@Override
	public  void agregarHechizo(Sistema sistema, Scanner entrada) {
		System.out.print("\nIngrese un nombre para el hechizo nuevo: ");
		String nombre = entrada.nextLine();
		
		System.out.println("\nIngrese el tipo de hechizo que será " + nombre + ": "); //no sé cómo seguir
		
		
	}


	@Override
	public void modificarHechizo() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void eliminarHechizo(Sistema sistema, Scanner entrada) {
		System.out.println("Los hechizos existentes son: ");
		sistema.mostrarHechizos();
		
		int hechizo;
		do {
			System.out.print("\nIngrese el numero del hechizo a eliminar: ");
			hechizo = leerOpcionSegura(entrada) - 1;
			if (hechizo == -1) {
				System.out.println("No existe ese hechizo...\n");
			}
		} while (hechizo == -1);
		
	}

	@Override
	public void mostrarMagos() {
		int n = 0;
		for (Mago mago: listMagos) {
			System.out.println(++n + ") " + mago.getNombre());
		}
	}
	
	@Override
	public void mostrarHechizos() {
		int n = 0;
		for (Hechizo hechizo: listHechizos) {
			System.out.println(++n + ") " + hechizo.getNombre());
		}
	}


	@Override
	public boolean aprenderHechizo(String nomMago, int indice) {
		//System.out.println(indice + " < " + listHechizos.size());
		if( indice < listHechizos.size()) {
			Mago aprendiz = buscarMago(nomMago);
			aprendiz.agregarHechizo(listHechizos.get(indice));
			return true;
		}
		
			System.out.println("No se pudo aprender el hechizo...\n");
		return false;
	}

	//@Override
	public static int leerOpcionSegura(Scanner sc) {
		try {
			int opcion = Integer.parseInt(sc.nextLine());
			return opcion;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}
	
}
