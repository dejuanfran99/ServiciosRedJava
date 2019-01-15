package FTP;
/**
 * 
 */


import java.io.Serializable;

/**
 * @author dejua
 *
 */
public class PideFichero implements Serializable{

	String nombreFichero;
	
	public PideFichero(String nom) {
		this.nombreFichero = nom;
	}

	public String getNombreFichero() {
		return nombreFichero;
	}

	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}
	
	
}
