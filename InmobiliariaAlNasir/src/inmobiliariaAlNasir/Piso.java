package inmobiliariaAlNasir;

import java.io.Serializable;
import java.util.Date;

/**
 * Trabaja con los elementos del Piso de forma individual. Puede ser tanto
 * alquilado como vendido. Tiene como atributos propios: <br>
 * <ul>
 * <li>boolean ascensor
 * <li>int planta
 * </ul>
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 */
public class Piso extends Vivienda implements Serializable {

	/**
	 * ID de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Numero de planta del piso
	 */
	private int planta;
	/**
	 * Indica si el piso tiene ascensor o no
	 */
	private boolean ascensor;
	/**
	 * Indica si la casa ha sido vendida o no. Iniciado a false
	 */
	private boolean vendido = false;

	/**
	 * Crea un piso vacio y un c�digo aleatorio siguiendo un patr�n.
	 */
	public Piso() {
		setCodigoVivienda();
	}

	/**
	 * Crea un piso con todos los elementos.
	 * 
	 * @param codigoVivienda
	 *            Codigo un�voco de cada vivienda
	 * @param zona
	 *            Zona donde se situa
	 * @param m2
	 *            N�mero de metros cuadrados
	 * @param numHabitaciones
	 *            N�mero total de habitaciones
	 * @param numBa�os
	 *            N�mero total de ba�os
	 * @param fecha
	 *            Fecha de construcci�n
	 * @param precio
	 *            Valor de la vivienda
	 * @param planta
	 *            Numero total de planta
	 * @param ascensor
	 *            Indica si tiene ascensor o no
	 * @throws ValorInvalidoException
	 *             Si alguno de los valores no es adecuado, salta la excepci�n
	 */
	public Piso(String codigoVivienda, Zona zona, int m2, int numHabitaciones,
			int numBa�os, Date fecha, float precio, int planta, boolean ascensor)
			throws ValorInvalidoException {
		super(codigoVivienda, zona, m2, numHabitaciones, numBa�os, fecha,
				precio);
		setPlanta(planta);
		setAscensor(ascensor);
	}

	/**
	 * Crea un piso con un c�digo de vivienda
	 * 
	 * @param codigo
	 *            Codigo de vivienda
	 * @throws CodigoNoValidoException
	 *             Si el c�digo no cumple con el patr�n, salta la excepci�n
	 */
	public Piso(String codigo) throws CodigoNoValidoException {
		codigoVivienda = codigo;
	}

	/**
	 * Devuelve la planta del piso
	 * 
	 * @return planta Planta donde est� situado
	 */
	public int getPlanta() {
		return planta;
	}

	/**
	 * Crea el c�digo de vivienda. Para ello, une tres n�meros aleatorios con
	 * una letra. En este caso, al tratarse de un piso, la letra es la P. Si el
	 * codigo no es validado correctamente por el patr�n, el c�digo ser� null
	 */
	public void setCodigoVivienda() {
		String codigo = "";
		Integer n;
		for (int i = 0; i < 4; i++) {
			n = (int) (Math.random() * 9);
			codigo = codigo + n.toString();
		}
		codigo = codigo + "P";
		if (esValido(codigo))
			codigoVivienda = codigo;
		else
			codigoVivienda = null;

	}

	/**
	 * Establece el numero de la planta
	 * 
	 * @param planta
	 *            Planta en la que est� situado
	 * @throws ValorInvalidoException
	 *             Si la planta es negativa
	 */
	public void setPlanta(int planta) throws ValorInvalidoException {
		this.planta = planta;
	}

	/**
	 * Indica si el piso tiene ascensor o no
	 * 
	 * @return boolean True si tiene ascensor, False si no
	 */
	public boolean isAscensor() {
		return ascensor;
	}

	/**
	 * Establece la vivienda como piso con ascensor
	 * 
	 * @param ascensor
	 *            True si tiene ascensor, False si no
	 */
	public void setAscensor(boolean ascensor) {
		this.ascensor = ascensor;
	}

	/**
	 * Vende el piso. Establece la variable vendido a True
	 */
	public void vender() {
		this.vendido = true;

	}

	/**
	 * Indica si el piso ha sido vendido
	 * 
	 * @return boolean True si ha sido vendido, False si no
	 */
	public boolean isVendido() {
		return vendido;
	}

}
