package FTP;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * 
 * @author jesus
 */
public class VistaCliente extends JFrame {
	private JList listaDirec = new JList();

	/* campos de cabecera parte superios */
	private JLabel cabecera1 = new JLabel();
	private JLabel cabecera2 = new JLabel();

	/* campos de mensajes parte inferior */
	private JLabel campo = new JLabel();
	private JLabel campo2 = new JLabel();

	/* botones */
	private JButton botonCargar = new JButton("Subir fichero");
	private JButton botonDescargar = new JButton("Descargar fichero");
	private JButton botonSalir = new JButton("Salir");

	/* contenedor */
	private final Container c = getContentPane();

	public VistaCliente() {
		super("SERVIDOR DE FICHEROS");
		/* restringimos la seleccion de elementos a 'solo ficheros' */
		listaDirec.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		/* añadimos una barra de desplazamiento */
		JScrollPane barraDesplazamiento = new JScrollPane(listaDirec);
		barraDesplazamiento.setPreferredSize(new Dimension(335, 420));
		barraDesplazamiento.setBounds(new Rectangle(5, 65, 335, 420));

		/* añadimos las cabeceras a la ventana */
		c.add(cabecera1);
		c.add(cabecera2);
		
		/* añadimos la barra de desplazamiento a la ventana */
		c.add(barraDesplazamiento);
		
		/* añadimos los campos de mensajes a la ventana */
		c.add(campo);
		c.add(campo2);

		/*añadimos los botones a la ventana*/
		c.add(botonCargar);
		c.add(botonDescargar);
		c.add(botonSalir);

		c.setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public JList getListaDirec() {
		return listaDirec;
	}

	public JLabel getCabecera1() {
		return cabecera1;
	}

	public JLabel getCabecera2() {
		return cabecera2;
	}

	public JLabel getCampo() {
		return campo;
	}

	public JLabel getCampo2() {
		return campo2;
	}

	public JButton getBotonCargar() {
		return botonCargar;
	}

	public JButton getBotonDescargar() {
		return botonDescargar;
	}

	public JButton getBotonSalir() {
		return botonSalir;
	}

	public Container getC() {
		return c;
	}
}
