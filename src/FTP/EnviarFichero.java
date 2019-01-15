package FTP;
/**
 * 
 */


import java.io.Serializable;

/**
 * @author dejua
 *
 */
public class EnviarFichero implements Serializable{

	private byte[] contenidoFichero;
	private String nombre;
	private String directorio;
	
	public EnviarFichero(byte[] contenidoFichero, String nombre, String directorio) {
		super();
		this.contenidoFichero = contenidoFichero;
		this.nombre = nombre;
		this.directorio = directorio;
	}

	public byte[] getContenidoFichero() {
		return contenidoFichero;
	}

	public void setContenidoFichero(byte[] contenidoFichero) {
		this.contenidoFichero = contenidoFichero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDirectorio() {
		return directorio;
	}

	public void setDirectorio(String directorio) {
		this.directorio = directorio;
	}
	
}
