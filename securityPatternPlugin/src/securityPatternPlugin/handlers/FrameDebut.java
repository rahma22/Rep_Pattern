package securityPatternPlugin.handlers;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class FrameDebut extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static String profile;
	public static String getProfile() {
		return profile;
	}

	public static void setProfile(String profile) {
		FrameDebut.profile = profile;
	}
	
//	public static String getProfile() {
//		return profile;
//	}
//
//	public static void setProfile(String profile) {
//		FrameDebut.profile = profile;
//	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameDebut frame = new FrameDebut();
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
	public FrameDebut() {
		setTitle("Security patterns");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Security patterns", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(54, 46, 215, 172);
		contentPane.add(panel);
		panel.setLayout(null);
		
		/*
		 * * Initialisation of radion buttons:
		 */
		
		final JRadioButton rdbtnRBAC = new JRadioButton("RBAC");
		rdbtnRBAC.setActionCommand("RBAC");
		rdbtnRBAC.setBounds(6, 30, 80, 23);
		panel.add(rdbtnRBAC);
		
		final JRadioButton rdbtnSESSION = new JRadioButton("Session");
		rdbtnSESSION.setActionCommand("Session");
		rdbtnSESSION.setBounds(6, 56, 109, 23);
		panel.add(rdbtnSESSION);
		
		final JRadioButton rdbtnAUTHORIZATION = new JRadioButton("Authorization");
		rdbtnAUTHORIZATION.setActionCommand("Authorization");
		rdbtnAUTHORIZATION.setEnabled(false);
		rdbtnAUTHORIZATION.setBounds(6, 82, 109, 23);
		panel.add(rdbtnAUTHORIZATION);
		
		final JRadioButton rdbtnMULTILEVEL = new JRadioButton("Multilevel");
		rdbtnMULTILEVEL.setActionCommand("Multilevel");
		rdbtnMULTILEVEL.setEnabled(false);
		rdbtnMULTILEVEL.setBounds(6, 108, 109, 23);
		panel.add(rdbtnMULTILEVEL);
		
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
		group.add(rdbtnSESSION);
		group.add(rdbtnAUTHORIZATION);
		group.add(rdbtnMULTILEVEL);
		group.add(rdbtnREFMONITOR);
		
		
		
		JLabel lblPatronsDeSecurit = new JLabel("Applicable security patterns for the component diagram: ");
		lblPatronsDeSecurit.setBounds(10, 11, 293, 14);
		contentPane.add(lblPatronsDeSecurit);
		
		JButton btnNewButton_1 = new JButton("Next >");
		btnNewButton_1.addActionListener(new ActionListener() {
		
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				hide();
				/*
				 * *Open the frame responsible for entry stereotypes choice 
				 */
				new FramePattronChoisi().show();
				/*
				 * /Copy initial transformations in the package
				 */
				CopyFile c=new CopyFile();
				
				//Begin copy section for RBAC transformation
			if(group.getSelection().getActionCommand().equals("RBAC")){
				try {
					c.copy(System.getProperty("user.dir" )+"/initialTransformations/RBACTransformation.atl",System.getProperty("user.dir" )+"/src/securityPatternPlugin/handlers/RBACTransformation.atl");
					c.copy(System.getProperty("user.dir" )+"/initialTransformations/RBACTransformation.asm",System.getProperty("user.dir" )+"/src/securityPatternPlugin/handlers/RBACTransformation.asm");
				} catch (IOException e) {
					e.printStackTrace();
				}}
			//End Copy section for RBAC transformation
			
			}
		});
		
		btnNewButton_1.setBounds(217, 229, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Details");
		
		btnNewButton.setBounds(120, 229, 89, 23);
		contentPane.add(btnNewButton);
/*
 * * ActionPerformed Methods
 */
		
		rdbtnRBAC.addActionListener(new ActionListener() {
			//RBAC radio button action
			public void actionPerformed(ActionEvent arg0) {
				//setProfile("MyProfile");
				setProfile("RBAC_Profile");
				rdbtnMULTILEVEL.setEnabled(true);
				//Changement des labels
			}
		});
		rdbtnSESSION.addActionListener(new ActionListener() {
			// SESSION radio button action
			public void actionPerformed(ActionEvent arg0) {
				rdbtnRBAC.setEnabled(false);
				rdbtnAUTHORIZATION.setEnabled(true);
				//Changement des labels
			}
		});
		
		rdbtnAUTHORIZATION.addActionListener(new ActionListener() {
			// AUTHORIZATION radio button action
			public void actionPerformed(ActionEvent arg0) {
				rdbtnSESSION.setEnabled(false);
				rdbtnMULTILEVEL.setEnabled(true);
				//Changement des labels
			}
		});
		
		rdbtnMULTILEVEL.addActionListener(new ActionListener() {
			// MULTILEVEL radio button action
			public void actionPerformed(ActionEvent arg0) {
				rdbtnAUTHORIZATION.setEnabled(false);
				rdbtnREFMONITOR.setEnabled(true);
				//Changement des labels
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				new FramePattronApercu().show();
			}
		});
		
		
	}
}
