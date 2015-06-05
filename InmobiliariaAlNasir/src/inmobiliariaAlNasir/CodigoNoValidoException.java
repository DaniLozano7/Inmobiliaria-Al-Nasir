package inmobiliariaAlNasir;

/**
 * Excepci�n que indica que el c�digo de vivienda introducido no es valido
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 */
public class CodigoNoValidoException extends Exception {
	/**
	 * ID de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Muestra un mensaje
	 * @param message Mensaje
	 */
	public CodigoNoValidoException(String message) {
		super(message);
		
	}
}
