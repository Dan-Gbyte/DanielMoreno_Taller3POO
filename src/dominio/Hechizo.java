package dominio;

public abstract class Hechizo implements Calculable {
	private String nombre;
	private String tipo;
	private int daño;
	
	public Hechizo(String nombre, String tipo, int daño) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.daño = daño;
		
	}
	
	//para modificar hechizos
	public abstract void modificarExtras(int ext1, int ext2);
	
	public String getNombre() {
		return nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public int getDaño() {
		return daño;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setDaño(int daño) {
		this.daño = daño;
	}
	
	public String getDatos() {
		String datos = this.nombre + ";" + tipo + ";" + daño;
		return datos;
	}
	
	
}
