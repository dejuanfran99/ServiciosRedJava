/**
 * 
 */
package SMTP_POP3;

import java.awt.Color;
import java.awt.Container;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author dejua
 *
 */
public class VistaSMTP extends JFrame{

	private ArrayList <JLabel> etiquetas;
	private ArrayList<String> contenido;
	private ArrayList<JPanel> paneles;
	private ArrayList<JTextField> caja;
	private JTextArea mensaje;
	private JScrollPane panelMensaje;
	private String titulo;
	private JButton boton;
	private BoxLayout ventana;
	
	public VistaSMTP() {
		// TODO Auto-generated constructor stub
		//GridLayout ventana = new GridLayout(5, 1);
		ventana = new BoxLayout(getContentPane(),BoxLayout.Y_AXIS);
		setLayout(ventana);
		etiquetas = new ArrayList<>();
		contenido = new ArrayList<>();
		paneles = new ArrayList<>();
		caja = new ArrayList<>();
		mensaje = new JTextArea(20,20);
		titulo = "Enviar Nuevo Mensaje";
		boton = new JButton("Enviar");
				
		initContenido();
		creaEtiquetasCaja();
		initBoton();
	}
	
	public ArrayList<JLabel> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(ArrayList<JLabel> etiquetas) {
		this.etiquetas = etiquetas;
	}

	public ArrayList<String> getContenido() {
		return contenido;
	}

	public void setContenido(ArrayList<String> contenido) {
		this.contenido = contenido;
	}

	public ArrayList<JPanel> getPaneles() {
		return paneles;
	}

	public void setPaneles(ArrayList<JPanel> paneles) {
		this.paneles = paneles;
	}

	public ArrayList<JTextField> getCaja() {
		return caja;
	}

	public void setCaja(ArrayList<JTextField> caja) {
		this.caja = caja;
	}

	public JTextArea getMensaje() {
		return mensaje;
	}

	public void setMensaje(JTextArea mensaje) {
		this.mensaje = mensaje;
	}

	public JScrollPane getPanelMensaje() {
		return panelMensaje;
	}

	public void setPanelMensaje(JScrollPane panelMensaje) {
		this.panelMensaje = panelMensaje;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public JButton getBoton() {
		return boton;
	}

	public void setBoton(JButton boton) {
		this.boton = boton;
	}

	public BoxLayout getVentana() {
		return ventana;
	}

	public void setVentana(BoxLayout ventana) {
		this.ventana = ventana;
	}

	public void initContenido() {
		contenido.add("De :");
		contenido.add("Para :");
		contenido.add("Asunto :");
		contenido.add("Mensaje :");
	}
	
	public void initBoton() {
		paneles.add(new JPanel());
		paneles.get(paneles.size()-1).add(boton);
		paneles.get(paneles.size()-1).setBackground(Color.gray);
		this.add(paneles.get(paneles.size()-1));
	}
	
	public void creaEtiquetasCaja() {
		for (int i = 0; i < contenido.size(); i++) {
			etiquetas.add(new JLabel(contenido.get(i)));
			paneles.add(new JPanel());
			paneles.get(i).setBackground(Color.gray);
			paneles.get(i).add(etiquetas.get(i));
			if(i!=contenido.size()-1) {
				caja.add(new JTextField(35));
				paneles.get(i).add(caja.get(i));
			}else {
				panelMensaje = new JScrollPane(mensaje);
				paneles.get(i).setLayout(new BoxLayout(paneles.get(i), BoxLayout.Y_AXIS));
				paneles.get(i).add(panelMensaje);
			}
			this.add(paneles.get(i));
		}
	}
	
	public void propiedadesVentana() {
		setTitle(titulo);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(500, 700);
		setVisible(true);
	}
}
