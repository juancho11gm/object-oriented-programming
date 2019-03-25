package co.edu.javeriana.vuelos.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class SistemaVuelos implements ISistemaViajes{
	private HashMap<Long,Aerolinea> listaAerolineas;//Relaciones
	private HashMap<Long,Agente> listaAgentes;//Relaciones
	private List<Ciudad> listaCiudades;//Relaciones

	public SistemaVuelos() {
		this.listaAerolineas = new HashMap<Long,Aerolinea>();
		this.listaAgentes = new  HashMap<Long,Agente>();
		this.listaCiudades = new ArrayList<>();
	}
	public HashMap<Long,Aerolinea> getListaAerolineas() {
		return listaAerolineas;
	}
	public void setListaAerolineas(HashMap<Long,Aerolinea> listaAerolineas) {
		this.listaAerolineas = listaAerolineas;
	}
	public HashMap<Long,Agente> getListaAgentes() {
		return listaAgentes;
	}
	public void setListaAgentes(HashMap<Long,Agente> listaAgentes) {
		this.listaAgentes = listaAgentes;
	}
	public List<Ciudad> getListaCiudades() {
		return listaCiudades;
	}
	public void setListaCiudades(ArrayList<Ciudad> listaCiudades) {
		this.listaCiudades = listaCiudades;
	}
	/**
	 * Recibe el codigo de una ciudad como parametro
	 * Recorre la lista de ciudades y valida que si el codigo de dicho objeto Ciudad es igual al codigo recibido, se retornara el objeto Ciudad, de lo contrario retorna null.
	 * @param codigo
	 * @return
	 */
	public Ciudad buscarCiudad(long codigo){
		Ciudad aux;
		Ciudad ciudad = null;
		for(int i=0;i<listaCiudades.size();i++){
			aux = listaCiudades.get(i);
			if(aux.getCodigo() == codigo){
				ciudad = aux;
			}
		}
		return ciudad;
	}
	/**
	 * Recibe el nombre de una ciudad como parametro.
	 * Recorre la lista de ciudades y valida que si el nombre de dicho objeto Ciudad es igual al nombre recibido, se retornara el objeto Ciudad, de lo contrario retorna null.
	 * @param nombre
	 * @return
	 */
	public Ciudad buscarCiudad(String nombre){
		Ciudad aux;
		Ciudad ciudad = null;
		for(int i=0;i<listaCiudades.size();i++){
			aux = listaCiudades.get(i);
			if(aux.getNombre().equals(nombre)){
				ciudad = aux;
			}
		}
		return ciudad;
	}
	/**
	 * Recibe como parametros los datos del objeto Aerolinea  a instanciar.
	 * Instancia un objeto Aerolinea con los datos dados.
	 * @param codigo
	 * @param nombre
	 * @param cuentaBanco
	 */

	public Agente buscarAgente(String nombre){
		for(Agente a:this.getListaAgentes().values()){
			if(nombre.equals(a.getNombre())){
				return a;
			}
		}
		return null;
	}
	public void agregarAerolinea(long codigo,String nombre,String cuentaBanco){
		Aerolinea aerolinea=new Aerolinea(codigo,nombre,cuentaBanco);
		listaAerolineas.put(codigo, aerolinea);
	}
	/**
	 * Recibe como parametros dos codigos de ciuades, una de origen y otra de destino, el codigo de una aerolinea y los datos de un Vuelo Planeado a instanciar.
	 * Busca la aerolinea por medio del codigo recibido, invocando el metodo buscarAerolinea.
	 * Invoca el metodo agregarVueloPlaneado sobre la aerolinea encontrada.
	 * @param codigoAerolinea
	 * @param codigo
	 * @param numeroVuelo
	 * @param diaSemana
	 * @param horaSalida
	 * @param horaLlegada
	 * @param codigoCiudadOrigen
	 * @param codigoCiudadDestino
	 */
	public void agregarVueloPlaneado(long codigoAerolinea,long codigo,String numeroVuelo,String diaSemana,String horaSalida,String horaLlegada,long codigoCiudadOrigen,long codigoCiudadDestino){
		Ciudad ciudadOrigen =  buscarCiudad(codigoCiudadOrigen);
		Ciudad ciudadDestino = buscarCiudad(codigoCiudadDestino);
		Aerolinea aerolinea= buscarAerolinea(codigoAerolinea);
		aerolinea.agregarVueloPlaneado(codigo,numeroVuelo,diaSemana,horaSalida,horaLlegada,ciudadOrigen,ciudadDestino);
	}
	/**
	 * Recibe la informacion de la ciudad a instanciar.
	 * Instancia un objeto Ciudad con los parametros recibidos.
	 * Agrega el objeto ciudad a la lista de ciudades de el sistema.
	 * @param codigo
	 * @param nombreCiudad
	 */
	public void agregarCiudad(long codigo,String nombreCiudad){
		Ciudad ciudad=new Ciudad(codigo,nombreCiudad);
		this.listaCiudades.add(ciudad);
	}
	/**
	 * Recibe el codigo de una aerolinea y el codigo de un vuelo planeado.
	 * Recorre la lista de vuelos planeados de la aerolinea.
	 * Valida si lel codigo de un vuelo planeado es igual al codigo recibido como parametro.
	 * Si se cumple la condicion asigna al atributo Aerolinea de dicho vuelo planeado, el objeto aerolinea que coincida con el codigo del mismo.
	 * @param codigoAerolinea
	 * @param codigoVueloPlaneado
	 */
	public void asociarAerolinea(long codigoAerolinea,long codigoVueloPlaneado){
		Aerolinea aerolinea=buscarAerolinea(codigoAerolinea);
		for(int i=0;i<aerolinea.getListaVuelosPlaneados().size();i++){
			if(aerolinea.getListaVuelosPlaneados().get(i).getCodigo()==codigoVueloPlaneado){
				aerolinea.getListaVuelosPlaneados().get(i).setAerolinea(aerolinea);
			}
		}
	}
	/**
	 * Recibe los datos de un objeto Agente a instanciar.
	 * Instancia el objeto agente y lo agrega a la lista de agentes del sistema.
	 * @param codigo
	 * @param nombre
	 * @param email
	 */
	public void agregarAgente(long codigo,String nombre,String email){
		Agente agente = new Agente(codigo,nombre,email);
		this.listaAgentes.put(codigo, agente);
	}
	/**
	 * Recibe el codigo de un agente.
	 * Recorre la lista de agentes del sistema y retorna el Agente que tenga el mismo codigo que es recibido como parametro.
	 * @param codigo
	 * @return
	 */
	public Agente buscarAgente(long codigo){
		return listaAgentes.get(codigo);
	}
	/**
	 * Recibe el codigo y el nombre de un itinerario a crear.
	 * Busca el Agente invocando el metodo buscarAgente.
	 * Invoca el metodo agregarItinerario sobre el agente encontrado.
	 * @param codigoAgente
	 * @param nombre
	 */
	public void agregarItinerario(long codigoAgente,String nombre){
		Agente agente=buscarAgente(codigoAgente);
		agente.agregarItinerario(codigoAgente, nombre);
	}
	/**
	 * Recibe el codigo de un agente y el nombre de un itinerario.
	 * Busca el Agente invocando el metodo buscarAgente.
	 * Invoca el metodo buscarItinerario sobre el Agente encontrado.
	 * @param codigoAgente
	 * @param nombreItinerario
	 * @return
	 */
	public Itinerario buscarItinerario(long codigoAgente,long codigoItinerario){
		Agente agente = buscarAgente(codigoAgente);
		return agente.buscarItinerario(codigoItinerario);
	}
	public long buscarCodigoItinerario(long codigoAgente,String nombreItinerario){
		Agente agente = buscarAgente(codigoAgente);
		return agente.buscarCodigoItinerario(nombreItinerario);
	}
	/**
	 * Recibe el codigo de un agente, el nombre de un itinerario y la informacion del Pasajero a crear.
	 * Invoca el metodo buscarAgente.
	 * Invoca el metodo buscarItinerario sobre el Agente encontrado.
	 * Invoca el metodo agregarPasajero sobre el Itinerario encontrado.
	 * @param codigoAgente
	 * @param nombreItinerario
	 * @param identificacion
	 * @param nombre
	 */
	
	/**
	 * Recibe el codigo de una aerolinea.
	 * Recorre la lista de aerolineas del sistema.
	 * Valida si el codigo de alguna Aerolinea es igual al codigo recibido como parametro y devuelve dicho objeto.
	 * @param codigo
	 * @return
	 */
	public Aerolinea buscarAerolinea(long codigo){
		return listaAerolineas.get(codigo);
	}
	/**
	 * Recorre la lista de aerolineas del sistema.
	 * Invoca el metodo verificarVueloespecifico sobre cada aerolinea.
	 * Retorna el objeto VueloEspecifico si se logra encontrar, de lo contrario retorna Null.
	 * @param codigoOrigen
	 * @param codigoDestino
	 * @param fecha
	 * @return
	 */
	public VueloEspecifico verificarVueloEspecifico(long codigoOrigen,long codigoDestino,LocalDate fecha){
		VueloEspecifico vueloEspecifico = null;
		Set<Long> keys=listaAerolineas.keySet();
		for(Long k:keys){
			vueloEspecifico=this.listaAerolineas.get(k).verificarVueloEspecifico(codigoOrigen, codigoDestino, fecha);
			if(vueloEspecifico!=null){
				return vueloEspecifico;
			}
		}
		return vueloEspecifico;
	}
	/**
	 * Recibe el codigo de un agente y el codigo de un itinerario.
	 * Busca el Agente invocando el metodo buscarAgente.
	 * Recorre la lista de itinerarios del agente encontrado.
	 * Si el codigo de algun itinerario es igual al codigo recibido como parametro, se procede a verificar el estado del atributo 'Comprado' de dicho itinerario.
	 * Si el atributo es verdadero, retorna true, si es falso retorna false.
	 * @param codAgente
	 * @param codItinerario
	 * @return
	 */
	public boolean verificarEstadoItinerario(long codAgente,long codItinerario){
		Agente agente=buscarAgente(codAgente);
		for(int i=0;i<agente.getListaItinerarios().size();i++){
			if(agente.getListaItinerarios().get(i).getCodigo()==codItinerario){
				if(agente.getListaItinerarios().get(i).isComprado()){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Recibe el codigo de un vuelo especifico.
	 * Recorre la lista de Aerolineas del sistema.
	 * Invoca sobre cada aerolinea el metodo buscarVueloEspecifico.
	 * Si encunetra el objeto, retorna el objeto VueloEspecifico que es encontrado, de lo contrario retorna Null.
	 * @param codigoEspecifico
	 * @return
	 */
	public VueloEspecifico buscarEspecifico(long codigoEspecifico){
		VueloEspecifico vueloEspecifico=null;
		Set<Long> keys=listaAerolineas.keySet();
		for(Long k:keys){
			return this.listaAerolineas.get(k).buscarVueloEspecifico(codigoEspecifico);
		}
		return vueloEspecifico;
	}
	/**
	 * Recibe el codigo de un itinerario, un objeto VueloEspecifico y el codigo de un agente.
	 * Busca el Agente invocando el metodo buscarAgente.
	 * Invoca el metodo agregarTrayecto sobre el Agente encontrado.
	 * @param codigoItinerario
	 * @param vueloEspecifico
	 * @param codigoAgente
	 */
	public void agregarTrayecto(long codigoItinerario,VueloEspecifico vueloEspecifico,long codigoAgente){
		Agente agente=buscarAgente(codigoAgente);
		agente.agregarTrayecto(codigoItinerario,vueloEspecifico);

	}
	/**
	 * Recibe el codigo de una aerolinea, el codigo de un vuelo planeado y los datos de un vueloEspecifico a instanciar.
	 * Busca la aerolinea invocando el metodo buscar Aerolinea.
	 * Invoca el metodo agregarVueloEspecifico sobre la aerolinea encontrada.
	 * @param codigoAerolinea
	 * @param codigoVueloPlaneado
	 * @param fecha
	 * @param tipoAvion
	 * @param capacidad
	 * @param tarifa
	 */
	@Override
	public void agregarVueloEspecificoNacional(long codigoAerolinea, long codigoVueloPlaneado, LocalDate fecha,
			String tipoAvion, int capacidad,long tarifa, int iva,TipoAvion tipo) {
		Aerolinea aerolinea=buscarAerolinea(codigoAerolinea);
		aerolinea.agregarVueloEspecificoNacional(codigoVueloPlaneado,fecha,tipoAvion,capacidad,tarifa,iva,tipo);
		// TODO Auto-generated method stub
		
	}
	@Override
	public void agregarVueloEspecificoInternacional(long codigoAerolinea, long codigoVueloPlaneado, LocalDate fecha,
			String tipoAvion, int capacidad,long tarifa, long impuesto,TipoAvion tipo) {
		Aerolinea aerolinea=buscarAerolinea(codigoAerolinea);
		aerolinea.agregarVueloEspecificoInternacional(codigoVueloPlaneado,fecha,tipoAvion,capacidad,tarifa,impuesto,tipo);
		// TODO Auto-generated method stub
		
	}

	/**
	 * Recibe el codigo de un agente y el nombre de un itinerario.
	 * Busca al agente invocando el metodo buscar Agente.
	 * Invoca el metodo verificarCupoEnTrayectos que devuelve verdadero si hay cupo y falso de lo contrario.
	 * Devuelve el valor de la respuest recibida por el metodo invocado.
	 * @param codigoAgente
	 * @param nombreItinerario
	 * @return
	 */
	public boolean verificarCupoEnTrayectos(long codigoAgente, long codigoItinerario){
		boolean res = false;
		Agente agente = this.buscarAgente(codigoAgente);
		res = agente.verificarCupoEnTrayectos(codigoItinerario);
		return res;
	}
	/**
	 * Recibe el codigo de un agente y el nombre de un itinerario.
	 * Busca al agente invocando el metodo buscar Agente.
	 * Invoca el metodo calcularValorItinerario sobre el agente encontrado.
	 * @param codigoAgente
	 * @param nombreItinerario
	 */
	public void calcularValorItinerario(long codigoAgente,long codigoItinerario){
		Agente agente = this.buscarAgente(codigoAgente);
		agente.calcularValor(codigoItinerario);
	}
	/**
	 * Recibe el codigo de un agente y el nombre de un itinerario.
	 * Busca al agente invocando el metodo buscar Agente.
	 * invoca el metodo obtenerValorItinerario sobre el agente encontrado.
	 * Retorna el valor que es devuelto por este metodo, el cual es una variable 'lon' con el valor del respectivo itinerario.
	 * @param codigoAgente
	 * @param nombreItinerario
	 * @return
	 */
	public long obtenerValorItinerario(long codigoAgente,long codigoItinerario){
		Agente agente = this.buscarAgente(codigoAgente);
		return agente.obtenerValorItinerario(codigoItinerario);
	}
	/**
	 * Recibe el codigo de un agente y el nombre de un itinerario.
	 * Busca al agente invocando el metodo buscar Agente.
	 * Invoca el metodo asignarItinerarioComoComprado sobre el agente encontrado.
	 * @param codigoAgente
	 * @param nombreItinerario
	 */
	public void asignarItinerarioComoComprado(long codigoAgente, long codigoItinerario){
		Agente agente = this.buscarAgente(codigoAgente);
		agente.asignarItinerarioComoComprado(codigoItinerario);
	}
	/**
	 * Busca al agente invocando el metodo buscar Agente.
	 * Invoca el metodo marcarSillaComoComprada sobre el agente encontrado.
	 * @param codigoAgente
	 * @param nombreItinerario
	 * @param idTrayecto
	 * @param idSilla
	 * @param identificacion
	 */
	public void marcarSillaComoComprada(long codigoAgente,long codigoItinerario,int idTrayecto, String idSilla,String identificacion){
		Agente agente = this.buscarAgente(codigoAgente);
		System.out.println("AGENTE "+agente.getNombre());
		agente.marcarSillaComoComprada(codigoItinerario, idTrayecto, idSilla,identificacion);
	}
	
	@Override
	public void agregarPasajero(long codigoAgente, long codigoItinerario, String identificacion, String nombre,
			LocalDate fecha, boolean bool) {
		Agente agente=buscarAgente(codigoAgente);
		Itinerario itinerario=agente.buscarItinerario(codigoItinerario);
		itinerario.agregarPasajero(identificacion, nombre,fecha,bool);
		// TODO Auto-generated method stub
		
	}
	public Itinerario buscarItinerarioPorNombre(String nombreAgente,String nombreItinerario){
		Agente agente = null;
		for(Agente a:this.getListaAgentes().values()){
			if(a.getNombre().equals(nombreAgente)){
				agente = a;
			}
		}
		Itinerario itinerario =agente.buscarItinerarioPorNombre(nombreItinerario);
		return itinerario;
		
	}
	
}
