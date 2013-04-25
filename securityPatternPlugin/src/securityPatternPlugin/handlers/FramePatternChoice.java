package securityPatternPlugin.handlers;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.m2m.atl.common.ATLExecutionException;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;
import org.xml.sax.SAXException;

public class FramePatternChoice extends JFrame {

	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	int nombreStereotype=1;
	String componentStereotypeApplicationCommande="";
	String profileApplicationCommande="";
	String portStereotypeApplicationCommande="";
	boolean trouve=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePatternChoice frame = new FramePatternChoice();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FramePatternChoice() {
		/*frame parameters*/
		setTitle("Pattern Application");
		setBounds(100, 100, 495, 326); //frame bounds
		getContentPane().setLayout(null);// frame layout
		
		/*setting the parameters of the indication label */
		JLabel lblVeuillezChoisirLes = new JLabel("...");
		if(FrameBegin.getProfile().equals("RBAC_Profile"))lblVeuillezChoisirLes.setText("Enter User and ProtectionObject stereotypes for RBAC pattern application: ");
		if(FrameBegin.getProfile().equals("Authenticator_Profile"))lblVeuillezChoisirLes.setText("Enter User stereotypes for Authenticator pattern application: ");
		if(FrameBegin.getProfile().equals("CheckPoint_Profile"))lblVeuillezChoisirLes.setText("Enter ProtectedSystem stereotypes for CheckPoint pattern application: ");
		lblVeuillezChoisirLes.setBounds(10, 11, 459, 14);
		getContentPane().add(lblVeuillezChoisirLes);
		
		/*setting the parameters of the "Add Stereotype" button */
		JButton btnAjouterComposant = new JButton("Add stereotype");
		btnAjouterComposant.setIcon(new ImageIcon("icons/add.png"));
		btnAjouterComposant.setBounds(251, 191, 147, 23);
		getContentPane().add(btnAjouterComposant);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 52, 357, 128);
		getContentPane().add(scrollPane);
		
		/*setting the parameters of the table   */
		table = new JTable();
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(table);
		table.setBackground(Color.LIGHT_GRAY);
		
		
		
			final DefaultTableModel model=new DefaultTableModel(new Object[][] {{"None", "None"},},
					new String[] {"Component", "Stereotype"});
			table.setModel(model);
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setMinWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setMinWidth(25);

		/*setting the parameters of the "Apply Pattern" button */
		JButton btnAppliquerPatron = new JButton("Apply Pattern");
		btnAppliquerPatron.setBounds(336, 254, 133, 23);
		getContentPane().add(btnAppliquerPatron);
		
		JButton btnNewButton = new JButton("< Back");
		
		btnNewButton.setBounds(194, 254, 133, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete stereotype");
		btnNewButton_1.setIcon(new ImageIcon("icons/delete.png"));
		
		btnNewButton_1.setBounds(78, 191, 147, 23);
		getContentPane().add(btnNewButton_1);
//		TableColumn CompColumn = table.getColumnModel().getColumn(0);
		TableColumn CompColumn = table.getColumnModel().getColumn(0);//get column of components
		JComboBox comboComp = new JComboBox();// creating comboBox for components
/*
 * Fill ComboBox by Components Of the IN Model
 */
		ParserUtilities P=new ParserUtilities();
		try {
			for(int i=0;i<P.getComponent(FrameBegin.getModelinPath()).size();i++)
			comboComp.addItem(P.getComponent(FrameBegin.getModelinPath()).get(i));
			//Essai
			for(int j=0;j<P.getProfile(FrameBegin.getModelinPath()).size();j++)
				System.out.println(P.getProfile(FrameBegin.getModelinPath()).get(j));
			//End Essai
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
/*
 * End Filling ComboBox	
 */
		CompColumn.setCellEditor(new DefaultCellEditor(comboComp));//Affect the ComboBox comboComp to the first column "CompColumn" of the table

//		TableColumn SterColumn = table.getColumnModel().getColumn(1);
		TableColumn SterColumn = table.getColumnModel().getColumn(1);//get column of stereotypes
		JComboBox comboSter = new JComboBox();// creating comboBox for stereotypes
/*
 * Fill ComboBox by Stereotypes
 */
		
		//For RBAC Pattern
		if(FrameBegin.getProfile().equals("RBAC_Profile"))
			{
			comboSter.addItem("User");
			comboSter.addItem("ProtectionObject");
			}
		
		//For Authenticator Pattern
		if(FrameBegin.getProfile().equals("Authenticator_Profile"))
			{
			comboSter.addItem("User");
			}
		
		//For CheckPoint Pattern
				if(FrameBegin.getProfile().equals("CheckPoint_Profile"))
					{
					comboSter.addItem("ProtectedSystem");
					}
/*
 * End Filling ComboBox	
 */
		SterColumn.setCellEditor(new DefaultCellEditor(comboSter));//Affect the ComboBox comboSter to the second column "SterColumn" of the table
		
/*
 * *actionPerformed of "Add Stereotype" button
 */
		btnAjouterComposant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Add a row in the table
				nombreStereotype=nombreStereotype+1;
				model.addRow(new String [] {"None","None"});
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Delete a row from the table
			if(FrameBegin.getProfile().equals("RBAC_Profile")){	
				if(model.getRowCount()>2){
					model.removeRow(model.getRowCount()-1);
					nombreStereotype=nombreStereotype-1;}
					else JOptionPane.showMessageDialog(null,"you can not delete this row");}
			
			if((FrameBegin.getProfile().equals("Authenticator_Profile"))||(FrameBegin.getProfile().equals("CheckPoint_Profile"))){	
				if(model.getRowCount()>1){
					model.removeRow(model.getRowCount()-1);
					nombreStereotype=nombreStereotype-1;}
					else JOptionPane.showMessageDialog(null,"you can not delete this row");}
			}	
		});
		
