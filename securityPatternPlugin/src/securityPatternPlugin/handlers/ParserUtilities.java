package securityPatternPlugin.handlers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParserUtilities {
		

public ArrayList<String> getComponent(String XMLFile) throws ParserConfigurationException, SAXException, IOException{
	ArrayList<String> componentListe = new ArrayList<String>();
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document document = db.parse(new File(XMLFile));
    NodeList nodeList = document.getElementsByTagName("packagedElement");
    for(int x=0,size= nodeList.getLength(); x<size; x++) {
    	if(nodeList.item(x).getAttributes().getNamedItem("xmi:type").getNodeValue().equals("uml:Component"))
    		componentListe.add(nodeList.item(x).getAttributes().getNamedItem("name").getNodeValue());
       // System.out.println(nodeList.item(x).getAttributes().getNamedItem("name").getNodeValue());
    }
	return componentListe;
}

}
