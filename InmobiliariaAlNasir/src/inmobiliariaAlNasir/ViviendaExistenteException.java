package inmobiliariaAlNasir;

/**
 * Excepcion que indica que la vivienda ya existe
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 */
public class ViviendaExistenteException extends Exception {

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
	public ViviendaExistenteException(String message) {
		super(message);
	}
}
