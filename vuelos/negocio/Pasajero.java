package co.edu.javeriana.vuelos.negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Pasajero {
	protected String identificacion;
	protected String nombre;
	protected LocalDate fechaNacimiento; // Se agrega este atributo
	private long valorItinerario;
	private ArrayList<Silla> listaSillas; //Relacion


	public Pasajero(String identificacion, String nombre,LocalDate fecha) {
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.fechaNacimiento = fecha;
		this.valorItinerario = 0;
		this.listaSillas=new ArrayList<>();
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Silla> getListaSillas() {
		return listaSillas;
	}
	public void setListaSillas(ArrayList<Silla> listaSillas) {
		this.listaSillas = listaSillas;
	}
	public String toString(){
		LocalDate localDate = this.fechaNacimiento;//For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String fecha = localDate.format(formatter);
		return String.format("%-20s %-20s %-20s", identificacion,nombre,fecha);
	}
	/**
	 * Recibe un objeto Silla y lo agrega a la lista de sillas del pasajero.
	 * @param silla
	 */
	public void agregarSilla(Silla silla){
		this.listaSillas.add(silla);
	}
	public abstract long calcularValorItinerario();
}
