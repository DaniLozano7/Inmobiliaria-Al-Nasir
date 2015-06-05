package entornoGrafico;

import inmobiliariaAlNasir.Casa;
import inmobiliariaAlNasir.CasaRural;
import inmobiliariaAlNasir.CodigoNoValidoException;
import inmobiliariaAlNasir.Piso;
import inmobiliariaAlNasir.Vivienda;
import inmobiliariaAlNasir.ViviendaNoExistenteException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Se encarga de los alquileres de las viviendas desde la GUI
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 */
public class Alquilar extends VentanaPadre {

	/**
	 * ID de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Vivienda que queremos alquilar
	 */
	private Vivienda vivienda;
	/**
	 * Etiqueta que aparecerá en caso de que introduzcamos un codigo de vivienda
	 * invalido
	 */
	private JLabel lblCodigoInvlido;
	/**
	 * Etiqueta que mostrará claramente si una vivienda ha sido alquilada o no
	 */
	private JLabel alquilado;

	/**
	 * Crea el diálogo. Asigna tamaño y valores a la ventana.
	 */
	public Alquilar() {
		txtPrecio.setBounds(90, 144, 60, 20);
		lblFecha.setBounds(158, 147, 76, 14);
		txtFecha.setBounds(240, 144, 53, 20);
		lblFecha.setText("Antig\u00FCedad:");
		ascensorCheckBox.setEnabled(false);
		setModal(true);
		setTitle("Alquilar");
		txtCodigoVivienda.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				lblCodigoInvlido.setVisible(false);
				txtCodigoVivienda.setForeground(Color.BLACK);
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (!Casa.esValido(txtCodigoVivienda.getText())) {
					lblCodigoInvlido.setVisible(true);
					txtCodigoVivienda.setForeground(Color.RED);
				}

			}
		});
		adosadaCheckBox.setEnabled(false);
		adosadaCheckBox.setVisible(false);
		ascensorCheckBox.setVisible(false);
		bañosComboBox.setEnabled(false);
		plantasComboBox.setEnabled(false);
		zonaComboBox.setEnabled(false);
		txtFecha.setEnabled(false);
		txtFecha.setEditable(false);
		txtPrecio.setEnabled(false);
		txtPrecio.setEditable(false);

		alquilado = new JLabel("");
		Image imgA = new ImageIcon(this.getClass().getResource(
				"/img/alquilado.png")).getImage();
		alquilado.setIcon(new ImageIcon(imgA));
		alquilado.setBounds(38, 11, 196, 37);
		contentPanel.add(alquilado);
		alquilado.setVisible(false);

		habitacionesComboBox.setEnabled(false);
		txtMetrosCuadrados.setEnabled(false);
		txtMetrosCuadrados.setEditable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		lblCodigoInvlido = new JLabel("Codigo Inv\u00E1lido");
		lblCodigoInvlido.setForeground(Color.RED);
		lblCodigoInvlido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCodigoInvlido.setBounds(148, 34, 97, 14);
		lblCodigoInvlido.setVisible(false);
		contentPanel.add(lblCodigoInvlido);
		txtCodigoVivienda.setVisible(true);
		lblCodigoDeVivienda.setVisible(true);
		zonaComboBox.setVisible(true);
		lblZona.setVisible(true);
		plantasComboBox.setVisible(true);
		lblPlantas.setVisible(true);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton alquilar = new JButton("Alquilar");
				alquilar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						String codigo = txtCodigoVivienda.getText()
								.toUpperCase();

						if (codigo.equals("")) {
							JOptionPane.showMessageDialog(contentPanel,
									"Introduce una vivienda");
							return;
						}

						mostrar(codigo);

					}

				});
				alquilar.setActionCommand("Alquilar");
				buttonPane.add(alquilar);
				getRootPane().setDefaultButton(alquilar);
			}
			{
				JButton cancelar = new JButton("Cancelar");
				cancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						clear();
					}
				});
				cancelar.setActionCommand("Cancelar");
				buttonPane.add(cancelar);
			}
		}
	}

	/**
	 * Alquila la vivienda. Si el alquiler ha sido satisfactorio, muestra un
	 * mensaje de confirmación. En caso contrario, indicará el tipo de error.
	 */
	private void alquilar() {

		try {
			General.lista.alquilar(txtCodigoVivienda.getText());
			JOptionPane.showMessageDialog(contentPanel,
					"La vivienda ha sido alquilada");
			alquilado.setVisible(true);
		} catch (ViviendaNoExistenteException e) {
			JOptionPane
					.showMessageDialog(contentPanel, "La vivienda no existe",
							"Error", JOptionPane.ERROR_MESSAGE);
		} catch (CodigoNoValidoException e) {
			JOptionPane.showMessageDialog(contentPanel, "Codigo inválido",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Muestra la vivienda antes de alquilarla. Se identifica por el codigo de
	 * vivienda.
	 * 
	 * @param codigo
	 *            Codigo de la vivienda
	 */
	private void mostrar(String codigo) {
		try {
			vivienda = General.lista.get(codigo);

			Integer m2 = (Integer) vivienda.getM2();
			txtMetrosCuadrados.setText(m2.toString());
			habitacionesComboBox.addItem(vivienda.getNumHabitaciones());
			habitacionesComboBox.setSelectedItem(vivienda.getNumHabitaciones());
			Float precio = (Float) vivienda.getPrecio();
			txtPrecio.setText(precio.toString());
			bañosComboBox.addItem(vivienda.getNumBaños());
			bañosComboBox.setSelectedItem(vivienda.getNumBaños());
			zonaComboBox.setSelectedItem(vivienda.getZona());
			txtFecha.setText(vivienda.getAntiguedad());

			if (vivienda.getClass() == Casa.class) {
				adosadaCheckBox.setVisible(true);
				if (((Casa) vivienda).isAdosada() == true)
					adosadaCheckBox.setSelected(true);
				plantasComboBox.addItem(((Casa) vivienda).getNumPlantas());
				plantasComboBox.setSelectedItem(((Casa) vivienda)
						.getNumPlantas());
			}
			if (vivienda.getClass() == Piso.class) {
				ascensorCheckBox.setVisible(true);
				if (((Piso) vivienda).isAscensor() == true)
					ascensorCheckBox.setSelected(true);
				plantasComboBox.addItem(((Piso) vivienda).getPlanta());
				plantasComboBox.setSelectedItem(((Piso) vivienda).getPlanta());
			}
			if (vivienda.getClass() == CasaRural.class) {
				plantasComboBox.addItem(((CasaRural) vivienda).getNumPlantas());
				plantasComboBox.setSelectedItem(((CasaRural) vivienda)
						.getNumPlantas());
			}

			comprobarDisponibilidad();

		} catch (ViviendaNoExistenteException e) {
			JOptionPane
					.showMessageDialog(contentPanel, "La vivienda no existe",
							"Error", JOptionPane.ERROR_MESSAGE);
		} catch (CodigoNoValidoException e) {
			JOptionPane.showMessageDialog(contentPanel, "Codigo Invalido",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Comprueba que la vivienda está disponible, es decir, que no es igual a
	 * null, que no está alquilada y que no ha sido vendida
	 */
	private void comprobarDisponibilidad() {
		if (vivienda.getClass() == Casa.class) {
			if (vivienda != null && vivienda.isAlquilado() == false
					&& ((Casa) vivienda).isVendido() == false)
				alquilar();
			else
				JOptionPane.showMessageDialog(contentPanel,
						"La casa no está disponible", "Error",
						JOptionPane.ERROR_MESSAGE);
		} else if (vivienda.getClass() == Piso.class) {
			if (vivienda != null && vivienda.isAlquilado() == false
					&& ((Piso) vivienda).isVendido() == false)
				alquilar();
			else
				JOptionPane.showMessageDialog(contentPanel,
						"El piso no está disponible", "Error",
						JOptionPane.ERROR_MESSAGE);
		} else {
			if (vivienda != null && vivienda.isAlquilado() == false)
				alquilar();
			else
				JOptionPane.showMessageDialog(contentPanel,
						"La casa rural no está disponible", "Error",
						JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Limpia la ventana para un posterior uso
	 */
	private void clear() {

		txtCodigoVivienda.setText("");
		adosadaCheckBox.setSelected(false);
		ascensorCheckBox.setSelected(false);
		adosadaCheckBox.setVisible(false);
		ascensorCheckBox.setVisible(false);
		zonaComboBox.setSelectedItem(-1);
		plantasComboBox.setSelectedItem(-1);
		txtMetrosCuadrados.setText("");
		txtPrecio.setText("");
		habitacionesComboBox.setSelectedItem(-1);
		bañosComboBox.setSelectedItem(-1);
		txtFecha.setText("");
	}
}
