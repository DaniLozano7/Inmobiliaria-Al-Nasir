package inmobiliariaAlNasir;

/**
 * Excepcion que indica que alguno de los valores introducidos no es valido
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 */
public class ValorInvalidoException extends Exception {
	/**
	 * ID de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Muestra un mensaje
	 * @param message Mensaje
	 */
	public ValorInvalidoException(String message) {
		super(message);
	}
}
