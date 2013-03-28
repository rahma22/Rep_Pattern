package securityPatternPlugin.handlers;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

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
		setTitle("Patrons de securit\u00E9");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Patron de securit\u00E9", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 49, 156, 302);
		contentPane.add(panel);
		panel.setLayout(null);
		
		/*
		 * * Initialisation of radion buttons:
		 */
		
		final JRadioButton rdbtnRBAC = new JRadioButton("RBAC");
		rdbtnRBAC.setBounds(6, 30, 80, 23);
		panel.add(rdbtnRBAC);
		
		final JRadioButton rdbtnSESSION = new JRadioButton("Session");	
		rdbtnSESSION.setBounds(6, 56, 109, 23);
		panel.add(rdbtnSESSION);
		
		final JRadioButton rdbtnAUTHORIZATION = new JRadioButton("Authorization");
		rdbtnAUTHORIZATION.setEnabled(false);
		rdbtnAUTHORIZATION.setBounds(6, 82, 109, 23);
		panel.add(rdbtnAUTHORIZATION);
		
		final JRadioButton rdbtnMULTILEVEL = new JRadioButton("Multilevel");
		rdbtnMULTILEVEL.setEnabled(false);
		rdbtnMULTILEVEL.setBounds(6, 108, 109, 23);
		panel.add(rdbtnMULTILEVEL);
		
		final JRadioButton rdbtnREFMONITOR = new JRadioButton("Reference Monitor ");
		rdbtnREFMONITOR.setEnabled(false);
		rdbtnREFMONITOR.setBounds(6, 135, 129, 23);
		panel.add(rdbtnREFMONITOR);
		
		/*
		 * *Group the radio buttons.
		 */
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnRBAC);
		group.add(rdbtnSESSION);
		group.add(rdbtnAUTHORIZATION);
		group.add(rdbtnMULTILEVEL);
		group.add(rdbtnREFMONITOR);
		
		
		
		JLabel lblPatronsDeSecurit = new JLabel("Patrons de securit\u00E9 applicables sur le diagramme de composants");
		lblPatronsDeSecurit.setBounds(10, 11, 316, 14);
		contentPane.add(lblPatronsDeSecurit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "Aper\u00E7u", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(176, 49, 398, 302);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pattern name : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 22, 96, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("RBAC Pattern");
		lblNewLabel_1.setBounds(116, 23, 102, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblIntent = new JLabel("Intent : ");
		lblIntent.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIntent.setBounds(10, 47, 57, 14);
		panel_1.add(lblIntent);
		
		JLabel lblNewLabel_2 = new JLabel("Description de l'Intent du patron RBAC");
		lblNewLabel_2.setBounds(70, 48, 225, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblContext = new JLabel("Context: ");
		lblContext.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContext.setBounds(10, 72, 60, 14);
		panel_1.add(lblContext);
		
		JLabel lblNewLabel_3 = new JLabel("Description du contexte d'utilisation du patron RBAC");
		lblNewLabel_3.setBounds(80, 73, 286, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblProblem = new JLabel("Problem: ");
		lblProblem.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProblem.setBounds(10, 97, 60, 14);
		panel_1.add(lblProblem);
		
		JLabel lblNewLabel_4 = new JLabel("Description du probl\u00E8me ");
		lblNewLabel_4.setBounds(79, 98, 152, 14);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblSolution = new JLabel("Solution: ");
		lblSolution.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSolution.setBounds(10, 122, 60, 14);
		panel_1.add(lblSolution);
		
		JLabel lblNewLabel_5 = new JLabel("Solution propos\u00E9e");
		lblNewLabel_5.setBounds(80, 123, 138, 14);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblConsquences = new JLabel("Cons\u00E9quences: ");
		lblConsquences.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConsquences.setBounds(10, 147, 96, 14);
		panel_1.add(lblConsquences);
		
		JLabel lblNewLabel_6 = new JLabel("Cons\u00E9quence de l'utilisation du patron RBAC");
		lblNewLabel_6.setBounds(116, 148, 250, 14);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblKnownUses = new JLabel("Known uses: ");
		lblKnownUses.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKnownUses.setBounds(10, 172, 96, 14);
		panel_1.add(lblKnownUses);
		
		JButton btnNewButton = new JButton("Structure du Patron");
		btnNewButton.addActionListener(new ActionListener() {
			// action du bouton aperçu
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				new FramePattronApercu().show();
			}
		});
		btnNewButton.setBounds(245, 268, 152, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Valider");
		btnNewButton_1.addActionListener(new ActionListener() {
			//Action du boutton
		
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				//new FrameApplicationProfile().show();
				new FramePattronChoisi().show();
				
			}
		});
		btnNewButton_1.setBounds(485, 359, 89, 23);
		contentPane.add(btnNewButton_1);
/*
 * * ActionPerformed Methods
 */
		
		rdbtnRBAC.addActionListener(new ActionListener() {
			//RBAC radio button action
			public void actionPerformed(ActionEvent arg0) {
				//setProfile("MyProfile");
				setProfile("MyProfile");
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
		
		
	}

	
}
