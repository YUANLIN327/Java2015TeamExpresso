import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EmployeeLogin {

	JFrame loginFrame;
	private JTextField txtUserName;
	private JPasswordField pswPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeLogin window = new EmployeeLogin();
					window.loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	
	/**
	 * Create the application.
	 */
	public EmployeeLogin() {
		initialize();
		connection  = sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		loginFrame = new JFrame();
		loginFrame.getContentPane().setForeground(new Color(210,180,140));
		loginFrame.getContentPane().setBackground(new Color(128, 0, 0));
		loginFrame.setBounds(300, 200, 841, 539);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(183, 111, 490, 316);
		loginFrame.getContentPane().add(panel_1);
		panel_1.setBackground(new Color(210,180,140));
		panel_1.setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(183, 83, 242, 32);
		txtUserName.setBackground(Color.WHITE);
		txtUserName.setForeground(Color.LIGHT_GRAY);
		panel_1.add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Log In");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(22, 17, 92, 47);
		lblNewLabel_1.setFont(new Font("Hiragino Sans", Font.PLAIN, 25));
		panel_1.add(lblNewLabel_1);
		
		JButton btnLogin = new JButton("Log In");
		btnLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name$="";
				try{
					String query = "Select * from EmployeeInfo where username=? and password=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, txtUserName.getText());
					pst.setString(2, pswPassword.getText());
					ResultSet rs =pst.executeQuery();
					int counter=0;
					while(rs.next()){
						counter++;
						name$=rs.getString("name");
					}
					if(counter>=1){
						
						JOptionPane.showMessageDialog(null,"Login Successfully");
						CoffeePOS coffeeapp = new CoffeePOS();
						coffeeapp.lblWelcome.setText( "Welcome "+ name$);
						coffeeapp.setVisible(true);
						loginFrame.dispose();
					
					}
					else{
						JOptionPane.showMessageDialog(null,"Invalid username or password");
					}
					rs.close();
					pst.close();
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
				
				
			}
		});
		btnLogin.setBounds(338, 274, 146, 36);
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setForeground(new Color(0, 0, 0));
		panel_1.add(btnLogin);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserName.setBounds(22, 83, 127, 40);
		panel_1.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(22, 144, 127, 47);
		panel_1.add(lblPassword);
		
		pswPassword = new JPasswordField();
		pswPassword.setBounds(183, 154, 242, 32);
		panel_1.add(pswPassword);
		
		JLabel lblNewLabel = new JLabel("Expresso Login");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(243, 27, 394, 73);
		loginFrame.getContentPane().add(lblNewLabel);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Apple Chancery", Font.PLAIN, 50));
	}
}
