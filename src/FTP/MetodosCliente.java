/**
 * 
 */
package FTP;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 * @author dejua
 *
 */
public class MetodosCliente {

	public MetodosCliente() {
	}

	/**
	 * Método en el cual cerramos la conexión con el socket pasado por parámetro
	 * 
	 * @param socket <Socket>
	 */
	public void botonSalir(Socket socket) {
		try {
			socket.close();
			System.exit(0);
		} catch (Exception e2) {
		}
	}

	/**
	 * Método en el cual descargamos un fichero seleccionado en un directorio
	 * específico
	 * 
	 * @param ficherocompleto <String>
	 * @param escribir        <ObjectOutputStream>
	 * @param leer            <ObjectInputStream>
	 * @param ficheroSelec    <String>
	 */
	public void botonDescargar(String ficherocompleto, ObjectOutputStream escribir, ObjectInputStream leer,
			String ficheroSelec) {

		/* nada si no seleccionamos nigún fichero */
		if (ficherocompleto.equals("")) {
			return;
		}

		/*
		 * obtenemos el nombre del fichero seleccionado a partir de la ruta + nombre del
		 * fichero
		 */
		PideFichero pido = new PideFichero(ficherocompleto);
		try {
			/* creamos un flujo de escritura en la ruta por defecto */
			escribir.writeObject(pido);
			FileOutputStream fos = new FileOutputStream(carpetaDestino() + "\\" + ficheroSelec);
			Object obtengo = leer.readObject();

			if (obtengo instanceof ObtenerFichero) {
				/*
				 * obtenemos el contenido del fichero y lo escribimos en el nuevo creado en la
				 * ruta por defecto
				 */
				ObtenerFichero fic = (ObtenerFichero) obtengo;
				fos.write(fic.getContenidoFichero());
				fos.close();
				JOptionPane.showMessageDialog(null, "FICHERO DESCARGADO");
			}
		} catch (Exception e2) {
			System.exit(0);
		}
	}

	/**
	 * Método en el cual cargamos un fichero en la ruta por defecto
	 * 
	 * @param escribir   <ObjectOutputStream>
	 * @param nodo       <EstructuraFicheros>
	 * @param direcSelec <String>
	 * @param leer       <ObjectInputStream>
	 * @param campo2     <JLabel>
	 * @param listaDirec <JList>
	 */
	public void botonCargar(ObjectOutputStream escribir, EstructuraFicheros nodo, String direcSelec,
			ObjectInputStream leer, JLabel campo2, JList listaDirec) {
		/* abrimos una nueva ventan FileChooser */
		JFileChooser f;
		File file;
		f = new JFileChooser();
		/* añadimos la restricción de 'solo ficheros' */
		f.setFileSelectionMode(JFileChooser.FILES_ONLY);
		f.setDialogTitle("Selecciona el Fichero a SUBIR AL SERVIDOR DE FICHEROS");
		int returnVal = f.showDialog(f, "Cargar");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			/* obtenemos el fichero seleccionado */
			file = f.getSelectedFile();
			/* obtenemos el nombre del fichero seleccionado y su ruta */
			String archivo = file.getAbsolutePath();
			String nombreArchivo = file.getName();

			/* generamos el array con el contenido del fichero */
			BufferedInputStream in;
			try {
				in = new BufferedInputStream(new FileInputStream(archivo));
				long bytes = file.length();
				byte[] buff = new byte[(int) bytes];
				int i, j = 0;
				while ((i = in.read()) != -1) {
					buff[j] = (byte) i;
					j++;
				}
				in.close();
				/* enviamos al servidor un objeto con el nuevo fichero a cargar */
				Object ff = new EnviarFichero(buff, nombreArchivo, direcSelec);
				escribir.writeObject(ff);
				JOptionPane.showMessageDialog(null, "FICHERO CARGADO");

				/* obtenemos los ficheros de la ruta por defecto */
				nodo = (EstructuraFicheros) leer.readObject();
				EstructuraFicheros[] lista = nodo.getLista();
				direcSelec = nodo.getRuta();

				/* actualizamos el panel de visualización de ficheros */
				llenarLista(lista, nodo.getnFich(), listaDirec);
				campo2.setText("Numero de ficheros en el directorio: " + lista.length);

			} catch (Exception e2) {
				// TODO: handle exception
				System.exit(0);
			}
		}
	}

	/**
	 * Método en el cual actualizamos el panel de visualización de ficheros
	 * 
	 * @param files      <EstructuraFicheros>
	 * @param numero     <int>
	 * @param listaDirec <JList>
	 */
	void llenarLista(EstructuraFicheros[] files, int numero, JList listaDirec) {
		if (numero == 0) {
			return;
		}
		/* creamos un nuevo modelo para la lista */
		DefaultListModel modeloLista = new DefaultListModel();
		modeloLista = new DefaultListModel();

		/* añadimos propiedades */
		listaDirec.setForeground(Color.BLUE);
		Font fuente = new Font("Arial", Font.PLAIN, 12);
		listaDirec.setFont(fuente);
		listaDirec.removeAll();

		/* añadimos elementos */
		for (int i = 0; i < files.length; i++) {
			if(!files[i].isDir()) {
				modeloLista.addElement(files[i]);
				try {
					listaDirec.setModel(modeloLista);
				} catch (Exception e) {
					System.out.println("Error del puntero , FIN DEL PROGRAMA");
					System.exit(0);
				}
			}		
		}
	}

	/*
	 * Método que devuelve un String con la carpeta donde se van a descargar el
	 * fichero
	 */
	public String carpetaDestino() {
		File file = null;
		JFileChooser f = new JFileChooser();
		/* añadimos restrición de 'solo directorios' */
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		f.setDialogTitle("Selecciona el Directorio donde DESCARGAR el fichero");

		int returnVal = f.showDialog(null, "Descargar");
		/* obtenemos el fichero seleccionado */
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = f.getSelectedFile();
		}

		/* devolvemos la ruta del fichero seleccionado */
		return file.getPath().toString();
	}
}
