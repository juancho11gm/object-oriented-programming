package co.edu.javeriana.vuelos.negocio;

import java.time.LocalDate;

public class Menor extends Pasajero {

	private boolean viajaSolo;
	
	public Menor(String identificacion, String nombre,LocalDate fecha,boolean viajaSolo) {
		super(identificacion, nombre,fecha);
		this.viajaSolo = viajaSolo;
		// TODO Auto-generated constructor stub
	}
	public boolean isViajaSolo() {
		return viajaSolo;
	}

	public void setViajaSolo(boolean viajaSolo) {
		this.viajaSolo = viajaSolo;
	}
	@Override
	public long calcularValorItinerario() {
		long valor=0;	
		for(Silla s:this.getListaSillas()){
			valor += s.getVueloEspecifico().getTarifa();
		}
		valor = (long) (valor-(0.35*valor));
		if(this.isViajaSolo() == true){
			valor += 90; //Chequear si se suma por cada vuelo o solo una vez;(Si el recargo se hace una vez, o si se hace por cada vuelo)
		}
		return valor;
		// TODO Auto-generated method stub
	}
	
	
	
	
}
