package dominio;

import java.util.ArrayList;

public class Mago implements Calculable {
	private String nombre;
	private ArrayList<Hechizo> listHechizos;
	
	public Mago(String nombre) {
		this.nombre = nombre;
		this.listHechizos = new ArrayList<Hechizo>();
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

	public String getNombre() {
		return nombre;
	}

	//tenia una idea para esto pero me arrepentí, igual puede servir
	/*public boolean comparar(Mago mago) {
		if (this.nombre.equals(mago.getNombre())) {
			return true;
		}
		return false;
	}*/ 
	
	public String getRepertorio() {
		String repertorio = listHechizos.get(0).getNombre();
		for (int i = 1; i < listHechizos.size(); i++) {
			repertorio = repertorio + "|" + listHechizos.get(0).getNombre();
		}
		return repertorio;
	}
}
