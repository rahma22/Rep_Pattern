package securityPatternPlugin.handlers;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
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

public class FrameSingleAccessPointchoice extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int nombreStereotype=1;
	private JPanel contentPane;
	private JTable table;
	boolean trouve=false;
	String profileApplicationCommande="";
	String componentStereotypeApplicationCommande="";
	String portStereotypeApplicationCommande="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameSingleAccessPointchoice frame = new FrameSingleAccessPointchoice();
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
	public FrameSingleAccessPointchoice() {
		setTitle("Pattern Application");
		setBounds(100, 100, 495, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterProtectedsystemAnd = new JLabel("Enter ProtectedSystem and SingleAccessPoint stereotypes for Single Access Point \r\n");
		lblEnterProtectedsystemAnd.setBounds(10, 11, 459, 14);
		contentPane.add(lblEnterProtectedsystemAnd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 459, 73);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		final DefaultTableModel model=new DefaultTableModel(new Object[][] {{"None", "None","None","None"},},
				new String[] {"Component", "Stereotype","Port","Stereotype"});
		table.setModel(model);
		final ParserUtilities P=new ParserUtilities();
		TableColumn CompColumn = table.getColumnModel().getColumn(0);//get column of components
		final JComboBox comboComp = new JComboBox();
		/*
		 * Fill ComboBox by Components Of the IN Model
		 */
				
				try {
					for(int i=0;i<P.getComponent(FrameBegin.getModelinPath()).size();i++)
					comboComp.addItem(P.getComponent(FrameBegin.getModelinPath()).get(i));
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


		TableColumn SterComposantColumn = table.getColumnModel().getColumn(1);//get column of stereotypes
		JComboBox comboSterComposant = new JComboBox();// creating comboBox for stereotypes
		/*
		 * Fill ComboBox by Stereotypes
		 */
						
						if(FrameBegin.getProfile().equals("SingleAccessPoint_Profile"))
							{
							comboSterComposant.addItem("ProtectedSystem");
							}
		/*
		 * End Filling ComboBox	
		 */
			SterComposantColumn.setCellEditor(new DefaultCellEditor(comboSterComposant));//Affect the ComboBox comboSter to the second column "SterColumn" of the table
				
			TableColumn SterPortColumn = table.getColumnModel().getColumn(3);//get column of stereotypes
			JComboBox portSterComposant = new JComboBox();// creating comboBox for stereotypes
			/*
			 * Fill ComboBox by Stereotypes
			 */
							
							if(FrameBegin.getProfile().equals("SingleAccessPoint_Profile"))
								{
								portSterComposant.addItem("SingleAccessPoint");
								}
			/*
			 * End Filling ComboBox	
			 */
				SterPortColumn.setCellEditor(new DefaultCellEditor(portSterComposant));//Affect the ComboBox comboSter to the second column "SterColumn" of the table


			final TableColumn PortColumn = table.getColumnModel().getColumn(2);//get column of port
			final JComboBox comboPort = new JComboBox();
			comboComp.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					/*
					 * Fill ComboBox by ports Of the seclected component in IN Model 
					 */
							comboPort.removeAllItems();
							model.setValueAt("None", table.getSelectedRow(), 2);
							try {					
								for(int j=0;j<P.getComponentPorts(FrameBegin.getModelinPath(), comboComp.getSelectedItem().toString()).size();j++)
									comboPort.addItem(P.getComponentPorts(FrameBegin.getModelinPath(), comboComp.getSelectedItem().toString()).get(j));
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
							PortColumn.setCellEditor(new DefaultCellEditor(comboPort));//Affect the ComboBox comboComp to the first column "CompColumn" of the table

				}
			});
			
			
					
					
						JButton button = new JButton("Delete stereotype");
						button.setIcon(new ImageIcon("icons/delete.png"));
		button.setBounds(87, 147, 147, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Add stereotype");
		button_1.setIcon(new ImageIcon("icons/add.png"));
		button_1.setBounds(260, 147, 147, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Apply Pattern");
		button_2.setBounds(336, 254, 133, 23);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("< Back");
		button_3.setBounds(194, 254, 133, 23);
		contentPane.add(button_3);
		
		JLabel lblPatternApplication = new JLabel(" pattern application:");
		lblPatternApplication.setBounds(10, 26, 112, 14);
		contentPane.add(lblPatternApplication);
		
		//ActionPerformed of "Add Stereotype" button
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombreStereotype=nombreStereotype+1;
				model.addRow(new String [] {"None","None","None","None"});
			}
		});
		//ActionPerformed of "Delete Stereotype"  button
		 
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(model.getRowCount()>1){
					model.removeRow(model.getRowCount()-1);
					 nombreStereotype = nombreStereotype-1;}
					else JOptionPane.showMessageDialog(null,"you can not delete this row");}	
			
		});
		
		//ActionPerformed of "< Back" button
		button_3.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				hide();
				new FrameBegin().show();
			}
		});
		
		//ActionPerformed of "Apply Pattern" button
		
		button_2.addActionListener(new ActionListener() {
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
				
				if(trouve){
				JOptionPane.showMessageDialog(null, errorMessage);
				errorMessage="";
				//End Verification of table Entries
				//Applying Transformation if entries are correct
				}
				else
				{

/*
 * *construct the command (applyProfile)to insert into the transformation
*/

					profileApplicationCommande="t.applyProfile(UML2!Package.allInstances() -> select(s | s.name ='"+FrameBegin.getProfile()+"') -> first());\n" +
									"thisModule.entityProfile<-UML2!Package.allInstances() -> select(s | s.name = '"+FrameBegin.getProfile()+"') -> first();";					
/*
 * *construct the command (applyStereotype)to insert into the transformation
 */
					//For SingleAccessPoint Pattern
					for(int i=0;i<nombreStereotype;i++){
						// Stereotype Application to components
						componentStereotypeApplicationCommande=componentStereotypeApplicationCommande+"if(s.name = '"+table.getValueAt(i, 0)+"')\n" +
						"{t.applyStereotype(thisModule.getStereotype(thisModule.entityProfile,'SingleAccessPoint."+table.getValueAt(i, 1)+"'));}\n";
					
						//Stereotype Application to ports
						 portStereotypeApplicationCommande = portStereotypeApplicationCommande+"if(s.name='"+table.getValueAt(i, 2)+"')\n" +
									"{t.applyStereotype(thisModule.getStereotype(thisModule.entityProfile,'SingleAccessPoint."+table.getValueAt(i, 3)+"'));}\n";
					
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
									

										
										/*running the SingleAccessPoint transformations*/		
												
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
												
											} catch (ATLCoreException e) {
												e.printStackTrace();
											} catch (IOException e) {
												e.printStackTrace();
											} catch (ATLExecutionException e) {
												e.printStackTrace();
											} //End SingleAccessPoint Transformation
											
 /*
  * Delete temporary files (RBACTransformation.atl and RBACTransformation.asm)
  */

//								File ATLFile = new File("./src/securityPatternPlugin/handlers/RBACTransformation.atl");
//								ATLFile.delete();
//								File ASMFile = new File("./src/securityPatternPlugin/handlers/RBACTransformation.asm");
//								ASMFile.delete();
															
															
								/*Indicate the end of the transformation*/
								JOptionPane.showMessageDialog(null,"Transformation terminée");											
				}//End trouve==false
		
			
			}
		});
		
		
	}
}
