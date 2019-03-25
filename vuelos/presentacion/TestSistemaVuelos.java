package co.edu.javeriana.vuelos.presentacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import co.edu.javeriana.vuelos.negocio.Aerolinea;
import co.edu.javeriana.vuelos.negocio.Agente;
import co.edu.javeriana.vuelos.negocio.Ciudad;
import co.edu.javeriana.vuelos.negocio.Itinerario;
import co.edu.javeriana.vuelos.negocio.Mayor;
import co.edu.javeriana.vuelos.negocio.Menor;
import co.edu.javeriana.vuelos.negocio.Pasajero;
import co.edu.javeriana.vuelos.negocio.Silla;
import co.edu.javeriana.vuelos.negocio.SistemaVuelos;
import co.edu.javeriana.vuelos.negocio.Trayecto;
import co.edu.javeriana.vuelos.negocio.VueloEspecifico;
import co.edu.javeriana.vuelos.negocio.VueloEspecificoNacional;
import co.edu.javeriana.vuelos.negocio.VueloPlaneado;
import co.edu.javeriana.vuelos.persistencia.ManejoArchivos;

public class TestSistemaVuelos {

	/**
	 * En el main est� el cuerpo del trabajo, cada caso invoca metodos diferentes dependiendo de la necesidad.
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		int opcion;
		SistemaVuelos sistemaVuelo=new SistemaVuelos();
		boolean salir=false;
		while(salir==false){
			opcion=mostrarMenu();
			switch(opcion){
			case 1:
				System.out.println("Ingresar ciudades del sistema");
				ManejoArchivos.ingresarCiudades(sistemaVuelo);
				imprimirCiudades(sistemaVuelo);
				imprimirBonito();
				break;
			case 2:
				System.out.println("Ingresar aerolineas y vuelos planeados del sistema");
				ManejoArchivos.ingresarAerolineasYVuelosPlaneados(sistemaVuelo);
				imprimirAerolineas(sistemaVuelo);
				imprimirBonito();
				break;
			case 3:
				System.out.println("Ingresar agentes del sistema");
				ManejoArchivos.ingresarAgentes(sistemaVuelo);
				imprimirAgentes(sistemaVuelo);
				imprimirBonito();
				break;
			case 4:
				System.out.println("Agregar un vuelo especifico para un vuelo planeado");
				agregarVueloEspecificoAVueloPlaneado(sistemaVuelo);
				imprimirBonito();
				break;
			case 5:
				imprimirAerolineas(sistemaVuelo);
				imprimirBonito();
				break;
			case 6:
				imprimirAgentes(sistemaVuelo);
				agregarItinerario(sistemaVuelo);
				imprimirBonito();
				break;
			case 7:
				agregarTrayectoItinerario(sistemaVuelo);
				imprimirBonito();
				break;
			case 8:
				imprimirTrayectos(sistemaVuelo); 
				imprimirBonito();
				break;
			case 9:
				comprarItinerario(sistemaVuelo);
				imprimirBonito();
				break;
			case 10:
				tiqueteElectronico(sistemaVuelo);
				imprimirBonito();
				break;
			case 11:
				ManejoArchivos.generarReporte(sistemaVuelo);
				imprimirBonito();
				break;
			case 12:
				System.out.println("QUE TENGA UN BUEN D�A, HASTA LUEGO");
				salir=true;
			}
		}
	}
	/**
	 * Imprime el menu
	 * @return
	 */
	public static void imprimirBonito(){
		System.out.println();
		System.out.println("Press Any Key To Continue...");
		new java.util.Scanner(System.in).nextLine();
		for (int i = 0; i < 50; ++i) System.out.println();
	}

