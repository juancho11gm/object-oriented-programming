package co.edu.javeriana.vuelos.negocio;

import java.util.ArrayList;
import java.util.List;

public class Trayecto {
	private static int CONSECUTIVO=0;
	private int id;
	private Itinerario itinerario;//Relacion
	private VueloEspecifico vueloEspecifico; //Relacion
	private List<Silla> listaSillas;//Relacion

	public Trayecto(Itinerario itinerario,VueloEspecifico vueloEspecifico) {
		CONSECUTIVO++;
		this.id = CONSECUTIVO;
		this.itinerario = itinerario;
		this.listaSillas = new ArrayList<Silla>();
		this.vueloEspecifico=vueloEspecifico;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Itinerario getItinerario() {
		return itinerario;
	}
	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}
	public VueloEspecifico getVueloEspecifico() {
		return vueloEspecifico;
	}
	public void setVueloEspecifico(VueloEspecifico vueloEspecifico) {
		this.vueloEspecifico = vueloEspecifico;
	}
	public List<Silla> getListaSillas() {
		return listaSillas;
	}
	public void setListaSillas(ArrayList<Silla> listaSillas) {
		this.listaSillas = listaSillas;
	}
	public static int getCONSECUTIVO() {
		return CONSECUTIVO;
	}
	public String toString(){
		return String.format("%25d %-20s \n",id,vueloEspecifico.toString());
	}
	/**
	 * Recibe un numero de pasajeros.
	 * Verifica que el VueloEspecifico asociado al Trayecto, que tiene como atributo cuposLibres sea mayor o igual  al numero de pasajeros.
	 * Si es mayor, retorna verdadero, de lo contrario retorna falso.
	 * @param numPasajeros
	 * @return
	 */
	public boolean verificarCupoEnVueloEspecifico(int numPasajeros){
		if(this.getVueloEspecifico().getCuposLibres()>=numPasajeros){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Retorna el valor del atributo 'tarifa' del Trayecto.
	 * @return
	 */
	public long calcularValor(){ //Falta tomar en cuenta las sillas asociadas
		long tarifa;
		tarifa = this.getVueloEspecifico().getTarifa();
		return tarifa;
	}
	/**
	 * Instancia un objeto Silla con la informacion enviada como parametro.
	 * Añade la silla a la lista de sillas del trayecto.
	 */
	public void marcarSillaComoComprada(String idSilla,Pasajero pasajero){
		Silla silla=new Silla(idSilla,true,this.getVueloEspecifico());
		pasajero.agregarSilla(silla); //Agrego la silla a la lista de sillas del pasajero
		System.out.println("verificar"+idSilla);
		this.listaSillas.add(silla);
	}
}
