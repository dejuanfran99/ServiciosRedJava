package SMTP_POP3;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Clase POP3 Cliente, la cual realiza la conexión con el servidor POP3 de
 * GOOGLE y almacena todos los mensajes en un ArrayList de correos
 * 
 * @author Juan Francisco Cabello, Mario Gonzalez, Jesús Romero
 * @version 1.0
 *
 */
public class POP3ClientJavaxMail implements ActionListener {
	/**
	 * Email de origen
	 */
	public String origen;
	/**
	 * Password de origen
	 */
	public String pwdOrigen;
	/**
	 * Nombre de host
	 */
	private String host = "pop.gmail.com";
	/**
	 * Array de correos
	 */
	private ArrayList<Correo> correos = new ArrayList<>();
	/**
	 * Vista de POP3
	 */
	private VistaPOP3 vi;
	/**
	 * Vista de login
	 */
	private VistaLogin login;

	/**
	 * Constructor de POP3 client, el cual se conecta con el servidor de POP3 de
	 * GMAIL y recupera todos los correos.
	 * 
	 * @param v         vista POP3
	 * @param origen    Email de origen
	 * @param pwdOrigen Password de origen
	 * @param login     vista login
	 */
	public POP3ClientJavaxMail(VistaPOP3 v, String origen, String pwdOrigen){
	     
		try {
			this.origen = origen;
			this.pwdOrigen = pwdOrigen;
			this.vi = v;

			// Propiedades del servidor POP3
			Properties properties = new Properties();
			properties.put("mail.pop3.host", host);
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3.starttls.enable", "true");

			// Iniciamos la sesión con el servidor POP3
			Session emailSession = Session.getDefaultInstance(properties);

			// Creamos el Store de POP3 y nos conectamos con el servidor POP3
			Store store = emailSession.getStore("pop3s");
			store.connect(host, origen, pwdOrigen);

			// Abre INBOX y lo guarda en una carpeta
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// Recibe los mensajes en un array
			recogerDatos(emailFolder.getMessages());
			vi.initPaneles(leerCorreos().size(), leerCorreos());
			for (int i = 0; i < vi.getBotones().size(); i++) {
				vi.getBotones().get(i).addActionListener(this);

			}
			vi.propiedadesVentana();

			// Cierra el emailFolder y el store
			emailFolder.close(false);
			store.close();
			
		    // Clase en la que está el código a ejecutar 
		     TimerTask timerTask = new TimerTask() 
		     { 
		         public void run()
		         { 
		             // Aquí el código que queremos ejecutar. 		        	 
		        	 try {
		        		Session sesion = Session.getDefaultInstance(properties);
						Store store = sesion.getStore("pop3s");
						store.connect(host, origen, pwdOrigen);
						
						Folder bandeja = store.getFolder("INBOX");
						bandeja.open(Folder.READ_ONLY);
						//System.out.println("TAMAÑO CORREOS : "+correos.size());
						int tamaño = correos.size();
						if(bandeja.getMessages().length>=1) {
							recogerDatos(bandeja.getMessages());
							JOptionPane.showMessageDialog(v, "TIENES UN NUEVO MENSAJE", "MENSAJE NUEVO RECIBIDO", JOptionPane.INFORMATION_MESSAGE);
							vi.dispose();
							vi = new VistaPOP3();
							vi.initPaneles(leerCorreos().size(), leerCorreos());
							for (int i = 0; i < vi.getBotones().size(); i++) {
								vi.getBotones().get(i).addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										accionBoton(e);
										
									}
								});
							}
							vi.propiedadesVentana();
						}
						
						bandeja.close();
						store.close();
						
					} catch (NoSuchProviderException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		         } 
		     }; 

