package entornoGrafico;

import inmobiliariaAlNasir.Casa;
import inmobiliariaAlNasir.CasaRural;
import inmobiliariaAlNasir.Inmobiliaria;
import inmobiliariaAlNasir.Piso;
import inmobiliariaAlNasir.ValorInvalidoException;
import inmobiliariaAlNasir.Vivienda;
import inmobiliariaAlNasir.ViviendaExistenteException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Muestra las viviendas que han sido vendidas
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 */
public class MostrarVendidas extends VentanaPadre {

	/**
	 * ID de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Nos permite recorrer el ArrayList de viviendasAlquiladas
	 */
	private int indice = 0;
	/**
	 * Nos permite volver a la vivienda anterior, en caso de que la hubiera
	 */
	private JButton botonAnterior;
	/**
	 * Nos permite pasar a la siguiente vivienda, en caso de que la hubiera
	 */
	private JButton botonSiguiente;
	/**
	 * Conjunto de viviendas que han sido vendidas.
	 */
	Inmobiliaria viviendasVendidas;

	/**
	 * Crea el diálogo. Asigna tamaño y valores a la ventana.
	 */
	public MostrarVendidas() {
		lblFecha.setBounds(158, 147, 76, 14);
		txtFecha.setBounds(240, 144, 53, 20);
		lblFecha.setText("Antig\u00FCedad:");
		txtPrecio.setBounds(90, 144, 60, 20);
		setModal(true);
		txtCodigoVivienda.setEnabled(false);
		txtCodigoVivienda.setEditable(false);
		adosadaCheckBox.setLocation(240, 58);
		ascensorCheckBox.setLocation(339, 58);
		setTitle("Mostrar Vendidas");
		txtCodigoVivienda.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtCodigoVivienda.setForeground(Color.BLACK);
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (!Casa.esValido(txtCodigoVivienda.getText())) {
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
				{
					botonAnterior = new JButton("<");
					botonAnterior.setVisible(false);
					botonAnterior.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							adosadaCheckBox.setVisible(false);
							ascensorCheckBox.setVisible(false);
							mostrarAnterior();
						}
					});
					buttonPane.add(botonAnterior);
				}
				{
					botonSiguiente = new JButton(">");
					botonSiguiente.setVisible(false);
					botonSiguiente.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							adosadaCheckBox.setVisible(false);
							ascensorCheckBox.setVisible(false);
							mostrarSiguiente();
						}
					});
					buttonPane.add(botonSiguiente);
				}
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
		empezar();
	}

	/**
	 * Muestra la primera vivienda de la lista.
	 */
	void empezar() {

		try {
			viviendasVendidas = Inmobiliaria.generarInmobiliariaVendidas();
			Collections.sort(viviendasVendidas.viviendas);
		} catch (ValorInvalidoException e) {
			JOptionPane.showMessageDialog(contentPanel, "Valores invalidos",
					"Error", JOptionPane.ERROR_MESSAGE);
		} catch (ViviendaExistenteException e) {
			JOptionPane
					.showMessageDialog(contentPanel, "La vivienda ya existe",
							"Error", JOptionPane.ERROR_MESSAGE);
		}

		if (viviendasVendidas.size() == 0) {
			JOptionPane.showMessageDialog(contentPanel,
					"No hay viviendas vendidas", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		mostrar(viviendasVendidas.get(indice));
		comprobarTamaño();
		botonSiguiente.setVisible(true);
		botonAnterior.setVisible(true);

	}

	/**
	 * Muestra la vivienda
	 * 
	 * @param vivienda
	 *            Vivienda a mostrar
	 */
	private void mostrar(Vivienda vivienda) {
		txtCodigoVivienda.setText(vivienda.getCodigoVivienda());
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
			plantasComboBox.setSelectedItem(((Casa) vivienda).getNumPlantas());
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

	}

	/**
	 * Muestra la siguiente vivienda de la lista
	 */
	private void mostrarSiguiente() {

		adosadaCheckBox.setSelected(false);
		ascensorCheckBox.setSelected(false);
		mostrar(viviendasVendidas.get(++indice));
		comprobarTamaño();

	}

	/**
	 * Muestra la vivienda anterior de la lista
	 */
	private void mostrarAnterior() {

		adosadaCheckBox.setSelected(false);
		ascensorCheckBox.setSelected(false);
		mostrar(viviendasVendidas.get(--indice));
		comprobarTamaño();

	}

	/**
	 * Comprueba que existan viviendas antes o despues de la actual
	 */
	private void comprobarTamaño() {
		if (viviendasVendidas.get(indice + 1) == null)
			botonSiguiente.setEnabled(false);
		else
			botonSiguiente.setEnabled(true);

		if (viviendasVendidas.get(indice - 1) == null)
			botonAnterior.setEnabled(false);
		else
			botonAnterior.setEnabled(true);
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
		botonSiguiente.setVisible(false);
		botonAnterior.setVisible(false);
	}

}