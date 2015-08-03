package com.fogthecatman.currency;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Enumeration;


public class ViewerProperties {
	
	private Properties properties = new Properties();
	
	public ViewerProperties(){
		getPropertiesFile();
	}
	
	
	public void getPropertiesFile(){
		
		try{
			
			File propFile = new File("res/config/config.properties");
			FileInputStream fileInput = new FileInputStream(propFile);
			properties.load(fileInput);
			fileInput.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void writeProperties(String user, String amount){
		
		try {
			properties.setProperty(user, amount);

			File file = new File("res/config/config.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "Viewer Coins");
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public int getCoins(String user)
	{
		int coins = 0;
		String coinString = "";
		coinString = properties.getProperty(user);				
		coins = Integer.parseInt(coinString);		
		return coins;
	}
	
	public void setCoins(String user, String coins)
	{
		properties.setProperty(user, coins);
	}
	
	public boolean containsPerson(String user)
	{
		if(properties.containsKey(user))
			return true;
		else
			return false;
	}
	
	public void addPoints(int amtAddPoints, ArrayList<String> peoples)
	{
		System.out.println("Adding points to config file");
		
		Enumeration enuKeys = properties.keys();
		while (enuKeys.hasMoreElements()) 
		{
			String key = (String) enuKeys.nextElement();
			String value = properties.getProperty(key);
			
			if(peoples.contains(key))
			{
				int valueAmt = Integer.parseInt(value);
				valueAmt += amtAddPoints;
				properties.setProperty(key,"" + valueAmt);
				System.out.println(key + ": " + valueAmt);
			}
			
		}
	}
}
