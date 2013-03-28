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

public class FramePattronApercu extends JFrame {

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
					FramePattronApercu frame = new FramePattronApercu();
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
	public FramePattronApercu() {
		setTitle("Aper\u00E7u du patron");
		setBounds(100, 100, 565, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("icons/patronRBAC.png"));
		lblNewLabel.setBounds(0, 34, 549, 173);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Patron RBAC");
		lblNewLabel_1.setBounds(243, 231, 101, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Fermer");
		btnNewButton.addActionListener(new ActionListener() {
			// action du boutton fermer
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				hide();
			}
		});
		btnNewButton.setBounds(450, 273, 89, 23);
		contentPane.add(btnNewButton);
	}
}
