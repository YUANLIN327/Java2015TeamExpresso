import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CoffeePOS1 extends JFrame {

	static CoffeePOS1 frame;
	private JPanel contentPane;
	BufferedImage buttonIcon1 = ImageIO.read(this.getClass().getResource(
			"Tea.png"));
	BufferedImage buttonIcon2 = ImageIO.read(this.getClass().getResource(
			"Latte.png"));
	BufferedImage buttonIcon3 = ImageIO.read(this.getClass().getResource(
			"DripCoffee.png"));
	BufferedImage buttonIcon4 = ImageIO.read(this.getClass().getResource(
			"Frappuccino.png"));
	BufferedImage buttonIcon5 = ImageIO.read(this.getClass().getResource(
			"BlackTea.png"));
	BufferedImage buttonIcon6 = ImageIO.read(this.getClass().getResource(
			"ChaiTea.png"));
	BufferedImage buttonIcon7 = ImageIO.read(this.getClass().getResource(
			"HerbalTea.png"));
	BufferedImage buttonIcon8 = ImageIO.read(this.getClass().getResource(
			"RegularCoffee.png"));
	BufferedImage buttonIcon9 = ImageIO.read(this.getClass().getResource(
			"VanillaCoffee.png"));
	BufferedImage buttonIcon10 = ImageIO.read(this.getClass().getResource(
			"PumpkinCoffee.png"));
	// Button set 2./*
	BufferedImage buttonIcon11 = ImageIO.read(this.getClass().getResource(
			"ChocolateFrap.png"));
	BufferedImage buttonIcon12 = ImageIO.read(this.getClass().getResource(
			"VanillaFrap.png"));
	BufferedImage buttonIcon13 = ImageIO.read(this.getClass().getResource(
			"CaramelFrap.png"));
	BufferedImage buttonIcon14 = ImageIO.read(this.getClass().getResource(
			"HazelnutLatte.png"));
	BufferedImage buttonIcon15 = ImageIO.read(this.getClass().getResource(
			"MochaLatte.png"));
	BufferedImage buttonIcon16 = ImageIO.read(this.getClass().getResource(
			"VanillaLatte.png"));
	BufferedImage buttonIcon17 = ImageIO.read(this.getClass().getResource(
			"RooibosTea.png")); // rooibos tea
	BufferedImage buttonIcon18 = ImageIO.read(this.getClass().getResource(
			"HazelnutCoffee.png")); // hazelnut coffee
	BufferedImage buttonIcon19 = ImageIO.read(this.getClass().getResource(
			"MintFrap.png")); // mint frap
	BufferedImage buttonIcon20 = ImageIO.read(this.getClass().getResource(
			"CaramelLatte.png")); // caramel latte
	BufferedImage buttonIcon21 = ImageIO.read(this.getClass().getResource(
			"logo.png")); // logi
	BufferedImage btnIconCash = ImageIO.read(this.getClass().getResource(
			"Cash.png"));
	BufferedImage btnIconCheck = ImageIO.read(this.getClass().getResource(
			"Check.png"));
	BufferedImage btnIconCoupon = ImageIO.read(this.getClass().getResource(
			"Coupon.png"));
	BufferedImage btnIconCredit = ImageIO.read(this.getClass().getResource(
			"Credit.png"));
	BufferedImage btnIconGiftCard = ImageIO.read(this.getClass().getResource(
			"GiftCard.png"));
	BufferedImage btnIconBack = ImageIO.read(this.getClass().getResource(
			"BackIcon.png"));

	// initate card layout field instance
	CardLayout c1 = new CardLayout();
	CardLayout cmenu = new CardLayout();
	DefaultListModel<OrderItem1> oidata = new DefaultListModel();
	JList itemlist = new JList(oidata);
	boolean isOrderEmpty = true;
	boolean isManager = false;
	JLabel lblSubTotal;
	JLabel lblWelcome;
	JLabel lblCouponReceived;
	JLabel lblTotalAmount;
	JButton btnOverridePrice;
	JFrame Receipt = new JFrame();
	JPanel categoryContainer;
	JPanel pnlContainer;
	Desktop desktop = null;
	String currentemployeeid;
	ArrayList<Order1> orders = new ArrayList<Order1>();
	
	NumberFormat nfpercent = NumberFormat
			.getPercentInstance(Locale.US);					
	NumberFormat nfcurrency= NumberFormat
			.getCurrencyInstance(Locale.US);
	JTextArea ta = new JTextArea();
	String CashAmt;
	String CurrentEmployee = "";
	private JTextField txtSearch;
	Order1 currentOrder = null;
	Customer customer = null;
	Connection connection = null;
	private JTextField txtCoupon;
	private JTextField txtBeforeTax;
	private JTextField txtTax;
	private JTextField txtItems;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new CoffeePOS1();
					frame.setVisible(true);
					frame.setSize(1100, 730);
					frame.setTitle("iCoffee Shop");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */

	public CoffeePOS1() throws IOException {
		connection = sqliteConnection.dbConnector();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JPanel pnlRibbon = new JPanel();
		pnlRibbon.setBounds(0, 14, 1090, 75);
		pnlRibbon.setLayout(null);
		pnlRibbon.setBackground(new Color(128, 0, 0));
		contentPane.add(pnlRibbon);

		JRadioButton rdbDinein = new JRadioButton("Dine In");
		rdbDinein.setForeground(Color.WHITE);
		rdbDinein.setBounds(550, 13, 97, 50);
		pnlRibbon.add(rdbDinein);
		rdbDinein.setFont(new Font("Dialog", Font.BOLD, 18));
		rdbDinein.setBackground(new Color(128, 0, 0));
		rdbDinein.setFocusPainted(false);

		JRadioButton rdbTakehome = new JRadioButton("Take Home");
		rdbTakehome.setForeground(Color.WHITE);
		rdbTakehome.setFont(new Font("Dialog", Font.BOLD, 18));
		rdbTakehome.setBackground(new Color(128, 0, 0));
		rdbTakehome.setBounds(666, 7, 124, 63);
		rdbTakehome.setFocusPainted(false);
		pnlRibbon.add(rdbTakehome);

		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbDinein);
		btnGroup.add(rdbTakehome);

		JButton btnLogout = new JButton("Log out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeLogin emp = new EmployeeLogin();
				emp.loginFrame.setVisible(true);
				frame.dispose();

			}
		});
		btnLogout.setBounds(216, 41, 144, 29);
		pnlRibbon.add(btnLogout);

		txtSearch = new JTextField();
		txtSearch.setBounds(801, 24, 183, 33);
		pnlRibbon.add(txtSearch);
		txtSearch.setColumns(10);

		lblWelcome = new JLabel("Welcome");
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setFont(new Font("Dialog", Font.BOLD, 18));
		lblWelcome.setBounds(214, 13, 201, 17);
		pnlRibbon.add(lblWelcome);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search$ = txtSearch.getText();
				String category$ = "";
				int counter = 0;
				if(connection==null){
					connection = sqliteConnection.dbConnector();
				}
				try {
					String query = "Select * from Item where name=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, search$);
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						counter++;
						category$ = rs.getString("Category");
						System.out.println("run");
					}
					if (counter >= 1) {
						JOptionPane.showMessageDialog(null, "Item  found");
						cmenu.show(categoryContainer, category$);
					} else {
						JOptionPane.showMessageDialog(null,
								"Item not found, sorry");
					}

					rs.close();
					pst.close();					

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnSearch.setBounds(994, 23, 86, 34);
		pnlRibbon.add(btnSearch);

		JButton btnLogo = new JButton(new ImageIcon(buttonIcon21));
		btnLogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogo.setBackground(new Color(128, 0, 0));
		btnLogo.setBounds(0, 0, 137, 77);
		pnlRibbon.add(btnLogo);

		// add container panel for menu and check out
		pnlContainer = new JPanel();
		pnlContainer.setBackground(new Color(100, 149, 237));
		pnlContainer.setBounds(390, 92, 700, 587);
		// apply the card layout
		pnlContainer.setLayout(c1);
		contentPane.add(pnlContainer);

		// add menu panel
		JPanel pnlMenu = new JPanel();
		pnlMenu.setLayout(null);
		pnlMenu.setBackground(new Color(210, 180, 140));
		// add menu panel to container panel
		pnlContainer.add(pnlMenu, "Menu");

		// add checkout panel
		JPanel pnlCheckout = new JPanel();
		pnlCheckout.setLayout(null);
		pnlCheckout.setBackground(new Color(210, 180, 140));

		// add checkout panel to container panel
		pnlContainer.add(pnlCheckout, "Checkout");

		// Cashbutton
		JButton btnCash = new JButton(new ImageIcon(btnIconCash));
		btnCash.setText("Cash");
		btnCash.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnCash.setBounds(472, 148, 129, 63);
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final JFrame cashFrame = new JFrame();
				cashFrame.setTitle("Check");
				cashFrame.setBounds(100, 100, 349, 292);
				cashFrame.setVisible(true);

				JPanel cashContentPane = new JPanel();
				cashContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				cashContentPane.setLayout(null);
				cashFrame.setContentPane(cashContentPane);

				JLabel lblChange = new JLabel("cash Routing No:");
				lblChange.setFont(new Font("Dialog", Font.BOLD, 12));
				lblChange.setBounds(35, 88, 150, 18);
				cashContentPane.add(lblChange);

				final JTextField txtChange = new JTextField();
				txtChange.setColumns(10);
				txtChange.setBounds(35, 109, 263, 29);
				cashContentPane.add(txtChange);
				txtChange.setEnabled(false);
				
				JLabel lblNewLabel = new JLabel("Amount Tendered:");
				lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
				lblNewLabel.setBounds(35, 27, 104, 18);
				cashContentPane.add(lblNewLabel);

				final JTextField txtAmountTendered = new JTextField();
				txtAmountTendered.setBounds(35, 48, 263, 29);
				cashContentPane.add(txtAmountTendered);
				txtAmountTendered.setColumns(10);
				txtAmountTendered.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent evt) {
						int key = evt.getKeyCode();
						txtAmountTendered.setEditable(true);
					
						if((key>=KeyEvent.VK_0 && key<=KeyEvent.VK_9)||key==KeyEvent.VK_BACK_SPACE||
								(key>=KeyEvent.VK_NUMPAD0 &&key<=KeyEvent.VK_NUMPAD9)||key==KeyEvent.VK_PERIOD){
							txtAmountTendered.setEditable(true);
						}
						else{
							txtAmountTendered.setEditable(false);
						}
						}
				
				});



				
				JButton btnCalculate = new JButton("CalCulate");
				btnCalculate.setBounds(35, 156, 89, 36);
				cashContentPane.add(btnCalculate);
				btnCalculate.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						BigDecimal changebd = new BigDecimal(txtAmountTendered.getText());
						changebd = changebd.subtract(currentOrder.getTotal());
						changebd = changebd.setScale(2, BigDecimal.ROUND_HALF_EVEN);
						if(changebd.compareTo(new BigDecimal("0.00"))>=0){
							txtChange.setText(changebd.toString());
						}
						else{
							JOptionPane.showMessageDialog(null,"Ah oh, amount tendered should be larger than total due.");
							txtAmountTendered.setText("");							
						}
						
						
					}
				});
				
				JButton btnSubmit = new JButton("Submit");
				btnSubmit.setBounds(135, 156, 89, 36);
				cashContentPane.add(btnSubmit);
				btnSubmit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						triggerReceipt();
						ta.append("This is for credit card");
						
					}
				});

				JButton btnCancel = new JButton("Cancel");
				btnCancel.setBounds(235, 156, 89, 36);
				cashContentPane.add(btnCancel);
				btnCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						cashFrame.dispose();
						
					}
				});
			
		
				
				

			}

		});
		
				// creditcard button
				JButton btnCreditdebitCard = new JButton(new ImageIcon(btnIconCredit));
				btnCreditdebitCard.setText("Credit/Debit");
				btnCreditdebitCard.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
				btnCreditdebitCard.setBounds(472, 37, 129, 63);
				btnCreditdebitCard.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						JFrame creditFrame = new JFrame();
						creditFrame.setTitle("Credit/Debit");
						creditFrame.setBounds(100, 100, 349, 392);
						creditFrame.setVisible(true);

						JPanel creditContentPane = new JPanel();
						creditContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
						creditContentPane.setLayout(null);
						creditFrame.setContentPane(creditContentPane);

						JLabel lblNewLabel = new JLabel("Name:");
						lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
						lblNewLabel.setBounds(35, 27, 104, 18);
						creditContentPane.add(lblNewLabel);

						JTextField txtName = new JTextField();
						txtName.setBounds(35, 48, 263, 29);
						creditContentPane.add(txtName);
						txtName.setColumns(10);

						JLabel lblCardNumber = new JLabel("Card Number:");
						lblCardNumber.setFont(new Font("Dialog", Font.BOLD, 14));
						lblCardNumber.setBounds(35, 88, 104, 18);
						creditContentPane.add(lblCardNumber);

						JTextField txtCardNum = new JTextField();
						txtCardNum.setColumns(10);
						txtCardNum.setBounds(35, 109, 263, 29);
						creditContentPane.add(txtCardNum);

						JLabel lblExpirationDate = new JLabel("Expiration Date:");
						lblExpirationDate.setFont(new Font("Dialog", Font.BOLD, 14));
						lblExpirationDate.setBounds(35, 159, 160, 18);
						creditContentPane.add(lblExpirationDate);

						JLabel lblSecurityCode = new JLabel("Security Code");
						lblSecurityCode.setFont(new Font("Dialog", Font.BOLD, 14));
						lblSecurityCode.setBounds(35, 231, 104, 18);
						creditContentPane.add(lblSecurityCode);

						JTextField txtSecurityCode = new JTextField();
						txtSecurityCode.setColumns(10);
						txtSecurityCode.setBounds(35, 252, 72, 29);
						creditContentPane.add(txtSecurityCode);

						String[] monthStrings = { "Jan", "Feb", "Mar", "Apr", "May",
								"June", "July", "Aug", "Sep", "Oct", "Nov", "Dec" };
						JComboBox cbxMonth = new JComboBox(monthStrings);
						cbxMonth.setSelectedIndex(11);
						cbxMonth.setMaximumRowCount(12);
						cbxMonth.setBounds(35, 188, 72, 32);
						creditContentPane.add(cbxMonth);

						String[] yearStrings = { "2015", "2016", "2017", "2018",
								"2019", "2020", "2021", "2022", "2023", "2024" };
						JComboBox cbxYear = new JComboBox(yearStrings);
						cbxYear.setMaximumRowCount(20);
						cbxYear.setBounds(133, 188, 72, 32);
						creditContentPane.add(cbxYear);

						JButton btnSubmit = new JButton("Submit");
						btnSubmit.setBounds(72, 306, 89, 36);
						creditContentPane.add(btnSubmit);
						btnSubmit.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								triggerReceipt();
								ta.append("This is for credit card");
