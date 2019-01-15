/**
 * 
 */
package MainUnificado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import FTP.ControladorCliente;
import FTP.Servidor;
import SMTP_POP3.POP3ClientJavaxMail;
import SMTP_POP3.VistaLogin;
import SMTP_POP3.VistaLoginPOP3;

/**
 * @author dejua
 *
 */
public class ControladorMenu implements ActionListener{

	VistaMenu v;
	boolean serverinit;
	
	public ControladorMenu() {
		// TODO Auto-generated constructor stub
		
		v = new VistaMenu();
		for (int i = 0; i < v.getBotones().size(); i++) {
			v.getBotones().get(i).addActionListener(this);
		}
		serverinit = false;
		
	}

	@Override
	public void actionPerformed(ActionEvent boton) {
		// TODO Auto-generated method stub
		
		JButton b = (JButton) boton.getSource();
		
		if(b.getText().equals(v.getBotones().get(0).getText())) {
			HiloUnificador hu = new HiloUnificador();
			serverinit = true;
			hu.start();
			
		}
		else if(b.getText().equals(v.getBotones().get(1).getText())) {
			if(serverinit) {
				int puerto = 4444;
				Socket s = null;
					try {
						s = new Socket("localhost", puerto);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Error debe iniciar el servidor primero", "ERROR SERVIDOR NO INICIADO", JOptionPane.ERROR_MESSAGE);
					}

				ControladorCliente hiloC = new ControladorCliente(s);
				new Thread(hiloC).start();
			}else {
				JOptionPane.showMessageDialog(null, "Error debe iniciar el servidor primero", "ERROR SERVIDOR NO INICIADO", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if(b.getText().equals(v.getBotones().get(2).getText())) {
			new VistaLoginPOP3();
		}
		
		else if(b.getText().equals(v.getBotones().get(3).getText())) {
			new VistaLogin();
		}
		
	}
}
