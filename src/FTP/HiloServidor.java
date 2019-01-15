package FTP;
/**
 * 
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.tools.FileObject;

/**
 * @author dejua
 *
 */
public class HiloServidor extends Thread{

	Socket socket;
	ObjectOutputStream escribir;
	ObjectInputStream leer;
	EstructuraFicheros NF;
	
	public HiloServidor(Socket s,EstructuraFicheros NF) throws IOException {
		// TODO Auto-generated constructor stub
		this.socket=s;
		this.NF=NF;
		escribir = new ObjectOutputStream(socket.getOutputStream());
		leer = new ObjectInputStream(socket.getInputStream());
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			escribir.writeObject(NF);
			
			while(true) {
				Object peticion = leer.readObject();
				if(peticion instanceof PideFichero) {
					PideFichero fichero = (PideFichero) peticion;
					EnviarFichero(fichero);
				}
				if(peticion instanceof EnviarFichero) {
					EnviarFichero fic = (EnviarFichero)peticion;
					File d = new File(fic.getDirectorio());
					File f1 = new File(d,fic.getNombre());
					
					FileOutputStream fos = new FileOutputStream(f1);
					fos.write(fic.getContenidoFichero());
					fos.close();
					EstructuraFicheros ef = new EstructuraFicheros(fic.getDirectorio());
					escribir.writeObject(ef);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			try {
				escribir.close();
				leer.close();
				socket.close();
				System.out.println("CLIENTE CERRADO");
			}catch (Exception ex) {
				// TODO: handle exception
			}
		}
		
	}
	private void EnviarFichero(PideFichero fich) {
		
		File fichero = new File(fich.getNombreFichero());
		FileInputStream leer = null;
		
		try {
			
			leer = new FileInputStream(fichero);
			long bytes = fichero.length();
			byte[] buff = new byte [(int)bytes];
			int i, j = 0;
			
			while((i = leer.read()) != -1) {
				buff[j] = (byte)i;
				j++;
			}
			
			leer.close();
			Object ff = new ObtenerFichero(buff);
			escribir.writeObject(ff);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
