import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class CreatingTables extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	Connection connection = null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreatingTables frame = new CreatingTables();
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
	public CreatingTables() {
		connection = sqliteConnection1.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton btnCoupon = new JButton("CreateCouponTable");
		btnCoupon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statement stmt=null;
				String couponquery="Create table Coupon("
						+ "CouponCode TEXT primary key not null,"
						+ "CouponAmount CHAR(10)"					
						+");";
				try {
					stmt = connection.createStatement();
					stmt.executeUpdate(couponquery);								
					System.out.println("coupon query created sucessfully");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
			}
		});
		btnCoupon.setBounds(10, 11, 152, 54);
		contentPane.add(btnCoupon);
		
		JButton btnCustomer = new JButton("Create Customer Table");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement stmt=null;
				String customerquery="Create table Customer("
						+ "Customer_ID TEXT primary key not null,"
						+ "Gender BOOL,"
						+ "GiftcardID TEXT,"
						+ "Points DOUBLE"
						+");";
				try {
					stmt = connection.createStatement();
					stmt.executeUpdate(customerquery);								
					System.out.println("customer query created sucessfully");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCustomer.setBounds(204, 11, 176, 54);
		contentPane.add(btnCustomer);
		
		JButton btnEmployee = new JButton("Create Employee Table");
		btnEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement stmt=null;
				String employeerquery="CREATE TABLE EmployeeInfo ("
						+"Employee_ID TEXT PRIMARY KEY  NOT NULL ,"
						+"Name TEXT,"
						+"UserName TEXT,"
						+"password TEXT,"
						+"Is_Manager BOOL DEFAULT false"
						+");";
				try {
					stmt = connection.createStatement();
					stmt.executeUpdate(employeerquery);								
					System.out.println("employee query created sucessfully");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEmployee.setBounds(411, 11, 192, 54);
		contentPane.add(btnEmployee);
		
		JButton btnItem = new JButton("Create Item Table");
		btnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement stmt=null;
				String itemquery="CREATE TABLE Item ("
						+"Product_ID INTEGER PRIMARY KEY  NOT NULL ,"
						+"Description TEXT,"
						+"Name TEXT,"
						+"Category TEXT, "
						+"UnitPrice TEXT"
						+");";
				try {
					stmt = connection.createStatement();
					stmt.executeUpdate(itemquery);								
					System.out.println("item query created sucessfully");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnItem.setBounds(10, 124, 152, 54);
		contentPane.add(btnItem);
		
		JButton btnOrder = new JButton("Create Order Table");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement stmt=null;
				String orderquery="CREATE TABLE OrderInfo ("
						+"Order_ID INTEGER PRIMARY KEY ,"
						+"Customer_ID TEXT references Customer(Customer_ID) NOT NULL ,"
						+"Employee_ID TEXT references EmployeeInfo(Employee_ID) NOT NULL ,"					
						+"Order_Date DATETIME,"
						+"Balance DOUBLE"
						+");";
				try {
					stmt = connection.createStatement();
					stmt.executeUpdate(orderquery);								
					System.out.println("order query created sucessfully");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOrder.setBounds(204, 124, 176, 54);
		contentPane.add(btnOrder);
		
		JButton btnOrderItem = new JButton("Create OrderItem Table");
		btnOrderItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement stmt=null;
				String orderitemquery="CREATE TABLE OrderItem("
						+"OrderItem_ID INTEGER PRIMARY KEY ,"
						+"Order_ID INTEGER references OrderInfo(Order_ID) NOT NULL ,"
						+"Product_ID INTEGER references Item(Product_ID) NOT NULL ,"					
						+"Quantity INTEGER NOT NULL"
						+");";
				try {
					stmt = connection.createStatement();
					stmt.executeUpdate(orderitemquery);								
					System.out.println("orderitem query created sucessfully");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOrderItem.setBounds(411, 124, 192, 54);
		contentPane.add(btnOrderItem);
	}
	

}
