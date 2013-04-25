package securityPatternPlugin.handlers;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class FrameBegin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static String profile;
	private JTextField textField;
	private static String modelinPath;
	boolean correctEntries=false;
	
	public static String getProfile() {
		return profile;
	}

	public static void setProfile(String profile) {
		FrameBegin.profile = profile;
	}
	
	public static String getModelinPath() {
		return modelinPath;
	}

	public static void setModelinPath(String modelinPath) {
		FrameBegin.modelinPath = modelinPath;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameBegin frame = new FrameBegin();
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
	public FrameBegin() {
		setTitle("Security patterns");
		setBounds(100, 100, 372, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Security patterns", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 99, 336, 172);
		contentPane.add(panel);
		panel.setLayout(null);
		
		/*
		 * * Initialisation of radion buttons:
		 */
		
		final JRadioButton rdbtnRBAC = new JRadioButton("RBAC");
		rdbtnRBAC.setEnabled(false);
		rdbtnRBAC.setActionCommand("RBAC");
		rdbtnRBAC.setBounds(6, 56, 80, 23);
		panel.add(rdbtnRBAC);
		
		final JRadioButton rdbtnAuthenticator = new JRadioButton("Authenticator");
		rdbtnAuthenticator.setActionCommand("Authenticator");
		rdbtnAuthenticator.setBounds(6, 25, 109, 23);
		panel.add(rdbtnAuthenticator);
		
		final JRadioButton rdbtnCheckPoint = new JRadioButton("CheckPoint");
		rdbtnCheckPoint.setEnabled(false);
		rdbtnCheckPoint.setActionCommand("CheckPoint");
		rdbtnCheckPoint.setBounds(6, 113, 109, 23);
		panel.add(rdbtnCheckPoint);
		
		final JRadioButton rdbtnSINGLEACCESSPOINT = new JRadioButton("SingleAccessPoint");
		rdbtnSINGLEACCESSPOINT.setActionCommand("SingleAccessPoint");
		rdbtnSINGLEACCESSPOINT.setBounds(6, 87, 129, 23);
		panel.add(rdbtnSINGLEACCESSPOINT);
		
		final JRadioButton rdbtnREFMONITOR = new JRadioButton("Reference Monitor");
		rdbtnREFMONITOR.setActionCommand("Reference Monitor");
		rdbtnREFMONITOR.setEnabled(false);
		rdbtnREFMONITOR.setBounds(6, 135, 129, 23);
		panel.add(rdbtnREFMONITOR);
		
		/*
		 * *Group the radio buttons.
		 */
		
		final ButtonGroup group = new ButtonGroup();
		group.add(rdbtnRBAC);
		group.add(rdbtnAuthenticator);
		group.add(rdbtnCheckPoint);
		group.add(rdbtnSINGLEACCESSPOINT);
		group.add(rdbtnREFMONITOR);
		
		
		
		JLabel lblPatronsDeSecurit = new JLabel("Applicable security patterns for the component diagram: ");
		lblPatronsDeSecurit.setBounds(10, 74, 293, 14);
		contentPane.add(lblPatronsDeSecurit);
		
		JButton btnNewButton_1 = new JButton("Next >");
		btnNewButton_1.addActionListener(new ActionListener() {
		
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
			/*
			 * *Entries verification
			 */
			if(!(textField.getText().equals(""))&&((rdbtnAuthenticator.isSelected())||(rdbtnCheckPoint.isSelected())||(rdbtnRBAC.isSelected())||(rdbtnREFMONITOR.isSelected())||(rdbtnSINGLEACCESSPOINT.isSelected())))correctEntries=true;
			if(correctEntries==false){JOptionPane.showMessageDialog(null, "Choose your IN Model");}
			else{
				hide();
			/*
			 * *Open the frame responsible for entry stereotypes choice 
			 */
				if(group.getSelection().getActionCommand().equals("SingleAccessPoint"))new FrameSingleAccessPointchoice().show();
				else new FramePatternChoice().show();
			/*
			 * /Copy initial transformations in the package
			 */
				CopyFile c=new CopyFile();
				
			//Begin copy InitialTransformation 
//			if(group.getSelection().getActionCommand().equals("RBAC")){}
			//	URL fileUrl = Platform.getBundle("securityPatternPlugin").getEntry("/");
			//	ResourcesPlugin.getWorkspace();
				System.out.println(System.getProperty("user.dir"));
				
				try {
					c.copy("./transformations/initialTransformations/InitialTransformation.atl","./src/securityPatternPlugin/handlers/InitialTransformation.atl");
					c.copy("./transformations/initialTransformations/InitialTransformation.asm","./src/securityPatternPlugin/handlers/InitialTransformation.asm");
				} catch (IOException e) {
					e.printStackTrace();
				}
			//End Copy InitialTransformation
			
			}
			}//End if coorectEntries==true
		
		});
		
		btnNewButton_1.setBounds(257, 280, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Details");
		
		btnNewButton.setBounds(160, 280, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblChooseYourComponent = new JLabel("Choose your Component Model :");
		lblChooseYourComponent.setBounds(10, 11, 336, 14);
		contentPane.add(lblChooseYourComponent);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(10, 36, 221, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Open");
		btnNewButton_2.setBounds(257, 35, 89, 23);
		contentPane.add(btnNewButton_2);
/*
 * * ActionPerformed Methods
 */
		
		rdbtnRBAC.addActionListener(new ActionListener() {
			//RBAC radio button action
			public void actionPerformed(ActionEvent arg0) {
				setProfile("RBAC_Profile");
				//Changement des labels
			}
		});
		rdbtnAuthenticator.addActionListener(new ActionListener() {
			// SESSION radio button action
			public void actionPerformed(ActionEvent arg0) {
				setProfile("Authenticator_Profile");
			}
		});
		
		rdbtnCheckPoint.addActionListener(new ActionListener() {
			// AUTHORIZATION radio button action
			public void actionPerformed(ActionEvent arg0) {
				setProfile("CheckPoint_Profile");
			}
		});
		
		rdbtnSINGLEACCESSPOINT.addActionListener(new ActionListener() {
			// SINGLEACCESSPOINT radio button action
			public void actionPerformed(ActionEvent arg0) {
				setProfile("SingleAccessPoint_Profile");
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				new FramePatternView().show();
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser(new File("."));
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "UML Models", "uml");
				filechooser.setFileFilter(filter);
				if (filechooser.showOpenDialog(null)== JFileChooser.APPROVE_OPTION) {
					modelinPath=filechooser.getSelectedFile().getName();
					textField.setText(filechooser.getSelectedFile().getName());
					
/*
 * *Organize dependencies between Applied profiles and applicable profiles
 */
					ParserUtilities P=new ParserUtilities();
					
					try {
						//If Ahthenticator_Profile is already applied
						if(P.getProfile(modelinPath).contains("Authenticator_Profile"))
							{
							rdbtnAuthenticator.setEnabled(false);
							rdbtnRBAC.setEnabled(true);
							}
						//If SingleAccessPoint_Profile is already applied
						if(P.getProfile(modelinPath).contains("SingleAccessPoint_Profile"))
						{
							rdbtnSINGLEACCESSPOINT.setEnabled(false);
							rdbtnCheckPoint.setEnabled(true);
						}
						//If RBAC_Profile is already applied
						if(P.getProfile(modelinPath).contains("RBAC_Profile"))
						{
							rdbtnRBAC.setEnabled(false);
							rdbtnAuthenticator.setEnabled(false);
						}
						//If CheckPoint_Profile is already applied
						if(P.getProfile(modelinPath).contains("CheckPoint_Profile"))
						{
							rdbtnCheckPoint.setEnabled(false);
							rdbtnSINGLEACCESSPOINT.setEnabled(false);
						}
					} catch (ParserConfigurationException e) {
						e.printStackTrace();
					} catch (SAXException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					}		
			}
		});
		
	}

	
}
