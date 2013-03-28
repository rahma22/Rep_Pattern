package securityPatternPlugin.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReplaceString {
String S;

public ReplaceString(String s) {
	super();
	S = s;
}

public void MethodeRemplacer(String chaineAremplacer,String chaine)throws FileNotFoundException, IOException{
	File fichier=new File(S); 
	FileReader fileReader=new FileReader(fichier); 
	FileReader fileReader1=new FileReader(fichier); 
	BufferedReader bufferedReader1=new BufferedReader(fileReader1); 
	int nbLigne=0; 
	String string1=bufferedReader1.readLine(); 
	while(string1!=null){ 
	nbLigne++; 
	string1=bufferedReader1.readLine(); 
	} 
	bufferedReader1.close(); 
	fileReader1.close(); 
	String []str = new String[nbLigne]; 
	BufferedReader bufferedReader=new BufferedReader(fileReader); 
	String string=bufferedReader.readLine(); 
	int i=0; 
	while(string!=null){ 
	str[i]=string.replaceAll(chaineAremplacer,chaine); 
	i++; 
	string=bufferedReader.readLine(); 
	} 
	bufferedReader.close(); 
	fileReader.close(); 
	FileWriter fileWriter=new FileWriter(fichier); 
	BufferedWriter bufferedWriter=new BufferedWriter(fileWriter); 
	for(int z=0;z<str.length;z++){ 
	bufferedWriter.write(str[z]); 
	bufferedWriter.newLine(); 
	} 
	bufferedWriter.close(); 
}
}