/*
 * * actionPerformed of "< Back" button
 */
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				hide();
				new FrameBegin().show();
			}
		});
		
/*
 * *actionPerformed of "Apply Patron" button
 */
		btnAppliquerPatron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Verification of table entries
				String errorMessage="";
				for(int i=0;i<table.getRowCount();i++){
					for(int j=0;j<table.getColumnCount();j++){
						if(table.getValueAt(i, j).equals("None")){
						trouve=true;	
						errorMessage=errorMessage+"Fill cell at Row "+(i+1)+"and Column "+(j+1)+"\n";
						}
						
					}
				}
				if(trouve)
				{JOptionPane.showMessageDialog(null, errorMessage);
				errorMessage="";}
				//End Verification of table Entries
				//Applying Transformation if entries are correct
				else{
/*
 * *construct the command (applyProfile)to insert into the transformation
 */

		profileApplicationCommande="t.applyProfile(UML2!Package.allInstances() -> select(s | s.name ='"+FrameBegin.getProfile()+"') -> first());\n" +
				"thisModule.entityProfile<-UML2!Package.allInstances() -> select(s | s.name = '"+FrameBegin.getProfile()+"') -> first();";
/*
 * *construct the command (applyStereotype)to insert into the transformation
 */
		//For Authenticator Pattern
		if(FrameBegin.getProfile().equals("Authenticator_Profile")){
			for(int i=0;i<nombreStereotype;i++){
					componentStereotypeApplicationCommande=componentStereotypeApplicationCommande+"if(s.name = '"+table.getValueAt(i, 0)+"')\n" +
					"{t.applyStereotype(thisModule.getStereotype(thisModule.entityProfile,'Authenticator."+table.getValueAt(i, 1)+"'));}\n";
				}
		}
		
		//For RBAC Pattern
		if(FrameBegin.getProfile().equals("RBAC_Profile")){
			for(int i=0;i<nombreStereotype;i++){
				componentStereotypeApplicationCommande=componentStereotypeApplicationCommande+"if((s.name = '"+table.getValueAt(i, 0)+"')and(not s.hasStereotype('Authenticator.User')))\n" +
					"{t.applyStereotype(thisModule.getStereotype(thisModule.entityProfile,'RBAC."+table.getValueAt(i, 1)+"'));}\n";
				}
		}
		
		//For CheckPoint Pattern
		if(FrameBegin.getProfile().equals("CheckPoint_Profile")){
			// Stereotype Application to components
			for(int i=0;i<nombreStereotype;i++){
				componentStereotypeApplicationCommande=componentStereotypeApplicationCommande+"if((s.name = '"+table.getValueAt(i, 0)+"')and(s.hasStereotype('SingleAccessPoint.ProtectedSystem')))\n" +
					"{t.applyStereotype(thisModule.getStereotype(thisModule.entityProfile,'CheckPoint."+table.getValueAt(i, 1)+"'));}\n";
				}
			//Stereotype Application to ports
			 portStereotypeApplicationCommande = portStereotypeApplicationCommande+"if(s.hasStereotype('SingleAccessPoint.SingleAccessPoint'))\n" +
					"{t.applyStereotype(thisModule.getStereotype(thisModule.entityProfile,'CheckPoint.CheckPoint'));}\n";
		}
		
/*
 * * Insert the command into the transformation
 */
				
/*create an ReplaceString Object with the transformation path as a parameter.
 * * The transformation file is located in an ATL Project to ensure updating the asm file associated to the atl file.
 */
		ReplaceString R=new ReplaceString("./src/securityPatternPlugin/handlers/InitialTransformation.atl");
		try {
		/*Replace the String "---REMPLACER_PROFILE---" by the String profileApplicationCommande (profileApplicationCommande is the ATL command which apply a profile to the input model) */
		R.MethodeRemplacer("---REPLACE_PROFILE---", profileApplicationCommande);
				
		/*Replace the String "---REPLACE_STEREO_COMPOSANT---" by the String componentStereotypeApplicationCommande (componentStereotypeApplicationCommande is the ATL command which apply stereotypes to components of input model) */	
		R.MethodeRemplacer("---REPLACE_STEREO_COMPOSANT---",componentStereotypeApplicationCommande);
		
		/*Replace the String "---REPLACE_STEREO_PORT---" by the String portStereotypeApplicationCommande (portStereotypeApplicationCommande is the ATL command which apply stereotypes to ports of input model) */	
		R.MethodeRemplacer("---REPLACE_STEREO_PORT---",portStereotypeApplicationCommande);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(5000);
		} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
				
			System.out.println("insertion terminée");

		/*loading necessary resources*/
			ResourceSet RESOURCE_SET = new ResourceSetImpl();
			UMLResourcesUtil.init(RESOURCE_SET);
			URI baseUri = 
					URI.createURI("jar:file:lib/org.eclipse.uml2.uml.resources_4.0.2.v20130114-0902.jar!/");
					URIConverter.URI_MAP.put(URI.createURI( UMLResource.LIBRARIES_PATHMAP ), 
					baseUri.appendSegment( "libraries" ).appendSegment( "" ));
					URIConverter.URI_MAP.put(URI.createURI( UMLResource.METAMODELS_PATHMAP 
					), baseUri.appendSegment( "metamodels" ).appendSegment( "" ));
					URIConverter.URI_MAP.put(URI.createURI( UMLResource.PROFILES_PATHMAP ), 
					baseUri.appendSegment( "profiles" ).appendSegment( "" ));	
				
							
					
		/*running the transformations*/		
				
			try {					
		/*create a new runner (object associated to the transformation class) */
				InitialTransformation runner = new InitialTransformation();
				System.out.println(FrameBegin.getModelinPath());
		/*load the inputs required in the transformation (the model & the profile) */
				runner.loadModels(FrameBegin.getModelinPath(), "SecurityProfile.profile.uml");
					
		/*Apply the transformation*/
				runner.doInitialTransformation(new NullProgressMonitor());
						
		/*save the generated output model*/
				runner.saveModels("MedicalManagementSystemout.uml");
				
/*******************************************************************************/									
				if(FrameBegin.getProfile().equals("RBAC_Profile")){	
					//transformations For RBAC Pattern
					//Second Transformation
		/*create a new runner (object associated to the transformation class) */
				RBACTransformation2 runner2 = new RBACTransformation2();
						
		/*load the inputs required in the transformation (the model & the profile) */
				runner2.loadModels("MedicalManagementSystemout.uml", "SecurityProfile.profile.uml");
						
		/*Apply the transformation*/
				runner2.doRBACTransformation2(new NullProgressMonitor());
						
		/*save the generated output model*/
				runner2.saveModels("MedicalManagementSystemout.uml");	
				
				//Third Transformation		
						
		/*create a new runner (object associated to the transformation class) */
				RBACTransformation3 runner3 = new RBACTransformation3();
								
		/*load the inputs required in the transformation (the model & the profile) */
				runner3.loadModels("MedicalManagementSystemout.uml", "SecurityProfile.profile.uml");
								
		/*Apply the transformation*/
				runner3.doRBACTransformation3(new NullProgressMonitor());
								
		/*save the generated output model*/
				runner3.saveModels("MedicalManagementSystemout.uml");			
				}	//End Transformation for RBAC Pattern
				
/*******************************************************************************/
/*******************************************************************************/				
				
				if(FrameBegin.getProfile().equals("Authenticator_Profile")){	
					//Transformation for Authenticator Pattern
					//Second Transformation
		/*create a new runner (object associated to the transformation class) */
					AuthenticatorTransformation2 runner2 = new AuthenticatorTransformation2();
						
		/*load the inputs required in the transformation (the model & the profile) */
				runner2.loadModels("MedicalManagementSystemout.uml", "SecurityProfile.profile.uml");
						
		/*Apply the transformation*/
				runner2.doAuthenticatorTransformation2(new NullProgressMonitor());
						
		/*save the generated output model*/
				runner2.saveModels("MedicalManagementSystemout.uml");	
				
				}//End Transformation for Authenticator Pattern
				
				
/*******************************************************************************/				
								
				} catch (ATLCoreException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ATLExecutionException e) {
					e.printStackTrace();
				}  
					//End Transformations
		
/*
 * Delete temporary files (RBACTransformation.atl and RBACTransformation.asm)
 */

//				File ATLFile = new File("./src/securityPatternPlugin/handlers/RBACTransformation.atl");
//				ATLFile.delete();
//				File ASMFile = new File("./src/securityPatternPlugin/handlers/RBACTransformation.asm");
//				ASMFile.delete();
				
				
				/*Indicate the end of the transformation*/
				JOptionPane.showMessageDialog(null,"Transformation terminée");
							
				
			}//End trouve==false
		}
		});
		
	}
}
