package co.edu.javeriana.vuelos.negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class VueloEspecifico {
	private static long CONSECUTIVO = 0;
	protected long codigo;
	protected LocalDate fecha;
	protected String tipoAvion;
	protected int capacidad;
	protected int cuposLibres;
	protected long tarifa;
	protected TipoAvion tipo;
	private VueloPlaneado vueloPlaneado;//Relaciones
	private List<Trayecto> listaTrayectos;//Relaciones
	private List<Silla> listaSillas;//Relaciones

	public VueloEspecifico(LocalDate fecha,String tipoAvion,int capacidad,VueloPlaneado vueloPlaneado,long tarifa,TipoAvion tipo){
		CONSECUTIVO++;
		this.tipo=tipo;
		this.codigo = CONSECUTIVO;
		this.fecha = fecha;
		this.tipoAvion = tipoAvion;
		this.capacidad = capacidad;
		this.cuposLibres=capacidad;
		this.tarifa = tarifa;
		this.vueloPlaneado = vueloPlaneado;
		this.listaTrayectos = new ArrayList<Trayecto>();
		this.listaSillas = new ArrayList<Silla>();

		int num = 1;
		int cont =0;
		char letter = 'A';
		String numero;
		String letra;
		String id;
		for(int i=0;i<capacidad;i++){
			if(letter > 'E'){
				letter = 'A';
			}
			if(cont==5){
				num++;
				cont=0;
			}
			numero = Integer.toString(num);
			letra = Character.toString(letter);
			id = numero.concat(letra);
			this.agregarSilla(id, false);
			cont++;
			letter++;
		}
	}

	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getTipoAvion() {
		return tipoAvion;
	}
	public void setTipoAvion(String tipoAvion) {
		this.tipoAvion = tipoAvion;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public int getCuposLibres() {
		return cuposLibres;
	}
	public void setCuposLibres(int cuposLibres) {
		this.cuposLibres = cuposLibres;
	}
	public long getTarifa() {
		return tarifa;
	}
	public void setTarifa(long tarifa) {
		this.tarifa = tarifa;
	}
	public VueloPlaneado getVueloPlaneado() {
		return vueloPlaneado;
	}
	public List<Trayecto> getListaTrayectos() {
		return listaTrayectos;
	}
	public void setListaTrayectos(ArrayList<Trayecto> listaTrayectos) {
		this.listaTrayectos = listaTrayectos;
	}
	public List<Silla> getListaSillas() {
		return listaSillas;
	}
	public void setListaSillas(ArrayList<Silla> listaSillas) {
		this.listaSillas = listaSillas;
	}
	public void setVueloPlaneado(VueloPlaneado vueloPlaneado) {
		this.vueloPlaneado = vueloPlaneado;
	}
	public abstract String toString();
	
	public long getCodigo() {
		return codigo;
	}
	/**
	 * Recibe el la informacion de una silla a instanciar
	 * Intancia un objeto silla.
	 * Añade la silla a la lista de sillas del VueloEspecifico.
	 * @param id
	 * @param comprada
	 */
	public void agregarSilla(String id,boolean comprada){
		Silla silla = new Silla(id,comprada,this);
		this.listaSillas.add(silla);
	}
	/**
	 * Recibe el codigo de una ciudad de origen, el codigo de una ciudad destino de un vuelo y una fecha.
	 * Verifica que el codigo de origen, el codigo de destino y la fecha recibidas sean iguales a los atributos del vueloPlaneado asociado al VueloEspecifico.
	 * Si se cumple la condicion, se retorna el VueloEspecifico.
	 * @param codigoOrigen
	 * @param codigoDestino
	 * @param fecha
	 * @return
	 */
	public VueloEspecifico verificarVueloEspecifico(long codigoOrigen, long codigoDestino,LocalDate fecha){
		Scanner scann=new Scanner(System.in);
		if(this.vueloPlaneado.getOrigen().getCodigo()==codigoOrigen&&this.vueloPlaneado.getDestino().getCodigo()==codigoDestino&&this.fecha.equals(fecha)){
			return this;
		}
		return null;
	}
	/**
	 * Recibe un objeto Trayecto como parametro.
	 * Al objeto trayecto le asigna como atributo de VueloEspecifico el objeto de VueloEspecifico actual.
	 * Añade el trayecto a la lista de trayectos del VueloEspecifico.
	 * @param trayecto
	 */
	public void agregarTrayecto(Trayecto trayecto){
		trayecto.setVueloEspecifico(this);
		this.listaTrayectos.add(trayecto);
	}
	public abstract long calcularValor();
}
