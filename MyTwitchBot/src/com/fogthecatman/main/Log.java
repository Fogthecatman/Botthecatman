package com.fogthecatman.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Log {
	
	public Log(){
		//Default 
	}

	
	public void writeLog()
	{	
		try
		{
			
			String chatLogString = GUI.chatBox.getText();
			String[] chatLogArray = chatLogString.split("\\[");
					
			String fileName = getDate() + ".txt";
			
			File newFile = new File("res/logs/" + fileName);
			newFile.createNewFile();
			
			FileWriter f = new FileWriter(newFile, true);
			BufferedWriter logSave = new BufferedWriter(f);
			
			logSave.write("==================================");
			logSave.newLine();
			logSave.write("= " +   getDate()             + "=");
			logSave.newLine();
			logSave.write("==================================");
			logSave.newLine();
			for(int i = 1; i < chatLogArray.length; i++)
			{
					logSave.write(" [" + chatLogArray[i]);
					//System.out.println(" [" + chatLogArray[i]);
					logSave.newLine();
			}
			logSave.close();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public String getDate()
	{
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("EEE,-d-MMM-yyyy-HH-mm");
    	String dateAndTime = "[" + sdf.format(cal.getTime()) + "]";
    	return dateAndTime;
	}
	

}



