package FTP;
/**
 * 
 */


import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import MainUnificado.ControladorMenu;

/**
 * @author dejua
 *
 */
public class Servidor {

	static int PUERTO=4444;
	static public EstructuraFicheros NF;
	static ServerSocket servidor;
	
	public static void aplicacion() throws IOException {
		String Directorio="";
		JFileChooser f = new JFileChooser();
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		f.setDialogTitle("SELECCIONA EL DIRECTORIO DONDE ESTAN LOS ARCHIVOS");
		int returnVal = f.showDialog(f, "Seleccionar");
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = f.getSelectedFile();
			Directorio = file.getAbsolutePath();
		}
		if(Directorio.equals("")) {
			System.out.println("Debe seleccionar un archivo");
			JOptionPane.showMessageDialog(null,"Debe seleccionar un archivo","Error",  JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		servidor = new ServerSocket(PUERTO);
		System.out.println("Servidor Iniciado");
		while(true) {
			try {
				Socket cliente = servidor.accept();
				System.out.println("CLIENTE CONECTADO");
				NF = new EstructuraFicheros(Directorio);
				HiloServidor hilo = new HiloServidor(cliente,NF);
				hilo.start();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println(e.getMessage());
				System.exit(-1);
			}
		}
	}
}
