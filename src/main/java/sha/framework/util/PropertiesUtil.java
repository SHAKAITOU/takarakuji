package sha.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import sha.framework.exception.TKRKException;

public class PropertiesUtil {
	
	private ResourceBundle bundle;


	public PropertiesUtil(InputStream input) {
		try {
			bundle = new PropertyResourceBundle(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new TKRKException(e.getMessage());
		}
	}
	
	public PropertiesUtil(File file) {
		try {
			bundle = new PropertyResourceBundle(new FileInputStream(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new TKRKException(e.getMessage());
		}
	}
	
	public String getProperty(String key) {
		String test = bundle.getString(key);
		try {
			test = new String( test.getBytes("8859_1"),"UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new TKRKException(e.getMessage());
		}

		return test;
	}
	
	public Enumeration<String> getProperties() {
		return bundle.getKeys();
	}
	
	public boolean containsKey(String key) {
		return bundle.containsKey(key);
	}
}
