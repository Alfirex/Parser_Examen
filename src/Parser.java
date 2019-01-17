
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class Parser {
	
	private Document dom = null;
	private ArrayList<Accion> aAccion = null;

	public Parser() {
		aAccion = new ArrayList<Accion>();
	}

	public void parseFicheroXml(String fichero) {
		// creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// creamos un documentbuilder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parseamos el XML y obtenemos una representación DOM
			dom = db.parse(fichero);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	public void parseDocument() {
		// obtenemos el elemento raíz
		Element docEle = dom.getDocumentElement();
		String textVal = null;
		// obtenemos el nodelist de elementos
		NodeList nl = docEle.getElementsByTagName("accion");
			
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {
				
				// obtenemos un elemento de la lista (Libro)
				Element el = (Element) nl.item(i);
				
		
		     
				    
				// obtenemos un objeto libro
				Accion a = getAccion(el);

				// lo añadimos al array
				aAccion.add(a);
				
			}
		}
		  
	}
	
	/**
	 * Esta funcion se encargara de Obtener todas las acciones
	 * @param libro
	 * @return
	 */
	 private Accion getAccion(Element libro){
		// Llamamos al método para sacar varios valores de ahí:
	    String editor = getTextValue(libro,"nombre");
	   
	    ArrayList<Compras> oCompra = getCompras(libro, "compras");
	    // Creamos un objeto libro con estos datos y lo devolvemos:
	    Accion oAccion = new Accion(editor, oCompra);
	    return oAccion; 
		 
		 }
	  
	  
	  /**
	   *  Valor dentro de la respectiva etiqueta. 
	   */
	  private String getTextValue(Element ele, String tagName) {
	    String textVal = null;
	    NodeList nodeList = ele.getElementsByTagName(tagName);
	    
	    // Si el nodo tiene nombre o contenido:
	    if(nodeList != null && nodeList.getLength() > 0) {
	      Element element = (Element)nodeList.item(0);
	      textVal = element.getFirstChild().getNodeValue();
	    }  
	    
	    return textVal;
	  }
	  
	
	  
	  /**
	   * Funcion  para obtener los campos de Compras
	   */
	  public ArrayList<Compras> getCompras(Element ele, String tagName){
			 ArrayList<Compras> alCompra = new ArrayList<>();
			 ArrayList<String> alCantidad = new ArrayList<>();
			 ArrayList<String> alPrecio = new ArrayList<>();
			 Compras oCompras;
			 String sCantidad, sPrecio;
			 
			 alCantidad = getCantidadElemento(ele, tagName);// coge el nombre de cada etiqueta y lo guarda en un ArrayList
			 alPrecio = getPrecioElemento(ele, tagName);// coge el apellidos de cada etiqueta y lo guarda en un ArrayList
			 
			 for(int i = 0; i < alCantidad.size(); i++) {//,size es el .length de array list
				 sCantidad = alCantidad.get(i);//obtiene el nombre de esa posicion
				 sPrecio = alPrecio.get(i);
				 
				 oCompras = new Compras(sCantidad, sPrecio);//Creamos el objeto autor y lo almacenamos
				
				 alCompra.add(oCompras);//  le pasamos el objeto Autor y que lo aguarde en el array List 
			 }
		
			 return alCompra;
		 }
	
	// Recoge Cantidad. 
	 private ArrayList<String> getCantidadElemento(Element ele, String tagName) {//tagName == <autores>
		
			NodeList nodeList = ele.getElementsByTagName(tagName);
			ArrayList<String> alPrecio = new ArrayList<>();
			String sCompra = "";// para que no sale null  
			
			if (nodeList != null && nodeList.getLength() > 0) {// Comprobar que no esta vacio
				Element el = (Element) nodeList.item(0);// pilla la primera etique de autores
				
				NodeList nlCantidad = el.getElementsByTagName("cantidad");// Vamos a por la etiqueta nombre
				
				if (nlCantidad != null && nlCantidad.getLength() > 0) {// si hay algo en la etiqueta nombre que recorra todo lo que hay aen la etiqueta
					for (int i=0; i < nlCantidad.getLength(); i++) {
							Element eNom = (Element) nlCantidad.item(i);
							
							sCompra = eNom.getFirstChild().getTextContent();//obtenemos su valor
							alPrecio.add(sCompra);// añadimos al array list
						}
				}
			}
			return alPrecio;			
	}
	 
	// Recoge Precio. 
		 private ArrayList<String> getPrecioElemento(Element ele, String tagName) {//tagName == <autores>
			
				NodeList nodeList = ele.getElementsByTagName(tagName);
				ArrayList<String> alCantidad = new ArrayList<>();
				String sCompra = "";// para que no sale null  
				
				if (nodeList != null && nodeList.getLength() > 0) {// Comprobar que no esta vacio
					Element el = (Element) nodeList.item(0);// pilla la primera etique de autores
					
					NodeList nlCantidad = el.getElementsByTagName("precio");// Vamos a por la etiqueta nombre
					
					if (nlCantidad != null && nlCantidad.getLength() > 0) {// si hay algo en la etiqueta nombre que recorra todo lo que hay aen la etiqueta
						for (int i=0; i < nlCantidad.getLength(); i++) {
								Element eNom = (Element) nlCantidad.item(i);
								
								sCompra = eNom.getFirstChild().getTextContent();//obtenemos su valor
								alCantidad.add(sCompra);// añadimos al array list
							}
					}
				}
				return alCantidad;			
		}
	  
	
	  /**
	   * Print de libros
	   */
	  public void printLibro() {
		Iterator<Accion> it = aAccion.iterator();
		StringBuilder sb = new StringBuilder();
		
		while(it.hasNext()) {
			Accion a = it.next();
		 // Llama al método toString() de libro para sacar el formato deseado:
	     sb.append(a.toString() + "\n\n");
	    }
		System.out.println(sb);;	
	  }


}