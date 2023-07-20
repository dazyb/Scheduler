package model;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Temp {
	PropertiesConfiguration conf;
	public PropertiesConfiguration configuration() throws ConfigurationException {
		conf = new PropertiesConfiguration("src/temp.properties");
		return conf;
	}
	
	public void setPositionNumber(int number) throws ConfigurationException {
		conf = configuration();
		conf.setProperty("position", number);
	}
	
	public int getPositionNumber() throws ConfigurationException {
		conf = configuration();
		return Integer.parseInt(conf.getProperty("position").toString());
	}
	
	
}
