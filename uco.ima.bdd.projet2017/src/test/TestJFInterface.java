package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TestJFInterface extends JFrame implements ActionListener{

	private JPanel contentPane;
	
	private TestJPanel tJP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestJFInterface frame = new TestJFInterface();
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
	public TestJFInterface() {
		
		Init();
		
//		TestJPanel tJP=new TestJPanel();
//		setContentPane(tJP);
//		tJP.repaint();
//		tJP.revalidate();
		
	}
	
	public void Init(){

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
		
		
		tJP=new TestJPanel();
		setContentPane(tJP);
		tJP.repaint();
		tJP.revalidate();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		tJP.actionPerformed(e);
		
	}
	
	

}
