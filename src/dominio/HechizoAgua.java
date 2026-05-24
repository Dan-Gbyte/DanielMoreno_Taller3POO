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

}
