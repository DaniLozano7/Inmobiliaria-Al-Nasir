package entornoGrafico;

import inmobiliariaAlNasir.Casa;
import inmobiliariaAlNasir.CasaRural;
import inmobiliariaAlNasir.CodigoNoValidoException;
import inmobiliariaAlNasir.Piso;
import inmobiliariaAlNasir.ValorInvalidoException;
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
 * Se encarga de las ventas de las viviendas desde la GUI
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 */
public class Vender extends VentanaPadre {

	/**
	 * ID de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Vivienda que queremos vender
	 */
	private Vivienda vivienda;
	/**
	 * Etiqueta que aparecerá en caso de que introduzcamos un codigo de vivienda
	 * invalido
	 */
	private JLabel lblCodigoInvlido;
	/**
	 * Etiqueta que mostrará claramente si una vivienda ha sido vendida
	 */
	private JLabel vendido;

	/**
	 * Crea el diálogo. Asigna tamaño y valores a la ventana.
	 */
	public Vender() {
		txtPrecio.setBounds(90, 144, 60, 20);
		lblFecha.setBounds(158, 147, 76, 14);
		txtFecha.setBounds(240, 144, 53, 20);
		lblFecha.setText("Antig\u00FCedad:");
		ascensorCheckBox.setEnabled(false);
		setModal(true);
		setTitle("Vender");
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
		adosadaCheckBox.setVisible(false);
		ascensorCheckBox.setVisible(false);
		plantasComboBox.setEnabled(false);
		zonaComboBox.setEnabled(false);
		bañosComboBox.setEnabled(false);
		txtFecha.setEditable(false);
		txtFecha.setEnabled(false);
		txtPrecio.setEditable(false);
		txtPrecio.setEnabled(false);
		habitacionesComboBox.setEnabled(false);
		txtMetrosCuadrados.setEditable(false);
		txtMetrosCuadrados.setEnabled(false);

		vendido = new JLabel("");
		Image imgV = new ImageIcon(this.getClass().getResource(
				"/img/vendido.png")).getImage();
		vendido.setIcon(new ImageIcon(imgV));
		vendido.setBounds(246, 11, 176, 37);
		contentPanel.add(vendido);
		vendido.setVisible(false);

		adosadaCheckBox.setEnabled(false);
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

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton vender = new JButton("Vender");
				vender.addActionListener(new ActionListener() {
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
				vender.setActionCommand("Vender");
				buttonPane.add(vender);
				getRootPane().setDefaultButton(vender);
			}
			{
				JButton cancelar = new JButton("Cancelar");
				cancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
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
	private void vender() {

		try {
			General.lista.vender(txtCodigoVivienda.getText());
			JOptionPane.showMessageDialog(contentPanel,
					"La vivienda ha sido vendida");
			vendido.setVisible(true);
		} catch (ViviendaNoExistenteException e) {
			JOptionPane
					.showMessageDialog(contentPanel, "La vivienda no existe",
							"Error", JOptionPane.ERROR_MESSAGE);
		} catch (CodigoNoValidoException e) {
			JOptionPane.showMessageDialog(contentPanel, "Codigo inválido",
					"Error", JOptionPane.ERROR_MESSAGE);
		} catch (ValorInvalidoException e) {
			JOptionPane.showMessageDialog(contentPanel,
					"Las casas rurales no pueden ser vendidas", "Error",
					JOptionPane.ERROR_MESSAGE);
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

			if (vivienda.getClass()==Casa.class) {
				adosadaCheckBox.setVisible(true);
				if (((Casa) vivienda).isAdosada() == true)
					adosadaCheckBox.setSelected(true);
				plantasComboBox.addItem(((Casa) vivienda).getNumPlantas());
				plantasComboBox.setSelectedItem(((Casa) vivienda).getNumPlantas());
			}
			if (vivienda.getClass()==Piso.class) {
				ascensorCheckBox.setVisible(true);
				if (((Piso) vivienda).isAscensor() == true)
					ascensorCheckBox.setSelected(true);
				plantasComboBox.addItem(((Piso) vivienda).getPlanta());
				plantasComboBox.setSelectedItem(((Piso) vivienda).getPlanta());
			}
			if (vivienda.getClass()==CasaRural.class) {
				plantasComboBox.addItem(((CasaRural) vivienda)
						.getNumPlantas());
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
			if (vivienda != null
					&& vivienda.isAlquilado() == false
					&& ((Casa) vivienda).isVendido() == false)
				vender();
			else
				JOptionPane.showMessageDialog(contentPanel,
						"La casa no está disponible", "Error",
						JOptionPane.ERROR_MESSAGE);
		} else if (vivienda.getClass() == Piso.class) {
			if (vivienda != null
					&& vivienda.isAlquilado() == false
					&& ((Piso) vivienda).isVendido() == false)
				vender();
			else
				JOptionPane.showMessageDialog(contentPanel,
						"El piso no está disponible", "Error",
						JOptionPane.ERROR_MESSAGE);
		} else {
			if (vivienda != null
					&& vivienda.isAlquilado() == false)
				vender();
			else
				JOptionPane.showMessageDialog(contentPanel,
						"La casa rural no está disponible",
						"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Limpia la ventana para su posterior uso
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
