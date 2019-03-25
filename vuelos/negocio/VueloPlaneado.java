package co.edu.javeriana.vuelos.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VueloPlaneado {
	private long codigo;
	private String numeroVuelo;
	private DiaSemana diaSemanax;
	private String horaSalida;
	private String horaLlegada;
	private Aerolinea aerolinea;//Relaciones
	private Ciudad origen;//Relaciones
	private Ciudad destino;//Relaciones


	private List<VueloEspecifico> listaVuelosEspecificos;//Relaciones
	private String diaSemana;

	public VueloPlaneado(long codigo,String numeroVuelo,String diaSemana,String horaSalida,String horaLlegada,Ciudad origen,Ciudad destino,Aerolinea aerolinea){
		this.codigo = codigo;
		this.numeroVuelo = numeroVuelo;
		this.diaSemana = diaSemana;
		this.horaSalida = horaSalida;
		this.horaLlegada = horaLlegada;
		this.aerolinea = aerolinea;
		this.origen = origen;
		this.destino = destino;

		this.listaVuelosEspecificos = new ArrayList<VueloEspecifico>();
	}
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getNumeroVuelo() {
		return numeroVuelo;
	}
	public void setNumeroVuelo(String numeroVuelo) {
		this.numeroVuelo = numeroVuelo;
	}
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public String getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(String DiaSemana) {
		this.horaSalida = horaSalida;
	}
	public String getHoraLlegada() {
		return horaLlegada;
	}
	public void setHoraLlegada(String horaLlegada) {
		this.horaLlegada = horaLlegada;
	}
	public Aerolinea getAerolinea() {
		return aerolinea;
	}
	public void setAerolinea(Aerolinea aerolinea) {
		this.aerolinea = aerolinea;
	}
	public Ciudad getOrigen() {
		return origen;
	}
	public void setOrigen(Ciudad origen) {
		this.origen = origen;
	}
	public Ciudad getDestino() {
		return destino;
	}
	public void setDestino(Ciudad destino) {
		this.destino = destino;
	}
	public List<VueloEspecifico> getListaVuelosEspecificos() {
		return listaVuelosEspecificos;
	}
	public void setListaVuelosEspecificos(ArrayList<VueloEspecifico> listaVuelosEspecificos) {
		this.listaVuelosEspecificos = listaVuelosEspecificos;
	}

	public void asociarAerolinea(Aerolinea aerolinea){
		this.aerolinea = aerolinea;
	}
	public String toString(){
		return String.format("%25d %-20s %-20s %-20s %-20s %-20s %25d %-20s %25d", codigo,numeroVuelo,diaSemana,horaSalida,horaLlegada,origen.getNombre(),origen.getCodigo(),destino.getNombre(),destino.getCodigo()); //FALTA AGREGAR CIUDADES
	}
	/**
	 * Recibe el codigo de un VueloEspecifico y un objeto Trayecto.
	 * Busca un vuelo especifico invocando el metodo buscarVueloEspecifico.
	 * Invoca el metodo agregarTrayecto sobre el objeto VueloEspecifico encontrado.
	 * 
	 */
	public void agregarTrayecto(long codigoEspecifico,Trayecto trayecto){
		if(this.listaVuelosEspecificos.size()>0){
			VueloEspecifico vueloEspecifico=buscarVueloEspecifico(codigoEspecifico);
			if(vueloEspecifico!=null){
				vueloEspecifico.agregarTrayecto(trayecto);
			}
		}
	}
	/**
	 * Recibe el codigo de una ciudad de origen, el codigo de una ciudad de destino y una fecha.
	 * Recorre la lista de vuelos especificos.
	 * Para cada VueloEspecifico invoca el metodo verificarVueloEspecifico. 
	 * @param codigoOrigen
	 * @param codigoDestino
	 * @param fecha
	 * @return
	 */
	public VueloEspecifico verificarVueloEspecifico(long codigoOrigen, long codigoDestino,LocalDate fecha){
		VueloEspecifico vueloEspecifico=null;
		for(int i=0;i<this.listaVuelosEspecificos.size();i++){
			vueloEspecifico=this.listaVuelosEspecificos.get(i).verificarVueloEspecifico(codigoOrigen, codigoDestino, fecha);
			if(vueloEspecifico!=null){
				return vueloEspecifico;
			}
		}
		return vueloEspecifico;
	}
	/**
	 * Recibe el codigo de un VueloEspecifico.
	 * Recorre la lista de vuelos especificos.
	 * Verifica que si el codigo que es recibido como parametro es igual al codigo de algun objeto Vuelo Especifico.
	 * Si se cumple la condicion se retorna dicho objeto VueloEspecifico, de loc ontrario retorna Null.
	 * @param codigo
	 * @return
	 */
	public VueloEspecifico buscarVueloEspecifico(long codigo){
		for(int i=0;i<this.getListaVuelosEspecificos().size();i++){
			if(this.getListaVuelosEspecificos().get(i).getCodigo()==codigo){
				return this.getListaVuelosEspecificos().get(i);
			}
		}
		return null;
	}
	/**
	 * Recibe los datos de el vueloEspecifico a instanciar.
	 * Instancia el VueloEspecifico.
	 * Añade el VueloEspecifico a la lista de vuelos especificos del VueloPlaneado.
	 * @param fecha
	 * @param tipoAvion
	 * @param capacidad
	 * @param tarifa
	 */
	public void agregarVueloEspecificoNacional(LocalDate fecha, String tipoAvion, int capacidad, long tarifa,int iva,TipoAvion tipo){
		VueloEspecifico vueloEspecifico=new VueloEspecificoNacional(fecha,tipoAvion,capacidad,this,tarifa,iva,tipo);
		this.listaVuelosEspecificos.add(vueloEspecifico);
		System.out.println("El vuelo especifico ha sido creado con el codigo "+vueloEspecifico.getCodigo());
	}
	public void agregarVueloEspecificoInternacional(LocalDate fecha, String tipoAvion, int capacidad, long tarifa,long impuesto,TipoAvion tipo){
		VueloEspecifico vueloEspecifico=new VueloEspecificoInternacional(fecha,tipoAvion,capacidad,this,tarifa,impuesto,tipo);
		this.listaVuelosEspecificos.add(vueloEspecifico);
		System.out.println("El vuelo especifico ha sido creado con el codigo "+vueloEspecifico.getCodigo());
	}
}