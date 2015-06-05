package inmobiliariaAlNasir;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Se encarga de las tareas relacionadas con los ficheros, como el guardado, la
 * apertura y la extension del mismo.
 * 
 * @author Daniel Lozano Torrico
 * @version 1.0
 *
 */
public class Fichero {

	/**
	 * Método para abrir una inmobiliaria ya creada
	 * 
	 * @param file
	 *            Fichero a abrir
	 * @return Inmobiliaria existente
	 * @throws ClassNotFoundException
	 *             Si no encuentra la clase
	 * @throws IOException
	 *             Si hay un error de escritura/lectura del fichero
	 */
	public static Inmobiliaria abrir(File file) throws ClassNotFoundException,
			IOException {
		file = comprobarExtension(file);
		try (ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(file)))) {
			return (Inmobiliaria) in.readObject();
		}
	}

	/**
	 * Método para guardar una inmobiliaria
	 * 
	 * @param file
	 *            Fichero a guardar
	 * @param inmobiliaria
	 *            La inmobiliaria a guardar
	 * @throws IOException
	 *             Si hay un error de escritura/lectura del fichero
	 */
	public static void guardar(File file, Inmobiliaria inmobiliaria)
			throws IOException {
		file = comprobarExtension(file);
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(file, false)))) {
			out.writeObject(inmobiliaria);
		}
	}

	/**
	 * Comprueba si el fichero existe
	 * 
	 * @param file
	 *            Fichero a comprobar
	 * @return true si el fichero existe, false en otro caso
	 */
	public static boolean isExists(File file) {
		file = comprobarExtension(file);
		return file.exists();
	}

	/**
	 * Comprueba si la extensión del fichero es válida
	 * 
	 * @param file
	 *            Fichero a comprobar
	 * @return Fichero con la extensión válida
	 */
	public static File comprobarExtension(File file) {
		String path = file.getPath();
		if (!path.endsWith(".inm"))
			return new File(path + ".inm");
		return file;
	}
}
