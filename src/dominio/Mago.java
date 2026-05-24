package dominio;

import java.util.ArrayList;

public class Mago implements Calculable {
	private String nombre;
	private ArrayList<Hechizo> listHechizos;
	
	public Mago(String nombre) {
		this.nombre = nombre;
		this.listHechizos = new ArrayList();
	}
	
	public void agregarHechizo(Hechizo hechizo){
		listHechizos.add(hechizo);
	}

	@Override
	public double calcularPuntaje() {
	    double total = 0;
	    for (Hechizo hechizo : listHechizos) {
	        total += hechizo.calcularPuntaje(); 
	    }
	    return total;
	}

}
