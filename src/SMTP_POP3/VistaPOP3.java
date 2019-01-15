/**
 * 
 */
package SMTP_POP3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Clase vista de POP3
 * 
 * @author Juan Francisco Cabello, Mario Gonzalez, Jesús Romero
 * @version 1.0
 *
 */
public class VistaPOP3 extends JFrame {
	/**
	 * Array de asuntos
	 */
	private ArrayList<JLabel> asuntos;
	/**
	 * Array de origen
	 */
	private ArrayList<JLabel> origen;
	/**
	 * Array de botones
	 */
	private ArrayList<JButton> botones;
	/**
	 * Array de paneles
	 */
	private ArrayList<JPanel> paneles;
	/**
	 * Titulo
	 */
	private String titulo;
	
	/**
	 * panel con Scroll
	 */
	private JScrollPane panelScroll;

	/**
	 * Constructor de la vista
	 */
	
	public VistaPOP3() {
		asuntos = new ArrayList<>();
		origen = new ArrayList<>();
		botones = new ArrayList<>();
		paneles = new ArrayList<>();
		panelScroll = new JScrollPane();
		titulo = "Bandeja de Entrada";
		
		BoxLayout ventana = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		setLayout(ventana);
	}

	/**
	 * Crea el panel de login
	 * 
	 * @param numeroMensajes
	 * @param correos
	 */
	public void initPaneles(int numeroMensajes, ArrayList<Correo> correos) {
		JPanel principal = new JPanel();
		principal.setLayout(new BoxLayout(principal, BoxLayout.Y_AXIS));
		for (int i = 0; i < numeroMensajes; i++) {

			paneles.add(new JPanel());
			asuntos.add(new JLabel(correos.get(i).getAsunto()));
			origen.add(new JLabel("De: " + correos.get(i).getDestinatario()));
			paneles.get(i).add(asuntos.get(i));
			paneles.get(i).add(origen.get(i));
			botones.add(new JButton("Mostrar"));
			botones.get(i).setName(""+i);
			paneles.get(i).add(botones.get(i));
			paneles.get(i).setLayout(new GridLayout(0, 3));
			//panelScroll.add(paneles.get(i));
			principal.add(paneles.get(i));
			
		}

		panelScroll.getViewport().add(principal);
		add(panelScroll);
	}

	/**
	 * Asigna las propiedades de la ventana
	 */
	public void propiedadesVentana() {
		setTitle(titulo);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(760, 800);
		setVisible(true);
	}

	/**
	 * @return the asuntos
	 */
	public ArrayList<JLabel> getAsuntos() {
		return asuntos;
	}

	/**
	 * @param asuntos the asuntos to set
	 */
	public void setAsuntos(ArrayList<JLabel> asuntos) {
		this.asuntos = asuntos;
	}

	/**
	 * @return the origen
	 */
	public ArrayList<JLabel> getOrigen() {
		return origen;
	}

	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(ArrayList<JLabel> origen) {
		this.origen = origen;
	}

	/**
	 * @return the botones
	 */
	public ArrayList<JButton> getBotones() {
		return botones;
	}

	/**
	 * @param botones the botones to set
	 */
	public void setBotones(ArrayList<JButton> botones) {
		this.botones = botones;
	}

	/**
	 * @return the paneles
	 */
	public ArrayList<JPanel> getPaneles() {
		return paneles;
	}

	/**
	 * @param paneles the paneles to set
	 */
	public void setPaneles(ArrayList<JPanel> paneles) {
		this.paneles = paneles;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the panelScroll
	 */
	public JScrollPane getPanelScroll() {
		return panelScroll;
	}

	/**
	 * @param panelScroll the panelScroll to set
	 */
	public void setPanelScroll(JScrollPane panelScroll) {
		this.panelScroll = panelScroll;
	}
	
	

}
