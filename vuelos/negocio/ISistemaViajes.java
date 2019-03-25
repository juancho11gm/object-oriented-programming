package co.edu.javeriana.vuelos.negocio;

import java.time.LocalDate;

public interface ISistemaViajes {
	public Agente buscarAgente(String nombre);
	public Ciudad buscarCiudad(long codigo);
	public Ciudad buscarCiudad(String nombre);
	public void agregarAerolinea(long codigo,String nombre,String cuentaBanco);
	public void agregarVueloPlaneado(long codigoAerolinea,long codigo,String numeroVuelo,String diaSemana,String horaSalida,String horaLlegada,long codigoCiudadOrigen,long codigoCiudadDestino);
	public void agregarCiudad(long codigo,String nombreCiudad);
	public void asociarAerolinea(long codigoAerolinea,long codigoVueloPlaneado);
	public void agregarAgente(long codigo,String nombre,String email);
	public Agente buscarAgente(long codigo);
	public void agregarItinerario(long codigoAgente,String nombre);
	public Itinerario buscarItinerario(long codigoAgente,long codigoItinerario);
	public void agregarPasajero(long codigoAgente,long codigoItinerario,String identificacion, String nombre,LocalDate fecha,boolean bool);
	public Aerolinea buscarAerolinea(long codigo);
	public VueloEspecifico verificarVueloEspecifico(long codigoOrigen,long codigoDestino,LocalDate fecha);
	public boolean verificarEstadoItinerario(long codAgente,long codItinerario);
	public VueloEspecifico buscarEspecifico(long codigoEspecifico);
	public void agregarTrayecto(long codigoItinerario,VueloEspecifico vueloEspecifico,long codigoAgente);
	public void agregarVueloEspecificoNacional(long codigoAerolinea, long codigoVueloPlaneado,LocalDate fecha, String tipoAvion, int capacidad,long tarifa, int iva,TipoAvion tipo);
	public void agregarVueloEspecificoInternacional(long codigoAerolinea, long codigoVueloPlaneado,LocalDate fecha, String tipoAvion, int capacidad,long tarifa, long impuesto,TipoAvion tipo);
	public boolean verificarCupoEnTrayectos(long codigoAgente, long codigoItinerario);
	public void calcularValorItinerario(long codigoAgente,long codigoItinerario);
	public long obtenerValorItinerario(long codigoAgente,long codigoItinerario);
	public void asignarItinerarioComoComprado(long codigoAgente, long codigoItinerario);
	public void marcarSillaComoComprada(long codigoAgente,long codigoItinerario,int idTrayecto, String idSilla,String identificacion);
	public Itinerario buscarItinerarioPorNombre(String nombreAgente,String nombreItinerario);
}
