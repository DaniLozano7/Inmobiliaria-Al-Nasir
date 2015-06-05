package inmobiliariaAlNasir;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Trabaja con la vivienda y todos sus valores.
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 */
public abstract class Vivienda implements Serializable, Precio,
		Comparable<Vivienda> {
	/**
	 * ID de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Codigo de la vivienda. Unívoco y autogenerado.
	 */
	protected String codigoVivienda;
	/**
	 * Zona de la vivienda
	 */
	private Zona zona;
	/**
	 * Metros cuadrados de la vivienda
	 */
	private int m2;
	/**
	 * Numero de habitaciones de la vivienda
	 */
	private int numHabitaciones;
	/**
	 * Numero de baños de la vivienda
	 */
	private int numBaños;
	/**
	 * Antiguedad de la vivienda. Medida en dias, meses, o años.
	 */
	private String antiguedad;
	/**
	 * Fecha de construcción de la vivienda
	 */
	private Date fecha;
	/**
	 * Valor de la vivienda en euros
	 */
	protected float precio;
	/**
	 * Indica si la vivienda está alquilada o no
	 */
	private boolean alquilado = false;
	/**
	 * Patrón para el código de la vivienda. Formado por 4 digitos y una cadena
	 * de texto:
	 * <ul>
	 * <li>C si es una casa
	 * <li>P si es un piso
	 * <li>CR si es una casa rural
	 * </ul>
	 */
	static final private Pattern patternCodigo = Pattern
			.compile("^[0-9]{4}(C|P|CR)$");
	/**
	 * Patrón para la fecha de construcción. Abarca desde 1900 en adelante.
	 */
	static final private Pattern patternFecha = Pattern
			.compile("^(0?[1-9]|[12][0-9]|3[01])[\\/](0?[1-9]|1[012])[\\/](19|20)\\d{2}");

	/**
	 * Crea una vivienda vacia y un código aleatorio siguiendo un patrón.
	 */
	Vivienda() {
		setCodigoVivienda();
	}

	/**
	 * Crea una vivienda con todos los elementos.
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
	 * @throws ValorInvalidoException
	 *             Si alguno de los valores no es adecuado, salta la excepción
	 */
	Vivienda(String codigoVivienda, Zona zona, int m2, int numHabitaciones,
			int numBaños, Date fecha, float precio)
			throws ValorInvalidoException {
		this.codigoVivienda = codigoVivienda;
		this.setFecha(fecha);
		setZona(zona);
		setM2(m2);
		setNumHabitaciones(numHabitaciones);
		setNumBaños(numBaños);
		setAntiguedad(fecha);
		setPrecio(calcularPrecio(precio));
	}

	/**
	 * Crea una vivienda con un código de vivienda
	 * 
	 * @param codigoVivienda
	 *            Codigo de la vivienda
	 * @throws CodigoNoValidoException
	 *             Si el código no cumple con el patrón, salta la excepción
	 */
	public Vivienda(String codigoVivienda) throws CodigoNoValidoException {
		this.codigoVivienda = codigoVivienda;
	}

	/**
	 * Método de la interfaz comparable. Compara las fechas entre dos viviendas
	 * y devuelve un entero en función del resultado de dicha comparación. Nos
	 * permite ordenar las viviendas por antigüedad.
	 */
	@Override
	public int compareTo(Vivienda otraVivienda) {
		if (this.fecha.before(otraVivienda.fecha)) {
			return 1;
		} else if (this.fecha.after(otraVivienda.fecha)) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * Devuelve la zona de la vivienda
	 * 
	 * @return Zona de la vivienda
	 */
	public Zona getZona() {
		return zona;
	}

	/**
	 * Establece la zona de la vivienda
	 * 
	 * @param zona
	 *            Zona de la vivienda
	 */
	public void setZona(Zona zona) {
		if (zona != null)
			this.zona = zona;
		else
			this.zona = null;
	}

	/**
	 * Comprueba si un codigo de vivienda es valido lanzandolo contra un patrón
	 * 
	 * @param codigoVivienda
	 *            Codigo que deseamos validar
	 * @return boolean True si es valido, False si no lo es
	 */
	public static boolean esValido(String codigoVivienda) {
		return patternCodigo.matcher(codigoVivienda).matches();
	}

	/**
	 * Comprueba si una fecha es valida usando un patrón
	 * 
	 * @param fecha
	 *            Fecha que deseamos validar
	 * @return boolean True si es valida, False si no lo es
	 */
	public static boolean validarFecha(String fecha) {
		return patternFecha.matcher(fecha).matches();
	}

	/**
	 * Devuelve el codigo de la vivienda
	 * 
	 * @return codigoVivienda
	 */
	public String getCodigoVivienda() {
		return codigoVivienda;
	}

	/**
	 * Establece el codigo de la vivienda
	 */
	public abstract void setCodigoVivienda();

	/**
	 * Devuelve los metros cuadrados de la vivienda
	 * 
	 * @return Metros cuadrados
	 */
	public int getM2() {
		return m2;
	}

	/**
	 * Establece los metros cuadrados de la vivienda
	 * 
	 * @param m2
	 *            Metros cuadrados para asignar
	 * @throws ValorInvalidoException
	 *             Si los m2 son inferiores a 0, salta la excepcion
	 */
	public void setM2(int m2) throws ValorInvalidoException {
		if (m2 > 0)
			this.m2 = m2;
		else
			throw new ValorInvalidoException("El valor no es valido");
	}

	/**
	 * Devuelve el numero de habitaciones
	 * 
	 * @return Numero de habitaciones
	 */
	public int getNumHabitaciones() {
		return numHabitaciones;
	}

	/**
	 * Método de la interfaz Precio. Calcula el valor de la casa en función de
	 * la zona donde está situada.
	 */
	@Override
	public float calcularPrecio(float precio) {
		switch (this.getZona()) {
		case BRILLANTE:
			precio = (float) (precio * Precio.Brillante);
			return precio;
		case VALDEOLLEROS:
			precio = (float) (precio * Precio.Valdeolleros);
			return precio;
		case FATIMA:
			precio = (float) (precio * Precio.Fatima);
			return precio;
		case CAÑERO:
			precio = (float) (precio * Precio.Cañero);
			return precio;
		case CIUDADJARDIN:
			precio = (float) (precio * Precio.Ciudadjardin);
			return precio;
		case JUDERIA:
			precio = (float) (precio * Precio.Juderia);
			return precio;
		case CENTRO:
			precio = (float) (precio * Precio.Centro);
			return precio;
		default:
			return 0;
		}

	}

	/**
	 * Establece el numero de habitaciones de la vivienda
	 * 
	 * @param numHabitaciones
	 *            Numero de habitaciones
	 */
	public void setNumHabitaciones(int numHabitaciones) {
		this.numHabitaciones = numHabitaciones;

	}

	/**
	 * Devuelve el numero de baños de la vivienda
	 * 
	 * @return Numero de baños
	 */
	public int getNumBaños() {
		return numBaños;
	}

	/**
	 * Establece el numero de baños de la vivienda
	 * 
	 * @param numBaños
	 *            Numero de baños
	 */
	public void setNumBaños(int numBaños) {
		this.numBaños = numBaños;
	}

	/**
	 * Devuelve la antigüedad de la vivienda
	 * 
	 * @return Antigüedad
	 */
	public String getAntiguedad() {
		return antiguedad;
	}

	/**
	 * Establece la antigüedad de la vivienda. Resta una fecha a la actual. La
	 * diferencia la pasa a años, meses o días según el tamaño de la misma.
	 * 
	 * @param fecha
	 *            Fecha a comparar
	 * @throws ValorInvalidoException
	 *             Si la diferencia es inferior a 0, salta la excepción
	 */
	public void setAntiguedad(Date fecha) throws ValorInvalidoException {
		Calendar c = Calendar.getInstance();
		long fechaActual = (c.getTimeInMillis()) / (1000 * 60 * 60 * 24);
		long fechaConstruccion = (fecha.getTime()) / (1000 * 60 * 60 * 24);
		Integer antiguedad = (int) (fechaActual - fechaConstruccion); // Dias
		if (antiguedad < 1)
			throw new ValorInvalidoException("El valor no es valido");
		if (antiguedad > 365) {
			antiguedad = antiguedad / 365; // Años
			this.antiguedad = antiguedad.toString() + " años";
		} else if (antiguedad > 30) {
			antiguedad = antiguedad / 30; // Meses
			this.antiguedad = antiguedad.toString() + " meses";
		} else {
			this.antiguedad = antiguedad.toString() + " días";
		}
	}

	/**
	 * Devuelve la fecha de la vivienda
	 * 
	 * @return Fecha de la vivienda
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Establece la fecha de la vivienda
	 * 
	 * @param fecha
	 *            Fecha de la vivienda
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Devuelve el precio de la vivienda
	 * 
	 * @return Precio de la vivienda
	 */
	public float getPrecio() {
		return precio;
	}

	/**
	 * Establece el precio de la vivienda
	 * 
	 * @param precio
	 *            Precio de la vivienda
	 * @throws ValorInvalidoException
	 *             Si el precio es inferior a 0, salta la excepción
	 */
	public void setPrecio(float precio) throws ValorInvalidoException {
		if (precio > 0)
			this.precio = precio;
		else
			throw new ValorInvalidoException("El valor no es valido");
	}

	/**
	 * Alquila una vivienda
	 */
	public void alquilar() {
		this.alquilado = true;

	}

	/**
	 * Comprueba si la vivienda esta alquilada o no
	 * 
	 * @return boolean True si ha sido alquilada, False si no
	 */
	public boolean isAlquilado() {
		return alquilado;
	}

	/**
	 * Si los objetos son iguales segun el metodo equals, tendrán el mismo valor
	 * hash
	 * 
	 * @return Valor hash
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoVivienda == null) ? 0 : codigoVivienda.hashCode());
		return result;
	}

	/**
	 * Indica si otro vivienda es igual a esta. Se basa en el codigo de la
	 * vivienda para hacer la comparación
	 * 
	 * @param obj
	 *            Vivienda que vamos a comparar.
	 * @return boolean True si son iguales y False en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vivienda other = (Vivienda) obj;
		if (codigoVivienda == null) {
			if (other.codigoVivienda != null)
				return false;
		} else if (!codigoVivienda.equals(other.codigoVivienda))
			return false;
		return true;
	}

	/**
	 * Muestra los elementos de la vivienda en una cadena
	 */
	@Override
	public String toString() {
		return "Vivienda [codigoVivienda=" + codigoVivienda + ", m2=" + m2
				+ ", numHabitaciones=" + numHabitaciones + ", numBaños="
				+ numBaños + ", añosAntiguedad=" + antiguedad + ", precio="
				+ precio + "]";
	}

}