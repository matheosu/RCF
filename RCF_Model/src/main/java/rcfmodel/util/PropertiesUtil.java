package rcfmodel.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public  abstract class PropertiesUtil {

	private PropertiesUtil(){}
	

	public static String getValue(String path, String key){
		if(path== null)
			return null;
		Properties properties = new Properties();
		InputStream is = PropertiesUtil.class.getResourceAsStream(path);
		try {
			properties.load(is);
		} catch (IOException ioE) {
			System.out.println("PropertiesUtil: File not found " + ioE.getMessage());
			ioE.printStackTrace();
		}
		try {
			is.close();
		} catch (IOException ioE) {
			System.out.println("PropertiesUtil: " + ioE.getMessage());
			ioE.printStackTrace();
		}
		
		if(key==null)
			return null;
		
		return properties.getProperty(key);
	}
	
	public static boolean setValue(String path, String key, String value){
		if(path == null){
			System.out.println("PropertiesUtil: Path is null");
			return false;
		}
		
		Properties properties = new Properties();
		InputStream is = PropertiesUtil.class.getResourceAsStream(path);
		try {
			properties.load(is);
		} catch (IOException ioE) {
			System.out.println("PropertiesUtil: File not found " + ioE.getMessage());
			ioE.printStackTrace();
			return false;
		}
		try {
			is.close();
		} catch (IOException ioE) {
			System.out.println("PropertiesUtil: " + ioE.getMessage());
			ioE.printStackTrace();
			return false;
		}

		
		if(key== null && value==null){
			System.out.println("PropertiesUtil: Key or Value is null");
			return false;
		}

		properties.setProperty(key, value);
		return true;
	}
}


