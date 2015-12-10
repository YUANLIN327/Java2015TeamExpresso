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
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CoffeePOS extends JFrame {

	static CoffeePOS frame;
	private JPanel contentPane;
	BufferedImage buttonIcon1 = ImageIO.read(new File("Tea.png"));	
	BufferedImage buttonIcon2 = ImageIO.read(new File("Latte.png"));
	BufferedImage buttonIcon3 = ImageIO.read(new File("DripCoffee.png"));
	BufferedImage buttonIcon4 = ImageIO.read(new File("Frappuccino.png"));
	BufferedImage buttonIcon5 = ImageIO.read(new File("BlackTea.png"));
	BufferedImage buttonIcon6 = ImageIO.read(new File("ChaiTea.png"));
	BufferedImage buttonIcon7 = ImageIO.read(new File("HerbalTea.png"));
	BufferedImage buttonIcon8 = ImageIO.read(new File("RegularCoffee.png"));
	BufferedImage buttonIcon9 = ImageIO.read(new File("VanillaCoffee.png"));
	BufferedImage buttonIcon10 = ImageIO.read(new File("PumpkinCoffee.png"));
	//Button set 2./*
	BufferedImage buttonIcon11 = ImageIO.read(new File("ChocolateFrap.png"));
	BufferedImage buttonIcon12 = ImageIO.read(new File("VanillaFrap.png"));
	BufferedImage buttonIcon13 = ImageIO.read(new File("CaramelFrap.png"));
	BufferedImage buttonIcon14 = ImageIO.read(new File("HazelnutLatte.png"));
	BufferedImage buttonIcon15 = ImageIO.read(new File("MochaLatte.png"));
	BufferedImage buttonIcon16 = ImageIO.read(new File("VanillaLatte.png"));
	BufferedImage buttonIcon17 = ImageIO.read(new File("RooibosTea.png")); //rooibos tea
	BufferedImage buttonIcon18 = ImageIO.read(new File("HazelnutCoffee.png")); //hazelnut coffee
	BufferedImage buttonIcon19 = ImageIO.read(new File("MintFrap.png")); //mint frap
	BufferedImage buttonIcon20 = ImageIO.read(new File("CaramelLatte.png")); //caramel latte
	BufferedImage buttonIcon21 = ImageIO.read(new File("logo.png")); //logi
	BufferedImage btnIconCash = ImageIO.read(new File("Cash.png")); 
	BufferedImage btnIconCheck = ImageIO.read(new File("Check.png")); 
	BufferedImage btnIconCoupon = ImageIO.read(new File("Coupon.png"));
	BufferedImage btnIconCredit = ImageIO.read(new File("Credit.png")); 
	BufferedImage btnIconGiftCard = ImageIO.read(new File("GiftCard.png")); 
	BufferedImage btnIconBack = ImageIO.read(new File("BackIcon.png")); 
	
	
	//initate card layout field instance
	CardLayout c1 = new CardLayout();
	CardLayout cmenu = new CardLayout();
	DefaultListModel<OrderItem> oidata = new DefaultListModel();	
	JList itemlist = new JList(oidata);
	
	
	private JTextField txtAmountDue;
	private JTextField txtChange;
	boolean isOrderEmpty = true;
	boolean isManager = false;
	
	JLabel lblDiscount;
	JLabel lblSubTotal;
	JLabel lblTax;
	JLabel lblTotal;
	JLabel lblWelcome;
    JFrame Receipt = new JFrame();
    JPanel categoryContainer;
    JPanel pnlContainer;
    Desktop desktop = null;
	ArrayList<Order> orders = new ArrayList<Order>();
//	ArrayList<Item> items= new ArrayList<Item>();
	HashMap<String, Double> items = new HashMap<String, Double>();
	HashMap<String, Double> couponcode = new HashMap<String, Double>();
	private JTextField txtAmountTendered;
    JTextArea ta = new JTextArea();
    String CashAmt;
    String CurrentEmployee="";
    private JTextField txtSearch;

    
    Connection connection = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new CoffeePOS();
					frame.setVisible(true);
					frame.setSize(1100,730);
					frame.setTitle("iCoffee Shop");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	

	public CoffeePOS() throws IOException {
		connection  = sqliteConnection.dbConnector();
	
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel pnlRibbon = new JPanel();
		pnlRibbon.setBounds(0, 14, 1090, 77);
		pnlRibbon.setLayout(null);
		pnlRibbon.setBackground(new Color(128, 0, 0));
		contentPane.add(pnlRibbon);
		
		
		
		JRadioButton rdbDinein = new JRadioButton("Dine In");
		rdbDinein.setForeground(Color.WHITE);
		rdbDinein.setBounds(573, 13, 97, 50);
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
		
		JButton btnNewButton = new JButton("Log out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeLogin emp = new EmployeeLogin();				
				emp.loginFrame.setVisible(true);
				frame.dispose();
				
			}
		});
		btnNewButton.setBounds(216, 41, 144, 29);
		pnlRibbon.add(btnNewButton);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(801, 24, 183, 33);
		pnlRibbon.add(txtSearch);
		txtSearch.setColumns(10);
		
		lblWelcome = new JLabel("Welcome");
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setFont(new Font("Dialog", Font.BOLD, 18));
		lblWelcome.setBounds(206, 13, 201, 17);
		pnlRibbon.add(lblWelcome);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search$ = txtSearch.getText();
				String category$="";
				int counter=0;
				connection  = sqliteConnection.dbConnector();
				try {
					String query = "Select * from Item where name=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, search$);
					ResultSet rs=pst.executeQuery();
					while(rs.next()){
						counter++;
						category$=rs.getString("Category");
						System.out.println("run");
					}
					if(counter>=1){
						JOptionPane.showMessageDialog(null,"Item  found");
						cmenu.show(categoryContainer,category$);
					}
					else {
						JOptionPane.showMessageDialog(null,"Item not found, sorry");
					}
					
					rs.close();
					pst.close();
					connection.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnSearch.setBounds(994, 23, 86, 34);
		pnlRibbon.add(btnSearch);
		
		JButton btnLogo = new JButton (new ImageIcon(buttonIcon21));
		btnLogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogo.setBackground(new Color(128, 0, 0));
		btnLogo.setBounds(0, 0, 137, 77);
		pnlRibbon.add(btnLogo);
		
		
		
		//add container panel for menu and check out		
		pnlContainer = new JPanel();
		pnlContainer.setBackground(new Color(100, 149, 237));
		pnlContainer.setBounds(390, 90, 700, 589);
		//apply the card layout 
		pnlContainer.setLayout(c1);
		contentPane.add(pnlContainer);
		
		//add menu panel
		JPanel pnlMenu = new JPanel();
		pnlMenu.setLayout(null);
		pnlMenu.setBackground(new Color(210, 180, 140));	
		//add menu panel to container panel
		pnlContainer.add(pnlMenu,"Menu");		
		
		//add checkout panel
		JPanel pnlCheckout = new JPanel();
		pnlCheckout.setLayout(null);
		pnlCheckout.setBackground(new Color(210, 180, 140));
		
		//add checkout panel to container panel
		pnlContainer.add(pnlCheckout, "Checkout");
		
		txtAmountDue = new JTextField();
		txtAmountDue.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtAmountDue.setEnabled(false);
		txtAmountDue.setBounds(242, 39, 172, 56);
		pnlCheckout.add(txtAmountDue);
		txtAmountDue.setColumns(10);
		txtAmountDue.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtAmountTendered = new JTextField();	
		txtAmountTendered.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtAmountTendered.setEnabled(false);
		txtAmountTendered.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAmountTendered.setColumns(10);
		txtAmountTendered.setBounds(242, 155, 172, 56);
		pnlCheckout.add(txtAmountTendered);
		
		txtChange = new JTextField();	
		txtChange.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtChange.setEnabled(false);
		txtChange.setHorizontalAlignment(SwingConstants.RIGHT);
		txtChange.setColumns(10);
		txtChange.setBounds(242, 270, 172, 56);
		pnlCheckout.add(txtChange);
		

		
	
 		//Cashbutton
		JButton btnCash = new JButton(new ImageIcon(btnIconCash));		
		btnCash.setText("Cash");
		btnCash.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnCash.setBounds(472, 148, 129, 63);
		btnCash.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
				BigDecimal totalbd=new BigDecimal(txtAmountDue.getText());
				String change$="0.00";
				BigDecimal tenderedbd = new BigDecimal(change$);
				while (totalbd.compareTo(tenderedbd)==1){
						change$=showInputDialog();
						tenderedbd = new BigDecimal(change$);					
		    	}
				
				BigDecimal changebd = tenderedbd.subtract(totalbd);
				changebd = changebd.setScale(2,BigDecimal.ROUND_HALF_EVEN);
				txtAmountTendered.setText(tenderedbd.toString());
				txtChange.setText(changebd.toString());
				triggerReceipt();               
                ta.append("Total Amount Due: " + txtAmountDue.getText() + "\n"+ "Amount Tendered: " + txtAmountTendered.getText() + "\n"+
                 "Change: " + txtChange.getText());
                clearOrder();

		    }

});
		
		pnlCheckout.add(btnCash);
		
		//check button
		JButton btnCheck = new JButton(new ImageIcon(btnIconCheck));		
		btnCheck.setText("Check");
		btnCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnCheck.setBounds(472, 253, 129, 63);
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame checkFrame= new JFrame();
				checkFrame.setTitle("Check");
				checkFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				btnSubmit.addActionListener(new ActionListener(){
	                 @Override
	                 public void actionPerformed(ActionEvent e) {
	                	 triggerReceipt();
	                	 ta.append("This is for credit card");
	                	 clearOrder();
	                 }
				});
				
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setBounds(194, 306, 89, 36);
				checkContentPane.add(btnCancel);
			}
		});		
			

		pnlCheckout.add(btnCheck);
		
		JButton btnCoupon = new JButton(new ImageIcon(btnIconCoupon));		
		btnCoupon.setText("Coupon");
		btnCoupon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input$ = JOptionPane.showInputDialog("Please enter a valid coupon code value");
				boolean isvalid =couponcode.containsKey(input$);
				if (isvalid){
					NumberFormat nf2 = NumberFormat.getPercentInstance(Locale.US);
					Order currOrder = orders.get(orders.size()-1);
					double discount =  couponcode.get(input$);
					currOrder.coupon = discount;
					updateitemlabel(currOrder);
					txtAmountDue.setText(""+currOrder.getTotal());
					JOptionPane.showMessageDialog(null, "Congratulations! A "+nf2.format(discount)+ " dicount has been applied!");
				}
				else{
					JOptionPane.showMessageDialog(null, "Sorry.That's not a valid coupon.");
				}
			}
		});
		btnCoupon.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnCoupon.setBounds(472, 460, 129, 63);
		pnlCheckout.add(btnCoupon);
		

		



		//giftcard button
		JButton btnGiftCard = new JButton(new ImageIcon(btnIconGiftCard));		
		btnGiftCard.setText("Gift Card");
		btnGiftCard.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnGiftCard.setBounds(472, 352, 129, 63);
		pnlCheckout.add(btnGiftCard);
		
		
		//creditcard button
		JButton btnCreditdebitCard = new JButton(new ImageIcon(btnIconCredit));		
		btnCreditdebitCard.setText("Credit/Debit");		
		btnCreditdebitCard.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnCreditdebitCard.setBounds(472, 37, 129, 63);
		btnCreditdebitCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame creditFrame= new JFrame();
				creditFrame.setTitle("Credit/Debit");
				creditFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
						"June","July","Aug","Sep","Oct","Nov","Dec"};
				JComboBox cbxMonth = new JComboBox(monthStrings);
				cbxMonth.setSelectedIndex(11);
				cbxMonth.setMaximumRowCount(12);
				cbxMonth.setBounds(35, 188, 72, 32);
				creditContentPane.add(cbxMonth);
				
				String[] yearStrings = {"2015","2016","2017","2018","2019","2020","2021","2022","2023","2024"};
				JComboBox cbxYear = new JComboBox(yearStrings);
				cbxYear.setMaximumRowCount(20);
				cbxYear.setBounds(133, 188, 72, 32);
				creditContentPane.add(cbxYear);
				
				JButton btnSubmit = new JButton("Submit");
				btnSubmit.setBounds(72, 306, 89, 36);
				creditContentPane.add(btnSubmit);
				btnSubmit.addActionListener(new ActionListener(){
	                 @Override
	                 public void actionPerformed(ActionEvent e) {
	                	 triggerReceipt();
	                	 ta.append("This is for credit card");
	                	 clearOrder();
	                 }
				});
				
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setBounds(194, 306, 89, 36);
				creditContentPane.add(btnCancel);
			}
		});		
		pnlCheckout.add(btnCreditdebitCard);
		
		
		//back button
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
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Amount Due: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(72, 37, 143, 63);
		pnlCheckout.add(lblNewLabel_1);
		
		JLabel lblChange = new JLabel("Change: ");
		lblChange.setHorizontalAlignment(SwingConstants.RIGHT);
		lblChange.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChange.setBounds(114, 270, 101, 46);
		pnlCheckout.add(lblChange);
		
		JLabel label_1 = new JLabel("Amount Tendered: ");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_1.setBounds(72, 155, 159, 44);
		pnlCheckout.add(label_1);
		
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
		//		pnlMenu.add(btnChai);
				
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
						JButton b =(JButton) e.getSource();
						addOrderItem(b.getText());				
					}
				});
				btnBlackTea.setBounds(56, 79, 180, 120);
				teaMenu.add(btnBlackTea);
				
				JButton btnChaiTea = new JButton(new ImageIcon(buttonIcon6));
				btnChaiTea.setText("Chai Tea");
				btnChaiTea.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton b =(JButton) e.getSource();
						addOrderItem(b.getText());				
					}
				});
				btnChaiTea.setBounds(270, 79, 180, 120);
				teaMenu.add(btnChaiTea);
				
				JButton btnHerbalTea = new JButton(new ImageIcon(buttonIcon7));
				btnHerbalTea.setText("Herbal Tea");
				btnHerbalTea.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton b =(JButton) e.getSource();
						addOrderItem(b.getText());
	
					}
				});
				btnHerbalTea.setBounds(56, 270, 180, 120);
				teaMenu.add(btnHerbalTea);
				
				JButton btnRooibosTea = new JButton(new ImageIcon(buttonIcon17));
				btnRooibosTea.setText("Rooibos Tea");
				btnRooibosTea.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton b =(JButton) e.getSource();
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
				categoryContainer.add(dripcoffeeMenu,"DripCoffee");
				
				JButton btnRegularCoffee = new JButton(new ImageIcon(buttonIcon8));
				btnRegularCoffee.setText("Regular Coffee");
				btnRegularCoffee.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton b =(JButton) e.getSource();
						addOrderItem(b.getText());
					}
				});
				btnRegularCoffee.setBounds(56, 79, 180, 120);
				dripcoffeeMenu.add(btnRegularCoffee);
				
				
				JButton btnVanillaCoffee = new JButton(new ImageIcon(buttonIcon9));
				btnVanillaCoffee.setText("Vanilla Coffee");
				btnVanillaCoffee.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton b =(JButton) e.getSource();
						addOrderItem(b.getText());
					}
				});
				btnVanillaCoffee.setBounds(270, 79, 180, 120);
				dripcoffeeMenu.add(btnVanillaCoffee);
				
				JButton btnPumpkinCoffee = new JButton(new ImageIcon(buttonIcon10));
				btnPumpkinCoffee.setText("Pumpkin Coffee");
				btnPumpkinCoffee.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton b =(JButton) e.getSource();
						addOrderItem(b.getText());
					}
				});
				btnPumpkinCoffee.setBounds(56, 270, 180, 120);
				dripcoffeeMenu.add(btnPumpkinCoffee);
				
				JButton btnHazelnutCoffee = new JButton(new ImageIcon(buttonIcon18));
				btnHazelnutCoffee.setText("Hazelnut Coffee");
				btnHazelnutCoffee.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton b =(JButton) e.getSource();
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
						JButton b =(JButton) e.getSource();
						addOrderItem(b.getText());
					}
				});
				btnChocolateFrap.setBounds(56, 79, 180, 120);
				frappuccinoMenu.add(btnChocolateFrap);
				
				JButton btnVanillaFrap = new JButton(new ImageIcon(buttonIcon12));
				btnVanillaFrap.setText("Vanilla Frap");
				btnVanillaFrap.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton b =(JButton) e.getSource();
						addOrderItem(b.getText());
					}
				});
				btnVanillaFrap.setBounds(270, 79, 180, 120);
				frappuccinoMenu.add(btnVanillaFrap);
				
				JButton btnCaramelFrap = new JButton(new ImageIcon(buttonIcon13));
				btnCaramelFrap.setText("Caramel Frap");
				btnCaramelFrap.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton b =(JButton) e.getSource();
						addOrderItem(b.getText());
					}
				});
				btnCaramelFrap.setBounds(56, 270, 180, 120);
				frappuccinoMenu.add(btnCaramelFrap);
				
				JButton btnMintFrap = new JButton(new ImageIcon(buttonIcon19));
				btnMintFrap.setText("Mint Frap");
				btnMintFrap.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton b =(JButton) e.getSource();
						addOrderItem(b.getText());
					}
				});
				btnMintFrap.setBounds(270, 270, 180, 120);
				frappuccinoMenu.add(btnMintFrap);
				
				JLabel lblCaramelFrappuccino = new JLabel("Caramel Frappuccino");
				lblCaramelFrappuccino.setForeground(Color.WHITE);
				lblCaramelFrappuccino.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
				lblCaramelFrappuccino.setBounds(73, 388, 163, 16);
				frappuccinoMenu.add(lblCaramelFrappuccino);
				
				JLabel lblChocolateFrappuccino = new JLabel("Chocolate Frappuccino");
				lblChocolateFrappuccino.setForeground(Color.WHITE);
				lblChocolateFrappuccino.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
				lblChocolateFrappuccino.setBounds(66, 198, 163, 16);
				frappuccinoMenu.add(lblChocolateFrappuccino);
				
				JLabel lblVanillaFrappuccino = new JLabel("Vanilla Frappuccino");
				lblVanillaFrappuccino.setForeground(Color.WHITE);
				lblVanillaFrappuccino.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
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
						JButton b =(JButton) e.getSource();
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
						JButton b =(JButton) e.getSource();
						addOrderItem(b.getText());
					}
				});
				btnMochaLatte.setBounds(270, 79, 180, 120);
				latteMenu.add(btnMochaLatte);
				
				JButton btnVanillaLatte = new JButton(new ImageIcon(buttonIcon16));
				btnVanillaLatte.setText("Vanilla Latte");
				btnVanillaLatte.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton b =(JButton) e.getSource();
						addOrderItem(b.getText());
					}
				});
				btnVanillaLatte.setBounds(56, 270, 180, 120);
				latteMenu.add(btnVanillaLatte);
				
				JButton btnCaramelLatte = new JButton(new ImageIcon(buttonIcon20));
				btnCaramelLatte.setText("Caramel Latte");
				btnCaramelLatte.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton b =(JButton) e.getSource();
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
		if(Desktop.isDesktopSupported())desktop=Desktop.getDesktop();
