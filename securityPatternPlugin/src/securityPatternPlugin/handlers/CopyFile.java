package securityPatternPlugin.handlers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CopyFile {

	/**
	 * @param args
	 */
	
	/* 
	before execution: "SourceFile" is initialized and is the name of an external existing file which is  closed,  
	"NewDestFile" is initialized and is the name of an external file that does not exist (if it already exists the contents of the previous file will be overwritten)
	after execution: "SourceFile" is unchanged. "NewDestFile" has the content of "SourceFile" and is closed 
	*/ 
		public void copy (String SourceFile, String NewDestFile) throws IOException
				{ 
			
		/*"inputfile" is initialized and linked to the external file "SourceFile". it is opened in read*/
		File nomFichier = new File(SourceFile); 
		Scanner inputFile = new Scanner(nomFichier); 

		/* Opening the file "NewDestFile"*/
		PrintWriter outputFile = new PrintWriter(NewDestFile); 

		/*write in the file "NewDestFile", the content of the SourceFile*/
		while (inputFile.hasNext())//see if the following line exists
		{ 
		outputFile.println(inputFile.nextLine());//copy the ligne from "SourceFile" to "NewDestFile" 
		} 

		outputFile.close(); //Close outputFile
		inputFile.close();//Close  inputFile
		} 

}


