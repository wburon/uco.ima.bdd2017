package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class JFInterface extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	private JButton btnOperationSurLe;
	private JButton btnOperationSurLa;
	private JButton btnOperationSurLhotel;
	
	private JPlog JPlog;
	private JPgeHotel JPgeHotel;
	private JPgePerso JPgePerso;
	private JPgerant JPgerant;

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



		JPlog=new JPlog(this);
		JPgerant = new JPgerant(this);
		JPgePerso = new JPgePerso(this);
		Init();
		
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(new BorderLayout(0, 0));
		
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		JPlog = new JPlog(this);
//		contentPane.add(JPlog);
//		JPlog.repaint();
//		JPlog.revalidate();
		
//		JPanel nord = new JPanel();
//		nord.setPreferredSize(new Dimension(50, 50));
//		contentPane.add(nord, BorderLayout.NORTH);
//		
//		JPanel panel = new JPanel();
//		nord.add(panel);
//		
//		JLabel label = new JLabel("LOGIN");
//		label.setHorizontalAlignment(SwingConstants.CENTER);
//		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		panel.add(label);
//		
//		textField = new JTextField();
//		textField.setColumns(10);
//		panel.add(textField);
//		
//		JLabel label_1 = new JLabel("PASSWORD");
//		label_1.setHorizontalAlignment(SwingConstants.CENTER);
//		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		panel.add(label_1);
//		
//		textField_1 = new JTextField();
//		textField_1.setColumns(10);
//		panel.add(textField_1);
//		
//		JPanel ouest = new JPanel();
//		ouest.setPreferredSize(new Dimension(50, 50));
//		contentPane.add(ouest, BorderLayout.WEST);
//		
//		JPanel est = new JPanel();
//		est.setPreferredSize(new Dimension(50, 50));
//		contentPane.add(est, BorderLayout.EAST);
//		
//		JPanel sud = new JPanel();
//		sud.setPreferredSize(new Dimension(50, 50));
//		contentPane.add(sud, BorderLayout.SOUTH);
//		
//		JPanel center = new JPanel();
//		contentPane.add(center, BorderLayout.CENTER);
//		center.setLayout(new GridLayout(2, 2, 0, 0));
//		
//		JPanel JPOpePersonnel = new JPanel();
//		center.add(JPOpePersonnel);
//		JPOpePersonnel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
//		
//		btnOperationSurLe = new JButton("Operation sur le personnel");
//		btnOperationSurLe.setAlignmentX(Component.CENTER_ALIGNMENT);
//		JPOpePersonnel.add(btnOperationSurLe);
//		btnOperationSurLe.addActionListener(this);
//		
//		JPanel JPOpeHotel = new JPanel();
//		center.add(JPOpeHotel);
//		JPOpeHotel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
//		
//		btnOperationSurLhotel = new JButton("Operation sur l'hotel");
//		JPOpeHotel.add(btnOperationSurLhotel);
//		btnOperationSurLhotel.addActionListener(this);
//		
//		JPanel JPOpeClientelle = new JPanel();
//		center.add(JPOpeClientelle);
//		JPOpeClientelle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
//		
//		btnOperationSurLa = new JButton("Operation sur la clientelle");
//		JPOpeClientelle.add(btnOperationSurLa);
//		
//		JPanel JPQuitter = new JPanel();
//		center.add(JPQuitter);
//		JPQuitter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
//		
//		JButton btnQuitter = new JButton("Quitter");
//		JPQuitter.add(btnQuitter);
	}
	
	public void Init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 575);
		setContentPane(JPlog);
	}

	public JPlog getJPlog() {
		return JPlog;
	}

	public void setJPlog(JPlog jPlog) {
		JPlog = jPlog;
	}

	public JPgeHotel getJPgeHotel() {
		return JPgeHotel;
	}

	public void setJPgeHotel(JPgeHotel jPgeHotel) {
		JPgeHotel = jPgeHotel;
	}

	public JPgePerso getJPgePerso() {
		return JPgePerso;
	}

	public void setJPgePerso(JPgePerso jPgePerso) {
		JPgePerso = jPgePerso;
	}

	public JPgerant getJPgerant() {
		return JPgerant;
	}

	public void setJPgerant(JPgerant jPgerant) {
		JPgerant = jPgerant;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPlog.actionPerformed(e);
//		if(e.getSource()==btnOperationSurLe){
//			JPgePerso pp=new JPgePerso();
//			this.setContentPane(pp);
//			this.repaint();
//			this.revalidate();
//		}else if(e.getSource() == btnOperationSurLhotel){
//			JPgeHotel hh = new JPgeHotel();
//			this.setContentPane(hh);
//			this.repaint();
//			this.revalidate();
//		}
		
	}

}
