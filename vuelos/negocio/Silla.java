package co.edu.javeriana.vuelos.negocio;

public class Silla {
	private String id;
	private boolean comprada;
	private VueloEspecifico vueloEspecifico;//Relacion
	private Pasajero pasajero;//Relacion

	public Silla(String id,boolean comprada,VueloEspecifico vueloEspecifico){
		this.id = id;
		this.comprada = comprada;
		this.vueloEspecifico = vueloEspecifico;
		this.pasajero = null;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isComprada() {
		return comprada;
	}
	public void setComprada(boolean comprada) {
		this.comprada = comprada;
	}
	public VueloEspecifico getVueloEspecifico() {
		return vueloEspecifico;
	}
	public void setVueloEspecifico(VueloEspecifico vueloEspecifico) {
		this.vueloEspecifico = vueloEspecifico;
	}
	public Pasajero getPasajero() {
		return pasajero;
	}
	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}

}
