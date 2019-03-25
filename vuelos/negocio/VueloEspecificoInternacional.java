package co.edu.javeriana.vuelos.negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VueloEspecificoInternacional extends VueloEspecifico {
	private long impuestoSalida;
	private long valorPasaje;
	public VueloEspecificoInternacional(LocalDate fecha, String tipoAvion, int capacidad, VueloPlaneado vueloPlaneado,
			long tarifa,long impuestoSalida,TipoAvion tipo) {
		super(fecha, tipoAvion, capacidad, vueloPlaneado,tarifa,tipo);
		// TODO Auto-generated constructor stub
		this.impuestoSalida=impuestoSalida;
		this.valorPasaje=tarifa+((tarifa*impuestoSalida)/100);	
		}
	
	public long getImpuestoSalida() {
		return impuestoSalida;
	}

	public void setImpuestoSalida(long impuestoSalida) {
		this.impuestoSalida = impuestoSalida;
	}

	public String toString(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String fechaAux = fecha.format(formatter);
		return  String.format("%25d %-20s %-20s %-20d %-20d %-20d %-20d", codigo,fechaAux,tipoAvion,capacidad,cuposLibres,tarifa,impuestoSalida);
	}
	public long calcularValor(){
		long valor=this.getTarifa();
		if(this.getFecha().getMonthValue()==6||this.getFecha().getMonthValue()==12){
			valor+=(valor*20)/100;
		}
		valor+=(valor*this.getImpuestoSalida())/100;
		return valor;
		
	}
}