		      // Aquí se pone en marcha el timer cada segundo. 
		     Timer timer = new Timer(); 
		     // Dentro de 0 milisegundos avísame cada 5000 milisegundos 
		     timer.scheduleAtFixedRate(timerTask, 0, 5000);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(v, "No se a conectar con el servidor", "Error al conectar con el servidor",
					JOptionPane.ERROR_MESSAGE);
			v.dispose();
		}
	}

	/**
	 * Método que recoge todos los datos de los correos
	 * 
	 * @param messages lista de mensajes
	 */
	public void recogerDatos(Message[] messages) {
		correos.removeAll(correos);
		for (int i = 0, n = messages.length; i < n; i++) {
			Message message = messages[i];

			try {
				  String contentType = message.getContentType();
				  String messageContent="";

				   if (contentType.contains("multipart")) {
				        Multipart multiPart = (Multipart) message.getContent();
				        int numberOfParts = multiPart.getCount();
				        for (int partCount = 0; partCount < numberOfParts; partCount++) {
				            MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
				                messageContent = part.getContent().toString();
				        }
				    }
				    else if (contentType.contains("text/plain")|| contentType.contains("text/html")) {
				        Object content = message.getContent();
				        if (content != null) {
				            messageContent = content.toString();
				        }
				    }
				
				correos.add(new Correo(message.getSubject().toString(), origen, pwdOrigen,
						message.getFrom()[0].toString(),messageContent));
				//System.out.println(message.getContent().toString()+ "-------------------");
			} catch (MessagingException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(vi, "No se a podido acceder a los mensajes",
						"Error al acceder a los mensajes", JOptionPane.ERROR_MESSAGE);
				vi.dispose();
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(vi, "No se a conectar con el servidor",
						"Error al conectar con el servidor", JOptionPane.ERROR_MESSAGE);
				vi.dispose();
			}
		}
		guardarCorreos();
	}

	public void guardarCorreos() {
		File fichero = new File("C:\\Correos.dat");
		FileOutputStream flujoEscritura;
		ArrayList<Correo> correosGuardados = leerCorreos();
		try {
			flujoEscritura = new FileOutputStream(fichero);
			ObjectOutputStream objetoEscritura = new ObjectOutputStream(flujoEscritura);

			for (int i = 0; i < correos.size(); i++) {
				objetoEscritura.writeObject(correos.get(i));
			}
			for (int i = 0; i < correosGuardados.size(); i++) {
				objetoEscritura.writeObject(correosGuardados.get(i));
			}
			objetoEscritura.close();
			flujoEscritura.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(vi, "No se ha podido leer el fichero",
					"Error al leer el fichero donde se almacena los correos", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(vi, "No se ha podido acceder al fichero",
					"Error al acceder al fichero donde se almacena los correos", JOptionPane.ERROR_MESSAGE);
		}
	}

	public ArrayList<Correo> leerCorreos() {

		File fichero = new File("C:\\Correos.dat");
		FileInputStream flujoLectura;
		ArrayList<Correo> CorreosGuardados = new ArrayList<>();
		if (fichero.exists()) {
			try {
				flujoLectura = new FileInputStream(fichero);
				ObjectInputStream objetoLectura = new ObjectInputStream(flujoLectura);
				try {
					if (fichero.canRead()) {
						while (true) {
							Correo c = (Correo) objetoLectura.readObject();
							CorreosGuardados.add(c);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				objetoLectura.close();
				flujoLectura.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(vi, "No se ha podido leer el fichero",
						"Error al leer el fichero donde se almacena los correos", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(vi, "No se ha podido acceder al fichero",
						"Error al acceder al fichero donde se almacena los correos", JOptionPane.ERROR_MESSAGE);
			}
		}
		return CorreosGuardados;
	}

	/**
	 * Devuelve las lista de correos
	 * 
	 * @return ArrayList correos
	 */
	public ArrayList<Correo> getCorreos() {
		return correos;
	}
	
	public void accionBoton(ActionEvent e) {
		JButton foco = (JButton) e.getSource();
		VistaSMTP vista = new VistaSMTP();
		vista.getCaja().get(0).setText(leerCorreos().get(Integer.parseInt(foco.getName())).getOrigen());
		vista.getCaja().get(1).setText(leerCorreos().get(Integer.parseInt(foco.getName())).getDestinatario());
		vista.getCaja().get(2).setText(leerCorreos().get(Integer.parseInt(foco.getName())).getAsunto());

		for (int i = 0; i < 3; i++) {
			vista.getCaja().get(i).setEnabled(false);
		}
		vista.getMensaje().setEditable(false);
		vista.getMensaje().setText(leerCorreos().get(Integer.parseInt(foco.getName())).getTexto());
		vista.getBoton().setVisible(false);

		vista.propiedadesVentana();
		vista.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		accionBoton(e);

	}
}
