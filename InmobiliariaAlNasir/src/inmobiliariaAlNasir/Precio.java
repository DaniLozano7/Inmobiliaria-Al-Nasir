package inmobiliariaAlNasir;

/**
 * Interfaz que calcula el precio de las viviendas en función de la zona donde estén situadas.
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 *
 */
public interface Precio {
	/**
	 * Modificador de precio para las viviendas del Brillante
	 */
	static final double Brillante = 1.40; 
	/**
	 * Modificador de precio para las viviendas de Valdeolleros
	 */
	static final double Valdeolleros = 1.10; 
	/**
	 * Modificador de precio para las viviendas de Fatima
	 */
	static final double Fatima = 1.11;
	/**
	 * Modificador de precio para las viviendas de Cañero
	 */
	static final double Cañero = 1.15;
	/**
	 * Modificador de precio para las viviendas de Ciudad Jardin
	 */
	static final double Ciudadjardin = 1.22; 
	/**
	 * Modificador de precio para las viviendas de la Juderia
	 */
	static final double Juderia = 1.30;
	/**
	 * Modificador de precio para las viviendas del Centro
	 */
	static final double Centro = 1.35;
	
	/**
	 * Reasigna el precio que recibe
	 * 
	 * @param precio Precio base de la vivienda
	 * @return float Precio final de la vivienda
	 */
	abstract float calcularPrecio(float precio);
}