	public static int mostrarMenu(){
		int opcion;
		Scanner scann=new Scanner(System.in);

		do{
			System.out.println("--------SISTEMA DE VUELOS------");
			System.out.println("Opcion 1: Ingresar ciudades del sistema.");
			System.out.println("Opcion 2: Ingresar aerolineas y vuelos planeados del sistema.");
			System.out.println("Opcion 3: Ingresar agentes del sistema.");
			System.out.println("Opcion 4: Agregar un vuelo especifico para un vuelo planeado");
			System.out.println("Opcion 5: Mostrar aerolineas, vuelos planeados y vuelos especificos del sistema.");
			System.out.println("Opcion 6: Agregar un itinerario para un agente.");
			System.out.println("Opcion 7: Agregar un trayecto asociado a un itinerario");
			System.out.println("Opcion 8: Mostrar agentes, itinerarios y trayectos");
			System.out.println("Opcion 9: Comprar un itinerario asignando a cada pasajero una silla sobre cada trayecto del itinerario");
			System.out.println("Opcion 10: Tiquete electronico: para un itinerario comprado mostrar su valor, sus trayectos, las sillas reservadas, y los pasajeros asociados.");
			System.out.println("Opcion 11: Reporte vuelos especificos: Dado el nombre de una ciudad de origen y el de una ciudad destino");
			System.out.println("Opcion 12: Terminar.");
			System.out.println("Por favor ingrese una opcion:");
			opcion=scann.nextInt();
			if(opcion<1||opcion>12){
				System.err.println("Ingrese una opcion valida");
			}
		}while(opcion<1||opcion>12);
		return opcion;
	}
	/**
	 * Imprime las ciudades del sistema
	 * @param sistema
	 */
	public static void imprimirCiudades(SistemaVuelos sistema){
		System.out.println(String.format("%-20s %-20s \n", "NOMBRE","CODIGO"));
		for(int i=0;i<sistema.getListaCiudades().size();i++){
			System.out.print(sistema.getListaCiudades().get(i).toString());
		}
	}
	/**
	 * Imprime los agentes del sistema
	 * @param sistema
	 */
	public static void imprimirAgentes(SistemaVuelos sistema){
		Set<Long>keys=sistema.getListaAgentes().keySet();
		System.out.println(String.format("%20s %-20s %-20s", "CODIGO","NOMBRE","EMAIL"));
		for(Long k:keys){
			System.out.println(sistema.getListaAgentes().get(k).toString());
		}
	}
	/**
	 * Imprime las aerolineas del sistema, junto con sus vuelos planeados y a la vez los vuelos espec�ficos
	 * @param sistema
	 */
	public static void imprimirAerolineas(SistemaVuelos sistema){
		int l=0;
		long pasaje;
		Set<Long> keys=sistema.getListaAerolineas().keySet();
		System.out.println("\nREPORTE DE AEROLINEAS, VUELOS PLANEADOS Y VUELOS ESPECIFICOS\n");
		for(Long k:keys){
			System.out.println("AEROLINEA: "+sistema.getListaAerolineas().get(k).getNombre());
			System.out.println("CUENTA BANCO: "+sistema.getListaAerolineas().get(k).getCuentaBanco());
			System.out.println("CODIGO: "+sistema.getListaAerolineas().get(k).getCodigo());
			System.out.println("Vuelos Planeados:");
			System.out.println(String.format("%25s %-20s %-20s %-20s %-20s %-20s %25s %-20s %25s", "CODIGO","NUMERO VUELO","DIA SEMANA","HORA SALIDA","HORA LLEGADA","CIUDAD ORIGEN","CODIGO ORIGEN","CIUDAD DESTINO","CODIGO DESTINO"));
			for(VueloPlaneado vp:sistema.getListaAerolineas().get(k).getListaVuelosPlaneados()){
				System.out.println(vp.toString());
				for(VueloEspecifico ve:vp.getListaVuelosEspecificos()){
					if(l==0){
						System.out.println("Vuelos Especificos:");
						if(ve instanceof VueloEspecificoNacional){
							System.out.println(String.format("%25s %-20s %-20s %-20s %-20s %-20s %-20s %-20s", "CODIGO","FECHA","TIPO AVION","CAPACIDAD","CUPOS LIBRES", "TARIFA","IVA","VALOR PASAJE"));
						}
						else{
							System.out.println(String.format("%25s %-20s %-20s %-20s %-20s %-20s %-20s %-20s", "CODIGO","FECHA","TIPO AVION","CAPACIDAD","CUPOS LIBRES", "TARIFA","IMPUESTO","VALOR PASAJE"));
						}
						l++;
					}
					pasaje=ve.calcularValor();
					ve.setTarifa(pasaje);
					System.out.println(String.format("%-20s %-20d",ve.toString(),pasaje));
				}
				System.out.println();
				l=0;
			}
			System.out.println();
		}
	}
	/**
	 * Imprime todos los Itinerarios del sistema
	 * @param sistema
	 */
	public static void imprimirItinerarios(SistemaVuelos sistema){ // no se usa
		System.out.println(String.format("%20s %-20s", "CODIGO","NOMBRE"));
		Set<Long> keys=sistema.getListaAgentes().keySet();
		for(Long k:keys){
			System.out.println(k.toString());{
				for(Itinerario t:sistema.getListaAgentes().get(k).getListaItinerarios()){
					System.out.println("   "+t.toString());
				}
			}
		}
	}
	/**
	 * Imprime todos los trayectos del sistema
	 * @param sistema
	 */
	public static void imprimirTrayectos(SistemaVuelos sistema){
		System.out.println("\nAgentes\n");
		System.out.println(String.format("%20s %-20s %-20s", "CODIGO","NOMBRE","EMAIL"));
		Set<Long> keys=sistema.getListaAgentes().keySet();
		/*
		Collection<Agente> agentes =sistema.getListaAgentes().values();
		for(Agente a:agentes){
			
		}
		*/
		for(Long k:keys){
			System.out.println(sistema.getListaAgentes().get(k).toString());
			System.out.println("Itinerarios:");
			System.out.println(String.format("%20s %-20s", "CODIGO","NOMBRE"));
			for(Itinerario t:sistema.getListaAgentes().get(k).getListaItinerarios()){
				System.out.println(t.toString());
				System.out.println("Trayectos:");
				System.out.println(String.format("%25s %25s %-20s %-20s %-20s %-20s %-20s", "ID","CODIGO","FECHA","TIPO AVION","CAPACIDAD","CUPOS LIBRES","TARIFA"));
				for(Trayecto tr:t.getListaTrayectos()){
					System.out.println(tr.toString());
				}
			}
		}
	}
	/**
	 * Punto numero 7.
	 * Se encarga de agregar un trayecto a determinado Itinerario.
	 * No es posible agregar uno sin haber creado un Itinerario con anterioridad
	 * @param sistemaVuelo
	 */
	public static void agregarTrayectoItinerario(SistemaVuelos sistemaVuelo){
		try{
			int cont=0;
			VueloEspecifico vueloEspecifico=null;
			imprimirAgentes(sistemaVuelo);
			System.out.println("Digite el codigo del agente:");
			Scanner scann=new Scanner(System.in);
			String codigo;
			codigo=scann.nextLine();
			long codAgente=Long.parseLong(codigo);
			mostrarItinerario(sistemaVuelo,codAgente);
			long codigoItinerario;
			System.out.println("Digite el codigo del itinerario:");
			codigoItinerario=Long.parseLong(scann.nextLine().trim());
			imprimirCiudades(sistemaVuelo);
			while(cont<2){
				System.out.println("Digite el codigo de la ciudad de origen:");
				String ciudadOrigen=scann.nextLine();
				System.out.println("Digite el codigo de la ciudad de destino:");
				String ciudadDestino=scann.nextLine();
				System.out.println("Ingrese la fecha (YYYY-MM-DD):");
				String auxFecha=scann.nextLine();
				long codigoOrigen=Long.parseLong(ciudadOrigen);
				long codigoDestino=Long.parseLong(ciudadDestino);
				StringTokenizer st=new StringTokenizer(auxFecha,"-");
				int ano=Integer.parseInt(st.nextToken().trim());
				int mes=Integer.parseInt(st.nextToken().trim());
				int dia=Integer.parseInt(st.nextToken().trim());
				LocalDate fecha = LocalDate.of(ano, mes, dia);
				vueloEspecifico=sistemaVuelo.verificarVueloEspecifico(codigoOrigen,codigoDestino,fecha);
				if(vueloEspecifico==null){
					cont++;
					System.err.println("Ingrese nuevamente la ciudad de origen, la ciudad de destino y la fecha por favor.");
				}
				else{
					break;
				}
			}
			if(cont==2){
				System.err.println("NO SE ENCONTRO EL VUELO");
			}
			else{
				System.out.println(String.format("%25s %-20s %-20s %-20s %-20s %-20s", "CODIGO","FECHA","TIPO AVION","CAPACIDAD","CUPOS LIBRES", "TARIFA"));
				System.out.println(vueloEspecifico.toString());
				System.out.println("Digite el codigo del vuelo especifico:");
				String auxiliar=scann.nextLine().trim();
				long codigoEspecifico=Long.parseLong(auxiliar);
				sistemaVuelo.agregarTrayecto(codigoItinerario,vueloEspecifico,codAgente);
			}
		}
		catch(Exception e){
			System.out.println("ERROR INESPERADO");
		}
	}
	/**
	 * Recibe el codigo del agente, con el cual se buscara el objeto Agente.
	 * Imprime el itinerario de el agente que es encontrado
	 * @param sistema
	 * @param codigoAgente
	 */
	public static void mostrarItinerario(SistemaVuelos sistema, long codigoAgente){
		Agente agente=sistema.buscarAgente(codigoAgente);
		System.out.println(".ITINERARIOS-");
		System.out.println(String.format("%25s %-20s","CODIGO","NOMBRE"));
		for(int i=0;i<agente.getListaItinerarios().size();i++){
			System.out.println(agente.getListaItinerarios().get(i).toString());
		}
	}

