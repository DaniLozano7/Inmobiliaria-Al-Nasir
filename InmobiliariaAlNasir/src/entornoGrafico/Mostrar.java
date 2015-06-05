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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

/**
 * Muestra una vivienda a través del GUI
 * @author Daniel Lozano Torrico
 * @version 1.0
 */
public class Mostrar extends VentanaPadre {

	/**
	 * ID de la vivienda
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Etiqueta que aparecerá en caso de que introduzcamos un codigo de vivienda
	 * invalido
	 */
	private JLabel lblCodigoInvlido;
	/**
	 * Vivienda que queremos mostrar
	 */
	private Vivienda vivienda;

	/**
	 * Create the dialog.
	 */
	public Mostrar() {
		txtPrecio.setBounds(90, 144, 60, 20);
		lblFecha.setBounds(158, 147, 76, 14);
		txtFecha.setBounds(240, 144, 53, 20);
		lblFecha.setText("Antig\u00FCedad:");
		setModal(true);
		adosadaCheckBox.setLocation(240, 58);
		ascensorCheckBox.setLocation(339, 58);
		setTitle("Mostrar");
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
		ascensorCheckBox.setEnabled(false);
		bañosComboBox.setEnabled(false);
		plantasComboBox.setEnabled(false);
		zonaComboBox.setEnabled(false);
		txtFecha.setEnabled(false);
		txtFecha.setEditable(false);
		txtPrecio.setEnabled(false);
		txtPrecio.setEditable(false);
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
				JButton Mostrar = new JButton("Mostrar");
				Mostrar.addActionListener(new ActionListener() {

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
				Mostrar.setActionCommand("Mostrar");
				buttonPane.add(Mostrar);
				getRootPane().setDefaultButton(Mostrar);
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
	 * Muestra la vivienda, identificada por un codigo de vivienda
	 * @param codigo Codigo de la vivienda
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
