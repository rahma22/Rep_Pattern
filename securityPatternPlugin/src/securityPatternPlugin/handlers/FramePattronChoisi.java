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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.m2m.atl.common.ATLExecutionException;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;

import securityPatternPlugin.handlers.FrameDebut;

public class FramePattronChoisi extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	int nombreStereotype=2;
	String stereotypeApplicationCommande="";
	String profileApplicationCommande="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePattronChoisi frame = new FramePattronChoisi();
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
	public FramePattronChoisi() {
		/*frame parameters*/
		setTitle("Application du patron");//frame title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit the program when closing the frame
		setBounds(100, 100, 456, 326); //frame bounds
		getContentPane().setLayout(null);// frame layout
		
		/*setting the parameters of the indication label */
		JLabel lblVeuillezChoisirLes = new JLabel("Veuillez choisir les st\u00E9r\u00E9otypes d'entr\u00E9es pour le patron RBAC : ");
		lblVeuillezChoisirLes.setBounds(26, 54, 386, 14);
		getContentPane().add(lblVeuillezChoisirLes);
		
		/*setting the parameters of the "Ajouter stéréotype" button */
		JButton btnAjouterComposant = new JButton("Ajouter st\u00E9r\u00E9otype");
		btnAjouterComposant.setIcon(new ImageIcon("icons/ajout.png"));
		btnAjouterComposant.setBounds(140, 174, 147, 23);
		getContentPane().add(btnAjouterComposant);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 79, 283, 68);
		getContentPane().add(scrollPane);
		
		/*setting the parameters of the table   */
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBackground(Color.LIGHT_GRAY);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
			},
			new String[] {
				"Composant", "St\u00E9r\u00E9otype"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setMinWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setMinWidth(25);

		/*setting the parameters of the "Appliquer Patron" button */
		JButton btnAppliquerPatron = new JButton("Appliquer Patron ");
		btnAppliquerPatron.setBounds(297, 254, 133, 23);
		getContentPane().add(btnAppliquerPatron);
		
		JLabel lblNewLabel = new JLabel("");
		//lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ACER\\Desktop\\pfe\\Sp\u00E9cialPFE\\Notes\\travaux.png"));
		lblNewLabel.setBounds(305, 144, 125, 99);
		
		getContentPane().add(lblNewLabel);
//		TableColumn CompColumn = table.getColumnModel().getColumn(0);
		TableColumn CompColumn = table.getColumnModel().getColumn(0);
		JComboBox comboComp = new JComboBox();
		comboComp.addItem("Comp1");
		comboComp.addItem("Comp2");
		CompColumn.setCellEditor(new DefaultCellEditor(comboComp));
//		TableColumn SterColumn = table.getColumnModel().getColumn(1);
		TableColumn SterColumn = table.getColumnModel().getColumn(1);
		JComboBox comboSter = new JComboBox();
		comboSter.addItem("Str1");
		comboSter.addItem("Str2");
		SterColumn.setCellEditor(new DefaultCellEditor(comboSter));
		
		/*
		 * *actionPerformed of "Ajouter Stéréotype" button
		 */
		btnAjouterComposant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Not yet implemented
			}
		});
		
		/*
		 * *actionPerformed of "Appliquer Patron" button
		 */
		btnAppliquerPatron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				new FrameDebut();
				new FrameDebut();
				/*
				 * *construct the command (applyProfile)to insert into the transformation
				 */
				profileApplicationCommande="t.applyProfile(UML2!Package.allInstances() -> select(s | s.name ='"+FrameDebut.getProfile()+"') -> first());\n" +
						"thisModule.entityProfile<-UML2!Package.allInstances() -> select(s | s.name = '"+FrameDebut.getProfile()+"') -> first();";
				/*
				 * *construct the command (applyStereotype)to insert into the transformation
				 */
				for(int i=0;i<nombreStereotype;i++){
						stereotypeApplicationCommande=stereotypeApplicationCommande+"if(s.name = '"+table.getValueAt(i, 0)+"')\n" +
						"{t.applyStereotype(thisModule.getStereotype(thisModule.entityProfile,'"+table.getValueAt(i, 1)+"'));}\n";
					}
				
				/*
				 * * Insert the command into the transformation
				 */
				System.out.println(System.getProperty("user.dir" ));
				/*create an ReplaceString Object with the transformation path as a parametre.
				 * The transformation file is located in an ATL Project to ensure updating the asm file associated to the atl file.*/
				ReplaceString R=new ReplaceString(System.getProperty("user.dir" )+"/../testATL/MyTransformation.asm");
				
				try {
				/*Replace the String "---REMPLACER_PROFIL---" by the String profileApplicationCommande (profileApplicationCommande is the ATL command which apply a profile to the input model) */
					R.MethodeRemplacer("---REMPLACER_PROFIL---", profileApplicationCommande);
				
				/*Replace the String "---REMPLACER_STEREO---" by the String stereotypeApplicationCommande (stereotypeApplicationCommande is the ATL command which apply stereotypes to the input model) */	
					R.MethodeRemplacer("---REMPLACER_STEREO---",stereotypeApplicationCommande);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				System.out.println("insertion terminée");
				
				/*create a CopyFile Object.*/
				CopyFile c=new CopyFile();
				
				try {
				/*Copying the asm file (related to the atl transformation) from the ATL project to the plugin project*/
					c.copy(System.getProperty("user.dir" )+"/../testATL/MyTransformation.asm",System.getProperty("user.dir" )+"/src/securityPatternPlugin/handlers/MyTransformation.asm");
					
				/*Copying the atl file from the ATL project to the plugin project*/
					c.copy(System.getProperty("user.dir" )+"/../testATL/MyTransformation.atl",System.getProperty("user.dir" )+"/src/securityPatternPlugin/handlers/MyTransformation.atl");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				System.out.println("copie terminée");
				
				/*thread whitch sleeps for 4 seconds before executing the transformation to wait for the workspace refreshment*/
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
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
					
				/*running the transformation*/
				try {					
				/*create a new runner (object associated to the transformation class) */
						MyTransformation runner = new MyTransformation();
						
				/*load the inputs required in the transformation (the model & the profile) */
						runner.loadModels("MyModel.uml", "MyProfile.profile.uml");
						
				/*Apply the transformation*/
						runner.doMyTransformation(new NullProgressMonitor());
						
				/*save the generated output model*/
						runner.saveModels("MyModelout.uml");
					
				} catch (ATLCoreException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ATLExecutionException e) {
					e.printStackTrace();
				}
				
				/*Indicate the end of the transformation*/
				JOptionPane.showMessageDialog(null,"Transformation terminée");
				
				/*
				 * Delete temporary files (MyTransformation.atl and MyTransformation.asm)
				 */
//				File ATLFile = new File("E:/MyNewJunoWS/securityPatternPlugin/src/securityPatternPlugin/handlers/MyTransformation.atl");
//				File ASMFile = new File("E:/MyNewJunoWS/securityPatternPlugin/src/securityPatternPlugin/handlers/MyTransformation.asm");
//				ATLFile.delete();
//				ASMFile.delete();
				
				/*
				 * restore Transformations
				 */
//				try {
//					c.copy("E:/MyNewJunoWS/testATL/TransformationBrute/MyTransformation.atl","E:/MyNewJunoWS/testATL/MyTransformation.atl");
//					c.copy("E:/MyNewJunoWS/testATL/TransformationBrute/MyTransformation.asm","E:/MyNewJunoWS/testATL/MyTransformation.asm");
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
				
				
				
			}
		});
		
	}
}