	/**
	 * Agrega un vuelo especifico a una Aerolinea que es escogida por el usuario adentro de la funcion, por medio deentrada por teclado.
	 * El usuario digita el vuelo planeado de la respectiva aerolinea a la cual le quiere agregar el vuelo especifico.
	 * Se piden los datos del nuevo vuelo especifico y con base en estos se instancia un objeto de tipo VueloEspecifico que estara asociado al respectivo vuelo planeado.
	 * @param sistema
	 */
	public static void agregarVueloEspecificoAVueloPlaneado(SistemaVuelos sistema){
		Scanner scan = new Scanner(System.in);
		String aux,aux2,aux3;
		long codigoAerolinea;
		long codigoVueloPlaneado;
		StringTokenizer tokenizer, tokenizer2;
		LocalDate fecha;
		int anio;
		int mes;
		int dia;
		String tipoAvion;
		int capacidad;
		long tarifa;
		int iva;
		long impuesto;
		String ivaOImpuesto;
		String tipo;
		System.out.println("Indique si es NACIONAL o INTERNACIONAL:");
		tipo=scan.nextLine();
		if(tipo.equals("NACIONAL")|| tipo.equals("nacional")){
			System.out.println("Digite datos del nuevo vuelo especifico : fecha(YYYY-MM-DD) - tipoAvion - capacidad - tarifa(US)- IVA");
		}
		else{
			System.out.println("Digite datos del nuevo vuelo especifico : fecha(YYYY-MM-DD) - tipoAvion - capacidad - tarifa(US)- ImpuestoSALIDA");
		}
		aux = scan.nextLine();
		tokenizer = new StringTokenizer(aux," ");
		aux2 = tokenizer.nextToken();
		aux2.trim();
		tokenizer2 = new StringTokenizer(aux2,"-");
		aux3 = tokenizer2.nextToken();
		aux3.trim();
		anio = Integer.parseInt(aux3);
		aux3 = tokenizer2.nextToken();
		aux3 = aux3.trim();
		mes = Integer.parseInt(aux3);
		aux3 = tokenizer2.nextToken();
		aux3 = aux3.trim();
		dia = Integer.parseInt(aux3);
		fecha = LocalDate.of(anio, mes, dia);
		aux2 = tokenizer.nextToken();
		aux2 = aux2.trim();
		tipoAvion = aux2;
		aux2 = tokenizer.nextToken();
		aux2 = aux2.trim();
		capacidad = Integer.parseInt(aux2);
		aux2 = tokenizer.nextToken();
		aux2 = aux2.trim();
		tarifa = Integer.parseInt(aux2);
		ivaOImpuesto=tokenizer.nextToken();
		Set<Long> keys=sistema.getListaAerolineas().keySet();
		for(Long k:keys){
			System.out.println(sistema.getListaAerolineas().get(k).getCodigo()+" "+sistema.getListaAerolineas().get(k).getNombre());
		}
		System.out.println("Digite el codigo de la aerolinea al cual pertenecera el nuevo vuelo especifico:");
		aux = scan.nextLine();
		codigoAerolinea = Long.parseLong(aux);
		mostrarVuelosPlaneados(sistema,codigoAerolinea);
		System.out.println("Digite el codigo del vuelo planeado al cual pertenecera el nuevo vuelo especifico:");
		aux = scan.nextLine();
		codigoVueloPlaneado = Long.parseLong(aux);
	}
		
	
	public static void mostrarVuelosPlaneados(SistemaVuelos sistema,long codigoAerolinea){
		Aerolinea aerolinea= sistema.buscarAerolinea(codigoAerolinea);
		VueloPlaneado vueloPlaneado;
		for(int i=0;i<aerolinea.getListaVuelosPlaneados().size();i++){
			vueloPlaneado = aerolinea.getListaVuelosPlaneados().get(i);
			System.out.println(vueloPlaneado.toString());
		}
	}
	/**
	 * Muestra las sillas disponibles en un trayecto, es decir las que no han sido compradas.
	 * Se hace una validacion, si los id's de una de las sillas de la lista de sillas del vuelo especifico asociado es igual a uno de los id's de la lista de sillas del trayecto, entonces no se mostrara dicho codigo.
	 * De esta manera se logra imprimir solo las sillas que no han sido compradas.
	 * @param trayecto
	 */
	public static void mostrarSillasDisponiblesTrayecto(Trayecto trayecto){
		VueloEspecifico vueloEspecifico=trayecto.getVueloEspecifico();
		List<Silla> listaSillas=trayecto.getListaSillas();
		List<Silla> nuevaLista=new ArrayList<>();
		List<Silla> aux=new ArrayList<>();

		System.out.println("\nSillas Disponibles\n");
		if(trayecto.getListaSillas().isEmpty()){
			for(int j=0;j<vueloEspecifico.getListaSillas().size();j++){
				System.out.println(vueloEspecifico.getListaSillas().get(j).getId());
			}
		}
		else{
			for(int i=0;i<vueloEspecifico.getListaSillas().size();i++){
				for(int j=0;j<listaSillas.size();j++){
					if(!vueloEspecifico.getListaSillas().get(i).getId().equals(listaSillas.get(j).getId())){
						nuevaLista.add(vueloEspecifico.getListaSillas().get(i));
					}
					else{

					}
				}
			}

			int cont=0;
			for(int i=0;i<nuevaLista.size();i++){
				for(int j=0;j<nuevaLista.size();j++){
					if(nuevaLista.get(i).getId().equals(nuevaLista.get(j).getId())){
						cont++;
					}
				}
				if(cont>=trayecto.getListaSillas().size()){
					aux.add(nuevaLista.get(i));
				}
				cont=0;
			}
			Set<Silla> linkedHashSet =new LinkedHashSet<Silla>();
			linkedHashSet.addAll(aux);
			aux.clear();
			aux.addAll(linkedHashSet);
			for(Silla silla:aux){
				System.out.println(silla.getId());
			}
		}
	}