//		btnBlackTea.setBounds(26, 30, 117, 105);
//		pnlMenu.add(btnBlackTea);
		
		JButton btnChai = new JButton("Chai");
		btnChai.setBounds(210, 184, 105, 68);
		
		
		JButton btnCancel = new JButton("Cancel Order");
//		btnCancel.setIcon(new ImageIcon(ImageIO.read(new File("Tea.png"))));
		btnCancel.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearOrder();	
				orders.remove(orders.size()-1);
			}
		});
		btnCancel.setBounds(95, 612, 123, 49);
		contentPane.add(btnCancel);
		
		
		//edit item quantity button
		JButton btnEdit = new JButton("Edit Amount");
		btnEdit.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sel = itemlist.getSelectedIndex();
				Order currorder = orders.get(orders.size()-1);
				OrderItem curroi = (OrderItem)itemlist.getSelectedValue();
				
				if (sel>-1){
					int newSizeX = 300;
					int newSizeY = 400;
					Font font = new Font("SanaSerif", Font.PLAIN,25);
					JFrame fAmount = new JFrame();
					JTextField tfamount = new JTextField("        "+curroi.quantity);
					tfamount.setFont(font);
					tfamount.setPreferredSize(new Dimension(40,50));
					tfamount.setHorizontalAlignment(SwingConstants.RIGHT);
					JPanel  pbuttons = new JPanel();
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
					fAmount.getContentPane().add(pbuttons,BorderLayout.CENTER);
					fAmount.setVisible(true);
					
					
						
				}
			}
		});
		btnEdit.setBounds(266, 470, 112, 33);
		contentPane.add(btnEdit);

		
		JButton btnOverridePrice = new JButton("Override Price");
		btnOverridePrice.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnOverridePrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if(isManager){
				int sel = itemlist.getSelectedIndex();
				if (sel>-1){
					Order currorder = orders.get(orders.size()-1);
					OrderItem curroi = (OrderItem)itemlist.getSelectedValue();
					String newprice$ = JOptionPane.showInputDialog("Please enter the new price");
					curroi.unitprice = Double.parseDouble(newprice$);
					oidata.remove(sel);
					oidata.insertElementAt(curroi, sel);	
					updateitemlabel(currorder);
							
				}
				else {
					JOptionPane.showMessageDialog(null, "Please select an item");
				}
			
			  }
