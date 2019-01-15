/**
 * 
 */
package MainUnificado;

import java.io.IOException;

import FTP.Servidor;

/**
 * @author dejua
 *
 */
public class HiloUnificador extends Thread{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Servidor s = new Servidor();
		try {
			s.aplicacion();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
