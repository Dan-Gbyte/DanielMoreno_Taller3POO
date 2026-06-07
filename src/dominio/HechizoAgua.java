package dominio;

public class HechizoAgua extends Hechizo {
	private int cantidadHeal;
	private int presionAgua;
	
	public HechizoAgua(String nombre, String tipo, int daño, int presionAgua, int cantidadHeal) {
		super(nombre, tipo, daño);
		this.cantidadHeal = cantidadHeal;
		this.presionAgua = presionAgua;
		
	}

	@Override
	public double calcularPuntaje() {
		return (super.getDaño()+this.cantidadHeal+this.presionAgua)*2;
	}

	@Override
	public String getDatos() {
		String datos = super.getDatos() + ";" + this.cantidadHeal + "," + this.presionAgua;
		return datos;
	}

	@Override
	public String toString() {
		return "Nombre: " + this.getNombre() + " | Tipo: " + this.getTipo() + " | Daño: "+ this.getDaño() + " | Healing: "+ cantidadHeal + " | Presión del agua: " + presionAgua + "\n";
	}

	
}
