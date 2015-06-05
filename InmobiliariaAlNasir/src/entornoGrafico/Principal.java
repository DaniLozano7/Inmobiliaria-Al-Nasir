package entornoGrafico;

import inmobiliariaAlNasir.Fichero;
import inmobiliariaAlNasir.Inmobiliaria;
import inmobiliariaAlNasir.ValorInvalidoException;
import inmobiliariaAlNasir.ViviendaExistenteException;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;

/**
 * Clase principal de nuestro entorno grafico
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 */
public class Principal {

	/**
	 * Ventana principal de nuestro GUI
	 */
	static JFrame frame;
	/**
	 * Herramienta utilizada para el uso de ficheros
	 */
	private JFileChooser fileChooser = new JFileChooser();
	/**
	 * Filtro de busqueda de archivos
	 */
	static FileNameExtensionFilter filtro = new FileNameExtensionFilter(
			"Archivos .inm", "inm");

	/**
	 * Lanza la aplicacion
	 * 
	 * @param args
	 *            Cadena de String
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Principal();
					frame.setTitle(General.archivo.getName());
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame,
							"No se ha podido cargar la ventana", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Crea la aplicación.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Inicializa los contenidos de la ventana.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				VerAyuda.class.getResource("/img/icono.png")));

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				salir();
			}
		});
		frame.setBounds(100, 100, 580, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setMnemonic('f');
		menuBar.add(mnArchivo);

		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevo();
			}

		});
		mnArchivo.add(mntmNuevo);

		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrir();

			}

		});
		mnArchivo.add(mntmAbrir);

		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar();

			}

		});
		mnArchivo.add(mntmGuardar);

		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como");
		mntmGuardarComo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				InputEvent.CTRL_MASK));
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarComo();
			}

		});
		mnArchivo.add(mntmGuardarComo);

		JSeparator separator_1 = new JSeparator();
		mnArchivo.add(separator_1);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salir();
			}

		});
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,
				InputEvent.CTRL_MASK));
		mnArchivo.add(mntmSalir);

		JMenu mnModificar = new JMenu("Modificar");
		menuBar.add(mnModificar);

		JMenu mnAadir = new JMenu("A\u00F1adir");
		mnModificar.add(mnAadir);

		JMenuItem mntmCasa = new JMenuItem("Casa");
		mntmCasa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmCasa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				añadirCasa();
			}

		});
		mnAadir.add(mntmCasa);

		JMenuItem mntmPiso = new JMenuItem("Piso");
		mntmPiso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmPiso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				añadirPiso();
			}

		});
		mnAadir.add(mntmPiso);

		JMenuItem mntmCasaRural = new JMenuItem("Casa Rural");
		mntmCasaRural.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmCasaRural.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				añadirCasaRural();
			}

		});
		mnAadir.add(mntmCasaRural);

		JMenuItem mntmEliminar = new JMenuItem("Eliminar");
		mntmEliminar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				InputEvent.CTRL_MASK));
		mntmEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}

		});
		mnModificar.add(mntmEliminar);

		JSeparator separator = new JSeparator();
		mnModificar.add(separator);

		JMenuItem mntmAlquilar = new JMenuItem("Alquilar");
		mntmAlquilar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmAlquilar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alquilar();
			}

		});
		mnModificar.add(mntmAlquilar);

		JMenuItem mntmVender = new JMenuItem("Vender");
		mntmVender.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vender();
			}

		});
		mnModificar.add(mntmVender);

		JMenu mnMostrar = new JMenu("Mostrar");
		menuBar.add(mnMostrar);

		JMenuItem mntmMostrarVivienda = new JMenuItem("Mostrar Vivienda");
		mntmMostrarVivienda.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_M, InputEvent.CTRL_MASK));
		mntmMostrarVivienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarVivienda();
			}

		});
		mnMostrar.add(mntmMostrarVivienda);

		JMenuItem mntmMostrarInmobiliaria = new JMenuItem(
				"Mostrar Inmobiliaria");
		mntmMostrarInmobiliaria.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mntmMostrarInmobiliaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarInmobiliaria();
			}

		});
		mnMostrar.add(mntmMostrarInmobiliaria);

		JSeparator separator_2 = new JSeparator();
		mnMostrar.add(separator_2);

		JMenuItem mntmMostrarAlquiladas = new JMenuItem("Mostrar Alquiladas");
		mntmMostrarAlquiladas.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_A, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmMostrarAlquiladas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarViviendasAlquiladas();

			}

		});
		mnMostrar.add(mntmMostrarAlquiladas);

		JMenuItem mntmMostrarVendidas = new JMenuItem("Mostrar Vendidas");
		mntmMostrarVendidas.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_V, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmMostrarVendidas.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				mostrarViviendasVendidas();
			}

		});
		mnMostrar.add(mntmMostrarVendidas);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mntmVerAyuda = new JMenuItem("Ver ayuda");
		mntmVerAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.ALT_MASK));
		mntmVerAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verAyuda();
			}

		});
		mnAyuda.add(mntmVerAyuda);

		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.ALT_MASK));
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								frame,
								"Programa realizado por Daniel Lozano Torrico, alumno del IES Gran Capitan v1.0");
			}
		});
		mnAyuda.add(mntmAcercaDe);

		/**
		 * Añadir imagen a la ventana principal
		 */
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource(
				"/img/inmobiliaria.png")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(29, 21, 450, 300);
		frame.getContentPane().add(label);
	}

	/**
	 * Crea una nueva inmobiliaria. Si la inmobiliaria actual ha sido
	 * modificada, nos preguntará si deseamos guardarla previamente
	 */
	private void nuevo() {
		if (General.lista.isModificado()) {
			int n = JOptionPane
					.showOptionDialog(
							frame.getContentPane(),
							"¿Desea guardar los cambios realizados en la inmobiliaria?",
							"Confirmar", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);
			switch (n) {
			case JOptionPane.YES_OPTION:
				guardar();
				break;
			case JOptionPane.NO_OPTION:
				break;
			case JOptionPane.CANCEL_OPTION:
				return;
			}
		}
		General.lista = new Inmobiliaria();
		General.archivo = new File("Sin_titulo.inm");
		General.lista.setModificado(false);
		frame.setTitle(General.archivo.getName());
	}

	/**
	 * Recupera el contenido de un fichero, en caso de que la inmobiliaria haya
	 * sido modificada, preguntará al usuario si desea guardarlo previamente
	 */
	private void abrir() {
		if (General.lista.isModificado()) {
			int n = JOptionPane
					.showOptionDialog(
							frame.getContentPane(),
							"¿Desea guardar los cambios realizados en la inmobiliaria?",
							"Confirmar", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);
			switch (n) {
			case JOptionPane.YES_OPTION:
				guardar();
				break;
			case JOptionPane.NO_OPTION:
				break;
			case JOptionPane.CANCEL_OPTION:
				return;
			}
		}
		int seleccion = fileChooser.showOpenDialog(frame);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			try {
				File file = fileChooser.getSelectedFile();
				General.lista = Fichero.abrir(file);
				General.archivo = file;
				frame.setTitle(General.archivo.getName());
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(frame.getContentPane(),
						"Fichero con información distinta a la esperada.",
						"Error", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(frame.getContentPane(),
						"El sistema no puede abrir el fichero", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Guarda el contenido en un fichero. Si no hay un fichero previo, se
	 * solicita el nombre del mismo para su posterior creación
	 */
	private void guardar() {
		if (General.archivo == null
				|| General.archivo.getName().equals("Sin_titulo.inm"))
			guardarComo();
		else {
			try {
				File file = fileChooser.getSelectedFile();
				Fichero.guardar(file, General.lista);
				General.lista.setModificado(false);
				General.archivo = file;
				frame.setTitle(General.archivo.getName());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(frame.getContentPane(),
						"El sistema no puede guardar el fichero", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Guarda el contenido en un fichero solicitando el nombre del mismo. En
	 * caso de existir un fichero con el mismo nombre, se pregunta al usuario si
	 * desea sobreescribirlo
	 */
	private void guardarComo() {
		int seleccion = fileChooser.showSaveDialog(frame);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			try {
				File file = fileChooser.getSelectedFile();
				if (Fichero.isExists(file)) {
					int n = JOptionPane.showOptionDialog(
							frame.getContentPane(),
							"El fichero ya existe. ¿Desea sobreescribirlo?",
							"Confirmar", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);
					switch (n) {
					case JOptionPane.NO_OPTION:
						return;
					}
				}
				Fichero.guardar(file, General.lista);
				General.lista.setModificado(false);
				General.archivo = file;
				frame.setTitle(General.archivo.getName());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(frame.getContentPane(),
						"El sistema no puede guardar el fichero", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Cierra el programa. Si la inmobiliaria actual ha sufrido cambios y estos
	 * no han sido guardados en el fichero, preguntara si desea guardar los
	 * cambios antes de cerrar
	 */
	private void salir() {
		if (General.lista.isModificado()) {
			int n = JOptionPane
					.showOptionDialog(
							frame.getContentPane(),
							"¿Desea guardar los cambios realizados en la inmobiliaria?",
							"Confirmar", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);
			switch (n) {
			case JOptionPane.YES_OPTION:
				guardar();
				System.exit(0);
			case JOptionPane.NO_OPTION:
				System.exit(0);
			case JOptionPane.CANCEL_OPTION:
				return;
			}
		} else {
			System.exit(0);
		}

	}

	/**
	 * Añade una casa a la inmobiliaria
	 */
	private void añadirCasa() {
		AñadirCasa añadircasa = new AñadirCasa();
		añadircasa.setVisible(true);
	}

	/**
	 * Añade un piso a la inmobiliaria
	 */
	private void añadirPiso() {
		AñadirPiso añadirpiso = new AñadirPiso();
		añadirpiso.setVisible(true);
	}

	/**
	 * Añade una casa rural a la inmobiliaria
	 */
	private void añadirCasaRural() {
		AñadirCasaRural añadircasarural = new AñadirCasaRural();
		añadircasarural.setVisible(true);
	}

	/**
	 * Elimina una vivienda de la inmobiliaria
	 */
	private void eliminar() {
		Eliminar eliminar = new Eliminar();
		eliminar.setVisible(true);
	}

	/**
	 * Alquila una vivienda de las disponibles
	 */
	private void alquilar() {
		Alquilar alquilar = new Alquilar();
		alquilar.setVisible(true);
	}

	/**
	 * Vende una vivienda de las disponibles
	 */
	private void vender() {
		Vender vender = new Vender();
		vender.setVisible(true);
	}

	/**
	 * Muestra la vivienda indicada por el código de vivienda
	 */
	private void mostrarVivienda() {
		Mostrar mostrar = new Mostrar();
		mostrar.setVisible(true);
	}

	/**
	 * Muestra toda la inmobiliaria, ordenada por antigüedad (de menor a mayor).
	 * Si alguna de las viviendas ha sido alquilada o vendida, lo mostrará
	 */
	private void mostrarInmobiliaria() {
		if (General.lista.size() == 0) {
			JOptionPane.showMessageDialog(frame, "La inmobiliaria esta vacia",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		MostrarInmobiliaria mostrarI = new MostrarInmobiliaria();
		mostrarI.setVisible(true);
	}

	/**
	 * Muestra todas las viviendas que han sido alquiladas, ordenadas por
	 * antigüedad (de menor a mayor)
	 */
	private void mostrarViviendasAlquiladas() {
		if (General.lista.size() == 0) {
			JOptionPane.showMessageDialog(frame, "La inmobiliaria esta vacia",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			Inmobiliaria viviendasAlquiladas = Inmobiliaria
					.generarInmobiliariaAlquiladas();
			if (viviendasAlquiladas.size() == 0) {
				JOptionPane.showMessageDialog(frame,
						"No hay viviendas alquiladas", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				MostrarAlquiladas mostrarA = new MostrarAlquiladas();
				mostrarA.setVisible(true);
			}
		} catch (ValorInvalidoException e) {
			JOptionPane.showMessageDialog(frame, "Valores invalidos", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (ViviendaExistenteException e) {
			JOptionPane.showMessageDialog(frame, "La vivienda ya existe",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Muestra todas las viviendas que han sido vendidas, ordenadas por
	 * antigüedad (de menor a mayor) 
	 */
	private void mostrarViviendasVendidas() {
		if (General.lista.size() == 0) {
			JOptionPane.showMessageDialog(frame, "La inmobiliaria esta vacia",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			Inmobiliaria viviendasVendidas = Inmobiliaria
					.generarInmobiliariaVendidas();
			if (viviendasVendidas.size() == 0) {
				JOptionPane.showMessageDialog(frame,
						"No hay viviendas vendidas", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				MostrarVendidas mostrarV = new MostrarVendidas();
				mostrarV.setVisible(true);
			}
		} catch (ValorInvalidoException e) {
			JOptionPane.showMessageDialog(frame, "Valores invalidos", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (ViviendaExistenteException e) {
			JOptionPane.showMessageDialog(frame, "La vivienda ya existe",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Muestra el menu de ayuda
	 */
	private void verAyuda() {
		VerAyuda verAyuda = new VerAyuda();
		verAyuda.setVisible(true);
	}
}
