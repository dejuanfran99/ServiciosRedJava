/**
 * 
 */
package SMTP_POP3;

import java.io.Serializable;

/**
 * 
 * @author Juan Francisco Cabello, Mario Gonzalez, Jesús Romero
 * @version 1.0
 *
 */
public class Correo implements Serializable{
	/**
	 * Asunto del mensaje
	 */
	private String asunto;
	/**
	 * Email desde donde se va a enviar el mensaje
	 */
	private String origen;
	/**
	 * Contraseña del email de origen
	 */
	private String passOrigen;
	/**
	 * Email al que se enviara el mensaje
	 */
	private String destinatario;
	/**
	 * mensaje del email
	 */
	private String texto;

	/**
	 * Constructor vacio
	 */
	public Correo() {

	}

	/**
	 * Constructor parametrizado
	 * 
	 * @param asunto       asunto del mensaje
	 * @param origen       email de origen
	 * @param passOrigen   password de origen
	 * @param destinatario email de destinatario
	 * @param texto        mensaje
	 */
	public Correo(String asunto, String origen, String passOrigen, String destinatario, String texto) {
		this.asunto = asunto;
		this.origen = origen;
		this.passOrigen = passOrigen;
		this.destinatario = destinatario;
		this.texto = texto;
	}

	/**
	 * Get de asunto
	 * 
	 * @return asunto
	 */
	public String getAsunto() {
		return asunto;
	}

	/**
	 * Set de asunto
	 * 
	 * @param asunto
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * Get origen
	 * 
	 * @return origen
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * Set origen
	 * 
	 * @param origen
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}

	/**
	 * Get password
	 * 
	 * @return password de origen
	 */
	public String getPassOrigen() {
		return passOrigen;
	}

	/**
	 * Set password
	 * 
	 * @param passOrigen
	 */
	public void setPassOrigen(String passOrigen) {
		this.passOrigen = passOrigen;
	}

	/**
	 * Get destinatario
	 * 
	 * @return destinatario
	 */
	public String getDestinatario() {
		return destinatario;
	}

	/**
	 * Set destinatario
	 * 
	 * @param destinatario
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/**
	 * Get texto
	 * 
	 * @return texto mensaje
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * Set texto
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/** Método toString
	 * @see java.lang.Object#toString()
	 * @return String
	 */
	@Override
	public String toString() {
		return "Correo [asunto=" + asunto + ", origen=" + origen + ", passOrigen=" + passOrigen + ", destinatario="
				+ destinatario + ", texto=" + texto + "]";
	}
	
	
}
