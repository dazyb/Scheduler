package application;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class Login {
	//read username from user.properties
	public static String Username() {
		String username=null;
		try {
			File configFile=new File("src/user.properties");
			FileReader reader=new FileReader(configFile);
			Properties props=new Properties();
			props.load(reader);
			username=props.getProperty("username");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return username;
		
	}
	//read password from user.properties
	public static String Password() {
		String password=null;
		try {
			File configFile=new File("src/user.properties");
			FileReader reader=new FileReader(configFile);
			Properties props=new Properties();
			props.load(reader);
			password=props.getProperty("password");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return  password;
	}	
	//compares entered credentials
	public static boolean isVerified(String username, String password) {
		return ((Username().equals(username) && Password().equals(password))?true:false);
	}
	
//	
//	public static void main(String[] args) {
//		System.out.println(Username());
//	}
}
