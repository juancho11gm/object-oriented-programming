package co.edu.javeriana.vuelos.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Aerolinea {
	private long codigo;
	private String nombre;
	private String cuentaBanco;

	private List<VueloPlaneado> listaVuelosPlaneados;//Relaciones

	public Aerolinea(long codigo,String nombre,String cuentaBanco){
		this.codigo = codigo;
		this.nombre = nombre;
		this.cuentaBanco = cuentaBanco;
		this.listaVuelosPlaneados = new ArrayList<VueloPlaneado>();
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

	public String getCuentaBanco() {
		return cuentaBanco;
	}

	public void setCuentaBanco(String cuentaBanco) {
		this.cuentaBanco = cuentaBanco;
	}

	public List<VueloPlaneado> getListaVuelosPlaneados() {
		return listaVuelosPlaneados;
	}

	public void setListaVuelosPlaneados(ArrayList<VueloPlaneado> listaVuelosPlaneados) {
		this.listaVuelosPlaneados = listaVuelosPlaneados;
	}

	public String toString(){
		return String.format("%25d %-20s %-20s", codigo,nombre,cuentaBanco);
	}
	/**
	 * Recorre la lista de vuelos planeados de la aerolinea.
	 * Busca un vuelo especifico recibiendo como parametro los codigos de dos ciudades, la de origen y la de destino respectivamente.
	 * Invoca un metodo de un objeto VueloPlaneado ya que este tiene la responsabilidad de buscar su respectivo vueloEspecifico.
	 * @param codigoOrigen
	 * @param codigoDestino
	 * @param fecha
	 * @return
	 */
	public VueloEspecifico verificarVueloEspecifico(long codigoOrigen, long codigoDestino,LocalDate fecha){
		VueloEspecifico vueloEspecifico = null;
		for(int i=0;i<this.listaVuelosPlaneados.size();i++){
			vueloEspecifico=this.listaVuelosPlaneados.get(i).verificarVueloEspecifico(codigoOrigen, codigoDestino, fecha);
			if(vueloEspecifico!=null){
				return vueloEspecifico;
			}
		}
		return vueloEspecifico;
	}
	/**
	 * Instancia un objeto VueloPlaneado a el objeto Aerolinea.
	 * El objeto es agregado a la lista de vuelos planeados.
	 * Recibe la informacion del VueloPlaneado a crear 
	 * @param codigo
	 * @param numeroVuelo
	 * @param diaSemana
	 * @param horaSalida
	 * @param horaLlegada
	 * @param origen
	 * @param destino
	 */
	public void agregarVueloPlaneado(long codigo,String numeroVuelo,String diaSemana,String horaSalida,String horaLlegada,Ciudad origen,Ciudad destino){
		VueloPlaneado vuelo=new VueloPlaneado(codigo,numeroVuelo,diaSemana,horaSalida,horaLlegada,origen,destino,this);
		listaVuelosPlaneados.add(vuelo);
	}
	/**
	 * Recibe infromacion de un nuevo objeto a crear de tipo VueloEspecifico.
	 * Busca el vueloPlaneado con el codigo que le es enviado.
	 * Invoca el metodo agregarVueloEspecifico sobre el VueloPlaneado Encontrado, ya que este es el enargado de instanciar el nuevo objeto.
	 * @param codigoVueloPlaneado
	 * @param fecha
	 * @param tipoAvion
	 * @param capacidad
	 * @param tarifa
	 */
	public void agregarVueloEspecificoNacional(long codigoVueloPlaneado,LocalDate fecha, String tipoAvion, int capacidad, long tarifa,int iva,TipoAvion tipo){
		VueloPlaneado vueloPlaneado=buscarVueloPlaneado(codigoVueloPlaneado);
		vueloPlaneado.agregarVueloEspecificoNacional(fecha,tipoAvion,capacidad,tarifa,iva,tipo);
	}
	
	public void agregarVueloEspecificoInternacional(long codigoVueloPlaneado,LocalDate fecha, String tipoAvion, int capacidad, long tarifa,long impuesto,TipoAvion tipo){
		VueloPlaneado vueloPlaneado=buscarVueloPlaneado(codigoVueloPlaneado);
		vueloPlaneado.agregarVueloEspecificoInternacional(fecha,tipoAvion,capacidad,tarifa,impuesto,tipo);
	}
	/**
	 * Recibe el codigo del vuelo especifico a encontrar.
	 *Recorre la lista de vuelos planeados y a cada objeto le invoca el metodo buscarVueloEspecifico, ya que la responsabilidad de encontrar el objeto la tiene el VueloPlaneado.
	 * @param codigoEspecifico
	 * @return
	 */
	public VueloEspecifico buscarVueloEspecifico(long codigoEspecifico){
		for(int i=0;i<this.listaVuelosPlaneados.size();i++){
			return this.getListaVuelosPlaneados().get(i).buscarVueloEspecifico(codigoEspecifico);
		}
		return null;
	}
	/**
	 * Busca un vuelo planeado por medio del codigo en la lista de vuelos planeados de la aerolinea
	 * Recibe el codigo del vuelo planeado a buscar.
	 * @param codigo
	 * @return
	 */
	public VueloPlaneado buscarVueloPlaneado(long codigo){
		VueloPlaneado aux;
		VueloPlaneado vueloPlaneado=null;
		for(int i=0;i<this.listaVuelosPlaneados.size();i++){
			aux = this.listaVuelosPlaneados.get(i);
			if(aux.getCodigo()==codigo){
				vueloPlaneado = aux;
			}
		}
		return vueloPlaneado;
	}

}
