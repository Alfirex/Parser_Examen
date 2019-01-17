import java.util.ArrayList;

public class Accion {
	public String nombre;
	ArrayList<Compras> compras = null;
	

	
	public Accion(String nombre, ArrayList<Compras> compras) {
		super();
		this.nombre = nombre;
		this.compras = compras;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public ArrayList<Compras> getCompras() {
		return compras;
	}

	public void setCompras(ArrayList<Compras> compras) {
		this.compras = compras;
	}
	
	@Override
	public String toString() {
		return "Accion [nombre=" + nombre + ", compras=" + mostrarArrayList(compras) + "]";
	}
	
	public StringBuilder mostrarArrayList(ArrayList<Compras> arrayCompras) {
		StringBuilder sb = new StringBuilder();
	    
	    for(Compras aut : arrayCompras) {
	    	sb.append(aut.toString());
	    }
		
		return sb;
	}
	

}
