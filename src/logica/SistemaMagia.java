package logica;

import java.io.File;
import java.io.FileNotFoundException;
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


	@Override
	public boolean modificarMago(String nomMago) {
		return false;
	}


	
	
	@Override
	public void eliminarMago(int indice) {
		if (indice < listMagos.size()) {
			listMagos.remove(indice);
		}
		
	}


	@Override
	public void agregarHechizo() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void modificarHechizo() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void eliminarHechizo() {
		// TODO Auto-generated method stub
		
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
}
