package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class JFInterface extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFInterface frame = new JFInterface();
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
	public JFInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel nord = new JPanel();
		nord.setPreferredSize(new Dimension(50, 50));
		contentPane.add(nord, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		nord.add(panel);
		
		JLabel label = new JLabel("LOGIN");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		panel.add(textField);
		
		JLabel label_1 = new JLabel("PASSWORD");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel.add(textField_1);
		
		JPanel ouest = new JPanel();
		ouest.setPreferredSize(new Dimension(50, 50));
		contentPane.add(ouest, BorderLayout.WEST);
		
		JPanel est = new JPanel();
		est.setPreferredSize(new Dimension(50, 50));
		contentPane.add(est, BorderLayout.EAST);
		
		JPanel sud = new JPanel();
		sud.setPreferredSize(new Dimension(50, 50));
		contentPane.add(sud, BorderLayout.SOUTH);
		
		JPanel center = new JPanel();
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}

}