//			}
		});
		btnOverridePrice.setBounds(132, 470, 112, 33);
		contentPane.add(btnOverridePrice);
		
		JButton btnSubmitOrder = new JButton("Submit Order");
		btnSubmitOrder.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnSubmitOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isOrderEmpty){
					c1.show(pnlContainer, "Checkout");
					Order currOrder = orders.get(orders.size()-1);
					txtAmountDue.setText(""+currOrder.getTotal());
				}
				else {
					
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
		
		JLabel labeldiscount = new JLabel("Discount:");
		labeldiscount.setHorizontalAlignment(SwingConstants.RIGHT);
		labeldiscount.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		labeldiscount.setBounds(21, 513, 74, 14);
		contentPane.add(labeldiscount);
		
		lblDiscount = new JLabel("$0.00");
		lblDiscount.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblDiscount.setBounds(144, 513, 74, 14);
		contentPane.add(lblDiscount);
		
		JLabel label_Toal = new JLabel("Total: ");
		label_Toal.setHorizontalAlignment(SwingConstants.RIGHT);
		label_Toal.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_Toal.setBounds(174, 538, 85, 14);
		contentPane.add(label_Toal);
		
		lblTotal = new JLabel("$0.00");
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTotal.setBounds(308, 538, 74, 14);
		contentPane.add(lblTotal);
		
		JLabel label_Tax = new JLabel("Tax: ");
		label_Tax.setHorizontalAlignment(SwingConstants.RIGHT);
		label_Tax.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_Tax.setBounds(164, 513, 85, 14);
		contentPane.add(label_Tax);
		
		lblSubTotal = new JLabel("$0.00");
		lblSubTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblSubTotal.setBounds(144, 537, 74, 14);
		contentPane.add(lblSubTotal);
				
		lblTax = new JLabel("$0.00");
		lblTax.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTax.setBounds(308, 513, 74, 14);
		contentPane.add(lblTax);
		
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
		label.setBounds(20, 538, 74, 14);
		contentPane.add(label);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(20, 470, 89, 33);
		btnRemove.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnRemove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int s = itemlist.getSelectedIndex();
				Order currOrder = orders.get(orders.size()-1);
				if(s != -1){
					currOrder.orderitems.remove(s);
					oidata.remove(s);
					updateitemlabel(currOrder);
					
				}
				
				
			}
			
		});
		
		contentPane.add(btnRemove);
		
		
		
		JPanel panel = new JPanel();		
		panel.setBackground(new Color(210, 180, 140));
		panel.setBounds(0, 92, 386, 582);
		contentPane.add(panel);
		
