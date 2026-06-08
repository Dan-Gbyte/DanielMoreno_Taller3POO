package dominio;

public class HechizoTierra extends Hechizo {
	private int mejoraDefensa;
	
	public HechizoTierra(String nombre, String tipo, int daño, int mejoraDefensa) {
		super(nombre, tipo, daño);
		this.mejoraDefensa = mejoraDefensa;
	}

	@Override
	public double calcularPuntaje() {
		return (super.getDaño()*this.mejoraDefensa)/2;
	}

	@Override
	public String getDatos() {
		String datos = super.getDatos() + ";" + this.mejoraDefensa;
		return datos;
	}

	@Override
	public void modificarExtras(int ext1, int ext2) {
		this.mejoraDefensa = ext1;
		
	}
	
}
