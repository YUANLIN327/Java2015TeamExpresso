import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;


public class GiftPanel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiftPanel frame = new GiftPanel();
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
	public GiftPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 222);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Customer Name:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 50, 138, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblCustomerName = new JLabel("name");
		lblCustomerName.setHorizontalAlignment(SwingConstants.LEFT);
		lblCustomerName.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblCustomerName.setBounds(175, 50, 138, 34);
		contentPane.add(lblCustomerName);
		
		JLabel lblPointsAvailable = new JLabel("Points Available:");
		lblPointsAvailable.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPointsAvailable.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPointsAvailable.setBounds(10, 110, 138, 34);
		contentPane.add(lblPointsAvailable);
		
		JLabel lblPoint = new JLabel("point");
		lblPoint.setHorizontalAlignment(SwingConstants.LEFT);
		lblPoint.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblPoint.setBounds(175, 110, 138, 34);
		contentPane.add(lblPoint);
	}
}
