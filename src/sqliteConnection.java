import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;
public class sqliteConnection {
	Connection conn = null;
	
	
	public static Connection dbConnector() {
		// TODO Auto-generated method stub
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:Expresso.sqlite");
			System.out.println("Connection built");
			return conn;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			System.out.println("Connection not built");
			return null;
		}
	}



	
}
