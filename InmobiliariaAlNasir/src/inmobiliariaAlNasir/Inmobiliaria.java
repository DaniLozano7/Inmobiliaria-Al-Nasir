package inmobiliariaAlNasir;

import java.io.Serializable;
import java.util.ArrayList;

import entornoGrafico.General;

/**
 * Clase en la cual se crea y se gestiona la inmobiliaria "Al-Nasir"
 * 
 * <ul>
 * <li>Lógicamente, no podrá añadirse una vivienda inválida en la inmobiliaria.
 * <li>Han de conocerse todas sus características. Ninguno de los valores
 * enteros (M2, Precio, etc) puede ser menor que 0.
 * </ul>
 * 
 * Realiza las siguientes opciones:
 * 
 * <ul>
 * <li>Añadir vivienda.
 * <li>Eliminar vivienda.
 * <li>Alquilar vivienda.
 * <li>Vender vivienda.
 * <li>Mostrar vivienda (por codigo).
 * <li>Mostrar vivienda (por indice).
 * <li>Mostrar el tamaño de la inmobiliaria
 * </ul>
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 */
public class Inmobiliaria implements Serializable {

	/**
	 * ID de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ArrayList de viviendas
	 */
	public ArrayList<Vivienda> viviendas = new ArrayList<Vivienda>();

	/**
	 * Campo que controla si la inmobiliaria ha sido modificada
	 */
	private boolean modificado = false;

	/**
	 * Añade una vivienda a la inmobiliaria. Si ya existe, mostrará un mensaje
	 * de error.
	 * 
	 * @param vivienda
	 *            Nueva vivienda con todos sus elementos
	 * @return boolean True si la vivienda ha sido añadida, False si no
	 * @throws ValorInvalidoException
	 *             Si alguno de los atributos de la vivienda no son validos,
	 *             salta la excepcion
	 * @throws ViviendaExistenteException
	 *             Si la vivienda ya existe, salta la excepcion
	 * @throws NumberFormatException
	 *             Si alguno de los atributos numericos recibe un valor
	 *             diferente, salta la excepción
	 */
	public boolean annadir(Vivienda vivienda) throws ValorInvalidoException,
			ViviendaExistenteException, NumberFormatException {
		if (viviendas.contains(vivienda))
			throw new ViviendaExistenteException("La vivienda ya existe");
		setModificado(true);
		return viviendas.add(vivienda);
	}

	/**
	 * Vende una vivienda de la inmobiliaria. Recibe el código individual de la
	 * misma para identificarla.
	 * 
	 * @param codigoVivienda
	 *            Codigo de la vivienda
	 * @throws ViviendaNoExistenteException
	 *             Si la vivienda no existe, salta la excepción
	 * @throws CodigoNoValidoException
	 *             Si el código que escribimos no es valido, salta la excepcion
	 * @throws ValorInvalidoException
	 *             Si intentamos vender una casa rural, salta la excepción
	 */
	public void vender(String codigoVivienda)
			throws ViviendaNoExistenteException, CodigoNoValidoException,
			ValorInvalidoException {
		codigoVivienda = codigoVivienda.toUpperCase();
		Vivienda vivienda = General.lista.get(codigoVivienda);
		Class<? extends Vivienda> clase = vivienda.getClass();

		if (clase == Casa.class)
			((Casa) vivienda).vender();
		else if (clase == Piso.class)
			((Piso) vivienda).vender();
		else
			throw new ValorInvalidoException(
					"Las casas rurales no se pueden vender");

		setModificado(true);
	}

	/**
	 * Alquila una vivienda. Recibe el codigo individual de la misma para
	 * identificarla
	 * 
	 * @param codigoVivienda
	 *            Codigo de la vivienda
	 * @throws ViviendaNoExistenteException
	 *             Si la vivienda no existe, salta la excepción
	 * @throws CodigoNoValidoException
	 *             Si el código que escribimos no es valido, salta la excepcion
	 */
	public void alquilar(String codigoVivienda)
			throws ViviendaNoExistenteException, CodigoNoValidoException {
		codigoVivienda = codigoVivienda.toUpperCase();
		if (Vivienda.esValido(codigoVivienda)) {
			for (Vivienda vivienda : viviendas) {
				if (vivienda.getCodigoVivienda().equals(codigoVivienda))
					vivienda.alquilar();
			}
			setModificado(true);
		} else {
			throw new CodigoNoValidoException("El codigo no es valido");
		}

	}

