package entornoGrafico;

import inmobiliariaAlNasir.Piso;
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
 * Se encarga de a�adir casas desde la GUI
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 */
public class A�adirPiso extends VentanaPadre {

	/**
	 * ID de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Piso vacio que nos asigna un c�digo de vivienda
	 */
	private Piso piso = new Piso();
	/**
	 * Formato para la fecha. De este modo nos aseguraremos de que cumple con
	 * estas condiciones.
	 */
	private static SimpleDateFormat formatoFecha = new SimpleDateFormat(
			"dd/MM/yyyy");

	/**
	 * Crea el di�logo. Establece valores y caracter�sticas de la ventana.
	 */
	public A�adirPiso() {
		setModal(true);
		adosadaCheckBox.setLocation(278, 32);
		ascensorCheckBox.setLocation(278, 58);
		setTitle("A\u00F1adir Piso");
		txtCodigoVivienda.setEditable(false);
		setBounds(100, 100, 450, 300);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		adosadaCheckBox.setVisible(false);
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
		ba�osComboBox.setVisible(true);
		lblBa�os.setVisible(true);
		txtFecha.setVisible(true);
		lblFecha.setVisible(true);

		if (!General.lista.viviendas.contains(piso))
			txtCodigoVivienda.setText(piso.getCodigoVivienda());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton a�adir = new JButton("A�adir");
				a�adir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						try {

							annadirPiso();

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
				a�adir.setActionCommand("A�adir");
				buttonPane.add(a�adir);
				getRootPane().setDefaultButton(a�adir);
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
	 * A�ade el piso a la inmobiliaria. En caso de que se haya a�adido de forma
	 * satisfactoria, mostrar� un mensaje de confirmaci�n. En caso contrario,
	 * mostrara un mensaje de error indicando el motivo del mismo
	 * 
	 * @throws NumberFormatException
	 *             Si algun valor int del piso recibe un tipo de atributo no
	 *             correspondiente
	 * @throws ValorInvalidoException
	 *             Si se producen fallos a la hora de asignar los atributos del,
	 *             como que sean menores que 0
	 * @throws ViviendaExistenteException
	 *             Si la vivienda ya existe
	 */
	private void annadirPiso() throws ValorInvalidoException,
			ViviendaExistenteException, NumberFormatException {
		General.lista.annadir(new Piso(txtCodigoVivienda.getText(),
				(inmobiliariaAlNasir.Zona) zonaComboBox.getSelectedItem(),
				Integer.parseInt(txtMetrosCuadrados.getText()), Integer
						.parseInt(habitacionesComboBox.getSelectedItem()
								.toString()), Integer.parseInt(ba�osComboBox
						.getSelectedItem().toString()), validarFecha(txtFecha
						.getText()), Float.parseFloat(txtPrecio.getText()),
				Integer.parseInt(plantasComboBox.getSelectedItem().toString()),
				ascensorCheckBox.isSelected()));

		JOptionPane.showMessageDialog(contentPanel,
				"El piso ha sido a�adido con exito");
	}

	/**
	 * Limpia la ventana para un posterior uso
	 */
	private void clear() {
		piso = new Piso();
		txtCodigoVivienda.setText(piso.getCodigoVivienda());
		ascensorCheckBox.setSelected(false);
		zonaComboBox.setModel(new DefaultComboBoxModel<Zona>(Zona.values()));
		plantasComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {
				"1", "2", "3", "4", "5" }));
		txtMetrosCuadrados.setText("");
		txtPrecio.setText("");
		habitacionesComboBox.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "1", "2", "3", "4", "5" }));
		ba�osComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {
				"1", "2", "3" }));
		txtFecha.setText("");
	}

	/**
	 * Valida la fecha
	 * @param fecha Fecha a validar
	 * @return Fecha ya validada
	 * @throws ValorInvalidoException Si la fecha no ha podido ser validada con el patr�n
	 */
	private static Date validarFecha(String fecha)
			throws ValorInvalidoException {
		try {
			formatoFecha.setLenient(true);
			if (Vivienda.validarFecha(fecha))
				return formatoFecha.parse(fecha);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Conversion de fecha err�nea",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		throw new ValorInvalidoException("Fecha inv�lida");
	}

}
