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
                    System.out.println(nomHechizo);
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

}