//		//Tea Prices
//		Item item1 = new Item("Black Tea","Tea",1.5d);
//		Item item2 = new Item("Black Tea","Tea",1.5d);
//		Item item3 = new Item("Chai Tea","Tea",1.75d);
//		Item item4 = new Item("Herbal Tea","Tea",1.25d);
//		Item item5 = new Item("Rooibos Tea","Tea", 1.75d);
//		//Coffee Prices
//		Item item6 = new Item("Regular Coffee","DripCoffee",1.25d);
//		Item item7 = new Item("Vanilla Coffee","DripCoffee",1.5d);
//		Item item8 = new Item("Pumpkin Coffee","DripCoffee",1.6d);
//		Item item9 = new Item("Hazelnut Coffee", "DripCoffee",1.7d);
//		//Frappuccino Prices
//		Item item10 = new Item("Chocolate Frap","Frappuccino",3.25d);
//		Item item11 = new Item("Vanilla Frap","Frappuccino",3.5d);
//		Item item12 = new Item("Caramel Frap","Frappuccino",3.6d);
//		Item item13 = new Item("Mint Frap","Frappuccino",3.7d);
//		//Latte Price
//		Item item14 = new Item("Hazelnut Latte","Latte",3.25d);
//		Item item15 = new Item("Mocha","Latte",3.5d);
//		Item item16 = new Item("Vanilla Latte","Latte",3.6d);
//		Item item17= new Item("Caramel Latte","Latte",3.6d);
//		
        items.put("Black Tea",1.5d);
        items.put("Chai Tea",1.75d);
        items.put("Herbal Tea",1.25d);
        items.put("Rooibos Tea", 1.75d);
        //Coffee Prices
        items.put("Regular Coffee",1.25d);
        items.put("Vanilla Coffee",1.5d);
        items.put("Pumpkin Coffee",1.6d);
        items.put("Hazelnut Coffee", 1.7d);
        //Frappuccino Prices
        items.put("Chocolate Frap",3.25d);
        items.put("Vanilla Frap",3.5d);
        items.put("Caramel Frap",3.6d);
        items.put("Mint Frap",3.7d);
        //Latte Price
        items.put("Hazelnut Latte",3.25d);
        items.put("Mocha",3.5d);
        items.put("Vanilla Latte",3.6d);
        items.put("Caramel Latte",3.6d);
		
		//coupon code
		couponcode.put("linyuan", 0.1d);
		couponcode.put("xichen", 0.27d);
		couponcode.put("samdawson", 0.2d);
		couponcode.put("claudiazamudio", 0.25d);
		couponcode.put("halliemillin", 0.3d);

	}
	


	private String showInputDialog(){
		String inputValue = JOptionPane.showInputDialog("Please enter cash received. Must larger than or equal to $"+txtAmountDue.getText());
		//if not valid, showInputDialog Method would re-run until
	    if(!inputValue.matches("[0-9.]*"))
	    {	
	    	
	        inputValue = showInputDialog();
	    }

	    return inputValue;
	}

	
	public void addOrderItem(String itemname){
		
		connection = sqliteConnection.dbConnector();		
		OrderItem oi = new OrderItem(itemname,items.get(itemname));
		
		
		if (isOrderEmpty){
			Order newOrder = new Order();	
			orders.add(newOrder);
			isOrderEmpty=false;
			newOrder.orderitems.add(oi);
			oidata.addElement(oi);
			System.out.println(newOrder.getSubtotal());
			updateitemlabel(newOrder);
			
			
			
		}
		else {
			Order currOrder = orders.get(orders.size()-1);
			boolean isrepeat=false;
			int position = 0;
			for (int i = 0;i<currOrder.orderitems.size();++i){
				if (currOrder.orderitems.get(i).name==itemname){
					isrepeat = true;
					position=i;
					break;					
				}
			}
			
			if(isrepeat){
				OrderItem selectedoi = currOrder.orderitems.get(position);
				selectedoi.quantity++;
				oidata.remove(position);
				oidata.insertElementAt(selectedoi, position);	
				updateitemlabel(currOrder);
			}
			else {
				currOrder.orderitems.add(oi);
				oidata.addElement(oi);
				updateitemlabel(currOrder);
			}
		}	
		
	}
	
	private void drawLine(){
        ta.append("------------------------------------------------------------\n");
	}

	private void updateitemlabel(Order currOrder) {
		System.out.println("updateitemlabel runned");
		lblDiscount.setText("($"+currOrder.getDiscount()+")");
		lblSubTotal.setText("$"+currOrder.getSubtotal());
		lblTax.setText("$"+currOrder.getTax());
		lblTotal.setText("$"+currOrder.getTotal());
	

	}
	
	private void clearOrder(){
		oidata.removeAllElements();
		isOrderEmpty = true;
		txtAmountDue.setText("");
		txtAmountTendered.setText("");
		txtChange.setText("");
		c1.show(pnlContainer, "Menu");
		
	}
	
	private void triggerReceipt() {
		 Receipt.setSize(500,500);
         Receipt.setVisible(true);                 
         Receipt.getContentPane().add(ta, BorderLayout.CENTER);
         JButton emailBtn = new JButton("Email Reciept");
         ta.setText("");
         ta.setFont(new Font("Dialog", Font.PLAIN, 30));
         drawLine();
         ta.append("\tiCoffeeShoppe\n");
         ta.append("\tRECIEPT\n");
         drawLine();
         Receipt.getContentPane().add(emailBtn, BorderLayout.SOUTH);
            emailBtn.addActionListener(new ActionListener(){
                 @Override
                 public void actionPerformed(ActionEvent e) {

                     String report$=ta.getText();
                     String mailto="Claudia_zamudio@baylor.edu?SUBJECT=Reciept [iCoffee Shoppe]&BODY=" + report$;

                     URI uri=null;

                     try{
                         uri=new URI("mailto", mailto, null);
                         try {
                             desktop.mail(uri);
                         } catch (IOException e1) {
                             // TODO Auto-generated catch block
                             e1.printStackTrace();
                         }
                     }catch (URISyntaxException e1){
                         e1.printStackTrace();

                     }
                 }

             });
	}
	
	@SuppressWarnings("serial")
	class ReceiptsPanel extends JPanel implements Printable {

	Font f = new Font("Helvetica", Font.PLAIN, 10);

	/**
	* paintComponent() contains the code to draw the receipt:
	*/
	public void paintComponent(Graphics graphics) {
	super.paintComponent(graphics);


	this.setLayout(null);

	// make it smooth:
	Graphics2D g2d = (Graphics2D) graphics;
	RenderingHints hints = new RenderingHints(
	RenderingHints.KEY_TEXT_ANTIALIASING,
	RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
	g2d.setRenderingHints(hints);



	// date/time stamp:
	Calendar cal = new GregorianCalendar();
	g2d.setColor(Color.BLACK);
	g2d.setFont(new Font("Helvetica", Font.PLAIN, 12));
	g2d.drawString(" iCoffee Shoppe Reciept", 24, 70);
	g2d.drawString((cal.get(Calendar.MONTH) + 1) 
	+ "/" + cal.get(Calendar.DAY_OF_MONTH) 
	+ "/" + cal.get(Calendar.YEAR)
	+ "  " + cal.get(Calendar.HOUR_OF_DAY) 
	+ ":" + cal.get(Calendar.MINUTE) 
	+ " " + (cal.get(Calendar.AM_PM) == 0 ? "AM" : "PM"), 66, 90);


//	// list of ordered items:
//	JLabel[] labelstoo = new JLabel[orders.size()];
//	for (int i = 0; i < orders.size(); i++) {
//	labelstoo[i] = new JLabel("" + (i + 1) + ". " +  orders.get(i).toShortString());
//	labelstoo[i].setFont(f);
//	labelstoo[i].setBounds(10, 110+(i*12), 240, 12);
//	add(labelstoo[i]);
//	}


	// total:
	g2d.setFont(new Font("Helvetica", Font.PLAIN, 18));


	g2d.drawString("Total: " + txtAmountDue.getText(), 74, getHeight()-55);


	// slogan:
	g2d.setFont(f.deriveFont(Font.ITALIC, 15));
	g2d.drawString("Have a blessed day!", 47, getHeight()-30);

	}

	/**
	* print() is required when we implement printable:
	*/
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
	throws PrinterException {
		if (pageIndex > 0) {
			return(NO_SUCH_PAGE);
		} else {
			Graphics2D g2d = (Graphics2D) graphics;
			g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			paint(g2d);
			return(PAGE_EXISTS);
			}
		}


	}
	
	
		
		
}
