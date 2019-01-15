package FTP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 * 
 * @author jesus
 * @see VistaCliente
 * @see MetodosCliente
 * 
 */
public class ControladorCliente implements Runnable, ListSelectionListener, ActionListener {
	/**
	 * Atributos
	 */
	private VistaCliente vista;
	private MetodosCliente metodos;
	private EstructuraFicheros raiz;
	private EstructuraFicheros nodo = null;
	private EstructuraFicheros[] nodos;
	private ObjectInputStream leer;
	private ObjectOutputStream escribir;
	private String direcSelec = "";
	private String ficheroSelec = "";
	private String ficherocompleto = "";
	private Socket socket;

	/**
	 * Constructor
	 * 
	 * @param s <Socket>
	 */
	public ControladorCliente(Socket s) {
		this.metodos = new MetodosCliente();
		/* instanciamos una nueva vista */
		this.vista = new VistaCliente();
		this.vista.setBounds(0, 0, 600, 600);
		this.vista.setVisible(true);
		this.vista.setResizable(false);

		socket = s;
		try {
			escribir = new ObjectOutputStream(socket.getOutputStream());
			leer = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		/*
		 * añadimos los listener al panel de visualización de los ficheros, y a los
		 * botones
		 */
		this.vista.getListaDirec().addListSelectionListener(this);
		this.vista.getBotonCargar().addActionListener(this);
		this.vista.getBotonDescargar().addActionListener(this);
		this.vista.getBotonSalir().addActionListener(this);
	}

	/**
	 * Run
	 */
	@Override
	public void run() {
		try {
			/* obtenemos los ficheros de la ruta por defecto */
			this.raiz = (EstructuraFicheros) this.leer.readObject();
			this.nodos = this.raiz.getLista();
			this.direcSelec = this.raiz.getRuta();

			/*
			 * actualizamos el panel de visualización de ficheros con la estructura obtenida
			 * anteriormente
			 */
			this.metodos.llenarLista(this.nodos, this.raiz.getnFich(), this.vista.getListaDirec());

			/* añadimos texto a los mensajes */
			this.vista.getCabecera2().setText("RAIZ:  " + this.direcSelec);
			this.vista.getCabecera1().setText("CONECTANDO AL SERVIDOR DE FICHEROS");
			this.vista.getCampo2().setText("Número de ficheros en el directorio: " + this.raiz.getnFich());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	@Override
	/**
	 * Método para cuando seleccionamos un elemento en la lista de ficheros de la
	 * vista
	 */
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			this.ficheroSelec = "";
			this.ficherocompleto = "";
			/* obtenemos el fichero seleccionado */
			this.nodo = (EstructuraFicheros) this.vista.getListaDirec().getSelectedValue();
			/* obtenemos el nombre del fichero */
			this.ficheroSelec = this.nodo.getNombre();
			/* construimos la ruta conpleta del fichero */
			this.ficherocompleto = this.nodo.getRuta();
			this.vista.getCampo().setText("FICHERO SELECCIONADO : " + this.ficheroSelec);
		}
	}

	@Override
	/* eventos para los botones salir, cargar y descargar */
	public void actionPerformed(ActionEvent bt) {
		Object btn = bt.getSource();
		/* cerramos la conexión cliente actual */
		if (btn == this.vista.getBotonSalir()) {
			this.metodos.botonSalir(this.socket);
			/* cargamos fichero */
		} else if (btn == this.vista.getBotonCargar()) {
			this.metodos.botonCargar(this.escribir, this.nodo, this.direcSelec, this.leer, this.vista.getCampo2(),
					this.vista.getListaDirec());
			/* descargamos fichero */
		} else if (btn == this.vista.getBotonDescargar()) {
			this.metodos.botonDescargar(this.ficherocompleto, this.escribir, this.leer, this.ficheroSelec);
		}
	}

	/**
	 * Main donde creamos un nuevo hilo de conexión con el servidor
	 * 
	 * @param args
	 * @throws UnknownHostException
	 * @throws IOException
	 */
/*	public static void main(String[] args) throws UnknownHostException, IOException {
		int puerto = 4444;
		Socket s = new Socket("localhost", puerto);
		ControladorCliente hiloC = new ControladorCliente(s);
		new Thread(hiloC).start();
	}*/
}
