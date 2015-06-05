package entornoGrafico;

import inmobiliariaAlNasir.Casa;
import inmobiliariaAlNasir.CasaRural;
import inmobiliariaAlNasir.Piso;
import inmobiliariaAlNasir.Vivienda;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

/**
 * Muestra las viviendas que hay en la inmobiliaria
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 */
public class MostrarInmobiliaria extends VentanaPadre {

	/**
	 * ID de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Nos permite recorrer el ArrayList de viviendasAlquiladas
	 */
	private int indice;
	/**
	 * Nos permite volver a la vivienda anterior, en caso de que la hubiera
	 */
	private JButton botonAnterior = new JButton();
	/**
	 * Nos permite pasar a la siguiente vivienda, en caso de que la hubiera
	 */
	private JButton botonSiguiente = new JButton();
	/**
	 * Etiqueta que mostrará claramente si una vivienda ha sido vendida
	 */
	private JLabel vendido;
	/**
	 * Etiqueta que mostrará claramente si una vivienda ha sido alquilada o no
	 */
	private JLabel alquilado;

	/**
	 * Crea el diálogo. Asigna tamaño y valores a la ventana.
	 */
	public MostrarInmobiliaria() {

		txtPrecio.setBounds(90, 144, 60, 20);
		lblFecha.setBounds(158, 147, 76, 14);
		txtFecha.setBounds(240, 144, 53, 20);
		lblFecha.setText("Antig\u00FCedad:");
		setModal(true);

		txtCodigoVivienda.setEnabled(false);
		txtCodigoVivienda.setEditable(false);
		adosadaCheckBox.setLocation(240, 58);
		ascensorCheckBox.setLocation(339, 58);
		setTitle("Mostrar Inmobiliaria");
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

		alquilado = new JLabel("");
		Image imgA = new ImageIcon(this.getClass().getResource(
				"/img/alquilado.png")).getImage();
		alquilado.setIcon(new ImageIcon(imgA));
		alquilado.setBounds(38, 11, 196, 37);
		contentPanel.add(alquilado);
		alquilado.setVisible(false);

		vendido = new JLabel("");
		Image imgV = new ImageIcon(this.getClass().getResource(
				"/img/vendido.png")).getImage();
		vendido.setIcon(new ImageIcon(imgV));
		vendido.setBounds(246, 11, 176, 37);
		contentPanel.add(vendido);
		vendido.setVisible(false);

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
							alquilado.setVisible(false);
							vendido.setVisible(false);
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
							alquilado.setVisible(false);
							vendido.setVisible(false);
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
		Collections.sort(General.lista.viviendas);
		empezar();
	}
	/**
	 * Muestra la primera vivienda de la lista.
	 */
	private void empezar() {

		indice = 0;
		mostrar(General.lista.get(indice));
		comprobarTamaño();
		botonSiguiente.setVisible(true);
		botonAnterior.setVisible(true);

	}
	/**
	 * Muestra la vivienda.
	 * @param vivienda Vivienda a mostrar
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
		if (vivienda.isAlquilado())
			alquilado.setVisible(true);
		if (comprobarVenta(vivienda))
			vendido.setVisible(true);

	}
	/**
	 * Muestra la siguiente vivienda de la lista
	 */
	private void mostrarSiguiente() {

		adosadaCheckBox.setSelected(false);
		ascensorCheckBox.setSelected(false);
		mostrar(General.lista.get(++indice));
		comprobarTamaño();

	}

	/**
	 * Muestra la vivienda anterior de la lista
	 */
	private void mostrarAnterior() {

		adosadaCheckBox.setSelected(false);
		ascensorCheckBox.setSelected(false);
		mostrar(General.lista.get(--indice));
		comprobarTamaño();

	}

	/**
	 * Comprueba que existan viviendas antes o despues de la actual
	 */
	private void comprobarTamaño() {
		if (General.lista.get(indice + 1) == null)
			botonSiguiente.setEnabled(false);
		else
			botonSiguiente.setEnabled(true);

		if (General.lista.get(indice - 1) == null)
			botonAnterior.setEnabled(false);
		else
			botonAnterior.setEnabled(true);
	}

	/**
	 * Comprueba si la vivienda ha sido vendida
	 * @param vivienda Vivienda a comprobar
	 * @return boolean True si ha sido vendida, False si no
	 */
	private boolean comprobarVenta(Vivienda vivienda) {
		if (vivienda.getCodigoVivienda().endsWith("C")) {
			if (((Casa) vivienda).isVendido() == true)
				return true;
		}
		if (vivienda.getCodigoVivienda().endsWith("P")) {
			if (((Piso) vivienda).isVendido() == true)
				return true;
		}
		return false;

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
		botonSiguiente.setVisible(false);
		botonAnterior.setVisible(false);
	}
}
