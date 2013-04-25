package securityPatternPlugin.handlers;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JEditorPane;

public class FramePatternView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePatternView frame = new FramePatternView();
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
	public FramePatternView() {
		setTitle("Pattern View");
		setBounds(100, 100, 689, 665);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			// action du boutton fermer
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				hide();
			}
		});
		btnNewButton.setBounds(574, 597, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 218, 653, 371);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		
		JLabel label = new JLabel("Pattern name : ");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(10, 22, 96, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("RBAC Pattern");
		label_1.setBounds(116, 23, 102, 14);
		panel.add(label_1);
		
		JLabel label_4 = new JLabel("Context: ");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_4.setBounds(10, 42, 60, 14);
		panel.add(label_4);
		
		JLabel label_6 = new JLabel("Problem: ");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_6.setBounds(10, 125, 60, 14);
		panel.add(label_6);
		
		JLabel label_8 = new JLabel("Solution: ");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_8.setBounds(10, 150, 60, 14);
		panel.add(label_8);
		
		JLabel label_12 = new JLabel("Known uses: ");
		label_12.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_12.setBounds(10, 257, 96, 14);
		panel.add(label_12);
		
		JEditorPane dtrpnJbk = new JEditorPane();
		dtrpnJbk.setEditable(false);
		dtrpnJbk.setText("Most institutions have a variety of job functions that require different skills and responsibilities. \r\nFor security reasons users should get rights based on their job functions.\r\n This corresponds to the application of the need-to-know principle, a fundamental security policy [Sum97].\r\nJob functions can be interpreted as roles that people play in performing their duties. In particular, \r\nweb-based systems have a variety of users: company employees, customers, partners, search engines, etc.");
		dtrpnJbk.setBounds(99, 38, 531, 76);
		panel.add(dtrpnJbk);
		
		JEditorPane dtrpnHowToAssign = new JEditorPane();
		dtrpnHowToAssign.setText("How to assign rights to users according to their roles in an institution?");
		dtrpnHowToAssign.setEditable(false);
		dtrpnHowToAssign.setBounds(99, 125, 457, 20);
		panel.add(dtrpnHowToAssign);
		
		JEditorPane dtrpnABasicModel = new JEditorPane();
		dtrpnABasicModel.setText("A basic model for Role-Based Access Control (RBAC) is shown in Figure . Classes User and Role describe the registered users and the predefined roles, respectively. Users are assigned to roles, roles are given rights \r\naccording to their functions. The association class Right defines the access types that a user within a role is authorized to apply to the protection object. In fact, the combination Role, ProtectionObject, and Right is an instance of the Authorization pattern. Accordingly, the predicate indicates contentdependent restrictions \r\nthat can be used to select specific objects. The attribute copy_flag indicates a Boolean value (true/false).");
		dtrpnABasicModel.setEditable(false);
		dtrpnABasicModel.setBounds(99, 150, 531, 90);
		panel.add(dtrpnABasicModel);
		
		JEditorPane dtrpnOurPatternRepresents = new JEditorPane();
		dtrpnOurPatternRepresents.setText("RBAC pattern represents in object-oriented form a model described in set terms in [San96]. That model has been the basis of most research papers and implementations of this idea [FBK99]. RBAC is implemented in a variety of commercial systems, including Sun\u2019s J2EE [Jaw00], Microsoft\u2019s Windows 2000, IBM\u2019s WebSphere, and Oracle, among others. The basic security facilities of Java\u2019s JDK 1.2 have been shown to be able to\r\n support a rich variety of RBAC policies [Giu99].");
		dtrpnOurPatternRepresents.setEditable(false);
		dtrpnOurPatternRepresents.setBounds(99, 257, 531, 81);
		panel.add(dtrpnOurPatternRepresents);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "RBAC Pattern", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 653, 196);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(83, 11, 560, 173);
		panel_1.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("./icons/patronRBAC.png"));
	}
}
