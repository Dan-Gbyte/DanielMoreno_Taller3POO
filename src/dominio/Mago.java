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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRepertorio() {
		if (listHechizos.isEmpty()) {
			return " ";
		}
		
		String repertorio = listHechizos.get(0).getNombre();
		for (int i = 1; i < listHechizos.size(); i++) {
			repertorio = repertorio + "| tipo:" + listHechizos.get(0).getTipo()+ "| daño base:" + listHechizos.get(0).getDaño();
		}
		return repertorio;
	}
	
	public boolean olvidarHechizo(String nombreHechizo) {
		
		for (int i = 0; i < listHechizos.size(); i++) {
			if (listHechizos.get(i).getNombre().equalsIgnoreCase(nombreHechizo)) {
				listHechizos.remove(i);
				return true;
			}
		}
		
		return false;
	}


	
}
