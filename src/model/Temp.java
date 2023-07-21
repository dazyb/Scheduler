package model;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Temp {
	static PropertiesConfiguration conf;
	public static PropertiesConfiguration configuration(){
		try {
			conf = new PropertiesConfiguration("src/temp.properties");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		return conf;
	}
	
	public static void setPositionNumber(int number, String courseCode){
		conf = configuration();
		conf.setProperty("position", number);
		conf.setProperty("coursecode", courseCode);
		try {
			conf.save();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());;
		}
	}
	
	public static int getPositionNumber(){
		conf = configuration();
		return Integer.parseInt(conf.getProperty("position").toString());
	}
	
	public static String getCourseCode(){
		conf = configuration();
		return conf.getProperty("coursecode").toString();
	}
	
//	public static void main(String[] args){
//		Temp.setPositionNumber(57);
//		System.out.println(Temp.getPositionNumber());
//	}
}
