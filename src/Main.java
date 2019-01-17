
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parser parser=new Parser();
		parser.parseFicheroXml("fichero.xml");
		parser.parseDocument();
		parser.printLibro();
	}


}
