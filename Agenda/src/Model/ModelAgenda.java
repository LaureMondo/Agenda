package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.DefaultListModel;

public class ModelAgenda extends DefaultListModel<String> {
	
	private Properties contacts;
	private String propertiesFileLocation = "myContacts.properties";

	public ModelAgenda() {
		super();
		this.contacts = new Properties();
		getProperties();
	}	
	
	public void saveProperties() {
		try(OutputStream out = new FileOutputStream(propertiesFileLocation)){
			contacts.store(out, "contacts");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addProperty(String key, String value) {
		contacts.setProperty(key, value);
		//saveProperties(); mettre quand quitte
	}
	
	public void addProperty(String key) {
		contacts.setProperty(key, "");
		//saveProperties(); mettre quand quitte
	}
	
	public boolean deleteProperty(String keyOld, String keyNew, String value) {
		if(contacts.containsKey(keyOld)) {			
			contacts.remove(keyOld);
			return true;
		}
		return false;
	}
	
	public boolean changeProperty(String key, String value) {
		if(contacts.containsKey(key)) {			
			contacts.setProperty(key, value);
			return true;
		}
		return false;
	}
	
	private void getProperties() {
		try(InputStream in = new FileInputStream(propertiesFileLocation)) {
			contacts.load(in);
			for (Object propriete : contacts.keySet()) {
				addElement(propriete.toString());
			}
		} catch(IOException el) {
			el.printStackTrace();
		}
	}
	
	public String getValue(String key) {
		return contacts.getProperty(key);
	}
}
