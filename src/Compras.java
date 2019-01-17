
public class Compras {
	
	private String cantidad, precio;

	
	public Compras(String cantidad, String precio) {
		super();
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Compras [cantidad=" + cantidad + ", precio=" + precio + "]";
	}

	
	
	

}