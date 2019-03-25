package co.edu.javeriana.vuelos.persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import co.edu.javeriana.vuelos.negocio.Aerolinea;
import co.edu.javeriana.vuelos.negocio.Agente;
import co.edu.javeriana.vuelos.negocio.Ciudad;
import co.edu.javeriana.vuelos.negocio.DiaSemana;
import co.edu.javeriana.vuelos.negocio.SistemaVuelos;
import co.edu.javeriana.vuelos.negocio.VueloEspecifico;
import co.edu.javeriana.vuelos.negocio.VueloPlaneado;

public class ManejoArchivos {

	/**
	 * Lee un archivo de texto que contiene la informacion de las ciudades que seran registradas en el sistema.
	 * Instancia objetos Ciudad
	 * @param sistema
	 */
	public static void ingresarCiudades(SistemaVuelos sistema){
		String texto;
		String line;
		String codigoAux;
		long codigo;
		String nombreCiudad;
		Scanner scann = null;
		Scanner input = new Scanner(System.in);



		try{

			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"JPG & GIF Images", "jpg", "gif");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " +
						chooser.getSelectedFile().getName());
			}
			texto=chooser.getSelectedFile().getName();
			File inFile=new File(texto.trim());
			scann= new Scanner(inFile);
			line = scann.nextLine();
			line = line.trim();
			StringTokenizer st;

			while(line.compareTo("0")!=0){
				while(line.charAt(0)=='#'){
					line=scann.nextLine().trim();
				}
					st=new StringTokenizer(line,"*");
					codigoAux=st.nextToken().trim();
					nombreCiudad=st.nextToken().trim();
					codigo=Long.parseLong(codigoAux);
					System.out.println(nombreCiudad);
					sistema.agregarCiudad(codigo,nombreCiudad);
					line=scann.nextLine();
				}
		}
		catch(Exception e){
			System.out.println("Excepcion Inesperada"+e.getMessage());
		}
		finally{
			scann.close();
		}

	}
	/**
	 * Lee un archivo de texto que contiene la informacion de las Aerolineas con sus respectivos Vuelos Planeados.
	 * Instancia objetos Aerolinea, y VueloPlaneado.
	 * @param sistema
	 */
	public static void ingresarAerolineasYVuelosPlaneados(SistemaVuelos sistema){
		String nombreArchivo;
		String linea;

		String aux;

		long codigoAerolinea;
		String nombreAerolinea;
		String cuentaBanco;

		long codigoVueloPlaneado;
		String numeroVuelo;

		String horaSalida;
		String horaLlegada;
		long codigoCiudadOrigen;
		long codigoCiudadDestino;

		Scanner input = null;
		Scanner scan = new Scanner(System.in);

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " +
					chooser.getSelectedFile().getName());
		}
		nombreArchivo = chooser.getSelectedFile().getName();
		File archivo = new File(nombreArchivo); 
		try{
			input = new Scanner(archivo);
			linea = input.nextLine();
			linea = linea.trim();
			//System.out.println(linea);
			while(!linea.equals("#FIN")){
				if(linea.startsWith("#")||linea.isEmpty()){
					linea = input.nextLine();
					linea = linea.trim();
					continue;//Sigue al siguiente ciclo del while
				}
				StringTokenizer tokenizer = new StringTokenizer(linea,"*");
				aux = tokenizer.nextToken();
				aux = aux.trim();
				codigoAerolinea = Long.parseLong(aux);
				aux = tokenizer.nextToken();
				aux = aux.trim();
				nombreAerolinea = aux;
				aux = tokenizer.nextToken();
				aux = aux.trim();
				cuentaBanco = aux;
				sistema.agregarAerolinea(codigoAerolinea,nombreAerolinea,cuentaBanco);
				System.out.println(nombreAerolinea);
				linea = input.nextLine();
				linea = linea.trim();
				while(!linea.equals("0")){
					if(linea.startsWith("#")||linea.isEmpty()){
						linea = input.nextLine();
						linea = linea.trim();
						continue;//Sigue al siguiente ciclo del while
					}
					tokenizer = new StringTokenizer(linea,"*"); //Chequear si no toca crear otro tokenizer
					aux = tokenizer.nextToken();
					aux = aux.trim();
					codigoVueloPlaneado = Long.parseLong(aux);
					aux = tokenizer.nextToken();
					aux = aux.trim();
					numeroVuelo = aux;
					aux = tokenizer.nextToken();
					aux = aux.trim();
					String diaSemanaAux=aux.toUpperCase();
					aux = tokenizer.nextToken();
					aux = aux.trim();
					horaSalida = aux;
					aux = tokenizer.nextToken();
					aux = aux.trim();
					horaLlegada = aux;
					aux = tokenizer.nextToken();
					aux = aux.trim();
					codigoCiudadOrigen = Long.parseLong(aux);
					aux = tokenizer.nextToken();
					aux = aux.trim();
					codigoCiudadDestino = Long.parseLong(aux);
					sistema.agregarVueloPlaneado(codigoAerolinea,codigoVueloPlaneado,numeroVuelo,diaSemanaAux,horaSalida,horaLlegada,codigoCiudadOrigen,codigoCiudadDestino);
					linea = input.nextLine();
					linea = linea.trim();
				}
				linea = input.nextLine();
				linea = linea.trim();
			}

		}
		catch(Exception e){
			System.out.println("Excepcion Inesperada"+e.getMessage());
		}
		finally{

			input.close();
		}
	}
	/**
	 * Lee un archivo de texto con la informacion de los Agentes del sistema.
	 * Instancia objetos Agente.
	 * @param sistema
	 */
	public static void ingresarAgentes(SistemaVuelos sistema){
		String nombreArchivo;
		String linea;
		String aux;
		Scanner input = null;
		long codigo;
		String nombre;
		String email;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el nombre del archivo de texto:");
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG & GIF Images", "jpg", "gif");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " +
					chooser.getSelectedFile().getName());
		}
		nombreArchivo = chooser.getSelectedFile().getName();
		File archivo = new File(nombreArchivo);
		try{
			input = new Scanner(archivo);
			linea = input.nextLine();
			linea.trim();
			while(!linea.startsWith("0")){
				if(linea.startsWith("#")||linea.isEmpty()){
					linea = input.nextLine();
					linea = linea.trim();
				}
				StringTokenizer tokenizer = new StringTokenizer(linea,"*");
				aux = tokenizer.nextToken();
				aux = aux.trim();
				codigo = Long.parseLong(aux);

				aux = tokenizer.nextToken();
				aux = aux.trim();
				nombre = aux;
				aux = tokenizer.nextToken();
				aux = aux.trim();
				email = aux;
				sistema.agregarAgente(codigo,nombre,email);
				linea = input.nextLine();
				linea.trim();
			}
		}
		catch(Exception e){
			System.out.println("Excepcion Inesperada"+e.getMessage());
			e.getStackTrace();
		}
		finally{
			input.close();
		}
	}
	/**
	 * Genera un archivo de texto cuyo nombre se le solicita al usuario.
	 * El archivo es creado con la informacion de los vuelos especificos que esten planeados entre dos ciudades (preguntadas al usuario) en el marco de un mes apartir de la fecha.
	 * @param sistema
	 */
	public static void generarReporte(SistemaVuelos sistema){

		Scanner scann=new Scanner(System.in);
		Scanner st=new Scanner(System.in);
		String archivo;
		System.out.println("Ingrese el nombre del archivo");
		archivo=scann.nextLine();
		File outFile = new File(archivo);
		FileOutputStream outStream = null;
		PrintWriter dataOutStream = null;
		try{
			outStream = new FileOutputStream(outFile);
			dataOutStream = new PrintWriter(outStream);
			String ciudadO,ciudadD;
			System.out.println("Ingrese el nombre de la ciudad de origen");
			ciudadO=st.nextLine();
			System.out.println("Ingrese el nombre de la ciudad de destino");
			ciudadD=st.nextLine();
			Ciudad origen=sistema.buscarCiudad(ciudadO);
			Ciudad destino=sistema.buscarCiudad(ciudadD);
			dataOutStream.println("REPORTE VUELOS");
			boolean b=false;
			Set<Long> keys=sistema.getListaAerolineas().keySet();
			dataOutStream.println(String.format("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s","numeroVuelo","diaSemana","horaSalida","horaLlegada","origen","codigoOrigen","destino","codigoDestino"));
			for(Long l:keys){
				for(VueloPlaneado vp:sistema.getListaAerolineas().get(l).getListaVuelosPlaneados()){
					for(VueloEspecifico ve:vp.getListaVuelosEspecificos()){
						if(ve.getVueloPlaneado().getOrigen().getCodigo()==origen.getCodigo()){
							if(ve.getVueloPlaneado().getDestino().getCodigo()==destino.getCodigo()){
								if(ve.getFecha().isAfter(LocalDate.now())&&ve.getFecha().isBefore(LocalDate.now().plusMonths(1))){
									dataOutStream.println(ve.getVueloPlaneado().toString());
									b=true;
								}
							}
						}
					}
				}
			}
			if(b){
				System.out.println("Se creó el archivo de manera exitosa");
			}
			else{
				System.out.println("No se encontro el vuelo con las caracteristicas indicadas");
			}

			dataOutStream.close();
			outStream.close();
		}
		catch(Exception e){
			System.out.println("ERROR INESPERADO");
		}
	}
}

