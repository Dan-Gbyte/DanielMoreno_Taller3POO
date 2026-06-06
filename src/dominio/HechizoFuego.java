package dominio;

public class HechizoFuego extends Hechizo {
	private int duracionQuemadura;
	
	public HechizoFuego(String nombre, String tipo, int daño, int duracionQuemadura) {
		super(nombre, tipo, daño);
		this.duracionQuemadura = duracionQuemadura;
	}

	@Override
	public double calcularPuntaje() {
		return super.getDaño()*this.duracionQuemadura;
		
	}

	@Override
	public String getDatos() {
		String datos = this.getDatos() + ";" + this.duracionQuemadura;
		return datos;
	}
}
