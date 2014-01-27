import java.io.File;
import javax.xml.transform.*;
import java.util.LinkedList;
import javax.xml.parsers.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;  //Document, Element, Node, NodeList


public class XMLFileManager{

  static String tagInput = "input";
  static String tagNumber = "number";
  static String tagResult = "result";
  String fileNameRead;
  Document newDocument;
  LinkedList<String> listOfNumbers;
  BigDecimalSum iBigDecimalSum;

  public XMLFileManager(String fileNameRead){
    this.fileNameRead = fileNameRead;
    listOfNumbers = new LinkedList<String>();
  }

  public void executeProgram(){
    readAllFile();
    getInputNode();
    iBigDecimalSum = new BigDecimalSum(listOfNumbers);
  }

	private void readAllFile(){
    try{
      File newFile = new File(fileNameRead);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document newDocument = dBuilder.parse(newFile);
      newDocument.getDocumentElement().normalize();
      this.newDocument = newDocument;
    }catch (Exception ex) { System.out.println("incorrect type file"); }
  }

	private void getInputNode(){
    NodeList nodes = newDocument.getElementsByTagName(tagInput);
    Node nodeTagInput = nodes.item(0);
    if (nodeTagInput.getNodeType() == Node.ELEMENT_NODE) {
      Element element = (Element) nodeTagInput;
      getEachNumber(tagNumber, element);
    }
  }

	private void getEachNumber(String tag, Element element) {
    try{
      for(int contador = 0; contador < 100 ;contador++){
        NodeList nodes = element.getElementsByTagName(tag)
                                .item(contador).getChildNodes();
        Node nodeTagNumber = (Node)nodes.item(0);
        listOfNumbers.add( nodeTagNumber.getNodeValue() );
      }
    }catch(Exception ex) {System.out.println("incorrect tags in the file");}
  }

  private void findChild(){
    Node resultTag = newDocument.getElementsByTagName( tagResult ).item(0);
    NodeList list = resultTag.getChildNodes();
    Node nodeNumerOfTagResult = list.item(1);
    if ( tagNumber.equals(nodeNumerOfTagResult.getNodeName() ) ) {
      nodeNumerOfTagResult.setTextContent( iBigDecimalSum.getBigDecimalNumber() );
    }
    writeInXMLFile();
  }

	private void writeInXMLFile(){
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(newDocument);
      StreamResult newFile = new StreamResult(new File(fileNameRead));
      transformer.transform(source, newFile);
    } catch (Exception ex){}
  }
}
