package SMTP_POP3;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * Clase SMTP cliente, la cual es la encargada de conectarse con el servidor de
 * GMAIL de GOOGLE, y enviar el mensaje al email deseado.
 * 
 * @author Juan Francisco Cabello, Mario Gonzalez, Jesús Romero
 * @version 1.0
 *
 */
public class SMTPClientJavaxMail {
	/**
	 * Objeto correo
	 */
	private Correo correo;

	/**
	 * Constructor de la clase SMTP Cliente el cual recibe la vista y el objeto
	 * correo
	 * 
	 * @param v      vista de SMTP
	 * @param correo objeto correo
	 */
	public SMTPClientJavaxMail(VistaSMTP v, Correo correo) {
		Properties props = new Properties(); //Propiedades del servidor SMTP
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		this.correo = correo;

		//Creamos la conexión con el servidor SMTP
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(correo.getOrigen(), correo.getPassOrigen());
			}
		});

		try {
			Transport.send(constructorMensaje(v, session)); // Envio del correo

			JOptionPane.showMessageDialog(v, "Mensaje Enviado Correctamente!", "Mensaje Enviado", 1);
			v.dispose();
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(v, "No se a podido enviar el mensaje", "Error al enviar el mensaje",
					JOptionPane.ERROR_MESSAGE);
			v.dispose();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Metodo que crea el mensaje y lo devuelve
	 * 
	 * @param v       vista de SMTP
	 * @param session seción con el servidor smtp
	 * @return devuelve el mensaje construido
	 */
	private Message constructorMensaje(VistaSMTP v, Session session) {
		Message mensaje = new MimeMessage(session);
		try {
			mensaje.setFrom(new InternetAddress(correo.getOrigen()));
			mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo.getDestinatario()));
			mensaje.setSubject(correo.getAsunto());
			mensaje.setText(correo.getTexto());
		} catch (AddressException e) {
			JOptionPane.showMessageDialog(v, "El email de destino no se encuentra", "Error con el destino",
					JOptionPane.ERROR_MESSAGE);
			v.dispose();
			e.printStackTrace();
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(v, "No se a podido enviar el mensaje", "Error al enviar el mensaje",
					JOptionPane.ERROR_MESSAGE);
			v.dispose();
			e.printStackTrace();
		}

		return mensaje;
	}

}
