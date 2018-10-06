package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


// public class readProp extends LoginCredentials{
public class EnvProperties extends Locators{

	private static Properties configProp = new Properties();
	private static String propFilePath;
	private static File file;
	private static FileInputStream fileInput;
	public static String key;
	public static String value;	
	
	public static String getEnv(String appUrl){
		logInfo("inside getEnv() method.");
		String urlParts[] = appUrl.split("/");		
		String envName = urlParts[2];
		String envParts[] = envName.split("\\.");		
		return envParts[0];
}

public static String getLocatorForEnvironment(String appUrl, String locatorName) throws IOException{
	logInfo("inside getLocatorForEnvironment() method.");
	String envName = getEnv(appUrl);	
	propFilePath = projectPath+"\\properties\\"+envName +".properties";
	
	try {
		file = new File(propFilePath);
		fileInput = new FileInputStream(file);
		configProp.load(fileInput);
		// fileInput.close();

		Enumeration enuKeys = configProp.keys();
		while (enuKeys.hasMoreElements()) {
			key = (String) enuKeys.nextElement();
			if(key.trim().equalsIgnoreCase(locatorName)){
				value = configProp.getProperty(key);			
				break;
			}
		}
	
	} catch (FileNotFoundException e) {
			e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
			return value;
}

public void setLocatorForEnvironment(String appUrl, String propertyName, String locatorValue) throws IOException, ConfigurationException {
	logInfo("inside setLocatorForEnvironment() method.");
	String envName = getEnv(appUrl);
	propFilePath = projectPath + "\\properties\\" + envName + ".properties";
	PropertiesConfiguration conf = new PropertiesConfiguration(propFilePath);
	conf.setProperty(propertyName, locatorValue);
	conf.save();
}


private static void logInfo(String string) {
	// TODO Auto-generated method stub
	
}	
}
