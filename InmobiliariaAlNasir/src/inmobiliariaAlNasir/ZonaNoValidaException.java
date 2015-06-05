package inmobiliariaAlNasir;

/**
 * Excepcion que indica que la zona de la vivienda no es valida
 *  
 * @author Daniel Lozano Torrico
 * @version 1.0
 */
public class ZonaNoValidaException extends Exception {

	/**
	 * ID de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Muestra un mensaje
	 * 
	 * @param message
	 *            Mensaje
	 */
	public ZonaNoValidaException(String message) {
		super(message);
		
	}
}
