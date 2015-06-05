package entornoGrafico;

import inmobiliariaAlNasir.CasaRural;
import inmobiliariaAlNasir.ValorInvalidoException;
import inmobiliariaAlNasir.Vivienda;
import inmobiliariaAlNasir.ViviendaExistenteException;
import inmobiliariaAlNasir.Zona;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Añade una casa rural a traves del GUI
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 *
 */
public class AñadirCasaRural extends VentanaPadre {

	/**
	 * ID de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Casa rural vacia que nos asigna un codigo de vivienda
	 */
	private CasaRural casa = new CasaRural();
	/**
	 * /** Formato para la fecha. De este modo nos aseguraremos de que cumple
	 * con estas condiciones.
	 */
	private static SimpleDateFormat formatoFecha = new SimpleDateFormat(
			"dd/MM/yyyy");

	/**
	 * Create el dialogo. Establece valores y atributos de la ventana.
	 */
	public AñadirCasaRural() {
		setModal(true);
		adosadaCheckBox.setLocation(278, 32);
		ascensorCheckBox.setLocation(278, 58);
		setTitle("A\u00F1adir Casa Rural");
		txtCodigoVivienda.setEditable(false);
		setBounds(100, 100, 450, 300);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		adosadaCheckBox.setVisible(false);
		ascensorCheckBox.setVisible(false);
		txtCodigoVivienda.setVisible(true);
		lblCodigoDeVivienda.setVisible(true);
		lblMetrosCuadrados.setVisible(true);
		txtMetrosCuadrados.setVisible(true);
		zonaComboBox.setVisible(true);
		lblZona.setVisible(true);
		plantasComboBox.setVisible(true);
		lblPlantas.setVisible(true);
		lblHabitaciones.setVisible(true);
		habitacionesComboBox.setVisible(true);
		lblPrecio.setVisible(true);
		txtPrecio.setVisible(true);
		bañosComboBox.setVisible(true);
		lblBaños.setVisible(true);
		txtFecha.setVisible(true);
		lblFecha.setVisible(true);

		if (!General.lista.viviendas.contains(casa))
			txtCodigoVivienda.setText(casa.getCodigoVivienda());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton añadir = new JButton("Añadir");
				añadir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {

							annadirCasaRural();

						} catch (ValorInvalidoException e) {
							JOptionPane.showMessageDialog(contentPanel,
									"Valores invalidos");
						} catch (ViviendaExistenteException e2) {
							JOptionPane.showMessageDialog(contentPanel,
									"La vivienda ya existe");
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(contentPanel,
									"Valores invalidos", "Error",
									JOptionPane.ERROR_MESSAGE);
						}

					}

				});
				añadir.setActionCommand("Añadir");
				buttonPane.add(añadir);
				getRootPane().setDefaultButton(añadir);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						clear();
					}
				});
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * Añade la casa rural a la inmobiliaria. En caso de que se haya añadido de
	 * forma satisfactoria, mostrará un mensaje de confirmación. En caso
	 * contrario, mostrara un mensaje de error indicando el motivo del mismo
	 * 
	 * @throws ViviendaExistenteException
	 *             Si la vivienda ya existe
	 * @throws ValorInvalidoException
	 *             Si se producen fallos a la hora de asignar los atributos de
	 *             la casa rural, como que sean menores que 0
	 * @throws NumberFormatException
	 *             Si algun valor int de la casa rural recibe un tipo de
	 *             atributo no correspondiente
	 */
	private void annadirCasaRural() throws ValorInvalidoException,
			ViviendaExistenteException, NumberFormatException {
		General.lista
				.annadir(new CasaRural(txtCodigoVivienda.getText(),
						(inmobiliariaAlNasir.Zona) zonaComboBox
								.getSelectedItem(), Integer
								.parseInt(txtMetrosCuadrados.getText()),
						Integer.parseInt(habitacionesComboBox.getSelectedItem()
								.toString()), Integer.parseInt(bañosComboBox
								.getSelectedItem().toString()),
						validarFecha(txtFecha.getText()), Float
								.parseFloat(txtPrecio.getText()), Integer
								.parseInt(plantasComboBox.getSelectedItem()
										.toString())));

		JOptionPane.showMessageDialog(contentPanel,
				"La casa rural ha sido añadida con exito");
	}

	/**
	 * Limpia la ventana para un posterior uso
	 */
	private void clear() {
		casa = new CasaRural();
		txtCodigoVivienda.setText(casa.getCodigoVivienda());
		adosadaCheckBox.setSelected(false);
		zonaComboBox.setModel(new DefaultComboBoxModel<Zona>(Zona.values()));
		plantasComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {
				"1", "2", "3", "4", "5" }));
		txtMetrosCuadrados.setText("");
		txtPrecio.setText("");
		habitacionesComboBox.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "1", "2", "3", "4", "5" }));
		bañosComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {
				"1", "2", "3" }));
		txtFecha.setText("");
	}

	/**
	 * Valida la fecha
	 * 
	 * @param fecha
	 *            Fecha a validar
	 * @return Fecha ya validada
	 * @throws ValorInvalidoException
	 *             Si la fecha no ha podido ser validada con el patrón
	 */
	private Date validarFecha(String fecha) throws ValorInvalidoException {
		try {
			formatoFecha.setLenient(true);
			if (Vivienda.validarFecha(fecha))
				return formatoFecha.parse(fecha);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Conversion de fecha errónea",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		throw new ValorInvalidoException("Fecha inválida");
	}
}
