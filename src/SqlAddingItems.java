import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SqlAddingItems extends JFrame {

	private JPanel contentPane;
	Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SqlAddingItems frame = new SqlAddingItems();
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
	public SqlAddingItems() {
		
		connection  = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton btnAddItem = new JButton("Add");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String sql="";
					Statement stm = connection.createStatement();
					connection.setAutoCommit(false);
					//insert item
//					sql = "INSERT INTO ITEM(Name,Category,UnitPrice) VALUES('Black Tea','Tea','1.5');";stm.executeUpdate(sql);
//					sql = "INSERT INTO ITEM(Name,Category,UnitPrice) VALUES('Chai Tea','Tea','1.75');";stm.executeUpdate(sql);
//					sql = "INSERT INTO ITEM(Name,Category,UnitPrice) VALUES('Herbal Tea','Tea','1.25');";stm.executeUpdate(sql);
//					sql = "INSERT INTO ITEM(Name,Category,UnitPrice) VALUES('Rooibos Tea','Tea','1.75');";stm.executeUpdate(sql);
//					sql = "INSERT INTO ITEM(Name,Category,UnitPrice) VALUES('Regular Coffee','DripCoffee','1.25');";stm.executeUpdate(sql);
//					sql = "INSERT INTO ITEM(Name,Category,UnitPrice) VALUES('Vanilla Coffee','DripCoffee','1.5');";stm.executeUpdate(sql);
//					sql = "INSERT INTO ITEM(Name,Category,UnitPrice) VALUES('Pumpkin Coffee','DripCoffee','1.6');";stm.executeUpdate(sql);
//					sql = "INSERT INTO ITEM(Name,Category,UnitPrice) VALUES('Hazelnut Coffee', 'DripCoffee','1.7');";stm.executeUpdate(sql);
//					sql = "INSERT INTO ITEM(Name,Category,UnitPrice) VALUES('Chocolate Frap','Frappuccino','3.25');";stm.executeUpdate(sql);
//					sql = "INSERT INTO ITEM(Name,Category,UnitPrice) VALUES('Vanilla Frap','Frappuccino','3.5');";stm.executeUpdate(sql);
//					sql = "INSERT INTO ITEM(Name,Category,UnitPrice) VALUES('Caramel Frap','Frappuccino','3.6');";stm.executeUpdate(sql);
//					sql = "INSERT INTO ITEM(Name,Category,UnitPrice) VALUES('Mint Frap','Frappuccino','3.7');";stm.executeUpdate(sql);
//					sql = "INSERT INTO ITEM(Name,Category,UnitPrice) VALUES('Hazelnut Latte','Latte','3.25');";stm.executeUpdate(sql);
//					sql = "INSERT INTO ITEM(Name,Category,UnitPrice) VALUES('Mocha','Latte','3.5');";stm.executeUpdate(sql);
//					sql = "INSERT INTO ITEM(Name,Category,UnitPrice) VALUES('Vanilla Latte','Latte','3.6');";stm.executeUpdate(sql);
//					sql = "INSERT INTO ITEM(Name,Category,UnitPrice) VALUES('Caramel Latte','Latte','3.6');";stm.executeUpdate(sql);
//					
////					insert coupon
//					sql = "INSERT INTO COUPON(CouponCode,CouponAmount) VALUES('Hallie','0.18');";
//					stm.executeUpdate(sql);
//					sql = "INSERT INTO COUPON(CouponCode,CouponAmount) VALUES('linyuan','0.27');";
//					stm.executeUpdate(sql);
//					sql = "INSERT INTO COUPON(CouponCode,CouponAmount) VALUES('xichen','0.20');";
//					stm.executeUpdate(sql);
//					sql = "INSERT INTO COUPON(CouponCode,CouponAmount) VALUES('claudia','0.15');";
//					stm.executeUpdate(sql);
//					sql = "INSERT INTO COUPON(CouponCode,CouponAmount) VALUES('sam','0.05');";
//					stm.executeUpdate(sql);
					
					//order
					String eid="1";
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					String datetime = df.format(date);
					sql ="INSERT INTO OrderInfo(Employee_id,Order_Date) VALUES("
							+"'"+eid+"',"
							+"'"+datetime+"'"
							+");";
					stm.executeUpdate(sql);

					
							
					
					
					System.out.println("runs");					
					stm.close();
					connection.commit();
					connection.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAddItem.setBounds(160, 127, 89, 23);
		contentPane.add(btnAddItem);
	}
}