import java.io.File;
import javax.xml.transform.*;
import java.util.LinkedList;
import javax.xml.parsers.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;  //Document, Element, Node, NodeList

public class ReadXMLFile extends BigDecimalSum{

	DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;
	Document newDocument;
	LinkedList<String> listOfNumbers;

	public ReadXMLFile(){ 
		listOfNumbers = new LinkedList<>();
	}

	public void run(){
		readAllFile();
		getInputNode();
		writeInXMLFile();
		sumList(listOfNumbers);
		findChild();
	}

	private void readAllFile(){
		try{
			File newFile = new File("base.xml");
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			Document newDocument = dBuilder.parse(newFile);
			newDocument.getDocumentElement().normalize();
			this.newDocument = newDocument;
		}catch (Exception ex) {}
	}

	private void getInputNode(){
		NodeList nodes = newDocument.getElementsByTagName("input");
		Node node = nodes.item(0);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			getEachNumber("number", element);
		}
	}    

	private void getEachNumber(String tag, Element element) {
		try{
			for(int contador = 0; contador < 100 ;contador++){
				NodeList nodes = element.getElementsByTagName(tag)
										.item(contador).getChildNodes();
				Node node = (Node) nodes.item(0);
				listOfNumbers.add( node.getNodeValue() );
			}
		}catch(Exception ex) {}
	}

	private void findChild(){
		Node resultTag = newDocument.getElementsByTagName("result").item(0); //result
		NodeList list = resultTag.getChildNodes();
		Node node = list.item(1);
		if ( "number".equals(node.getNodeName() ) ) {
			node.setTextContent( getResult() );
		}
		writeInXMLFile();
	}

	private void writeInXMLFile(){
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(newDocument);
			StreamResult newFile = new StreamResult(new File("base.xml"));
			transformer.transform(source, newFile);
		} catch (Exception ex){}
	}

} 
