package entornoGrafico;

import java.io.File;

import inmobiliariaAlNasir.Inmobiliaria;

/**
 * Almacena los elementos que serán usados por todas las clases del GUI
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 */
public class General {
	/**
	 * Inmobiliaria de uso general
	 */
	public static Inmobiliaria lista = new Inmobiliaria();
	/**
	 * Archivo vacio por defecto asignado a nuestro programa
	 */
	static File archivo = new File("Sin_titulo.inm");
}