	/**
	 * Se reciben datos los siguientes datos por teclado: codigo agente, nombre itinerario, numero de pasajeros en el itinerario y datos, datos de cada pasajero
	 * Crea un itinerario para el agente, y a este itinerario se le agrega el numero de pasajeros que indique el usuario con su respectiva informacion. (nombre e identificacion)
	 * 
	 * @param sistemaVuelo
	 */
	public static void agregarItinerario(SistemaVuelos sistemaVuelo){
		try{
			int codigoAgente,nPasajeros;
			int i=0;
			long codigoItinerario;
			long edad;
			boolean viajaSolo = false;
			boolean requiereAsistencia=false;
			LocalDate fecha;
			StringTokenizer tokenizer2;
			String line;
			String aux;
			String nombre;
			int anio,mes,dia;
			Scanner input=new Scanner(System.in);
			System.out.println("�Cual es el codigo del agente al cual pertenece el nuevo itinerario? ");
			line=input.nextLine();
			codigoAgente=Integer.parseInt(line);
			System.out.println("Indique el nombre del nuevo itinerario");
			nombre=input.nextLine();
			System.out.println("�Cuantos pasajeros realizan el itinerario?");
			line=input.nextLine();
			nPasajeros=Integer.parseInt(line);
			StringTokenizer tokenizer;
			sistemaVuelo.agregarItinerario(codigoAgente,nombre);
			Agente agente = sistemaVuelo.buscarAgente(codigoAgente);
			codigoItinerario = sistemaVuelo.buscarCodigoItinerario(codigoAgente, nombre);
			for(int j=0;j<nPasajeros;j++){
				System.out.println("Indique una linea con la siguiente informacion: identificacion, nombre y fecha de nacimiento(YYYY-MM-DD) separados por *");
				line=input.nextLine();
				tokenizer = new StringTokenizer(line,"*");
				String identificacion=tokenizer.nextToken().trim();
				String Nombre=tokenizer.nextToken().trim();
				String fechaString = tokenizer.nextToken().trim();
				tokenizer2 = new StringTokenizer(fechaString,"-");
				aux = tokenizer2.nextToken().trim();
				anio = Integer.parseInt(aux);
				aux = tokenizer2.nextToken().trim();
				mes = Integer.parseInt(aux);
				aux = tokenizer2.nextToken().trim();
				dia = Integer.parseInt(aux);
				fecha = LocalDate.of(anio, mes, dia);
				edad = ChronoUnit.YEARS.between(fecha, LocalDate.now());
				if(edad<12){

					System.out.println("El pasajero viaja solo? (si-no)");
					aux = input.nextLine();
					if(aux.equals("si")||aux.equals("Si")||aux.equals("SI")){
						viajaSolo = true;
					}
					sistemaVuelo.agregarPasajero(codigoAgente,codigoItinerario, identificacion, Nombre,fecha, viajaSolo);
				}else{
					if(edad>=12){
						System.out.println("El pasajero requiere asistencia? (si-no)");
						aux = input.nextLine();
						if(aux.equals("si")||aux.equals("Si")||aux.equals("SI")){
							requiereAsistencia = true;
						}
						sistemaVuelo.agregarPasajero(codigoAgente,codigoItinerario, identificacion, Nombre,fecha, requiereAsistencia);
					}
				}
				
			}
			System.out.println("El itinerario ha sido creado de manera exitosa");
		}
		catch(Exception e){
			System.out.println("ERROR INESPERADO");
		}
	}
	/**
	 * Se recibe la siguiente informacion por teclado: codigo del agente, nombre de un itinerario.
	 * Se busca el objeto agente, y el objeto itinerario con el codigo y el nombre respectivamente.
	 * Se valida que hayan cupos en los trayectos del itinerario.
	 * Si hay cupos, se calcula el valor del itinerario y se muestra en pantalla.
	 * Si hay cupos, se asigna el itinerario como 'comprado' y se procede a asignarle las sillas de cada trayecto a cada pasajero.
	 * El usuario escoge la silla que quiere asignarle a cada pasajero.
	 * Se asocia un objeto silla a la lista de sillas de cada pasajero, y se añaden cada una de las sillas de los pasajeros a la lista de sillas de el objeto Trayecto.
	 * @param sistema
	 */
	public static void comprarItinerario(SistemaVuelos sistema){
		try{
			Scanner scan = new Scanner(System.in);
			String aux;
			String idSilla;
			long codigoAgente;
			long codigoItinerario;
			long valorItinerario;
			boolean hayCupos = false;
			Agente agente;
			Itinerario itinerario;
			imprimirAgentes(sistema);
			System.out.println("Digite el CODIGO del agente deseado:");
			aux = scan.nextLine();
			codigoAgente = Long.parseLong(aux);
			agente = sistema.buscarAgente(codigoAgente);
			mostrarItinerario(sistema,codigoAgente);
			System.out.println("Digite el CODIGO del itinerio deseado:");
			codigoItinerario = Long.parseLong(scan.nextLine());
			itinerario = sistema.buscarItinerario(codigoAgente,codigoItinerario);
			hayCupos = sistema.verificarCupoEnTrayectos(codigoAgente, codigoItinerario);
			if(hayCupos){
				sistema.calcularValorItinerario(codigoAgente, codigoItinerario);
				valorItinerario = sistema.obtenerValorItinerario(codigoAgente, codigoItinerario);
				System.out.println("El valor del itinerario es $"+valorItinerario);
				sistema.asignarItinerarioComoComprado(codigoAgente, codigoItinerario);
				for(int i=0;i<itinerario.getListaPasajeros().size();i++){
					System.out.println("Asignacion de Sillas para el pasajero "+itinerario.getListaPasajeros().get(i).getNombre());
					System.out.println("Identificacion: "+itinerario.getListaPasajeros().get(i).getIdentificacion());
					System.out.println();
					for(int j=0;j<itinerario.getListaTrayectos().size();j++){
						System.out.println("TRAYECTO "+(j+1)+":");
						System.out.println("ID: "+itinerario.getListaTrayectos().get(j).getId());
						System.out.println("Codigo Vuelo Especifico: "+itinerario.getListaTrayectos().get(j).getVueloEspecifico().getCodigo());
						System.out.println("Destino: "+itinerario.getListaTrayectos().get(j).getVueloEspecifico().getVueloPlaneado().getDestino().getNombre());
						System.out.println();
						mostrarSillasDisponiblesTrayecto(itinerario.getListaTrayectos().get(j));
						System.out.println("Digite una silla:");
						idSilla = scan.nextLine();
						//Marcar silla como comprada
						sistema.marcarSillaComoComprada(codigoAgente, codigoItinerario, itinerario.getListaTrayectos().get(j).getId(), idSilla,itinerario.getListaPasajeros().get(i).getIdentificacion());
					}
				}
				System.out.println("Compra exitosa");
			}else{
				System.out.println("No hay cupos para alguno de los trayectos.");
			}
		}
		catch(Exception e){
			System.out.println("ERROR INESPERADO");
		}
	}
	/**
	 * Imprime la informacion de un itinerario. 
	 * Se muestran datos como los trayectos, vuelos planeados y las sillas asignadas.
	 * @param sistema
	 */
	public static void tiqueteElectronico(SistemaVuelos sistema){
		//valor 
		Scanner scann=new Scanner(System.in);
		long codAgente,codItinerario;
		long valorItinerario=0;
		imprimirAgentes(sistema);
		System.out.println("Ingrese el codigo del agente que desea");
		codAgente=Long.parseLong(scann.nextLine().trim());
		mostrarItinerario(sistema,codAgente);
		System.out.println("Ingrese el codigo del itinerario que desea");
		codItinerario=Long.parseLong(scann.nextLine().trim());
		if(!sistema.verificarEstadoItinerario(codAgente,codItinerario)){
			System.out.println("El itinerario no se ha comprado.");
		}
		else{
			Agente agente;
			agente=sistema.buscarAgente(codAgente);
			Itinerario itinerario;
			itinerario=agente.buscarItinerarioCodigo(codItinerario);
			System.out.println(itinerario.toString());

			for(int i=0;i<itinerario.getListaTrayectos().size();i++){
				System.out.println(String.format("%25s %25s %-20s %-20s %-20s %-20s %-20s", "ID","CODIGO","FECHA","TIPO AVION","CAPACIDAD","CUPOS LIBRES","TARIFA"));
				System.out.println(itinerario.getListaTrayectos().get(i).toString());
				System.out.println("Vuelo Planeado:");
				System.out.println(String.format("%25s %-20s %-20s %-20s %-20s %-20s %25s %-20s %25s", "CODIGO","NUMERO VUELO","DIA SEMANA","HORA SALIDA","HORA LLEGADA","CIUDAD ORIGEN","CODIGO ORIGEN","CIUDAD DESTINO","CODIGO DESTINO"));
				System.out.println(itinerario.getListaTrayectos().get(i).getVueloEspecifico().getVueloPlaneado().toString());
				System.out.println("Sillas:");
				for(int j=0;j<itinerario.getListaTrayectos().get(i).getListaSillas().size();j++){
					System.out.println(itinerario.getListaTrayectos().get(i).getListaSillas().get(j).getId());
				}
			}
			long valor = 0;
			long valorTotalItinerario=0;
			for(Pasajero p:itinerario.getListaPasajeros()){
				System.out.println(String.format("%25s %25s %-20s", "IDENTIFICACION","NOMBRE","FECHA"));
				System.out.println(p.toString());
				valor = p.calcularValorItinerario();
				if(p instanceof Menor){
					if(((Menor) p).isViajaSolo()){
						System.out.println("El pasajero viaja solo durante sus vuelos.");
					}else{
						System.out.println("El pasajero viajara acompañado durante todos sus vuelos");
					}
				}else{
					if(((Mayor)p).isRequiereAsistencia()){
						System.out.println("El pasajero necesia asistencia durante sus vuelos.");
					}else{
						System.out.println("El pasajero no requiere de asistencia durante sus vuelos.");
					}
				}
				System.out.println("Valor por pasajero: $"+valor);
				valorTotalItinerario += valor;

			}
			itinerario.setValor(valorTotalItinerario);
			System.out.println("Valor total itinerario: $"+valorTotalItinerario);
			
		}
	}

}
