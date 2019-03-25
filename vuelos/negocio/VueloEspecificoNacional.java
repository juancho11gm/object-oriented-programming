package co.edu.javeriana.vuelos.negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VueloEspecificoNacional extends VueloEspecifico{
	private int iva;
	private long valorPasaje;
	public VueloEspecificoNacional(LocalDate fecha, String tipoAvion, int capacidad, VueloPlaneado vueloPlaneado,
			long tarifa,int iva,TipoAvion tipo) {
		super(fecha, tipoAvion, capacidad, vueloPlaneado, tarifa,tipo);
		this.valorPasaje=tarifa+((tarifa*iva)/100);
		this.iva=iva;
		// TODO Auto-generated constructor stub
	}
	
	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public String toString(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String fechaAux = fecha.format(formatter);
		return  String.format("%25d %-20s %-20s %-20d %-20d %-20d %-20d", codigo,fechaAux,tipoAvion,capacidad,cuposLibres,tarifa,iva);
	}
	public long calcularValor(){
		long valor=this.getTarifa();
		if(this.getFecha().getMonthValue()==6||this.getFecha().getMonthValue()==12){
			valor+=(valor*20)/100;
		}
		valor+=(valor*this.getIva())/100;
		return valor;
	}

}
