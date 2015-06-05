package inmobiliariaAlNasir;

import java.io.Serializable;
import java.util.Date;

/**
 * Trabaja con los elementos de la CasaRural de forma individual. Puede ser
 * únicamente alquilada.
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 */
public class CasaRural extends Vivienda implements Serializable {

	/**
	 * ID de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Numero de plantas de la casa rural
	 */
	private int numPlantas;

	/**
	 * Crea una casa rural vacia y un código aleatorio siguiendo un patrón.
	 */
	public CasaRural() {
		setCodigoVivienda();
	}

	/**
	 * Crea una casa rural con todos los elementos.
	 * 
	 * @param codigoVivienda
	 *            Codigo unívoco de cada vivienda
	 * @param zona
	 *            Zona donde se situa
	 * @param m2
	 *            Número de metros cuadrados
	 * @param numHabitaciones
	 *            Número total de habitaciones
	 * @param numBaños
	 *            Número total de baños
	 * @param fecha
	 *            Fecha de construcción
	 * @param precio
	 *            Valor de la vivienda
	 * @param numPlantas
	 *            Numero total de plantas
	 * @throws ValorInvalidoException
	 *             Si alguno de los valores no es adecuado, salta la excepción
	 */
	public CasaRural(String codigoVivienda, Zona zona, int m2,
			int numHabitaciones, int numBaños, Date fecha, float precio,
			int numPlantas) throws ValorInvalidoException {
		super(codigoVivienda, zona, m2, numHabitaciones, numBaños, fecha,
				precio);
		setNumPlantas(numPlantas);
	}

	/**
	 * Crea una casa rural con un código de vivienda
	 * 
	 * @param codigoVivienda
	 *            Codigo de la vivienda
	 * @throws CodigoNoValidoException
	 *             Si el código no cumple con el patrón, salta la excepción
	 */
	CasaRural(String codigoVivienda) throws CodigoNoValidoException {
		super(codigoVivienda);
	}

	/**
	 * Establece el numero de plantas
	 * 
	 * @param numPlantas
	 *            Número de plantas
	 * @throws ValorInvalidoException
	 *             Si el numero de plantas es inferior a 0
	 */
	private void setNumPlantas(int numPlantas) throws ValorInvalidoException {
		this.numPlantas = numPlantas;

	}

	/**
	 * Crea el código de vivienda. Para ello, une tres números aleatorios con
	 * una letra. En este caso, al tratarse de una casa rural, son dos letras,
	 * CR. Si el codigo no es validado correctamente por el patrón, el código
	 * será null
	 */
	public void setCodigoVivienda() {
		String codigo = "";
		Integer n;
		for (int i = 0; i < 4; i++) {
			n = (int) (Math.random() * 9);
			codigo = codigo + n.toString();
		}
		codigo = codigo + "CR";
		if (esValido(codigo))
			codigoVivienda = codigo;
		else
			codigoVivienda = null;

	}

	/**
	 * Devuelve el numero de plantas
	 * 
	 * @return Número de plantas
	 */
	public int getNumPlantas() {
		return numPlantas;
	}

}