	/**
	 * Elimina una vivienda. Recibe el codigo individual de la misma para
	 * identificarla
	 * 
	 * @param codigoVivienda
	 *            Codigo de la vivienda
	 * @return boolean True si ha sido eliminado, False si no
	 * @throws CodigoNoValidoException
	 *             Si el código que escribimos no es valido, salta la excepcion
	 * @throws ViviendaNoExistenteException
	 *             Si la vivienda no existe, salta la excepción
	 */
	public boolean eliminar(String codigoVivienda)
			throws CodigoNoValidoException, ViviendaNoExistenteException {
		codigoVivienda = codigoVivienda.toUpperCase();
		if (Vivienda.esValido(codigoVivienda)) {
			for (Vivienda vivienda : viviendas) {
				codigoVivienda = codigoVivienda.toUpperCase();
				if (vivienda.getCodigoVivienda().equals(codigoVivienda)) {
					setModificado(true);
					return viviendas.remove(vivienda);
				} else
					throw new ViviendaNoExistenteException(
							"La vivienda no existe");
			}
			return false;
		} else {
			throw new CodigoNoValidoException("El codigo no es valido");
		}

	}

	/**
	 * Devuelve una vivienda. Recibe el codigo individual de la misma para
	 * identificarla
	 * 
	 * @param codigoVivienda
	 *            Codigo de la vivienda
	 * @return Vivienda que cumple con las especificaciones indicadas
	 * @throws ViviendaNoExistenteException
	 *             Si la vivienda no existe, salta la excepción
	 * @throws CodigoNoValidoException
	 *             Si el código que escribimos no es valido, salta la excepcion
	 */
	public Vivienda get(String codigoVivienda)
			throws ViviendaNoExistenteException, CodigoNoValidoException {
		codigoVivienda = codigoVivienda.toUpperCase();

		if (!Vivienda.esValido(codigoVivienda))
			throw new CodigoNoValidoException("El codigo no es valido");

		for (Vivienda vivienda : viviendas) {
			if (vivienda.getCodigoVivienda().equals(codigoVivienda))
				return vivienda;
		}

		throw new ViviendaNoExistenteException("La vivienda no existe");

	}

	/**
	 * Devuelve una vivienda de la inmobiliaria segun su posición en el
	 * ArrayList
	 * 
	 * @param index
	 *            Posicion de la vivienda
	 * @return Vivienda que cumple con las especificaciones indicadas
	 */
	public Vivienda get(int index) {
		if (viviendas.isEmpty())
			return null;
		if (index < 0 | index > viviendas.size() - 1)
			return null;
		return viviendas.get(index);
	}
	
	/**
	 * Crea el conjunto de viviendas basandose en que si hayan sido alquiladas
	 * @return Inmobiliaria Nueva inmobiliaria formada con viviendas alquiladas
	 * @throws ValorInvalidoException Si los valores de la vivienda que añade no cumplen con los requisitos
	 * @throws ViviendaExistenteException Si la vivienda que va a añadir ya existe
	 */
	public static Inmobiliaria generarInmobiliariaAlquiladas() throws ValorInvalidoException,
			ViviendaExistenteException {
		Inmobiliaria viviendasAlquiladas = new Inmobiliaria();
		for (Vivienda vivienda : General.lista.viviendas) {
			if (vivienda.isAlquilado() == true)
				viviendasAlquiladas.annadir(vivienda);

		}
		return viviendasAlquiladas;
	}
	
	/**
	 * Crea el conjunto de viviendas basandose en que si hayan sido vendidas
	 * @return Inmobiliaria Nueva inmobiliaria formada con viviendas vendidas
	 * @throws ValorInvalidoException Si los valores de la vivienda que añade no cumplen con los requisitos
	 * @throws ViviendaExistenteException Si la vivienda que va a añadir ya existe
	 */
	public static Inmobiliaria generarInmobiliariaVendidas() throws ValorInvalidoException,
			ViviendaExistenteException {
		Inmobiliaria viviendasVendidas = new Inmobiliaria();
		for (Vivienda vivienda : General.lista.viviendas) {
			if (vivienda.getClass() == Casa.class) {
				if (((Casa) vivienda).isVendido() == true)
					viviendasVendidas.annadir(vivienda);
			} else if (vivienda.getClass() == Piso.class) {
				if (((Piso) vivienda).isVendido() == true)
					viviendasVendidas.annadir(vivienda);
			}
		}
		return viviendasVendidas;
	}

	/**
	 * @param modificado
	 *            the modificado to set
	 */
	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	/**
	 * @return the modificado
	 */
	public boolean isModificado() {
		return modificado;
	}

	/**
	 * Muestra el tamaño de la inmobiliaria
	 * 
	 * @return int Tamaño de la inmobiliaria
	 */
	public int size() {
		return viviendas.size();
	}
}
