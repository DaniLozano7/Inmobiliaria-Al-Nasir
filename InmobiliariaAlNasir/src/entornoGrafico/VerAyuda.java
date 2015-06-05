package entornoGrafico;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

/**
 * Nos muestra la ventana de ayuda de nuestro programa.
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 */

public class VerAyuda extends JDialog {

	/**
	 * ID de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Panel principal de la ventana
	 */
	private final JPanel contentPanel = new JPanel();

	/**
	 * Crea el dialogo. Asigna campos y valores.
	 */
	public VerAyuda() {
		setTitle("Ver Ayuda");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				VerAyuda.class.getResource("/img/icono.png")));
		setBounds(100, 100, 480, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 452, 318);
		contentPanel.add(scrollPane);

		JEditorPane dtrpnHola = new JEditorPane();
		dtrpnHola.setEditable(false);
		scrollPane.setViewportView(dtrpnHola);
		dtrpnHola.setContentType("text/html");
		dtrpnHola
				.setText("<html>\r\n<body>\r\n<DIV ALIGN=center>\r<h1><u>Ayuda</u></h1><br></DIV>\r\n<h2>Ficheros</h2>\r\n<ul>\r\n<li><u>Nuevo:</u> (Ctrl+N) Inicia una nueva inmobiliaria en un nuevo fichero. Pregunta de antemano si se desea guardarla inmobiliaria existente en ese momento.</li>\r\n<li><u>Abrir:</u> (Ctrl+O) Abre una inmobiliaria ya existente en un fichero. Pregunta de antemano si se desea guardar la inmobiliaria existente en ese momento.</li>\r\n<li><u>Guardar:</u> (Ctrl+S) Guarda la inmobiliaria actual en un fichero. Si la inmobiliaria no ha sido guardada anteriormente, pedira un nombre y una ubicacion para guardar el archivo. Si ya ha sido creada, guardara los cambios en el mismo fichero seleccionado.</li>\r\n<li><u>Guardar como:</u> (Ctrl+D) Guarda la inmobiliaria actual en un fichero. Pregunta que nombre deseamos darle y la ubicacion donde se guardar\u00E1.</li>\r\n</ul>\r\n<br>\r\n<h2>Modificar</h2>\r\n<ul>\r\n<li><u>A\u00F1adir:</u><ul><li><u>Casa:</u> (Ctrl+Mayus+C) A\u00F1ade una nueva casa a la inmobiliaria. Pide datos comunes de la vivienda como los metros cuadrados, fecha de construcci\u00F3n y zona. Tambi\u00E9n pide datos propios de las casas como, por ejemplo, si son adosadas o el n\u00FAmero de plantas.</li><li><u>Piso:</u> (Ctrl+Mayus+P) A\u00F1ade un nuevo piso a la inmobiliaria. Pide datos comunes de la vivienda como los metros cuadrados, fecha de construcci\u00F3n y zona. Tambi\u00E9n pide datos propios de los pisos como, por ejemplo, si tienen ascensor o la planta donde est\u00E1 situado.</li><li><u>Casa Rural:</u> (Ctrl+Mayus+R) A\u00F1ade una nueva casa rural a la inmobiliaria. Pide datos comunes de la vivienda como los metros cuadrados, fecha de construcci\u00F3n y zona. Es importante recordar que las casas rurales no pueden ser vendidas, \u00FAnicamente alquiladas.</li></ul></li>\r\n<li><u>Eliminar:</u> (Ctrl+D) Elimina una vivienda de la inmobiliaria. Pide el c\u00F3digo de vivienda, muestra los datos de la misma y nos pide confirmaci\u00F3n para eliminarla.</li>\r\n<li><u>Alquilar:</u> (Ctrl+Mayus+A) Alquila la vivienda indicada por el c\u00F3digo de vivienda. Muestra los datos de la misma antes de realizar la operaci\u00F3n.</li>\r\n<li><u>Vender:</u> (Ctrl+Mayus+V) Vende la vivienda indicada por el c\u00F3digo de viviendal. Muestra los datos de la misma antes de realizar la operaci\u00F3n. Las casas rurales no pueden ser vendidas.</li>\r\n</ul>\r\n<br>\r\n<h2>Mostrar</h2>\r\n<ul>\r\n<li><u>Mostrar Vivienda:</u> (Ctrl+M) Muestra todos los datos de la vivienda indicada por el c\u00F3digo de vivienda. Si el c\u00F3digo no es v\u00E1lido lo indicar\u00E1. Si la vivienda no existe, tambi\u00E9n lo indicar\u00E1. </li>\r\n<li><u>Mostrar Inmobiliaria:</u> (Ctrl+I) Muestra todas las viviendas existentes en la inmobiliaria. Esto incluye:\r\n<ul><li>Viviendas libres</li><li>Viviendas alquiladas</li><li>Viviendas vendidas</li></ul></li>\r\n<li><u>Mostrar Alquiladas</u> (Ctrl+Alt+A) Muestra todas las viviendas que se encuentran alquiladas, con todos sus datos correspondientes.</li>\r\n<li><u>Mostrar Vendidas:</u> (Ctrl+Alt+V) Muestra todas las viviendas que han sido vendidas, con todos sus datos correspondientes.</li>\r\n</ul>\r\n<br>\r\n<h2>Ayuda</h2>\r\n<ul>\r\n<li><u>Ver ayuda:</u> (Alt+A) Muestra la descripcion de cada opcion del menu con todas sus caracteristicas. Ayuda general.</li>\r\n<li><u>Acerca de:</u> (Alt+S) Datos del programa y version</li>\r\n</ul>\r\n</body>\r\n</html>");
		dtrpnHola.setCaretPosition(0);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton botonSalir = new JButton("Salir");
				botonSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				buttonPane.add(botonSalir);
			}
		}
	}
}
