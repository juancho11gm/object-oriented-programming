package co.edu.javeriana.vuelos.negocio;

import java.util.ArrayList;
import java.util.List;

public class Agente {
	private long codigo;
	private String nombre;
	private String email;

	private List<Itinerario> listaItinerarios;//Relaciones

	public Agente(long codigo,String nombre,String email){
		this.codigo = codigo;
		this.nombre = nombre;
		this.email = email;

		listaItinerarios = new ArrayList<Itinerario>();
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String toString(){
		return String.format("%20d %-20s %-20s", codigo,nombre,email);
	}
	public List<Itinerario> getListaItinerarios() {
		return listaItinerarios;
	}

	public void setListaItinerarios(ArrayList<Itinerario> listaItinerarios) {
		this.listaItinerarios = listaItinerarios;
	}
	/**
	 * Recibe como parametros la informacion del itinerario a crear
	 * Instancia un objeto Itinerario.
	 * Agrega un itinerario a la lista de itinerarios del agente.
	 * @param codigo
	 * @param nombre
	 */
	public void agregarItinerario(long codigo,String nombre){
		Itinerario itinerario=new Itinerario(nombre,this);
		listaItinerarios.add(itinerario);
	}
	/**
	 * Recibe como parametros el codigo de un itinerario y de un vuelo especifico.
	 * Busca el itinerario en la lista del agente por medio del codigo.
	 * Invoca el metodo agregar trayecto sobre el objeto itinerario para que este se encargue de hacer el resto de la labor.
	 * @param codigoItinerario
	 * @param vueloEspecifico
	 */
	public void agregarTrayecto(long codigoItinerario,VueloEspecifico vueloEspecifico){
		Itinerario itinerario=buscarItinerarioCodigo(codigoItinerario);
		itinerario.agregarTrayecto(vueloEspecifico);
	}
	/**
	 * Recibe el codigo de un itinerario.
	 * Recorre la lista de itinerarios y devuelve el objeto Itinerario que cumpla con la condicion de tener el mismo codigo que recibio.
	 * @param codigo
	 * @return
	 */
	public Itinerario buscarItinerarioCodigo(long codigo){
		for(int i=0;i<this.listaItinerarios.size();i++){
			if(this.listaItinerarios.get(i).getCodigo()==codigo){
				return this.listaItinerarios.get(i);
			}
		}
		return null;
	}
	public long buscarCodigoItinerario(String nombreItinerario){
		for(Itinerario i:this.getListaItinerarios()){
			if(i.getNombre().equals(nombreItinerario)){
				return i.getCodigo();
			}
		}
		return (Long) null;
	}
	/**
	 * Recorre la lista de itineraios del agente.
	 * Invoca sobre cada itinerario el metodo buscarTrayecto, pues la responsabilidad de encontrar el objeto es de el Itinerario.
	 * @param id
	 * @return
	 */
	public Trayecto buscarTrayecto(int id){
		Trayecto trayecto=null;
		for(int i=0;i<this.listaItinerarios.size();i++){
			trayecto=this.listaItinerarios.get(i).buscarTrayecto(id);
		}
		return trayecto;
	}

	/**
	 * Recorre la lista de itinerarios y se busca el itinerario que tenga el mismo nombre que es recibido como parametro.
	 * Devuelve el objeto Itinerario que es encontrado.
	 * @param nombre
	 * @return
	 */
	public Itinerario buscarItinerario(long codigoItinerario){
		for(Itinerario i:this.getListaItinerarios()){
			if(i.getCodigo()==codigoItinerario){
				return i;
			}
		}
		return null;
	}
	//Desde qui son nuevos metodos
	/**
	 * Busca el itinerario por el nombre.
	 * Devuelve verdadero si encontro cupos en el trayecto o falso si no hay cupos.
	 * @param nombreItinerario
	 * @return
	 */
	public boolean verificarCupoEnTrayectos(long codigoItinerario){
		boolean res=false;
		Itinerario itinerario = this.buscarItinerario(codigoItinerario);
		res = itinerario.verificarCupoEnTrayectos();
		return res;
	}
	/**
	 * Busca un itinerario por el nombre.
	 * Calcula el valor de un Itinerario, invocando el metodo sobre el itinerario encontrado.
	 * @param nombreItinerario
	 */
	public void calcularValor(long codigoItinerario){
		Itinerario itinerario = this.buscarItinerario(codigoItinerario);
		itinerario.calcularValor();
	}
	/**
	 * Busca el itinerario invocando el metodo buscar itinerario.
	 * Invoca el metodo getValor sobre el itinerario encntrado.
	 * Devuelve el valor encontrado.
	 * @param nombreItinerario
	 * @return
	 */
	public long obtenerValorItinerario(long codigoItinerario){
		Itinerario itinerario = this.buscarItinerario(codigoItinerario);
		return itinerario.getValor();
	}
	/**
	 * Invoca el metodo buscarItinerario por nombre.
	 * Sobre el itinerario comprado invoca el metodo setComprado.
	 * @param nombreItinerario
	 */
	public void asignarItinerarioComoComprado(long codigoItinerario){
		Itinerario itinerario = this.buscarItinerario(codigoItinerario);
		itinerario.setComprado(true);
	}
	/**
	 * Busca un itinerario invocando el metodo buscarItinerario por el nombre
	 * invoca el metodo marcarSilla comoComprada sobre el itinerario encontrado. envia como parametros los datos de la silla a buscar.
	 * @param nombreItinerario
	 * @param idTrayecto
	 * @param idSilla
	 * @param identificacion
	 */
	public void marcarSillaComoComprada(long codigoItinerario,int idTrayecto,String idSilla,String identificacion){
		Itinerario itinerario = this.buscarItinerario(codigoItinerario);
		boolean encontro = false;
		for(Itinerario i:this.listaItinerarios){
			for(Trayecto t:i.getListaTrayectos()){
				if(t.getId()==idTrayecto){ 
					for(Silla s: t.getListaSillas()){
						if(s.getId() == idSilla){
							encontro = true;
						}

					}
				}
				
			}
		}
		if(encontro == false){ //Revisar esto
			System.out.println(itinerario.getNombre());
			System.out.println(idTrayecto);
			System.out.println(identificacion);
			System.out.println(idSilla);
			itinerario.marcarSillaComoComprada(idTrayecto, idSilla, identificacion);//FAlta retornar un booleano apra que imprima que ya esta comprada
		}
	}
	public Itinerario buscarItinerarioPorNombre(String nombreItinerario){
		Itinerario itinerario = null;
		
		for(Itinerario i:this.getListaItinerarios()){
			if(i.getNombre().equals(nombreItinerario)){
				itinerario = i;
				return itinerario;
			}
		}
		return itinerario;
	}
}
