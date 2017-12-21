package agenda;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Test {
	
	public static void storeJVM() {
		String propertiesFileLocation = "myProperties.properties";
		
		Properties properties = System.getProperties();
		
		try(OutputStream out = new FileOutputStream(propertiesFileLocation)){
			properties.store(out, "properties");
			System.out.println("Fichié crée");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
