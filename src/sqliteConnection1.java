import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;
public class sqliteConnection1 {
	Connection conn = null;
	
	
	public static Connection dbConnector() {
		// TODO Auto-generated method stub
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:ExpressoData.sqlite");
			System.out.println("Connection built with expressodata.sqlite");
			return conn;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			System.out.println("Connection not built");
			return null;
		}
	}



	
}
