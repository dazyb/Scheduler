package model;


import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Temp {
	static PropertiesConfiguration config;
	public static PropertiesConfiguration configuration() {
		try {
			config = new PropertiesConfiguration("src/temp.properties");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return config;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	static PropertiesConfiguration conf;
//	public static PropertiesConfiguration configuration(){
//		try {
//			conf = new PropertiesConfiguration("src/temp.properties");
//		} catch (ConfigurationException e) {
//			// TODO Auto-generated catch block
//			System.err.println(e.getMessage());
//		}
//		return conf;
//	}
//	
//	static Connection conn = null;
//	public static Connection connectdb() {
//		try {
//			Connection conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TimeTable;integratedSecurity=true");
//			return conn; 
//		}
//		catch(Exception e) {
//			JOptionPane.showMessageDialog(null, e);
//			System.err.println(e.getMessage());
//			
//			return null;
//		}
//	}
//	
//	public static ArrayList<String> getDepartment() {
//		conn = connectdb();
//		ArrayList<String> dept = new ArrayList<String>();
//		String query = "select distinct Department from SecondSemester";
//		try {
//			PreparedStatement ps = conn.prepareStatement(query);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				dept.add(rs.getString("Department"));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return dept;
//	}
//	
//	
//	public static void setPropertyList(String $name, ArrayList<String> $value) throws ConfigurationException {
//		conf = configuration();
//		conf.setProperty($name, $value);
//		conf.save();	}
//	
//	public static String getPropertyValue(String $name) {
//		conf = configuration();
//		return conf.getProperty($name).toString();
//	}
//	
//	public static void main(String[] args) throws ConfigurationException {
//		setPropertyList ("dept_list", getDepartment());
//		System.out.println(getPropertyValue("dept_list"));
//	}
	
}
