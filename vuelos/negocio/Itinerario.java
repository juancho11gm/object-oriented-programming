package co.edu.javeriana.vuelos.negocio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Itinerario {
	public static long CONSECUTIVO=0;
	private long codigo;
	private String nombre;
	private boolean comprado;
	private long valor;//BASADO EN EL PUNTO 9 me parecio necesario
	private Agente agente; //Relacion
	private List<Trayecto> listaTrayectos;//Relaciones
	private List<Pasajero> listaPasajeros;//Relaciones


	public Itinerario(String nombre,Agente agente) {
		CONSECUTIVO++;
		this.codigo = CONSECUTIVO;
		this.nombre = nombre;
		this.comprado = false;
		this.valor=0;
		this.agente = agente;
		listaTrayectos=new ArrayList<>();
		listaPasajeros=new ArrayList<>();
	}
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public long getValor() {
		return valor;
	}
	public void setValor(long valor) {
		this.valor = valor;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isComprado() {
		return comprado;
	}
	public void setComprado(boolean comprado) {
		this.comprado = comprado;
	}
	public Agente getAgente() {
		return agente;
	}
	public void setAgente(Agente agente) {
		this.agente = agente;
	}
	public List<Trayecto> getListaTrayectos() {
		return listaTrayectos;
	}
	public void setListaTrayectos(ArrayList<Trayecto> listaTrayectos) {
		this.listaTrayectos = listaTrayectos;
	}
	public List<Pasajero> getListaPasajeros() {
		return listaPasajeros;
	}
	public void setListaPasajeros(ArrayList<Pasajero> listaPasajeros) {
		this.listaPasajeros = listaPasajeros;
	}
	public static long getCONSECUTIVO() {
		return CONSECUTIVO;
	}
	public String toString(){
		return String.format("%20d %-20s", codigo,nombre);
	}
	/**
	 * Recorre la lista de trayectos que tiene el itinerario.
	 * Busca el trayecto que tenga el mismo id que es recibido como parametro. Devuelve el objeto Trayecto.
	 * @param id
	 * @return
	 */
	public Trayecto buscarTrayecto(int id){
		Trayecto trayecto = null;
		for(Trayecto t:this.listaTrayectos){
			if(t.getId() == id){
				trayecto = t;
			}
		}
		return trayecto;
	}
	/**
	 * Recorre la lista de pasajeros que tiene el itinerario.
	 * Busca el pasajero que tenga el mismo id que es recibido como parametro. Devuelve el objeto Pasajero.
	 * @param id
	 * @return
	 */
	public Pasajero buscarPasajero(String id){
		Pasajero pasajero = null;
		for(Pasajero p:this.listaPasajeros)	{
			if(p.getIdentificacion().equals(id)){
				pasajero = p;
			}
		}
		return pasajero;
	}
	/**
	 * Recibe un Vueloespecifico que se asociara al nuevo Trayecto que se creara.
	 * Se instancia un objeto Trayecto y se agrega a la lista de trayectos del itinerario.
	 * @param vueloEspecifico
	 */
	public void agregarTrayecto(VueloEspecifico vueloEspecifico){
		Trayecto trayecto=new Trayecto(this,vueloEspecifico);
		this.listaTrayectos.add(trayecto);
	}
	/**
	 * Recibe los datos del objeto Pasajero a instanciar.
	 * Instancia un objeto Pasajero
	 * Se agrega a la lista de pasajeros del objeto.
	 * @param identificacion
	 * @param nombre
	 */
	public void agregarPasajero(String identificacion,String nombre,LocalDate fecha,boolean bool){
		long edad = ChronoUnit.YEARS.between(fecha, LocalDate.now()); //Retorna la edad del pasajero
		Pasajero pasajero;
		if(edad>=12){
			pasajero=new Mayor(identificacion,nombre,fecha,bool);
		}else{
			pasajero = new Menor(identificacion,nombre,fecha,bool);
		}
		this.listaPasajeros.add(pasajero);	
	}
	//Desde aqui son nuevas
	/**
	 * Recorre la lista de trayectos e invoca sobre cada Trayecto el metodo verificarCupoEnVueloEspecifico.
	 * Retorna verdadero si hay cipo y falso si no.
	 * @return
	 */
	public boolean verificarCupoEnTrayectos(){//Poner retorno de valorItinerario
		boolean hayCupo=false;
		boolean res = false;
		int cont =0;
		for(int i=0;i<this.listaTrayectos.size();i++){
			hayCupo = this.listaTrayectos.get(i).verificarCupoEnVueloEspecifico(this.listaPasajeros.size());
			if(hayCupo){
				cont++;
			}
		}
		if(cont == this.listaTrayectos.size()){
			res = true;
		}
		return res;

	}
	/**
	 * Recore la lista de trayectos y de pasajeros. El valor del itinerario es calculado sumando el valor del trayecto por cada pasajero.
	 */
	public void calcularValor(){ //  ESTA ES
		
		/*
		for(Trayecto t:this.listaTrayectos){
			valor = t.calcularValor();
			for(Pasajero p:this.listaPasajeros){
				if(p instanceof Menor){
					p.calcularValorItinerario(valor,this);
				}else{
					if(p instanceof Mayor){
						p.calcularValorItinerario(valor,this);
					}
				}
			}
		}
		*/
		for(Trayecto t:this.listaTrayectos){
			for(int i=0;i<this.listaPasajeros.size();i++){
				this.valor+=t.calcularValor();
			}
		}
		
		
	}
	/**
	 * Busca un trayecto invocando el metodo buscarTrayecto.
	 * Busca un pasajero invocando el metodo buscarPasajero.
	 * Invoca sobre el trayecto encontrado el metodo marcarSillaComoComprada y le envia el id de la silla y el objeto pasajero que encontro.
	 * @param idTrayecto
	 * @param idSilla
	 * @param identificacion
	 */
	public void marcarSillaComoComprada(int idTrayecto,String idSilla,String identificacion){
		System.out.println(idTrayecto);
		System.out.println(identificacion);
		Trayecto trayecto = buscarTrayecto(idTrayecto);
		Pasajero pasajero = buscarPasajero(identificacion);
		System.out.println(trayecto.getId());
		System.out.println(pasajero.getIdentificacion());
		if(this.listaTrayectos.isEmpty() == false){
			trayecto.marcarSillaComoComprada(idSilla,pasajero);
		}
	}
}
