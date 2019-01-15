package FTP;
/**
 * 
 */


import java.io.File;
import java.io.Serializable;

/**
 * @author dejua
 *
 */
public class EstructuraFicheros implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String ruta;
	private boolean isDir;
	private int nFich;
	private EstructuraFicheros[] lista;
	
	public EstructuraFicheros(String nombre) {
		File file = new File(nombre);
		this.nombre = file.getName();
		this.ruta = file.getPath();
		this.isDir = file.isDirectory();
		this.lista = getListaFiles();
		
		if(isDir) {
			File[] ficheros = file.listFiles();
			this.nFich = 0;
			if(!(ficheros==null)) {
				this.nFich = ficheros.length;
			}
		}
	}

	
	public EstructuraFicheros[] getListaFiles() {
		EstructuraFicheros[] lista = null;
		File f = new File(this.ruta);
		File[] ficheros = f.listFiles();
		if(ficheros.length>0) {
			lista = new EstructuraFicheros[ficheros.length];
			
			for (int i = 0; i < ficheros.length; i++) {
				EstructuraFicheros elemento;
				String nombre = ficheros[i].getName();
				String ruta = ficheros[i].getPath();
				boolean isDir = ficheros[i].isDirectory();
				int num = 0;
				if(isDir) {
					File[] fic = ficheros[i].listFiles();
					if(!(fic ==null)) {
						num = fic.length;
					}
				}
				
				elemento = new EstructuraFicheros(nombre,ruta,isDir,num);
				lista[i] = elemento;
			}
		}
		return lista;
	}
	
	
	@Override
	public String toString() {
		return nombre;
	}

	public EstructuraFicheros(String nombre, String ruta, boolean isDir, int nFich) {
		super();
		this.nombre = nombre;
		this.ruta = ruta;
		this.isDir = isDir;
		this.nFich = nFich;
	}
	
	public String getNombre() {
		String nombreDirec = nombre;
		if(isDir) {
			int i = ruta.lastIndexOf(File.separator);
			nombreDirec = ruta.substring(i+1,ruta.length());
		}
		return nombreDirec;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public boolean isDir() {
		return isDir;
	}

	public void setDir(boolean isDir) {
		this.isDir = isDir;
	}

	public int getnFich() {
		return nFich;
	}

	public void setnFich(int nFich) {
		this.nFich = nFich;
	}

	public EstructuraFicheros[] getLista() {
		return lista;
	}

	public void setLista(EstructuraFicheros[] lista) {
		this.lista = lista;
	}
	
	
}
