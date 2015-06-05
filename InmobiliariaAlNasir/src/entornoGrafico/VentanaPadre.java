package entornoGrafico;

import inmobiliariaAlNasir.Zona;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * Crea la ventana padre para el resto del entorno grafico
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 */
public class VentanaPadre extends JDialog {

	/**
	 * ID de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Panel principal de la ventana
	 */
	protected final JPanel contentPanel = new JPanel();
	/**
	 * Nos permite escribir el codigo de la vivienda
	 */
	protected JTextField txtCodigoVivienda;
	/**
	 * Indica que se trata del codigo de la vivienda
	 */
	protected JLabel lblCodigoDeVivienda;
	/**
	 * Nos permite indicar si la casa es adosada o no
	 */
	protected JCheckBox adosadaCheckBox;
	/**
	 * Nos permite elegir la zona
	 */
	protected JComboBox<Zona> zonaComboBox;
	/**
	 * Indica que se trata de la zona de la vivienda
	 */
	protected JLabel lblZona;
	/**
	 * Nos permite elegir le numero de plantas
	 */
	protected JComboBox<Object> plantasComboBox;
	/**
	 * Indica que se trata del numero de plantas
	 */
	protected JLabel lblPlantas;
	/**
	 * Nos permite escribir el numero de metros cuadrados
	 */
	protected JTextField txtMetrosCuadrados;
	/**
	 * Indica que se trata de los metros cuadrados
	 */
	protected JLabel lblMetrosCuadrados;
	/**
	 * Nos permite escribir el precio
	 */
	protected JTextField txtPrecio;
	/**
	 * Nos permite elegir el numero de habitaciones
	 */
	protected JComboBox<Object> habitacionesComboBox;
	/**
	 * Indica que se trata del numero de habitaciones
	 */
	protected JLabel lblHabitaciones;
	/**
	 * Indica que se trata del numero de baños
	 */
	protected JLabel lblBaños;
	/**
	 * Nos permite elegir el numero de baños
	 */
	protected JComboBox<Object> bañosComboBox;
	/**
	 * Nos indica que se trata del precio
	 */
	protected JLabel lblPrecio;
	/**
	 * Nos indica que se trata de la fecha
	 */
	protected JLabel lblFecha;
	/**
	 * Nos permite escribir la fecha de construccion
	 */
	protected JTextField txtFecha;
	/**
	 * Nos permite indicar si el piso tiene ascensor o no
	 */
	protected JCheckBox ascensorCheckBox;
	/**
	 * Etiqueta que nos da información sobre el codigo de vivienda
	 */
	private JLabel Help;

	/**
	 * Lanza la aplicacion
	 * 
	 * @param args
	 *            Cadena de String
	 */
	public static void main(String[] args) {
		try {
			VentanaPadre dialog = new VentanaPadre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el dialogo. Asigna valores y campos.
	 */
	public VentanaPadre() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				VerAyuda.class.getResource("/img/icono.png")));

		txtCodigoVivienda = new JTextField();
		txtCodigoVivienda.setBounds(158, 59, 76, 20);
		contentPanel.add(txtCodigoVivienda);
		txtCodigoVivienda.setColumns(10);

		lblCodigoDeVivienda = new JLabel("Codigo de vivienda:");
		lblCodigoDeVivienda.setBounds(38, 62, 110, 14);
		contentPanel.add(lblCodigoDeVivienda);

		adosadaCheckBox = new JCheckBox("Adosada");
		adosadaCheckBox.setBounds(240, 58, 86, 23);
		contentPanel.add(adosadaCheckBox);

		zonaComboBox = new JComboBox<Zona>();
		zonaComboBox.setModel(new DefaultComboBoxModel<Zona>(Zona.values()));
		zonaComboBox.setBounds(84, 181, 119, 22);
		contentPanel.add(zonaComboBox);

		lblZona = new JLabel("Zona:");
		lblZona.setBounds(38, 185, 46, 14);
		contentPanel.add(lblZona);

		plantasComboBox = new JComboBox<Object>();
		plantasComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {
				"1", "2", "3", "4", "5" }));
		plantasComboBox.setBounds(353, 181, 52, 22);
		contentPanel.add(plantasComboBox);

		lblPlantas = new JLabel("Planta(s):");
		lblPlantas.setBounds(278, 185, 65, 14);
		contentPanel.add(lblPlantas);

		txtMetrosCuadrados = new JTextField();
		txtMetrosCuadrados.setBounds(158, 99, 76, 20);
		contentPanel.add(txtMetrosCuadrados);
		txtMetrosCuadrados.setColumns(10);

		lblMetrosCuadrados = new JLabel("Metros cuadrados:");
		lblMetrosCuadrados.setBounds(38, 102, 110, 14);
		contentPanel.add(lblMetrosCuadrados);

		habitacionesComboBox = new JComboBox<Object>();
		habitacionesComboBox.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "1", "2", "3", "4", "5" }));
		habitacionesComboBox.setBounds(353, 98, 52, 22);
		contentPanel.add(habitacionesComboBox);

		lblHabitaciones = new JLabel("Habitaciones:");
		lblHabitaciones.setBounds(264, 102, 85, 14);
		contentPanel.add(lblHabitaciones);

		lblBaños = new JLabel("Ba\u00F1os:");
		lblBaños.setBounds(303, 147, 46, 14);
		contentPanel.add(lblBaños);

		bañosComboBox = new JComboBox<Object>();
		bañosComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {
				"1", "2", "3" }));
		bañosComboBox.setBounds(353, 143, 52, 22);
		contentPanel.add(bañosComboBox);

		lblPrecio = new JLabel("Valor (\u20AC):");
		lblPrecio.setBounds(38, 147, 52, 14);
		contentPanel.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setBounds(95, 144, 65, 20);
		contentPanel.add(txtPrecio);
		txtPrecio.setColumns(10);

		lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(170, 147, 46, 14);
		contentPanel.add(lblFecha);

		txtFecha = new JTextField();
		txtFecha.setBounds(218, 144, 75, 20);
		contentPanel.add(txtFecha);
		txtFecha.setColumns(10);

		ascensorCheckBox = new JCheckBox("Ascensor");
		ascensorCheckBox.setBounds(339, 58, 97, 23);
		contentPanel.add(ascensorCheckBox);

		Help = new JLabel("");
		Help.setToolTipText("El c\u00F3digo debe estar formado por 4 d\u00EDgitos y una cadena de texto (\"C\", \"P\", \"CR\")\r\n");
		Image img = new ImageIcon(this.getClass().getResource("/img/Help.png"))
				.getImage();
		Help.setIcon(new ImageIcon(img));
		Help.setBounds(10, 59, 16, 16);
		contentPanel.add(Help);

	}
}
