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
import javax.swing.JTextField;

/**
 * @author dejua
 *
 */
public class VistaLogin extends JFrame implements ActionListener{

	private ArrayList<JLabel> etiquetas;
	private ArrayList<JTextField> cajas;
	private JPasswordField pass;
	private ArrayList<String>contenido;
	private ArrayList<JPanel>paneles;
	private JButton boton;
	private String titulo;
	
	public VistaLogin() {
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

	public ArrayList<JLabel> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(ArrayList<JLabel> etiquetas) {
		this.etiquetas = etiquetas;
	}

	public ArrayList<JTextField> getCajas() {
		return cajas;
	}

	public void setCajas(ArrayList<JTextField> cajas) {
		this.cajas = cajas;
	}

	public JPasswordField getPass() {
		return pass;
	}

	public void setPass(JPasswordField pass) {
		this.pass = pass;
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

	public JButton getBoton() {
		return boton;
	}

	public void setBoton(JButton boton) {
		this.boton = boton;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		VistaSMTP v= new VistaSMTP();
		v.propiedadesVentana();
		v.getCaja().get(0).setText(getCajas().get(0).getText());
		v.getCaja().get(0).setEnabled(false);
		v.getBoton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Correo c= new Correo(v.getCaja().get(2).getText(),getCajas().get(0).getText(),getPass().getText(),v.getCaja().get(1).getText().toString(),v.getMensaje().getText());
				SMTPClientJavaxMail smtp = new SMTPClientJavaxMail(v,c);
			}
		});
	}
	
	
}
