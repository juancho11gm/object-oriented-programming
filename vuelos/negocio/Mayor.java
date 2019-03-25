package co.edu.javeriana.vuelos.negocio;

import java.time.LocalDate;

public class Mayor extends Pasajero {
	
	private boolean requiereAsistencia;
	
	public Mayor(String identificacion, String nombre,LocalDate fecha,boolean asistencia) {
		super(identificacion, nombre,fecha);
		this.requiereAsistencia = asistencia;
		// TODO Auto-generated constructor stub
	}

	public boolean isRequiereAsistencia() {
		return requiereAsistencia;
	}

	public void setRequiereAsistencia(boolean requiereAsistencia) {
		this.requiereAsistencia = requiereAsistencia;
	}

	@Override
	public long calcularValorItinerario() {
		long valor=0;
		for(Silla s:this.getListaSillas()){
			valor+= s.getVueloEspecifico().getTarifa();
		}
		
		if(this.requiereAsistencia==true){
			valor += 50; //Chequear si se suma por cada vuelo o solo una vez.
		}
		// TODO Auto-generated method stub
		return valor;
	}
	
	
}
