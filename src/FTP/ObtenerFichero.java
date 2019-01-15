package FTP;

import java.io.Serializable;

/**
 * Creamos y devolvemos un array de bytes que contendrá el contenido del fichero que estemos
 * cargardo/descargando
 * 
 * @author dejua
 *
 */
public class ObtenerFichero implements Serializable {

	byte[] contenidoFichero;

	public ObtenerFichero(byte[] cf) {
		contenidoFichero = cf;
	}

	public byte[] getContenidoFichero() {
		return contenidoFichero;
	}

	public void setContenidoFichero(byte[] contenidoFichero) {
		this.contenidoFichero = contenidoFichero;
	}

}
