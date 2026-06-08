package dominio;

public class HechizoPlanta extends Hechizo {
	private int duracionStun;
	private int cantPlantas;
	
	public HechizoPlanta(String nombre, String tipo, int daño, int duracionStun, int cantPlantas) {
		super(nombre, tipo, daño);
		this.cantPlantas = cantPlantas;
		this.duracionStun = duracionStun;
	}

	@Override
	public double calcularPuntaje() {
		return super.getDaño()+(this.cantPlantas*this.duracionStun);
	}

	@Override
	public String getDatos() {
		String datos = super.getDatos() + ";" + this.duracionStun + "," + this.cantPlantas;
		return datos;
	}

	@Override
	public void modificarExtras(int ext1, int ext2) {
		this.duracionStun = ext1;
        this.cantPlantas = ext2;
	}
	
}
