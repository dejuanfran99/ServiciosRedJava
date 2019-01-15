/**
 * 
 */
package SMTP_POP3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * @author dejua
 *
 */
public class VistaLoginPOP3 extends JFrame implements ActionListener{

	private ArrayList<JLabel> etiquetas;
	private ArrayList<JTextField> cajas;
	private JPasswordField pass;
	private ArrayList<String>contenido;
	private ArrayList<JPanel>paneles;
	private JButton boton;
	private String titulo;
	
	public VistaLoginPOP3() {
		// TODO Auto-generated constructor stub
		etiquetas = new ArrayList<>();
		cajas = new ArrayList<>();
		contenido = new ArrayList<>();
		paneles = new ArrayList<>();
		boton = new JButton("Entrar");
		pass = new JPasswordField(20);
		titulo = "Login";
		BoxLayout ventana = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		setLayout(ventana);
		
		boton.addActionListener(this);
		initContenido();
		initEtiquetasCajas();
		propiedadesVentana();

	}
	
	public void initContenido() {
		contenido.add("Correo Electrónico");
		contenido.add("Contraseña");
	}
	
	public void initEtiquetasCajas() {
		for (int i = 0; i < 2; i++) {
			paneles.add(new JPanel());
			etiquetas.add(new JLabel(contenido.get(i)));
			paneles.get(i).add(etiquetas.get(i));
			if(i==1) {
				paneles.get(i).add(pass);
			}else {
				cajas.add(new JTextField(20));
				paneles.get(i).add(cajas.get(i));
			}										
			this.add(paneles.get(i));
		}
		paneles.add(new JPanel());
		paneles.get(2).add(boton);
		this.add(paneles.get(2));
	}
	
	public void propiedadesVentana() {
		setTitle(titulo);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300, 300);
		setResizable(false);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		VistaPOP3 v = new VistaPOP3();
		POP3ClientJavaxMail pop3 = new POP3ClientJavaxMail(v,getCajas().get(0).getText(),getPass().getText());
	}
	
	
	
	/**
	 * @return the etiquetas
	 */
	public ArrayList<JLabel> getEtiquetas() {
		return etiquetas;
	}

	/**
	 * @param etiquetas the etiquetas to set
	 */
	public void setEtiquetas(ArrayList<JLabel> etiquetas) {
		this.etiquetas = etiquetas;
	}

	/**
	 * @return the cajas
	 */
	public ArrayList<JTextField> getCajas() {
		return cajas;
	}

	/**
	 * @param cajas the cajas to set
	 */
	public void setCajas(ArrayList<JTextField> cajas) {
		this.cajas = cajas;
	}

	/**
	 * @return the pass
	 */
	public JPasswordField getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(JPasswordField pass) {
		this.pass = pass;
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
	 * @return the boton
	 */
	public JButton getBoton() {
		return boton;
	}

	/**
	 * @param boton the boton to set
	 */
	public void setBoton(JButton boton) {
		this.boton = boton;
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

}
