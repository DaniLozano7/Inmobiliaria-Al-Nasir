package inmobiliariaAlNasir;

import java.io.Serializable;
import java.util.Date;

/**
 * Trabaja con los elementos de la Casa de forma individual. Puede ser tanto
 * alquilada como vendida. Tiene como atributos propios: <br>
 * <ul>
 * <li>boolean adosada
 * <li>int numPlantas
 * </ul>
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 */
public class Casa extends Vivienda implements Serializable {

	/**
	 * ID de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Numero de plantas de la casa
	 */
	private int numPlantas;
	/**
	 * Indica si la casa es adosada o no
	 */
	private boolean adosada;
	/**
	 * Indica si la casa ha sido vendida o no. Iniciado a false
	 */
	private boolean vendido = false;

	/**
	 * Crea una casa vacia y un c�digo aleatorio siguiendo un patr�n.
	 */
	public Casa() {
		setCodigoVivienda();
	}

	/**
	 * Crea una casa con todos los elementos.
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
	 * @param numPlantas
	 *            Numero total de plantas
	 * @param adosada
	 *            Indica si es adosada o no
	 * @throws ValorInvalidoException
	 *             Si alguno de los valores no es adecuado, salta la excepci�n
	 */
	public Casa(String codigoVivienda, Zona zona, int m2, int numHabitaciones,
			int numBa�os, Date fecha, float precio, int numPlantas,
			boolean adosada) throws ValorInvalidoException {
		super(codigoVivienda, zona, m2, numHabitaciones, numBa�os, fecha,
				precio);
		setNumPlantas(numPlantas);
		setAdosada(adosada);
	}

	/**
	 * Crea una casa con un c�digo de vivienda
	 * 
	 * @param codigo
	 *            Codigo de vivienda
	 * @throws CodigoNoValidoException
	 *             Si el c�digo no cumple con el patr�n, salta la excepci�n
	 */
	public Casa(String codigo) throws CodigoNoValidoException {
		codigoVivienda = codigo;
	}

	/**
	 * Establece la vivienda como casa adosada
	 * 
	 * @param adosada
	 *            True si es adosada, False si no lo es
	 */
	private void setAdosada(boolean adosada) {
		this.adosada = adosada;
	}

	/**
	 * Crea el c�digo de vivienda. Para ello, une tres n�meros aleatorios con
	 * una letra. En este caso, al tratarse de una casa, la letra es la C. Si el
	 * codigo no es validado correctamente por el patr�n, el c�digo ser� null
	 */
	public void setCodigoVivienda() {
		String codigo = "";
		Integer n;
		for (int i = 0; i < 4; i++) {
			n = (int) (Math.random() * 9);
			codigo = codigo + n.toString();
		}
		codigo = codigo + "C";
		if (esValido(codigo))
			codigoVivienda = codigo;
		else
			codigoVivienda = null;

	}

	/**
	 * Establece el numero de plantas
	 * 
	 * @param numPlantas
	 *            N�mero de plantas
	 */
	private void setNumPlantas(int numPlantas) {
		this.numPlantas = numPlantas;
	}

	/**
	 * Devuelve el numero de plantas
	 * 
	 * @return N�mero de plantas
	 */
	public int getNumPlantas() {
		return numPlantas;
	}

	/**
	 * Indica si la casa es adosada
	 * 
	 * @return boolean True si es adosada, False si no lo es
	 */
	public boolean isAdosada() {
		return adosada;
	}

	/**
	 * Vende la casa. Establece la variable vendido a True
	 */
	public void vender() {
		this.vendido = true;

	}

	/**
	 * Indica si la casa ha sido vendida o no
	 * 
	 * @return boolean True si ha sido vendida, False si no
	 */
	public boolean isVendido() {
		return vendido;
	}

	

}
