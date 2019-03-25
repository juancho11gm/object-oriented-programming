package co.edu.javeriana.vuelos.presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import co.edu.javeriana.vuelos.negocio.Agente;
import co.edu.javeriana.vuelos.negocio.Ciudad;
import co.edu.javeriana.vuelos.negocio.Itinerario;
import co.edu.javeriana.vuelos.negocio.Mayor;
import co.edu.javeriana.vuelos.negocio.Menor;
import co.edu.javeriana.vuelos.negocio.Pasajero;
import co.edu.javeriana.vuelos.negocio.Silla;
import co.edu.javeriana.vuelos.negocio.SistemaVuelos;
import co.edu.javeriana.vuelos.negocio.TipoAvion;
import co.edu.javeriana.vuelos.negocio.Trayecto;
import co.edu.javeriana.vuelos.negocio.VueloEspecifico;
import co.edu.javeriana.vuelos.negocio.VueloEspecificoInternacional;
import co.edu.javeriana.vuelos.negocio.VueloEspecificoNacional;
import co.edu.javeriana.vuelos.negocio.VueloPlaneado;
import co.edu.javeriana.vuelos.persistencia.ManejoArchivos;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class TestGUISistemaViajes extends JFrame {

	private JPanel contentPane;
	private JPanel Menu;
	private JPanel IngresarArchivos;
	private JTable table;
	private JTabbedPane tabbedPane;
	SistemaVuelos sistema=new SistemaVuelos();
	private String[] aerolineasNom = {"Nombre","Código", "Cuenta banco"};
	private String[] vueloPlaneadoNom={"Código","NumVuelo", "Día semana","Hora salida/llegada","Origen","Destino"};
	private Vector<String> vecV;//columnNamwa
	private Vector<Vector<Comparable>> vec;//rowData
	private	Vector<String> vueloPlaneadoV;
	private JPanel MostrarAerolineas;
	private JTable tableAerolineas;
	private JScrollPane scrollPane;
	private JTable tableVuelosPlaneados1;

	private JTable tableVuelosEspecificos;
	private long key=0;
	private int m=-1,n=-1;
	private JComboBox<String> comboBoxAerolineas;
	private JTable tableVuelosPlaneados;
	private JTextField textCapacidad;
	private JTextField textTarifaBasica;
	private JTextField textImpuestoOIva;
	private JComboBox<String> comboBoxTipoVuelo;
	private JComboBox<Integer> comboBoxDia;
	private JComboBox<Integer> comboBoxMes;
	private JComboBox<Integer> comboBoxAno;
	private JComboBox<TipoAvion> comboBoxTipoAvion;
	private JComboBox<String> comboBoxAgentes;
	private JTextField textNombreItinerario;
	private JTextField textIdentificacion;
	private JTextField textNombrePasajero;
	private long codigoAgente,codigoItinerario;
	private JComboBox<Integer> comboBoxDia1;
	private JComboBox<Integer> comboBoxMes1;
	private JComboBox<Integer> comboBoxAno1;
	private JRadioButton radioSi;
	private JRadioButton radioNo;
	private JComboBox<String> comboBoxAgentes1;
	private JTable tableItinerarios;
	private ButtonGroup buttonGroup;
	private JTable tableTrayectos;
	private JTable tableVuelosEspecificos1;
	private JComboBox<Integer> comboBoxAno2;
	private JComboBox<Integer> comboBoxMes2;
	private JComboBox<Integer> comboBoxDia2;
	private JComboBox<String> comboBoxDestino;
	private JComboBox<String> comboBoxOrigen;
	private JComboBox<String> comboBoxAgentes2;
	private JComboBox<String> comboBoxItinerarios2;
	private JComboBox comboBoxAgentesItinerarios;
	private JComboBox comboBoxItinerarios;
	private JComboBox comboBoxPasajeros;
	private JTable tablaTrayectos;
	private JComboBox comboBoxSilla;
	private JComboBox comboBoxAgentesTiquete;
	private JComboBox comboBoxItinerariosTiquete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SistemaVuelos sistemaVuelo=new SistemaVuelos();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGUISistemaViajes frame = new TestGUISistemaViajes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestGUISistemaViajes() {
		setTitle("Sistema viajes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 711, 459);
		contentPane.add(tabbedPane);

		vueloPlaneadoV=new Vector<String>(Arrays.asList(this.vueloPlaneadoNom));
		vec=new Vector<Vector<Comparable>>();
		initButtonGroup();


		Menu = new JPanel();
		tabbedPane.addTab("Men\u00FA", null, Menu, null);
		Menu.setLayout(null);



		JButton button = new JButton("Ingresar ciudades, vuelos y agentes.");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		button.setBounds(25, 38, 229, 23);
		Menu.add(button);

		JButton button_1 = new JButton("Agregar trayecto a itinerario");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);

			}
		});
		button_1.setBounds(25, 158, 229, 23);
		Menu.add(button_1);

		JButton button_2 = new JButton("Agregar vuelo especifico");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		button_2.setBounds(25, 78, 229, 23);
		Menu.add(button_2);

		JButton button_3 = new JButton("Agregar Itinerario");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(8);

			}
		});
		button_3.setBounds(25, 120, 229, 23);
		Menu.add(button_3);

		JButton button_4 = new JButton("Mostrar aerolineas, vuelos planeados y vuelos espec\u00EDficos");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);

			}
		});
		button_4.setBounds(322, 38, 317, 23);
		Menu.add(button_4);

		JButton button_5 = new JButton("Comprar itinerario");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(7);
			}
		});
		button_5.setBounds(322, 120, 317, 23);
		Menu.add(button_5);

		JButton button_6 = new JButton("Generar tiquete electr\u00F3nico");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(5);
			}
		});
		button_6.setBounds(322, 158, 317, 23);
		Menu.add(button_6);

		JButton button_7 = new JButton("Mostrar agentes, itinerarios y trayectos");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(6);
			}
		});
		button_7.setBounds(322, 78, 317, 23);
		Menu.add(button_7);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Juan Sebasti\u00E1n\\Videos\\pics\\comparateur.jpg"));
		lblNewLabel.setBounds(0, 11, 696, 405);
		Menu.add(lblNewLabel);

		IngresarArchivos = new JPanel();
		tabbedPane.addTab("Ingresar archivos", null, IngresarArchivos, null);
		IngresarArchivos.setLayout(null);

		JButton btnIngresarCiudades = new JButton("Seleccionar archivo de ciudades");
		btnIngresarCiudades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					ManejoArchivos.ingresarCiudades(sistema);
					for(Ciudad c:sistema.getListaCiudades()){
						comboBoxOrigen.addItem(c.getNombre());
					}
					for(Ciudad c:sistema.getListaCiudades()){
						comboBoxDestino.addItem(c.getNombre());
					}
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog( null,"No es posible cargar los archivos. ");
				}

			}
		});
		btnIngresarCiudades.setBounds(32, 36, 249, 23);
		IngresarArchivos.add(btnIngresarCiudades);

		JButton btnSeleccionarArchivoDe = new JButton("Seleccionar archivo de vuelos");
		btnSeleccionarArchivoDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				try{
					ManejoArchivos.ingresarAerolineasYVuelosPlaneados(sistema);
					Set<Long> keys=sistema.getListaAerolineas().keySet();
					for(Long k:keys){
						agregarAerolinea(k);
					};
					for(Long k:keys){
						comboBoxAerolineas.addItem(sistema.getListaAerolineas().get(k).getNombre());
					}
					scrollPane.setVisible(true);
					scrollPane.setViewportView(tableAerolineas);
				}

				catch(Exception e1){
					JOptionPane.showMessageDialog( null,"No es posible cargar los archivos. ");
				}
			}
		});
		btnSeleccionarArchivoDe.setBounds(32, 85, 249, 23);
		IngresarArchivos.add(btnSeleccionarArchivoDe);

		JButton btnSeleccionarArchivoDe_1 = new JButton("Seleccionar archivo de agentes");
		btnSeleccionarArchivoDe_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					ManejoArchivos.ingresarAgentes(sistema);
					Set<Long> keys=sistema.getListaAgentes().keySet();
					for(Long k:keys){
						comboBoxAgentes.addItem(sistema.getListaAgentes().get(k).getNombre());
						comboBoxAgentes1.addItem(sistema.getListaAgentes().get(k).getNombre());
						comboBoxAgentes2.addItem(sistema.getListaAgentes().get(k).getNombre());
						comboBoxAgentesItinerarios.addItem(sistema.getListaAgentes().get(k).getNombre());
						comboBoxAgentesTiquete.addItem(sistema.getListaAgentes().get(k).getNombre());
					}
				}catch(Exception ec){
					JOptionPane.showMessageDialog( null,"No es posible cargar los archivos. ");
				}
			}
			});
		btnSeleccionarArchivoDe_1.setBounds(36, 138, 245, 23);
		IngresarArchivos.add(btnSeleccionarArchivoDe_1);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnVolver.setBounds(36, 265, 89, 23);
		IngresarArchivos.add(btnVolver);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Juan Sebasti\u00E1n\\Videos\\pics\\images.jpg"));
		label.setBounds(-52, 11, 748, 415);
		IngresarArchivos.add(label);

		JPanel AgregarVueloEspecifico = new JPanel();
		tabbedPane.addTab("Agregar vuelo espec\u00EDfico", null, AgregarVueloEspecifico, null);
		AgregarVueloEspecifico.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 95, 658, 84);
		AgregarVueloEspecifico.add(scrollPane_2);

		tableVuelosPlaneados = new JTable();
		tableVuelosPlaneados.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"C\u00F3digo", "NumVuelo", "D\u00EDa semana", "Hora salida/llegada", "Origen", "Destino"
				}
				));
		scrollPane_2.setViewportView(tableVuelosPlaneados);

		JLabel lblNewLabel_1 = new JLabel("Aerol\u00EDneas");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(22, 11, 72, 14);
		AgregarVueloEspecifico.add(lblNewLabel_1);

		JList<?> list = new JList<Object>();
		list.setBounds(155, 61, 125, -26);
		AgregarVueloEspecifico.add(list);

		JLabel lblVuelosPlaneadosDe = new JLabel("Vuelos planeados de la aerolinea");
		lblVuelosPlaneadosDe.setForeground(Color.RED);
		lblVuelosPlaneadosDe.setBounds(10, 71, 185, 14);
		AgregarVueloEspecifico.add(lblVuelosPlaneadosDe);

		table = new JTable();
		table.setBounds(57, 172, 237, -26);
		AgregarVueloEspecifico.add(table);

		JList<?> list_1 = new JList<Object>();
		list_1.setBounds(256, 35, -106, 14);
		AgregarVueloEspecifico.add(list_1);

		comboBoxAerolineas = new JComboBox<String>();

		comboBoxAerolineas.setBounds(137, 8, 107, 20);
		AgregarVueloEspecifico.add(comboBoxAerolineas);

		JButton btnMostrarVuelosPlaneados = new JButton("Mostrar vuelos planeados para la aerol\u00EDnea");
		btnMostrarVuelosPlaneados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(n!=-1){
					borrarTabla(tableVuelosPlaneados);
				}
				String nombreAerolinea=String.valueOf(comboBoxAerolineas.getSelectedItem());
				Set<Long> keys=sistema.getListaAerolineas().keySet();
				long cod = 0;
				for(Long k:keys){	
					if(sistema.getListaAerolineas().get(k).getNombre().equals(nombreAerolinea)){
						cod=sistema.getListaAerolineas().get(k).getCodigo();
					}
				}
				for(VueloPlaneado vp:sistema.getListaAerolineas().get(cod).getListaVuelosPlaneados()){
					agregarVueloPlaneado(vp,tableVuelosPlaneados);
				}
				n++;
			}
		});
		btnMostrarVuelosPlaneados.setBounds(10, 37, 256, 23);
		AgregarVueloEspecifico.add(btnMostrarVuelosPlaneados);

		JLabel lblDatosNuevoVuelo = new JLabel("Datos nuevo vuelo espec\u00EDfico");
		lblDatosNuevoVuelo.setForeground(Color.RED);
		lblDatosNuevoVuelo.setBounds(10, 190, 185, 14);
		AgregarVueloEspecifico.add(lblDatosNuevoVuelo);

		JLabel lblTipoVuelo = new JLabel("Tipo vuelo");
		lblTipoVuelo.setBounds(10, 215, 72, 14);
		AgregarVueloEspecifico.add(lblTipoVuelo);

		comboBoxTipoVuelo = new JComboBox<String>();
		comboBoxTipoVuelo.setBounds(79, 215, 71, 20);
		comboBoxTipoVuelo.addItem("INTERNACIONAL");
		comboBoxTipoVuelo.addItem("NACIONAL");
		AgregarVueloEspecifico.add(comboBoxTipoVuelo);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(10, 250, 46, 14);
		AgregarVueloEspecifico.add(lblFecha);

		comboBoxDia = new JComboBox<Integer>();
		comboBoxDia.setBounds(78, 247, 46, 20);
		AgregarVueloEspecifico.add(comboBoxDia);

		comboBoxMes = new JComboBox<Integer>();
		comboBoxMes.setBounds(131, 247, 34, 20);
		AgregarVueloEspecifico.add(comboBoxMes);
		comboBoxAno = new JComboBox<Integer>();
		comboBoxAno.setBounds(175, 247, 53, 20);
		AgregarVueloEspecifico.add(comboBoxAno);

		JLabel lblTipoAvin = new JLabel("Tipo avi\u00F3n");
		lblTipoAvin.setBounds(10, 275, 61, 20);
		AgregarVueloEspecifico.add(lblTipoAvin);

		comboBoxTipoAvion = new JComboBox<TipoAvion>();
		comboBoxTipoAvion.setBounds(79, 275, 71, 20);
		comboBoxTipoAvion.addItem(TipoAvion.AIRBUS);
		comboBoxTipoAvion.addItem(TipoAvion.BOENG);
		comboBoxTipoAvion.addItem(TipoAvion.DOUGLAS);
		comboBoxTipoAvion.addItem(TipoAvion.CESSNA);
		AgregarVueloEspecifico.add(comboBoxTipoAvion);

		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setBounds(256, 215, 72, 14);
		AgregarVueloEspecifico.add(lblCapacidad);

		JLabel lblTarifaBsica = new JLabel("Tarifa b\u00E1sica");
		lblTarifaBsica.setBounds(256, 247, 83, 14);
		AgregarVueloEspecifico.add(lblTarifaBsica);

		JLabel lblImpuestoiva = new JLabel("Impuesto/IVA");
		lblImpuestoiva.setBounds(256, 278, 72, 14);
		AgregarVueloEspecifico.add(lblImpuestoiva);

		textCapacidad = new JTextField();
		textCapacidad.setBounds(362, 212, 86, 20);
		AgregarVueloEspecifico.add(textCapacidad);
		textCapacidad.setColumns(10);

		textTarifaBasica = new JTextField();
		textTarifaBasica.setBounds(362, 244, 86, 20);
		AgregarVueloEspecifico.add(textTarifaBasica);
		textTarifaBasica.setColumns(10);

		textImpuestoOIva = new JTextField();
		textImpuestoOIva.setBounds(362, 275, 86, 20);
		AgregarVueloEspecifico.add(textImpuestoOIva);
		textImpuestoOIva.setColumns(10);

		JButton btnNewButton = new JButton("REGISTRAR VUELO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarVueloEspecificoAVueloPlaneado(sistema);
			}
		});
		btnNewButton.setBounds(506, 211, 162, 23);
		AgregarVueloEspecifico.add(btnNewButton);

		JButton btnVolver_1 = new JButton("Volver");
		btnVolver_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnVolver_1.setBounds(525, 274, 89, 23);
		AgregarVueloEspecifico.add(btnVolver_1);

		JPanel AgregarATrayectoAItinerario = new JPanel();
		tabbedPane.addTab("Agregar trayecto a itinerario", null, AgregarATrayectoAItinerario, null);

		comboBoxOrigen = new JComboBox<String>();


		JLabel lblOrigen = new JLabel("Origen");

		JLabel lblDestino = new JLabel("Destino");

		comboBoxDestino = new JComboBox<String>();


		JLabel lblFechaSalida = new JLabel("Fecha salida");

		comboBoxDia2 = new JComboBox<Integer>();
		for(int i=1;i<=31;i++){
			comboBoxDia2.addItem(i);
		}

		comboBoxMes2 = new JComboBox<Integer>();
		for(int i=1;i<=12;i++){
			comboBoxMes2.addItem(i);
		}

		comboBoxAno2 = new JComboBox<Integer>();
		for(int i=2017;i<=2019;i++){
			comboBoxAno2.addItem(i);
		}

		JButton btnMostrarAlternativas = new JButton("Mostrar alternativas");
		btnMostrarAlternativas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<Comparable> fila=new Vector<Comparable>();	
				Ciudad origen=sistema.buscarCiudad(String.valueOf(comboBoxOrigen.getSelectedItem()));
				Ciudad destino=sistema.buscarCiudad(String.valueOf(comboBoxDestino.getSelectedItem()));
				int anio=(int) comboBoxAno2.getSelectedItem();
				int mes=(int) comboBoxMes2.getSelectedItem();
				int dia=(int) comboBoxDia2.getSelectedItem();
				LocalDate fecha=LocalDate.of(anio, mes, dia);
				Set<Long> keys=sistema.getListaAerolineas().keySet();
				for(Long k:keys){
					for(VueloPlaneado vp:sistema.getListaAerolineas().get(k).getListaVuelosPlaneados()){
						for(VueloEspecifico ve:vp.getListaVuelosEspecificos()){
							if(origen.equals(vp.getOrigen())&&destino.equals(vp.getDestino())&&fecha.equals(ve.getFecha())){
								fila.add(ve.getCodigo());
								fila.add(sistema.getListaAerolineas().get(k).getNombre());
								fila.add(vp.getNumeroVuelo());
								fila.add(vp.getHoraLlegada()+"/"+vp.getHoraSalida());
								fila.add(ve.getCapacidad());
								fila.add(ve.getTarifa());
								if(ve instanceof VueloEspecificoInternacional){
									fila.add("Internacional");
									fila.add(((VueloEspecificoInternacional) ve).getImpuestoSalida());
								}
								else{
									fila.add("Nacional");
									fila.add(((VueloEspecificoNacional)ve).getIva());
								}
								((DefaultTableModel) tableVuelosEspecificos1.getModel()).addRow(fila);
							}
						}
					}
				}
			}
		});

		JLabel lblVuelosQueCumplen = new JLabel("Vuelos que cumplen con los requisitos");
		lblVuelosQueCumplen.setForeground(Color.RED);

		JScrollPane scrollPane_6 = new JScrollPane();

		JLabel lblSeleccioneAgente = new JLabel("Seleccione agente");

		comboBoxAgentes2 = new JComboBox<String>();
		comboBoxAgentes2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxItinerarios2.removeAllItems();
				Agente a=sistema.buscarAgente((String) comboBoxAgentes2.getSelectedItem());
				for(Itinerario i:a.getListaItinerarios()){
					comboBoxItinerarios2.addItem(i.getNombre());
				}
			}
		});

		JLabel lblSeleccioneItinerario = new JLabel("Seleccione itinerario");

		comboBoxItinerarios2 = new JComboBox<String>();

		JButton btnRegistrarTrayecto = new JButton("Registrar trayecto");
		btnRegistrarTrayecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String nombreA=(String) comboBoxAgentes2.getSelectedItem();
					String nombreI=(String) comboBoxItinerarios2.getSelectedItem();
					Agente a=sistema.buscarAgente(nombreA);
					long codigoI=a.buscarCodigoItinerario(nombreI);
					Itinerario i=sistema.buscarItinerario(a.getCodigo(), codigoI);
					int f =tableVuelosEspecificos1.getSelectedRow();
					long codigoVueloE=(long) ((DefaultTableModel) tableVuelosEspecificos1.getModel()).getValueAt(f, 0);
					System.out.println(codigoVueloE);
					VueloEspecifico ve=sistema.buscarEspecifico(codigoVueloE);
					sistema.agregarTrayecto(codigoItinerario,ve,a.getCodigo());
					JOptionPane.showMessageDialog(null, "Se pudo agregar el trayecto con exito.");
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "No se pudo agregar el trayecto");
				}

			}
		});

		JButton btnVolver_3 = new JButton("Volver");
		GroupLayout gl_AgregarATrayectoAItinerario = new GroupLayout(AgregarATrayectoAItinerario);
		gl_AgregarATrayectoAItinerario.setHorizontalGroup(
				gl_AgregarATrayectoAItinerario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AgregarATrayectoAItinerario.createSequentialGroup()
						.addGap(34)
						.addGroup(gl_AgregarATrayectoAItinerario.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_6, GroupLayout.PREFERRED_SIZE, 636, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblVuelosQueCumplen)
								.addComponent(btnMostrarAlternativas)
								.addGroup(gl_AgregarATrayectoAItinerario.createSequentialGroup()
										.addComponent(lblFechaSalida)
										.addGap(18)
										.addComponent(comboBoxDia2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(comboBoxMes2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(comboBoxAno2, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_AgregarATrayectoAItinerario.createSequentialGroup()
										.addGroup(gl_AgregarATrayectoAItinerario.createParallelGroup(Alignment.LEADING)
												.addComponent(lblOrigen, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblDestino))
										.addGap(18)
										.addGroup(gl_AgregarATrayectoAItinerario.createParallelGroup(Alignment.LEADING, false)
												.addComponent(comboBoxDestino, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(comboBoxOrigen, 0, 125, Short.MAX_VALUE)))
								.addGroup(gl_AgregarATrayectoAItinerario.createSequentialGroup()
										.addGroup(gl_AgregarATrayectoAItinerario.createParallelGroup(Alignment.LEADING, false)
												.addGroup(gl_AgregarATrayectoAItinerario.createSequentialGroup()
														.addComponent(lblSeleccioneItinerario)
														.addGap(18)
														.addComponent(comboBoxItinerarios2, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addGroup(gl_AgregarATrayectoAItinerario.createSequentialGroup()
														.addComponent(lblSeleccioneAgente)
														.addGap(28)
														.addComponent(comboBoxAgentes2, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
										.addGap(57)
										.addComponent(btnRegistrarTrayecto)
										.addGap(34)
										.addComponent(btnVolver_3)))
						.addContainerGap(36, Short.MAX_VALUE))
				);
		gl_AgregarATrayectoAItinerario.setVerticalGroup(
				gl_AgregarATrayectoAItinerario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AgregarATrayectoAItinerario.createSequentialGroup()
						.addGap(22)
						.addGroup(gl_AgregarATrayectoAItinerario.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblOrigen, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxOrigen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_AgregarATrayectoAItinerario.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDestino)
								.addComponent(comboBoxDestino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_AgregarATrayectoAItinerario.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFechaSalida)
								.addComponent(comboBoxDia2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxMes2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxAno2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnMostrarAlternativas)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lblVuelosQueCumplen)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane_6, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_AgregarATrayectoAItinerario.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_AgregarATrayectoAItinerario.createSequentialGroup()
										.addGap(18)
										.addGroup(gl_AgregarATrayectoAItinerario.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblSeleccioneAgente)
												.addComponent(comboBoxAgentes2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_AgregarATrayectoAItinerario.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblSeleccioneItinerario)
												.addComponent(comboBoxItinerarios2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_AgregarATrayectoAItinerario.createSequentialGroup()
										.addGap(26)
										.addGroup(gl_AgregarATrayectoAItinerario.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnRegistrarTrayecto)
												.addComponent(btnVolver_3))))
						.addContainerGap(16, Short.MAX_VALUE))
				);

		tableVuelosEspecificos1 = new JTable();
		tableVuelosEspecificos1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"C\u00F3digo", "Aerol\u00EDnea", "N\u00FAmero vuelo", "Hora salida/llegada", "Cupos libres", "Tarifa", "Tipo", "IVA/Impuesto"
				}
				));
		scrollPane_6.setViewportView(tableVuelosEspecificos1);
		AgregarATrayectoAItinerario.setLayout(gl_AgregarATrayectoAItinerario);

		MostrarAerolineas = new JPanel();
		tabbedPane.addTab("Mostrar aerolineas", null, MostrarAerolineas, null);

		scrollPane = new JScrollPane();

		JScrollPane scrollPane_1 = new JScrollPane();

		JButton btnMostrarVuelosEspecficos = new JButton("Mostrar vuelos planeados");
		btnMostrarVuelosEspecficos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(m!=-1){
					borrarTabla(tableVuelosPlaneados1);
				}
				DefaultTableModel tm = (DefaultTableModel) tableAerolineas.getModel();
				key=Long.parseLong(String.valueOf(tm.getValueAt(tableAerolineas.getSelectedRow(),1)));
				for(VueloPlaneado vp:sistema.getListaAerolineas().get(key).getListaVuelosPlaneados()){
					agregarVueloPlaneado(vp,tableVuelosPlaneados1);
				}

				m++;
			}
		});

		JButton btnMostrarVuelosEspecficos_1 = new JButton("Mostrar vuelos espec\u00EDficos");
		btnMostrarVuelosEspecficos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableVuelosEspecificos.removeAll();
				DefaultTableModel tm = (DefaultTableModel) tableVuelosPlaneados1.getModel();
				int x=tableVuelosPlaneados1.getSelectedRow();
				for(VueloEspecifico ve:sistema.getListaAerolineas().get(key).getListaVuelosPlaneados().get(x).getListaVuelosEspecificos()){
					agregarVueloEspecifico(ve);
				}
			}
		});

		JLabel lblAerolneas = new JLabel("AEROL\u00CDNEAS");
		lblAerolneas.setForeground(Color.RED);

		JLabel lblVuelosPlaneados = new JLabel("VUELOS PLANEADOS");
		lblVuelosPlaneados.setForeground(Color.RED);

		JLabel lblNewLabel_2 = new JLabel("VUELOS ESPEC\u00CDFICOS");
		lblNewLabel_2.setForeground(Color.RED);

		JScrollPane scrollPane_3 = new JScrollPane();
		GroupLayout gl_MostrarAerolineas = new GroupLayout(MostrarAerolineas);
		gl_MostrarAerolineas.setHorizontalGroup(
				gl_MostrarAerolineas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MostrarAerolineas.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_MostrarAerolineas.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_MostrarAerolineas.createSequentialGroup()
										.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
										.addContainerGap())
								.addGroup(gl_MostrarAerolineas.createSequentialGroup()
										.addComponent(lblAerolneas)
										.addContainerGap(633, Short.MAX_VALUE))
								.addGroup(gl_MostrarAerolineas.createSequentialGroup()
										.addComponent(lblNewLabel_2)
										.addContainerGap(589, Short.MAX_VALUE))
								.addGroup(gl_MostrarAerolineas.createSequentialGroup()
										.addGroup(gl_MostrarAerolineas.createParallelGroup(Alignment.TRAILING)
												.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
												.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
												.addComponent(lblVuelosPlaneados, Alignment.LEADING))
										.addGroup(gl_MostrarAerolineas.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_MostrarAerolineas.createSequentialGroup()
														.addGap(18)
														.addComponent(btnMostrarVuelosEspecficos_1))
												.addGroup(gl_MostrarAerolineas.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(btnMostrarVuelosEspecficos)))
										.addGap(13))))
				);
		gl_MostrarAerolineas.setVerticalGroup(
				gl_MostrarAerolineas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MostrarAerolineas.createSequentialGroup()
						.addGroup(gl_MostrarAerolineas.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_MostrarAerolineas.createSequentialGroup()
										.addGap(12)
										.addComponent(lblAerolneas)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
										.addGap(7)
										.addComponent(lblVuelosPlaneados))
								.addGroup(gl_MostrarAerolineas.createSequentialGroup()
										.addGap(54)
										.addComponent(btnMostrarVuelosEspecficos)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_MostrarAerolineas.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnMostrarVuelosEspecficos_1))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblNewLabel_2)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);

		tableVuelosEspecificos = new JTable();
		tableVuelosEspecificos.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"C\u00F3digo", "Fecha", "Tipo vuelo", "Capacidad", "Cupos libres", "Tarifa", "Tipo vuelo", "IVA", "Valor pasaje"
				}
				));
		scrollPane_3.setViewportView(tableVuelosEspecificos);

		tableVuelosPlaneados1 = new JTable();
		tableVuelosPlaneados1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"C\u00F3digo", "NumVuelo", "D\u00EDa semana", "Hora salida/llegada", "Origen", "Destino"
				}
				));
		scrollPane_1.setViewportView(tableVuelosPlaneados1);

		tableAerolineas = new JTable();
		tableAerolineas.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Nombre", "C\u00F3digo", "C\u00F3digo banco"
				}
				));
		scrollPane.setViewportView(tableAerolineas);
		MostrarAerolineas.setLayout(gl_MostrarAerolineas);

		JPanel GenerarTiqueteElectronico = new JPanel();
		tabbedPane.addTab("Generar tiquete electr\u00F3nico", null, GenerarTiqueteElectronico, null);
		GenerarTiqueteElectronico.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("Agentes");
		lblNewLabel_6.setForeground(Color.RED);
		lblNewLabel_6.setBounds(220, 50, 46, 14);
		GenerarTiqueteElectronico.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Itinerarios");
		lblNewLabel_7.setForeground(Color.RED);
		lblNewLabel_7.setBounds(220, 110, 64, 14);
		GenerarTiqueteElectronico.add(lblNewLabel_7);

		comboBoxAgentesTiquete = new JComboBox();
		comboBoxAgentesTiquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxItinerariosTiquete.removeAllItems();
				String nombreAgente = (String) comboBoxAgentesTiquete.getSelectedItem();
				Agente agente = sistema.buscarAgente(nombreAgente);
				for(Itinerario i:agente.getListaItinerarios()){
					comboBoxItinerariosTiquete.addItem(i.getNombre());
				}
			}
		});
		comboBoxAgentesTiquete.setBounds(332, 47, 103, 20);
		GenerarTiqueteElectronico.add(comboBoxAgentesTiquete);

		comboBoxItinerariosTiquete = new JComboBox();
		comboBoxItinerariosTiquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comboBoxItinerariosTiquete.setBounds(332, 107, 103, 20);
		GenerarTiqueteElectronico.add(comboBoxItinerariosTiquete);

		JButton btnNewButton_2 = new JButton("Generar Tiquete");
		JFrame frameArchivo = this;
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreAgente = (String) comboBoxAgentesTiquete.getSelectedItem();
				Agente agente = sistema.buscarAgente(nombreAgente);
				String nombreItinerario = (String) comboBoxItinerariosTiquete.getSelectedItem();
				Itinerario itinerario = sistema.buscarItinerarioPorNombre(nombreAgente, nombreItinerario);

				if(!sistema.verificarEstadoItinerario(agente.getCodigo(),itinerario.getCodigo())){
					System.out.println("El itinerario no se ha comprado.");
				}
				else{

					JFileChooser chooser = new JFileChooser();
					int valor = chooser.showSaveDialog(frameArchivo);
					if(valor==JFileChooser.APPROVE_OPTION){
						File file=chooser.getSelectedFile();
						//File outFile = new File(archivo);
						FileOutputStream outStream = null;
						PrintWriter dataOutStream = null;
						try{
							outStream = new FileOutputStream(file);
							dataOutStream = new PrintWriter(outStream);
							dataOutStream.println(itinerario.toString());
							for(int i=0;i<itinerario.getListaTrayectos().size();i++){
								dataOutStream.println((String.format("%25s %25s %-20s %-20s %-20s %-20s %-20s %-20s", "ID","CODIGO","FECHA","TIPO AVION","CAPACIDAD","CUPOS LIBRES","TARIFA","IVA/IMPUESTO")));
								dataOutStream.println(itinerario.getListaTrayectos().get(i).toString());
								dataOutStream.println("Vuelo Planeado:");
								dataOutStream.println(String.format("%25s %-20s %-20s %-20s %-20s %-20s %25s %-20s %25s", "CODIGO","NUMERO VUELO","DIA SEMANA","HORA SALIDA","HORA LLEGADA","CIUDAD ORIGEN","CODIGO ORIGEN","CIUDAD DESTINO","CODIGO DESTINO"));
								dataOutStream.println(itinerario.getListaTrayectos().get(i).getVueloEspecifico().getVueloPlaneado().toString());
								dataOutStream.println("Sillas:");
								for(int j=0;j<itinerario.getListaTrayectos().get(i).getListaSillas().size();j++){
									dataOutStream.println(itinerario.getListaTrayectos().get(i).getListaSillas().get(j).getId());
								}
							}

							long valorAux = 0;
							long valorTotalItinerario=0;
							for(Pasajero p:itinerario.getListaPasajeros()){
								dataOutStream.println(String.format("%25s %25s %-20s", "IDENTIFICACION","NOMBRE","FECHA"));
								dataOutStream.println(p.toString());
								valorAux = p.calcularValorItinerario();
								if(p instanceof Menor){
									if(((Menor) p).isViajaSolo()){
										dataOutStream.println("El pasajero viaja solo durante sus vuelos.");
									}else{
										dataOutStream.println("El pasajero viajara acompaÃ±ado durante todos sus vuelos");
									}
								}else{
									if(((Mayor)p).isRequiereAsistencia()){
										dataOutStream.println("El pasajero necesia asistencia durante sus vuelos.");
									}else{
										dataOutStream.println("El pasajero no requiere de asistencia durante sus vuelos.");
									}
								}
								dataOutStream.println("Valor por pasajero: $"+valorAux);
								valorTotalItinerario += valorAux;

							}
							itinerario.setValor(valorTotalItinerario);
							dataOutStream.println("Valor total itinerario: $"+valorTotalItinerario);

							dataOutStream.close();
							outStream.close();

						}catch(Exception ex){
							ex.getStackTrace();
							System.out.println(ex.getMessage());
						}finally{

						}

					}
					/*
							String archivo;
							Scanner scann=new Scanner(System.in);
							System.out.println("Ingrese el nombre del archivo");
							archivo=scann.nextLine();
					 */


				}



			}
		});
		btnNewButton_2.setBounds(271, 167, 134, 23);
		GenerarTiqueteElectronico.add(btnNewButton_2);

		JButton btnRegresar_1 = new JButton("Regresar");
		btnRegresar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnRegresar_1.setBounds(567, 257, 89, 23);
		GenerarTiqueteElectronico.add(btnRegresar_1);

		JPanel MostrarAgentes = new JPanel();
		tabbedPane.addTab("Mostrar agentes", null, MostrarAgentes, null);

		JLabel lblAgentes = new JLabel("Agentes");
		lblAgentes.setForeground(Color.RED);

		comboBoxAgentes1 = new JComboBox<String>();

		JLabel lblItinerarios = new JLabel("Itinerarios");
		lblItinerarios.setForeground(Color.RED);

		JButton btnMostrarTrayecto = new JButton("Mostrar trayecto");
		btnMostrarTrayecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Itinerario it = null;
				String nombre=(String) ((DefaultTableModel) tableItinerarios.getModel()).getValueAt(tableItinerarios.getSelectedRow(), 1);
				for(Agente a:sistema.getListaAgentes().values()){
					for(Itinerario i:a.getListaItinerarios()){
						if(nombre.equals(i.getNombre())){
							it=i;
						}
					}
				}
				Vector fila=new Vector<>();	
				for(Trayecto t:it.getListaTrayectos()){
					System.out.println(t.getVueloEspecifico().getCodigo());
					fila.add(t.getVueloEspecifico().getCodigo());
					fila.add(t.getVueloEspecifico().getVueloPlaneado().getAerolinea().getNombre());
					fila.add(t.getVueloEspecifico().getVueloPlaneado().getNumeroVuelo());
					fila.add(t.getVueloEspecifico().getFecha());
					fila.add(t.getVueloEspecifico().getVueloPlaneado().getHoraSalida()+"/"+t.getVueloEspecifico().getVueloPlaneado().getHoraLlegada());
					fila.add(t.getVueloEspecifico().getTarifa());
					if(t.getVueloEspecifico() instanceof VueloEspecificoInternacional){
						fila.add("Internacional");
						fila.add(((VueloEspecificoInternacional) t.getVueloEspecifico()).getImpuestoSalida());
					}
					else{
						fila.add("Nacional");
						fila.add(((VueloEspecificoNacional)t.getVueloEspecifico()).getIva());
					}
					((DefaultTableModel) tableTrayectos.getModel()).addRow(fila);
				}
			}
		});

		JScrollPane scrollPane_4 = new JScrollPane();

		JButton btnMostrarItinerario = new JButton("Mostrar Itinerario");
		btnMostrarItinerario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agente a = null;
				String nombreA=(String) comboBoxAgentes1.getSelectedItem();
				Set<Long> keys=sistema.getListaAgentes().keySet();
				for(Long k:keys){
					if(sistema.getListaAgentes().get(k).getNombre().equals(nombreA)){
						a=sistema.getListaAgentes().get(k);
					}
				}
				Vector<Comparable> fila=new Vector<Comparable>();
				for(Itinerario i:a.getListaItinerarios()){
					fila.add(i.getCodigo());
					fila.add(i.getNombre());
				}
				((DefaultTableModel) tableItinerarios.getModel()).addRow(fila);
			}
		});

		JScrollPane scrollPane_5 = new JScrollPane();

		JLabel lblTrayectos = new JLabel("Trayectos");
		lblTrayectos.setForeground(Color.RED);
		GroupLayout gl_MostrarAgentes = new GroupLayout(MostrarAgentes);
		gl_MostrarAgentes.setHorizontalGroup(
				gl_MostrarAgentes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MostrarAgentes.createSequentialGroup()
						.addGap(22)
						.addGroup(gl_MostrarAgentes.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_MostrarAgentes.createSequentialGroup()
										.addGroup(gl_MostrarAgentes.createParallelGroup(Alignment.LEADING)
												.addComponent(lblItinerarios)
												.addComponent(lblAgentes))
										.addGap(45)
										.addGroup(gl_MostrarAgentes.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_MostrarAgentes.createSequentialGroup()
														.addComponent(comboBoxAgentes1, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
														.addGap(40)
														.addComponent(btnMostrarItinerario))
												.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)))
								.addComponent(btnMostrarTrayecto, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(386, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_MostrarAgentes.createSequentialGroup()
						.addContainerGap(22, Short.MAX_VALUE)
						.addGroup(gl_MostrarAgentes.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTrayectos)
								.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 674, GroupLayout.PREFERRED_SIZE))
						.addGap(194))
				);
		gl_MostrarAgentes.setVerticalGroup(
				gl_MostrarAgentes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MostrarAgentes.createSequentialGroup()
						.addGap(24)
						.addGroup(gl_MostrarAgentes.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAgentes)
								.addComponent(comboBoxAgentes1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnMostrarItinerario))
						.addGap(18)
						.addGroup(gl_MostrarAgentes.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblItinerarios)
								.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addComponent(btnMostrarTrayecto)
						.addGap(20)
						.addComponent(lblTrayectos)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addGap(40))
				);

		tableTrayectos = new JTable();
		tableTrayectos.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"C\u00F3digo trayecto", "Aerolinea", "N\u00FAmero vuelo", "Fecha", "Hora salida//llegada", "Tarifa", "Tipo", "IVA/Impuesto"
				}
				));
		scrollPane_5.setViewportView(tableTrayectos);

		tableItinerarios = new JTable();
		tableItinerarios.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"N\u00FAmero", "Itinerario"
				}
				));
		scrollPane_4.setViewportView(tableItinerarios);
		MostrarAgentes.setLayout(gl_MostrarAgentes);

		JPanel ComprarItinerario = new JPanel();
		tabbedPane.addTab("Comprar itinerario", null, ComprarItinerario, null);
		ComprarItinerario.setLayout(null);

		JLabel lblAgentes_1 = new JLabel("Agentes");
		lblAgentes_1.setForeground(Color.RED);
		lblAgentes_1.setBounds(164, 11, 46, 14);
		ComprarItinerario.add(lblAgentes_1);

		JLabel lblItinerarios_1 = new JLabel("Itinerarios");
		lblItinerarios_1.setForeground(Color.RED);
		lblItinerarios_1.setBounds(164, 36, 68, 14);
		ComprarItinerario.add(lblItinerarios_1);

		JLabel lblPasajeros = new JLabel("Pasajeros");
		lblPasajeros.setForeground(Color.RED);
		lblPasajeros.setBounds(164, 71, 68, 14);
		ComprarItinerario.add(lblPasajeros);

		comboBoxAgentesItinerarios = new JComboBox();
		comboBoxAgentesItinerarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxItinerarios.removeAllItems();
				String aux = (String) comboBoxAgentesItinerarios.getSelectedItem();
				Agente agente = sistema.buscarAgente(aux);
				for(Itinerario i:agente.getListaItinerarios()){
					comboBoxItinerarios.addItem(i.getNombre());
				}
			}
		});
		comboBoxAgentesItinerarios.setBounds(286, 8, 138, 20);
		ComprarItinerario.add(comboBoxAgentesItinerarios);

		comboBoxItinerarios = new JComboBox();
		comboBoxItinerarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxPasajeros.removeAllItems();
				String nombreItinerario = (String)comboBoxItinerarios.getSelectedItem();
				String nombreAgente = (String) comboBoxAgentesItinerarios.getSelectedItem();
				Itinerario itinerario = sistema.buscarItinerarioPorNombre(nombreAgente, nombreItinerario);
				for(Pasajero p:itinerario.getListaPasajeros()){
					if(p instanceof Mayor){
						comboBoxPasajeros.addItem(p.getIdentificacion()+"-"+p.getNombre()+"-(Mayor)");
					}else{
						if(p instanceof Menor){
							if(p instanceof Mayor){
								comboBoxPasajeros.addItem(p.getIdentificacion()+"-"+p.getNombre()+"-(Menor)");
							}
						}
					}
				}
			}
		});
		comboBoxItinerarios.setBounds(286, 36, 138, 20);
		ComprarItinerario.add(comboBoxItinerarios);

		comboBoxPasajeros = new JComboBox();
		comboBoxPasajeros.setBounds(286, 68, 138, 20);
		ComprarItinerario.add(comboBoxPasajeros);

		JButton btnMostrarTrayectos = new JButton("Mostrar Trayectos");
		btnMostrarTrayectos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarTabla(tablaTrayectos);
				Itinerario it = null;
				String nombre=String.valueOf(comboBoxItinerarios.getSelectedItem());
				for(Agente a:sistema.getListaAgentes().values()){
					for(Itinerario i:a.getListaItinerarios()){
						if(nombre.equals(i.getNombre())){
							it=i;
						}
					}
				}
				Vector fila=new Vector<>();	
				for(Trayecto t:it.getListaTrayectos()){
					System.out.println(t.getVueloEspecifico().getCodigo());
					fila.add(t.getId());
					fila.add(t.getVueloEspecifico().getVueloPlaneado().getAerolinea().getNombre());
					fila.add(t.getVueloEspecifico().getVueloPlaneado().getNumeroVuelo());
					fila.add(t.getVueloEspecifico().getFecha());
					fila.add(t.getVueloEspecifico().getVueloPlaneado().getHoraSalida()+"/"+t.getVueloEspecifico().getVueloPlaneado().getHoraLlegada());
					fila.add(t.getVueloEspecifico().getTarifa());
					if(t.getVueloEspecifico() instanceof VueloEspecificoInternacional){
						fila.add("Internacional");
						System.out.println(((VueloEspecificoInternacional) t.getVueloEspecifico()).getImpuestoSalida());
						fila.add(((VueloEspecificoInternacional) t.getVueloEspecifico()).getImpuestoSalida());
					}
					else{
						fila.add("Nacional");
						fila.add(((VueloEspecificoNacional)t.getVueloEspecifico()).getIva());
					}
					((DefaultTableModel) tablaTrayectos.getModel()).addRow(fila);
				}
			}
		});
		btnMostrarTrayectos.setBounds(277, 99, 121, 23);
		ComprarItinerario.add(btnMostrarTrayectos);

		JLabel lblNewLabel_5 = new JLabel("Trayectos");
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setBounds(142, 130, 68, 14);
		ComprarItinerario.add(lblNewLabel_5);

		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(22, 155, 635, 103);
		ComprarItinerario.add(scrollPane_7);

		tablaTrayectos = new JTable();
		tablaTrayectos.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Codigo", "Aerolinea", "Numero Vuelo", "fecha", "Hora salida/llegada", "Tarifa", "Tipo", "IVA/Impuesto"
				}
				));
		scrollPane_7.setViewportView(tablaTrayectos);

		JButton botonMostrarSillasDisponibles = new JButton("Mostrar Sillas Disponibles");
		botonMostrarSillasDisponibles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxSilla.removeAllItems();
				int codigo =  (int) ((DefaultTableModel) tablaTrayectos.getModel()).getValueAt(tablaTrayectos.getSelectedRow(), 0);
				String nombreAgente = (String) comboBoxAgentesItinerarios.getSelectedItem();
				String nombreItinerario = (String) comboBoxItinerarios.getSelectedItem();
				Itinerario itinerario = sistema.buscarItinerarioPorNombre(nombreAgente, nombreItinerario);
				Trayecto trayecto = itinerario.buscarTrayecto(codigo);
				boolean hayCupos = sistema.verificarCupoEnTrayectos(itinerario.getAgente().getCodigo(),itinerario.getCodigo());
				if(hayCupos){
					sistema.calcularValorItinerario(itinerario.getAgente().getCodigo(),itinerario.getCodigo());
					long valorItinerario = sistema.obtenerValorItinerario(itinerario.getAgente().getCodigo(),itinerario.getCodigo());
					sistema.asignarItinerarioComoComprado(itinerario.getAgente().getCodigo(),itinerario.getCodigo());
					VueloEspecifico vueloEspecifico=trayecto.getVueloEspecifico();
					List<Silla> listaSillas=trayecto.getListaSillas();
					List<Silla> nuevaLista=new ArrayList<>();
					List<Silla> aux=new ArrayList<>();
					if(trayecto.getListaSillas().isEmpty()){
						for(int j=0;j<vueloEspecifico.getListaSillas().size();j++){
							comboBoxSilla.addItem(vueloEspecifico.getListaSillas().get(j).getId());
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
							comboBoxSilla.addItem(silla.getId());
						}
					}
				}
			}
		});
		botonMostrarSillasDisponibles.setBounds(268, 269, 156, 23);
		ComprarItinerario.add(botonMostrarSillasDisponibles);

		JLabel lblSeleccioneSilla = new JLabel("Seleccione silla");
		lblSeleccioneSilla.setBounds(162, 315, 89, 14);
		ComprarItinerario.add(lblSeleccioneSilla);

		comboBoxSilla = new JComboBox();
		comboBoxSilla.setBounds(286, 312, 89, 20);
		ComprarItinerario.add(comboBoxSilla);

		JLabel lblFila = new JLabel("fila");
		lblFila.setBounds(286, 292, 46, 14);
		ComprarItinerario.add(lblFila);

		JLabel lblSilla = new JLabel("silla");
		lblSilla.setBounds(330, 292, 46, 14);
		ComprarItinerario.add(lblSilla);

		JButton btnRegistrarSilla = new JButton("Registrar Silla");
		btnRegistrarSilla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tablaTrayectos.getSelectedRow();
				int codigo = (int) tablaTrayectos.getModel().getValueAt(fila, 0);
				String nombreAgente = (String) comboBoxAgentesItinerarios.getSelectedItem();
				String nombreItinerario = (String) comboBoxItinerarios.getSelectedItem();
				Itinerario itinerario = sistema.buscarItinerarioPorNombre(nombreAgente, nombreItinerario);
				Trayecto trayecto = null;

				for(Trayecto t:itinerario.getListaTrayectos()){
					if(t.getId() == codigo){
						trayecto = t;
					}
				}
				String idSilla=(String) comboBoxSilla.getSelectedItem();
				String identificacion = (String) comboBoxPasajeros.getSelectedItem();
				StringTokenizer tokenizer = new StringTokenizer(identificacion, "-");
				String aux;
				aux = tokenizer.nextToken().trim();
				System.out.println("iden "+aux);
				identificacion = aux;
				long valorItinerario = sistema.obtenerValorItinerario(itinerario.getAgente().getCodigo(),itinerario.getCodigo());
				sistema.marcarSillaComoComprada(itinerario.getAgente().getCodigo(), itinerario.getCodigo(),trayecto.getId(), idSilla, identificacion);
			}
		});
		btnRegistrarSilla.setBounds(268, 361, 109, 23);
		ComprarItinerario.add(btnRegistrarSilla);

		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(568, 346, 89, 23);
		ComprarItinerario.add(btnRegresar);

		JPanel AgregarItinerario = new JPanel();
		tabbedPane.addTab("Agregar itinerario", null, AgregarItinerario, null);

		comboBoxAgentes = new JComboBox<String>();

		JLabel label_1 = new JLabel("Agentes");
		label_1.setForeground(Color.RED);

		JLabel lblNewLabel_3 = new JLabel("Nombre nuevo itinerario");

		textNombreItinerario = new JTextField();
		textNombreItinerario.setColumns(10);

		JButton btnAgregarItinerario = new JButton("Agregar itinerario");
		btnAgregarItinerario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarItinerario(sistema);
			}
		});

		JLabel lblNewLabel_4 = new JLabel("Datos de un pasajero");
		lblNewLabel_4.setForeground(Color.RED);

		JLabel lblIdentificacin = new JLabel("Identificaci\u00F3n");

		JLabel lblNombre = new JLabel("Nombre");

		textIdentificacion = new JTextField();
		textIdentificacion.setColumns(10);

		textNombrePasajero = new JTextField();
		textNombrePasajero.setColumns(10);

		JLabel lblFechaNacimiento = new JLabel("Fecha nacimiento");

		JLabel lblViajaSoloasistencias = new JLabel("Viaja solo/asistencia");

		radioSi = new JRadioButton("Si");

		radioNo = new JRadioButton("No");

		JButton btnRegistrarPasajero = new JButton("Registrar pasajero");
		btnRegistrarPasajero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarPasajero(sistema);
			}
		});

		JButton btnVolver_2 = new JButton("Volver");
		btnVolver_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});

		comboBoxDia1 = new JComboBox<Integer>();

		comboBoxMes1 = new JComboBox<Integer>();

		comboBoxAno1 = new JComboBox<Integer>();

		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});

		JLabel label_2 = new JLabel("");
		GroupLayout gl_AgregarItinerario = new GroupLayout(AgregarItinerario);
		gl_AgregarItinerario.setHorizontalGroup(
				gl_AgregarItinerario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AgregarItinerario.createSequentialGroup()
						.addGroup(gl_AgregarItinerario.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_AgregarItinerario.createSequentialGroup()
										.addGap(31)
										.addGroup(gl_AgregarItinerario.createParallelGroup(Alignment.LEADING)
												.addComponent(btnAgregarItinerario)
												.addComponent(lblNewLabel_4)
												.addGroup(gl_AgregarItinerario.createSequentialGroup()
														.addComponent(lblViajaSoloasistencias)
														.addGap(18)
														.addComponent(radioSi)
														.addGap(18)
														.addComponent(radioNo))
												.addGroup(gl_AgregarItinerario.createSequentialGroup()
														.addGroup(gl_AgregarItinerario.createParallelGroup(Alignment.LEADING)
																.addComponent(lblNewLabel_3)
																.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblFechaNacimiento)
																.addComponent(lblNombre)
																.addComponent(lblIdentificacin))
														.addGroup(gl_AgregarItinerario.createParallelGroup(Alignment.LEADING)
																.addComponent(textNombrePasajero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addComponent(textIdentificacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addGroup(gl_AgregarItinerario.createSequentialGroup()
																		.addGroup(gl_AgregarItinerario.createParallelGroup(Alignment.LEADING)
																				.addGroup(gl_AgregarItinerario.createSequentialGroup()
																						.addComponent(comboBoxDia1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
																						.addGap(18)
																						.addComponent(comboBoxMes1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
																						.addGap(18)
																						.addComponent(comboBoxAno1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
																				.addGroup(gl_AgregarItinerario.createSequentialGroup()
																						.addGap(25)
																						.addGroup(gl_AgregarItinerario.createParallelGroup(Alignment.LEADING)
																								.addComponent(textNombreItinerario, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
																								.addComponent(comboBoxAgentes, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))))
																		.addGap(74)
																		.addComponent(btnRegistrarPasajero)
																		.addGap(18)
																		.addComponent(btnNewButton_1)
																		.addGap(138)
																		.addComponent(btnVolver_2))))))
								.addGroup(gl_AgregarItinerario.createSequentialGroup()
										.addGap(335)
										.addComponent(label_2)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		gl_AgregarItinerario.setVerticalGroup(
				gl_AgregarItinerario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AgregarItinerario.createSequentialGroup()
						.addGroup(gl_AgregarItinerario.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_AgregarItinerario.createSequentialGroup()
										.addGap(21)
										.addGroup(gl_AgregarItinerario.createParallelGroup(Alignment.BASELINE)
												.addComponent(label_1)
												.addComponent(comboBoxAgentes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_AgregarItinerario.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_3)
												.addComponent(textNombreItinerario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addComponent(btnAgregarItinerario)
										.addGap(21)
										.addComponent(lblNewLabel_4)
										.addGap(18)
										.addGroup(gl_AgregarItinerario.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblIdentificacin)
												.addComponent(textIdentificacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_AgregarItinerario.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNombre)
												.addComponent(textNombrePasajero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_AgregarItinerario.createSequentialGroup()
										.addGap(45)
										.addComponent(label_2)))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_AgregarItinerario.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnVolver_2)
								.addComponent(lblFechaNacimiento)
								.addComponent(comboBoxDia1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxMes1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxAno1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRegistrarPasajero)
								.addComponent(btnNewButton_1))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_AgregarItinerario.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblViajaSoloasistencias)
								.addComponent(radioSi)
								.addComponent(radioNo))
						.addContainerGap(120, Short.MAX_VALUE))
				);
		initButtonGroup();
		AgregarItinerario.setLayout(gl_AgregarItinerario);
		for(int i=1;i<=31;i++){
			comboBoxDia.addItem(i);
			comboBoxDia1.addItem(i);

		}
		for(int i=1;i<=12;i++){
			comboBoxMes.addItem(i);
			comboBoxMes1.addItem(i);
		}
		java.util.Date fecha = new Date();
		for(int i=2017;i<=2019;i++){
			comboBoxAno.addItem(i);
		}
		for(int i=1900;i<=2017;i++){
			comboBoxAno1.addItem(i);
		}
		}
		public void volver(){
			tabbedPane.setSelectedIndex(0);
		}
		public void agregarAerolinea(long k){

			Vector<Comparable> fila=new Vector<Comparable>();

			fila.add(sistema.getListaAerolineas().get(k).getNombre());
			fila.add(sistema.getListaAerolineas().get(k).getCodigo());
			fila.add(sistema.getListaAerolineas().get(k).getCuentaBanco());
			vec.add(fila);
			vecV=new Vector<String>(Arrays.asList(this.aerolineasNom));
			tableAerolineas = new JTable(vec, vecV);
		}
		public void agregarVueloPlaneado(VueloPlaneado vp,JTable table){
			Vector<Comparable> fila=new Vector<Comparable>();	
			fila.add(vp.getCodigo());
			fila.add(vp.getNumeroVuelo());
			fila.add(vp.getDiaSemana());
			fila.add(vp.getHoraSalida());
			fila.add(vp.getOrigen().getNombre());
			fila.add(vp.getDestino().getNombre());
			((DefaultTableModel) table.getModel()).addRow(fila);

		}
		public void agregarVueloEspecifico(VueloEspecifico ve){
			Vector<Comparable> fila=new Vector<Comparable>();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
			String fechaAux = ve.getFecha().format(formatter);
			fila.add(ve.getCodigo());
			fila.add(fechaAux);
			fila.add(ve.getTipoAvion());
			fila.add(ve.getCapacidad());
			fila.add(ve.getCuposLibres());
			fila.add(ve.getTarifa());
			if(ve instanceof VueloEspecificoInternacional){
				fila.add("Internacional");
				fila.add(((VueloEspecificoInternacional) ve).getImpuestoSalida());
			}
			else{
				fila.add("Nacional");
				fila.add(((VueloEspecificoNacional)ve).getIva());
			}
			fila.add(ve.getTarifa());

			((DefaultTableModel) tableVuelosEspecificos.getModel()).addRow(fila);
		}
		public void borrarTabla(JTable tabla){
			DefaultTableModel temp = (DefaultTableModel) tabla.getModel();
			int a =temp.getRowCount();
			for(int i=0; i<a; i++)
				temp.removeRow(0);
		}
		public void agregarVueloEspecificoAVueloPlaneado(SistemaVuelos sistema){
			try{
				long codigoAerolinea = 0;
				long codigoVueloPlaneado;
				LocalDate fecha;
				int anio=(int) comboBoxAno.getSelectedItem();
				int mes=(int) comboBoxMes.getSelectedItem();
				int dia=(int) comboBoxDia.getSelectedItem();
				fecha = LocalDate.of(anio, mes, dia);
				String tipoAvion=String.valueOf(comboBoxTipoAvion.getSelectedItem());
				int capacidad=Integer.parseInt(textCapacidad.getText());
				long tarifa=Long.parseLong(textTarifaBasica.getText());
				int iva;
				long impuesto;
				String ivaOImpuesto=textImpuestoOIva.getText();
				TipoAvion tipo= (TipoAvion) comboBoxTipoAvion.getSelectedItem();
				String nombreAerolinea=String.valueOf(comboBoxAerolineas.getSelectedItem());
				Set<Long> keys=sistema.getListaAerolineas().keySet();
				for(Long k:keys){	
					if(sistema.getListaAerolineas().get(k).getNombre().equals(nombreAerolinea)){
						codigoAerolinea=sistema.getListaAerolineas().get(k).getCodigo();
					}
				}
				DefaultTableModel tm = (DefaultTableModel) tableVuelosPlaneados.getModel();
				codigoVueloPlaneado=Long.parseLong(String.valueOf(tm.getValueAt(tableVuelosPlaneados.getSelectedRow(),0)));
				if(tipo.equals("NACIONAL")||tipo.equals("nacional")){
					iva=Integer.parseInt(ivaOImpuesto.trim());
					sistema.agregarVueloEspecificoNacional(codigoAerolinea, codigoVueloPlaneado, fecha, tipoAvion, capacidad, tarifa, iva,tipo);
				}
				else{
					impuesto=Long.parseLong(ivaOImpuesto.trim());
					sistema.agregarVueloEspecificoInternacional(codigoAerolinea, codigoVueloPlaneado, fecha, tipoAvion, capacidad, tarifa, impuesto,tipo);

				}
				JOptionPane.showMessageDialog( null,"Se creó el vuelo específico exitosamente");
			}catch(Exception e){
				JOptionPane.showMessageDialog( null,"No es posible crear el vuelo específico");
				e.printStackTrace();
			}
		}
		public void agregarItinerario(SistemaVuelos sistemaVuelo){
			try{
				String nombre;
				Scanner input=new Scanner(System.in);
				nombre=textNombreItinerario.getText();
				String x=(String) comboBoxAgentes.getSelectedItem();
				Set<Long> keys=sistema.getListaAgentes().keySet();
				for(Long k:keys){ 
					if(sistema.getListaAgentes().get(k).getNombre().equals(x)){
						codigoAgente=sistema.getListaAgentes().get(k).getCodigo();
					}
				}
				sistemaVuelo.agregarItinerario(codigoAgente,nombre);

				codigoItinerario = sistemaVuelo.buscarCodigoItinerario(codigoAgente, nombre);
				JOptionPane.showMessageDialog( null,"Se creó el itinerario exitosamente");

			}
			catch(Exception e){
				JOptionPane.showMessageDialog( null,"No es posible crear el itinerario");
				e.printStackTrace();
			}
		}
		public void agregarPasajero(SistemaVuelos sistema){
			try{
				String Nombre,identificacion;
				LocalDate fechaNacimiento;
				int anio,mes,dia;
				boolean viajaSoloAsistencia = false;
				Nombre=textNombrePasajero.getText();
				identificacion=textIdentificacion.getText();
				anio=(int) comboBoxAno1.getSelectedItem();
				mes=(int) comboBoxMes1.getSelectedItem();
				dia=(int) comboBoxDia1.getSelectedItem();
				fechaNacimiento = LocalDate.of(anio, mes, dia);
				JRadioButton boton = (JRadioButton) buttonGroup.getSelection();
				if(radioSi.isSelected()){
					viajaSoloAsistencia=true;
				}
				sistema.agregarPasajero(codigoAgente,codigoItinerario, identificacion, Nombre,fechaNacimiento,viajaSoloAsistencia);
				JOptionPane.showMessageDialog( null,"Se creó el pasajero exitosamente");
			}
			catch(Exception e){
				JOptionPane.showMessageDialog( null,"No es posible crear el pasajero");
			}

		}
		private void initButtonGroup() {
			buttonGroup = new ButtonGroup();
			buttonGroup.add(this.getRadioNo());
			buttonGroup.add(this.getRadioSi());
		}

		public JRadioButton getRadioSi() {
			return radioSi;
		}

		public void setRadioSi(JRadioButton radioSi) {
			this.radioSi = radioSi;
		}

		public JRadioButton getRadioNo() {
			return radioNo;
		}

		public void setRadioNo(JRadioButton radioNo) {
			this.radioNo = radioNo;
		}
	}
