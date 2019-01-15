/**
 * 
 */
package MainUnificado;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 *  @author Juan Francisco Cabello, Mario Gonzalez, Jesús Romero
 *  @version 1.0
 */
public class VistaMenu extends JFrame{

	/**
	 * ArrayList que contiene los botones
	 */
	ArrayList<JButton>botones;
	/**
	 * ArrayList que contiene el texto de los botones
	 */
	ArrayList<String>contenido;
	
	/**
	 * Menu
	 * 
	 * Constructor donde inicializo los atributos y 
	 * llamo a los metodos necesarios.
	 */
	public VistaMenu() {
		// TODO Auto-generated constructor stub
		
		botones = new ArrayList<>();
		contenido = new ArrayList<>();
		
		GridLayout Ventana = new GridLayout(4, 1);
		this.setLayout(Ventana);
		
		initContenido();
		initBotones();
		propiedadesVentana("Menu PEVAL");
	}
	
	/**
	 * initBotones
	 * 
	 * Lleno el arrayList de botones con botones 
	 * y le añado texto , color y fuente.
	 */
	public void initBotones() {
		for(int i=0;i<contenido.size();i++) {
			botones.add(new JButton(contenido.get(i)));
			botones.get(i).setBackground(new Color(59, 89, 182));
			botones.get(i).setFont(new Font("Tahoma", Font.BOLD, 40));
			botones.get(i).setBackground(new Color(59, 89, 182));
			botones.get(i).setFocusPainted(false);
			botones.get(i).setForeground(Color.WHITE);
			add(botones.get(i));
		}
	}
	
	/**
	 * initContenido
	 * 
	 * Lleno mi arrayList con el texto que van a llevar los botones
	 */
	public void initContenido() {
		contenido.add("Iniciar Servidor FTP");
		contenido.add("Iniciar Cliente FTP");
		contenido.add("Iniciar Cliente PoP3");
		contenido.add("Iniciar Cliente SMTP");
	}
	
	/**
	 * Añado a la ventana las propiedades necesarias
	 * @param titulo
	 */
	public void propiedadesVentana(String titulo) {
		setTitle(titulo);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 300);
		setResizable(false);
		setVisible(true);
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
	 * @return the contenido
	 */
	public ArrayList<String> getContenido() {
		return contenido;
	}

	/**
	 * @param contenido the contenido to set
	 */
	public void setContenido(ArrayList<String> contenido) {
		this.contenido = contenido;
	}
	
	
}