//								clearOrder();
							}
						});

						JButton btnCancel = new JButton("Cancel");
						btnCancel.setBounds(194, 306, 89, 36);
						creditContentPane.add(btnCancel);
					}
				});
				pnlCheckout.add(btnCreditdebitCard);

		pnlCheckout.add(btnCash);

		// check button
		
		JButton btnCheck = new JButton(new ImageIcon(btnIconCheck));
		btnCheck.setText("Check");
		btnCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnCheck.setBounds(472, 253, 129, 63);
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final JFrame checkFrame = new JFrame();
				checkFrame.setTitle("Check");
				checkFrame.setBounds(100, 100, 349, 392);
				checkFrame.setVisible(true);

				JPanel checkContentPane = new JPanel();
				checkContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				checkContentPane.setLayout(null);
				checkFrame.setContentPane(checkContentPane);

				JLabel lblNewLabel = new JLabel("Name:");
				lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
				lblNewLabel.setBounds(35, 27, 104, 18);
				checkContentPane.add(lblNewLabel);

				JTextField txtName = new JTextField();
				txtName.setBounds(35, 48, 263, 29);
				checkContentPane.add(txtName);
				txtName.setColumns(10);

				JLabel lblCardNumber = new JLabel("Check Routing No:");
				lblCardNumber.setFont(new Font("Dialog", Font.BOLD, 12));
				lblCardNumber.setBounds(35, 88, 150, 18);
				checkContentPane.add(lblCardNumber);

				JTextField txtCardNum = new JTextField();
				txtCardNum.setColumns(10);
				txtCardNum.setBounds(35, 109, 263, 29);
				checkContentPane.add(txtCardNum);

				JLabel lblDLNo = new JLabel("DL No:");
				lblDLNo.setFont(new Font("Dialog", Font.BOLD, 12));
				lblDLNo.setBounds(35, 159, 160, 18);
				checkContentPane.add(lblDLNo);

				JTextField txtDLNo = new JTextField();
				txtDLNo.setColumns(10);
				txtDLNo.setBounds(35, 180, 263, 29);
				checkContentPane.add(txtDLNo);

				JButton btnSubmit = new JButton("Submit");
				btnSubmit.setBounds(72, 306, 89, 36);
				checkContentPane.add(btnSubmit);
				btnSubmit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						triggerReceipt();
						ta.append("This is for credit card");
						
					}
				});

				JButton btnCancel = new JButton("Cancel");
				btnCancel.setBounds(194, 306, 89, 36);
				checkContentPane.add(btnCancel);
				btnCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						checkFrame.dispose();
						
					}
				});
			
			}
		});

		pnlCheckout.add(btnCheck);

		JButton btnCoupon = new JButton(new ImageIcon(btnIconCoupon));
		btnCoupon.setText("Coupon");
		btnCoupon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isfound = false;
				
				String coupon$ = JOptionPane.showInputDialog("Please enter the valid coupon code."
						+ "(Case sensitive)");
				BigDecimal couponbd = new BigDecimal("0.00");
				if(connection == null){
					connection = sqliteConnection.dbConnector();
				}
				
				try {
					String couponquery = "Select CouponAmount from Coupon where CouponCode=?";
					PreparedStatement pst = connection.prepareStatement(couponquery);
					pst.setString(1, coupon$);
					ResultSet rs = pst.executeQuery();
					while(rs.next()){
						isfound = true;
						currentOrder.couponbd = new BigDecimal(rs.getString("CouponAmount"));						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
				if (isfound) {
			
					updateitemlabel(currentOrder);
					lblCouponReceived.setText("Received "+nfpercent.format(currentOrder.couponbd)+" Off");
					JOptionPane.showMessageDialog(null, "Congratulations! A "
							+ nfpercent.format(currentOrder.couponbd)
							+ " dicount has been applied!");
				} else {
					JOptionPane.showMessageDialog(null,
							"Sorry.That's not a valid coupon.");
				}
			}
		});
		
		btnCoupon.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnCoupon.setBounds(472, 359, 129, 63);
		pnlCheckout.add(btnCoupon);

		//giftcard button
		JButton btnGiftCard = new JButton(new ImageIcon(btnIconGiftCard));		
		btnGiftCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("run 1");
				connection = sqliteConnection.dbConnector();
				int counter=0;
				String gift$ = JOptionPane.showInputDialog("Please enter your 5 digits giftcard#");			
			
				try {
					System.out.println("run 2");
					String query = "Select * from Customer where GiftCard=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, gift$);
					ResultSet rs = pst.executeQuery();
					System.out.println("run 3");
					while(rs.next()){	
						System.out.println("run 4");
						customer = new Customer();
						customer.GiftCardID=rs.getString("GiftCard");
						customer.name=rs.getString("Name");
						customer.points=rs.getDouble("Points");
						counter++;
					}
					if(counter>=1){
						JFrame giftframe = new JFrame();
						giftframe.setBounds(100, 100, 315, 222);
						giftframe.setVisible(true);
						JPanel giftcontentPane = new JPanel();
						giftcontentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
						giftcontentPane.setLayout(null);
						giftframe.setContentPane(giftcontentPane);
						JLabel lblNewLabel = new JLabel("Customer Name:");
						lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
						lblNewLabel.setBounds(10, 50, 138, 34);
						giftcontentPane.add(lblNewLabel);
						
						JLabel lblCustomerName = new JLabel(customer.name);
						lblCustomerName.setHorizontalAlignment(SwingConstants.LEFT);
						lblCustomerName.setFont(new Font("Dialog", Font.PLAIN, 14));
						lblCustomerName.setBounds(175, 50, 138, 34);
						giftcontentPane.add(lblCustomerName);
						
						JLabel lblPointsAvailable = new JLabel("Points Available:");
						lblPointsAvailable.setHorizontalAlignment(SwingConstants.RIGHT);
						lblPointsAvailable.setFont(new Font("Dialog", Font.BOLD, 14));
						lblPointsAvailable.setBounds(10, 110, 138, 34);
						giftcontentPane.add(lblPointsAvailable);
						
						JLabel lblPoint = new JLabel(""+customer.points);
						lblPoint.setHorizontalAlignment(SwingConstants.LEFT);
						lblPoint.setFont(new Font("Dialog", Font.PLAIN, 14));
						lblPoint.setBounds(175, 110, 138, 34);
						giftcontentPane.add(lblPoint);
					}
					System.out.println("run 5");
					rs.close();
					pst.close();
					connection.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnGiftCard.setText("Gift Card");
		btnGiftCard.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnGiftCard.setBounds(472, 472, 129, 63);
		pnlCheckout.add(btnGiftCard);
		
		// back button
		JButton btnBack = new JButton(new ImageIcon(btnIconBack));
		btnBack.setBackground(SystemColor.window);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c1.show(pnlContainer, "Menu");
			}
		});
		btnBack.setBounds(10, 11, 38, 39);
		btnBack.setBorderPainted(false);
		pnlCheckout.add(btnBack);

		JLabel lblNewLabel_1 = new JLabel("Items:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(72, 37, 119, 39);
		pnlCheckout.add(lblNewLabel_1);

		JLabel lblCreditdebit = new JLabel("Credit/Debit");
		lblCreditdebit.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreditdebit.setForeground(new Color(139, 0, 0));
		lblCreditdebit.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblCreditdebit.setBounds(482, 106, 104, 16);
		pnlCheckout.add(lblCreditdebit);

		JLabel lblCash = new JLabel("Cash");
		lblCash.setHorizontalAlignment(SwingConstants.CENTER);
		lblCash.setForeground(new Color(139, 0, 0));
		lblCash.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblCash.setBounds(482, 217, 104, 16);
		pnlCheckout.add(lblCash);

		JLabel lblCheck = new JLabel("Check");
		lblCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheck.setForeground(new Color(139, 0, 0));
		lblCheck.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblCheck.setBounds(482, 322, 104, 16);
		pnlCheckout.add(lblCheck);

		JLabel lblCoupon = new JLabel("Coupon/");
		lblCoupon.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoupon.setForeground(new Color(139, 0, 0));
		lblCoupon.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblCoupon.setBounds(482, 428, 101, 16);
		pnlCheckout.add(lblCoupon);
		
		JLabel lblGiftcard = new JLabel("GiftCard");
		lblGiftcard.setHorizontalAlignment(SwingConstants.CENTER);
		lblGiftcard.setForeground(new Color(139, 0, 0));
		lblGiftcard.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblGiftcard.setBounds(482, 546, 104, 16);
		pnlCheckout.add(lblGiftcard);
		
		JLabel lblEmployeeDiscount = new JLabel("Employee Discount");
		lblEmployeeDiscount.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployeeDiscount.setForeground(new Color(139, 0, 0));
		lblEmployeeDiscount.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblEmployeeDiscount.setBounds(472, 445, 143, 16);
		pnlCheckout.add(lblEmployeeDiscount);
		
		lblCouponReceived = new JLabel("Received 0% Off");
		lblCouponReceived.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCouponReceived.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblCouponReceived.setBounds(72, 103, 119, 39);
		pnlCheckout.add(lblCouponReceived);
		
		txtCoupon = new JTextField();
		txtCoupon.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCoupon.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtCoupon.setEnabled(false);
		txtCoupon.setColumns(10);
		txtCoupon.setBounds(242, 105, 151, 39);
		pnlCheckout.add(txtCoupon);
		
		JLabel lblTotalBeforeTax = new JLabel("Total before Tax:");
		lblTotalBeforeTax.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalBeforeTax.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblTotalBeforeTax.setBounds(72, 179, 119, 39);
		pnlCheckout.add(lblTotalBeforeTax);
		
		txtBeforeTax = new JTextField();
		txtBeforeTax.setHorizontalAlignment(SwingConstants.RIGHT);
		txtBeforeTax.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtBeforeTax.setEnabled(false);
		txtBeforeTax.setColumns(10);
		txtBeforeTax.setBounds(242, 181, 151, 39);
		pnlCheckout.add(txtBeforeTax);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(108, 166, 285, 2);
		pnlCheckout.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(120, 322, 285, 2);
		pnlCheckout.add(separator_1);
		
		JLabel lblEstimatedTax = new JLabel("Estimated Tax:");
		lblEstimatedTax.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstimatedTax.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEstimatedTax.setBounds(72, 253, 119, 39);
		pnlCheckout.add(lblEstimatedTax);
		
		txtTax = new JTextField();
		txtTax.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTax.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtTax.setEnabled(false);
		txtTax.setColumns(10);
		txtTax.setBounds(242, 255, 151, 39);
		pnlCheckout.add(txtTax);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblTotal.setBounds(72, 335, 119, 39);
		pnlCheckout.add(lblTotal);
		
		txtItems = new JTextField();
		txtItems.setHorizontalAlignment(SwingConstants.RIGHT);
		txtItems.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtItems.setEnabled(false);
		txtItems.setColumns(10);
		txtItems.setBounds(242, 36, 151, 39);
		pnlCheckout.add(txtItems);
		
		lblTotalAmount = new JLabel("$0.00");
		lblTotalAmount.setForeground(Color.RED);
		lblTotalAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalAmount.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTotalAmount.setBounds(274, 335, 119, 39);
		pnlCheckout.add(lblTotalAmount);
		
		JButton btnNewButton = new JButton("Insert Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insertOrder(currentOrder);
			}
		});
		btnNewButton.setBounds(171, 427, 151, 52);
		pnlCheckout.add(btnNewButton);

		c1.show(pnlContainer, "Menu");

		JButton btnTea = new JButton(new ImageIcon(buttonIcon1));
		btnTea.setBackground(new Color(0, 51, 51));
		btnTea.setText("Tea");
		btnTea.setBounds(6, 24, 180, 120);
		pnlMenu.add(btnTea);

		JButton btnLatte = new JButton(new ImageIcon(buttonIcon2));
		btnLatte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLatte.setText("Latte");
		btnLatte.setBounds(6, 465, 180, 120);
		pnlMenu.add(btnLatte);

		JButton btnFrappuccino = new JButton(new ImageIcon(buttonIcon4));
		btnFrappuccino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFrappuccino.setText("Frappuccino");
		btnFrappuccino.setBounds(6, 322, 180, 120);
		pnlMenu.add(btnFrappuccino);
		// pnlMenu.add(btnChai);

		JButton btnDripCoffee = new JButton(new ImageIcon(buttonIcon3));
		btnDripCoffee.setText("DripCoffee");
		btnDripCoffee.setBounds(6, 172, 180, 120);
		pnlMenu.add(btnDripCoffee);

		categoryContainer = new JPanel();
		categoryContainer.setBounds(192, 0, 508, 599);
		categoryContainer.setBackground(new Color(30, 144, 255));
		categoryContainer.setLayout(cmenu);
		pnlMenu.add(categoryContainer);
		cmenu.show(categoryContainer, "Tea");

		JButton btnBlackTea = new JButton(new ImageIcon(buttonIcon5));
		btnBlackTea.setText("Black Tea");

		JPanel teaMenu = new JPanel();
		categoryContainer.add(teaMenu, "Tea");
		teaMenu.setBackground(new Color(128, 0, 0));
		teaMenu.setLayout(null);

		btnBlackTea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				addOrderItem(b.getText());
			}
		});
		btnBlackTea.setBounds(56, 79, 180, 120);
		teaMenu.add(btnBlackTea);

		JButton btnChaiTea = new JButton(new ImageIcon(buttonIcon6));
		btnChaiTea.setText("Chai Tea");
		btnChaiTea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				addOrderItem(b.getText());
			}
		});
		btnChaiTea.setBounds(270, 79, 180, 120);
		teaMenu.add(btnChaiTea);

		JButton btnHerbalTea = new JButton(new ImageIcon(buttonIcon7));
		btnHerbalTea.setText("Herbal Tea");
		btnHerbalTea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				addOrderItem(b.getText());

			}
		});
		btnHerbalTea.setBounds(56, 270, 180, 120);
		teaMenu.add(btnHerbalTea);

		JButton btnRooibosTea = new JButton(new ImageIcon(buttonIcon17));
		btnRooibosTea.setText("Rooibos Tea");
		btnRooibosTea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				addOrderItem(b.getText());
			}
		});
		btnRooibosTea.setBounds(270, 270, 180, 120);
		teaMenu.add(btnRooibosTea);

		JLabel lblBlackTea = new JLabel("Black Tea");
		lblBlackTea.setForeground(new Color(255, 255, 255));
		lblBlackTea.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblBlackTea.setBounds(112, 199, 104, 16);
		teaMenu.add(lblBlackTea);

		JLabel lblChaiTea = new JLabel("Chai Tea");
		lblChaiTea.setForeground(Color.WHITE);
		lblChaiTea.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblChaiTea.setBounds(325, 200, 104, 16);
		teaMenu.add(lblChaiTea);

		JLabel lblHerbalTea = new JLabel("Herbal Tea");
		lblHerbalTea.setForeground(Color.WHITE);
		lblHerbalTea.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblHerbalTea.setBounds(103, 389, 84, 16);
		teaMenu.add(lblHerbalTea);

		JLabel lblRooibosTea = new JLabel("Rooibos Tea");
		lblRooibosTea.setForeground(Color.WHITE);
		lblRooibosTea.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblRooibosTea.setBounds(317, 389, 104, 16);
		teaMenu.add(lblRooibosTea);

		JLabel lblTea = new JLabel("Tea");
		lblTea.setForeground(Color.WHITE);
		lblTea.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTea.setBounds(21, 11, 104, 16);
		teaMenu.add(lblTea);

		JPanel dripcoffeeMenu = new JPanel();
		dripcoffeeMenu.setBounds(132, 0, 255, 428);
		dripcoffeeMenu.setBackground(new Color(128, 0, 0));
		dripcoffeeMenu.setLayout(null);
		categoryContainer.add(dripcoffeeMenu, "DripCoffee");

		JButton btnRegularCoffee = new JButton(new ImageIcon(buttonIcon8));
		btnRegularCoffee.setText("Regular Coffee");
		btnRegularCoffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				addOrderItem(b.getText());
			}
		});
		btnRegularCoffee.setBounds(56, 79, 180, 120);
		dripcoffeeMenu.add(btnRegularCoffee);

		JButton btnVanillaCoffee = new JButton(new ImageIcon(buttonIcon9));
		btnVanillaCoffee.setText("Vanilla Coffee");
		btnVanillaCoffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				addOrderItem(b.getText());
			}
		});
		btnVanillaCoffee.setBounds(270, 79, 180, 120);
		dripcoffeeMenu.add(btnVanillaCoffee);

		JButton btnPumpkinCoffee = new JButton(new ImageIcon(buttonIcon10));
		btnPumpkinCoffee.setText("Pumpkin Coffee");
		btnPumpkinCoffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				addOrderItem(b.getText());
			}
		});
		btnPumpkinCoffee.setBounds(56, 270, 180, 120);
		dripcoffeeMenu.add(btnPumpkinCoffee);

		JButton btnHazelnutCoffee = new JButton(new ImageIcon(buttonIcon18));
		btnHazelnutCoffee.setText("Hazelnut Coffee");
		btnHazelnutCoffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				addOrderItem(b.getText());
			}
		});
		btnHazelnutCoffee.setBounds(270, 270, 180, 120);
		dripcoffeeMenu.add(btnHazelnutCoffee);

		JLabel lblPumpkinCoffee = new JLabel("Pumpkin Coffee");
		lblPumpkinCoffee.setForeground(Color.WHITE);
		lblPumpkinCoffee.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPumpkinCoffee.setBounds(91, 388, 136, 19);
		dripcoffeeMenu.add(lblPumpkinCoffee);

		JLabel lblRegularCoffee = new JLabel("Regular Coffee");
		lblRegularCoffee.setForeground(Color.WHITE);
		lblRegularCoffee.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblRegularCoffee.setBounds(91, 199, 122, 19);
		dripcoffeeMenu.add(lblRegularCoffee);

		JLabel lblVanillaCoffee = new JLabel("Vanilla Coffee");
		lblVanillaCoffee.setForeground(Color.WHITE);
		lblVanillaCoffee.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblVanillaCoffee.setBounds(314, 200, 104, 16);
		dripcoffeeMenu.add(lblVanillaCoffee);

		JLabel lblHazelnutCoffee = new JLabel("Hazelnut Coffee");
		lblHazelnutCoffee.setForeground(Color.WHITE);
		lblHazelnutCoffee.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblHazelnutCoffee.setBounds(301, 388, 122, 16);
		dripcoffeeMenu.add(lblHazelnutCoffee);

		JLabel lblDripCoffee = new JLabel("DripCoffee");
		lblDripCoffee.setForeground(Color.WHITE);
		lblDripCoffee.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDripCoffee.setBounds(10, 11, 104, 16);
		dripcoffeeMenu.add(lblDripCoffee);

		JPanel frappuccinoMenu = new JPanel();
		frappuccinoMenu.setBackground(new Color(128, 0, 0));
		categoryContainer.add(frappuccinoMenu, "Frappuccino");
		frappuccinoMenu.setLayout(null);

		JButton btnChocolateFrap = new JButton(new ImageIcon(buttonIcon11));
		btnChocolateFrap.setText("Chocolate Frap");
		btnChocolateFrap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				addOrderItem(b.getText());
			}
		});
		btnChocolateFrap.setBounds(56, 79, 180, 120);
		frappuccinoMenu.add(btnChocolateFrap);

		JButton btnVanillaFrap = new JButton(new ImageIcon(buttonIcon12));
		btnVanillaFrap.setText("Vanilla Frap");
		btnVanillaFrap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				addOrderItem(b.getText());
			}
		});
		btnVanillaFrap.setBounds(270, 79, 180, 120);
		frappuccinoMenu.add(btnVanillaFrap);

		JButton btnCaramelFrap = new JButton(new ImageIcon(buttonIcon13));
		btnCaramelFrap.setText("Caramel Frap");
		btnCaramelFrap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				addOrderItem(b.getText());
			}
		});
		btnCaramelFrap.setBounds(56, 270, 180, 120);
		frappuccinoMenu.add(btnCaramelFrap);

		JButton btnMintFrap = new JButton(new ImageIcon(buttonIcon19));
		btnMintFrap.setText("Mint Frap");
		btnMintFrap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				addOrderItem(b.getText());
			}
		});
		btnMintFrap.setBounds(270, 270, 180, 120);
		frappuccinoMenu.add(btnMintFrap);

		JLabel lblCaramelFrappuccino = new JLabel("Caramel Frappuccino");
		lblCaramelFrappuccino.setForeground(Color.WHITE);
		lblCaramelFrappuccino
				.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblCaramelFrappuccino.setBounds(73, 388, 163, 16);
		frappuccinoMenu.add(lblCaramelFrappuccino);

		JLabel lblChocolateFrappuccino = new JLabel("Chocolate Frappuccino");
		lblChocolateFrappuccino.setForeground(Color.WHITE);
		lblChocolateFrappuccino.setFont(new Font("Lucida Grande", Font.PLAIN,
				15));
		lblChocolateFrappuccino.setBounds(66, 198, 163, 16);
		frappuccinoMenu.add(lblChocolateFrappuccino);

		JLabel lblVanillaFrappuccino = new JLabel("Vanilla Frappuccino");
		lblVanillaFrappuccino.setForeground(Color.WHITE);
		lblVanillaFrappuccino
				.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblVanillaFrappuccino.setBounds(290, 199, 153, 16);
		frappuccinoMenu.add(lblVanillaFrappuccino);

		JLabel lblMintFrappuccino = new JLabel("Mint Frappuccino");
		lblMintFrappuccino.setForeground(Color.WHITE);
		lblMintFrappuccino.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblMintFrappuccino.setBounds(300, 388, 130, 16);
		frappuccinoMenu.add(lblMintFrappuccino);

		JLabel lblFrappuccino = new JLabel("Frappuccino");
		lblFrappuccino.setForeground(Color.WHITE);
		lblFrappuccino.setFont(new Font("Dialog", Font.BOLD, 18));
		lblFrappuccino.setBounds(10, 11, 130, 16);
		frappuccinoMenu.add(lblFrappuccino);

		JPanel latteMenu = new JPanel();
		latteMenu.setBackground(new Color(128, 0, 0));
		categoryContainer.add(latteMenu, "Latte");

		JButton btnHazelnutLatte = new JButton(new ImageIcon(buttonIcon14));
		btnHazelnutLatte.setText("Hazelnut Latte");
		btnHazelnutLatte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				addOrderItem(b.getText());
			}
		});
		latteMenu.setLayout(null);
		btnHazelnutLatte.setBounds(56, 79, 180, 120);
		latteMenu.add(btnHazelnutLatte);

		JButton btnMochaLatte = new JButton(new ImageIcon(buttonIcon15));
		btnMochaLatte.setText("Mocha");
		btnMochaLatte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				addOrderItem(b.getText());
			}
		});
		btnMochaLatte.setBounds(270, 79, 180, 120);
		latteMenu.add(btnMochaLatte);

		JButton btnVanillaLatte = new JButton(new ImageIcon(buttonIcon16));
		btnVanillaLatte.setText("Vanilla Latte");
		btnVanillaLatte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				addOrderItem(b.getText());
			}
		});
		btnVanillaLatte.setBounds(56, 270, 180, 120);
		latteMenu.add(btnVanillaLatte);

		JButton btnCaramelLatte = new JButton(new ImageIcon(buttonIcon20));
		btnCaramelLatte.setText("Caramel Latte");
		btnCaramelLatte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				addOrderItem(b.getText());
			}
		});
		btnCaramelLatte.setBounds(270, 270, 180, 120);
		latteMenu.add(btnCaramelLatte);

		JLabel lblVanillaLatte = new JLabel("Vanilla Latte");
		lblVanillaLatte.setForeground(Color.WHITE);
		lblVanillaLatte.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblVanillaLatte.setBounds(97, 388, 104, 16);
		latteMenu.add(lblVanillaLatte);

		JLabel lblHazelnutLatte = new JLabel("Hazelnut Latte");
		lblHazelnutLatte.setForeground(Color.WHITE);
		lblHazelnutLatte.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblHazelnutLatte.setBounds(89, 197, 126, 16);
		latteMenu.add(lblHazelnutLatte);

		JLabel lblMochaLatte = new JLabel("Mocha Latte");
		lblMochaLatte.setForeground(Color.WHITE);
		lblMochaLatte.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblMochaLatte.setBounds(316, 198, 112, 16);
		latteMenu.add(lblMochaLatte);

		JLabel lblCaramelLatte = new JLabel("Caramel Latte");
		lblCaramelLatte.setForeground(Color.WHITE);
		lblCaramelLatte.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblCaramelLatte.setBounds(304, 388, 112, 16);
		latteMenu.add(lblCaramelLatte);

		JLabel lblLattee = new JLabel("Lattee");
		lblLattee.setForeground(Color.WHITE);
		lblLattee.setFont(new Font("Dialog", Font.BOLD, 18));
		lblLattee.setBounds(10, 11, 104, 16);
		latteMenu.add(lblLattee);

		JLabel lblLatte = new JLabel("Lattes");
		lblLatte.setForeground(new Color(139, 0, 0));
		lblLatte.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblLatte.setBounds(71, 448, 47, 16);
		pnlMenu.add(lblLatte);

		JLabel lblFrappuccinos = new JLabel("Frappuccinos");
		lblFrappuccinos.setForeground(new Color(139, 0, 0));
		lblFrappuccinos.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblFrappuccinos.setBounds(42, 304, 104, 16);
		pnlMenu.add(lblFrappuccinos);

		JLabel lblDripCoffees = new JLabel("DripCoffees");
		lblDripCoffees.setForeground(new Color(139, 0, 0));
		lblDripCoffees.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblDripCoffees.setBounds(42, 154, 104, 16);
		pnlMenu.add(lblDripCoffees);

		JLabel lblTeas = new JLabel("Teas");
		lblTeas.setForeground(new Color(139, 0, 0));
		lblTeas.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTeas.setBounds(71, 8, 36, 16);
		pnlMenu.add(lblTeas);

		btnTea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cmenu.show(categoryContainer, "Tea");
			}
		});

		btnDripCoffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmenu.show(categoryContainer, "DripCoffee");
			}
		});
		btnFrappuccino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmenu.show(categoryContainer, "Frappuccino");
			}
		});
		btnLatte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmenu.show(categoryContainer, "Latte");
			}
		});
		if (Desktop.isDesktopSupported())
			desktop = Desktop.getDesktop();
		// btnBlackTea.setBounds(26, 30, 117, 105);
		// pnlMenu.add(btnBlackTea);

		JButton btnChai = new JButton("Chai");
		btnChai.setBounds(210, 184, 105, 68);

		JButton btnCancel = new JButton("Cancel Order");
		// btnCancel.setIcon(new
		// ImageIcon(ImageIO.read(this.getClass().getResource("Tea.png"))));
		btnCancel.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearOrder();
				orders.remove(orders.size() - 1);
			}
		});
		btnCancel.setBounds(95, 612, 123, 49);
		contentPane.add(btnCancel);

		// edit item quantity button
		JButton btnEdit = new JButton("Edit Amount");
		btnEdit.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sel = itemlist.getSelectedIndex();
				currentOrder = orders.get(orders.size() - 1);
				OrderItem curroi = (OrderItem) itemlist.getSelectedValue();

				if (sel > -1) {
					int newSizeX = 300;
					int newSizeY = 400;
					Font font = new Font("SanaSerif", Font.PLAIN, 25);
					JFrame fAmount = new JFrame();
					JTextField tfamount = new JTextField("        "
							+ curroi.quantity);
					tfamount.setFont(font);
					tfamount.setPreferredSize(new Dimension(40, 50));
					tfamount.setHorizontalAlignment(SwingConstants.RIGHT);
					JPanel pbuttons = new JPanel();
					JButton btnIncrease = new JButton("Increase");
					btnIncrease.setSize(newSizeX, newSizeY);
					btnIncrease.setFont(font);
					JButton btnDecrease = new JButton("Decrease");
					btnDecrease.setSize(newSizeX, newSizeY);
					btnDecrease.setFont(font);
					JButton btnUpdate = new JButton("Update");
					btnUpdate.setSize(newSizeX, newSizeY);
					btnUpdate.setFont(font);
					pbuttons.add(btnIncrease);
					pbuttons.add(tfamount);
					pbuttons.add(btnDecrease);
					JLabel avgLabel = new JLabel();
					fAmount.setSize(500, 200);
					fAmount.getContentPane().add(btnUpdate, BorderLayout.SOUTH);
					fAmount.getContentPane().add(pbuttons, BorderLayout.CENTER);
					fAmount.setVisible(true);

				}
			}
		});
		btnEdit.setBounds(266, 470, 112, 33);
		contentPane.add(btnEdit);

		btnOverridePrice = new JButton("Override Price");
		btnOverridePrice.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnOverridePrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int sel = itemlist.getSelectedIndex();
				if (sel > -1) {
					if (!isManager) {
						int dialogResult = JOptionPane
								.showConfirmDialog(null,
										"Access denied. You are not a manager. \n"
										+ "Do you want to log out and re-login as a manager?");
						if (dialogResult == JOptionPane.YES_OPTION) {
							EmployeeLogin1 emapp = new EmployeeLogin1();
							emapp.coffeeapp=CoffeePOS1.this;
							emapp.isOveriding = true;
							emapp.items = currentOrder.orderitems;
							emapp.sel = sel;
							emapp.loginFrame.setVisible(true);
							
						}
					} else {
						System.out.println("before itemlist");
						OrderItem1 curroi = (OrderItem1) itemlist
								.getSelectedValue();
						System.out.println("after itemlist");
						String newprice$ = showInputDialog("Please enter the new price");
						curroi.Unitprice = new BigDecimal(newprice$);
						oidata.remove(sel);
						oidata.insertElementAt(curroi, sel);
						updateitemlabel(currentOrder);
					}

				} else {
					JOptionPane
							.showMessageDialog(null, "Please select an item");
				}

			}
			// }
		});
		btnOverridePrice.setBounds(132, 470, 112, 33);
		contentPane.add(btnOverridePrice);

		JButton btnSubmitOrder = new JButton("Submit Order");
		btnSubmitOrder.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnSubmitOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentOrder!=null) {					
					c1.show(pnlContainer, "Checkout");
					updateitemlabel(currentOrder);
				} else {

				}
			}
		});
		btnSubmitOrder.setBounds(254, 612, 126, 49);
		contentPane.add(btnSubmitOrder);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, 369, 339);
		contentPane.add(scrollPane);
		itemlist.setForeground(new Color(0, 0, 0));
		itemlist.setFont(new Font("Courier New", Font.BOLD, 14));

		scrollPane.setViewportView(itemlist);

		lblSubTotal = new JLabel("$0.00");
		lblSubTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblSubTotal.setBounds(306, 544, 74, 14);
		contentPane.add(lblSubTotal);

		JLabel lblCustomer = new JLabel("Quantity");
		lblCustomer.setForeground(new Color(0, 0, 0));
		lblCustomer.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblCustomer.setBounds(95, 102, 69, 15);
		contentPane.add(lblCustomer);

		JLabel lblOrder = new JLabel("Name");
		lblOrder.setForeground(new Color(0, 0, 0));
		lblOrder.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblOrder.setBounds(10, 101, 61, 16);
		contentPane.add(lblOrder);

		JLabel lblUnitprice = new JLabel("Unit Price");
		lblUnitprice.setForeground(Color.BLACK);
		lblUnitprice.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblUnitprice.setBounds(212, 102, 83, 14);
		contentPane.add(lblUnitprice);

		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setForeground(Color.BLACK);
		lblTotal_1.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblTotal_1.setBounds(332, 102, 46, 14);
		contentPane.add(lblTotal_1);

		JLabel label = new JLabel("Sub Total:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label.setBounds(182, 545, 74, 14);
		contentPane.add(label);

		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(20, 470, 89, 33);
		btnRemove.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int s = itemlist.getSelectedIndex();
				currentOrder = orders.get(orders.size() - 1);
				if (s != -1) {
					currentOrder.orderitems.remove(s);
					oidata.remove(s);
					updateitemlabel(currentOrder);

				}

			}

		});

		contentPane.add(btnRemove);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(210, 180, 140));
		panel.setBounds(0, 92, 386, 582);
		contentPane.add(panel);




	}

	private String showInputDialog(String message) {
		String inputValue = JOptionPane
				.showInputDialog(message);
		// if not valid, showInputDialog Method would re-run until
		if (!inputValue.matches("[0-9.]*")) {

			inputValue = showInputDialog(message);
		}

		return inputValue;
	}

	
	public void addOrderItem(String itemname) {
		OrderItem1 addedOrderItem;
		String unitprice$ ="";
		boolean isfound=false;	


		if(connection==null){
			connection = sqliteConnection.dbConnector();
		}
		try {
			String query = "Select UnitPrice from Item where name=?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, itemname);
			ResultSet rs = pst.executeQuery();
	
			while(rs.next()){
				isfound=true;
				unitprice$ = rs.getString("UnitPrice");			
				
			}
			if(isfound){
				addedOrderItem = new OrderItem1(itemname,new BigDecimal(unitprice$));
				if (isOrderEmpty) {
					currentOrder = new Order1();
					orders.add(currentOrder);
					isOrderEmpty = false;
					currentOrder.orderitems.add(addedOrderItem);
					oidata.addElement(addedOrderItem);
					System.out.println(currentOrder.getSubtotal());
					updateitemlabel(currentOrder);

				} else {
					System.out.println("not empty");
					boolean isrepeat = false;
					int position = 0;
					for (int i = 0; i < currentOrder.orderitems.size(); ++i) {
						if (currentOrder.orderitems.get(i).name == itemname) {
							isrepeat = true;
							position = i;
							break;
						}
					}

					if (isrepeat) {				
						OrderItem1 selectedoi = currentOrder.orderitems.get(position);
						selectedoi.quantity=selectedoi.quantity.add(new BigDecimal("1"));
						System.out.println(selectedoi.quantity.toString());
						oidata.remove(position);
						oidata.insertElementAt(selectedoi, position);		
						updateitemlabel(currentOrder);
					} else {
						currentOrder.orderitems.add(addedOrderItem);
						oidata.addElement(addedOrderItem);
						updateitemlabel(currentOrder);
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Sorry, not found item. Please contact IT helping team");
			}
			
			rs.close();
			pst.close();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		

	}

	private void drawLine() {
		ta.append("------------------------------------------------------------\n");
	}

	public void updateitemlabel(Order1 currOrder) {
		
		System.out.println("updateitemlabel runned");		
		lblSubTotal.setText("$" + currentOrder.getSubtotal());
		txtItems.setText(nfcurrency.format(currentOrder.getSubtotal()));
		txtCoupon.setText(nfcurrency.format(currentOrder.getDiscount()));
		txtBeforeTax.setText(nfcurrency.format(currentOrder.getBeforeTax()));
		txtTax.setText(nfcurrency.format(currentOrder.getTax()));
		lblTotalAmount.setText(nfcurrency.format(currentOrder.getTotal()));
		
	}
	
	public void insertOrder(Order1 currOrder) {		
		
		if(connection==null){
			connection = sqliteConnection.dbConnector();
		}
		
		
		try {
			Statement stm =connection.createStatement();
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String datetime = df.format(date);
			String insertorderquery ="INSERT INTO OrderInfo(Employee_id,Order_Date) VALUES("
					+"'"+currentemployeeid+"',"
					+"'"+datetime+"'"
					+");";
			stm.executeUpdate(insertorderquery);
			System.out.println("Insert order successfully");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	private void clearOrder() {
		oidata.removeAllElements();
		isOrderEmpty = true;
		currentOrder=null;
		customer=null;
		txtItems.setText("");
		txtCoupon.setText("");
		txtBeforeTax.setText("");
		txtTax.setText("");
		lblTotalAmount.setText("$0.00");
		lblSubTotal.setText("$0.00");
		c1.show(pnlContainer, "Menu");

	}
	

	private void triggerReceipt() {
		
		if(customer!=null){
			System.out.println("not null");
			connection = sqliteConnection.dbConnector();
			boolean isFound = false;
			double newpoints=0.0;
			try {
				
				String querysearch = "Select * from Customer where GiftCard=?";
				PreparedStatement pst = connection.prepareStatement(querysearch);
				pst.setString(1,customer.GiftCardID);
				ResultSet rs = pst.executeQuery();
				System.out.println("execute querysearch");
				while(rs.next()){	
					isFound = true;
					System.out.println(" "+rs.getDouble("Points"));
					newpoints = rs.getDouble("Points")+currentOrder.getSubtotal().doubleValue()*100.0;			
				}
				if(isFound){
						
					String queryupdate = "Update Customer Set Points=? where GiftCard=?";
					PreparedStatement pstupdate = connection.prepareStatement(queryupdate);
					pstupdate.setDouble(1,newpoints);
					pstupdate.setString(2, customer.GiftCardID);
					pstupdate.executeUpdate();					
					
					
				}
				
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		// generate on screen receipt:
		final ReceiptsPanel p = new ReceiptsPanel();
		JFrame f = new JFrame();
		JButton print = new JButton("Print");
		JButton emailrec = new JButton("Email");
		JPanel widgets = new JPanel();
		f.setSize(new Dimension(260, 390 + 20 * orders.size()));
		f.getContentPane().add(p);
		f.setTitle("Receipt");
		f.setVisible(true);
		f.getContentPane().add(p, BorderLayout.CENTER);
		f.getContentPane().add(widgets, BorderLayout.SOUTH);
		widgets.add(emailrec);
		widgets.add(print);
		
		print.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable(p);
				if (job.printDialog()) {
					try {
						job.print();
					} catch (PrinterException x_x) {
						System.out.println("Error printing: " + x_x);
					}
				}
				clearOrder();
			}
		});

		emailrec.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
				ta.append("Total Amount Due: " + nfcurrency.format(currentOrder.getTotal()));
				String report$ = "";
				report$ += "Thank you for shopping at iCoffeeShop. We really appreciate your business. \n";
				report$ += "Your order total is $" + nfcurrency.format(currentOrder.getTotal()) 
						+ ".\n";

				String mailto = "John_Carlson@Baylor.edu?SUBJECT=Reciept [iCoffee Shoppe]&BODY="
						+ report$;

				URI uri = null;

				try {
					uri = new URI("mailto", mailto, null);
					try {
						desktop.mail(uri);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (URISyntaxException e1) {
					e1.printStackTrace();

				}
				
				clearOrder();
			}



		});
	}

	@SuppressWarnings("serial")
	class ReceiptsPanel extends JPanel implements Printable {

		// Declaring the font of the Receipt and the Logo Image for the Receipt
		Font f = new Font("Helvetica", Font.PLAIN, 10);
//		Image receiptIMG = Toolkit.getDefaultToolkit().getImage("logo.png");
//		ImageIcon receiptIMGICON = new ImageIcon(receiptIMG.getScaledInstance(
//				115, 60, Image.SCALE_SMOOTH));

		// Contains the code to draw the receipt
		public void paintComponent(Graphics graphics) {
			super.paintComponent(graphics);

			this.setLayout(null);

			Graphics2D g2d = (Graphics2D) graphics;
			RenderingHints hints = new RenderingHints(
					RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
			g2d.setRenderingHints(hints);

					
			JButton btnLogo = new JButton(new ImageIcon(buttonIcon21));
			btnLogo.setBounds(65, 10, 115, 60);
			btnLogo.setEnabled(false);
			btnLogo.setBackground(Color.WHITE);
			add(btnLogo);

			// date/time stamp:

			Calendar cal = new GregorianCalendar();
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("Helvetica", Font.PLAIN, 12));
			g2d.drawString(" iCoffee Shoppe Reciept", 58, 95);
			g2d.drawString(
					(cal.get(Calendar.MONTH) + 1) + "/"
							+ cal.get(Calendar.DAY_OF_MONTH) + "/"
							+ cal.get(Calendar.YEAR) + "  "
							+ cal.get(Calendar.HOUR_OF_DAY) + ":"
							+ cal.get(Calendar.MINUTE) + " "
							+ (cal.get(Calendar.AM_PM) == 0 ? "AM" : "PM"), 66,
					110);

		
			// Show the list of items ordered
			
			for (int i = 0; i < currentOrder.orderitems.size(); i++) {
				String itemname =currentOrder.orderitems.get(i).name;
				
				g2d.drawString(itemname+"\t"+nfcurrency.format(currentOrder.orderitems.get(i).Unitprice), 60, 135 + 15 * i);

			}

		     // Displaying the total:
            g2d.setFont(new Font("Helvetica", Font.PLAIN, 12));
            g2d.drawString("Subtotal:" + currentOrder.getSubtotal(),74, getHeight()-60);
            g2d.drawString("Discount:" + currentOrder.getDiscount(),  74, getHeight()-50);
            g2d.drawString("Total: " + currentOrder.getTotal(), 74, getHeight()-35);

			// slogan:
			g2d.setFont(f.deriveFont(Font.ITALIC, 15));
			g2d.drawString("Have a blessed day!", 47, getHeight() - 20);

		}

		// Implementing printable:

		@Override
		public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
				throws PrinterException {
			if (pageIndex > 0) {
				return (NO_SUCH_PAGE);
			} else {
				Graphics2D g2d = (Graphics2D) graphics;
				g2d.translate(pageFormat.getImageableX(),
						pageFormat.getImageableY());
				paint(g2d);
				return (PAGE_EXISTS);
			}
		}

	}
}
