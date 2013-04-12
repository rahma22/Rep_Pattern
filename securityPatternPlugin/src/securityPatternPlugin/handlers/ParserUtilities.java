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
		
/*
 * *Method return an ArrayList of Properties of the INModel
 */
public ArrayList<String> getComponent(String XMLFile) throws ParserConfigurationException, SAXException, IOException{
	
	//Initialize the ArrayList
	ArrayList<String> propertyListe = new ArrayList<String>();
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    
    //Create the document to parse 
    Document document = db.parse(new File(XMLFile));
    
    //Get All node called "ownedAttribute"
    NodeList nodeList = document.getElementsByTagName("ownedAttribute");
    
    //Add properties (properties are ownedAttribute which parent node has xmi:type="uml:Class") to the ArrayList "propertyListe"
    for(int x=0,size= nodeList.getLength(); x<size; x++) {
    	if(nodeList.item(x).getParentNode().getAttributes().getNamedItem("xmi:type").getNodeValue().equals("uml:Class"))
    		propertyListe.add(nodeList.item(x).getAttributes().getNamedItem("name").getNodeValue());
    }
	return propertyListe;
}

/*
 * *Method return an ArrayList of applied profiles on the  INModel
 * this method exclude all standards profiles application 
 */
public ArrayList<String> getProfile(String XMLFile) throws ParserConfigurationException, SAXException, IOException{
	
	//Initialize the ArrayList
	ArrayList<String> profileListe = new ArrayList<String>();
	
	//Attribute for storing the id of the profile
	String idProfile = null;
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    
    //Create the document to parse (here the in model) 
    Document document = db.parse(new File(XMLFile));
    
    //Create the document to parse (here the profile)
    Document documentProfile = db.parse(new File("SecurityProfile.profile.uml"));
    
    //Get All node called "appliedProfile" in the in model 
    NodeList nodeListP = document.getElementsByTagName("appliedProfile"); 
    
    //Add profiles to the ArrayList profileListe
    for(int a=0,size= nodeListP.getLength(); a<size; a++){  	
    
    	//exclude Standard profile application	
      int pathmapPos = nodeListP.item(a).getAttributes().getNamedItem("href").getNodeValue().indexOf("pathmap");	
  	  if(pathmapPos < 0){
  	
  		  //Extract the id of every profile applied on the model in	  
  	  int DiezPos = nodeListP.item(a).getAttributes().getNamedItem("href").getNodeValue().indexOf("#");
  	  int EndPos=nodeListP.item(a).getAttributes().getNamedItem("href").getNodeValue().length();
  	  idProfile=nodeListP.item(a).getAttributes().getNamedItem("href").getNodeValue().substring(DiezPos+1,EndPos);
  	
  	  // Parse the XML file of the profile to get names of profiles (here profile is a pckagedElement with xmi:id=idProfile and xmi:type="uml:Profile")
  	  NodeList nodeListPro=documentProfile.getElementsByTagName("packagedElement");	
  	  for(int b=0; b<nodeListPro.getLength(); b++){
  		  if((nodeListPro.item(b).getAttributes().getNamedItem("xmi:id").getNodeValue().equals(idProfile))&&(nodeListPro.item(b).getAttributes().getNamedItem("xmi:type").getNodeValue().equals("uml:Profile")))
  		  profileListe.add(nodeListPro.item(b).getAttributes().getNamedItem("name").getNodeValue());
  			
  	  }
  	  
  	  }
    }
	return profileListe;	
	
}

}
