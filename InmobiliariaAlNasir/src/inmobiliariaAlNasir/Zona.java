package inmobiliariaAlNasir;

/**
 * Enumeracion de las zonas disponibles para instalar viviendas.
 * Los zonas son:
 * 
 * <ul>
 * <li> Brillante
 * <li> Valdeolleros
 * <li> Fatima
 * <li> Cañero
 * <li> Ciudad Jardin
 * <li> Juderia
 * <li> Centro
 * </ul>
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 */
public enum Zona {

	/**
	 * Zona del Brillante
	 */
	BRILLANTE,
	/**
	 * Barrio de Valdeolleros
	 */
	VALDEOLLEROS,
	/**
	 * Barrio de Fatima
	 */
	FATIMA,
	/**
	 * Barriada de Cañero
	 */
	CAÑERO,
	/**
	 * Zona de Ciudad Jardin
	 */
	CIUDADJARDIN,
	/**
	 * Zona de la Juderia
	 */
	JUDERIA,
	/**
	 * Zona del Centro de Córdoba
	 */
	CENTRO;
	
	/**
	 * Array que contiene el número de zonas que tiene la enumeracion
	 */
	private static final Zona[] VALUES = Zona.values();
	/**
	 * Genera las opciones para un menu de zonas
	 * @return devuelve un array de cadenas con todas las opciones del menu
	 */
	public static String[] generarOpcionesMenu() {
		String[] opcionesMenu = new String[getValues().length + 1];
		int i = 0;
		for (Zona zona : getValues()) {
			opcionesMenu[i++] = zona.name();
		}
		opcionesMenu[i] = "Salir";
		return opcionesMenu;
	}
	/**
	 * Metodo que devuelve un array de zonas
	 * @return devuelve el campo values
	 */
	public static Zona[] getValues() {
		return VALUES;
	}
}
